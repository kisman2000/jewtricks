//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.fasttype;

import java.lang.reflect.*;
import com.matt.forgehax.asm.utils.name.*;
import com.matt.forgehax.asm.utils.*;
import com.matt.forgehax.asm.utils.environment.*;
import joptsimple.internal.*;

public class FastField<V> extends FastType<Field>
{
    private final boolean stripFinal;
    
    public FastField(final Class<?> insideClass, final IName<String> name, final boolean stripFinal) {
        super(insideClass, name);
        this.stripFinal = stripFinal;
    }
    
    public <E> V get(final E instance, final V defaultValue) {
        try {
            if (this.attemptLookup()) {
                return (V)((Field)this.type).get(instance);
            }
        }
        catch (Exception e) {
            if (this.printOnce.compareAndSet(false, true)) {
                ASMStackLogger.printStackTrace((Throwable)e);
            }
        }
        return defaultValue;
    }
    
    public <E> V get(final E instance) {
        return this.get(instance, (V)null);
    }
    
    public V getStatic(final V defaultValue) {
        return this.get((Object)null, defaultValue);
    }
    
    public V getStatic() {
        return this.get((Object)null);
    }
    
    public <E> boolean set(final E instance, final V to) {
        try {
            if (this.attemptLookup()) {
                ((Field)this.type).set(instance, to);
                return true;
            }
        }
        catch (Exception e) {
            if (this.printOnce.compareAndSet(false, true)) {
                ASMStackLogger.printStackTrace((Throwable)e);
            }
        }
        return false;
    }
    
    public boolean setStatic(final V to) {
        return this.set((Object)null, to);
    }
    
    @Override
    protected Field lookup() throws Exception {
        for (final State state : State.values()) {
            final String n = this.name.getByState(state);
            if (!Strings.isNullOrEmpty(n)) {
                try {
                    final Field f = this.insideClass.getDeclaredField(n);
                    f.setAccessible(true);
                    if (this.stripFinal) {
                        final Field modifiersField = Field.class.getDeclaredField("modifiers");
                        modifiersField.setAccessible(true);
                        modifiersField.setInt(f, f.getModifiers() & 0xFFFFFFEF);
                    }
                    return f;
                }
                catch (Exception ex) {}
            }
        }
        return null;
    }
}
