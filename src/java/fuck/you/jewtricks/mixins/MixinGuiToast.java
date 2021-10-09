//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.toasts.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiToast.class })
public class MixinGuiToast
{
    @Inject(method = { "add" }, at = { @At("HEAD") }, cancellable = true)
    public void add(final IToast toastIn, final CallbackInfo info) {
        final String from = toastIn.getClass().toString();
        if (from.contains(".tutorial.")) {
            info.cancel();
        }
    }
}
