//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import fuck.you.jewtricks.base.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityPlayer.class })
public class MixinEntityPlayer
{
    @Inject(method = { "onCriticalHit" }, at = { @At("HEAD") }, cancellable = true)
    public void onCriticalHit(final Entity otherEntity, final CallbackInfo info) {
        Wrapper.printChat("NORM > onCriticalHit");
    }
}
