//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.misc;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import fuck.you.jewtricks.events.*;
import net.minecraft.network.play.server.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.fml.common.eventhandler.*;

@RegisterMod
public class AutoCaptcha extends ToggleMod
{
    public AutoCaptcha() {
        super(Category.MISC, "AutoCaptcha", false, "Solves captcha for you on 2b2t.org.ru");
    }
    
    @SubscribeEvent
    public void onReadPacket(final ReadPacketEvent event) {
        if (event.getPacket() instanceof SPacketChat) {
            final SPacketChat packet = (SPacketChat)event.getPacket();
            if (packet.getChatComponent() == null) {
                return;
            }
            final String text = packet.getChatComponent().getUnformattedText();
            if (text.contains("\u0412\u0432\u0435\u0434\u0438 \u044d\u0442\u043e\u0442 \u0442\u0435\u043a\u0441\u0442 \u0434\u043b\u044f ")) {
                final String captcha = text.substring(text.indexOf(": ") + 2);
                Wrapper.getLocalPlayer().sendChatMessage(captcha);
            }
        }
    }
}
