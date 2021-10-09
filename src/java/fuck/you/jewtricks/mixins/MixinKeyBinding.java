//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import net.minecraft.client.settings.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import fuck.you.jewtricks.modules.render.*;
import fuck.you.jewtricks.base.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ KeyBinding.class })
public class MixinKeyBinding
{
    @Shadow
    private String keyDescription;
    
    @Inject(method = { "isKeyDown" }, at = { @At("HEAD") }, cancellable = true)
    public void isKeyDown(final CallbackInfoReturnable<Boolean> info) {
        if (Wrapper.isModEnabled((Class)AlwaysRenderPlayerList.class) && this.keyDescription.equals("key.playerlist")) {
            info.setReturnValue((Object)true);
        }
    }
}
