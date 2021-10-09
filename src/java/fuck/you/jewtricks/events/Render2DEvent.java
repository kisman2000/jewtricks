//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.events;

import net.minecraftforge.fml.common.eventhandler.*;

public class Render2DEvent extends Event
{
    private float ticks;
    
    public Render2DEvent(final float partialTicks) {
        this.ticks = partialTicks;
    }
    
    public float getPartialTicks() {
        return this.ticks;
    }
}
