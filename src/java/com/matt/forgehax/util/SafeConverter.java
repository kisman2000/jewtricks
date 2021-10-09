//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import java.util.*;
import com.matt.forgehax.util.command.*;

public class SafeConverter
{
    private static final String ACCEPTABLE_TRUE_BOOLEAN_STRINGS;
    
    public static boolean toBoolean(final Object o, final boolean defaultValue) {
        try {
            Objects.requireNonNull(o);
            if (o instanceof Boolean) {
                return (boolean)o;
            }
            final String str = String.valueOf(o);
            try {
                return Integer.valueOf(str) != 0;
            }
            catch (Exception e) {
                return str.toLowerCase().matches(SafeConverter.ACCEPTABLE_TRUE_BOOLEAN_STRINGS);
            }
        }
        catch (Throwable t) {
            return defaultValue;
        }
    }
    
    public static boolean toBoolean(final Object o) {
        return toBoolean(o, Boolean.FALSE);
    }
    
    public static byte toByte(final Object o, final byte defaultValue) {
        try {
            Objects.requireNonNull(o);
            if (o instanceof Number) {
                return ((Number)o).byteValue();
            }
            final String str = String.valueOf(o);
            return Byte.parseByte(str);
        }
        catch (Throwable t) {
            return defaultValue;
        }
    }
    
    public static byte toByte(final Object o) {
        return toByte(o, (byte)0);
    }
    
    public static char toCharacter(final Object o, final char defaultValue) {
        try {
            Objects.requireNonNull(o);
            if (o instanceof Character) {
                return (char)o;
            }
            final String str = String.valueOf(o);
            return str.charAt(0);
        }
        catch (Throwable t) {
            return defaultValue;
        }
    }
    
    public static char toCharacter(final Object o) {
        return toCharacter(o, '\0');
    }
    
    public static double toDouble(final Object o, final double defaultValue) {
        try {
            Objects.requireNonNull(o);
            if (o instanceof Number) {
                return ((Number)o).doubleValue();
            }
            final String str = String.valueOf(o);
            return Double.parseDouble(str);
        }
        catch (Throwable t) {
            return defaultValue;
        }
    }
    
    public static double toDouble(final Object o) {
        return toDouble(o, 0.0);
    }
    
    public static float toFloat(final Object o, final float defaultValue) {
        try {
            Objects.requireNonNull(o);
            if (o instanceof Number) {
                return ((Number)o).floatValue();
            }
            final String str = String.valueOf(o);
            return Float.parseFloat(str);
        }
        catch (Throwable t) {
            return defaultValue;
        }
    }
    
    public static float toFloat(final Object o) {
        return toFloat(o, 0.0f);
    }
    
    public static int toInteger(final Object o, final int defaultValue) {
        try {
            Objects.requireNonNull(o);
            if (o instanceof Number) {
                return ((Number)o).intValue();
            }
            return Integer.valueOf(String.valueOf(o));
        }
        catch (Throwable t) {
            return defaultValue;
        }
    }
    
    public static int toInteger(final Object o) {
        return toInteger(o, 0);
    }
    
    public static long toLong(final Object o, final long defaultValue) {
        try {
            Objects.requireNonNull(o);
            if (o instanceof Number) {
                return ((Number)o).longValue();
            }
            final String str = String.valueOf(o);
            return Long.parseLong(str);
        }
        catch (Throwable t) {
            return defaultValue;
        }
    }
    
    public static long toLong(final Object o) {
        return toLong(o, 0L);
    }
    
    public static short toShort(final Object o, final short defaultValue) {
        try {
            Objects.requireNonNull(o);
            if (o instanceof Number) {
                return ((Number)o).shortValue();
            }
            final String str = String.valueOf(o);
            return Short.parseShort(str);
        }
        catch (Throwable t) {
            return defaultValue;
        }
    }
    
    public static short toShort(final Object o) {
        return toShort(o, (short)0);
    }
    
    public static String toString(final Object o) {
        return String.valueOf(o);
    }
    
    static {
        ACCEPTABLE_TRUE_BOOLEAN_STRINGS = CommandHelper.join(new String[] { Boolean.TRUE.toString(), "t", "on", "enable", "enabled" }, "|");
    }
}
