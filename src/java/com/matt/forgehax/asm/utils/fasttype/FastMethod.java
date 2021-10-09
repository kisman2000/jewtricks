//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.fasttype;

import java.lang.reflect.*;
import com.matt.forgehax.asm.utils.name.*;
import com.matt.forgehax.asm.utils.*;
import java.util.*;
import com.matt.forgehax.asm.utils.environment.*;
import joptsimple.internal.*;

public class FastMethod<V> extends FastType<Method>
{
    private final Class<?>[] parameters;
    
    public FastMethod(final Class<?> insideClass, final IName<String> name, final Class<?>[] parameters) {
        super(insideClass, name);
        this.parameters = Arrays.copyOf(parameters, parameters.length);
    }
    
    public Class<?>[] getParameters() {
        return this.parameters;
    }
    
    public <E> V invoke(final E instance, final V defaultValue, final Object... args) {
        try {
            if (this.attemptLookup()) {
                return (V)((Method)this.type).invoke(instance, args);
            }
        }
        catch (Exception e) {
            if (this.printOnce.compareAndSet(false, true)) {
                ASMStackLogger.printStackTrace((Throwable)e);
            }
        }
        return defaultValue;
    }
    
    public <E> V invoke(final E instance, final Object... args) {
        return this.invoke(instance, (V)null, args);
    }
    
    public V invokeStatic(final Object... args) {
        return this.invoke((Object)null, (V)null, args);
    }
    
    @Override
    protected Method lookup() throws Exception {
        Objects.requireNonNull(this.parameters);
        for (final State state : State.values()) {
            final String n = this.name.getByState(state);
            if (!Strings.isNullOrEmpty(n)) {
                try {
                    final Method m = this.insideClass.getDeclaredMethod(n, this.parameters);
                    m.setAccessible(true);
                    return m;
                }
                catch (Exception ex) {}
            }
        }
        return null;
    }
}
