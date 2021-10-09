//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter;

import javax.annotation.*;
import java.util.*;
import com.google.common.collect.*;

public class TypeConverterRegistry
{
    private static final Map<Class<?>, TypeConverter<?>> REGISTRY;
    
    public static <T> void registerAll(final TypeConverter<T> converter, final Class<?>... types) {
        final Collection<Class<?>> all = (Collection<Class<?>>)Sets.newHashSet((Object[])types);
        all.add(converter.type());
        final com.matt.forgehax.util.typeconverter.TypeConverter<?> typeConverter;
        all.forEach(t -> typeConverter = TypeConverterRegistry.REGISTRY.put(t, converter));
    }
    
    public static <T> void register(final TypeConverter<T> converter) {
        registerAll(converter, converter.type());
    }
    
    public static <T> void unregister(final TypeConverter<T> converter) {
        TypeConverterRegistry.REGISTRY.forEach((k, v) -> {
            if (v.equals((Object)converter)) {
                TypeConverterRegistry.REGISTRY.remove(k);
            }
        });
    }
    
    @Nullable
    public static <T> TypeConverter<T> get(final Class<?> type) {
        try {
            for (final TypeConverter<?> converter : TypeConverterRegistry.REGISTRY.values()) {
                if (converter.isType((Class)type)) {
                    return (TypeConverter<T>)converter;
                }
            }
        }
        catch (Throwable t) {}
        return null;
    }
    
    @Nullable
    public static <T> TypeConverter<T> getByName(final String className) {
        return (TypeConverter<T>)TypeConverterRegistry.REGISTRY.entrySet().stream().filter(entry -> Objects.equals(className, entry.getKey().getName())).findFirst().map(entry -> {
            try {
                return (TypeConverter)entry.getValue();
            }
            catch (Throwable t) {
                return null;
            }
        }).orElse(null);
    }
    
    static {
        REGISTRY = Maps.newHashMap();
        registerAll(TypeConverters.BOOLEAN, Boolean.TYPE);
        registerAll(TypeConverters.BYTE, Byte.TYPE);
        registerAll(TypeConverters.CHARACTER, Character.TYPE);
        registerAll(TypeConverters.DOUBLE, Double.TYPE);
        registerAll(TypeConverters.FLOAT, Float.TYPE);
        registerAll(TypeConverters.INTEGER, Integer.TYPE);
        registerAll(TypeConverters.LONG, Long.TYPE);
        registerAll(TypeConverters.SHORT, Short.TYPE);
        register(TypeConverters.STRING);
    }
}
