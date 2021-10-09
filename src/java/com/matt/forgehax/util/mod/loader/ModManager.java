//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.mod.loader;

import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.*;
import com.google.common.collect.*;
import java.io.*;
import com.matt.forgehax.util.classloader.*;
import fuck.you.jewtricks.base.*;
import java.nio.file.*;
import com.matt.forgehax.util.*;
import java.util.stream.*;
import java.util.*;
import java.util.function.*;
import javax.annotation.*;
import java.lang.annotation.*;

public class ModManager extends AbstractClassLoader<BaseMod> implements Globals
{
    private static final ModManager INSTANCE;
    private final Set<Class<? extends BaseMod>> classes;
    private final Set<BaseMod> active;
    
    public ModManager() {
        this.classes = (Set<Class<? extends BaseMod>>)Sets.newHashSet();
        this.active = (Set<BaseMod>)Sets.newTreeSet((Comparator)Comparator.comparing((Function<? super Object, ?>)BaseMod::getModName, (Comparator<? super Object>)String.CASE_INSENSITIVE_ORDER));
    }
    
    public static ModManager getInstance() {
        return ModManager.INSTANCE;
    }
    
    public boolean searchPackage(final String packageDir) {
        try {
            return this.classes.addAll(this.filterClassPaths((ClassLoader)getFMLClassLoader(), (Collection)ClassLoaderHelper.getClassPathsInPackage((ClassLoader)getFMLClassLoader(), packageDir)));
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean searchPlugin(final Path jar, final String packageDir) {
        if (!Files.exists(jar, new LinkOption[0])) {
            throw new IllegalArgumentException("path must lead to an existing jar file");
        }
        try {
            final FileSystem fs = FileHelper.newFileSystem(jar);
            final ClassLoader classLoader = CustomClassLoaders.newFsClassLoader((ClassLoader)getFMLClassLoader(), fs);
            return this.classes.addAll(this.filterClassPaths(classLoader, (Collection)ClassLoaderHelper.getClassPathsInJar(jar, packageDir)));
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean searchPluginDirectory(final Path directory, final String... packageDir) {
        if (packageDir.length > 1) {
            throw new IllegalArgumentException("Path should be contained in first array index");
        }
        if (!Files.exists(directory, new LinkOption[0])) {
            Wrapper.getLog().warn("plugin directory '" + directory.toString() + "' does not exist");
            return false;
        }
        if (!Files.isDirectory(directory, new LinkOption[0])) {
            Wrapper.getLog().warn("path '" + directory.toString() + "' is not a directory");
            return false;
        }
        try {
            return Files.list(directory).filter(x$0 -> Files.isRegularFile(x$0, new LinkOption[0])).filter(path -> FileHelper.getFileExtension(path).equals("jar")).anyMatch(path -> this.searchPlugin(path, (String)ArrayHelper.getOrDefault((Object[])packageDir, 0, (Object)"")));
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean searchPluginDirectory(final String directory, final String... packageDir) {
        return this.searchPluginDirectory(Paths.get(directory, new String[0]), packageDir);
    }
    
    private Stream<Class<? extends BaseMod>> unloadedClasses() {
        return this.classes.stream().filter(clazz -> this.active.stream().noneMatch(mod -> mod.getClass().equals(clazz)));
    }
    
    private Stream<Class<? extends BaseMod>> loadedClasses() {
        return this.classes.stream().filter(clazz -> this.active.stream().anyMatch(mod -> mod.getClass().equals(clazz)));
    }
    
    public Collection<Class<? extends BaseMod>> getUnloadedClasses() {
        return this.unloadedClasses().collect((Collector<? super Class<? extends BaseMod>, Object, Collection<Class<? extends BaseMod>>>)Immutables.toImmutableList());
    }
    
    public Collection<Class<? extends BaseMod>> getLoadedClasses() {
        return this.loadedClasses().collect((Collector<? super Class<? extends BaseMod>, Object, Collection<Class<? extends BaseMod>>>)Immutables.toImmutableList());
    }
    
    public Collection<BaseMod> getMods() {
        return Collections.unmodifiableCollection((Collection<? extends BaseMod>)this.active);
    }
    
    public Optional<? extends BaseMod> get(final String modName) {
        return (Optional<? extends BaseMod>)this.active.stream().filter(mod -> mod.getModName().equalsIgnoreCase(modName)).findFirst();
    }
    
    public <T extends BaseMod> Optional<T> get(final Class<T> clazz) {
        return this.active.stream().filter(mod -> Objects.equals(clazz, mod.getClass())).map(mod -> mod).findFirst();
    }
    
    public void load(final Class<? extends BaseMod> clazz) {
        this.unloadedClasses().filter(clazz::equals).findFirst().ifPresent(this::_load);
    }
    
    private void _load(final Class<? extends BaseMod> clazz) {
        if (this.active.add((BaseMod)this.loadClass((Class)clazz))) {
            Wrapper.getLog().info("Loaded mod " + clazz.getSimpleName());
        }
    }
    
    public void loadAll() {
        this.unloadedClasses().forEach(this::_load);
    }
    
    public void unload(final BaseMod mod) {
        if (this.active.remove(mod)) {
            mod.unload();
        }
    }
    
    public void unloadAll() {
        this.active.forEach(this::unload);
    }
    
    public void refresh() {
        this.forEach(BaseMod::unload);
        this.forEach(BaseMod::load);
    }
    
    public void forEach(final Consumer<BaseMod> consumer) {
        this.active.forEach(consumer);
    }
    
    @Nullable
    public Class<BaseMod> getInheritedClass() {
        return BaseMod.class;
    }
    
    @Nullable
    public Class<? extends Annotation> getAnnotationClass() {
        return RegisterMod.class;
    }
    
    static {
        INSTANCE = new ModManager();
    }
}
