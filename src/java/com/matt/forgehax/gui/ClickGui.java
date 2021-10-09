//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.gui;

import com.matt.forgehax.*;
import com.matt.forgehax.gui.windows.*;
import net.minecraft.client.gui.*;
import com.matt.forgehax.util.mod.*;
import net.minecraftforge.client.event.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import fuck.you.jewtricks.base.*;
import com.matt.forgehax.util.draw.*;
import com.matt.forgehax.util.color.*;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import com.google.common.collect.*;
import java.io.*;
import org.lwjgl.input.*;

public class ClickGui extends GuiScreen implements Globals
{
    private static ClickGui INSTANCE;
    public final List<GuiWindow> windowList;
    private String breakingtext;
    private int breakingsize;
    private List<String> breakingstrs;
    private List<Integer> breakingstrslens;
    private int breakingindex;
    private int breakingstralpha;
    private int breakingstrtime;
    private int breakingstrstate;
    private GuiWindowMod worldWindow;
    private GuiWindowMod combatWindow;
    private GuiWindowMod renderWindow;
    private GuiWindowMod miscWindow;
    private GuiWindowMod movementWindow;
    public static ScaledResolution scaledRes;
    public int baseColor;
    private float rainbow;
    
    private ClickGui() {
        this.windowList = new ArrayList<GuiWindow>();
        this.breakingtext = "+++ BREAKING NEWS +++";
        this.breakingsize = 0;
        this.breakingstrs = Arrays.asList("Study: 77% of LGBT+ gamers prefer consoles over PC", "Study: 40% of transgender people have attempted suicide", "44% of all Armed Robbery is NOT committed by Black People", "46% of all Murder is NOT committed by Black People", "27% of Black Babies DO have fathers", "Study shows that only 1 in 3 Black Men have Felony Convictions", "25% of NYC Shootings are NOT committed by Black People", "53% of NYC Rape is NOT committed by Black People", "7% of Black Murder is NOT committed by other Blacks");
        this.breakingstrslens = new ArrayList<Integer>();
        this.breakingindex = 0;
        this.breakingstralpha = 0;
        this.breakingstrtime = 0;
        this.breakingstrstate = 0;
        this.worldWindow = new GuiWindowMod(Category.WORLD);
        this.combatWindow = new GuiWindowMod(Category.COMBAT);
        this.renderWindow = new GuiWindowMod(Category.RENDER);
        this.miscWindow = new GuiWindowMod(Category.MISC);
        this.movementWindow = new GuiWindowMod(Category.MOVEMENT);
        this.windowList.add(this.worldWindow);
        this.windowList.add(this.combatWindow);
        this.windowList.add(this.renderWindow);
        this.windowList.add(this.miscWindow);
        this.windowList.add(this.movementWindow);
        this.rainbow = 0.0f;
        for (int i = 0; i < this.windowList.size(); ++i) {
            final int x = (i + 1) * ClickGui.scaledRes.getScaledWidth() / (this.windowList.size() + 1) - this.windowList.get(i).width / 2 + 30;
            final int y = ClickGui.scaledRes.getScaledHeight() / 15;
            this.windowList.get(i).setPosition(x, y);
        }
    }
    
