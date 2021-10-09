//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command.callbacks;

import com.matt.forgehax.util.command.*;

public class CancelableCallbackData extends CallbackData
{
    private boolean canceled;
    
    public CancelableCallbackData(final Command command) {
        super(command);
        this.canceled = false;
    }
    
    public boolean isCanceled() {
        return this.canceled;
    }
    
    public void setCanceled(final boolean canceled) {
        this.canceled = canceled;
    }
    
    public void cancel() {
        this.setCanceled(true);
    }
}
