//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import fuck.you.jewtricks.modules.misc.*;
import fuck.you.jewtricks.base.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiDisconnected.class })
public class MixinGuiDisconnected extends GuiScreen
{
    @Inject(method = { "drawScreen" }, at = { @At("RETURN") }, cancellable = true)
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo info) {
        try {
            final DisconnectedCoords module = Wrapper.getModManager().get((Class)DisconnectedCoords.class).get();
            if (module != null && module.isEnabled()) {
                final String x = module.getX();
                final String y = module.getY();
                final String z = module.getZ();
                final String dimension = module.getDimension();
                final int xwidth = this.fontRenderer.getStringWidth(x);
                final int ywidth = this.fontRenderer.getStringWidth(y);
                final int zwidth = this.fontRenderer.getStringWidth(z);
                final int dimensionwidth = this.fontRenderer.getStringWidth(dimension);
                this.drawString(this.fontRenderer, x, this.width - 2 - xwidth, 5, 16777215);
                this.drawString(this.fontRenderer, y, this.width - 2 - ywidth, 15, 16777215);
                this.drawString(this.fontRenderer, z, this.width - 2 - zwidth, 25, 16777215);
                this.drawString(this.fontRenderer, dimension, this.width - 2 - dimensionwidth, 35, 16777215);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
