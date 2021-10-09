//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.render;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.events.*;
import net.minecraft.network.play.server.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import fuck.you.jewtricks.*;

@RegisterMod
public class PMSound extends ToggleMod
{
    public final Setting<SoundType> sound;
    
    public PMSound() {
        super(Category.RENDER, "PMSound", false, "Plays a sound whenever you get a private message");
        this.sound = (Setting<SoundType>)((SettingEnumBuilder)((SettingEnumBuilder)this.getCommandStub().builders().newSettingEnumBuilder().name("sound")).description("[OLDSTEAM]")).defaultTo((Enum)SoundType.OLDSTEAM).build();
    }
    
    @SubscribeEvent
    public void onReadPacket(final ReadPacketEvent event) {
        if (event.getPacket() instanceof SPacketChat) {
            final SPacketChat packet = (SPacketChat)event.getPacket();
            if (packet.getChatComponent() == null) {
                return;
            }
            final String text = packet.getChatComponent().getUnformattedText();
            if (text.contains(" \u0448\u0435\u043f\u0447\u0435\u0442: ") || text.contains(" whispers: ")) {
                final SoundEvent soundevent = this.getSound();
                if (soundevent != null) {
                    Wrapper.getLocalPlayer().playSound(soundevent, 1.0f, 1.0f);
                }
            }
        }
    }
    
    public SoundEvent getSound() {
        switch ((SoundType)this.sound.get()) {
            case OLDSTEAM: {
                return Sounds.INSTANCE.PMSOUND_OLDSTEAM;
            }
            default: {
                return null;
            }
        }
    }
    
    enum SoundType
    {
        OLDSTEAM;
    }
}
