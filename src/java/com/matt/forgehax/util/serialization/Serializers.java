//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.serialization;

import javax.annotation.*;
import java.io.*;
import com.google.gson.stream.*;

public class Serializers
{
    private static final ISerializableImmutable IMMUTABLE_NULL;
    
    public static <T> ISerializableImmutable<T> nullSerializer() {
        return (ISerializableImmutable<T>)Serializers.IMMUTABLE_NULL;
    }
    
    static {
        IMMUTABLE_NULL = (ISerializableImmutable)new ISerializableImmutable() {
            public void serialize(final JsonWriter writer, @Nullable final Object instance) throws IOException {
                writer.nullValue();
            }
            
            @Nullable
            public Object deserialize(final JsonReader reader) throws IOException {
                reader.nextNull();
                return null;
            }
        };
    }
}
