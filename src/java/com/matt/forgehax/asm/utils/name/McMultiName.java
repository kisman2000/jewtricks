//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.name;

import javax.annotation.*;
import com.matt.forgehax.asm.utils.environment.*;

public class McMultiName<E> extends SingleName<E>
{
    private final E srg;
    private final E obf;
    
    public McMultiName(@Nonnull final E type, @Nullable final E srg, @Nullable final E obf) {
        super(type);
        this.srg = srg;
        this.obf = obf;
    }
    
    @Nullable
    public E getSrg() {
        return this.srg;
    }
    
    @Nullable
    public E getObf() {
        return this.obf;
    }
    
    @Nullable
    @Override
    public E getByState(final State state) {
        switch (state) {
            case SRG: {
                return this.srg;
            }
            case OBFUSCATED: {
                return this.obf;
            }
            case NORMAL: {
                return this.get();
            }
            default: {
                return null;
            }
        }
    }
    
    @Override
    public int getStateCount() {
        return super.getStateCount() + ((this.srg != null) ? 1 : 0) + ((this.obf != null) ? 1 : 0);
    }
}
