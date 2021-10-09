//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.misc;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.events.*;
import net.minecraft.network.play.server.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.fml.common.eventhandler.*;

@RegisterMod
public class AutoLogin extends ToggleMod
{
    public Setting<String> password;
    
    public AutoLogin() {
        super(Category.MISC, "AutoLogin", false, "Automatically types /login <password> for you");
        this.password = (Setting<String>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("password")).description("")).defaultTo((Object)"").build();
    }
    
    @SubscribeEvent
    public void onReadPacket(final ReadPacketEvent event) {
        if (event.getPacket() instanceof SPacketChat) {
            final SPacketChat packet = (SPacketChat)event.getPacket();
            if (packet.getChatComponent() == null) {
                return;
            }
            final String text = packet.getChatComponent().getUnformattedText();
            if (text.contains("/login") && !((String)this.password.get()).equals("")) {
                Wrapper.getLocalPlayer().sendChatMessage("/login " + (String)this.password.get());
            }
        }
    }
}
