//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.render;

import com.matt.forgehax.util.mod.loader.*;
import java.lang.reflect.*;
import com.matt.forgehax.util.mod.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.relauncher.*;

@RegisterMod
public class NoHandCooldownAnimation extends ToggleMod
{
    protected static final Field equippedProgressMainHand;
    protected static final Field prevEquippedProgressMainHand;
    protected static final Field itemStackMainHand;
    
    public NoHandCooldownAnimation() {
        super(Category.RENDER, "NoHandCooldownAnimation", false, "Removes hand cooldown animation");
    }
    
    public static void set() {
        if (Wrapper.getMinecraft() == null || Wrapper.getMinecraft().entityRenderer == null || Wrapper.getMinecraft().entityRenderer.itemRenderer == null) {
            return;
        }
        try {
            final ItemRenderer renderer = Wrapper.getMinecraft().entityRenderer.itemRenderer;
            NoHandCooldownAnimation.equippedProgressMainHand.setFloat(renderer, 1.0f);
            NoHandCooldownAnimation.prevEquippedProgressMainHand.setFloat(renderer, 1.0f);
            NoHandCooldownAnimation.itemStackMainHand.set(renderer, Wrapper.getLocalPlayer().getHeldItemMainhand());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        equippedProgressMainHand = ReflectionHelper.findField((Class)ItemRenderer.class, new String[] { "equippedProgressMainHand", "equippedProgressMainHand", "f" });
        prevEquippedProgressMainHand = ReflectionHelper.findField((Class)ItemRenderer.class, new String[] { "prevEquippedProgressMainHand", "prevEquippedProgressMainHand", "g" });
        itemStackMainHand = ReflectionHelper.findField((Class)ItemRenderer.class, new String[] { "itemStackMainHand", "itemStackMainHand", "d" });
    }
}
