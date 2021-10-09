//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;
import fuck.you.jewtricks.modules.world.*;
import fuck.you.jewtricks.base.*;

@Mixin({ Minecraft.class })
public class MixinMinecraft
{
    @Shadow
    public int leftClickCounter;
    private int backup_leftClickCounter;
    
    @Inject(method = { "setIngameFocus" }, at = { @At("HEAD") })
    public void setIngameFocusHEAD(final CallbackInfo info) {
        this.backup_leftClickCounter = this.leftClickCounter;
    }
    
    @Inject(method = { "setIngameFocus" }, at = { @At("RETURN") })
    public void setIngameFocusRETURN(final CallbackInfo info) {
        if (Wrapper.isModEnabled((Class)AutoMine.class)) {
            this.leftClickCounter = this.backup_leftClickCounter;
        }
    }
    
    @Inject(method = { "sendClickBlockToController" }, at = { @At("HEAD") })
    public void sendClickBlockToController(boolean leftClick, final CallbackInfo info) {
        final AutoMine mod = Wrapper.getModManager().get((Class)AutoMine.class).orElse(null);
        if (mod != null && mod.isEnabled() && mod.pressed) {
            leftClick = true;
        }
    }
}
