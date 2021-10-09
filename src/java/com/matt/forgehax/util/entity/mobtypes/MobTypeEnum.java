//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entity.mobtypes;

public enum MobTypeEnum
{
    PLAYER, 
    HOSTILE, 
    NEUTRAL, 
    FRIENDLY, 
    INVALID;
    
    public boolean isValid() {
        return this.ordinal() > 0;
    }
}
