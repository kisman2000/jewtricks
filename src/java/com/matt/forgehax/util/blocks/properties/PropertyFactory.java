//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.blocks.properties;

import java.util.*;
import java.util.function.*;
import javax.annotation.*;
import com.google.common.collect.*;

public class PropertyFactory
{
    private static final Map<Class<? extends IBlockProperty>, Supplier<? extends IBlockProperty>> PROPERTY_FACTORY;
    private static final Map<Class<? extends IBlockProperty>, IBlockProperty> CLASS_TO_IMMUTABLE;
    private static final Map<String, Class<? extends IBlockProperty>> HEADING_TO_CLASS;
    
    public static void registerPropertyFactory(final Class<? extends IBlockProperty> clazz, final Supplier<? extends IBlockProperty> factory) {
        final IBlockProperty temp = (IBlockProperty)factory.get();
        PropertyFactory.CLASS_TO_IMMUTABLE.put(clazz, temp.newImmutableInstance());
        PropertyFactory.HEADING_TO_CLASS.put(temp.toString(), clazz);
        PropertyFactory.PROPERTY_FACTORY.put(clazz, factory);
    }
    
    public static <T extends IBlockProperty> T newInstance(final Class<T> clazz) {
        if (clazz != null) {
            final Supplier<? extends IBlockProperty> supplier = PropertyFactory.PROPERTY_FACTORY.get(clazz);
            if (supplier != null) {
                return (T)((IBlockProperty)supplier.get()).cast();
            }
        }
        return null;
    }
    
    public static <T extends IBlockProperty> T newInstance(final String name) {
        return newInstance(getClassByName(name));
    }
    
    public static <T extends IBlockProperty> Class<T> getClassByName(final String name) {
        return (Class<T>)PropertyFactory.HEADING_TO_CLASS.get(name);
    }
    
    @Nullable
    public static <T extends IBlockProperty> T getImmutableInstance(final Class<T> clazz) {
        return (T)((clazz != null) ? PropertyFactory.CLASS_TO_IMMUTABLE.get(clazz).cast() : null);
    }
    
    @Nullable
    public static <T extends IBlockProperty> T getImmutableInstance(final String name) {
        return getImmutableInstance(getClassByName(name));
    }
    
    @Nullable
    public static <T extends IBlockProperty> T newImmutableInstance(final Class<T> clazz) {
        final IBlockProperty immutable = getImmutableInstance((Class<IBlockProperty>)clazz);
        return (T)((immutable != null) ? immutable.newImmutableInstance().cast() : null);
    }
    
    @Nullable
    public static <T extends IBlockProperty> T newImmutableInstance(final String name) {
        return newImmutableInstance(getClassByName(name));
    }
    
    static {
        PROPERTY_FACTORY = Maps.newHashMap();
        CLASS_TO_IMMUTABLE = Maps.newHashMap();
        HEADING_TO_CLASS = Maps.newHashMap();
        registerPropertyFactory((Class<? extends IBlockProperty>)BoundProperty.class, (Supplier<? extends IBlockProperty>)BoundProperty::new);
        registerPropertyFactory((Class<? extends IBlockProperty>)ColorProperty.class, (Supplier<? extends IBlockProperty>)ColorProperty::new);
        registerPropertyFactory((Class<? extends IBlockProperty>)DimensionProperty.class, (Supplier<? extends IBlockProperty>)DimensionProperty::new);
        registerPropertyFactory((Class<? extends IBlockProperty>)TagProperty.class, (Supplier<? extends IBlockProperty>)TagProperty::new);
        registerPropertyFactory((Class<? extends IBlockProperty>)ToggleProperty.class, (Supplier<? extends IBlockProperty>)ToggleProperty::new);
    }
}
