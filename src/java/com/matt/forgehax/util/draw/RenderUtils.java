//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.draw;

import com.matt.forgehax.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import fuck.you.jewtricks.base.*;
import com.matt.forgehax.util.entity.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class RenderUtils implements Globals
{
    public static Vec3d getRenderPos() {
        return new Vec3d(RenderUtils.MC.player.lastTickPosX + (RenderUtils.MC.player.posX - RenderUtils.MC.player.lastTickPosX) * RenderUtils.MC.getRenderPartialTicks(), RenderUtils.MC.player.lastTickPosY + (RenderUtils.MC.player.posY - RenderUtils.MC.player.lastTickPosY) * RenderUtils.MC.getRenderPartialTicks(), RenderUtils.MC.player.lastTickPosZ + (RenderUtils.MC.player.posZ - RenderUtils.MC.player.lastTickPosZ) * RenderUtils.MC.getRenderPartialTicks());
    }
    
    public static void drawLine(final Vec3d startPos, final Vec3d endPos, final int color, final boolean smooth, final float width) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder BufferBuilder = tessellator.getBuffer();
        final Vec3d endVecPos = endPos.subtract(startPos);
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        final float a = (color >> 24 & 0xFF) / 255.0f;
        if (smooth) {
            GL11.glEnable(2848);
        }
        GL11.glLineWidth(width);
        GlStateManager.pushMatrix();
        GlStateManager.translate(startPos.x, startPos.y, startPos.z);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        BufferBuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        BufferBuilder.pos(0.0, 0.0, 0.0).color(r, g, b, a).endVertex();
        BufferBuilder.pos(endVecPos.x, endVecPos.y, endVecPos.z).color(r, g, b, a).endVertex();
        tessellator.draw();
        if (smooth) {
            GL11.glDisable(2848);
        }
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }
    
    public static void drawBox(final Vec3d startPos, final Vec3d endPos, final int color, final float width, final boolean ignoreZ) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder buffer = tessellator.getBuffer();
        final Vec3d renderPos = EntityUtils.getInterpolatedPos((Entity)Wrapper.getLocalPlayer(), RenderUtils.MC.getRenderPartialTicks());
        final Vec3d min = startPos.subtract(renderPos);
        final Vec3d max = endPos.subtract(renderPos);
        final double minX = min.x;
        final double minY = min.y;
        final double minZ = min.z;
        final double maxX = max.x;
        final double maxY = max.y;
        final double maxZ = max.z;
        final float r = (color >> 16 & 0xFF) / 255.0f;
        final float g = (color >> 8 & 0xFF) / 255.0f;
        final float b = (color & 0xFF) / 255.0f;
        final float a = (color >> 24 & 0xFF) / 255.0f;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.glLineWidth(width);
        if (ignoreZ) {
            GlStateManager.disableDepth();
        }
        GlStateManager.color(r, g, b, a);
        buffer.begin(3, DefaultVertexFormats.POSITION);
        buffer.pos(minX, minY, minZ).endVertex();
        buffer.pos(maxX, minY, minZ).endVertex();
        buffer.pos(maxX, minY, maxZ).endVertex();
        buffer.pos(minX, minY, maxZ).endVertex();
        buffer.pos(minX, minY, minZ).endVertex();
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION);
        buffer.pos(minX, maxY, minZ).endVertex();
        buffer.pos(maxX, maxY, minZ).endVertex();
        buffer.pos(maxX, maxY, maxZ).endVertex();
        buffer.pos(minX, maxY, maxZ).endVertex();
        buffer.pos(minX, maxY, minZ).endVertex();
        tessellator.draw();
        buffer.begin(1, DefaultVertexFormats.POSITION);
        buffer.pos(minX, minY, minZ).endVertex();
        buffer.pos(minX, maxY, minZ).endVertex();
        buffer.pos(maxX, minY, minZ).endVertex();
        buffer.pos(maxX, maxY, minZ).endVertex();
        buffer.pos(maxX, minY, maxZ).endVertex();
        buffer.pos(maxX, maxY, maxZ).endVertex();
        buffer.pos(minX, minY, maxZ).endVertex();
        buffer.pos(minX, maxY, maxZ).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }
    
    public static void drawBox(final BlockPos startPos, final BlockPos endPos, final int color, final float width, final boolean ignoreZ) {
        drawBox(new Vec3d((double)startPos.getX(), (double)startPos.getY(), (double)startPos.getZ()), new Vec3d((double)endPos.getX(), (double)endPos.getY(), (double)endPos.getZ()), color, width, ignoreZ);
    }
}
