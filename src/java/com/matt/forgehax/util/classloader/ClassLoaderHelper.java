//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.classloader;

import com.google.common.collect.*;
import java.io.*;
import com.matt.forgehax.util.*;
import java.util.stream.*;
import java.nio.file.*;
import sun.net.www.protocol.file.*;
import java.util.*;
import java.net.*;
import java.util.function.*;
import java.util.jar.*;

public class ClassLoaderHelper
{
    private static void searchDirectory(final Path directory, final Function<Path, Boolean> function) {
        Optional.ofNullable(directory).filter(x$0 -> Files.exists(x$0, new LinkOption[0])).filter(x$0 -> Files.isDirectory(x$0, new LinkOption[0])).ifPresent(dir -> {
            try {
                Files.list(dir).forEach(path -> {
                    if (function.apply(path)) {
                        searchDirectory(path, function);
                    }
                });
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    
    public static List<Path> getJarPathsInDirectory(final Path directory, final boolean recursive) {
        final List<Path> results = (List<Path>)Lists.newArrayList();
        final List<Path> list;
        searchDirectory(directory, path -> {
            if (Files.isDirectory(path, new LinkOption[0])) {
                return Boolean.valueOf(recursive);
            }
            else if (FileHelper.getFileExtension(path).equals("jar")) {
                return Boolean.valueOf(list.add(directory));
            }
            else {
                return Boolean.valueOf(false);
            }
        });
        return results;
    }
    
    public static List<Path> getJarPathsInDirectory(final File directory, final boolean recursive) {
        return getJarPathsInDirectory(directory.toPath(), recursive);
    }
    
    public static List<Path> getJarPathsInDirectory(final String directory, final boolean recursive) {
        return getJarPathsInDirectory(Paths.get(directory, new String[0]), recursive);
    }
    
    public static List<Path> getJarPathsInDirectory(final String directory) {
        final boolean recursive = directory.endsWith("/*") || directory.endsWith("\\*");
        return getJarPathsInDirectory(Paths.get(recursive ? directory.substring(0, directory.length() - 2) : directory, new String[0]), recursive);
    }
    
    public static List<Path> getClassPathsInDirectory(final Path directory, final boolean recursive) {
        final List<Path> results = (List<Path>)Lists.newArrayList();
        final List<Path> list;
        searchDirectory(directory, path -> {
            if (Files.isDirectory(path, new LinkOption[0])) {
                return Boolean.valueOf(recursive);
            }
            else {
                if (FileHelper.getFileExtension(path).equals("class")) {
                    list.add(path);
                }
                return Boolean.valueOf(false);
            }
        });
        return results;
    }
    
    public static List<Path> getClassPathsInDirectory(final File directory, final boolean recursive) {
        return getClassPathsInDirectory(directory.toPath(), recursive);
    }
    
    public static List<Path> getClassPathsInDirectory(final String directory, final boolean recursive) {
        return getClassPathsInDirectory(Paths.get(directory, new String[0]), recursive);
    }
    
    public static List<Path> getClassPathsInDirectory(final String directory) {
        final boolean recursive = directory.endsWith("/*") || directory.endsWith("\\*");
        return getClassPathsInDirectory(Paths.get(recursive ? directory.substring(0, directory.length() - 2) : directory, new String[0]), recursive);
    }
    
    public static List<Path> getClassPathsInJar(final JarFile jarFile, final String packageDir, final boolean recursive) throws IOException {
        Objects.requireNonNull(jarFile);
        Objects.requireNonNull(packageDir);
        final FileSystem fs = FileHelper.newFileSystem(jarFile.getName());
        final Path root = fs.getRootDirectories().iterator().next();
        final Path packagePath = root.resolve(packageDir);
        final Path path2;
        return Streamables.enumerationStream(jarFile.entries()).map(entry -> root.resolve(entry.getName())).filter(path -> FileHelper.getFileExtension(path).equals("class")).filter(path -> recursive || path.getNameCount() == path2.getNameCount() + 1).filter(path -> path.toString().startsWith(path.getFileSystem().getSeparator() + packageDir) && path.toString().length() > packageDir.length() + 2).collect((Collector<? super Object, ?, List<Path>>)Collectors.toList());
    }
    
    public static List<Path> getClassPathsInJar(final File file, final String packagePath, final boolean recursive) throws IOException {
        Objects.requireNonNull(file);
        return getClassPathsInJar(new JarFile(file), packagePath, recursive);
    }
    
    public static List<Path> getClassPathsInJar(final Path path, final String packagePath, final boolean recursive) throws IOException {
        Objects.requireNonNull(path);
        return getClassPathsInJar(path.toFile(), packagePath, recursive);
    }
    
    public static List<Path> getClassPathsInJar(final JarFile jarFile, final String packageDir) throws IOException {
        final boolean recursive = packageDir.endsWith(".*");
        return getClassPathsInJar(jarFile, recursive ? packageDir.substring(0, packageDir.length() - 2) : packageDir, recursive);
    }
    
    public static List<Path> getClassPathsInJar(final File file, final String packagePath) throws IOException {
        Objects.requireNonNull(file);
        return getClassPathsInJar(new JarFile(file.getPath()), packagePath);
    }
    
    public static List<Path> getClassPathsInJar(final Path path, final String packagePath) throws IOException {
        Objects.requireNonNull(path);
        return getClassPathsInJar(path.toFile(), packagePath);
    }
    
    public static List<Path> getClassPathsInPackage(final ClassLoader classLoader, final String packageDir, final boolean recursive) throws IOException {
        Objects.requireNonNull(packageDir);
        Objects.requireNonNull(classLoader);
        final List<Path> results = (List<Path>)Lists.newArrayList();
        final String pkgdir = FileHelper.asFilePath(packageDir);
        final Enumeration<URL> inside = classLoader.getResources(pkgdir);
        URLConnection connection;
        String path;
        String path2;
        final String s;
        String rootDir;
        String packDir;
        Path root;
        final List<Object> list;
        Streamables.enumerationStream(inside).forEach(url -> {
            try {
                connection = url.openConnection();
                path = URLDecoder.decode(url.getPath(), "UTF-8").replace('\\', '/');
                path2 = path.substring(path.indexOf(47) + 1);
                if (!System.getProperty("os.name").startsWith("Windows")) {
                    path2 = "/" + path2;
                }
                rootDir = path2.substring(0, path2.indexOf(s));
                packDir = path2.substring(path2.lastIndexOf(s));
                if (connection instanceof FileURLConnection) {
                    root = Paths.get(rootDir, new String[0]).normalize();
                    getClassPathsInDirectory(path2, recursive).stream().map((Function<? super Object, ?>)root::relativize).forEach(list::add);
                }
                else if (connection instanceof JarURLConnection) {
                    list.addAll(getClassPathsInJar(((JarURLConnection)connection).getJarFile(), packDir, recursive));
                }
                else {
                    throw new UnknownConnectionType();
                }
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            return;
        });
        return results;
    }
    
    public static List<Path> getClassPathsInPackage(final ClassLoader classLoader, final String packageDir) throws IOException {
        final boolean recursive = packageDir.endsWith(".*");
        return getClassPathsInPackage(classLoader, recursive ? packageDir.substring(0, packageDir.length() - 2) : packageDir, recursive);
    }
    
    public static List<Class<?>> getLoadedClasses(final ClassLoader classLoader, final Collection<Path> paths) {
        Objects.requireNonNull(classLoader);
        Objects.requireNonNull(paths);
        return paths.stream().map(path -> {
            try {
                return Class.forName(FileHelper.asPackagePath(path), false, classLoader);
            }
            catch (ClassNotFoundException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect((Collector<? super Object, ?, List<Class<?>>>)Collectors.toList());
    }
    
    public static class UnknownConnectionType extends Exception
    {
    }
}
