//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils;

import java.util.function.*;
import javax.annotation.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import java.util.*;
import java.util.stream.*;
import org.objectweb.asm.tree.*;

public class ASMHelper
{
    public static AbstractInsnNode findPattern(final AbstractInsnNode start, final int[] pattern, final char[] mask) {
        if (pattern.length != mask.length) {
            throw new IllegalArgumentException("Mask must be same length as pattern");
        }
        return findPattern(start, pattern.length, node -> true, (found, next) -> mask[found] != 'x' || next.getOpcode() == pattern[found], (first, last) -> first);
    }
    
    public static <T> T findPattern(final AbstractInsnNode start, final int patternSize, final Predicate<AbstractInsnNode> isValidNode, final BiPredicate<Integer, AbstractInsnNode> nodePredicate, final BiFunction<AbstractInsnNode, AbstractInsnNode, T> outputFunction) {
        if (start != null) {
            int found = 0;
            AbstractInsnNode next = start;
            do {
                final boolean validNode = isValidNode.test(next);
                if (!validNode || nodePredicate.test(found, next)) {
                    if (validNode) {
                        ++found;
                    }
                }
                else {
                    for (int i = 1; i <= found - 1; ++i) {
                        next = next.getPrevious();
                    }
                    found = 0;
                }
                if (found >= patternSize) {
                    final AbstractInsnNode end = next;
                    for (int j = 1; j <= found - 1; ++j) {
                        next = next.getPrevious();
                    }
                    return outputFunction.apply(next, end);
                }
                next = next.getNext();
            } while (next != null);
        }
        return null;
    }
    
    public static AbstractInsnNode findPattern(final AbstractInsnNode start, final int[] pattern, final String mask) {
        return findPattern(start, pattern, mask.toCharArray());
    }
    
    public static AbstractInsnNode findPattern(final AbstractInsnNode start, final int... opcodes) {
        final StringBuilder mask = new StringBuilder();
        for (final int op : opcodes) {
            mask.append((op == -666) ? '?' : 'x');
        }
        return findPattern(start, opcodes, mask.toString());
    }
    
    public static AbstractInsnNode findPattern(final InsnList instructions, final int... opcodes) {
        return findPattern(instructions.getFirst(), opcodes);
    }
    
    public static AbstractInsnNode findPattern(final MethodNode node, final int... opcodes) {
        return findPattern(node.instructions, opcodes);
    }
    
    @Nullable
    public static AbstractInsnNode forward(final AbstractInsnNode start, final int n) {
        AbstractInsnNode node = start;
        for (int i = 0; i < Math.abs(n) && node != null; ++i, node = ((n > 0) ? node.getNext() : node.getPrevious())) {}
        return node;
    }
    
    public static String getClassData(final ClassNode node) {
        final StringBuilder builder = new StringBuilder("METHODS:\n");
        for (final MethodNode method : node.methods) {
            builder.append("\t");
            builder.append(method.name);
            builder.append(method.desc);
            builder.append("\n");
        }
        builder.append("\nFIELDS:\n");
        for (final FieldNode field : node.fields) {
            builder.append("\t");
            builder.append(field.desc);
            builder.append(" ");
            builder.append(field.name);
            builder.append("\n");
        }
        return builder.toString();
    }
    
    public static MethodInsnNode call(final int opcode, final boolean isInterface, final ASMMethod method) {
        Objects.requireNonNull(method.getParentClass(), "Method requires assigned parent class");
        return new MethodInsnNode(opcode, method.getParentClass().getRuntimeInternalName(), method.getRuntimeName(), method.getRuntimeDescriptor(), false);
    }
    
    public static MethodInsnNode call(final int opcode, final ASMMethod method) {
        return call(opcode, false, method);
    }
    
    public static FieldInsnNode call(final int opcode, final ASMField field) {
        Objects.requireNonNull(field.getParentClass(), "Field requires assigned parent class");
        return new FieldInsnNode(opcode, field.getParentClass().getRuntimeInternalName(), field.getRuntimeName(), field.getRuntimeDescriptor());
    }
    
    public static int addNewLocalVariable(final MethodNode method, final String name, final String desc) {
        final AsmPattern labelPattern = new AsmPattern.Builder(0).label().build();
        final LabelNode start = labelPattern.test(method).getFirst();
        AbstractInsnNode iter = method.instructions.getFirst();
        LabelNode end = null;
        do {
            if (iter instanceof LabelNode) {
                end = (LabelNode)iter;
            }
            iter = iter.getNext();
        } while (iter != null);
        if (end == null) {
            throw new IllegalArgumentException("Failed to find LabelNode");
        }
        return addNewLocalVariable(method, name, desc, start, end);
    }
    
    public static int addNewLocalVariable(final MethodNode method, final String name, final String desc, final LabelNode start, final LabelNode end) {
        final Optional<LocalVariableNode> lastVar = (Optional<LocalVariableNode>)method.localVariables.stream().max(Comparator.comparingInt(var -> var.index));
        final int newIndex = lastVar.map(var -> var.desc.matches("[JD]") ? (var.index + 2) : (var.index + 1)).orElse(0);
        final LocalVariableNode variable = new LocalVariableNode(name, desc, (String)null, start, end, newIndex);
        method.localVariables.add(variable);
        return newIndex;
    }
    
    public static InsnList newInstance(final String name, final String[] argTypes, @Nullable final InsnList args) {
        final String desc = Stream.of(argTypes).collect(Collectors.joining("", "(", ")V"));
        return newInstance(name, desc, args);
    }
    
    public static InsnList newInstance(final String name, final String desc, @Nullable final InsnList args) {
        final InsnList list = new InsnList();
        list.add((AbstractInsnNode)new TypeInsnNode(187, name));
        list.add((AbstractInsnNode)new InsnNode(89));
        if (args != null) {
            list.add(args);
        }
        list.add((AbstractInsnNode)new MethodInsnNode(183, name, "<init>", desc, false));
        return list;
    }
    
    public interface MagicOpcodes
    {
        public static final int NONE = -666;
    }
}
