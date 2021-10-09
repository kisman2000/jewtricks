//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.misc;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.util.text.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;
import fuck.you.jewtricks.events.*;

@RegisterMod
public class ConnectionMessages extends ToggleMod
{
    public final Setting<Boolean> joins;
    public final Setting<Boolean> leave;
    public final Setting<Boolean> comendantmode;
    
    public ConnectionMessages() {
        super(Category.MISC, "ConnectionMessages", false, "Displays connection messages in chat");
        this.joins = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("join")).description("Display join messages")).defaultTo((Object)true).build();
        this.leave = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("leave")).description("Display leave messages")).defaultTo((Object)true).build();
        this.comendantmode = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("comendantmode")).description("")).defaultTo((Object)false).build();
    }
    
    @SubscribeEvent
    public void onPlayerJoin(final PlayerJoinEvent event) {
        if (this.joins.getAsBoolean()) {
            try {
                if ((boolean)this.comendantmode.get() && event.getPlayerName().equalsIgnoreCase("comendantmc")) {
                    for (int i = 0; i < 10; ++i) {
                        Wrapper.getMinecraft().ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(TextFormatting.YELLOW + event.getPlayerName() + TextFormatting.GRAY + " joined"));
                    }
                    Wrapper.getLocalPlayer().playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                }
                Wrapper.getMinecraft().ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(TextFormatting.GRAY + event.getPlayerName() + " joined"));
            }
            catch (Exception ex) {}
        }
    }
    
    @SubscribeEvent
    public void onPlayerLeave(final PlayerLeaveEvent event) {
        if (this.leave.getAsBoolean()) {
            try {
                Wrapper.getMinecraft().ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(TextFormatting.GRAY + event.getPlayerName() + " left"));
            }
            catch (Exception ex) {}
        }
    }
}
