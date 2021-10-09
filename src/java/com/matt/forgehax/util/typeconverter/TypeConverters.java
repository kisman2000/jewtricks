//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter;

import com.matt.forgehax.util.typeconverter.types.*;

public interface TypeConverters
{
    public static final TypeConverter<Boolean> BOOLEAN = new BooleanType();
    public static final TypeConverter<Byte> BYTE = new ByteType();
    public static final TypeConverter<Character> CHARACTER = new CharacterType();
    public static final TypeConverter<Double> DOUBLE = new DoubleType();
    public static final TypeConverter<Float> FLOAT = new FloatType();
    public static final TypeConverter<Integer> INTEGER = new IntegerType();
    public static final TypeConverter<Long> LONG = new LongType();
    public static final TypeConverter<Short> SHORT = new ShortType();
    public static final TypeConverter<String> STRING = new StringType();
}
