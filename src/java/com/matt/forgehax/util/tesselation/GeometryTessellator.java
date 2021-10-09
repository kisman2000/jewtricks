//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.tesselation;

import net.minecraft.client.renderer.vertex.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;

public class GeometryTessellator extends Tessellator
{
    private static GeometryTessellator instance;
    private static double deltaS;
    private double delta;
    
    public GeometryTessellator() {
        this(2097152);
    }
    
    public GeometryTessellator(final int size) {
        super(size);
        this.delta = 0.0;
    }
    
    public static GeometryTessellator getInstance() {
        if (GeometryTessellator.instance == null) {
            GeometryTessellator.instance = new GeometryTessellator();
        }
        return GeometryTessellator.instance;
    }
    
    public void setTranslation(final double x, final double y, final double z) {
        this.getBuffer().setTranslation(x, y, z);
    }
    
    public void beginQuads() {
        this.begin(7);
    }
    
    public void beginLines() {
        this.begin(1);
    }
    
    public void begin(final int mode) {
        this.getBuffer().begin(mode, DefaultVertexFormats.POSITION_COLOR);
    }
    
    public void draw() {
        super.draw();
    }
    
    public void setDelta(final double delta) {
        this.delta = delta;
    }
    
    public static void setStaticDelta(final double delta) {
        GeometryTessellator.deltaS = delta;
    }
    
    public void drawCuboid(final BlockPos pos, final int sides, final int argb) {
        this.drawCuboid(pos, pos, sides, argb);
    }
    
    public void drawCuboid(final BlockPos begin, final BlockPos end, final int sides, final int argb) {
        drawCuboid(this.getBuffer(), begin, end, sides, argb, this.delta);
    }
    
    public static void drawCuboid(final BufferBuilder buffer, final BlockPos pos, final int sides, final int argb) {
        drawCuboid(buffer, pos, pos, sides, argb);
    }
    
    public static void drawCuboid(final BufferBuilder buffer, final BlockPos begin, final BlockPos end, final int sides, final int argb) {
        drawCuboid(buffer, begin, end, sides, argb, GeometryTessellator.deltaS);
    }
    
    private static void drawCuboid(final BufferBuilder buffer, final BlockPos begin, final BlockPos end, final int sides, final int argb, final double delta) {
        if (buffer.getDrawMode() == -1 || sides == 0) {
            return;
        }
        final double x0 = begin.getX() - delta;
        final double y0 = begin.getY() - delta;
        final double z0 = begin.getZ() - delta;
        final double x2 = end.getX() + 1 + delta;
        final double y2 = end.getY() + 1 + delta;
        final double z2 = end.getZ() + 1 + delta;
        switch (buffer.getDrawMode()) {
            case 7: {
                drawQuads(buffer, x0, y0, z0, x2, y2, z2, sides, argb);
                break;
            }
            case 1: {
                drawLines(buffer, x0, y0, z0, x2, y2, z2, sides, argb);
                break;
            }
            default: {
                throw new IllegalStateException("Unsupported mode!");
            }
        }
    }
    
    public static void drawQuads(final BufferBuilder buffer, final double x0, final double y0, final double z0, final double x1, final double y1, final double z1, final int sides, final int argb) {
        final int a = argb >>> 24 & 0xFF;
        final int r = argb >>> 16 & 0xFF;
        final int g = argb >>> 8 & 0xFF;
        final int b = argb & 0xFF;
        drawQuads(buffer, x0, y0, z0, x1, y1, z1, sides, a, r, g, b);
    }
    
    public static void drawQuads(final BufferBuilder buffer, final double x0, final double y0, final double z0, final double x1, final double y1, final double z1, final int sides, final int a, final int r, final int g, final int b) {
        if ((sides & 0x1) != 0x0) {
            buffer.pos(x1, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x1, y0, z1).color(r, g, b, a).endVertex();
            buffer.pos(x0, y0, z1).color(r, g, b, a).endVertex();
            buffer.pos(x0, y0, z0).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x2) != 0x0) {
            buffer.pos(x1, y1, z0).color(r, g, b, a).endVertex();
            buffer.pos(x0, y1, z0).color(r, g, b, a).endVertex();
            buffer.pos(x0, y1, z1).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z1).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x4) != 0x0) {
            buffer.pos(x1, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x0, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x0, y1, z0).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z0).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x8) != 0x0) {
            buffer.pos(x0, y0, z1).color(r, g, b, a).endVertex();
            buffer.pos(x1, y0, z1).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z1).color(r, g, b, a).endVertex();
            buffer.pos(x0, y1, z1).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x10) != 0x0) {
            buffer.pos(x0, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x0, y0, z1).color(r, g, b, a).endVertex();
            buffer.pos(x0, y1, z1).color(r, g, b, a).endVertex();
            buffer.pos(x0, y1, z0).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x20) != 0x0) {
            buffer.pos(x1, y0, z1).color(r, g, b, a).endVertex();
            buffer.pos(x1, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z0).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z1).color(r, g, b, a).endVertex();
        }
    }
    
    public static void drawLines(final BufferBuilder buffer, final double x0, final double y0, final double z0, final double x1, final double y1, final double z1, final int sides, final int argb) {
        final int a = argb >>> 24 & 0xFF;
        final int r = argb >>> 16 & 0xFF;
        final int g = argb >>> 8 & 0xFF;
        final int b = argb & 0xFF;
        drawLines(buffer, x0, y0, z0, x1, y1, z1, sides, a, r, g, b);
    }
    
    public static void drawLines(final BufferBuilder buffer, final double x0, final double y0, final double z0, final double x1, final double y1, final double z1, final int sides, final int a, final int r, final int g, final int b) {
        if ((sides & 0x11) != 0x0) {
            buffer.pos(x0, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x0, y0, z1).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x12) != 0x0) {
            buffer.pos(x0, y1, z0).color(r, g, b, a).endVertex();
            buffer.pos(x0, y1, z1).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x21) != 0x0) {
            buffer.pos(x1, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x1, y0, z1).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x22) != 0x0) {
            buffer.pos(x1, y1, z0).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z1).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x5) != 0x0) {
            buffer.pos(x0, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x1, y0, z0).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x6) != 0x0) {
            buffer.pos(x0, y1, z0).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z0).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x9) != 0x0) {
            buffer.pos(x0, y0, z1).color(r, g, b, a).endVertex();
            buffer.pos(x1, y0, z1).color(r, g, b, a).endVertex();
        }
        if ((sides & 0xA) != 0x0) {
            buffer.pos(x0, y1, z1).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z1).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x14) != 0x0) {
            buffer.pos(x0, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x0, y1, z0).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x24) != 0x0) {
            buffer.pos(x1, y0, z0).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z0).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x18) != 0x0) {
            buffer.pos(x0, y0, z1).color(r, g, b, a).endVertex();
            buffer.pos(x0, y1, z1).color(r, g, b, a).endVertex();
        }
        if ((sides & 0x28) != 0x0) {
            buffer.pos(x1, y0, z1).color(r, g, b, a).endVertex();
            buffer.pos(x1, y1, z1).color(r, g, b, a).endVertex();
        }
    }
    
    static {
        GeometryTessellator.instance = null;
        GeometryTessellator.deltaS = 0.0;
    }
}
