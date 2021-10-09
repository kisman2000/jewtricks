//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.remapping;

import com.matt.forgehax.asm.utils.environment.*;
import javax.annotation.*;

public class NonObfuscatedStateMapper implements IStateMapper
{
    private static NonObfuscatedStateMapper INSTANCE;
    
    public static NonObfuscatedStateMapper getInstance() {
        return (NonObfuscatedStateMapper.INSTANCE == null) ? (NonObfuscatedStateMapper.INSTANCE = new NonObfuscatedStateMapper()) : NonObfuscatedStateMapper.INSTANCE;
    }
    
    @Nullable
    public String getObfClassName(final String className) {
        return null;
    }
    
    @Nullable
    public String getMcpClassName(final String className) {
        return null;
    }
    
    @Nullable
    public String getSrgMethodName(final String parentClassName, final String methodName, final String methodDescriptor) {
        return ObfuscatedStateMapper.getInstance().getSrgMethodName(parentClassName, methodName, methodDescriptor);
    }
    
    @Nullable
    public String getObfMethodName(final String parentClassName, final String methodName, final String methodDescriptor) {
        return null;
    }
    
    @Nullable
    public String getSrgFieldName(final String parentClassName, final String fieldName) {
        return ObfuscatedStateMapper.getInstance().getSrgFieldName(parentClassName, fieldName);
    }
    
    @Nullable
    public String getObfFieldName(final String parentClassName, final String fieldName) {
        return null;
    }
    
    static {
        NonObfuscatedStateMapper.INSTANCE = null;
    }
}
