//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.draw;

import java.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import com.matt.forgehax.util.draw.font.*;
import com.matt.forgehax.util.color.*;
import com.matt.forgehax.*;
import net.minecraft.item.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;

public class SurfaceBuilder
{
    public static final int COLOR = 1;
    public static final int SCALE = 2;
    public static final int TRANSLATION = 4;
    public static final int ROTATION = 8;
    public static final int ALL = 15;
    private static final SurfaceBuilder INSTANCE;
    private final Stack<RenderSettings> settings;
    private final RenderSettings DEFAULT_SETTINGS;
    
    public SurfaceBuilder() {
        this.settings = new Stack<RenderSettings>();
        this.DEFAULT_SETTINGS = new RenderSettings();
    }
    
    public static SurfaceBuilder getBuilder() {
        return SurfaceBuilder.INSTANCE;
    }
    
    private RenderSettings current() {
        return this.settings.isEmpty() ? this.DEFAULT_SETTINGS : this.settings.peek();
    }
    
    public SurfaceBuilder begin(final int mode) {
        GL11.glBegin(mode);
        return this;
    }
    
    public SurfaceBuilder beginLines() {
        return this.begin(1);
    }
    
    public SurfaceBuilder beginLineLoop() {
        return this.begin(2);
    }
    
    public SurfaceBuilder beginQuads() {
        return this.begin(7);
    }
    
    public SurfaceBuilder beginPolygon() {
        return this.begin(9);
    }
    
    public SurfaceBuilder end() {
        GL11.glEnd();
        return this;
    }
    
    public SurfaceBuilder autoApply(final boolean enabled) {
        this.current().setAutoApply(enabled);
        return this;
    }
    
    public SurfaceBuilder apply() {
        return this.apply(15);
    }
    
    public SurfaceBuilder apply(final int flags) {
        final RenderSettings current = this.current();
        if ((flags & 0x1) == 0x1) {
            current.applyColor();
        }
        if ((flags & 0x2) == 0x2) {
            current.applyScale();
        }
        if ((flags & 0x4) == 0x4) {
            current.applyTranslation();
        }
        if ((flags & 0x8) == 0x8) {
            current.applyRotation();
        }
        return this;
    }
    
    public SurfaceBuilder reset() {
        return this.reset(15);
    }
    
    public SurfaceBuilder reset(final int flags) {
        final RenderSettings current = this.current();
        if ((flags & 0x1) == 0x1) {
            current.resetColor();
        }
        if ((flags & 0x2) == 0x2) {
            current.resetScale();
        }
        if ((flags & 0x4) == 0x4) {
            current.resetTranslation();
        }
        if ((flags & 0x8) == 0x8) {
            current.resetRotation();
        }
        return this;
    }
    
    public SurfaceBuilder push() {
        GlStateManager.pushMatrix();
        this.settings.push(new RenderSettings());
        return this;
    }
    
    public SurfaceBuilder pop() {
        if (!this.settings.isEmpty()) {
            this.settings.pop();
        }
        GlStateManager.popMatrix();
        return this;
    }
    
    public SurfaceBuilder color(final double r, final double g, final double b, final double a) {
        this.current().setColor4d(new double[] { MathHelper.clamp(r, 0.0, 1.0), MathHelper.clamp(g, 0.0, 1.0), MathHelper.clamp(b, 0.0, 1.0), MathHelper.clamp(a, 0.0, 1.0) });
        return this;
    }
    
    public SurfaceBuilder color(final int buffer) {
        return this.color((buffer >> 16 & 0xFF) / 255.0, (buffer >> 8 & 0xFF) / 255.0, (buffer & 0xFF) / 255.0, (buffer >> 24 & 0xFF) / 255.0);
    }
    
    public SurfaceBuilder color(final int r, final int g, final int b, final int a) {
        return this.color(r / 255.0, g / 255.0, b / 255.0, a / 255.0);
    }
    
    public SurfaceBuilder scale(final double x, final double y, final double z) {
        this.current().setScale3d(new double[] { x, y, z });
        return this;
    }
    
    public SurfaceBuilder scale(final double s) {
        return this.scale(s, s, s);
    }
    
    public SurfaceBuilder scale() {
        return this.scale(0.0);
    }
    
    public SurfaceBuilder translate(final double x, final double y, final double z) {
        this.current().setTranslate3d(new double[] { x, y, z });
        return this;
    }
    
    public SurfaceBuilder translate(final double x, final double y) {
        return this.translate(x, y, 0.0);
    }
    
    public SurfaceBuilder rotate(final double angle, final double x, final double y, final double z) {
        this.current().setRotated4d(new double[] { angle, x, y, z });
        return this;
    }
    
    public SurfaceBuilder width(final double width) {
        GlStateManager.glLineWidth((float)width);
        return this;
    }
    
    public SurfaceBuilder vertex(final double x, final double y, final double z) {
        GL11.glVertex3d(x, y, z);
        return this;
    }
    
