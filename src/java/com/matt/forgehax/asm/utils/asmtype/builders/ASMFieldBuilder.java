//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.asmtype.builders;

import com.matt.forgehax.asm.*;
import org.objectweb.asm.*;
import com.matt.forgehax.asm.utils.name.*;
import joptsimple.internal.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import java.util.*;

public class ASMFieldBuilder implements ASMCommon
{
    private ASMClass parentClass;
    private String name;
    private String srgName;
    private String obfuscatedName;
    private IName<Type> type;
    private boolean auto;
    
    protected ASMFieldBuilder() {
        this.parentClass = null;
        this.name = null;
        this.srgName = null;
        this.obfuscatedName = null;
        this.type = null;
        this.auto = false;
    }
    
    public ASMFieldBuilder setParentClass(final ASMClass parentClass) {
        this.parentClass = parentClass;
        return this;
    }
    
    public ASMFieldBuilder setParentClass(final Type type) {
        return this.setParentClass(ASMBuilders.newClassBuilder().setClassName(type).build());
    }
    
    public ASMFieldBuilder setParentClass(final String internalClassName) {
        return this.setParentClass(ASMBuilders.newClassBuilder().setClassName(internalClassName).build());
    }
    
    public ASMFieldBuilder setParentClass(final Class<?> clazz) {
        return this.setParentClass(ASMBuilders.newClassBuilder().setClassName((Class)clazz).build());
    }
    
    public ASMFieldBuilder setName(final String name) {
        this.name = name;
        return this;
    }
    
    public ASMFieldBuilder setSrgName(final String srgName) {
        this.srgName = srgName;
        return this;
    }
    
    public ASMFieldBuilder setObfuscatedName(final String obfuscatedName) {
        this.obfuscatedName = obfuscatedName;
        return this;
    }
    
    public ASMFieldBuilder setType(final IName<Type> type) {
        this.type = type;
        return this;
    }
    
    public ASMFieldBuilder setType(final Type type) {
        return this.setType(NameBuilder.createSingleName(type));
    }
    
    public ASMFieldBuilder setType(final String internalClassName) {
        return this.setType(Strings.isNullOrEmpty(internalClassName) ? null : Type.getObjectType(internalClassName));
    }
    
    public ASMFieldBuilder setType(final Class<?> clazz) {
        return this.setType(Type.getType((Class)clazz));
    }
    
    public ASMFieldBuilder setType(final ASMClass clazz) {
        return this.setType(clazz.getAll());
    }
    
    public ASMFieldBuilder autoAssign() {
        this.auto = true;
        return this;
    }
    
    private void attemptAutoAssign() {
        this.setSrgName(ASMFieldBuilder.MAPPER.getSrgFieldName(this.parentClass.getInternalName(), this.name));
        this.setObfuscatedName(ASMFieldBuilder.MAPPER.getObfFieldName(this.parentClass.getInternalName(), this.name));
    }
    
    public ASMField build() {
        Objects.requireNonNull(this.name, "Missing field name");
        Objects.requireNonNull(this.type, "Missing field type");
        if (this.auto) {
            this.attemptAutoAssign();
        }
        return new ASMField(this.parentClass, (IName)NameBuilder.create(this.name, this.srgName, this.obfuscatedName), (IName)this.type);
    }
}
