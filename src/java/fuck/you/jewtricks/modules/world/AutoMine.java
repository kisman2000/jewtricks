//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.world;

import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.key.*;
import net.minecraftforge.fml.common.gameevent.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.block.material.*;

@RegisterMod
public class AutoMine extends ToggleMod
{
    public boolean pressed;
    
    public AutoMine() {
        super(Category.WORLD, "AutoMine", false, "Holds LMB for you");
        this.pressed = false;
    }
    
    protected void onEnabled() {
        Bindings.attack.bind();
    }
    
    protected void onDisabled() {
        Bindings.attack.unbind();
        this.setPressed(false);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Wrapper.getMinecraft() == null || Wrapper.getLocalPlayer() == null || Wrapper.getWorld() == null) {
            return;
        }
        switch (event.phase) {
            case START: {
                if (this.getMouseOverBlockTrace() == null) {
                    this.setPressed(false);
                    return;
                }
                this.setPressed(true);
                break;
            }
            case END: {
                this.setPressed(false);
                break;
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onGuiOpened(final GuiOpenEvent event) {
        if (Wrapper.getMinecraft() == null || Wrapper.getLocalPlayer() == null || Wrapper.getWorld() == null || event.getGui() == null) {
            return;
        }
        event.getGui().allowUserInput = true;
    }
    
    public RayTraceResult getMouseOverBlockTrace() {
        return Optional.ofNullable(Wrapper.getMinecraft().objectMouseOver).filter(tr -> tr.getBlockPos() != null).filter(tr -> RayTraceResult.Type.BLOCK.equals((Object)tr.typeOfHit) || !Material.AIR.equals(Wrapper.getWorld().getBlockState(tr.getBlockPos()).getMaterial())).orElse(null);
    }
    
    public void setPressed(final boolean state) {
        Bindings.attack.setPressed(state);
        this.pressed = state;
    }
}