    public SurfaceBuilder vertex(final double x, final double y) {
        GL11.glVertex2d(x, y);
        return this;
    }
    
    public SurfaceBuilder line(final double startX, final double startY, final double endX, final double endY) {
        return this.vertex(startX, startY).vertex(endX, endY);
    }
    
    public SurfaceBuilder rectangle(final double x, final double y, final double w, final double h) {
        return this.vertex(x, y).vertex(x, y + h).vertex(x + w, y + h).vertex(x + w, y);
    }
    
    public SurfaceBuilder fontRenderer(final MinecraftFontRenderer fontRenderer) {
        this.current().setFontRenderer(fontRenderer);
        return this;
    }
    
    private SurfaceBuilder text(final String text, final double x, final double y, final boolean shadow) {
        if (this.current().hasFontRenderer()) {
            this.current().getFontRenderer().drawString(text, x, y + 1.0, Color.of(this.current().getColor4d()).toBuffer(), shadow);
        }
        else {
            GlStateManager.pushMatrix();
            GlStateManager.translate(x, y, 0.0);
            Globals.MC.fontRenderer.drawString(text, 0.0f, 0.0f, Color.of(this.current().getColor4d()).toBuffer(), shadow);
            GlStateManager.popMatrix();
        }
        return this;
    }
    
    public SurfaceBuilder text(final String text, final double x, final double y) {
        return this.text(text, x, y, false);
    }
    
    public SurfaceBuilder textWithShadow(final String text, final double x, final double y) {
        return this.text(text, x, y, true);
    }
    
    public SurfaceBuilder task(final Runnable task) {
        task.run();
        return this;
    }
    
    public SurfaceBuilder item(final ItemStack stack, final double x, final double y) {
        Globals.MC.getRenderItem().zLevel = 100.0f;
        SurfaceHelper.renderItemAndEffectIntoGUI((EntityLivingBase)Wrapper.getLocalPlayer(), stack, x, y, this.current().hasScale() ? this.current().getScale3d()[0] : 16.0);
        Globals.MC.getRenderItem().zLevel = 0.0f;
        return this;
    }
    
    public SurfaceBuilder itemOverlay(final ItemStack stack, final double x, final double y) {
        SurfaceHelper.renderItemOverlayIntoGUI(Globals.MC.fontRenderer, stack, x, y, null, this.current().hasScale() ? this.current().getScale3d()[0] : 16.0);
        return this;
    }
    
    public SurfaceBuilder head(final ResourceLocation resource, final double x, final double y) {
        Globals.MC.renderEngine.bindTexture(resource);
        final double scale = this.current().hasScale() ? this.current().getScale3d()[0] : 12.0;
        SurfaceHelper.drawScaledCustomSizeModalRect(x * (1.0 / scale), y * (1.0 / scale), 8.0f, 8.0f, 8.0, 8.0, 12.0, 12.0, 64.0, 64.0);
        SurfaceHelper.drawScaledCustomSizeModalRect(x * (1.0 / scale), y * (1.0 / scale), 40.0f, 8.0f, 8.0, 8.0, 12.0, 12.0, 64.0, 64.0);
        return this;
    }
    
    public int getFontWidth(final String text) {
        return this.current().hasFontRenderer() ? this.current().getFontRenderer().getStringWidth(text) : Globals.MC.fontRenderer.getStringWidth(text);
    }
    
    public int getFontHeight() {
        return this.current().hasFontRenderer() ? this.current().getFontRenderer().getHeight() : Globals.MC.fontRenderer.FONT_HEIGHT;
    }
    
    public int getFontHeight(final String text) {
        return this.getFontHeight();
    }
    
    private double _getScaled(final int index, final double p) {
        return p * (1.0 / this.current().getScale3d()[index]);
    }
    
    public double getScaledX(final double x) {
        return this._getScaled(0, x);
    }
    
    public double getScaledY(final double y) {
        return this._getScaled(1, y);
    }
    
    public double getScaledZ(final double z) {
        return this._getScaled(2, z);
    }
    
    public double getScaled(final double p) {
        return this.getScaledX(p);
    }
    
    public double getItemSize() {
        return 16.0;
    }
    
    public static void disableTexture2D() {
        GlStateManager.disableTexture2D();
    }
    
    public static void enableTexture2D() {
        GlStateManager.enableTexture2D();
    }
    
    public static void enableBlend() {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
    }
    
    public static void disableBlend() {
        GlStateManager.disableBlend();
    }
    
    public static void enableFontRendering() {
        GlStateManager.disableDepth();
    }
    
    public static void disableFontRendering() {
        GlStateManager.enableDepth();
    }
    
    public static void enableItemRendering() {
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableLighting();
    }
    
    public static void disableItemRendering() {
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
    }
    