    public static ClickGui getInstance() {
        return (ClickGui.INSTANCE == null) ? (ClickGui.INSTANCE = new ClickGui()) : ClickGui.INSTANCE;
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    @SubscribeEvent
    public void onScreenUpdated(final GuiScreenEvent.InitGuiEvent.Post ev) {
        ClickGui.scaledRes = new ScaledResolution(ClickGui.MC);
        this.breakingsize = ClickGui.MC.fontRenderer.getStringWidth(this.breakingtext);
        this.breakingstrslens.clear();
        for (final String str : this.breakingstrs) {
            this.breakingstrslens.add(ClickGui.MC.fontRenderer.getStringWidth(str));
        }
    }
    
    public void moveWindowToTop(final GuiWindow window) {
        if (this.windowList.remove(window)) {
            this.windowList.add(window);
        }
    }
    
    public boolean isMouseInWindow(final int mouseX, final int mouseY, final GuiWindow window) {
        return mouseX > window.posX && mouseX < window.bottomX && mouseY > window.headerY && mouseY < window.bottomY;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        if (this.breakingsize == 0) {
            this.breakingsize = ClickGui.MC.fontRenderer.getStringWidth(this.breakingtext);
            this.breakingstrslens.clear();
            for (final String str : this.breakingstrs) {
                this.breakingstrslens.add(ClickGui.MC.fontRenderer.getStringWidth(str));
            }
        }
        this.rainbow += 0.06666667f * Wrapper.getFrametime();
        if (this.rainbow > 1.0f) {
            this.rainbow = 0.0f;
        }
        SurfaceHelper.drawRect(0, 0, ClickGui.scaledRes.getScaledWidth(), 15, Color.of(120, 120, 120, 120).toBuffer());
        final int rgb = java.awt.Color.HSBtoRGB(this.rainbow, 1.0f, 1.0f);
        final int red = rgb >> 16 & 0xFF;
        final int green = rgb >> 8 & 0xFF;
        final int blue = rgb & 0xFF;
        SurfaceHelper.drawTextShadow(this.breakingtext, 5, 4, Color.of(red, green, blue, 255).toBuffer());
        SurfaceHelper.drawLine(0, 15, ClickGui.scaledRes.getScaledWidth(), 15, Colors.BLACK.toBuffer(), 1.0f);
        SurfaceHelper.drawLine(this.breakingsize + 10, 0, this.breakingsize + 10, 15, Colors.BLACK.toBuffer(), 1.0f);
        final String currentstr = this.breakingstrs.get(this.breakingindex);
        this.breakingstralpha = MathHelper.clamp(this.breakingstralpha, 1, 255);
        switch (this.breakingstrstate) {
            case 0: {
                if (this.breakingstralpha <= 0) {
                    this.breakingstralpha = 1;
                }
                this.breakingstralpha += (int)(170.0f * Wrapper.getFrametime());
                if (this.breakingstralpha >= 255) {
                    this.breakingstrstate = 1;
                }
                GL11.glPushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                final int color = 16777215;
                SurfaceHelper.drawTextShadow(currentstr, this.breakingsize + 15, 4, color + (this.breakingstralpha << 24 & -color));
                GlStateManager.disableBlend();
                GL11.glPopMatrix();
                if (this.breakingstralpha >= 255) {
                    this.breakingstralpha = 0;
                    this.breakingstrtime = 0;
                    break;
                }
                break;
            }
            case 1: {
                SurfaceHelper.drawTextShadow(currentstr, this.breakingsize + 15, 4, Colors.WHITE.toBuffer());
                this.breakingstrtime += (int)(200.0f * Wrapper.getFrametime());
                if (this.breakingstrtime >= 100) {
                    this.breakingstralpha = 255;
                    this.breakingstrstate = 2;
                    break;
                }
                break;
            }
            case 2: {
                this.breakingstralpha -= (int)(170.0f * Wrapper.getFrametime());
                this.breakingstralpha = MathHelper.clamp(this.breakingstralpha, 1, 255);
                if (this.breakingstralpha > 60) {
                    this.breakingstralpha = MathHelper.clamp(this.breakingstralpha, 1, 255);
                    GL11.glPushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    final int _color = 16777215;
                    SurfaceHelper.drawTextShadow(currentstr, this.breakingsize + 15, 4, _color + (this.breakingstralpha << 24 & -_color));
                    GlStateManager.disableBlend();
                    GL11.glPopMatrix();
                    break;
                }
                this.breakingstralpha = 0;
                this.breakingstrstate = 0;
                ++this.breakingindex;
                if (this.breakingindex >= this.breakingstrs.size()) {
                    this.breakingindex = 0;
                    break;
                }
                break;
            }
        }
        for (final GuiWindow window : this.windowList) {
            window.drawWindow(mouseX, mouseY);
        }
        for (final GuiWindow window : this.windowList) {
            window.drawTooltip(mouseX, mouseY);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int b) throws IOException {
        try {
            for (final GuiWindow window : Lists.reverse((List)this.windowList)) {
                if (this.isMouseInWindow(mouseX, mouseY, window)) {
                    window.mouseClicked(mouseX, mouseY, b);
                    this.moveWindowToTop(window);
                    return;
                }
            }
            super.mouseClicked(mouseX, mouseY, b);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mouseReleased(final int x, final int y, final int state) {
        for (final GuiWindow window : this.windowList) {
            window.mouseReleased(x, y, state);
        }
        super.mouseReleased(x, y, state);
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (final GuiWindow window : this.windowList) {
            window.keyTyped(typedChar, keyCode);
        }
    }
    
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        final int scale = ClickGui.scaledRes.getScaleFactor();
        for (final GuiWindow window : Lists.reverse((List)this.windowList)) {
            if (this.isMouseInWindow(Mouse.getEventX() / scale, (ClickGui.MC.displayHeight - Mouse.getEventY()) / scale, window)) {
                window.handleMouseInput();
                break;
            }
        }
    }
    
    static {
        ClickGui.scaledRes = new ScaledResolution(ClickGui.MC);
    }
}
