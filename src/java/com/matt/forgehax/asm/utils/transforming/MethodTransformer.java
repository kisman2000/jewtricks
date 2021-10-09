//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.transforming;

import com.matt.forgehax.asm.*;
import com.google.common.collect.*;
import java.lang.annotation.*;
import org.objectweb.asm.tree.*;
import com.matt.forgehax.asm.utils.*;
import java.lang.reflect.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import joptsimple.internal.*;
import java.util.*;

public abstract class MethodTransformer implements ASMCommon
{
    private final Collection<TaskElement> tasks;
    
    public MethodTransformer() {
        this.tasks = (Collection<TaskElement>)Queues.newPriorityQueue();
        for (final Method m : this.getClass().getDeclaredMethods()) {
            try {
                m.setAccessible(true);
                if (m.isAnnotationPresent((Class<? extends Annotation>)Inject.class) && m.getParameterCount() > 0 && MethodNode.class.equals(m.getParameterTypes()[0])) {
                    this.tasks.add(new TaskElement(m, m.getAnnotation(Inject.class).description(), m.getAnnotation(Inject.class).priority()));
                }
            }
            catch (Exception e) {
                MethodTransformer.LOGGER.error(e.getMessage());
                ASMStackLogger.printStackTrace((Throwable)e);
            }
        }
    }
    
    public final Collection<TaskElement> getTasks() {
        return Collections.unmodifiableCollection((Collection<? extends TaskElement>)this.tasks);
    }
    
    public abstract ASMMethod getMethod();
    
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof MethodTransformer && Objects.equals(this.getMethod(), ((MethodTransformer)obj).getMethod());
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("MethodTransformer [");
        builder.append((this.getMethod() != null) ? this.getMethod().toString() : "null");
        builder.append("] ");
        if (this.tasks.isEmpty()) {
            builder.append("No transform tasks");
        }
        else {
            builder.append("Found ");
            builder.append(this.tasks.size());
            builder.append(" transform tasks: ");
            final Iterator<TaskElement> it = this.tasks.iterator();
            while (it.hasNext()) {
                final TaskElement next = it.next();
                final String desc = next.getMethod().getDeclaredAnnotation(Inject.class).description();
                if (!Strings.isNullOrEmpty(desc)) {
                    builder.append(desc);
                }
                if (it.hasNext()) {
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }
    
    public static class TaskElement implements Comparable<TaskElement>
    {
        private final Method method;
        private final String description;
        private final InjectPriority priority;
        
        public TaskElement(final Method method, final String description, final InjectPriority priority) {
            this.method = method;
            this.description = description;
            this.priority = priority;
        }
        
        public Method getMethod() {
            return this.method;
        }
        
        public String getDescription() {
            return this.description;
        }
        
        public InjectPriority getPriority() {
            return this.priority;
        }
        
        @Override
        public int compareTo(final TaskElement o) {
            return this.priority.compareTo((Enum)o.priority);
        }
        
        @Override
        public boolean equals(final Object obj) {
            return obj instanceof TaskElement && this.method.equals(((TaskElement)obj).method);
        }
    }
}
