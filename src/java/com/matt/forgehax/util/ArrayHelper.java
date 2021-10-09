//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

public class ArrayHelper
{
    public static <T> T getOrDefault(final T[] array, final int index, final T defaultValue) {
        return isInRange(array, index) ? array[index] : defaultValue;
    }
    
    public static <T> boolean isInRange(final T[] array, final int index) {
        return array != null && index >= 0 && index < array.length;
    }
}
