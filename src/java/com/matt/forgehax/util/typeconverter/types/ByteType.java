//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter.types;

import com.matt.forgehax.util.typeconverter.*;
import com.matt.forgehax.util.*;
import java.util.*;
import javax.annotation.*;

public class ByteType extends TypeConverter<Byte>
{
    public String label() {
        return "byte";
    }
    
    public Class<Byte> type() {
        return Byte.class;
    }
    
    public Byte parse(final String value) {
        return SafeConverter.toByte((Object)value);
    }
    
    public String toString(final Byte value) {
        return Byte.toString(value);
    }
    
    @Nullable
    public Comparator<Byte> comparator() {
        return Byte::compare;
    }
}
