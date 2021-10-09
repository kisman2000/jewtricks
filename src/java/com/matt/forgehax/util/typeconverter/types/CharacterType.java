//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter.types;

import com.matt.forgehax.util.typeconverter.*;
import com.google.common.base.*;
import java.util.*;
import javax.annotation.*;

public class CharacterType extends TypeConverter<Character>
{
    public String label() {
        return "char";
    }
    
    public Class<Character> type() {
        return Character.class;
    }
    
    public Character parse(final String value) {
        return Strings.isNullOrEmpty(value) ? '\0' : value.charAt(0);
    }
    
    public String toString(final Character value) {
        return Character.toString(value);
    }
    
    @Nullable
    public Comparator<Character> comparator() {
        return Character::compare;
    }
}
