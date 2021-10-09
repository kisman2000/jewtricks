//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.asmtype.builders;

import com.matt.forgehax.asm.*;
import org.objectweb.asm.*;
import com.matt.forgehax.asm.utils.name.*;
import joptsimple.internal.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import java.util.*;

public class ASMMethodBuilder implements ASMCommon
{
    private static final IName<Type>[] NO_PARAMETERS;
    private ASMClass parentClass;
    private String name;
    private String srgName;
    private String obfuscatedName;
    private IName<Type>[] parameterTypes;
    private IName<Type> returnType;
    private boolean auto;
    
    protected ASMMethodBuilder() {
        this.parentClass = null;
        this.name = null;
        this.srgName = null;
        this.obfuscatedName = null;
        this.parameterTypes = null;
        this.returnType = null;
        this.auto = false;
    }
    
    public ASMMethodBuilder setParentClass(final ASMClass parentClass) {
        this.parentClass = parentClass;
        return this;
    }
    
    public ASMMethodBuilder setParentClass(final Type type) {
        return this.setParentClass(ASMBuilders.newClassBuilder().setClassName(type).build());
    }
    
    public ASMMethodBuilder setParentClass(final String internalClassName) {
        return this.setParentClass(ASMBuilders.newClassBuilder().setClassName(internalClassName).build());
    }
    
    public ASMMethodBuilder setParentClass(final Class<?> clazz) {
        return this.setParentClass(ASMBuilders.newClassBuilder().setClassName((Class)clazz).build());
    }
    
    public ASMMethodBuilder setName(final String name) {
        this.name = name;
        return this;
    }
    
    public ASMMethodBuilder setSrgName(final String srgName) {
        this.srgName = srgName;
        return this;
    }
    
    public ASMMethodBuilder setObfuscatedName(final String obfuscatedName) {
        this.obfuscatedName = obfuscatedName;
        return this;
    }
    
    public ASMMethodBuilder setParameterTypes(final IName<Type>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }
    
    public ASMMethodBuilder emptyParameters() {
        return this.setParameterTypes(ASMMethodBuilder.NO_PARAMETERS);
    }
    
    public ParameterBuilder beginParameters() {
        return new ParameterBuilder(this);
    }
    
    public ASMMethodBuilder setReturnType(final IName<Type> returnType) {
        this.returnType = returnType;
        return this;
    }
    
    public ASMMethodBuilder setReturnType(final Type returnType) {
        return this.setReturnType(NameBuilder.createSingleName(returnType));
    }
    
    public ASMMethodBuilder setReturnType(final String internalClassName) {
        return this.setReturnType(Strings.isNullOrEmpty(internalClassName) ? null : Type.getObjectType(internalClassName));
    }
    
    public ASMMethodBuilder setReturnType(final Class<?> clazz) {
        return this.setReturnType(Type.getType((Class)clazz));
    }
    
    public ASMMethodBuilder setReturnType(final ASMClass clazz) {
        return this.setReturnType(clazz.getAll());
    }
    
    public ASMMethodBuilder autoAssign() {
        this.auto = true;
        return this;
    }
    
    private void attemptAutoAssign() {
        final Type[] normalParameters = new Type[this.parameterTypes.length];
        for (int i = 0; i < this.parameterTypes.length; ++i) {
            normalParameters[i] = this.parameterTypes[i].get();
        }
        final String descriptor = Type.getMethodType((Type)this.returnType.get(), normalParameters).getDescriptor();
        this.setSrgName(ASMMethodBuilder.MAPPER.getSrgMethodName(this.parentClass.getInternalName(), this.name, descriptor));
        this.setObfuscatedName(ASMMethodBuilder.MAPPER.getObfMethodName(this.parentClass.getInternalName(), this.name, descriptor));
    }
    
    public ASMMethod build() {
        Objects.requireNonNull(this.name, "Missing method name");
        Objects.requireNonNull(this.parameterTypes, "Missing method parameters (use emptyParameters() if none are present)");
        Objects.requireNonNull(this.returnType, "Missing method return type");
        if (this.auto) {
            this.attemptAutoAssign();
        }
        return new ASMMethod(this.parentClass, (IName)NameBuilder.create(this.name, this.srgName, this.obfuscatedName), (IName[])this.parameterTypes, (IName)this.returnType);
    }
    
    static {
        NO_PARAMETERS = new ParameterBuilder().asArray();
    }
}
