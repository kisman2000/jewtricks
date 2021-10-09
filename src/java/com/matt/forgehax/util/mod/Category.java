//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.mod;

public enum Category
{
    NONE("", ""), 
    COMBAT("Combat", "Combat related mods"), 
    PLAYER("Player", "Mods that interact with the local player"), 
    RENDER("Render", "2D/3D rendering mods"), 
    WORLD("World", "Any mod that has to do with the world"), 
    MISC("Misc", "Miscellaneous"), 
    SERVICE("Service", "Background mods"), 
    MOVEMENT("Movement", "Movement mods");
    
    private String prettyName;
    private String description;
    
    private Category(final String prettyName, final String description) {
        this.prettyName = prettyName;
        this.description = description;
    }
    
    public String getPrettyName() {
        return this.prettyName;
    }
    
    public String getDescription() {
        return this.description;
    }
}
