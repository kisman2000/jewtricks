//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.blocks.properties;

import net.minecraft.world.*;
import com.google.common.collect.*;
import net.minecraftforge.common.*;
import java.io.*;
import com.google.gson.stream.*;
import java.util.*;

public class DimensionProperty implements IBlockProperty
{
    private static final String HEADING = "dimensions";
    private Collection<DimensionType> dimensions;
    
    public DimensionProperty() {
        this.dimensions = (Collection<DimensionType>)Sets.newHashSet();
    }
    
    private boolean add(final DimensionType type) {
        return type != null && this.dimensions.add(type);
    }
    
    public boolean add(final int id) {
        try {
            return this.add(DimensionManager.getProviderType(id));
        }
        catch (Exception e) {
            return false;
        }
    }
    
    private boolean remove(final DimensionType type) {
        return type != null && this.dimensions.remove(type);
    }
    
    public boolean remove(final int id) {
        try {
            return this.remove(DimensionManager.getProviderType(id));
        }
        catch (Exception e) {
            return false;
        }
    }
    
    public boolean contains(final int id) {
        if (this.dimensions.isEmpty()) {
            return true;
        }
        try {
            return this.dimensions.contains(DimensionManager.getProviderType(id));
        }
        catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public void serialize(final JsonWriter writer) throws IOException {
        writer.beginArray();
        for (final DimensionType dimension : this.dimensions) {
            writer.value(dimension.getName());
        }
        writer.endArray();
    }
    
    @Override
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginArray();
        while (reader.hasNext() && reader.peek().equals((Object)JsonToken.STRING)) {
            final String dim = reader.nextString();
            for (final DimensionType type : DimensionType.values()) {
                if (Objects.equals(type.getName(), dim)) {
                    this.add(type);
                    break;
                }
            }
        }
    }
    
    @Override
    public boolean isNecessary() {
        return !this.dimensions.isEmpty();
    }
    
    @Override
    public String helpText() {
        final StringBuilder builder = new StringBuilder("{");
        final Iterator<DimensionType> it = this.dimensions.iterator();
        while (it.hasNext()) {
            final String name = it.next().getName();
            builder.append(name);
            if (it.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }
    
    @Override
    public IBlockProperty newImmutableInstance() {
        return new ImmutableDimension();
    }
    
    @Override
    public String toString() {
        return "dimensions";
    }
    
    private static class ImmutableDimension extends DimensionProperty
    {
        @Override
        public boolean add(final int id) {
            return false;
        }
        
        @Override
        public boolean remove(final int id) {
            return false;
        }
        
        @Override
        public boolean contains(final int id) {
            return true;
        }
    }
}
