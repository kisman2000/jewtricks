//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command.callbacks;

import com.matt.forgehax.util.command.*;

public class OnChangeCallback<E> extends CancelableCallbackData
{
    private final E from;
    private final E to;
    
    public OnChangeCallback(final Command command, final E from, final E to) {
        super(command);
        this.from = from;
        this.to = to;
    }
    
    public E getFrom() {
        return this.from;
    }
    
    public E getTo() {
        return this.to;
    }
}
