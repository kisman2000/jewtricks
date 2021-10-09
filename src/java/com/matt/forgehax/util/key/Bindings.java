//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.key;

import com.matt.forgehax.*;
import javax.annotation.*;
import net.minecraft.client.settings.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Bindings implements Globals
{
    public static final List<KeyBindingHandler> KEY_LIST;
    public static final KeyBindingHandler forward;
    public static final KeyBindingHandler back;
    public static final KeyBindingHandler left;
    public static final KeyBindingHandler right;
    public static final KeyBindingHandler jump;
    public static final KeyBindingHandler sprint;
    public static final KeyBindingHandler sneak;
    public static final KeyBindingHandler attack;
    public static final KeyBindingHandler use;
    
    @Nullable
    public static KeyBindingHandler getKey(final String name) {
        return Bindings.KEY_LIST.stream().filter(k -> k.getBinding().getKeyDescription().toLowerCase().contains(name.toLowerCase())).findFirst().orElse(null);
    }
    
    @Nullable
    private static List<KeyBindingHandler> getAllKeys() {
        final Field[] fields = GameSettings.class.getFields();
        return Arrays.stream(fields).filter(f -> f.getType() == KeyBinding.class).map((Function<? super Field, ?>)Bindings::getBinding).filter(Objects::nonNull).map((Function<? super Object, ?>)KeyBindingHandler::new).collect((Collector<? super Object, ?, List<KeyBindingHandler>>)Collectors.toList());
    }
    
    private static KeyBinding getBinding(final Field field) {
        try {
            return (KeyBinding)field.get(Bindings.MC.gameSettings);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        KEY_LIST = getAllKeys();
        forward = new KeyBindingHandler(Bindings.MC.gameSettings.keyBindForward);
        back = new KeyBindingHandler(Bindings.MC.gameSettings.keyBindBack);
        left = new KeyBindingHandler(Bindings.MC.gameSettings.keyBindLeft);
        right = new KeyBindingHandler(Bindings.MC.gameSettings.keyBindRight);
        jump = new KeyBindingHandler(Bindings.MC.gameSettings.keyBindJump);
        sprint = new KeyBindingHandler(Bindings.MC.gameSettings.keyBindSprint);
        sneak = new KeyBindingHandler(Bindings.MC.gameSettings.keyBindSneak);
        attack = new KeyBindingHandler(Bindings.MC.gameSettings.keyBindAttack);
        use = new KeyBindingHandler(Bindings.MC.gameSettings.keyBindUseItem);
    }
}
