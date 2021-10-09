//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter.types;

import com.matt.forgehax.util.typeconverter.*;
import java.util.*;
import javax.annotation.*;

public class StringType extends TypeConverter<String>
{
    public String label() {
        return "string";
    }
    
    public Class<String> type() {
        return String.class;
    }
    
    public String parse(final String value) {
        return value;
    }
    
    public String toString(final String value) {
        return (value != null) ? value : "null";
    }
    
    @Nullable
    public Comparator<String> comparator() {
        return new Comparator<String>() {
            @Override
            public int compare(final String o1, final String o2) {
                return o1.compareTo(o2);
            }
        };
    }
}
