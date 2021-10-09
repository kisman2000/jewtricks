//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.mod;

import com.google.common.collect.*;
import com.matt.forgehax.util.command.*;
import java.util.*;
import fuck.you.jewtricks.base.*;
import java.lang.reflect.*;
import java.lang.annotation.*;

public class CommandMod extends ServiceMod
{
    private final Collection<Command> commands;
    
    public CommandMod(final String name, final String desc) {
        super(name, desc);
        this.commands = (Collection<Command>)Lists.newArrayList();
    }
    
    public CommandMod(final String name) {
        super(name, "");
        this.commands = (Collection<Command>)Lists.newArrayList();
    }
    
    @Override
    protected void onLoad() {
        try {
            for (final Method m : this.getClass().getDeclaredMethods()) {
                try {
                    m.setAccessible(true);
                    if (m.isAnnotationPresent(RegisterCommand.class) && Arrays.equals(m.getParameterTypes(), new Class[] { CommandBuilders.class }) && Command.class.isAssignableFrom(m.getReturnType())) {
                        this.commands.add((Command)m.invoke(this, Wrapper.getGlobalCommand().builders()));
                    }
                }
                catch (Throwable t) {
                    Wrapper.handleThrowable(t);
                }
            }
        }
        catch (Throwable t2) {
            Wrapper.handleThrowable(t2);
        }
    }
    
    @Override
    protected void onUnload() {
        this.commands.forEach(Command::leaveParent);
    }
    
    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface RegisterCommand {
    }
}
