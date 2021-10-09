//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.gui.elements;

import com.matt.forgehax.gui.windows.*;
import com.matt.forgehax.util.command.*;
import java.io.*;

public class GuiElement
{
    public GuiWindowSetting parentWindow;
    public int width;
    public int height;
    public int subX;
    public int subY;
    public int x;
    public int y;
    public Setting setting;
    
    public GuiElement(final Setting settingIn, final GuiWindowSetting parent) {
        this.parentWindow = parent;
        this.setting = settingIn;
    }
    
    public void mouseClicked(final int x, final int y, final int state) {
    }
    
    public void mouseReleased(final int x, final int y, final int state) {
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
    }
    
    public void draw(final int mouseX, final int mouseY) {
        this.x = this.getPosX() + this.subX + 1;
        this.y = this.getPosY() + this.subY + 21;
    }
    
    public int getPosX() {
        return this.parentWindow.posX;
    }
    
    public int getPosY() {
        return this.parentWindow.headerY;
    }
    
    public boolean isMouseInElement(final int mouseX, final int mouseY) {
        return mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height;
    }
}
