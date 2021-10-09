//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.serialization;

import java.io.*;
import com.google.gson.stream.*;

public interface ISerializableMutable<E>
{
    void serialize(final E p0, final JsonWriter p1) throws IOException;
    
    void deserialize(final E p0, final JsonReader p1) throws IOException;
    
    default String heading() {
        return this.toString();
    }
}
