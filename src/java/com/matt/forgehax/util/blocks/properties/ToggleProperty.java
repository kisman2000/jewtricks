//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.blocks.properties;

import java.io.*;
import com.google.gson.stream.*;

public class ToggleProperty implements IBlockProperty
{
    private static final String HEADING = "enabled";
    private boolean enabled;
    
    public ToggleProperty() {
        this.enabled = true;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public void enable() {
        this.setEnabled(true);
    }
    
    public void disable() {
        this.setEnabled(false);
    }
    
    public void toggle() {
        if (this.enabled) {
            this.disable();
        }
        else {
            this.enable();
        }
    }
    
    public void serialize(final JsonWriter writer) throws IOException {
        writer.value(this.enabled);
    }
    
    public void deserialize(final JsonReader reader) throws IOException {
        this.setEnabled(reader.nextBoolean());
    }
    
    public boolean isNecessary() {
        return !this.enabled;
    }
    
    public String helpText() {
        return Boolean.toString(this.enabled);
    }
    
    public IBlockProperty newImmutableInstance() {
        return (IBlockProperty)new ImmutableToggle();
    }
    
    @Override
    public String toString() {
        return "enabled";
    }
    
    private static class ImmutableToggle extends ToggleProperty
    {
        @Override
        public boolean isEnabled() {
            return true;
        }
        
        @Override
        public void setEnabled(final boolean enabled) {
        }
        
        @Override
        public void enable() {
        }
        
        @Override
        public void disable() {
        }
        
        @Override
        public void toggle() {
        }
    }
}
