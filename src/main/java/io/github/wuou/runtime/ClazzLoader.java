package io.github.wuou.runtime;

import com.google.common.collect.Maps;
import io.github.wuou.clazz.Clazz;
import io.github.wuou.utils.MapUtils;

import java.io.*;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClazzLoader {

    @SuppressWarnings("unchecked")
    public static Map<String, Clazz> loadClasses() {
        Map<String, Clazz> clazzMap = new HashMap();
        try {
            Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources("META-INF/services/io.github.wuou.ClassMap");
            while (files.hasMoreElements()) {
                try {
                    URL url = files.nextElement();
                    if ("file".equals(url.getProtocol())) {
                        String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                        File file = new File(filePath);
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        clazzMap.putAll((Map<String, Clazz>) ois.readObject());
                        ois.close();
                        fis.close();
                    } else if ("jar".equals(url.getProtocol())) {
                        try {
                            JarEntry jarEntry = ((JarURLConnection) url.openConnection()).getJarEntry();
                            JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                            InputStream inputStream = jarFile.getInputStream(jarEntry);
                            ObjectInputStream ois = new ObjectInputStream(inputStream);
                            clazzMap.putAll((Map<String, Clazz>) ois.readObject());
                            ois.close();
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazzMap;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Clazz> loadDependenceClasses() {
        Map<String, Clazz> clazzMap = Maps.newHashMap();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = null;
        try {
            resources = classLoader.getResources("BOOT-INF/lib");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(resources)) {
            return clazzMap;
        }
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if ("jar".equals(url.getProtocol())) {
                try {
                    JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                    Enumeration<URL> classesMapUrls = classLoader.getResources("BOOT-INF/classes/META-INF/services/io.github.wuou.ClassMap");
                    Map<String, Clazz> stringClazzMap = loadClazzMap(classesMapUrls, jarFile);
                    if (!MapUtils.isEmpty(stringClazzMap)) {
                        clazzMap.putAll(stringClazzMap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return clazzMap;
    }

    private static Map<String, Clazz> loadClazzMap(Enumeration<URL> classesMapUrls, JarFile jarFile) throws IOException, ClassNotFoundException {
        while (classesMapUrls.hasMoreElements()) {
            URL classesMapUrl = classesMapUrls.nextElement();
            JarEntry jarEntry = ((JarURLConnection) classesMapUrl.openConnection()).getJarEntry();
            InputStream inputStream = jarFile.getInputStream(jarEntry);
            if (Objects.isNull(inputStream)) {
                continue;
            }
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Map<String, Clazz> res = (Map<String, Clazz>) ois.readObject();
            ois.close();
            inputStream.close();
            if (!MapUtils.isEmpty(res)) {
                return res;
            }
        }
        return null;
    }

}
