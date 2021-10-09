//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.name;

import java.util.*;
import com.matt.forgehax.asm.utils.environment.*;
import javax.annotation.*;

public class SingleName<E> implements IName<E>
{
    private final E normal;
    
    public SingleName(@Nonnull final E normal) {
        Objects.requireNonNull(normal);
        this.normal = normal;
    }
    
    public E get() {
        return this.normal;
    }
    
    @Nullable
    public E getByState(final State state) {
        switch (state) {
            case NORMAL: {
                return this.get();
            }
            default: {
                return null;
            }
        }
    }
    
    public int getStateCount() {
        return 1;
    }
}
