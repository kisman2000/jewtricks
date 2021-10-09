//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.gui.elements;

import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.color.*;

public class GuiButton
{
    private final BaseMod mod;
    private static final int COLOR_ENABLED;
    private static final int COLOR_DISABLED;
    public int width;
    public static final int height = 15;
    public int x;
    public int y;
    
    public GuiButton(final BaseMod modIn) {
        this.mod = modIn;
    }
    
    public void setCoords(final int xIn, final int yIn) {
        this.x = xIn;
        this.y = yIn;
    }
    
    public boolean isModEnabled() {
        return this.mod.isEnabled();
    }
    
    public void toggleMod() {
        if (!this.mod.isEnabled()) {
            this.mod.enable();
        }
        else {
            this.mod.disable();
        }
    }
    
    public String getName() {
        return this.mod.getModName();
    }
    
    public BaseMod getMod() {
        return this.mod;
    }
    
    public int getColor() {
        return this.isModEnabled() ? GuiButton.COLOR_ENABLED : GuiButton.COLOR_DISABLED;
    }
    
    static {
        COLOR_ENABLED = Color.of(65, 65, 65, 200).toBuffer();
        COLOR_DISABLED = Color.of(100, 100, 100, 150).toBuffer();
    }
}
