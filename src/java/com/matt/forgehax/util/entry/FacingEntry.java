//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entry;

import com.matt.forgehax.util.serialization.*;
import net.minecraft.util.*;
import java.util.*;
import java.io.*;
import com.google.gson.stream.*;

public class FacingEntry implements ISerializableJson
{
    private final EnumFacing facing;
    
    public FacingEntry(final EnumFacing facing) {
        Objects.requireNonNull(facing);
        this.facing = facing;
    }
    
    public FacingEntry(final String str) {
        this(EnumFacing.byName(str));
    }
    
    public EnumFacing getFacing() {
        return this.facing;
    }
    
    @Override
    public void serialize(final JsonWriter writer) throws IOException {
        writer.beginArray();
        writer.endArray();
    }
    
    @Override
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginArray();
        reader.endArray();
    }
    
    @Override
    public boolean equals(final Object obj) {
        return this == obj || (obj instanceof FacingEntry && this.facing.equals((Object)((FacingEntry)obj).getFacing())) || (obj instanceof EnumFacing && this.facing.equals(obj));
    }
    
    @Override
    public String toString() {
        return this.getFacing().getName2();
    }
}
