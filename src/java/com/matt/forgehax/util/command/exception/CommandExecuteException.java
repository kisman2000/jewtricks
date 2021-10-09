//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command.exception;

public class CommandExecuteException extends RuntimeException
{
    public CommandExecuteException() {
    }
    
    public CommandExecuteException(final String message) {
        super(message);
    }
    
    public CommandExecuteException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public CommandExecuteException(final Throwable cause) {
        super(cause);
    }
    
    protected CommandExecuteException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
