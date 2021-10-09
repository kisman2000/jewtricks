//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.asmtype.builders;

public class ASMBuilders
{
    public static ASMClassBuilder newClassBuilder() {
        return new ASMClassBuilder();
    }
    
    public static ASMMethodBuilder newMethodBuilder() {
        return new ASMMethodBuilder();
    }
    
    public static ASMFieldBuilder newFieldBuilder() {
        return new ASMFieldBuilder();
    }
    
    public static ParameterBuilder newParameterBuilder() {
        return new ParameterBuilder();
    }
}