    public static void clearColor() {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    static {
        INSTANCE = new SurfaceBuilder();
    }
    
    private static class RenderSettings
    {
        private static final double[] EMPTY_VECTOR3D;
        private static final double[] EMPTY_VECTOR4D;
        private double[] color4d;
        private double[] scale3d;
        private double[] translate3d;
        private double[] rotated4d;
        private boolean autoApply;
        private MinecraftFontRenderer fontRenderer;
        
        private RenderSettings() {
            this.color4d = RenderSettings.EMPTY_VECTOR4D;
            this.scale3d = RenderSettings.EMPTY_VECTOR3D;
            this.translate3d = RenderSettings.EMPTY_VECTOR3D;
            this.rotated4d = RenderSettings.EMPTY_VECTOR4D;
            this.autoApply = true;
            this.fontRenderer = null;
        }
        
        public double[] getColor4d() {
            return this.color4d;
        }
        
        public void setColor4d(final double[] color4d) {
            this.color4d = color4d;
            if (this.autoApply) {
                this.applyColor();
            }
        }
        
        public double[] getScale3d() {
            return this.scale3d;
        }
        
        public void setScale3d(final double[] scale3d) {
            this.scale3d = scale3d;
            if (this.autoApply) {
                this.applyScale();
            }
        }
        
        public double[] getTranslate3d() {
            return this.translate3d;
        }
        
        public void setTranslate3d(final double[] translate3d) {
            this.translate3d = translate3d;
            if (this.autoApply) {
                this.applyTranslation();
            }
        }
        
        public double[] getRotated4d() {
            return this.rotated4d;
        }
        
        public void setRotated4d(final double[] rotated4d) {
            this.rotated4d = rotated4d;
            if (this.autoApply) {
                this.applyRotation();
            }
        }
        
        public MinecraftFontRenderer getFontRenderer() {
            return this.fontRenderer;
        }
        
        public void setFontRenderer(final MinecraftFontRenderer fontRenderer) {
            this.fontRenderer = fontRenderer;
        }
        
        public void setAutoApply(final boolean autoApply) {
            this.autoApply = autoApply;
        }
        
        public boolean hasColor() {
            return this.color4d != RenderSettings.EMPTY_VECTOR4D;
        }
        
        public boolean hasScale() {
            return this.scale3d != RenderSettings.EMPTY_VECTOR3D;
        }
        
        public boolean hasTranslation() {
            return this.translate3d != RenderSettings.EMPTY_VECTOR3D;
        }
        
        public boolean hasRotation() {
            return this.rotated4d != RenderSettings.EMPTY_VECTOR4D;
        }
        
        public boolean hasFontRenderer() {
            return this.fontRenderer != null;
        }
        
        public void applyColor() {
            if (this.hasColor()) {
                GL11.glColor4d(this.color4d[0], this.color4d[1], this.color4d[2], this.color4d[3]);
            }
        }
        
        public void applyScale() {
            if (this.hasScale()) {
                GL11.glScaled(this.scale3d[0], this.scale3d[1], this.scale3d[2]);
            }
        }
        
        public void applyTranslation() {
            if (this.hasTranslation()) {
                GL11.glTranslated(this.translate3d[0], this.translate3d[1], this.translate3d[2]);
            }
        }
        
        public void applyRotation() {
            if (this.hasRotation()) {
                GL11.glRotated(this.rotated4d[0], this.rotated4d[1], this.rotated4d[2], this.rotated4d[3]);
            }
        }
        
        public void clearColor() {
            this.color4d = RenderSettings.EMPTY_VECTOR4D;
        }
        
        public void clearScale() {
            this.scale3d = RenderSettings.EMPTY_VECTOR3D;
        }
        
        public void clearTranslation() {
            this.translate3d = RenderSettings.EMPTY_VECTOR3D;
        }
        
        public void clearRotation() {
            this.rotated4d = RenderSettings.EMPTY_VECTOR4D;
        }
        
        public void clearFontRenderer() {
            this.fontRenderer = null;
        }
        
        public void resetColor() {
            if (this.hasColor()) {
                this.clearColor();
                GL11.glColor4d(1.0, 1.0, 1.0, 1.0);
            }
        }
        
        public void resetScale() {
            if (this.hasScale()) {
                this.clearScale();
                GL11.glScaled(1.0, 1.0, 1.0);
            }
        }
        
        public void resetTranslation() {
            if (this.hasTranslation()) {
                this.clearTranslation();
                GL11.glTranslated(0.0, 0.0, 0.0);
            }
        }
        
        public void resetRotation() {
            if (this.hasRotation()) {
                this.clearRotation();
                GL11.glRotated(0.0, 0.0, 0.0, 0.0);
            }
        }
        
        static {
            EMPTY_VECTOR3D = new double[] { 0.0, 0.0, 0.0 };
            EMPTY_VECTOR4D = new double[] { 0.0, 0.0, 0.0, 0.0 };
        }
    }
}
