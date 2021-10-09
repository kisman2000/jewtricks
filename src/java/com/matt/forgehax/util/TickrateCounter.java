//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

public class TickrateCounter
{
    private static double tr;
    
    public static double getTickrate() {
        return 0.0;
    }
    
    public static void setTickrate(final double tickrate) {
        TickrateCounter.tr = tickrate;
    }
    
    static {
        TickrateCounter.tr = 0.0;
    }
}
