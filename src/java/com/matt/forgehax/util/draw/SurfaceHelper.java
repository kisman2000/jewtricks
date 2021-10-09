//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.draw;

import com.matt.forgehax.*;
import com.matt.forgehax.util.draw.font.*;
import javax.annotation.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.vertex.*;
import com.matt.forgehax.util.math.*;
import java.util.*;
import net.minecraft.item.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraftforge.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import net.minecraft.client.entity.*;

public class SurfaceHelper implements Globals
{
    public static void drawString(@Nullable final MinecraftFontRenderer fontRenderer, final String text, final double x, final double y, final int color, final boolean shadow) {
        if (fontRenderer == null) {
            SurfaceHelper.MC.fontRenderer.drawString(text, (float)Math.round(x), (float)Math.round(y), color, shadow);
        }
        else {
            fontRenderer.drawString(text, x, y, color, shadow);
        }
    }
    
    public static double getStringWidth(@Nullable final MinecraftFontRenderer fontRenderer, final String text) {
        if (fontRenderer == null) {
            return SurfaceHelper.MC.fontRenderer.getStringWidth(text);
        }
        return fontRenderer.getStringWidth(text);
    }
    
    public static double getStringHeight(@Nullable final MinecraftFontRenderer fontRenderer) {
        if (fontRenderer == null) {
            return SurfaceHelper.MC.fontRenderer.FONT_HEIGHT;
        }
        return fontRenderer.getHeight();
    }
    
    public static void drawRect(final int x, final int y, final int w, final int h, final int color) {
        GL11.glLineWidth(1.0f);
        Gui.drawRect(x, y, x + w, y + h, color);
    }
    
    public static void drawOutlinedRect(final int x, final int y, final int w, final int h, final int color) {
        drawOutlinedRect(x, y, w, h, color, 1.0f);
    }
    
    public static void drawOutlinedRectShaded(final int x, final int y, final int w, final int h, final int colorOutline, final int shade, final float width) {
        final int shaded = (0xFFFFFF & colorOutline) | (shade & 0xFF) << 24;
        drawRect(x, y, w, h, shaded);
        drawOutlinedRect(x, y, w, h, colorOutline, width);
    }
    
