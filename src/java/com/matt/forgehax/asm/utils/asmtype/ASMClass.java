//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.asmtype;

import com.matt.forgehax.asm.utils.name.*;
import org.objectweb.asm.*;
import com.matt.forgehax.asm.utils.environment.*;
import com.matt.forgehax.asm.utils.asmtype.builders.*;
import java.util.*;

public class ASMClass implements IASMType
{
    private final IName<Type> className;
    
    public ASMClass(final IName<Type> className) {
        this.className = className;
    }
    
    public IName<Type> getAll() {
        return this.className;
    }
    
    @Override
    public String getNameByState(final State state) {
        return this.className.getByStateSafe(state).getClassName();
    }
    
    @Override
    public String getDescriptorByState(final State state) {
        return this.className.getByStateSafe(state).getDescriptor();
    }
    
    public String getInternalName() {
        return this.className.get().getInternalName();
    }
    
    public String getInternalNameByState(final State state) {
        return this.className.getByStateSafe(state).getInternalName();
    }
    
    public String getRuntimeInternalName() {
        return this.getInternalNameByState(RuntimeState.getState());
    }
    
    public ASMMethodBuilder childMethod() {
        return ASMBuilders.newMethodBuilder().setParentClass(this);
    }
    
    public ASMFieldBuilder childField() {
        return ASMBuilders.newFieldBuilder().setParentClass(this);
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof ASMClass && Objects.equals(this.getName(), ((ASMClass)obj).getName());
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("CLASS[states=%d]{", this.className.getStateCount()));
        final Iterator<State> it = Arrays.asList(State.values()).iterator();
        boolean needsSeparator = false;
        while (it.hasNext()) {
            final State next = it.next();
            final Type type = this.className.getByState(next);
            if (type != null) {
                if (needsSeparator) {
                    builder.append(",");
                }
                builder.append(next.name());
                builder.append("=");
                builder.append(type.getInternalName());
                needsSeparator = true;
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
