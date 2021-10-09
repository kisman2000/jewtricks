//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.render;

import com.matt.forgehax.util.mod.loader.*;
import net.minecraft.client.gui.*;
import com.matt.forgehax.util.mod.*;
import com.mojang.realmsclient.gui.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.base.*;
import fuck.you.jewtricks.events.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import com.matt.forgehax.util.entity.*;
import java.util.*;
import net.minecraft.util.math.*;
import com.matt.forgehax.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;

@RegisterMod
public class PlayerTracers extends ToggleMod
{
    public Setting<Integer> red;
    public Setting<Integer> green;
    public Setting<Integer> blue;
    public Setting<Integer> alpha;
    public Setting<Integer> size;
    public Setting<Boolean> antialias;
    private ScaledResolution scaledres;
    
    public PlayerTracers() {
        super(Category.RENDER, "PlayerTracers", false, "'Out of FoV arrows'" + ChatFormatting.RED + " WARNING: VERY LAGGY");
        this.red = (Setting<Integer>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("red")).description("Red value of the tracer color")).defaultTo((Object)128).min((Object)0).max((Object)255).build();
        this.green = (Setting<Integer>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("green")).description("Green value of the tracer color")).defaultTo((Object)128).min((Object)0).max((Object)255).build();
        this.blue = (Setting<Integer>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("blue")).description("Blue value of the tracer color")).defaultTo((Object)216).min((Object)0).max((Object)255).build();
        this.alpha = (Setting<Integer>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("alpha")).description("Alpha value of the tracer color")).defaultTo((Object)200).min((Object)0).max((Object)255).build();
        this.size = (Setting<Integer>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("size")).description("Size of tracers")).defaultTo((Object)8).min((Object)1).max((Object)20).build();
        this.antialias = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("antialias")).description("Toggle antialiasing")).defaultTo((Object)true).build();
        this.scaledres = new ScaledResolution(Wrapper.getMinecraft());
    }
    
    @SubscribeEvent
    public void onRender(final RenderEvent event) {
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.disableTexture2D();
        if (this.antialias.get()) {
            GL11.glEnable(2881);
            GL11.glEnable(2848);
        }
        final double cx = this.scaledres.getScaledWidth() / 2;
        final double cy = this.scaledres.getScaledHeight() / 2;
        for (final Entity entity : Wrapper.getWorld().loadedEntityList) {
            if (entity != null && !entity.equals((Object)Wrapper.getLocalPlayer())) {
                if (!(entity instanceof EntityPlayer)) {
                    continue;
                }
                final Vec3d entityPos = EntityUtils.getInterpolatedEyePos(entity, (double)Wrapper.getMinecraft().getRenderPartialTicks());
                final Plane screenPos = VectorUtils.toScreen(entityPos);
                GlStateManager.color(this.red.getAsFloat() / 255.0f, this.green.getAsFloat() / 255.0f, this.blue.getAsFloat() / 255.0f, this.alpha.getAsFloat() / 255.0f);
                GlStateManager.translate(0.0f, 0.0f, 15.0f);
                if (!screenPos.isVisible()) {
                    final double dx = cx - 2.0;
                    final double dy = cy - 20.0;
                    final double ex = (screenPos.getX() - cx) / dx;
                    final double ey = (screenPos.getY() - cy) / dy;
                    final double m = Math.abs(Math.sqrt(ex * ex + ey * ey));
                    final double nx = ex / m;
                    final double ny = ey / m;
                    final double x = cx + nx * dx;
                    final double y = cy + ny * dy;
                    final double wx = x - cx;
                    final double wy = y - cy;
                    final double ux = this.scaledres.getScaledWidth_double();
                    final double uy = 0.0;
                    final double mu = Math.sqrt(ux * ux + uy * uy);
                    final double mw = Math.sqrt(wx * wx + wy * wy);
                    double ang = Math.toDegrees(Math.acos((ux * wx + uy * wy) / (mu * mw)));
                    if (ang == Double.NaN) {
                        ang = 0.0;
                    }
                    if (y < cy) {
                        ang *= -1.0;
                    }
                    ang = (float)AngleHelper.normalizeInDegrees(ang);
                    GlStateManager.pushMatrix();
                    GlStateManager.translate(x, y, 0.0);
                    GlStateManager.rotate((float)ang, 0.0f, 0.0f, (int)this.size.get() / 2.0f);
                    GlStateManager.color(this.red.getAsFloat() / 255.0f, this.green.getAsFloat() / 255.0f, this.blue.getAsFloat() / 255.0f, this.alpha.getAsFloat() / 255.0f);
                    GlStateManager.glBegin(4);
                    GL11.glVertex2d(0.0, 0.0);
                    GL11.glVertex2d((double)(-(int)this.size.get()), (double)(-(int)this.size.get()));
                    GL11.glVertex2d((double)(-(int)this.size.get()), (double)(int)this.size.get());
                    GlStateManager.glEnd();
                    GlStateManager.popMatrix();
                }
                GlStateManager.translate(0.0f, 0.0f, -15.0f);
            }
        }
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glDisable(2881);
        GL11.glDisable(2848);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    @SubscribeEvent
    public void onScreenUpdated(final GuiScreenEvent.InitGuiEvent.Post event) {
        this.scaledres = new ScaledResolution(Wrapper.getMinecraft());
    }
}
