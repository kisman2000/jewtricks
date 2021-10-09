//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import java.util.*;
import java.util.function.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.stream.*;

public class FileManager
{
    private static final FileManager INSTANCE;
    private final Path base;
    
    public static FileManager getInstance() {
        return FileManager.INSTANCE;
    }
    
    private static String[] expandPath(final String fullPath) {
        return fullPath.split(":?\\\\\\\\|\\/");
    }
    
    private static Stream<String> expandPaths(final String... paths) {
        return Arrays.stream(paths).map((Function<? super String, ?>)FileManager::expandPath).flatMap((Function<? super Object, ? extends Stream<? extends String>>)Arrays::stream);
    }
    
    private static Path lookupPath(final Path root, final String... paths) {
        return Paths.get(root.toString(), paths);
    }
    
    private static Path getRoot() {
        return Paths.get("", new String[0]);
    }
    
    private static void createDirectory(final Path dir) {
        try {
            if (!Files.isDirectory(dir, new LinkOption[0])) {
                if (Files.exists(dir, new LinkOption[0])) {
                    Files.delete(dir);
                }
                Files.createDirectories(dir, (FileAttribute<?>[])new FileAttribute[0]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static Path getMkDirectory(final Path parent, final String... paths) {
        if (paths.length < 1) {
            return parent;
        }
        final Path dir = lookupPath(parent, paths);
        createDirectory(dir);
        return dir;
    }
    
    private FileManager() {
        getMkDirectory(this.base = getMkDirectory(getRoot(), "jewtricks"), "config");
        getMkDirectory(this.base, "cache");
    }
    
    public Path getBasePath() {
        return this.base;
    }
    
    public Path getBaseResolve(final String... paths) {
        final String[] names = expandPaths(paths).toArray(String[]::new);
        if (names.length < 1) {
            throw new IllegalArgumentException("missing path");
        }
        return lookupPath(this.getBasePath(), names);
    }
    
    public Path getMkBaseResolve(final String... paths) {
        final Path path = this.getBaseResolve(paths);
        createDirectory(path.getParent());
        return path;
    }
    
    public Path getConfig() {
        return this.getBasePath().resolve("config");
    }
    
    public Path getCache() {
        return this.getBasePath().resolve("cache");
    }
    
    public Path getMkBaseDirectory(final String... names) {
        return getMkDirectory(this.getBasePath(), expandPaths(names).collect(Collectors.joining(File.separator)));
    }
    
    public Path getMkConfigDirectory(final String... names) {
        return getMkDirectory(this.getConfig(), expandPaths(names).collect(Collectors.joining(File.separator)));
    }
    
    static {
        INSTANCE = new FileManager();
    }
}
