//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.serialization;

import java.io.*;
import com.google.gson.stream.*;

public interface ISerializableJson
{
    void serialize(final JsonWriter p0) throws IOException;
    
    void deserialize(final JsonReader p0) throws IOException;
    
    default String getUniqueHeader() {
        return this.toString();
    }
}
