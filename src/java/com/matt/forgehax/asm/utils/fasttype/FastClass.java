//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.fasttype;

import com.matt.forgehax.asm.utils.name.*;
import com.matt.forgehax.asm.utils.*;
import com.matt.forgehax.asm.utils.environment.*;

public class FastClass extends FastType<Class<?>>
{
    public FastClass(final IName<String> name) {
        super(null, name);
    }
    
    public Class<?> getClassHandle() {
        try {
            if (this.attemptLookup()) {
                return (Class<?>)this.type;
            }
        }
        catch (Throwable t) {
            if (this.printOnce.compareAndSet(false, true)) {
                ASMStackLogger.printStackTrace(t);
            }
        }
        return null;
    }
    
    @Override
    protected Class<?> lookup() throws Exception {
        return Class.forName(this.name.getByStateSafe(RuntimeState.getState()), false, this.getClass().getClassLoader());
    }
}
