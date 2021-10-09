//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter.types;

import com.matt.forgehax.util.typeconverter.*;
import com.matt.forgehax.util.*;
import java.util.*;
import javax.annotation.*;

public class ShortType extends TypeConverter<Short>
{
    public String label() {
        return "short";
    }
    
    public Class<Short> type() {
        return Short.class;
    }
    
    public Short parse(final String value) {
        return SafeConverter.toShort((Object)value);
    }
    
    public String toString(final Short value) {
        return Short.toString(value);
    }
    
    @Nullable
    public Comparator<Short> comparator() {
        return Short::compare;
    }
}
