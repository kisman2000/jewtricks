//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import java.util.*;
import javax.annotation.*;
import com.matt.forgehax.util.command.exception.*;

public class CommandGlobal extends CommandStub
{
    private static final CommandGlobal INSTANCE;
    
    public static CommandGlobal getInstance() {
        return CommandGlobal.INSTANCE;
    }
    
    private CommandGlobal() {
        super(((StubBuilder)((StubBuilder)CommandBuilders.getInstance().newStubBuilder().name("")).helpOption(false)).getData());
    }
    
    public boolean isGlobal() {
        return true;
    }
    
    public String getName() {
        return "";
    }
    
    public String getAbsoluteName() {
        return "";
    }
    
    public void run(@Nonnull final String[] args) throws CommandExecuteException, NullPointerException {
        if (this.processChildren(args)) {
            return;
        }
        if (args.length > 0) {
            throw new CommandExecuteException(String.format("Unknown command \"%s\"", args[0]));
        }
        throw new CommandExecuteException("Missing argument(s)");
    }
    
    static {
        INSTANCE = new CommandGlobal();
    }
}
