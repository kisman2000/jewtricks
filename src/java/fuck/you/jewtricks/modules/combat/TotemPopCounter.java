//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.combat;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import fuck.you.jewtricks.events.*;
import net.minecraft.network.play.server.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.network.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import java.util.function.*;
import java.util.*;

@RegisterMod
public class TotemPopCounter extends ToggleMod
{
    private static final Map<String, Integer> list;
    
    public TotemPopCounter() {
        super(Category.COMBAT, "TotemPopCounter", false, "Prints in chat when someone pops a totem");
    }
    
    @SubscribeEvent
    public void onReadPacket(final ReadPacketEvent event) {
        if (event.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus packet = (SPacketEntityStatus)event.getPacket();
            if (packet.getOpCode() == 35) {
                final Entity entity = packet.getEntity(Wrapper.getWorld());
                if (entity == null || !(entity instanceof EntityPlayer)) {
                    return;
                }
                if (!TotemPopCounter.list.containsKey(entity.getName())) {
                    TotemPopCounter.list.put(entity.getName(), 1);
                }
                else {
                    TotemPopCounter.list.put(entity.getName(), TotemPopCounter.list.get(entity.getName()) + 1);
                }
                this.notifyPop((EntityPlayer)entity);
            }
            else if (packet.getOpCode() == 3) {
                final Entity entity = packet.getEntity(Wrapper.getWorld());
                if (entity == null || !(entity instanceof EntityPlayer)) {
                    return;
                }
                this.notifyDeath((EntityPlayer)entity);
            }
        }
    }
    
    @SubscribeEvent
    public void onDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        TotemPopCounter.list.clear();
    }
    
    private void notifyPop(final EntityPlayer player) {
        if (!TotemPopCounter.list.containsKey(player.getName())) {
            return;
        }
        final int popcount = TotemPopCounter.list.get(player.getName());
        String str = ChatFormatting.GOLD + player.getName() + ChatFormatting.WHITE + " popped " + popcount + " totem(s)";
        if (player.getName().equals(Wrapper.getLocalPlayer().getName())) {
            str = str + " (" + this.getTotemsLeft(player) + " left)";
        }
        Wrapper.printChat(str);
    }
    
    private void notifyDeath(final EntityPlayer player) {
        final int count = TotemPopCounter.list.get(player.getName());
        TotemPopCounter.list.remove(player.getName());
        Wrapper.printChat(ChatFormatting.GOLD + player.getName() + ChatFormatting.WHITE + " died after popping " + count + " totem(s)");
    }
    
    private int getTotemsLeft(final EntityPlayer player) {
        int ret = player.inventory.mainInventory.stream().filter(item -> item.getItem().equals(Items.TOTEM_OF_UNDYING)).mapToInt(ItemStack::getCount).sum() - 1;
        if (player.getHeldItemOffhand().getItem().equals(Items.TOTEM_OF_UNDYING)) {
            ++ret;
        }
        return Math.max(0, ret);
    }
    
    static {
        list = new HashMap<String, Integer>();
    }
}
