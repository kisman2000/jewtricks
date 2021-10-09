//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.services;

import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.mod.loader.*;
import fuck.you.jewtricks.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.network.*;

@RegisterMod
public class FPSService extends ServiceMod
{
    public static FPSService INSTANCE;
    private long previoustime;
    private float averagefps;
    private float fps;
    
    public FPSService() {
        super("FPSService");
        this.previoustime = -1L;
        this.averagefps = -1.0f;
        this.fps = 0.0f;
        FPSService.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onRender(final RenderEvent event) {
        final long frametime = System.currentTimeMillis() - this.previoustime;
        if (frametime > 0L && this.previoustime != -1L) {
            this.fps = -1.0f;
            final float weight = 0.1f;
            final float newframe = (float)(1000L / frametime);
            if (this.averagefps < 0.0f) {
                this.averagefps = newframe;
            }
            else {
                this.averagefps *= 1.0f - weight;
                this.averagefps += newframe * weight;
            }
            this.fps = this.averagefps;
        }
        this.previoustime = System.currentTimeMillis();
    }
    
    @SubscribeEvent
    public void onDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.previoustime = -1L;
        this.averagefps = -1.0f;
    }
    
    public int getFPS() {
        return (int)this.fps;
    }
    
    public float getFrametime() {
        return 1.0f / this.fps;
    }
}
