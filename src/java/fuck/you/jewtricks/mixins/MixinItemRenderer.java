//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import fuck.you.jewtricks.modules.render.*;
import fuck.you.jewtricks.base.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ItemRenderer.class })
public class MixinItemRenderer
{
    @Inject(method = { "updateEquippedItem" }, at = { @At("RETURN") }, cancellable = true)
    public void updateEquippedItem(final CallbackInfo info) {
        if (Wrapper.isModEnabled((Class)NoHandCooldownAnimation.class)) {
            NoHandCooldownAnimation.set();
        }
    }
}
