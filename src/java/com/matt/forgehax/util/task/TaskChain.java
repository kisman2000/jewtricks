//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.task;

import java.util.*;
import com.google.common.collect.*;

public interface TaskChain<E> extends Iterator<E>
{
    public static final TaskChain EMPTY = new TaskChain<Object>() {
        @Override
        public TaskChain<Object> then(final Object task) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public TaskChain<Object> thenLast(final Object task) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public boolean hasNext() {
            return false;
        }
        
        @Override
        public Object next() {
            return null;
        }
    };
    
    default <T> Builder<T> builder() {
        return new Builder<T>();
    }
    
    default <T> TaskChain<T> singleton(final T taskIn) {
        return new TaskChain<T>() {
            T task = taskIn;
            
            @Override
            public TaskChain<T> then(final T task) {
                throw new UnsupportedOperationException();
            }
            
            @Override
            public TaskChain<T> thenLast(final T task) {
                throw new UnsupportedOperationException();
            }
            
            @Override
            public boolean hasNext() {
                return this.task != null;
            }
            
            @Override
            public T next() {
                final T ret = this.task;
                this.task = null;
                return ret;
            }
        };
    }
    
    default <T> TaskChain<T> empty() {
        return (TaskChain<T>)TaskChain.EMPTY;
    }
    
    TaskChain<E> then(final E p0);
    
    TaskChain<E> thenLast(final E p0);
    
    default boolean isEmpty() {
        return !this.hasNext();
    }
    
    public static class Builder<T>
    {
        private final Queue<T> queue;
        
        public Builder() {
            this.queue = (Queue<T>)Queues.newArrayDeque();
        }
        
        public Builder<T> then(final T task) {
            this.queue.add(task);
            return this;
        }
        
        public Builder<T> addAll(final Collection<T> tsks) {
            this.queue.addAll((Collection<?>)tsks);
            return this;
        }
        
        public Builder<T> collect(final TaskChain<T> ts) {
            while (ts.hasNext()) {
                this.queue.add(ts.next());
            }
            return this;
        }
        
        public TaskChain<T> build() {
            return new DynamicTaskChain<T>((Collection)this.queue);
        }
    }
    
    public static class DynamicTaskChain<T> implements TaskChain<T>
    {
        private final List<T> tasks;
        
        private DynamicTaskChain() {
            this.tasks = (List<T>)Lists.newArrayList();
        }
        
        private DynamicTaskChain(final Collection<T> collection) {
            (this.tasks = (List<T>)Lists.newArrayList()).addAll((Collection<? extends T>)collection);
        }
        
        @Override
        public TaskChain<T> then(final T task) {
            this.tasks.add(0, task);
            return this;
        }
        
        @Override
        public TaskChain<T> thenLast(final T task) {
            this.tasks.add(task);
            return null;
        }
        
        @Override
        public boolean hasNext() {
            return !this.tasks.isEmpty();
        }
        
        @Override
        public T next() {
            return this.tasks.remove(0);
        }
    }
}
