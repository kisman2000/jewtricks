//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.mixins;

import java.util.*;
import net.minecraft.client.network.*;
import org.spongepowered.asm.mixin.*;
import com.google.common.collect.*;
import net.minecraft.network.play.server.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import fuck.you.jewtricks.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ NetHandlerPlayClient.class })
public class MixinNetHandlerPlayClient
{
    @Shadow
    private final Map<UUID, NetworkPlayerInfo> playerInfoMap;
    private String lastJoin;
    private String lastLeave;
    
    public MixinNetHandlerPlayClient() {
        this.playerInfoMap = (Map<UUID, NetworkPlayerInfo>)Maps.newHashMap();
        this.lastJoin = "";
        this.lastLeave = "";
    }
    
    @Inject(method = { "Lnet/minecraft/client/network/NetHandlerPlayClient;handlePlayerListItem(Lnet/minecraft/network/play/server/SPacketPlayerListItem;)V" }, at = { @At("HEAD") })
    public void preHandlePlayerListItem(final SPacketPlayerListItem listItem, final CallbackInfo callbackInfo) {
        try {
            if (listItem.getEntries().size() == 1) {
                if (listItem.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER) {
                    final SPacketPlayerListItem.AddPlayerData data = listItem.getEntries().get(0);
                    if (!data.getProfile().getId().equals(Wrapper.getLocalPlayer().getGameProfile().getId())) {
                        final String name = data.getProfile().getName();
                        if (!this.lastJoin.equals(name)) {
                            final PlayerJoinEvent event = new PlayerJoinEvent(name);
                            MinecraftForge.EVENT_BUS.post((Event)event);
                            this.lastJoin = name;
                        }
                    }
                }
                else if (listItem.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER) {
                    final SPacketPlayerListItem.AddPlayerData data = listItem.getEntries().get(0);
                    if (!data.getProfile().getId().equals(Wrapper.getLocalPlayer().getGameProfile().getId())) {
                        final String name = this.playerInfoMap.get(data.getProfile().getId()).getGameProfile().getName();
                        if (!this.lastLeave.equals(name)) {
                            final PlayerLeaveEvent event2 = new PlayerLeaveEvent(name);
                            MinecraftForge.EVENT_BUS.post((Event)event2);
                            if (this.lastLeave.equals(this.lastJoin)) {
                                this.lastJoin = "";
                            }
                            this.lastLeave = name;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
}
