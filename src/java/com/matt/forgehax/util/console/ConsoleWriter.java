//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.console;

public interface ConsoleWriter
{
    default void write(final String msg) {
        ConsoleIO.write(msg);
    }
    
    default void incrementIndent() {
        ConsoleIO.incrementIndent();
    }
    
    default void decrementIndent() {
        ConsoleIO.decrementIndent();
    }
}
