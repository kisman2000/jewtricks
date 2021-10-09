//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.fasttype;

import com.matt.forgehax.asm.utils.name.*;
import java.util.concurrent.atomic.*;

public abstract class FastType<T>
{
    protected final Class<?> insideClass;
    protected final IName<String> name;
    protected T type;
    protected boolean lookupFailed;
    protected AtomicBoolean printOnce;
    
    public FastType(final Class<?> insideClass, final IName<String> name) {
        this.type = null;
        this.lookupFailed = false;
        this.printOnce = new AtomicBoolean(false);
        this.insideClass = insideClass;
        this.name = name;
    }
    
    public Class<?> getInsideClass() {
        return this.insideClass;
    }
    
    public IName<String> getName() {
        return this.name;
    }
    
    public boolean isError() {
        return this.printOnce.get();
    }
    
    protected boolean attemptLookup() throws Exception {
        if (!this.lookupFailed) {
            if (this.type == null) {
                this.type = this.lookup();
                this.lookupFailed = (this.type == null);
            }
            return !this.lookupFailed;
        }
        return true;
    }
    
    protected abstract T lookup() throws Exception;
}
