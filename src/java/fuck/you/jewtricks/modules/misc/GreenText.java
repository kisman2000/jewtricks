//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.misc;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

@RegisterMod
public class GreenText extends ToggleMod
{
    public final Setting<AddMode> add;
    public final Setting<Boolean> bypass;
    
    public GreenText() {
        super(Category.MISC, "GreenText", false, "Chat greentext thing");
        this.add = (Setting<AddMode>)((SettingEnumBuilder)((SettingEnumBuilder)this.getCommandStub().builders().newSettingEnumBuilder().name("add")).description("Force green text in every message [DISABLED/NORMAL/SPACE]")).defaultTo((Enum)AddMode.DISABLED).build();
        this.bypass = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("bypass")).description("Add a similar to right arrow symbol")).defaultTo((Object)false).build();
    }
    
    @SubscribeEvent
    public void onSendPacket(final SendPacketEvent event) {
        if (event.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage packet = (CPacketChatMessage)event.getPacket();
            if (!((AddMode)this.add.get()).equals(AddMode.DISABLED)) {
                final StringBuilder builder = new StringBuilder(packet.message);
                if (((AddMode)this.add.get()).equals(AddMode.SPACE)) {
                    if (this.bypass.getAsBoolean()) {
                        builder.insert(0, ">\u1433 ");
                    }
                    else {
                        builder.insert(0, "> ");
                    }
                }
                else if (this.bypass.getAsBoolean()) {
                    builder.insert(0, ">\u1433");
                }
                else {
                    builder.insert(0, ">");
                }
                packet.message = builder.toString();
            }
            else if (packet.message.charAt(0) == '>' && this.bypass.getAsBoolean()) {
                final StringBuilder builder = new StringBuilder(packet.message);
                builder.insert(1, "\u1433");
                packet.message = builder.toString();
            }
        }
    }
    
    enum AddMode
    {
        DISABLED, 
        NORMAL, 
        SPACE;
    }
}
