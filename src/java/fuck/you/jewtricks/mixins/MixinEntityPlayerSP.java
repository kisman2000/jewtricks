//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import com.mojang.authlib.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import fuck.you.jewtricks.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityPlayerSP.class })
public class MixinEntityPlayerSP extends AbstractClientPlayer
{
    public MixinEntityPlayerSP() {
        super((World)null, (GameProfile)null);
    }
    
    @Inject(method = { "onCriticalHit" }, at = { @At("HEAD") }, cancellable = true)
    public void onCriticalHit(final Entity otherEntity, final CallbackInfo info) {
    }
    
    @Inject(method = { "onUpdate" }, at = { @At("RETURN") }, cancellable = true)
    public void onUpdatePost(final CallbackInfo info) {
        final PlayerPostUpdateEvent event = new PlayerPostUpdateEvent(Wrapper.getLocalPlayer().posX, Wrapper.getLocalPlayer().posY, Wrapper.getLocalPlayer().posZ, Wrapper.getLocalPlayer().rotationYaw, Wrapper.getLocalPlayer().rotationPitch, Wrapper.getLocalPlayer().onGround);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            info.cancel();
        }
    }
    
    @Redirect(method = { "move" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(final AbstractClientPlayer player, final MoverType moverType, final double x, final double y, final double z) {
        final PlayerMoveEvent event = new PlayerMoveEvent(x, y, z, Wrapper.getLocalPlayer().onGround);
        MinecraftForge.EVENT_BUS.post((Event)event);
        super.move(moverType, event.getX(), event.getY(), event.getZ());
    }
}