    public static void drawOutlinedRect(final int x, final int y, final int w, final int h, final int color, final float width) {
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder BufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(r, g, b, a);
        GL11.glLineWidth(width);
        BufferBuilder.begin(2, DefaultVertexFormats.POSITION);
        BufferBuilder.pos((double)x, (double)y, 0.0).endVertex();
        BufferBuilder.pos((double)x, y + (double)h, 0.0).endVertex();
        BufferBuilder.pos(x + (double)w, y + (double)h, 0.0).endVertex();
        BufferBuilder.pos(x + (double)w, (double)y, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawTexturedRect(final int x, final int y, final int textureX, final int textureY, final int width, final int height, final int zLevel) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder BufferBuilder = tessellator.getBuffer();
        BufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        BufferBuilder.pos((double)(x + 0), (double)(y + height), (double)zLevel).tex((double)((textureX + 0) * 0.00390625f), (double)((textureY + height) * 0.00390625f)).endVertex();
        BufferBuilder.pos((double)(x + width), (double)(y + height), (double)zLevel).tex((double)((textureX + width) * 0.00390625f), (double)((textureY + height) * 0.00390625f)).endVertex();
        BufferBuilder.pos((double)(x + width), (double)(y + 0), (double)zLevel).tex((double)((textureX + width) * 0.00390625f), (double)((textureY + 0) * 0.00390625f)).endVertex();
        BufferBuilder.pos((double)(x + 0), (double)(y + 0), (double)zLevel).tex((double)((textureX + 0) * 0.00390625f), (double)((textureY + 0) * 0.00390625f)).endVertex();
        tessellator.draw();
    }
    
    public static void drawLine(final int x1, final int y1, final int x2, final int y2, final int color, final float width) {
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        final float a = (color >> 24 & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder BufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(r, g, b, a);
        GL11.glLineWidth(width);
        BufferBuilder.begin(1, DefaultVertexFormats.POSITION);
        BufferBuilder.pos((double)x1, (double)y1, 0.0).endVertex();
        BufferBuilder.pos((double)x2, (double)y2, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawText(final String msg, final int x, final int y, final int color) {
        SurfaceHelper.MC.fontRenderer.drawString(msg, x, y, color);
    }
    
    public static void drawTextShadow(final String msg, final int x, final int y, final int color) {
        SurfaceHelper.MC.fontRenderer.drawStringWithShadow(msg, (float)x, (float)y, color);
    }
    
    public static void drawTextShadowCentered(final String msg, final float x, final float y, final int color) {
        final float offsetX = getTextWidth(msg) / 2.0f;
        final float offsetY = getTextHeight() / 2.0f;
        SurfaceHelper.MC.fontRenderer.drawStringWithShadow(msg, x - offsetX, y - offsetY, color);
    }
    
    public static void drawTextAlignH(final String msg, final int x, final int y, final int color, final boolean shadow, final int alignmask) {
        final int offsetX = AlignHelper.alignH(getTextWidth(msg), alignmask);
        SurfaceHelper.MC.fontRenderer.drawString(msg, (float)(x - offsetX), (float)y, color, shadow);
    }
    
    public static void drawTextShadowAlignH(final String msg, final int x, final int y, final int color, final int alignmask) {
        drawTextAlignH(msg, x, y, color, true, alignmask);
    }
    
    public static void drawTextAlign(final String msg, final int x, final int y, final int color, final boolean shadow, final int alignmask) {
        final int offsetX = AlignHelper.alignH(getTextWidth(msg), alignmask);
        final int offsetY = AlignHelper.alignV(getTextHeight(), alignmask);
        SurfaceHelper.MC.fontRenderer.drawString(msg, (float)(x - offsetX), (float)(y - offsetY), color, shadow);
    }
    
    public static void drawTextShadowAlign(final String msg, final int x, final int y, final int color, final int alignmask) {
        drawTextAlign(msg, x, y, color, true, alignmask);
    }
    
    public static void drawTextAlign(final String msg, final int x, final int y, final int color, final double scale, final boolean shadow, final int alignmask) {
        final int offsetX = AlignHelper.alignH((int)(getTextWidth(msg) * scale), alignmask);
        final int offsetY = AlignHelper.alignV((int)(getTextHeight() * scale), alignmask);
        if (scale != 1.0) {
            drawText(msg, x - offsetX, y - offsetY, color, scale, shadow);
        }
        else {
            SurfaceHelper.MC.fontRenderer.drawString(msg, (float)(x - offsetX), (float)(y - offsetY), color, shadow);
        }
    }
    
    public static void drawTextAlign(final List<String> msgList, final int x, final int y, final int color, final double scale, final boolean shadow, final int alignmask) {
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.scale(scale, scale, scale);
        final int offsetY = AlignHelper.alignV((int)(getTextHeight() * scale), alignmask);
        final int height = (int)(AlignHelper.getFlowDirY2(alignmask) * (getTextHeight() + 1) * scale);
        final float invScale = (float)(1.0 / scale);
        for (int i = 0; i < msgList.size(); ++i) {
            final int offsetX = AlignHelper.alignH((int)(getTextWidth(msgList.get(i)) * scale), alignmask);
            SurfaceHelper.MC.fontRenderer.drawString((String)msgList.get(i), (x - offsetX) * invScale, (y - offsetY + height * i) * invScale, color, shadow);
        }
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }
    
    public static void drawText(final String msg, final int x, final int y, final int color, final double scale, final boolean shadow) {
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.scale(scale, scale, scale);
        SurfaceHelper.MC.fontRenderer.drawString(msg, (float)(int)(x * (1.0 / scale)), (float)(int)(y * (1.0 / scale)), color, shadow);
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }
    
    public static void drawText(final String msg, final int x, final int y, final int color, final double scale) {
        drawText(msg, x, y, color, scale, false);
    }
    
    public static void drawTextShadow(final String msg, final int x, final int y, final int color, final double scale) {
        drawText(msg, x, y, color, scale, true);
    }
    
    public static int getTextWidth(final String text, final double scale) {
        return (int)(SurfaceHelper.MC.fontRenderer.getStringWidth(text) * scale);
    }
    
    public static int getTextWidth(final String text) {
        return getTextWidth(text, 1.0);
    }
    
    public static int getTextHeight() {
        return SurfaceHelper.MC.fontRenderer.FONT_HEIGHT;
    }
    
    public static int getTextHeight(final double scale) {
        return (int)(SurfaceHelper.MC.fontRenderer.FONT_HEIGHT * scale);
    }
    
    public static void drawItem(final ItemStack item, final int x, final int y) {
        SurfaceHelper.MC.getRenderItem().renderItemAndEffectIntoGUI(item, x, y);
    }
    
    public static void drawItemOverlay(final ItemStack stack, final int x, final int y) {
        SurfaceHelper.MC.getRenderItem().renderItemOverlayIntoGUI(SurfaceHelper.MC.fontRenderer, stack, x, y, (String)null);
    }
    
    public static void drawItem(final ItemStack item, final double x, final double y) {
        GlStateManager.pushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableLighting();
        SurfaceHelper.MC.getRenderItem().zLevel = 100.0f;
        renderItemAndEffectIntoGUI((EntityLivingBase)Wrapper.getLocalPlayer(), item, x, y, 16.0);
        SurfaceHelper.MC.getRenderItem().zLevel = 0.0f;
        GlStateManager.popMatrix();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawItemWithOverlay(final ItemStack item, final double x, final double y, final double scale) {
        GlStateManager.pushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableLighting();
        SurfaceHelper.MC.getRenderItem().zLevel = 100.0f;
        renderItemAndEffectIntoGUI((EntityLivingBase)Wrapper.getLocalPlayer(), item, x, y, 16.0);
        renderItemOverlayIntoGUI(SurfaceHelper.MC.fontRenderer, item, x, y, null, scale);
        SurfaceHelper.MC.getRenderItem().zLevel = 0.0f;
        GlStateManager.popMatrix();
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawPotionEffect(final PotionEffect potion, final int x, final int y) {
        final int index = potion.getPotion().getStatusIconIndex();
        GlStateManager.pushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        SurfaceHelper.MC.getTextureManager().bindTexture(GuiContainer.INVENTORY_BACKGROUND);
        drawTexturedRect(x, y, index % 8 * 18, 198 + index / 8 * 18, 18, 18, 100);
        potion.getPotion().renderHUDEffect(x, y, potion, SurfaceHelper.MC, 255.0f);
        GlStateManager.disableLighting();
        GlStateManager.enableDepth();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.popMatrix();
    }
    
    public static void drawHead(final ResourceLocation skinResource, final double x, final double y, final float scale) {
        GlStateManager.pushMatrix();
        SurfaceHelper.MC.renderEngine.bindTexture(skinResource);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.scale(scale, scale, scale);
        drawScaledCustomSizeModalRect(x * (1.0f / scale), y * (1.0f / scale), 8.0f, 8.0f, 8.0, 8.0, 12.0, 12.0, 64.0, 64.0);
        drawScaledCustomSizeModalRect(x * (1.0f / scale), y * (1.0f / scale), 40.0f, 8.0f, 8.0, 8.0, 12.0, 12.0, 64.0, 64.0);
        GlStateManager.popMatrix();
    }
    
    protected static void renderItemAndEffectIntoGUI(@Nullable final EntityLivingBase living, final ItemStack stack, final double x, final double y, final double scale) {
        if (!stack.isEmpty()) {
            final RenderItem getRenderItem = SurfaceHelper.MC.getRenderItem();
            getRenderItem.zLevel += 50.0f;
            try {
                renderItemModelIntoGUI(stack, x, y, SurfaceHelper.MC.getRenderItem().getItemModelWithOverrides(stack, (World)null, living), scale);
            }
            catch (Throwable t) {
                Wrapper.handleThrowable(t);
            }
            finally {
                final RenderItem getRenderItem2 = SurfaceHelper.MC.getRenderItem();
                getRenderItem2.zLevel -= 50.0f;
            }
        }
    }
    
    private static void renderItemModelIntoGUI(final ItemStack stack, final double x, final double y, IBakedModel bakedmodel, final double scale) {
        GlStateManager.pushMatrix();
        SurfaceHelper.MC.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        SurfaceHelper.MC.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.translate(x, y, (double)(100.0f + SurfaceHelper.MC.getRenderItem().zLevel));
        GlStateManager.translate(8.0f, 8.0f, 0.0f);
        GlStateManager.scale(1.0f, -1.0f, 1.0f);
        GlStateManager.scale(scale, scale, scale);
        if (bakedmodel.isGui3d()) {
            GlStateManager.enableLighting();
        }
        else {
            GlStateManager.disableLighting();
        }
        bakedmodel = ForgeHooksClient.handleCameraTransforms(bakedmodel, ItemCameraTransforms.TransformType.GUI, false);
        SurfaceHelper.MC.getRenderItem().renderItem(stack, bakedmodel);
        GlStateManager.disableAlpha();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableLighting();
        GlStateManager.popMatrix();
        SurfaceHelper.MC.getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        SurfaceHelper.MC.getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
    }
    
    protected static void renderItemOverlayIntoGUI(final FontRenderer fr, final ItemStack stack, final double xPosition, final double yPosition, @Nullable final String text, final double scale) {
        final double SCALE_RATIO = 1.23076923077;
        if (!stack.isEmpty()) {
            if (stack.getCount() != 1 || text != null) {
                final String s = (text == null) ? String.valueOf(stack.getCount()) : text;
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                GlStateManager.disableBlend();
                fr.drawStringWithShadow(s, (float)(xPosition + 19.0 - 2.0 - fr.getStringWidth(s)), (float)(yPosition + 6.0 + 3.0), 16777215);
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
                GlStateManager.enableBlend();
            }
            if (stack.getItem().showDurabilityBar(stack)) {
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                GlStateManager.disableTexture2D();
                GlStateManager.disableAlpha();
                GlStateManager.disableBlend();
                final double health = stack.getItem().getDurabilityForDisplay(stack);
                final int rgbfordisplay = stack.getItem().getRGBDurabilityForDisplay(stack);
                final int i = Math.round(13.0f - (float)health * 13.0f);
                final int j = rgbfordisplay;
                draw(xPosition + scale / 8.0, yPosition + scale / 1.23076923077, 13.0, 2.0, 0, 0, 0, 255);
                draw(xPosition + scale / 8.0, yPosition + scale / 1.23076923077, i, 1.0, j >> 16 & 0xFF, j >> 8 & 0xFF, j & 0xFF, 255);
                GlStateManager.enableBlend();
                GlStateManager.enableAlpha();
                GlStateManager.enableTexture2D();
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
            }
            final EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
            final float f3 = (entityplayersp == null) ? 0.0f : entityplayersp.getCooldownTracker().getCooldown(stack.getItem(), Minecraft.getMinecraft().getRenderPartialTicks());
            if (f3 > 0.0f) {
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                GlStateManager.disableTexture2D();
                draw(xPosition, yPosition + scale * (1.0f - f3), 16.0, scale * f3, 255, 255, 255, 127);
                GlStateManager.enableTexture2D();
                GlStateManager.enableLighting();
                GlStateManager.enableDepth();
            }
        }
    }
    
    private static void draw(final double x, final double y, final double width, final double height, final int red, final int green, final int blue, final int alpha) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder renderer = tessellator.getBuffer();
        renderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos(x + 0.0, y + 0.0, 0.0).color(red, green, blue, alpha).endVertex();
        renderer.pos(x + 0.0, y + height, 0.0).color(red, green, blue, alpha).endVertex();
        renderer.pos(x + width, y + height, 0.0).color(red, green, blue, alpha).endVertex();
        renderer.pos(x + width, y + 0.0, 0.0).color(red, green, blue, alpha).endVertex();
        Tessellator.getInstance().draw();
    }
    
    protected static void drawScaledCustomSizeModalRect(final double x, final double y, final float u, final float v, final double uWidth, final double vHeight, final double width, final double height, final double tileWidth, final double tileHeight) {
        final double f = 1.0 / tileWidth;
        final double f2 = 1.0 / tileHeight;
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, 0.0).tex(u * f, (v + (float)vHeight) * f2).endVertex();
        bufferbuilder.pos(x + width, y + height, 0.0).tex((u + (float)uWidth) * f, (v + (float)vHeight) * f2).endVertex();
        bufferbuilder.pos(x + width, y, 0.0).tex((u + (float)uWidth) * f, v * f2).endVertex();
        bufferbuilder.pos(x, y, 0.0).tex(u * f, v * f2).endVertex();
        tessellator.draw();
    }
    
    public static int getHeadWidth(final float scale) {
        return (int)(scale * 12.0f);
    }
    
    public static int getHeadWidth() {
        return getHeadWidth(1.0f);
    }
    
    public static int getHeadHeight(final float scale) {
        return (int)(scale * 12.0f);
    }
    
    public static int getHeadHeight() {
        return getHeadWidth(1.0f);
    }
}
