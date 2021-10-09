//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.misc;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import fuck.you.jewtricks.events.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;

@RegisterMod
public class VisualRange extends ToggleMod
{
    private ArrayList<String> names;
    private ArrayList<String> newnames;
    
    public VisualRange() {
        super(Category.MISC, "VisualRange", false, "Print players who enter/leave your range");
        this.names = new ArrayList<String>();
        this.newnames = new ArrayList<String>();
    }
    
    @SubscribeEvent
    public void onUpdate(final PlayerUpdateEvent event) {
        this.newnames.clear();
        try {
            for (final Entity entity : Wrapper.getWorld().loadedEntityList) {
                if (entity instanceof EntityPlayer && !entity.getName().equalsIgnoreCase(Wrapper.getLocalPlayer().getName())) {
                    this.newnames.add(entity.getName());
                }
            }
            if (!this.names.equals(this.newnames)) {
                for (final String name : this.newnames) {
                    if (!this.names.contains(name)) {
                        Wrapper.printChat(name + " entered visual range");
                    }
                }
                for (final String name : this.names) {
                    if (!this.newnames.contains(name)) {
                        Wrapper.printChat(name + " left visual range");
                    }
                }
                this.names.clear();
                for (final String name : this.newnames) {
                    this.names.add(name);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @SubscribeEvent
    public void onLogIn(final PlayerEvent.PlayerLoggedInEvent event) {
        this.names.clear();
        this.newnames.clear();
    }
}
