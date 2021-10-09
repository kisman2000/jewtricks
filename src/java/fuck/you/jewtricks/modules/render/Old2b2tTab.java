//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.render;

import com.matt.forgehax.util.mod.loader.*;
import java.util.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.events.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.util.text.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

@RegisterMod
public class Old2b2tTab extends ToggleMod
{
    public final Setting<Boolean> force;
    private final TextComponentString header;
    private Random random;
    
    public Old2b2tTab() {
        super(Category.RENDER, "Old2b2tTab", false, "Modifies tab on 2b2t.org");
        this.force = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("force")).description("Force tab modification on other servers")).defaultTo((Object)false).build();
        this.header = new TextComponentString(TextFormatting.DARK_RED + "\n2builders" + TextFormatting.DARK_BLUE + "2tools\n");
        this.random = new Random();
    }
    
    @SubscribeEvent
    public void onPlayerList(final ReadPlayerListEvent event) {
        if (Wrapper.getMinecraft() != null && Wrapper.getMinecraft().getCurrentServerData() != null) {
            if (this.force.getAsBoolean()) {
                event.setHeader((ITextComponent)this.header);
                event.setFooter((ITextComponent)new TextComponentString(event.getFooter().getUnformattedText() + "\n" + ChatFormatting.RESET + ChatFormatting.DARK_GRAY + Integer.toString(this.random.nextInt(100000)) + "\n"));
            }
            else if (Wrapper.getMinecraft().getCurrentServerData().serverIP.equals("2b2t.org")) {
                event.setHeader((ITextComponent)this.header);
                String footer = event.getFooter().getUnformattedText();
                if (footer.contains("tps")) {
                    try {
                        final String currentfooter = event.getFooter().getUnformattedText();
                        final String[] split = currentfooter.split(" ");
                        final String tps = TextFormatting.getTextWithoutFormattingCodes(split[0]).replaceAll("\n", "");
                        final String players = TextFormatting.getTextWithoutFormattingCodes(split[3]);
                        final String ping = TextFormatting.getTextWithoutFormattingCodes(split[6]);
                        footer = ChatFormatting.DARK_GRAY + "\nTPS " + ChatFormatting.WHITE + ChatFormatting.BOLD + tps + ChatFormatting.RESET + ChatFormatting.DARK_GRAY + " ping " + ChatFormatting.WHITE + ChatFormatting.BOLD + ping + ChatFormatting.RESET + ChatFormatting.DARK_GRAY + " players " + ChatFormatting.WHITE + ChatFormatting.BOLD + players;
                        event.setFooter((ITextComponent)new TextComponentString(footer + "\n\n" + ChatFormatting.RESET + ChatFormatting.DARK_GRAY + Integer.toString(this.random.nextInt(100000)) + "\n"));
                        return;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Wrapper.printChat("Exception caught in Old2b2tTab");
                    }
                }
                event.setFooter((ITextComponent)new TextComponentString(footer + "\n" + ChatFormatting.RESET + ChatFormatting.DARK_GRAY + Integer.toString(this.random.nextInt(100000)) + "\n"));
            }
        }
    }
}
