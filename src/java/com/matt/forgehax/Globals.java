//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax;

import net.minecraft.client.*;
import org.apache.logging.log4j.*;
import net.minecraftforge.fml.client.*;
import com.matt.forgehax.util.command.*;

public interface Globals
{
    public static final Logger LOGGER = LogManager.getLogger("JewTricks");
    public static final Minecraft MC = FMLClientHandler.instance().getClient();
    public static final Command GLOBAL_COMMAND = CommandGlobal.getInstance();
}
