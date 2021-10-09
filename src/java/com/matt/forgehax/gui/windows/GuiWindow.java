//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.gui.windows;

import com.matt.forgehax.gui.*;
import com.matt.forgehax.util.draw.*;
import java.io.*;
import com.matt.forgehax.*;
import net.minecraft.client.gui.*;
import com.matt.forgehax.util.color.*;

public abstract class GuiWindow
{
    public boolean isHidden;
    private String title;
    public int posX;
    public int headerY;
    public int windowY;
    public int bottomX;
    public int bottomY;
    private int dragX;
    private int dragY;
    private boolean dragging;
    public int maxHeight;
    public int width;
    public int height;
    
    GuiWindow(final String titleIn) {
        this.maxHeight = (int)(ClickGui.scaledRes.getScaledHeight() * 0.8);
        this.title = titleIn;
        this.width = SurfaceHelper.getTextWidth(this.title) + 15;
    }
    
    public void setPosition(final int x, final int y) {
        this.posX = x;
        this.headerY = y;
    }
    
    private String getTitle() {
        return this.title;
    }
    
    boolean isMouseInHeader(final int mouseX, final int mouseY) {
        return mouseX > this.posX && mouseX < this.posX + this.width && mouseY > this.headerY + 22 && mouseY < this.headerY + 40;
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int state) {
        if (state != 0) {
            return;
        }
        if (this.isMouseInHeader(mouseX, mouseY)) {
            this.dragging = true;
            this.dragX = mouseX - this.posX;
            this.dragY = mouseY - this.headerY;
        }
    }
    
    public void mouseReleased(final int x, final int y, final int state) {
        this.dragging = false;
    }
    
    public void handleMouseInput() throws IOException {
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
    }
    
    public void drawWindow(final int mouseX, final int mouseY) {
        ClickGui.scaledRes = new ScaledResolution(Globals.MC);
        if (this.dragging) {
            this.posX = mouseX - this.dragX;
            this.headerY = mouseY - this.dragY;
        }
        this.drawHeader();
        this.windowY = this.headerY + 22;
        SurfaceHelper.drawRect(this.posX + 2, this.windowY + 18, this.width - 4, this.height - 20, Color.of(0, 0, 0, 150).toBuffer());
    }
    
    public void drawTooltip(final int mouseX, final int mouseY) {
    }
    
    public void drawHeader() {
        SurfaceHelper.drawRect(this.posX, this.windowY, this.width, this.height, -2139062144);
        SurfaceHelper.drawRect(this.posX + 2, this.windowY + 2, this.width - 4, 14, Color.of(0, 0, 0, 200).toBuffer());
        SurfaceHelper.drawTextShadow(this.getTitle(), this.posX + 5, this.windowY + 5, -11141291);
    }
}
