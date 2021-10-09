//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.asmtype.builders;

import org.objectweb.asm.*;
import com.google.common.collect.*;
import com.matt.forgehax.asm.utils.name.*;
import joptsimple.internal.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import java.util.*;

public class ParameterBuilder
{
    private ASMMethodBuilder callback;
    private List<IName<Type>> parameters;
    private boolean overrideObfuscation;
    
    protected ParameterBuilder() {
        this.callback = null;
        this.parameters = (List<IName<Type>>)Lists.newArrayList();
        this.overrideObfuscation = false;
    }
    
    protected ParameterBuilder(final ASMMethodBuilder callback) {
        this.callback = null;
        this.parameters = (List<IName<Type>>)Lists.newArrayList();
        this.overrideObfuscation = false;
        this.callback = callback;
    }
    
    public ParameterBuilder unobfuscated() {
        this.overrideObfuscation = true;
        return this;
    }
    
    public ParameterBuilder add(final Type parameter) {
        this.parameters.add(NameBuilder.createSingleName(parameter));
        return this;
    }
    
    public ParameterBuilder add(final String internalClassName) {
        return this.add(Strings.isNullOrEmpty(internalClassName) ? null : Type.getObjectType(internalClassName));
    }
    
    public ParameterBuilder add(final Class<?> clazz) {
        return this.add(Type.getType((Class)clazz));
    }
    
    public ParameterBuilder add(final ASMClass parameter) {
        if (this.overrideObfuscation) {
            this.parameters.add(NameBuilder.createSingleName(parameter.getAll().get()));
        }
        else {
            this.parameters.add(parameter.getAll());
        }
        return this;
    }
    
    public IName<Type>[] asArray() {
        return this.parameters.toArray(new IName[0]);
    }
    
    public Collection<IName<Type>> asCollection() {
        return Collections.unmodifiableCollection((Collection<? extends IName<Type>>)this.parameters);
    }
    
    public ASMMethodBuilder finish() {
        Objects.requireNonNull(this.callback, "Attempted to use finishParameters() without a callback");
        return this.callback.setParameterTypes((IName[])this.asArray());
    }
}
