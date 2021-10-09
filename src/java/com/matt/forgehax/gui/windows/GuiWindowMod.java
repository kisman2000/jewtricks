//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.gui.windows;

import com.matt.forgehax.gui.elements.*;
import fuck.you.jewtricks.base.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.draw.*;
import java.util.*;
import com.matt.forgehax.gui.*;
import com.matt.forgehax.util.color.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import java.io.*;

public class GuiWindowMod extends GuiWindow
{
    public List<GuiButton> buttonList;
    private int buttonListOffset;
    public Category category;
    
    public GuiWindowMod(final Category categoryIn) {
        super(categoryIn.getPrettyName());
        this.buttonList = new ArrayList<GuiButton>();
        this.category = categoryIn;
        this.addModsToButtonList();
    }
    
    private void addModsToButtonList() {
        int maxWidth = 0;
        int newHeight = 0;
        for (final BaseMod mod : Wrapper.getModManager().getMods()) {
            if (mod.getModCategory().equals(this.category) && !mod.isHidden()) {
                final GuiButton moduleButton = new GuiButton(mod);
                this.buttonList.add(moduleButton);
                newHeight += 16;
                final String name = moduleButton.getName();
                final int width = SurfaceHelper.getTextWidth(name);
                if (width <= maxWidth) {
                    continue;
                }
                maxWidth = width;
            }
        }
        newHeight += 15;
        this.height = Math.min(this.maxHeight, newHeight + 3);
        this.width = maxWidth + 12;
    }
    
    private void resize() {
        int maxWidth = 0;
        int newHeight = 0;
        for (final GuiButton button : this.buttonList) {
            newHeight += 15 + 1;
            final String name = button.getName();
            final int width = SurfaceHelper.getTextWidth(name);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        newHeight += 15;
        this.height = Math.min(this.maxHeight, newHeight + 3);
        this.width = maxWidth + 12;
    }
    
    private void drawModTooltip(final BaseMod mod, final int xScaled, final int yScaled) {
        final int scale = ClickGui.scaledRes.getScaleFactor();
        final String modName = mod.getModName();
        final String modDescription = mod.getModDescription();
        final int offset = 2;
        int tooltipX = xScaled / scale + offset;
        int tooltipY = yScaled / scale + offset;
        final int padding = 2;
        final int tooltipWidth = Math.max(SurfaceHelper.getTextWidth(modName), SurfaceHelper.getTextWidth(modDescription)) / scale + padding * 2;
        final int lineHeight = SurfaceHelper.getTextHeight() / scale;
        final int lineSpacing = 2;
        final int tooltipHeight = lineHeight * 2 + lineSpacing + padding * 2;
        if ((tooltipX + tooltipWidth) * scale > ClickGui.scaledRes.getScaledWidth()) {
            tooltipX -= tooltipWidth + offset * 2;
        }
        if ((tooltipY + tooltipHeight) * scale > ClickGui.scaledRes.getScaledHeight()) {
            tooltipY -= tooltipHeight + offset * 2;
        }
        final int col = Color.of(50, 50, 50, 255).toBuffer();
        SurfaceHelper.drawRect(tooltipX * scale, tooltipY * scale + 1, tooltipWidth * scale, tooltipHeight * scale - 2, -2139062144);
        SurfaceHelper.drawRect(tooltipX * scale + 1, tooltipY * scale + 2, tooltipWidth * scale - 2, tooltipHeight * scale - 4, Color.of(0, 0, 0, 130).toBuffer());
        SurfaceHelper.drawTextShadow(modName, (tooltipX + padding) * scale, (tooltipY + padding) * scale, -11141291);
        SurfaceHelper.drawTextShadow(modDescription, (tooltipX + padding) * scale, (tooltipY + padding + lineHeight + lineSpacing) * scale, Colors.WHITE.toBuffer());
    }
    
    public void drawWindow(final int mouseX, final int mouseY) {
        final int newMaxHeight = (int)(ClickGui.scaledRes.getScaledHeight() * 0.8);
        if (newMaxHeight != this.maxHeight) {
            this.maxHeight = newMaxHeight;
            this.resize();
        }
        super.drawWindow(mouseX, mouseY);
        this.windowY = this.headerY + 22;
        if (this.buttonListOffset < 0) {
            this.buttonListOffset = 0;
        }
        int buttonY = this.windowY - this.buttonListOffset + 15;
        final int scale = ClickGui.scaledRes.getScaleFactor();
        final int fuckheight = this.height - 16;
        GL11.glPushMatrix();
        final int scissorY = Wrapper.getMinecraft().displayHeight - (scale * this.windowY + scale * fuckheight - 29);
        final int sx = scale * this.posX;
        final int sy = scissorY - 58;
        final int swidth = scale * this.width;
        final int sheight = scale * fuckheight - 8;
        GL11.glScissor(sx, sy, swidth, sheight);
        GL11.glEnable(3089);
        for (final GuiButton button : this.buttonList) {
            final int color = (mouseX > button.x && mouseX < button.x + this.width && mouseY > button.y && mouseY < button.y + 15) ? -11141291 : Colors.WHITE.toBuffer();
            SurfaceHelper.drawTextShadow(button.getName(), this.posX + 5, (int)(buttonY + 7.5f), color);
            button.setCoords(this.posX + 5, buttonY);
            buttonY += 16;
        }
        GL11.glDisable(3089);
        GL11.glPopMatrix();
        this.bottomX = this.posX + this.width;
        this.bottomY = this.windowY + this.height;
    }
    
    public void drawTooltip(final int mouseX, final int mouseY) {
        final int scale = ClickGui.scaledRes.getScaleFactor();
        if (mouseX >= this.posX && mouseX < this.bottomX && mouseY >= this.windowY + 5 / scale && mouseY < this.bottomY - 5 / scale) {
            for (final GuiButton button : this.buttonList) {
                if (mouseX > button.x && mouseX < button.x + this.width && mouseY > button.y && mouseY < button.y + 15) {
                    this.drawModTooltip(button.getMod(), mouseX, mouseY);
                    break;
                }
            }
        }
    }
    
    public void mouseClicked(final int x, final int y, final int state) {
        super.mouseClicked(x, y, state);
        for (final GuiButton button : this.buttonList) {
            if (x > button.x && x < button.x + this.width && y > button.y && y < button.y + 15 && !this.isMouseInHeader(x, y)) {
                button.toggleMod();
                break;
            }
        }
    }
    
    public void handleMouseInput() throws IOException {
        int i = Mouse.getEventDWheel();
        i = MathHelper.clamp(i, -1, 1);
        this.buttonListOffset -= i * 10;
        if (this.buttonListOffset < 0) {
            this.buttonListOffset = 0;
        }
        final int lowestButtonY = 16 * this.buttonList.size() + this.windowY;
        final int lowestAllowedOffset = lowestButtonY - this.height - this.windowY + 3;
        if (lowestButtonY - this.buttonListOffset < this.bottomY) {
            this.buttonListOffset = lowestAllowedOffset;
        }
    }
}
