//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.math;

public class AlignHelper
{
    public static int getFlowDirX2(final Align mask) {
        return getFlowDirX2(mask.ordinal());
    }
    
    public static int getFlowDirX2(final int mask) {
        return (mask % 3 < 2) ? 1 : -1;
    }
    
    public static int getFlowDirY2(final Align mask) {
        return getFlowDirY2(mask.ordinal());
    }
    
    public static int getFlowDirY2(final int mask) {
        return (mask / 3 < 2) ? 1 : -1;
    }
    
    public static int getPosX(final int mask) {
        return mask % 3;
    }
    
    public static int getPosY(final int mask) {
        return mask / 3;
    }
    
    public static int getSignumX(final int mask) {
        return mask % 3 - 1;
    }
    
    public static int getSignumY(final int mask) {
        return mask / 3 - 1;
    }
    
    public static int alignH(final int width, final int mask) {
        return width * getPosX(mask) / 2;
    }
    
    public static int alignV(final int height, final int mask) {
        return height * getPosY(mask) / 2;
    }
    
    public static boolean isLeft(final Align mask) {
        return isLeft(mask.ordinal());
    }
    
    public static boolean isLeft(final int mask) {
        return mask % 3 == 0;
    }
    
    public static boolean isMiddleH(final Align mask) {
        return isMiddleH(mask.ordinal());
    }
    
    public static boolean isMiddleH(final int mask) {
        return mask % 3 == 1;
    }
    
    public static boolean isRight(final Align mask) {
        return isRight(mask.ordinal());
    }
    
    public static boolean isRight(final int mask) {
        return mask % 3 == 2;
    }
    
    public static boolean isTop(final Align mask) {
        return isTop(mask.ordinal());
    }
    
    public static boolean isTop(final int mask) {
        return mask / 3 == 0;
    }
    
    public static boolean isMiddleV(final Align mask) {
        return isMiddleV(mask.ordinal());
    }
    
    public static boolean isMiddleV(final int mask) {
        return mask / 3 == 1;
    }
    
    public static boolean isBottom(final Align mask) {
        return mask.ordinal() / 3 == 2;
    }
    
    public static boolean isBottom(final int mask) {
        return mask / 3 == 2;
    }
    
    public static boolean isCenter(final Align mask) {
        return isMiddleH(mask.ordinal()) && isMiddleV(mask.ordinal());
    }
    
    public static boolean isTopLeft(final Align mask) {
        return isTop(mask.ordinal()) && isLeft(mask.ordinal());
    }
    
    public static boolean isTopRight(final Align mask) {
        return isTop(mask.ordinal()) && isRight(mask.ordinal());
    }
    
    public static boolean isBottomLeft(final Align mask) {
        return isBottom(mask.ordinal()) && isLeft(mask.ordinal());
    }
    
    public static boolean isBottomRight(final Align mask) {
        return isBottom(mask.ordinal()) && isRight(mask.ordinal());
    }
    
    public enum Align
    {
        TOPLEFT, 
        TOP, 
        TOPRIGHT, 
        CENTERLEFT, 
        CENTER, 
        CENTERRIGHT, 
        BOTTOMLEFT, 
        BOTTOM, 
        BOTTOMRIGHT;
    }
}
