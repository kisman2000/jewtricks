//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.key;

import com.matt.forgehax.*;
import net.minecraftforge.client.settings.*;
import net.minecraft.client.settings.*;
import com.matt.forgehax.asm.reflection.*;

public class KeyBindingHandler implements Globals
{
    private static final IKeyConflictContext OVERRIDE_KEYCONFLICT_CONTEXT;
    private final KeyBinding binding;
    private IKeyConflictContext oldConflictContext;
    private int bindingCount;
    
    public KeyBindingHandler(final KeyBinding bind) {
        this.oldConflictContext = null;
        this.bindingCount = 0;
        this.binding = bind;
    }
    
    public KeyBinding getBinding() {
        return this.binding;
    }
    
    public boolean isPressed() {
        return (boolean)FastReflection.Fields.Binding_pressed.get((Object)this.binding);
    }
    
    public void setPressed(final boolean pressed) {
        FastReflection.Fields.Binding_pressed.set((Object)this.binding, (Object)pressed);
    }
    
    public int getPressTime() {
        return (int)FastReflection.Fields.Binding_pressTime.get((Object)this.binding);
    }
    
    public void setPressTime(final int time) {
        FastReflection.Fields.Binding_pressTime.set((Object)this.binding, (Object)time);
    }
    
    public boolean isBound() {
        return this.binding.getKeyConflictContext() == KeyBindingHandler.OVERRIDE_KEYCONFLICT_CONTEXT;
    }
    
    public void bind() {
        ++this.bindingCount;
        if (this.oldConflictContext == null) {
            this.oldConflictContext = this.binding.getKeyConflictContext();
            this.binding.setKeyConflictContext(KeyBindingHandler.OVERRIDE_KEYCONFLICT_CONTEXT);
        }
    }
    
    public void attemptBind() {
        if (!this.isBound()) {
            this.bind();
        }
    }
    
    public void unbind() {
        --this.bindingCount;
        if (this.oldConflictContext != null && this.bindingCount <= 0) {
            this.binding.setKeyConflictContext(this.oldConflictContext);
            this.oldConflictContext = null;
        }
        if (this.bindingCount < 0) {
            this.bindingCount = 0;
        }
    }
    
    public void attemptUnbind() {
        if (this.isBound()) {
            this.unbind();
        }
    }
    
    static {
        OVERRIDE_KEYCONFLICT_CONTEXT = (IKeyConflictContext)new IKeyConflictContext() {
            public boolean isActive() {
                return true;
            }
            
            public boolean conflicts(final IKeyConflictContext other) {
                return false;
            }
        };
    }
}
