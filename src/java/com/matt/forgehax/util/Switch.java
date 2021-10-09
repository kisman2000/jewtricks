//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import com.google.common.collect.*;
import java.util.*;

public abstract class Switch
{
    private final Set<Handle> handles;
    private final String name;
    private int level;
    
    public Switch(final String name) {
        this.handles = (Set<Handle>)Sets.newHashSet();
        this.level = 0;
        this.name = name;
    }
    
    public Handle createHandle(final String id) {
        final Handle handle = new Handle(this, id);
        synchronized (this.handles) {
            if (this.handles.add(handle)) {
                return handle;
            }
            throw new Error("failed to add handle with id '" + id + "'");
        }
    }
    
    public boolean removeHandle(final Handle handle) {
        synchronized (this.handles) {
            return this.handles.remove(handle);
        }
    }
    
    private void enable() {
        this.level = Math.min(this.handles.size(), this.level + 1);
    }
    
    private void disable() {
        this.level = Math.max(0, this.level - 1);
    }
    
    public boolean isEnabled() {
        return this.level > 0;
    }
    
    public boolean isDisabled() {
        return !this.isEnabled();
    }
    
    protected abstract void onEnabled();
    
    protected abstract void onDisabled();
    
    @Override
    public String toString() {
        return this.name + "@" + this.handles.size();
    }
    
    public static class Handle
    {
        private final Switch parent;
        private final String id;
        private boolean enabled;
        
        private Handle(final Switch parent, final String id) {
            this.enabled = false;
            Objects.requireNonNull(parent, "null parent");
            Objects.requireNonNull(id, "null id");
            this.parent = parent;
            this.id = id;
        }
        
        public void enable() {
            if (!this.enabled) {
                this.enabled = true;
                this.parent.enable();
            }
            if (this.parent.isEnabled()) {
                this.parent.onEnabled();
            }
        }
        
        public void disable() {
            if (this.enabled) {
                this.enabled = false;
                this.parent.disable();
            }
            if (this.parent.isDisabled()) {
                this.parent.onDisabled();
            }
        }
        
        @Override
        public int hashCode() {
            return this.id.hashCode();
        }
        
        @Override
        public boolean equals(final Object obj) {
            return this == obj || (obj instanceof Handle && this.parent.equals(((Handle)obj).parent) && this.id == ((Handle)obj).id);
        }
        
        @Override
        public String toString() {
            return this.id;
        }
    }
}
