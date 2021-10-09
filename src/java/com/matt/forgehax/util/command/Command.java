//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import com.matt.forgehax.util.command.callbacks.*;
import com.google.common.collect.*;
import com.matt.forgehax.util.*;
import javax.annotation.*;
import java.util.*;
import fuck.you.jewtricks.base.*;
import com.matt.forgehax.util.command.exception.*;
import java.util.stream.*;
import java.util.function.*;
import com.matt.forgehax.util.console.*;
import joptsimple.*;
import com.matt.forgehax.util.serialization.*;
import com.matt.forgehax.*;
import java.nio.file.*;
import com.google.gson.stream.*;
import java.io.*;

public class Command implements Comparable<Command>, ISerializer, GsonConstant
{
    private static final Path SETTINGS_DIR;
    public static final String NAME = "Command.name";
    public static final String DESCRIPTION = "Command.description";
    public static final String OPTIONBUILDERS = "Command.optionbuilder";
    public static final String PROCESSORS = "Command.processors";
    public static final String HELP = "Command.help";
    public static final String PARENT = "Command.parent";
    public static final String HELPAUTOGEN = "Command.helpAutoGen";
    public static final String CALLBACKS = "Command.callbacks";
    public static final String REQUIREDARGS = "Command.requiredArgs";
    private final String name;
    private final String description;
    private final String lowercasename;
    protected final OptionParser parser;
    protected final Collection<Consumer<ExecuteData>> processors;
    protected final Consumer<ExecuteData> help;
    private final Set<Command> children;
    protected final Multimap<CallbackType, Consumer<CallbackData>> callbacks;
    private final int requiredArgs;
    private Command parent;
    
    protected Command(final Map<String, Object> data) throws CommandBuildException {
        this.parser = new OptionParser();
        this.processors = (Collection<Consumer<ExecuteData>>)Lists.newArrayList();
        this.children = (Set<Command>)Sets.newHashSet();
        this.callbacks = (Multimap<CallbackType, Consumer<CallbackData>>)Multimaps.newSetMultimap((Map)Maps.newHashMap(), Sets::newLinkedHashSet);
        try {
            this.name = data.get("Command.name");
            this.lowercasename = data.get("Command.name").toLowerCase();
            Objects.requireNonNull(this.name, "Command requires name");
            this.description = data.getOrDefault("Command.description", "");
            this.help = data.get("Command.help");
            final Collection<Consumer<ExecuteData>> processors = data.get("Command.processors");
            if (processors != null) {
                this.processors.addAll(processors);
            }
            final Command parent = data.get("Command.parent");
            if (parent != null) {
                parent.addChild(this);
            }
            final Boolean helpAutoGen = data.getOrDefault("Command.helpAutoGen", true);
            if (helpAutoGen) {
                this.parser.acceptsAll((List)Arrays.asList("help", "?"), "Help text for options");
            }
            final Collection<Consumer<OptionParser>> optionBuilders = data.get("Command.optionbuilder");
            if (optionBuilders != null) {
                for (final Consumer<OptionParser> c : optionBuilders) {
                    c.accept(this.parser);
                }
            }
            final Multimap<CallbackType, Consumer<CallbackData>> callbacks = (Multimap<CallbackType, Consumer<CallbackData>>)data.get("Command.callbacks");
            if (callbacks != null) {
                this.callbacks.putAll((Multimap)callbacks);
            }
            this.requiredArgs = Math.max(SafeConverter.toInteger(data.getOrDefault("Command.requiredArgs", 0)), 0);
        }
        catch (Throwable t) {
            throw new CommandBuildException("Failed to build command", t);
        }
    }
    
