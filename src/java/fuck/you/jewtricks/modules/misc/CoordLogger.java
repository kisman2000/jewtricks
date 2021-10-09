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
public class CoordLogger extends ToggleMod
{
    private Setting<Boolean> slime;
    
    public CoordLogger() {
        super(Category.MISC, "CoordLogger", false, "Logs coordinates of entities/players");
        this.slime = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("slime")).description("Log slimes")).defaultTo((Object)false).build();
    }
    
    @SubscribeEvent
    public void onReadPacket(final ReadPacketEvent event) {
        if (event.getPacket() instanceof SPacketSpawnMob) {
            final SPacketSpawnMob packet = (SPacketSpawnMob)event.getPacket();
            if ((boolean)this.slime.get() && packet.getEntityType() == 55) {
                Wrapper.printChat("Slime spawned in X: " + (int)packet.getX() + " | Y: " + (int)packet.getY() + " | Z: " + (int)packet.getZ());
            }
        }
    }
}
