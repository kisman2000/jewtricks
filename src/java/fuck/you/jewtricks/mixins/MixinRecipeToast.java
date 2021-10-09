//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.gui.toasts.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RecipeToast.class })
public class MixinRecipeToast
{
    @Inject(method = { "draw" }, at = { @At("RETURN") }, cancellable = true)
    public void draw(final GuiToast toastGui, final long delta, final CallbackInfoReturnable<IToast.Visibility> info) {
        info.setReturnValue((Object)IToast.Visibility.HIDE);
    }
}
