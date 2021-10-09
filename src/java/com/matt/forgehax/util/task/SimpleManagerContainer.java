//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.task;

import com.google.common.collect.*;
import com.matt.forgehax.util.common.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public class SimpleManagerContainer<T>
{
    private final List<Member<T>> functions;
    private final List<Listener<T>> listeners;
    
    public SimpleManagerContainer() {
        this.functions = (List<Member<T>>)Lists.newArrayList();
        this.listeners = (List<Listener<T>>)Lists.newArrayList();
    }
    
    private Optional<Member<T>> get(final T func) {
        synchronized (this.functions) {
            return this.functions.stream().filter(member -> member.getFunction() == func).findFirst();
        }
    }
    
    private Optional<Member<T>> find(final Predicate<Member<T>> predicate) {
        synchronized (this.functions) {
            return this.functions.stream().filter(predicate).findFirst();
        }
    }
    
    private boolean register(final T func, final PriorityEnum priority, final boolean once) {
        synchronized (this.functions) {
            if (this.get(func).isPresent()) {
                throw new IllegalArgumentException("function already registered");
            }
            synchronized (this.listeners) {
                this.listeners.forEach(l -> l.onRegister(func));
            }
            final boolean r = this.functions.add(new Member<T>((Object)func, priority, once));
            Collections.sort(this.functions);
            return r;
        }
    }
    
    public boolean register(final T func, final PriorityEnum priority) {
        return this.register(func, priority, false);
    }
    
    public boolean register(final T func) {
        return this.register(func, PriorityEnum.DEFAULT);
    }
    
    public boolean registerTemporary(final T func, final PriorityEnum priority) {
        return this.register(func, priority, true);
    }
    
    public boolean registerTemporary(final T func) {
        return this.registerTemporary(func, PriorityEnum.DEFAULT);
    }
    
    private void unregister(final Member<T> member) {
        synchronized (this.functions) {
            synchronized (this.listeners) {
                this.listeners.forEach(l -> l.onUnregister(member.getFunction()));
            }
            this.functions.remove(member);
        }
    }
    
    public void unregister(final T func) {
        synchronized (this.functions) {
            this.get(func).ifPresent(this::unregister);
        }
    }
    
    public boolean registerListener(final Listener<T> listener) {
        synchronized (this.listeners) {
            return this.listeners.add(listener);
        }
    }
    
    public boolean unregisterListener(final Listener<T> listener) {
        synchronized (this.listeners) {
            return this.listeners.remove(listener);
        }
    }
    
    public List<T> functions() {
        synchronized (this.functions) {
            return this.functions.stream().map((Function<? super Object, ?>)Member::getFunction).collect((Collector<? super Object, ?, List<T>>)Collectors.toList());
        }
    }
    
    private void setRunning(final Member<T> member, final boolean state) {
        synchronized (this.listeners) {
            if (state) {
                this.listeners.forEach(l -> l.onFunctionStarted(member.getFunction()));
                ((Member<Object>)member).setRunning(true);
            }
            else {
                this.listeners.forEach(l -> l.onFunctionStarted(member.getFunction()));
                ((Member<Object>)member).setRunning(false);
            }
        }
    }
    
    public void begin(final T function) {
        synchronized (this.functions) {
            final Member<T> member = this.get(function).orElse(null);
            if (member == null) {
                return;
            }
            this.find(Member::isRunning).ifPresent(m -> {
                synchronized (this.listeners) {
                    this.listeners.forEach(l -> l.onFunctionStopped(m.getFunction()));
                }
                m.setRunning(false);
                return;
            });
            this.setRunning(member, true);
        }
    }
    
    public void finish(final T function) {
        synchronized (this.functions) {
            final Member<T> member = this.get(function).orElse(null);
            if (member == null || !member.isRunning()) {
                return;
            }
            this.setRunning(member, false);
            if (((Member<Object>)member).isOnce()) {
                this.unregister(member);
            }
        }
    }
    
    protected static class Member<E> implements Comparable<Member>
    {
        private final E function;
        private final PriorityEnum priority;
        private final boolean once;
        private boolean running;
        
        private Member(final E function, final PriorityEnum priority, final boolean once) {
            this.running = false;
            Objects.requireNonNull(function);
            Objects.requireNonNull(priority);
            this.function = function;
            this.priority = priority;
            this.once = once;
        }
        
        public E getFunction() {
            return this.function;
        }
        
        public PriorityEnum getPriority() {
            return this.priority;
        }
        
        private boolean isOnce() {
            return this.once;
        }
        
        public boolean isRunning() {
            return this.running;
        }
        
        private void setRunning(final boolean running) {
            this.running = running;
        }
        
        @Override
        public int compareTo(final Member o) {
            return (this.isOnce() || o.isOnce()) ? Boolean.compare(this.isOnce(), o.isOnce()) : this.getPriority().compareTo((Enum)o.getPriority());
        }
        
        @Override
        public int hashCode() {
            return System.identityHashCode(this.getFunction());
        }
        
        @Override
        public boolean equals(final Object obj) {
            return this == obj || (obj instanceof Member && this.getFunction() == ((Member)obj).getFunction());
        }
    }
    
    public interface Listener<E>
    {
        void onRegister(final E p0);
        
        void onUnregister(final E p0);
        
        void onFunctionStarted(final E p0);
        
        void onFunctionStopped(final E p0);
    }
}
