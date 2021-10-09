//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import com.matt.forgehax.util.serialization.*;
import com.matt.forgehax.util.typeconverter.*;
import com.matt.forgehax.util.command.exception.*;
import com.matt.forgehax.util.*;
import com.matt.forgehax.util.console.*;
import com.matt.forgehax.util.command.callbacks.*;
import javax.annotation.*;
import java.util.*;
import java.io.*;
import com.google.gson.stream.*;

public class Setting<E> extends Command implements ISerializableJson
{
    public static final String DEFAULTVALUE = "Setting.defaultValue";
    public static final String CONVERTER = "Setting.converter";
    public static final String COMPARATOR = "Setting.comparator";
    public static final String MINVALUE = "Setting.minvalue";
    public static final String MAXVALUE = "Setting.maxvalue";
    public static final String RESETAUTOGEN = "Setting.resetAutoGen";
    public static final String DEFAULTPROCESSOR = "Setting.defaultProcessor";
    private final E defaultValue;
    private final TypeConverter<E> converter;
    private final Comparator<E> comparator;
    private final E minValue;
    private final E maxValue;
    private E value;
    
    protected Setting(final Map<String, Object> data) throws CommandBuildException {
        super((Map)data);
        try {
            Objects.requireNonNull(this.converter = data.get("Setting.converter"), "Setting requires converter");
            this.defaultValue = (E)data.get("Setting.defaultValue");
            this.comparator = data.get("Setting.comparator");
            this.minValue = (E)data.get("Setting.minvalue");
            this.maxValue = (E)data.get("Setting.maxvalue");
            final Boolean defaultProcessor = data.getOrDefault("Setting.defaultProcessor", true);
            if (defaultProcessor) {
                final Object arg;
                this.processors.add(in -> {
                    in.requiredArguments(1);
                    arg = in.getArgument(0);
                    if (arg != null) {
                        this.rawSet(String.valueOf(arg));
                        this.serialize();
                        in.markSuccess(new ExecuteData.State[0]);
                    }
                    else {
                        in.markFailed(new ExecuteData.State[0]);
                    }
                    return;
                });
            }
            final Boolean resetAutoGen = data.getOrDefault("Setting.resetAutoGen", true);
            if (resetAutoGen) {
                this.parser.acceptsAll((List)Arrays.asList("r", "reset"), "Sets the command to its default value");
            }
            this.set(this.defaultValue, true);
        }
        catch (Throwable t) {
            throw new CommandBuildException("Failed to build setting", t);
        }
    }
    
    public E get() {
        return this.value;
    }
    
    public E getMin() {
        return this.minValue;
    }
    
    public E getMax() {
        return this.maxValue;
    }
    
    public E getDefault() {
        return this.defaultValue;
    }
    
    @Nonnull
    public Class<?> getType() {
        return this.converter.type();
    }
    
    public boolean getAsBoolean() {
        return SafeConverter.toBoolean(this.get());
    }
    
    public byte getAsByte() {
        return SafeConverter.toByte(this.get());
    }
    
    public char getAsCharacter() {
        return SafeConverter.toCharacter(this.get());
    }
    
    public double getAsDouble() {
        return SafeConverter.toDouble(this.get());
    }
    
    public float getAsFloat() {
        return SafeConverter.toFloat(this.get());
    }
    
    public int getAsInteger() {
        return SafeConverter.toInteger(this.get());
    }
    
    public long getAsLong() {
        return SafeConverter.toLong(this.get());
    }
    
    public short getAsShort() {
        return SafeConverter.toShort(this.get());
    }
    
    public String getAsString() {
        return this.converter.toString(this.get());
    }
    
    public boolean set(E value, final boolean silent) {
        if (this.comparator != null && value != null && this.value != null) {
            if (this.minValue != null && this.comparator.compare(value, this.minValue) < 0) {
                value = this.minValue;
            }
            else if (this.maxValue != null && this.comparator.compare(value, this.maxValue) > 0) {
                value = this.maxValue;
            }
        }
        if (!Objects.equals(this.get(), value)) {
            if (!silent) {
                ConsoleIO.write(String.format("%s = %s", this.getAbsoluteName(), this.converter.toStringSafe(value)));
                final OnChangeCallback<E> cb = (OnChangeCallback<E>)new OnChangeCallback((Command)this, this.get(), (Object)value);
                this.invokeCallbacks(CallbackType.CHANGE, (CallbackData)cb);
                if (cb.isCanceled()) {
                    return false;
                }
            }
            this.value = value;
            return true;
        }
        return false;
    }
    
    public boolean set(final E value) {
        return this.set(value, false);
    }
    
    public boolean rawSet(final String value, final boolean silent) {
        return this.set(this.converter.parseSafe(value), silent);
    }
    
    public boolean rawSet(final String value) {
        return this.rawSet(value, false);
    }
    
    public boolean reset() {
        return this.set(this.defaultValue);
    }
    
    public TypeConverter<E> getConverter() {
        return this.converter;
    }
    
    public String getPrintText() {
        return this.getName() + " = " + this.getAsString() + " - " + this.getDescription();
    }
    
    public boolean addChild(@Nonnull final Command child) {
        throw new UnsupportedOperationException("Command::addChild is not supported for a Setting type");
    }
    
    public boolean removeChild(@Nonnull final Command child) {
        return false;
    }
    
    @Nullable
    public Command getChild(final String name) {
        return null;
    }
    
    public Collection<Command> getChildren() {
        return (Collection<Command>)Collections.emptySet();
    }
    
    public void getChildrenDeep(final Collection<Command> all) {
    }
    
    public Collection<Command> getChildrenDeep() {
        return (Collection<Command>)Collections.emptySet();
    }
    
    protected boolean preprocessor(final String[] args) {
        if (args.length > 0) {
            final String opt = args[0];
            if (opt.matches("-r|--reset")) {
                this.reset();
                this.serialize();
                return false;
            }
        }
        return true;
    }
    
    public void serialize(final JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("value");
        writer.value(this.getAsString());
        writer.endObject();
    }
    
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginObject();
        reader.nextName();
        this.rawSet(reader.nextString(), true);
        reader.endObject();
    }
}
