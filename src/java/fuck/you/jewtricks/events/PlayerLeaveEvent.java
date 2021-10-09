//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.events;

import net.minecraftforge.fml.common.eventhandler.*;

public class PlayerLeaveEvent extends Event
{
    private String name;
    
    public PlayerLeaveEvent(final String name) {
        this.name = name;
    }
    
    public String getPlayerName() {
        return this.name;
    }
}
