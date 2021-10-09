//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entry;

import com.matt.forgehax.util.serialization.*;
import java.util.*;
import net.minecraft.launchwrapper.*;
import javax.annotation.*;
import java.io.*;
import com.google.gson.stream.*;

public class ClassEntry implements ISerializableJson
{
    private final String clazzName;
    private Class<?> clazz;
    
    public ClassEntry(final String clazzName) {
        this.clazzName = clazzName;
        this.getClassInstance();
    }
    
    public ClassEntry(final Class<?> clazz) {
        Objects.requireNonNull(clazz);
        this.clazzName = clazz.getCanonicalName();
        this.clazz = clazz;
    }
    
    public String getClassName() {
        return this.clazzName;
    }
    
    @Nullable
    public Class<?> getClassInstance() {
        if (this.clazz == null) {
            try {
                this.clazz = Class.forName(this.clazzName, true, (ClassLoader)Launch.classLoader);
            }
            catch (Throwable t) {}
        }
        return this.clazz;
    }
    
    @Override
    public void serialize(final JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.endObject();
    }
    
    @Override
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginObject();
        reader.endObject();
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj == this || (obj instanceof ClassEntry && this.clazzName.equals(((ClassEntry)obj).clazzName)) || (obj instanceof String && this.clazzName.equals(obj)) || (this.getClassInstance() != null && obj != null && obj instanceof Class && this.getClassInstance().equals(obj));
    }
    
    @Override
    public int hashCode() {
        return this.clazzName.toLowerCase().hashCode();
    }
    
    @Override
    public String toString() {
        return this.clazzName;
    }
}
