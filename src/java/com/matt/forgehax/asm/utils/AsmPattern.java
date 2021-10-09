//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils;

import com.google.common.collect.*;
import java.util.function.*;
import java.util.*;
import org.objectweb.asm.tree.*;

public class AsmPattern
{
    public static final int IGNORE_FRAMES = 1;
    public static final int IGNORE_LABELS = 2;
    public static final int IGNORE_LINENUMBERS = 4;
    public static final int CODE_ONLY = 7;
    private final int flags;
    private final ImmutableList<Predicate<AbstractInsnNode>> insnPredicates;
    
    private AsmPattern(final List<Predicate<AbstractInsnNode>> predicates, final int flags) {
        this.insnPredicates = (ImmutableList<Predicate<AbstractInsnNode>>)ImmutableList.copyOf((Collection)predicates);
        this.flags = flags;
    }
    
    public InsnPattern test(final MethodNode main) {
        return this.test(main.instructions.getFirst());
    }
    
    public InsnPattern test(final AbstractInsnNode start) {
        return (InsnPattern)ASMHelper.findPattern(start, this.insnPredicates.size(), node -> !this.testFlag(node, (Class<? extends AbstractInsnNode>)FrameNode.class, 1) && !this.testFlag(node, (Class<? extends AbstractInsnNode>)LabelNode.class, 2) && !this.testFlag(node, (Class<? extends AbstractInsnNode>)LineNumberNode.class, 4), (found, node) -> ((Predicate)this.insnPredicates.get((int)found)).test(node), (BiFunction)InsnPattern::new);
    }
    
    private boolean testFlag(final AbstractInsnNode node, final Class<? extends AbstractInsnNode> type, final int flag) {
        return type.isInstance(node) && (this.flags & flag) != 0x0;
    }
    
    public static class Builder
    {
        private final int flags;
        private final List<Predicate<AbstractInsnNode>> predicates;
        
        public Builder(final int flags) {
            this.predicates = new ArrayList<Predicate<AbstractInsnNode>>();
            this.flags = flags;
        }
        
        public Builder opcode(final int opcode) {
            return this.add(insn -> insn.getOpcode() == opcode);
        }
        
        public Builder opcodes(final int... opcodes) {
            for (final int o : opcodes) {
                this.opcode(o);
            }
            return this;
        }
        
        public Builder invoke() {
            return this.add(insn -> insn instanceof MethodInsnNode);
        }
        
        public Builder any() {
            return this.add(insn -> true);
        }
        
        public Builder label() {
            if ((this.flags & 0x2) != 0x0) {
                throw new IllegalStateException("Attempting to find a label with flag IGNORE_LABELS");
            }
            return this.add(insn -> insn instanceof LabelNode);
        }
        
        public <T extends AbstractInsnNode> Builder custom(final Predicate<T> predicate) {
            return this.add(predicate);
        }
        
        private Builder add(final Predicate<? extends AbstractInsnNode> predicate) {
            this.predicates.add((Predicate<AbstractInsnNode>)predicate);
            return this;
        }
        
        public AsmPattern build() {
            return new AsmPattern(this.predicates, this.flags, null);
        }
    }
}
