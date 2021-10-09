//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.fasttype;

import com.matt.forgehax.asm.*;
import java.util.*;
import com.matt.forgehax.asm.utils.name.*;
import org.objectweb.asm.*;

public class FastTypeBuilder implements ASMCommon
{
    private Class<?> insideClass;
    private String name;
    private String srgName;
    private String obfuscatedName;
    private Class<?>[] parameters;
    private Class<?> returnType;
    private boolean auto;
    private boolean stripFinal;
    
    public FastTypeBuilder() {
        this.insideClass = null;
        this.name = null;
        this.srgName = null;
        this.obfuscatedName = null;
        this.parameters = null;
        this.returnType = null;
        this.auto = false;
        this.stripFinal = false;
    }
    
    public static FastTypeBuilder create() {
        return new FastTypeBuilder();
    }
    
    public FastTypeBuilder setInsideClass(final Class<?> insideClass) {
        this.insideClass = insideClass;
        return this;
    }
    
    public FastTypeBuilder setInsideClass(final FastClass clazz) {
        return this.setInsideClass(clazz.getClassHandle());
    }
    
    public FastTypeBuilder setName(final String name) {
        this.name = name;
        return this;
    }
    
    public FastTypeBuilder setSrgName(final String name) {
        this.srgName = name;
        return this;
    }
    
    public FastTypeBuilder setObfuscatedName(final String name) {
        this.obfuscatedName = name;
        return this;
    }
    
    public FastTypeBuilder setParameters(final Class<?>... parameters) {
        this.parameters = Arrays.copyOf(parameters, parameters.length);
        return this;
    }
    
    public FastTypeBuilder setReturnType(final Class<?> returnType) {
        this.returnType = returnType;
        return this;
    }
    
    public FastTypeBuilder autoAssign() {
        this.auto = true;
        return this;
    }
    
    public FastTypeBuilder definalize() {
        this.stripFinal = true;
        return this;
    }
    
    public FastClass asClass() {
        Objects.requireNonNull(this.name);
        if (this.auto) {
            this.obfuscatedName = FastTypeBuilder.MAPPER.getObfClassName(this.name);
        }
        return new FastClass((IName)NameBuilder.create(this.name, this.srgName, this.obfuscatedName));
    }
    
    public <V> FastField<V> asField() {
        Objects.requireNonNull(this.insideClass);
        Objects.requireNonNull(this.name);
        if (this.auto) {
            final String parentClassInternalName = Type.getType((Class)this.insideClass).getInternalName();
            this.srgName = FastTypeBuilder.MAPPER.getSrgFieldName(parentClassInternalName, this.name);
            this.obfuscatedName = FastTypeBuilder.MAPPER.getObfFieldName(parentClassInternalName, this.name);
        }
        return (FastField<V>)new FastField((Class)this.insideClass, (IName)NameBuilder.create(this.name, this.srgName, this.obfuscatedName), this.stripFinal);
    }
    
    public <V> FastMethod<V> asMethod() {
        Objects.requireNonNull(this.insideClass);
        Objects.requireNonNull(this.name);
        Objects.requireNonNull(this.parameters);
        if (this.auto) {
            Objects.requireNonNull(this.returnType, "Return type required for auto assigning methods");
            final String parentClassInternalName = Type.getType((Class)this.insideClass).getInternalName();
            final Type[] args = new Type[this.parameters.length];
            for (int i = 0; i < args.length; ++i) {
                args[i] = Type.getType((Class)this.parameters[i]);
            }
            final String descriptor = Type.getMethodType(Type.getType((Class)this.returnType), args).getDescriptor();
            this.srgName = FastTypeBuilder.MAPPER.getSrgMethodName(parentClassInternalName, this.name, descriptor);
            this.obfuscatedName = FastTypeBuilder.MAPPER.getObfMethodName(parentClassInternalName, this.name, descriptor);
        }
        return (FastMethod<V>)new FastMethod((Class)this.insideClass, (IName)NameBuilder.create(this.name, this.srgName, this.obfuscatedName), (Class[])this.parameters);
    }
}
