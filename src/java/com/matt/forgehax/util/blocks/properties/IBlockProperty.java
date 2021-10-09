//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.blocks.properties;

import com.matt.forgehax.util.serialization.*;

public interface IBlockProperty extends ISerializableJson
{
    boolean isNecessary();
    
    String helpText();
    
    IBlockProperty newImmutableInstance();
    
    default <T extends IBlockProperty> T cast() {
        return (T)this;
    }
    
    default <T extends IBlockProperty> T checkedCast() {
        try {
            return (T)this.cast();
        }
        catch (Throwable t) {
            return null;
        }
    }
}
