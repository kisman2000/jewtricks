//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.serialization;

import javax.annotation.*;
import java.io.*;
import com.google.gson.stream.*;

public interface ISerializableImmutable<E>
{
    void serialize(final JsonWriter p0, @Nullable final E p1) throws IOException;
    
    @Nullable
    E deserialize(final JsonReader p0) throws IOException;
}
