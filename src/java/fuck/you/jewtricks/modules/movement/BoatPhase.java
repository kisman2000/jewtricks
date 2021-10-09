//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.movement;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.fml.common.eventhandler.*;
import fuck.you.jewtricks.events.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.math.*;

@RegisterMod
public class BoatPhase extends ToggleMod
{
    public BoatPhase() {
        super(Category.MOVEMENT, "BoatPhase", false, "Boat phase (use sand to phase)");
    }
    
    public void onDisable() {
        if (Wrapper.getMinecraft() == null || Wrapper.getLocalPlayer() == null) {
            return;
        }
        Wrapper.getLocalPlayer().noClip = false;
    }
    
    @SubscribeEvent
    public void onUpdatePost(final PlayerPostUpdateEvent event) {
        if (!Wrapper.getLocalPlayer().isRiding()) {
            Wrapper.getLocalPlayer().noClip = false;
            return;
        }
        Wrapper.getLocalPlayer().noClip = true;
        Wrapper.getLocalPlayer().motionY = 0.0;
        Wrapper.getLocalPlayer().onGround = false;
        Wrapper.getLocalPlayer().capabilities.isFlying = false;
    }
    
    @SubscribeEvent
    public void onMove(final PlayerMoveEvent event) {
        if (Wrapper.getLocalPlayer().ridingEntity != null && Wrapper.getLocalPlayer().ridingEntity instanceof EntityBoat) {
            final AxisAlignedBB bb = Wrapper.getLocalPlayer().ridingEntity.getEntityBoundingBox();
            Wrapper.getLocalPlayer().ridingEntity.setEntityBoundingBox(bb.offset(event.getX() * 10.0, event.getY(), event.getZ() * 10.0));
        }
    }
}
