//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.gui.elements;

import com.matt.forgehax.util.command.*;
import com.matt.forgehax.gui.windows.*;
import com.matt.forgehax.*;
import net.minecraft.util.text.*;
import net.minecraft.util.math.*;
import java.io.*;
import com.matt.forgehax.util.color.*;
import com.matt.forgehax.util.draw.*;

public class GuiTextInput extends GuiElement
{
    private final int blinkSpeed = 30;
    private int ticks;
    private boolean isActive;
    private int selectedIndex;
    private StringBuilder input;
    
    public GuiTextInput(final Setting settingIn, final GuiWindowSetting parent) {
        super(settingIn, parent);
        this.selectedIndex = -1;
        this.input = new StringBuilder();
        this.height = 12;
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int state) {
        this.isActive = this.isMouseInElement(mouseX, mouseY);
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (this.isActive) {
            switch (keyCode) {
                case 1: {
                    this.isActive = false;
                    break;
                }
                case 28: {
                    this.isActive = false;
                    Globals.MC.player.sendMessage((ITextComponent)new TextComponentString(this.input.toString()));
                    break;
                }
                case 14: {
                    if (this.selectedIndex > -1) {
                        this.input.deleteCharAt(this.selectedIndex);
                        --this.selectedIndex;
                        break;
                    }
                    break;
                }
                case 203: {
                    --this.selectedIndex;
                    break;
                }
                case 205: {
                    ++this.selectedIndex;
                    break;
                }
                default: {
                    if (this.isValidChar(typedChar)) {
                        ++this.selectedIndex;
                        this.input.insert(this.selectedIndex, typedChar);
                        break;
                    }
                    break;
                }
            }
            this.selectedIndex = MathHelper.clamp(this.selectedIndex, -1, this.input.length() - 1);
        }
    }
    
    public void draw(final int mouseX, final int mouseY) {
        super.draw(this.x, this.y);
        SurfaceHelper.drawRect(this.x, this.y, this.width - 2, this.height, Colors.WHITE.toBuffer());
        SurfaceHelper.drawOutlinedRect(this.x, this.y, this.width - 2, this.height, Colors.BLACK.toBuffer());
        if (this.ticks % 30 * 2 > 30 && this.isActive) {
            this.getBlinkWidth();
        }
        SurfaceHelper.drawText(this.getInputString(), this.x + 1, this.y + 2, Colors.BLACK.toBuffer());
        ++this.ticks;
    }
    
    private int getBlinkWidth() {
        if (this.input.length() > 0) {
            return SurfaceHelper.getTextWidth(this.input.substring(0, this.selectedIndex + 1));
        }
        return 0;
    }
    
    private String getInputString() {
        return this.input.toString();
    }
    
    private boolean isValidChar(final char charIn) {
        return !Character.isISOControl(charIn);
    }
    
    private void setValue(final String in) {
        this.setting.set(in);
    }
}
