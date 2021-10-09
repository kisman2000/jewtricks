//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter.types;

import com.matt.forgehax.util.typeconverter.*;
import com.matt.forgehax.util.*;
import java.util.*;
import javax.annotation.*;

public class FloatType extends TypeConverter<Float>
{
    public String label() {
        return "float";
    }
    
    public Class<Float> type() {
        return Float.class;
    }
    
    public Float parse(final String value) {
        return SafeConverter.toFloat((Object)value);
    }
    
    public String toString(final Float value) {
        return Float.toString(value);
    }
    
    @Nullable
    public Comparator<Float> comparator() {
        return Float::compare;
    }
}
