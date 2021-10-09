//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter.types;

import com.matt.forgehax.util.typeconverter.*;
import com.matt.forgehax.util.*;
import java.util.*;
import javax.annotation.*;

public class IntegerType extends TypeConverter<Integer>
{
    public String label() {
        return "int";
    }
    
    public Class<Integer> type() {
        return Integer.class;
    }
    
    public Integer parse(final String value) {
        return SafeConverter.toInteger((Object)value);
    }
    
    public String toString(final Integer value) {
        return Integer.toString(value);
    }
    
    @Nullable
    public Comparator<Integer> comparator() {
        return Integer::compare;
    }
}
