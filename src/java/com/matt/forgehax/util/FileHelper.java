//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import com.google.common.io.*;
import joptsimple.internal.*;
import javax.annotation.*;
import java.util.regex.*;
import java.io.*;
import java.nio.file.*;

public class FileHelper
{
    private static final Pattern PATTERN_PACKAGE_FROM_PATH;
    private static final Pattern PATTERN_JAR_DIR_FROM_PATH;
    private static final Pattern PATTERN_FILE_SEPARATORS;
    
    public static String getFileExtension(final String fullName) {
        return Files.getFileExtension(fullName);
    }
    
    public static String getFileExtension(final File file) {
        return getFileExtension(file.getName());
    }
    
    public static String getFileExtension(final Path path) {
        return getFileExtension(path.getFileName().toString());
    }
    
    public static String getNameWithoutExtension(final String file) {
        return Files.getNameWithoutExtension(file);
    }
    
    public static String getNameWithoutExtension(final File file) {
        return getNameWithoutExtension(file.getName());
    }
    
    public static String getNameWithoutExtension(final Path path) {
        return getNameWithoutExtension(path.getFileName().toString());
    }
    
    public static String getPathWithoutExtension(final String path) {
        final String ext = getFileExtension(path);
        return Strings.isNullOrEmpty(ext) ? path : path.substring(0, path.lastIndexOf("." + ext));
    }
    
    public static String getPathWithoutExtension(final File file) {
        return getPathWithoutExtension(file.getPath());
    }
    
    public static String getPathWithoutExtension(final Path path) {
        return getPathWithoutExtension(path.toString());
    }
    
    public static String asPackagePath(@Nullable final String filePath) {
        if (filePath == null) {
            return "";
        }
        String str = getPathWithoutExtension(filePath);
        str = FileHelper.PATTERN_FILE_SEPARATORS.matcher(str).replaceAll(".");
        if (str.startsWith(".")) {
            str = str.substring(1);
        }
        if (str.endsWith(".")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
    
    public static String asPackagePath(final File file) {
        return asPackagePath(file.getPath());
    }
    
    public static String asPackagePath(final Path path) {
        return asPackagePath(path.toString());
    }
    
    public static String asFilePath(@Nullable final String packagePath) {
        return (packagePath == null) ? "" : packagePath.replace('.', '/');
    }
    
    public static String getPackageFromFullPath(final String file) {
        final Matcher matcher = FileHelper.PATTERN_PACKAGE_FROM_PATH.matcher(file);
        return matcher.find() ? matcher.group(1) : null;
    }
    
    public static String getJarPathFromFullPath(final String file) {
        final Matcher matcher = FileHelper.PATTERN_JAR_DIR_FROM_PATH.matcher(file);
        return matcher.find() ? matcher.group(1) : null;
    }
    
    public static FileSystem newFileSystem(final Path filePath, final ClassLoader parent) throws IOException {
        return FileSystems.newFileSystem(filePath, parent);
    }
    
    public static FileSystem newFileSystem(final File file, final ClassLoader parent) throws IOException {
        return FileSystems.newFileSystem(file.toPath(), parent);
    }
    
    public static FileSystem newFileSystem(final String filePath, final ClassLoader parent) throws IOException {
        return FileSystems.newFileSystem(Paths.get(filePath, new String[0]), parent);
    }
    
    public static FileSystem newFileSystem(final Path filePath) throws IOException {
        return newFileSystem(filePath, null);
    }
    
    public static FileSystem newFileSystem(final File file) throws IOException {
        return newFileSystem(file, null);
    }
    
    public static FileSystem newFileSystem(final String filePath) throws IOException {
        return newFileSystem(filePath, null);
    }
    
    static {
        PATTERN_PACKAGE_FROM_PATH = Pattern.compile("[.]jar[!][\\/|\\\\](.*)");
        PATTERN_JAR_DIR_FROM_PATH = Pattern.compile("(.*.jar)");
        PATTERN_FILE_SEPARATORS = Pattern.compile("[\\\\|\\/]");
    }
}
