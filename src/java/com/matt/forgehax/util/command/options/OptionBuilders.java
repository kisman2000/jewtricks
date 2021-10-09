//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command.options;

import joptsimple.*;
import java.util.*;

public class OptionBuilders
{
    public static void rgba(final OptionParser parser) {
        parser.acceptsAll((List)Arrays.asList("red", "r"), "red").withRequiredArg();
        parser.acceptsAll((List)Arrays.asList("green", "g"), "green").withRequiredArg();
        parser.acceptsAll((List)Arrays.asList("blue", "b"), "blue").withRequiredArg();
        parser.acceptsAll((List)Arrays.asList("alpha", "a"), "alpha").withRequiredArg();
    }
    
    public static void meta(final OptionParser parser) {
        parser.acceptsAll((List)Arrays.asList("meta", "m"), "blocks metadata id").withRequiredArg();
    }
    
    public static void id(final OptionParser parser) {
        parser.acceptsAll((List)Arrays.asList("id", "i"), "searches for block by id instead of name");
    }
    
    public static void regex(final OptionParser parser) {
        parser.acceptsAll((List)Arrays.asList("regex", "e"), "searches for blocks by using the argument as a regex expression");
    }
    
    public static void bounds(final OptionParser parser) {
        parser.accepts("bounds", "Will only draw blocks from within the min-max bounds given").withRequiredArg();
    }
}
