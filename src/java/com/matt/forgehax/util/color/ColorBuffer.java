//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.color;

public class ColorBuffer extends Color
{
    private static final Color FACTORY;
    private final int buffer;
    
    public static Color getFactory() {
        return ColorBuffer.FACTORY;
    }
    
    private ColorBuffer() {
        this(0);
    }
    
    private ColorBuffer(final int buffer) {
        this.buffer = buffer;
    }
    
    public Color set(final int buffer) {
        return new ColorBuffer(buffer);
    }
    
    public Color set(final float red, final float green, final float blue, final float alpha) {
        return this.set((int)(red * 255.0f), (int)(green * 255.0f), (int)(blue * 255.0f), (int)(alpha * 255.0f));
    }
    
    public int getRed() {
        return this.toBuffer() >> 16 & 0xFF;
    }
    
    public int getGreen() {
        return this.toBuffer() >> 8 & 0xFF;
    }
    
    public int getBlue() {
        return this.toBuffer() & 0xFF;
    }
    
    public int getAlpha() {
        return this.toBuffer() >> 24 & 0xFF;
    }
    
    public float getRedAsFloat() {
        return this.getRed() / 255.0f;
    }
    
    public float getGreenAsFloat() {
        return this.getGreen() / 255.0f;
    }
    
    public float getBlueAsFloat() {
        return this.getBlue() / 255.0f;
    }
    
    public float getAlphaAsFloat() {
        return this.getAlpha() / 255.0f;
    }
    
    public int toBuffer() {
        return this.buffer;
    }
    
    public float[] toFloatArray() {
        return new float[] { this.getRedAsFloat(), this.getGreenAsFloat(), this.getBlueAsFloat(), this.getAlphaAsFloat() };
    }
    
    public int hashCode() {
        return Integer.hashCode(this.buffer);
    }
    
    public String toString() {
        return String.format("r=%d,g=%d,b=%d,a=%d", this.getRed(), this.getGreen(), this.getBlue(), this.getAlpha());
    }
    
    static {
        FACTORY = new ColorBuffer();
    }
}
