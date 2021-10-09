//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import java.util.function.*;
import com.matt.forgehax.util.command.callbacks.*;
import java.util.*;
import com.google.common.collect.*;
import joptsimple.*;

public abstract class BaseCommandBuilder<T extends BaseCommandBuilder, R extends Command>
{
    protected final Map<String, Object> data;
    private List<Consumer<OptionParser>> optionBuilders;
    private List<Consumer<ExecuteData>> processors;
    private Multimap<CallbackType, Consumer<CallbackData>> callbacks;
    
    protected T insert(final String entry, final Object o) {
        final Object g = this.data.get(entry);
        if (g != null && o == null) {
            this.data.remove(entry);
        }
        else if (o != null) {
            this.data.put(entry, o);
        }
        return (T)this;
    }
    
    protected boolean has(final String entry) {
        return this.data.get(entry) != null;
    }
    
    protected <E> Collection<E> getCallbacks(final CallbackType type) {
        if (this.callbacks == null) {
            this.callbacks = (Multimap<CallbackType, Consumer<CallbackData>>)Multimaps.newSetMultimap((Map)Maps.newHashMap(), Sets::newHashSet);
            this.data.put("Command.callbacks", this.callbacks);
        }
        return (Collection<E>)this.callbacks.get((Object)type);
    }
    
    protected Collection<Consumer<OptionParser>> getOptionBuilders() {
        if (this.optionBuilders == null) {
            this.optionBuilders = (List<Consumer<OptionParser>>)Lists.newArrayList();
            this.data.put("Command.optionbuilder", this.optionBuilders);
        }
        return this.optionBuilders;
    }
    
    protected Collection<Consumer<ExecuteData>> getProcessors() {
        if (this.processors == null) {
            this.processors = (List<Consumer<ExecuteData>>)Lists.newArrayList();
            this.data.put("Command.processors", this.processors);
        }
        return this.processors;
    }
    
    protected BaseCommandBuilder() {
        this.data = (Map<String, Object>)Maps.newHashMap();
    }
    
    public T parent(final Command parent) {
        return this.insert("Command.parent", parent);
    }
    
    public T name(final String name) {
        return this.insert("Command.name", name);
    }
    
    public T description(final String description) {
        return this.insert("Command.description", description);
    }
    
    public T processor(final Consumer<ExecuteData> processor) {
        this.getProcessors().add(processor);
        return (T)this;
    }
    
    public T options(final Consumer<OptionParser> optionBuilder) {
        this.getOptionBuilders().add(optionBuilder);
        return (T)this;
    }
    
    public T help(final Consumer<OptionSet> consumer) {
        return this.insert("Command.help", consumer);
    }
    
    public T success(final Consumer<CallbackData> consumer) {
        this.getCallbacks(CallbackType.SUCCESS).add(consumer);
        return (T)this;
    }
    
    public T failure(final Consumer<CallbackData> consumer) {
        this.getCallbacks(CallbackType.FAILURE).add(consumer);
        return (T)this;
    }
    
    public T helpOption(final boolean b) {
        return this.insert("Command.helpAutoGen", b);
    }
    
    public T requiredArgs(final int required) {
        return this.insert("Command.requiredArgs", required);
    }
    
    public Map<String, Object> getData() {
        return this.data;
    }
    
    public abstract R build();
}
