//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils;

import java.util.*;
import com.google.common.collect.*;

public class MultiBoolean
{
    private final Set<String> ids;
    private int level;
    
    public MultiBoolean() {
        this.ids = (Set<String>)Sets.newCopyOnWriteArraySet();
        this.level = 0;
    }
    
    private void clampLevel() {
        this.level = Math.max(0, Math.min(this.ids.size(), this.level));
    }
    
    public void enable(final String uniqueId) {
        if (this.ids.add(uniqueId)) {
            ++this.level;
            this.clampLevel();
        }
    }
    
    public void disable(final String uniqueId) {
        if (this.ids.remove(uniqueId)) {
            --this.level;
            this.clampLevel();
        }
    }
    
    public void forceDisable() {
        this.level = 0;
        this.ids.clear();
    }
    
    public boolean isEnabled() {
        return this.level > 0;
    }
}
