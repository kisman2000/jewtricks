//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command.options;

import com.matt.forgehax.util.command.*;
import com.matt.forgehax.util.command.exception.*;
import com.google.common.collect.*;
import java.util.*;
import com.matt.forgehax.util.blocks.*;
import com.matt.forgehax.util.*;
import com.matt.forgehax.util.color.*;
import com.matt.forgehax.util.blocks.properties.*;

public class BlockEntryProcessor
{
    public static void buildCollection(final ExecuteData data) {
        data.requiredArguments(1);
        data.requiresEntry("meta");
        final String arg = data.getArgumentAsString(0);
        final int meta = (int)data.get("meta");
        final boolean id = data.options().has("id");
        final boolean regex = data.options().has("regex");
        if (id && regex) {
            throw new CommandExecuteException("Cannot contain both id and regex flag");
        }
        final Collection<BlockEntry> process = (Collection<BlockEntry>)Sets.newHashSet();
        try {
            if (regex) {
                process.addAll(BlockOptionHelper.getAllBlocksMatchingByUnlocalized(arg));
            }
            else {
                process.add(id ? new BlockEntry(SafeConverter.toInteger(arg), meta) : new BlockEntry(arg, meta));
            }
        }
        catch (Throwable t) {
            throw new CommandExecuteException(t.getMessage());
        }
        data.set("entries", (Object)process);
    }
    
    public static void processColor(final ExecuteData data) {
        data.requiresEntry("entries");
        final Collection<BlockEntry> entries = (Collection<BlockEntry>)data.get("entries");
        final boolean isColorPresent = (boolean)data.get("isColorPresent", (Object)false);
        if (isColorPresent) {
            final int colorBuffer = (int)data.get("colorBuffer", (Object)Colors.WHITE.toBuffer());
            entries.forEach(entry -> ((ColorProperty)entry.getWritableProperty((Class)ColorProperty.class)).set(colorBuffer));
        }
    }
    
    public static void processBounds(final ExecuteData data) {
        if (data.hasOption("bounds")) {
            data.requiresEntry("entries");
            final Collection<BlockEntry> entries = (Collection<BlockEntry>)data.get("entries");
            final String value;
            final String[] mm;
            int min;
            int max;
            final Iterable iterable;
            final IllegalArgumentException ex;
            data.getOptions("bounds").forEach(p -> {
                value = String.valueOf(p);
                mm = value.split("-");
                if (mm.length > 1) {
                    min = SafeConverter.toInteger(mm[0]);
                    max = SafeConverter.toInteger(mm[1]);
                    iterable.forEach(entry -> ((BoundProperty)entry.getWritableProperty((Class)BoundProperty.class)).add(min, max));
                }
                else {
                    new IllegalArgumentException(String.format("Invalid argument \"%s\" given for bounds option. Should be formatted like min-max", value));
                    throw ex;
                }
            });
        }
    }
}
