//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.asmtype;

import com.matt.forgehax.asm.utils.name.*;
import org.objectweb.asm.*;
import javax.annotation.*;
import com.matt.forgehax.asm.utils.environment.*;
import java.util.*;

public class ASMField extends ASMClassChild
{
    private final IName<String> fieldName;
    private final IName<Type> type;
    
    public ASMField(@Nullable final ASMClass parentClass, final IName<String> fieldName, final IName<Type> type) {
        super(parentClass);
        this.fieldName = fieldName;
        this.type = type;
    }
    
    public String getNameByState(final State state) {
        return this.fieldName.getByStateSafe(state);
    }
    
    public String getDescriptorByState(final State state) {
        return this.type.getByStateSafe(state).getDescriptor();
    }
    
    public boolean equals(final Object obj) {
        return obj instanceof ASMField && Objects.equals(this.getName(), ((ASMField)obj).getName()) && Objects.equals(this.getDescriptor(), ((ASMField)obj).getDescriptor());
    }
    
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("FIELD[states=%d,maxStates=%d]{", this.fieldName.getStateCount(), Math.max(this.fieldName.getStateCount(), this.type.getStateCount())));
        final Iterator<State> it = Arrays.asList(State.values()).iterator();
        boolean needsSeparator = false;
        while (it.hasNext()) {
            final State next = it.next();
            if (this.fieldName.getByState(next) != null || this.type.getByState(next) != null) {
                if (needsSeparator) {
                    builder.append(",");
                }
                builder.append(next.name());
                builder.append("=");
                builder.append(this.getNameByState(next));
                builder.append(":");
                builder.append(this.getDescriptorByState(next));
                needsSeparator = true;
            }
        }
        builder.append("}");
        return builder.toString();
    }
}
