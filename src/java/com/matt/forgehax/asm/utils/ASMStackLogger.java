//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils;

import com.google.common.base.*;
import org.apache.logging.log4j.*;

public class ASMStackLogger
{
    private static final Logger STACK_LOGGER;
    
    public static void printStackTrace(final Throwable e) {
        ASMStackLogger.STACK_LOGGER.error(Throwables.getStackTraceAsString(e));
    }
    
    static {
        STACK_LOGGER = LogManager.getLogger("ForgeHaxAsmStackTrace");
    }
}
