//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import fuck.you.jewtricks.modules.render.*;
import fuck.you.jewtricks.base.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ World.class })
public class MixinWorld
{
    @Inject(method = { "checkLightFor" }, at = { @At("HEAD") }, cancellable = true)
    public void checkLightFor(final EnumSkyBlock lightType, final BlockPos pos, final CallbackInfoReturnable<Boolean> info) {
        if (!Wrapper.isModEnabled((Class)NoSkylightUpdates.class)) {
            return;
        }
        if (lightType.equals((Object)EnumSkyBlock.SKY)) {
            info.setReturnValue((Object)false);
        }
    }
}
