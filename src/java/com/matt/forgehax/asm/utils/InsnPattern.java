//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils;

import org.objectweb.asm.tree.*;
import java.util.*;

public class InsnPattern
{
    private final AbstractInsnNode first;
    private final AbstractInsnNode last;
    
    public InsnPattern(final AbstractInsnNode first, final AbstractInsnNode last) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(last);
        this.first = first;
        this.last = last;
    }
    
    public <T extends AbstractInsnNode> T getFirst() {
        return (T)this.first;
    }
    
    public <T extends AbstractInsnNode> T getLast() {
        return (T)this.last;
    }
    
    public <T extends AbstractInsnNode> T getIndex(final int index) {
        AbstractInsnNode node = this.first;
        for (int i = 0; i < index; ++i) {
            node = node.getNext();
        }
        return (T)node;
    }
}
