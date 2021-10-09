//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.blocks.properties;

import com.google.common.collect.*;
import joptsimple.internal.*;
import java.util.*;
import java.io.*;
import com.google.gson.stream.*;

public class TagProperty implements IBlockProperty
{
    private static final String HEADING = "tags";
    private final Collection<String> tags;
    
    public TagProperty() {
        this.tags = (Collection<String>)Sets.newTreeSet((Comparator)String.CASE_INSENSITIVE_ORDER);
    }
    
    public boolean add(final String tag) {
        return !Strings.isNullOrEmpty(tag) && this.tags.add(tag);
    }
    
    public boolean remove(final String tag) {
        return !Strings.isNullOrEmpty(tag) && this.tags.remove(tag);
    }
    
    public boolean contains(final String tag) {
        return !Strings.isNullOrEmpty(tag) && this.tags.contains(tag);
    }
    
    public void serialize(final JsonWriter writer) throws IOException {
        writer.beginArray();
        for (final String tag : this.tags) {
            writer.value(tag);
        }
        writer.endArray();
    }
    
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            this.add(reader.nextString());
        }
        reader.endArray();
    }
    
    public boolean isNecessary() {
        return !this.tags.isEmpty();
    }
    
    public String helpText() {
        final StringBuilder builder = new StringBuilder("{");
        final Iterator<String> it = this.tags.iterator();
        while (it.hasNext()) {
            final String tag = it.next();
            builder.append(tag);
            if (it.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }
    
    public IBlockProperty newImmutableInstance() {
        return (IBlockProperty)new ImmutableBlockTag();
    }
    
    @Override
    public String toString() {
        return "tags";
    }
    
    private static class ImmutableBlockTag extends TagProperty
    {
        @Override
        public boolean add(final String tag) {
            return false;
        }
        
        @Override
        public boolean remove(final String tag) {
            return false;
        }
        
        @Override
        public boolean contains(final String tag) {
            return false;
        }
    }
}
