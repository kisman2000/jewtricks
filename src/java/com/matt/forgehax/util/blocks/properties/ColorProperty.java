//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.blocks.properties;

import net.minecraft.util.math.*;
import java.io.*;
import com.google.gson.stream.*;
import com.matt.forgehax.util.color.*;

public class ColorProperty implements IBlockProperty
{
    private static final String HEADING = "color";
    private static final int DEFAULT_COLOR_BUFFER;
    private static final int[] DEFAULT_COLOR_ARRAY;
    private int r;
    private int g;
    private int b;
    private int a;
    private int buffer;
    
    public ColorProperty() {
        this.set(ColorProperty.DEFAULT_COLOR_BUFFER);
    }
    
    public int getRed() {
        return this.r;
    }
    
    public int getGreen() {
        return this.g;
    }
    
    public int getBlue() {
        return this.b;
    }
    
    public int getAlpha() {
        return this.a;
    }
    
    public int[] getAsArray() {
        return new int[] { this.r, this.g, this.b, this.a };
    }
    
    public int getAsBuffer() {
        return this.buffer;
    }
    
    public void set(final int r, final int g, final int b, final int a) {
        this.r = MathHelper.clamp(r, 0, 255);
        this.g = MathHelper.clamp(g, 0, 255);
        this.b = MathHelper.clamp(b, 0, 255);
        this.a = MathHelper.clamp(a, 0, 255);
        this.buffer = Color.of(r, g, b, a).toBuffer();
    }
    
    public void set(final int buffer) {
        final int[] rgba = Color.of(buffer).toIntegerArray();
        this.set(rgba[0], rgba[1], rgba[2], rgba[3]);
    }
    
    @Override
    public void serialize(final JsonWriter writer) throws IOException {
        writer.value((long)this.buffer);
    }
    
    @Override
    public void deserialize(final JsonReader reader) throws IOException {
        this.set(reader.nextInt());
    }
    
    @Override
    public boolean isNecessary() {
        return this.buffer != ColorProperty.DEFAULT_COLOR_BUFFER;
    }
    
    @Override
    public String helpText() {
        return String.format("(%d, %d, %d, %d)", this.r, this.g, this.b, this.a);
    }
    
    @Override
    public IBlockProperty newImmutableInstance() {
        return new ImmutableColor();
    }
    
    @Override
    public String toString() {
        return "color";
    }
    
    static {
        DEFAULT_COLOR_BUFFER = Colors.WHITE.toBuffer();
        DEFAULT_COLOR_ARRAY = Color.of(ColorProperty.DEFAULT_COLOR_BUFFER).toIntegerArray();
    }
    
    private static class ImmutableColor extends ColorProperty
    {
        @Override
        public int getRed() {
            return ColorProperty.DEFAULT_COLOR_ARRAY[0];
        }
        
        @Override
        public int getGreen() {
            return ColorProperty.DEFAULT_COLOR_ARRAY[1];
        }
        
        @Override
        public int getBlue() {
            return ColorProperty.DEFAULT_COLOR_ARRAY[2];
        }
        
        @Override
        public int getAlpha() {
            return ColorProperty.DEFAULT_COLOR_ARRAY[3];
        }
        
        @Override
        public int getAsBuffer() {
            return ColorProperty.DEFAULT_COLOR_BUFFER;
        }
        
        @Override
        public int[] getAsArray() {
            return ColorProperty.DEFAULT_COLOR_ARRAY;
        }
        
        @Override
        public void set(final int buffer) {
        }
        
        @Override
        public void set(final int r, final int g, final int b, final int a) {
        }
    }
}
