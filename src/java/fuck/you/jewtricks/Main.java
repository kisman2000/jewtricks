//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.relauncher.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.fml.common.event.*;
import com.matt.forgehax.util.mod.*;
import net.minecraftforge.common.*;
import com.matt.forgehax.gui.*;
import java.io.*;

@Mod(modid = "jewtricks", name = "JewTricks", version = "1.0")
@SideOnly(Side.CLIENT)
public class Main
{
    public static final String MODID = "jewtricks";
    public static final String NAME = "JewTricks";
    public static final String VERSION = "1.0";
    public static final Main INSTANCE;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        Wrapper.getModManager().loadAll();
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) throws IOException {
        Wrapper.getLog().info("Loading JewTricks v1.0 by mrnv/ayywareseller");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Wrapper.getModManager().forEach(BaseMod::unload)));
        Wrapper.getModManager().forEach(BaseMod::load);
        MinecraftForge.EVENT_BUS.register((Object)new ForgeEventProcessor());
        MinecraftForge.EVENT_BUS.register((Object)ClickGui.getInstance());
        MinecraftForge.EVENT_BUS.register((Object)ConsoleGui.getInstance());
        Sounds.INSTANCE.init();
    }
    
    static {
        Wrapper.getModManager().searchPackage("fuck.you.jewtricks.modules.*");
        INSTANCE = new Main();
    }
}