    public boolean isGlobal() {
        return false;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getNameLowerCase() {
        return this.lowercasename;
    }
    
    public String getAbsoluteName() {
        return (this.getParent() != null && !this.getParent().isGlobal()) ? (this.getParent().getAbsoluteName() + "." + this.getName()) : this.getName();
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getPrintText() {
        return this.getName() + " - " + this.getDescription();
    }
    
    public String getOptionHelpText() {
        final StringWriter writer = new StringWriter();
        try {
            this.parser.printHelpOn((Writer)writer);
        }
        catch (IOException ex) {}
        finally {
            try {
                writer.close();
            }
            catch (IOException ex2) {}
        }
        return writer.toString();
    }
    
    @Nullable
    public Command getParent() {
        return this.parent;
    }
    
    protected void setParent(final Command parent) {
        if (this.parent != null && parent != null) {
            throw new CommandParentNonNullException("Command parent already exists");
        }
        this.parent = parent;
    }
    
    public boolean leaveParent() {
        return this.parent != null && this.parent.removeChild(this);
    }
    
    public CommandBuilders builders() {
        return CommandBuilders.newInstance(this);
    }
    
    public boolean addChild(@Nonnull final Command child) {
        final boolean b;
        if (b = this.children.add(child)) {
            child.setParent(this);
        }
        return b;
    }
    
    public boolean removeChild(@Nonnull final Command child) {
        final boolean b;
        if (b = this.children.remove(child)) {
            child.setParent(null);
        }
        return b;
    }
    
    @Nullable
    public Command getChild(final String name) {
        for (final Command command : this.children) {
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
            }
        }
        return null;
    }
    
    public Collection<Command> getChildren() {
        return Collections.unmodifiableCollection((Collection<? extends Command>)this.children);
    }
    
    public void getChildrenDeep(final Collection<Command> all) {
        all.addAll(this.getChildren());
        this.children.forEach(child -> child.getChildrenDeep(all));
    }
    
    public Collection<Command> getChildrenDeep() {
        final Collection<Command> all = (Collection<Command>)Sets.newHashSet();
        this.getChildrenDeep(all);
        return all;
    }
    
    public void abandonChildren() {
        this.children.forEach(Command::leaveParent);
    }
    
    @Nullable
    protected <T extends CallbackData> Consumer<T> addCallback(final CallbackType type, final Consumer<T> consumer) {
        return this.callbacks.put((Object)type, (Object)consumer) ? consumer : null;
    }
    
    protected boolean removeCallback(final CallbackType type, final Consumer<? extends CallbackData> consumer) {
        return this.callbacks.remove((Object)type, (Object)consumer);
    }
    
    protected <T extends CallbackData> void invokeCallbacks(final CallbackType type, final T data) {
        this.callbacks.get((Object)type).forEach(c -> c.accept(data));
    }
    
    protected boolean processHelp(final ExecuteData data) throws CommandExecuteException, NullPointerException {
        if (this.help != null) {
            this.help.accept(data);
            return true;
        }
        if (data.options().has("help")) {
            Wrapper.printMessageNaked(this.getOptionHelpText());
            return true;
        }
        return false;
    }
    
    protected boolean processMain(final ExecuteData data) throws CommandExecuteException, NullPointerException {
        if (this.processors != null) {
            for (final Consumer<ExecuteData> c : this.processors) {
                try {
                    c.accept(data);
                    if (data.isStopped()) {
                        break;
                    }
                    continue;
                }
                catch (Throwable t) {
                    data.markFailed(new ExecuteData.State[0]);
                    throw t;
                }
            }
            return true;
        }
        return false;
    }
    
    protected boolean processChildren(@Nonnull final String[] args) throws CommandExecuteException, NullPointerException {
        if (args.length > 0) {
            final String lookup = ((args[0] != null) ? args[0] : "").toLowerCase();
            final Command child = this.getChild(lookup);
            if (child != null) {
                child.run(CommandHelper.forward(args));
                return true;
            }
            final List<Command> results = this.children.stream().filter(cmd -> cmd.getName().toLowerCase().startsWith(lookup)).collect((Collector<? super Object, ?, List<Command>>)Collectors.toList());
            if (results.size() == 1) {
                results.get(0).run(CommandHelper.forward(args));
                return true;
            }
            if (results.size() > 1) {
                throw new CommandExecuteException(String.format("Ambiguous command \"%s\": %s", lookup, results.stream().map((Function<? super Object, ?>)Command::getName).collect((Collector<? super Object, ?, String>)Collectors.joining(", "))));
            }
        }
        return false;
    }
    
    protected boolean preprocessor(final String[] args) {
        return true;
    }
    
    public void run(@Nonnull final String[] args) throws CommandExecuteException, NullPointerException {
        if (!this.processChildren(args)) {
            if (!this.preprocessor(args)) {
                return;
            }
            String[] required;
            OptionSet options;
            if (this.requiredArgs > 0) {
                if (args.length == 0) {
                    ConsoleIO.write(this.getPrintText());
                    return;
                }
                if (args.length < this.requiredArgs) {
                    throw new CommandExecuteException("Missing argument(s)");
                }
                required = Arrays.copyOfRange(args, 0, this.requiredArgs);
                String[] nargs;
                if (args.length > this.requiredArgs) {
                    nargs = Arrays.copyOfRange(args, this.requiredArgs, args.length);
                }
                else {
                    nargs = new String[0];
                }
                options = this.parser.parse(nargs);
            }
            else {
                options = this.parser.parse(args);
                required = new String[0];
            }
            final ExecuteData data = new ExecuteData(this, options, required);
            if (!this.processHelp(data)) {
                this.processMain(data);
            }
            switch (data.state()) {
                case SUCCESS: {
                    this.invokeCallbacks(CallbackType.SUCCESS, new CallbackData(this));
                    break;
                }
                case FAILED: {
                    this.invokeCallbacks(CallbackType.FAILURE, new CallbackData(this));
                    break;
                }
            }
        }
    }
    
    private Path getSettingsPath() {
        return Command.SETTINGS_DIR.resolve(this.getAbsoluteName() + ".json");
    }
    
    @Override
    public void serialize() {
        if (this instanceof ISerializableJson) {
            final ISerializableJson serializable = (ISerializableJson)this;
            final Path path = this.getSettingsPath();
            final StringWriter sw = new StringWriter();
            final JsonWriter writer = new JsonWriter((Writer)sw);
            try {
                writer.beginArray();
                serializable.serialize(writer);
                writer.endArray();
                Files.write(path, sw.toString().getBytes(), new OpenOption[0]);
            }
            catch (Throwable t) {
                Wrapper.printStackTrace(t);
                Globals.LOGGER.warn(String.format("Could not serialize \"%s\": %s", this.getAbsoluteName(), t.getMessage()));
                try {
                    sw.close();
                }
                catch (IOException ex) {}
                finally {
                    try {
                        writer.close();
                    }
                    catch (IOException ex2) {}
                }
            }
            finally {
                try {
                    sw.close();
                }
                catch (IOException ex3) {
                    try {
                        writer.close();
                    }
                    catch (IOException ex4) {}
                }
                finally {
                    try {
                        writer.close();
                    }
                    catch (IOException ex5) {}
                }
            }
        }
    }
    
    public void serializeAll() {
        this.serialize();
        this.getChildren().forEach(Command::serializeAll);
    }
    
    @Override
    public void deserialize() {
        if (this instanceof ISerializableJson) {
            final ISerializableJson serializable = (ISerializableJson)this;
            final Path path = this.getSettingsPath();
            StringReader sr = null;
            JsonReader reader = null;
            if (Files.exists(path, new LinkOption[0])) {
                try {
                    sr = new StringReader(new String(Files.readAllBytes(path)));
                    reader = new JsonReader((Reader)sr);
                    reader.beginArray();
                    serializable.deserialize(reader);
                    reader.endArray();
                }
                catch (Throwable t) {
                    Wrapper.printStackTrace(t);
                    Globals.LOGGER.warn(String.format("Could not deserialize \"%s\": %s", this.getAbsoluteName(), t.getMessage()));
                }
                finally {
                    if (sr != null) {
                        sr.close();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    }
                    catch (IOException ex) {}
                }
            }
        }
    }
    
    public void deserializeAll() {
        this.deserialize();
        this.getChildren().forEach(Command::deserializeAll);
    }
    
    @Override
    public int compareTo(final Command o) {
        return String.CASE_INSENSITIVE_ORDER.compare(this.getAbsoluteName(), o.getAbsoluteName());
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof Command && this.getAbsoluteName().equalsIgnoreCase(((Command)o).getAbsoluteName());
    }
    
    @Override
    public int hashCode() {
        return this.getAbsoluteName().toLowerCase().hashCode();
    }
    
    @Override
    public String toString() {
        return this.getAbsoluteName();
    }
    
    static {
        SETTINGS_DIR = Wrapper.getFileManager().getMkConfigDirectory("settings");
    }
}
