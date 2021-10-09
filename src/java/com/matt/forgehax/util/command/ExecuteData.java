//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import com.matt.forgehax.*;
import com.matt.forgehax.util.console.*;
import joptsimple.*;
import javax.annotation.*;
import com.matt.forgehax.util.*;
import com.google.common.collect.*;
import java.util.*;
import com.matt.forgehax.util.command.exception.*;

public class ExecuteData implements Globals, ConsoleWriter
{
    private final Command command;
    private final OptionSet options;
    private final List arguments;
    @Nullable
    private Map<String, Object> data;
    private State state;
    private boolean stopped;
    
    public ExecuteData(final Command command, final OptionSet options, final Object[] extraArguments) {
        this.arguments = Lists.newArrayList();
        this.data = null;
        this.state = State.FAILED;
        this.stopped = false;
        this.command = command;
        this.options = options;
        this.arguments.addAll(Arrays.asList(extraArguments));
        this.arguments.addAll(options.nonOptionArguments());
    }
    
    public State state() {
        return this.state;
    }
    
    private void setState(final State state, final State... conditions) {
        for (final State s : conditions) {
            if (this.state.equals(s)) {
                return;
            }
        }
        this.state = state;
    }
    
    public void markSuccess(final State... conditions) {
        this.setState(State.SUCCESS, conditions);
    }
    
    public void markFailed(final State... conditions) {
        this.setState(State.FAILED, conditions);
    }
    
    public void unmark() {
        this.state = State.NONE;
    }
    
    public Command command() {
        return this.command;
    }
    
    public OptionSet options() {
        return this.options;
    }
    
    public List<?> arguments() {
        return (List<?>)this.arguments;
    }
    
    public <T> T getArgument(final int index) {
        try {
            return this.arguments.get(index);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public String getArgumentAsString(final int index) {
        return SafeConverter.toString(this.getArgument(index));
    }
    
    public int getArgumentCount() {
        return this.arguments.size();
    }
    
    public Object getOption(final String name, final Object defaultValue) {
        try {
            return this.getOptions(name).get(0);
        }
        catch (Throwable t) {
            return defaultValue;
        }
    }
    
    public Object getOption(final String name) {
        return this.getOption(name, null);
    }
    
    public String getOptionAsString(final String name) {
        return String.valueOf(this.getOption(name));
    }
    
    public List<?> getOptions(final String name) {
        if (this.options.has(name)) {
            try {
                return (List<?>)this.options.valuesOf(name);
            }
            catch (Throwable t) {}
        }
        return Collections.emptyList();
    }
    
    public boolean hasOption(final String name) {
        return this.options.has(name);
    }
    
    public <T> void set(final String name, final T element) {
        if (this.data == null) {
            this.data = (Map<String, Object>)Maps.newHashMap();
        }
        if (element == null) {
            this.data.remove(name);
        }
        else {
            this.data.put(name, element);
        }
    }
    
    public boolean has(final String name) {
        return this.data != null && this.data.get(name) != null;
    }
    
    public <T> T get(final String name, final T defaultValue) {
        try {
            Objects.requireNonNull(this.data);
            return (T)this.data.getOrDefault(name, defaultValue);
        }
        catch (Throwable t) {
            ExecuteData.LOGGER.warn(String.format("Cannot find entry named \"%s\"", name));
            return defaultValue;
        }
    }
    
    public <T> T get(final String name) {
        return this.get(name, (T)null);
    }
    
    public void requiresEntry(final String name) throws MissingEntryException {
        if (this.get(name) == null) {
            this.markFailed(new State[0]);
            throw new MissingEntryException(String.format("Missing data entry \"%s\"", name));
        }
    }
    
    public void requiredArguments(final int numberRequired) {
        if (this.arguments.size() < numberRequired) {
            this.markFailed(new State[0]);
            throw new CommandExecuteException("Missing argument(s)");
        }
    }
    
    public boolean isStopped() {
        return this.stopped;
    }
    
    public void startProcessing() {
        this.stopped = false;
    }
    
    public void stopProcessing() {
        this.stopped = true;
    }
    
    public enum State
    {
        NONE, 
        SUCCESS, 
        FAILED;
    }
}
