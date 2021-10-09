//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.color;

import java.util.*;

public abstract class Color
{
    public static int clamp(final int c) {
        return Math.min(255, Math.max(0, c));
    }
    
    public static float clamp(final float c) {
        return Math.min(1.0f, Math.max(0.0f, c));
    }
    
    public static Color ofInteger() {
        return ColorBuffer.getFactory();
    }
    
    public static Color ofFloat() {
        return Color4F.getFactory();
    }
    
    public static Color of(final int buffer) {
        return ofInteger().set(buffer);
    }
    
    public static Color of(final int red, final int green, final int blue, final int alpha) {
        return ofInteger().set(red, green, blue, alpha);
    }
    
    public static Color of(final int red, final int green, final int blue) {
        return ofInteger().set(red, green, blue);
    }
    
    public static Color of(final int[] color) {
        return ofInteger().set(color);
    }
    
    public static Color of(final float red, final float green, final float blue, final float alpha) {
        return ofFloat().set(red, green, blue, alpha);
    }
    
    public static Color of(final float red, final float green, final float blue) {
        return ofFloat().set(red, green, blue);
    }
    
    public static Color of(final float[] color) {
        return ofFloat().set(color);
    }
    
    public static Color of(final double red, final double green, final double blue, final double alpha) {
        return ofFloat().set(red, green, blue, alpha);
    }
    
    public static Color of(final double red, final double green, final double blue) {
        return ofFloat().set(red, green, blue);
    }
    
    public static Color of(final double[] color) {
        return ofFloat().set(color);
    }
    
    public abstract Color set(final int p0);
    
    public Color set(final int red, final int green, final int blue, final int alpha) {
        return this.set((red << 16) + (green << 8) + blue + (alpha << 24));
    }
    
    public Color set(final int red, final int green, final int blue) {
        return this.set(red, green, blue, 255);
    }
    
    public Color set(final int[] color) {
        Objects.requireNonNull(color);
        switch (color.length) {
            case 3: {
                return this.set(color[0], color[1], color[2]);
            }
            case 4: {
                return this.set(color[0], color[1], color[2], color[3]);
            }
            default: {
                throw new IllegalArgumentException("color[] must be of length 3 or 4");
            }
        }
    }
    
    public abstract Color set(final float p0, final float p1, final float p2, final float p3);
    
    public Color set(final float red, final float green, final float blue) {
        return this.set(red, green, blue, 1.0f);
    }
    
    public Color set(final float[] color) {
        Objects.requireNonNull(color);
        switch (color.length) {
            case 3: {
                return this.set(color[0], color[1], color[2]);
            }
            case 4: {
                return this.set(color[0], color[1], color[2], color[3]);
            }
            default: {
                throw new IllegalArgumentException("color[] must be of length 3 or 4");
            }
        }
    }
    
    public Color set(final double red, final double green, final double blue, final double alpha) {
        return this.set((float)red, (float)green, (float)blue, (float)alpha);
    }
    
    public Color set(final double red, final double green, final double blue) {
        return this.set(red, green, blue, 1.0);
    }
    
    public Color set(final double[] color) {
        Objects.requireNonNull(color);
        switch (color.length) {
            case 3: {
                return this.set(color[0], color[1], color[2]);
            }
            case 4: {
                return this.set(color[0], color[1], color[2], color[3]);
            }
            default: {
                throw new IllegalArgumentException("color[] must be of length 3 or 4");
            }
        }
    }
    
    public abstract int getRed();
    
    public abstract int getGreen();
    
    public abstract int getBlue();
    
    public abstract int getAlpha();
    
    public Color setRed(final int red) {
        return this.set(red, this.getGreen(), this.getBlue(), this.getAlpha());
    }
    
    public Color setGreen(final int green) {
        return this.set(this.getRed(), green, this.getBlue(), this.getAlpha());
    }
    
    public Color setBlue(final int blue) {
        return this.set(this.getRed(), this.getGreen(), blue, this.getAlpha());
    }
    
    public Color setAlpha(final int alpha) {
        return this.set(this.getRed(), this.getGreen(), this.getBlue(), alpha);
    }
    
    public abstract float getRedAsFloat();
    
    public abstract float getGreenAsFloat();
    
    public abstract float getBlueAsFloat();
    
    public abstract float getAlphaAsFloat();
    
    public Color setRed(final float red) {
        return this.set(red, this.getGreenAsFloat(), this.getBlueAsFloat(), this.getAlphaAsFloat());
    }
    
    public Color setGreen(final float green) {
        return this.set(this.getRedAsFloat(), green, this.getBlueAsFloat(), this.getAlphaAsFloat());
    }
    
    public Color setBlue(final float blue) {
        return this.set(this.getRedAsFloat(), this.getGreenAsFloat(), blue, this.getAlphaAsFloat());
    }
    
    public Color setAlpha(final float alpha) {
        return this.set(this.getRedAsFloat(), this.getGreenAsFloat(), this.getBlueAsFloat(), alpha);
    }
    
    public double getRedAsDouble() {
        return this.getRedAsFloat();
    }
    
    public double getGreenAsDouble() {
        return this.getGreenAsFloat();
    }
    
    public double getBlueAsDouble() {
        return this.getBlueAsFloat();
    }
    
    public double getAlphaAsDouble() {
        return this.getAlphaAsFloat();
    }
    
    public Color setRed(final double red) {
        return this.setRed((float)red);
    }
    
    public Color setGreen(final double green) {
        return this.setGreen((float)green);
    }
    
    public Color setBlue(final double blue) {
        return this.setBlue((float)blue);
    }
    
    public Color setAlpha(final double alpha) {
        return this.setAlpha((float)alpha);
    }
    
    public int toBuffer() {
        return (this.getRed() << 16) + (this.getGreen() << 8) + this.getBlue() + (this.getAlpha() << 24);
    }
    
    public int[] toIntegerArray() {
        return new int[] { this.getRed(), this.getGreen(), this.getBlue(), this.getAlpha() };
    }
    
    public float[] toFloatArray() {
        return new float[] { this.getRedAsFloat(), this.getGreenAsFloat(), this.getBlueAsFloat(), this.getAlphaAsFloat() };
    }
    
    public double[] toDoubleArray() {
        final float[] array = this.toFloatArray();
        return new double[] { array[0], array[1], array[2], array[3] };
    }
    
    @Override
    public abstract String toString();
    
    @Override
    public abstract int hashCode();
    
    @Override
    public boolean equals(final Object obj) {
        return this == obj || (obj instanceof Color && this.hashCode() == obj.hashCode());
    }
}
