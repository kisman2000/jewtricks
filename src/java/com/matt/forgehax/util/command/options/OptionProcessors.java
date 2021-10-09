//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command.options;

import com.matt.forgehax.util.command.*;
import com.matt.forgehax.util.*;
import net.minecraft.util.math.*;
import com.matt.forgehax.util.color.*;

public class OptionProcessors
{
    public static void rgba(final ExecuteData data) {
        final int r = SafeConverter.toInteger(data.getOption("red"), 255);
        final int g = SafeConverter.toInteger(data.getOption("green"), 255);
        final int b = SafeConverter.toInteger(data.getOption("blue"), 255);
        final int a = SafeConverter.toInteger(data.getOption("alpha"), 255);
        data.set("colorBuffer", (Object)Color.of(MathHelper.clamp(r, 0, 255), MathHelper.clamp(g, 0, 255), MathHelper.clamp(b, 0, 255), MathHelper.clamp(a, 0, 255)).toBuffer());
        data.set("isColorPresent", (Object)(data.hasOption("red") || data.hasOption("green") || data.hasOption("blue") || data.hasOption("alpha")));
    }
    
    public static void meta(final ExecuteData data) {
        data.set("meta", (Object)SafeConverter.toInteger(data.getOption("meta"), 0));
    }
}
