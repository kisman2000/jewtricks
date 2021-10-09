//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.misc;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import fuck.you.jewtricks.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

@RegisterMod
public class DisconnectedCoords extends ToggleMod
{
    private double x;
    private double y;
    private double z;
    private int dimension;
    
    public DisconnectedCoords() {
        super(Category.MISC, "DisconnectedCoords", false, "Prints coords on Disconnected screen");
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.dimension = -2;
    }
    
    @SubscribeEvent
    public void onReadPacket(final ReadPacketEvent event) {
        if (event.getPacket() instanceof SPacketPlayerPosLook) {
            final SPacketPlayerPosLook packet = (SPacketPlayerPosLook)event.getPacket();
            this.x = packet.x;
            this.y = packet.y;
            this.z = packet.z;
        }
        else if (event.getPacket() instanceof SPacketJoinGame) {
            final SPacketJoinGame packet2 = (SPacketJoinGame)event.getPacket();
            this.dimension = packet2.getDimension();
        }
    }
    
    public String getX() {
        return String.format("%d", (int)Math.round(this.x));
    }
    
    public String getY() {
        return String.format("%d", (int)Math.round(this.y));
    }
    
    public String getZ() {
        return String.format("%d", (int)Math.round(this.z));
    }
    
    public String getDimension() {
        switch (this.dimension) {
            case -1: {
                return "NETHER";
            }
            case 0: {
                return "OVERWORLD";
            }
            case 1: {
                return "THE_END";
            }
            default: {
                return "NONE";
            }
        }
    }
}
