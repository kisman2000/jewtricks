//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.color;

import java.util.*;

public class Color4F extends Color
{
    private static final Color FACTORY;
    private final float[] color;
    
    public static Color getFactory() {
        return Color4F.FACTORY;
    }
    
    private Color4F() {
        this.color = new float[4];
    }
    
    private Color4F(final float red, final float green, final float blue, final float alpha) {
        (this.color = new float[4])[0] = red;
        this.color[1] = green;
        this.color[2] = blue;
        this.color[3] = alpha;
    }
    
    public Color set(final int buffer) {
        return this.set((buffer >> 16 & 0xFF) / 255.0f, (buffer >> 8 & 0xFF) / 255.0f, (buffer & 0xFF) / 255.0f, (buffer >> 24 & 0xFF) / 255.0f);
    }
    
    public Color set(final float red, final float green, final float blue, final float alpha) {
        return new Color4F(red, green, blue, alpha);
    }
    
    public int getRed() {
        return (int)(this.getRedAsFloat() * 255.0f);
    }
    
    public int getGreen() {
        return (int)(this.getRedAsFloat() * 255.0f);
    }
    
    public int getBlue() {
        return (int)(this.getRedAsFloat() * 255.0f);
    }
    
    public int getAlpha() {
        return (int)(this.getRedAsFloat() * 255.0f);
    }
    
    public float getRedAsFloat() {
        return this.color[0];
    }
    
    public float getGreenAsFloat() {
        return this.color[1];
    }
    
    public float getBlueAsFloat() {
        return this.color[2];
    }
    
    public float getAlphaAsFloat() {
        return this.color[3];
    }
    
    public float[] toFloatArray() {
        return Arrays.copyOf(this.color, this.color.length);
    }
    
    public String toString() {
        return String.format("r=%.2f,g=%.2f,b=%.2f,a=%.2f", this.getRedAsFloat(), this.getGreenAsFloat(), this.getBlueAsFloat(), this.getAlphaAsFloat());
    }
    
    public int hashCode() {
        return Arrays.hashCode(this.color);
    }
    
    static {
        FACTORY = new Color4F();
    }
}
