//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter.types;

import com.matt.forgehax.util.typeconverter.*;
import com.matt.forgehax.util.*;
import java.util.*;
import javax.annotation.*;

public class BooleanType extends TypeConverter<Boolean>
{
    public String label() {
        return "bool";
    }
    
    public Class<Boolean> type() {
        return Boolean.class;
    }
    
    public Boolean parse(final String value) {
        return SafeConverter.toBoolean((Object)value);
    }
    
    public String toString(final Boolean value) {
        return Boolean.toString(value);
    }
    
    @Nullable
    public Comparator<Boolean> comparator() {
        return Boolean::compare;
    }
}
