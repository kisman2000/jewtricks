//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.render;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import net.minecraftforge.event.entity.living.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.fml.common.eventhandler.*;

@RegisterMod
public class Killstreaks extends ToggleMod
{
    public Killstreaks() {
        super(Category.RENDER, "Killstreaks", false, "Exactly what the mod's name says");
    }
    
    @SubscribeEvent
    public void onLivingDeath(final LivingDeathEvent event) {
        try {
            if (event.getSource() == null || event.getEntity() == null || event.getEntityLiving() == null) {
                return;
            }
            if (!event.getEntity().world.isRemote) {
                return;
            }
            Wrapper.printChat(event.getEntity().getClass().getName());
        }
        catch (Exception ex) {}
    }
}
