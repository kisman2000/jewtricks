//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.key;

import java.util.*;
import net.minecraftforge.client.settings.*;
import org.lwjgl.input.*;
import net.minecraft.client.settings.*;
import com.google.common.collect.*;

public class BindingHelper
{
    private static final Map<Integer, String> MOUSE_CODES;
    private static final IKeyConflictContext EMPTY;
    
    public static String getIndexName(final int code) {
        if (BindingHelper.MOUSE_CODES.get(code) != null) {
            return BindingHelper.MOUSE_CODES.get(code);
        }
        if (code < 0) {
            return Mouse.getButtonName(100 + code);
        }
        return Keyboard.getKeyName(code);
    }
    
    public static String getIndexName(final KeyBinding binding) {
        return getIndexName(binding.getKeyCode());
    }
    
    public static IKeyConflictContext getEmptyKeyConflictContext() {
        return BindingHelper.EMPTY;
    }
    
    static {
        MOUSE_CODES = Maps.newHashMap();
        EMPTY = (IKeyConflictContext)new IKeyConflictContext() {
            public boolean isActive() {
                return false;
            }
            
            public boolean conflicts(final IKeyConflictContext other) {
                return false;
            }
        };
        BindingHelper.MOUSE_CODES.put(-100, "MOUSE_LEFT");
        BindingHelper.MOUSE_CODES.put(-99, "MOUSE_RIGHT");
        BindingHelper.MOUSE_CODES.put(-98, "MOUSE_MIDDLE");
    }
}
