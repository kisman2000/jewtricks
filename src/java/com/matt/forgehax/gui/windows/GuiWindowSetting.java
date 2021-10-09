//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.gui.windows;

import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.gui.elements.*;
import com.matt.forgehax.util.command.*;
import java.util.*;
import java.io.*;
import com.matt.forgehax.gui.*;

public class GuiWindowSetting extends GuiWindow
{
    public List<GuiElement> inputList;
    private BaseMod mod;
    
    public GuiWindowSetting(final BaseMod modIn, final int x, final int y) {
        super(modIn.getModName() + " Settings");
        this.inputList = new ArrayList<GuiElement>();
        this.mod = modIn;
        this.initializeInputs();
    }
    
    private void initializeInputs() {
        this.inputList.add((GuiElement)new GuiTextInput((Setting)null, this));
        this.height += 13;
    }
    
    public String getModName() {
        return this.mod.getModName();
    }
    
    public BaseMod getMod() {
        return this.mod;
    }
    
    public void drawWindow(final int mouseX, final int mouseY) {
        super.drawWindow(mouseX, mouseY);
        for (final GuiElement input : this.inputList) {
            input.x = 2;
            input.y = this.height + 2;
            input.width = this.width;
            input.draw(mouseX, mouseY);
        }
        this.bottomX = this.posX + this.width;
        this.bottomY = this.windowY + this.height;
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
        for (final GuiElement element : this.inputList) {
            element.keyTyped(typedChar, keyCode);
        }
    }
    
    public void mouseClicked(final int x, final int y, final int state) {
        super.mouseClicked(x, y, state);
        if (state == 2 && this.isMouseInHeader(x, y)) {
            ClickGui.getInstance().windowList.remove(this);
        }
        else {
            for (final GuiElement input : this.inputList) {
                input.mouseClicked(x, y, state);
            }
        }
    }
    
    public void mouseReleased(final int x, final int y, final int state) {
        super.mouseReleased(x, y, state);
        for (final GuiElement input : this.inputList) {
            input.mouseReleased(x, y, state);
        }
    }
}
