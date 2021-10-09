//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter.types;

import com.matt.forgehax.util.typeconverter.*;
import com.matt.forgehax.util.*;
import java.util.*;
import javax.annotation.*;

public class DoubleType extends TypeConverter<Double>
{
    public String label() {
        return "double";
    }
    
    public Class<Double> type() {
        return Double.class;
    }
    
    public Double parse(final String value) {
        return SafeConverter.toDouble((Object)value);
    }
    
    public String toString(final Double value) {
        return Double.toString(value);
    }
    
    @Nullable
    public Comparator<Double> comparator() {
        return Double::compare;
    }
}
