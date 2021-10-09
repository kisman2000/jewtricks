//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.asmtype;

import com.matt.forgehax.asm.utils.environment.*;

public interface IASMType
{
    String getNameByState(final State p0);
    
    String getDescriptorByState(final State p0);
    
    default String getName() {
        return this.getNameByState(State.NORMAL);
    }
    
    default String getDescriptor() {
        return this.getDescriptorByState(State.NORMAL);
    }
    
    default String getSrgName() {
        return this.getNameByState(State.SRG);
    }
    
    default String getSrgDescriptor() {
        return this.getDescriptorByState(State.SRG);
    }
    
    default String getObfuscatedName() {
        return this.getNameByState(State.OBFUSCATED);
    }
    
    default String getObfuscatedDescriptor() {
        return this.getDescriptorByState(State.OBFUSCATED);
    }
    
    default String getRuntimeName() {
        return this.getNameByState(RuntimeState.getState());
    }
    
    default String getRuntimeDescriptor() {
        return this.getDescriptorByState(RuntimeState.getState());
    }
}
