//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.environment;

import javax.annotation.*;

public interface IStateMapper
{
    @Nullable
    String getObfClassName(final String p0);
    
    @Nullable
    String getMcpClassName(final String p0);
    
    @Nullable
    String getSrgMethodName(final String p0, final String p1, final String p2);
    
    @Nullable
    String getObfMethodName(final String p0, final String p1, final String p2);
    
    @Nullable
    String getSrgFieldName(final String p0, final String p1);
    
    @Nullable
    String getObfFieldName(final String p0, final String p1);
}
