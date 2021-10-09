//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.blocks.properties;

import com.google.common.collect.*;
import javax.annotation.*;
import java.util.*;
import java.io.*;
import com.google.gson.stream.*;

public class BoundProperty implements IBlockProperty
{
    private static final String HEADING = "bounds";
    private final Collection<Bound> bounds;
    
    public BoundProperty() {
        this.bounds = (Collection<Bound>)Sets.newHashSet();
    }
    
    public boolean add(final int minY, final int maxY) {
        return this.bounds.add(new Bound(minY, maxY));
    }
    
    public boolean remove(final int minY, final int maxY) {
        final Bound bound = this.get(minY, maxY);
        return bound != null && this.bounds.remove(bound);
    }
    
    @Nullable
    public Bound get(final int minY, final int maxY) {
        for (final Bound bound : this.bounds) {
            if (bound.getMin() == minY && bound.getMax() == maxY) {
                return bound;
            }
        }
        return null;
    }
    
    public Collection<Bound> getAll() {
        return Collections.unmodifiableCollection((Collection<? extends Bound>)this.bounds);
    }
    
    public boolean isWithinBoundaries(final int posY) {
        if (this.bounds.isEmpty()) {
            return true;
        }
        for (final Bound bound : this.bounds) {
            if (bound.isWithinBound(posY)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void serialize(final JsonWriter writer) throws IOException {
        writer.beginArray();
        for (final Bound bound : this.bounds) {
            writer.beginArray();
            writer.value((long)bound.getMin());
            writer.value((long)bound.getMax());
            writer.endArray();
        }
        writer.endArray();
    }
    
    @Override
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginArray();
            this.add(reader.nextInt(), reader.nextInt());
            reader.endArray();
        }
        reader.endArray();
    }
    
    @Override
    public boolean isNecessary() {
        return !this.bounds.isEmpty();
    }
    
    @Override
    public String helpText() {
        final StringBuilder builder = new StringBuilder("{");
        final Iterator<Bound> it = this.bounds.iterator();
        while (it.hasNext()) {
            final Bound bound = it.next();
            builder.append('[');
            builder.append(bound.getMin());
            builder.append(',');
            builder.append(bound.getMax());
            builder.append(']');
            if (it.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append('}');
        return builder.toString();
    }
    
    @Override
    public IBlockProperty newImmutableInstance() {
        return new ImmutableBoundProperty();
    }
    
    @Override
    public String toString() {
        return "bounds";
    }
    
    public static class Bound
    {
        private final int min;
        private final int max;
        
        public Bound(final int min, final int max) throws IllegalArgumentException {
            if (min > max) {
                throw new IllegalArgumentException("min cannot be greater than max");
            }
            this.min = min;
            this.max = max;
        }
        
        public int getMin() {
            return this.min;
        }
        
        public int getMax() {
            return this.max;
        }
        
        public boolean isWithinBound(final int y) {
            return y >= this.min && y <= this.max;
        }
        
        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Bound && this.max == ((Bound)obj).max && this.min == ((Bound)obj).min;
        }
    }
    
    private static class ImmutableBoundProperty extends BoundProperty
    {
        @Override
        public boolean add(final int minY, final int maxY) {
            return false;
        }
        
        @Override
        public boolean remove(final int minY, final int maxY) {
            return false;
        }
        
        @Override
        public Bound get(final int minY, final int maxY) {
            return null;
        }
        
        @Override
        public Collection<Bound> getAll() {
            return (Collection<Bound>)Collections.emptySet();
        }
        
        @Override
        public boolean isWithinBoundaries(final int posY) {
            return true;
        }
    }
}
