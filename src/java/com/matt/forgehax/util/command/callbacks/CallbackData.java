//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command.callbacks;

import com.matt.forgehax.util.console.*;
import com.matt.forgehax.util.command.*;

public class CallbackData implements ConsoleWriter
{
    private final Command command;
    
    public CallbackData(final Command command) {
        this.command = command;
    }
    
    public <T extends Command> T command() {
        return (T)this.command;
    }
}
