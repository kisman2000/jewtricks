//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils;

import com.matt.forgehax.asm.utils.fasttype.*;
import com.matt.forgehax.asm.utils.environment.*;
import java.util.*;
import java.util.function.*;
import java.lang.reflect.*;
import com.matt.forgehax.asm.utils.name.*;

public class ReflectionHelper
{
    public static <F, T extends F> void copyOf(final F from, final T to, final boolean ignoreFinal) throws NoSuchFieldException, IllegalAccessException {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        final Class<?> clazz = from.getClass();
        for (final Field field : clazz.getDeclaredFields()) {
            makePublic(field);
            if (!isStatic(field)) {
                if (!ignoreFinal || !isFinal(field)) {
                    makeMutable(field);
                    field.set(to, field.get(from));
                }
            }
        }
    }
    
    public static <F, T extends F> void copyOf(final F from, final T to) throws NoSuchFieldException, IllegalAccessException {
        copyOf(from, to, false);
    }
    
    public static boolean isStatic(final Member instance) {
        return (instance.getModifiers() & 0x8) != 0x0;
    }
    
    public static boolean isFinal(final Member instance) {
        return (instance.getModifiers() & 0x10) != 0x0;
    }
    
    public static void makeAccessible(final AccessibleObject instance, final boolean accessible) {
        Objects.requireNonNull(instance);
        instance.setAccessible(accessible);
    }
    
    public static void makePublic(final AccessibleObject instance) {
        makeAccessible(instance, true);
    }
    
    public static void makePrivate(final AccessibleObject instance) {
        makeAccessible(instance, false);
    }
    
    public static void makeMutable(final Member instance) throws NoSuchFieldException, IllegalAccessException {
        Objects.requireNonNull(instance);
        final Field modifiers = Field.class.getDeclaredField("modifiers");
        makePublic(modifiers);
        modifiers.setInt(instance, instance.getModifiers() & 0xFFFFFFEF);
    }
    
    public static void makeImmutable(final Member instance) throws NoSuchFieldException, IllegalAccessException {
        Objects.requireNonNull(instance);
        final Field modifiers = Field.class.getDeclaredField("modifiers");
        makePublic(modifiers);
        modifiers.setInt(instance, instance.getModifiers() & 0x10);
    }
    
    public static Class<?> getMethodDeclaringClass(final FastMethod<?> method, final Object instance) {
        Objects.requireNonNull(instance);
        final IName<String> names = (IName<String>)method.getName();
        return Arrays.stream(State.values()).sorted(Comparator.comparing(state -> RuntimeState.getState().equals((Object)state))).map((Function<? super State, ?>)names::getByState).filter(Objects::nonNull).map(name -> {
            try {
                return instance.getClass().getDeclaredMethod(name, (Class<?>[])method.getParameters());
            }
            catch (NoSuchMethodException e) {
                return null;
            }
        }).filter(Objects::nonNull).peek((Consumer<? super Object>)ReflectionHelper::makePublic).findAny().map((Function<? super Object, ? extends Class<?>>)Method::getDeclaringClass).orElse(null);
    }
}
