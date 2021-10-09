//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import fuck.you.jewtricks.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ SPacketPlayerListHeaderFooter.class })
public class MixinPlayerList
{
    @Shadow
    private ITextComponent header;
    @Shadow
    private ITextComponent footer;
    
    @Inject(method = { "readPacketData" }, at = { @At("RETURN") }, cancellable = true)
    public void readPacketData(final PacketBuffer buf, final CallbackInfo info) {
        final ReadPlayerListEvent event = new ReadPlayerListEvent(this.header, this.footer);
        MinecraftForge.EVENT_BUS.post((Event)event);
        this.header = event.getHeader();
        this.footer = event.getFooter();
    }
}
