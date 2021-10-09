//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.misc;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.fml.common.eventhandler.*;
import fuck.you.jewtricks.events.*;
import net.minecraftforge.fml.common.network.*;

@RegisterMod
public class TeamRusherLag extends ToggleMod
{
    public Setting<Integer> time;
    private Setting<String> text;
    private boolean cansend;
    private long lastpacket;
    
    public TeamRusherLag() {
        super(Category.MISC, "TeamRusherLag", false, "Sends a message in chat when server is lagging");
        this.time = (Setting<Integer>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("time")).description("Lag time (in ms)")).defaultTo((Object)3000).min((Object)1000).max((Object)10000).build();
        this.text = (Setting<String>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("text")).description("Text to send")).defaultTo((Object)"> #TeamRusher Lag: ON").build();
        this.cansend = false;
        this.lastpacket = 0L;
    }
    
    @SubscribeEvent
    public void onRender(final RenderEvent event) {
        if (this.lastpacket != 0L && System.currentTimeMillis() > this.lastpacket + (int)this.time.get()) {
            if (this.cansend) {
                this.cansend = false;
                Wrapper.getLocalPlayer().sendChatMessage((String)this.text.get());
            }
        }
        else {
            this.cansend = true;
        }
    }
    
    @SubscribeEvent
    public void onReadPacket(final ReadPacketEvent event) {
        this.lastpacket = System.currentTimeMillis();
    }
    
    public void onEnabled() {
        this.lastpacket = 0L;
    }
    
    @SubscribeEvent
    public void onDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.lastpacket = 0L;
    }
}
