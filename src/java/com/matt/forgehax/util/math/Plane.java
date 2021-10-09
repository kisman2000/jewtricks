//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.math;

public class Plane
{
    private final double x;
    private final double y;
    private final boolean visible;
    
    public Plane(final double x, final double y, final boolean visible) {
        this.x = x;
        this.y = y;
        this.visible = visible;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
}
