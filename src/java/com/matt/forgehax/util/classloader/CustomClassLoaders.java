//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.classloader;

import java.net.*;
import com.matt.forgehax.util.*;
import java.nio.file.*;
import java.io.*;

public class CustomClassLoaders
{
    public static ClassLoader newFsClassLoader(final ClassLoader parent, final FileSystem fs) throws RuntimeException {
        try {
            return new FsClassLoader(parent, fs);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static class FsClassLoader extends URLClassLoader
    {
        private final Path root;
        
        private FsClassLoader(final ClassLoader parent, final Path path) throws MalformedURLException {
            super(new URL[] { path.toUri().toURL() }, parent);
            this.root = path;
        }
        
        public FsClassLoader(final ClassLoader parent, final FileSystem fileSystem) throws MalformedURLException {
            this(parent, fileSystem.getRootDirectories().iterator().next());
        }
        
        public Path getRoot() {
            return this.root;
        }
        
        public FileSystem getFileSystem() {
            return this.root.getFileSystem();
        }
        
        @Override
        protected Class<?> findClass(final String name) throws ClassNotFoundException {
            try {
                final Path location = this.getRoot().resolve(FileHelper.asFilePath(name).concat(".class"));
                if (Files.exists(location, new LinkOption[0])) {
                    final byte[] classData = Files.readAllBytes(location);
                    return this.defineClass(name, classData, 0, classData.length);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
