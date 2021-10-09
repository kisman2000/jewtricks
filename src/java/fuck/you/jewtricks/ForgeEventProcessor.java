//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks;

import net.minecraftforge.client.event.sound.*;
import net.minecraftforge.event.entity.living.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.passive.*;
import net.minecraft.client.renderer.*;
import fuck.you.jewtricks.events.*;
import net.minecraftforge.fml.common.gameevent.*;
import com.matt.forgehax.util.command.*;

public class ForgeEventProcessor
{
    @SubscribeEvent
    public void playSound(final PlaySoundEvent event) {
        if (event.getName().equals("entity.player.attack.crit")) {}
    }
    
    @SubscribeEvent
    public void playSoundSource(final PlaySoundSourceEvent event) {
    }
    
    @SubscribeEvent
    public void onUpdate(final LivingEvent.LivingUpdateEvent event) {
        if (Wrapper.getMinecraft() != null && Wrapper.getWorld() != null && event.getEntity().getEntityWorld().isRemote && event.getEntityLiving().equals((Object)Wrapper.getLocalPlayer())) {
            final PlayerUpdateEvent _event = new PlayerUpdateEvent(event.getEntityLiving());
            MinecraftForge.EVENT_BUS.post((Event)_event);
            event.setCanceled(_event.isCanceled());
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        try {
            if (event.isCanceled()) {
                return;
            }
            RenderGameOverlayEvent.ElementType target = RenderGameOverlayEvent.ElementType.EXPERIENCE;
            if (!Wrapper.getLocalPlayer().isCreative() && Wrapper.getLocalPlayer().getRidingEntity() != null && Wrapper.getLocalPlayer().getRidingEntity() instanceof AbstractHorse) {
                target = RenderGameOverlayEvent.ElementType.HEALTHMOUNT;
            }
            if (event.getType().equals((Object)target)) {
                final RenderEvent renderevent = new RenderEvent(event.getPartialTicks());
                MinecraftForge.EVENT_BUS.post((Event)renderevent);
                GlStateManager.enableCull();
                GlStateManager.depthMask(true);
                GlStateManager.enableTexture2D();
                GlStateManager.enableBlend();
                GlStateManager.enableDepth();
            }
        }
        catch (Exception ex) {}
    }
    
    @SubscribeEvent
    public void onRender2DText(final RenderGameOverlayEvent.Text event) {
        if (event.getType().equals((Object)RenderGameOverlayEvent.ElementType.TEXT)) {
            final Render2DEvent renderevent = new Render2DEvent(event.getPartialTicks());
            MinecraftForge.EVENT_BUS.post((Event)renderevent);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    @SubscribeEvent
    public void onKeyboardEvent(final InputEvent.KeyInputEvent event) {
        Wrapper.getGlobalCommand().getChildrenDeep().stream().filter(command -> command instanceof CommandStub).map(command -> command).filter(stub -> stub.getBind() != null).forEach(stub -> {
            if (stub.getBind().isPressed()) {
                stub.onKeyPressed();
            }
            if (stub.getBind().isKeyDown()) {
                stub.onKeyDown();
            }
        });
    }
}
