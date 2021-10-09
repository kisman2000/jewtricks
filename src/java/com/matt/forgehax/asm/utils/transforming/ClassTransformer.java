//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.transforming;

import com.matt.forgehax.asm.*;
import org.objectweb.asm.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import com.google.common.collect.*;
import com.matt.forgehax.asm.utils.*;
import com.matt.forgehax.asm.utils.environment.*;
import org.objectweb.asm.tree.*;
import java.lang.reflect.*;
import java.util.*;

public abstract class ClassTransformer implements ASMCommon, TypesMc, Opcodes, ASMHelper.MagicOpcodes
{
    private final ASMClass transformingClass;
    private final List<MethodTransformer> methodTransformers;
    
    public ClassTransformer(final ASMClass clazz) {
        this.methodTransformers = (List<MethodTransformer>)Lists.newArrayList();
        this.transformingClass = clazz;
        for (final Class c : this.getClass().getDeclaredClasses()) {
            try {
                if (c.isAnnotationPresent(RegisterMethodTransformer.class) && MethodTransformer.class.isAssignableFrom(c)) {
                    try {
                        final Constructor constructor = c.getDeclaredConstructor(this.getClass());
                        constructor.setAccessible(true);
                        final MethodTransformer t = constructor.newInstance(this);
                        this.registerMethodPatch(t);
                    }
                    catch (NoSuchMethodException e2) {
                        final Constructor constructor = c.getDeclaredConstructor((Class[])new Class[0]);
                        constructor.setAccessible(true);
                        final MethodTransformer t2 = constructor.newInstance(new Object[0]);
                        this.registerMethodPatch(t2);
                    }
                }
            }
            catch (Exception e) {
                ClassTransformer.LOGGER.error(e.getMessage());
                ASMStackLogger.printStackTrace((Throwable)e);
            }
        }
    }
    
    public State getClassObfuscationState() {
        return RuntimeState.getDefaultState();
    }
    
    public void registerMethodPatch(final MethodTransformer transformer) {
        this.methodTransformers.add(transformer);
    }
    
    public ASMClass getTransformingClass() {
        return this.transformingClass;
    }
    
    public String getTransformingClassName() {
        return this.transformingClass.getName();
    }
    
    public final void transform(final ClassNode node) {
        RuntimeState.setState(this.getClassObfuscationState());
        try {
            for (final MethodNode methodNode : node.methods) {
                final MethodNode methodNode2;
                final Object o;
                StringBuilder builder;
                StringBuilder builder2;
                this.methodTransformers.stream().filter(t -> Objects.equals(t.getMethod().getRuntimeName(), methodNode2.name) && Objects.equals(t.getMethod().getRuntimeDescriptor(), methodNode2.desc)).forEach(t -> t.getTasks().forEach(task -> {
                    try {
                        task.getMethod().invoke(t, o);
                        builder = new StringBuilder();
                        builder.append("Successfully transformed the task \"");
                        builder.append(task.getDescription());
                        builder.append("\" for ");
                        builder.append(this.getTransformingClassName());
                        builder.append("::");
                        builder.append(t.getMethod().getName());
                        ClassTransformer.LOGGER.info(builder.toString());
                    }
                    catch (Throwable e) {
                        if (e instanceof InvocationTargetException) {
                            e = e.getCause();
                        }
                        builder2 = new StringBuilder();
                        builder2.append(e.getClass().getSimpleName());
                        builder2.append(" thrown from ");
                        builder2.append(this.getTransformingClassName());
                        builder2.append("::");
                        builder2.append(t.getMethod().getName());
                        builder2.append(" for the task with the description \"");
                        builder2.append(task.getDescription());
                        builder2.append("\": ");
                        builder2.append(e.getMessage());
                        ClassTransformer.LOGGER.error(builder2.toString());
                        ASMStackLogger.printStackTrace(e);
                    }
                }));
            }
        }
        finally {
            RuntimeState.releaseState();
        }
    }
}
