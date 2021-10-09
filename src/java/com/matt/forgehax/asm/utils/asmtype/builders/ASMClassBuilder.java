//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.asmtype.builders;

import com.matt.forgehax.asm.*;
import org.objectweb.asm.*;
import joptsimple.internal.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import java.util.*;
import com.matt.forgehax.asm.utils.name.*;

public class ASMClassBuilder implements ASMCommon
{
    private Type name;
    private Type srgName;
    private Type obfuscatedName;
    private boolean auto;
    
    protected ASMClassBuilder() {
        this.name = null;
        this.srgName = null;
        this.obfuscatedName = null;
        this.auto = false;
    }
    
    public ASMClassBuilder setClassName(final Type type) {
        this.name = type;
        return this;
    }
    
    public ASMClassBuilder setClassName(final String internalClassName) {
        return this.setClassName(Strings.isNullOrEmpty(internalClassName) ? null : Type.getObjectType(internalClassName));
    }
    
    public ASMClassBuilder setClassName(final Class<?> clazz) {
        return this.setClassName(Type.getType((Class)clazz));
    }
    
    public ASMClassBuilder setSrgClassName(final String srgInternalClassName) {
        this.srgName = (Strings.isNullOrEmpty(srgInternalClassName) ? null : Type.getObjectType(srgInternalClassName));
        return this;
    }
    
    public ASMClassBuilder setObfuscatedClassName(final String obfuscatedInternalClassName) {
        this.obfuscatedName = (Strings.isNullOrEmpty(obfuscatedInternalClassName) ? null : Type.getObjectType(obfuscatedInternalClassName));
        return this;
    }
    
    public ASMClassBuilder autoAssign() {
        this.auto = true;
        return this;
    }
    
    private void attemptAutoAssign() {
        this.setObfuscatedClassName(ASMClassBuilder.MAPPER.getObfClassName(this.name.getInternalName()));
    }
    
    public ASMClass build() {
        Objects.requireNonNull(this.name, "Class name is missing");
        if (this.auto) {
            this.attemptAutoAssign();
        }
        return new ASMClass((IName)NameBuilder.create(this.name, this.srgName, this.obfuscatedName));
    }
}
