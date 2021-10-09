//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.name;

import com.matt.forgehax.asm.utils.environment.*;
import javax.annotation.*;

public interface IName<E>
{
    @Nonnull
    E get();
    
    @Nullable
    E getByState(final State p0);
    
    @Nonnull
    default E getByStateSafe(final State state) {
        final E value = this.getByState(state);
        return (value != null) ? value : this.get();
    }
    
    int getStateCount();
}
