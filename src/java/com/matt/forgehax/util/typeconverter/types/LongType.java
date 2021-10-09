//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter.types;

import com.matt.forgehax.util.typeconverter.*;
import com.matt.forgehax.util.*;
import java.util.*;
import javax.annotation.*;

public class LongType extends TypeConverter<Long>
{
    public String label() {
        return "long";
    }
    
    public Class<Long> type() {
        return Long.class;
    }
    
    public Long parse(final String value) {
        return SafeConverter.toLong((Object)value);
    }
    
    public String toString(final Long value) {
        return Long.toString(value);
    }
    
    @Nullable
    public Comparator<Long> comparator() {
        return Long::compare;
    }
}
