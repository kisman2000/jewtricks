//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.asmtype;

import com.matt.forgehax.asm.utils.name.*;
import org.objectweb.asm.*;
import javax.annotation.*;
import com.matt.forgehax.asm.utils.environment.*;
import java.util.*;

public class ASMMethod extends ASMClassChild
{
    private final IName<String> methodName;
    private final IName<Type>[] parameters;
    private final IName<Type> returnType;
    
    public ASMMethod(@Nullable final ASMClass parentClass, final IName<String> methodName, final IName<Type>[] parameters, final IName<Type> returnType) {
        super(parentClass);
        this.methodName = methodName;
        this.parameters = Arrays.copyOf(parameters, parameters.length);
        this.returnType = returnType;
    }
    
    public String getNameByState(final State state) {
        return this.methodName.getByStateSafe(state);
    }
    
    public String getDescriptorByState(final State state) {
        return Type.getMethodType(this.getReturnTypeByState(state), this.getArgumentTypesByState(state)).getDescriptor();
    }
    
    public Type[] getArgumentTypes() {
        return this.getArgumentTypesByState(State.NORMAL);
    }
    
    public Type[] getArgumentTypesByState(final State state) {
        final Type[] all = new Type[this.parameters.length];
        for (int i = 0; i < this.parameters.length; ++i) {
            all[i] = this.parameters[i].getByStateSafe(state);
        }
        return all;
    }
    
    private boolean isArgumentStatePresent(final State state) {
        for (int i = 0; i < this.parameters.length; ++i) {
            final Type arg = this.parameters[i].getByState(state);
            if (arg != null) {
                return true;
            }
        }
        return false;
    }
    
    public Type[] getRuntimeArgumentTypes() {
        return this.getArgumentTypesByState(RuntimeState.getState());
    }
    
    public Type getReturnType() {
        return this.returnType.get();
    }
    
    public Type getReturnTypeByState(final State state) {
        return this.returnType.getByStateSafe(state);
    }
    
    public Type getRuntimeReturnType() {
        return this.getReturnTypeByState(RuntimeState.getState());
    }
    
    public boolean equals(final Object obj) {
        return obj instanceof ASMMethod && Objects.equals(this.getName(), ((ASMMethod)obj).getName()) && Objects.equals(this.getDescriptor(), ((ASMMethod)obj).getDescriptor());
    }
    
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        int maxStates = Math.max(this.methodName.getStateCount(), this.returnType.getStateCount());
        for (final IName<Type> nm : this.parameters) {
            maxStates = Math.max(maxStates, nm.getStateCount());
        }
        builder.append(String.format("METHOD[states=%d,maxStates=%d]{", this.methodName.getStateCount(), maxStates));
        final Iterator<State> it = Arrays.asList(State.values()).iterator();
        boolean needsSeparator = false;
        while (it.hasNext()) {
            final State next = it.next();
            if (this.methodName.getByState(next) != null || this.returnType.getByState(next) != null || this.isArgumentStatePresent(next)) {
                if (needsSeparator) {
                    builder.append(",");
                }
                builder.append(next.name());
                builder.append("=");
                builder.append(this.getNameByState(next));
                builder.append(this.getDescriptorByState(next));
                needsSeparator = true;
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
