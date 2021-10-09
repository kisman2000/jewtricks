//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.classloader;

import javax.annotation.*;
import java.lang.annotation.*;
import java.nio.file.*;
import java.util.function.*;
import java.util.stream.*;
import java.io.*;
import java.util.*;
import fuck.you.jewtricks.base.*;
import java.lang.reflect.*;
import net.minecraft.launchwrapper.*;

public abstract class AbstractClassLoader<E>
{
    protected AbstractClassLoader() {
    }
    
    @Nullable
    public abstract Class<E> getInheritedClass();
    
    @Nullable
    public abstract Class<? extends Annotation> getAnnotationClass();
    
    public Collection<Class<? extends E>> filterClassPaths(final ClassLoader classLoader, final Collection<Path> classPaths) throws IOException {
        return ClassLoaderHelper.getLoadedClasses(classLoader, classPaths).stream().filter((Predicate<? super Object>)this::checkAnnotation).filter((Predicate<? super Object>)this::checkInheritedClass).map((Function<? super Object, ?>)this::wildCast).filter((Predicate<? super Object>)this::valid).collect((Collector<? super Object, ?, Collection<Class<? extends E>>>)Collectors.toList());
    }
    
    public Collection<? extends E> loadClasses(final Collection<Class<? extends E>> classes) {
        return classes.stream().map((Function<? super Class<? extends E>, ?>)this::create).filter(Objects::nonNull).collect((Collector<? super Object, ?, Collection<? extends E>>)Collectors.toList());
    }
    
    public E loadClass(final Class<? extends E> clazz) {
        return (E)this.loadClasses(Collections.singleton(clazz)).stream().findFirst().orElse((E)null);
    }
    
    protected boolean valid(final Class<? extends E> clazz) {
        try {
            return clazz.getDeclaredConstructor((Class<?>[])new Class[0]) != null;
        }
        catch (NoSuchMethodException e) {
            Wrapper.getLog().error("Class has no default constructor");
            return false;
        }
    }
    
    protected E create(final Class<? extends E> clazz) {
        try {
            return (E)clazz.getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            Wrapper.getLog().error("Failed to initialize class " + clazz.getSimpleName() + ": " + e.getClass().getSimpleName() + " - " + e.getMessage() + " - caused by: " + e.getCause());
            e.printStackTrace();
            return null;
        }
    }
    
    private Class<? extends E> wildCast(final Class<?> clazz) {
        return (Class<? extends E>)clazz;
    }
    
    private boolean checkAnnotation(final Class<?> clazz) {
        return this.getAnnotationClass() == null || clazz.isAnnotationPresent(this.getAnnotationClass());
    }
    
    private boolean checkInheritedClass(final Class<?> clazz) {
        return this.getInheritedClass() == null || this.getInheritedClass().isAssignableFrom(clazz);
    }
    
    public static LaunchClassLoader getFMLClassLoader() {
        return Launch.classLoader;
    }
}
