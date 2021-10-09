//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.key;

import net.minecraft.client.settings.*;

public interface IKeyBind
{
    void bind(final int p0);
    
    KeyBinding getBind();
    
    void onKeyPressed();
    
    void onKeyDown();
    
    default void unbind() {
        this.bind(0);
    }
}
