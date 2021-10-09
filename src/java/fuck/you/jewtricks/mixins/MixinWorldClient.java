//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.world.*;
import fuck.you.jewtricks.modules.render.*;
import fuck.you.jewtricks.base.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.item.*;
import net.minecraft.init.*;

@Mixin({ WorldClient.class })
public class MixinWorldClient
{
    @Redirect(method = { "doVoidFogParticles" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/PlayerControllerMP;getCurrentGameType()Lnet/minecraft/world/GameType;"))
    public GameType getCurrentGameType(final PlayerControllerMP player) {
        if (Wrapper.isModEnabled((Class)BarrierVision.class)) {
            return GameType.CREATIVE;
        }
        return player.getCurrentGameType();
    }
    
    @Redirect(method = { "doVoidFogParticles" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isEmpty()Z"))
    public boolean isEmpty(final ItemStack item) {
        return !Wrapper.isModEnabled((Class)BarrierVision.class) && item.isEmpty();
    }
    
    @Redirect(method = { "doVoidFogParticles" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;"))
    public Item getItem(final ItemStack item) {
        if (Wrapper.isModEnabled((Class)BarrierVision.class)) {
            return Item.getItemFromBlock(Blocks.BARRIER);
        }
        return item.getItem();
    }
}
