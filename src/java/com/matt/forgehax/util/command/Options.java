//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import com.matt.forgehax.util.serialization.*;
import java.util.function.*;
import com.matt.forgehax.util.command.exception.*;
import com.matt.forgehax.util.console.*;
import java.util.*;
import java.io.*;
import com.google.gson.stream.*;

public class Options<E extends ISerializableJson> extends Command implements Collection<E>, ISerializableJson
{
    public static final String SUPPLIER = "Options.supplier";
    public static final String FACTORY = "Options.factory";
    public static final String DEFAULTS = "Options.defaults";
    private final Collection<E> contents;
    private final Function<String, E> factory;
    private final Collection<E> defaults;
    
    protected Options(final Map<String, Object> data) throws CommandBuildException {
        super((Map)data);
        try {
            final Supplier<Collection<E>> supplier = data.get("Options.supplier");
            Objects.requireNonNull(supplier, "Missing supplier");
            this.contents = supplier.get();
            this.factory = data.get("Options.factory");
            final Supplier<Collection<E>> defaults = data.get("Options.defaults");
            if (defaults != null) {
                (this.defaults = supplier.get()).addAll((Collection<? extends E>)defaults.get());
                this.contents.addAll((Collection<? extends E>)defaults.get());
            }
            else {
                this.defaults = (Collection<E>)Collections.emptyList();
            }
        }
        catch (Throwable t) {
            throw new CommandBuildException("Failed to build options", t);
        }
    }
    
    protected boolean preprocessor(final String[] args) {
        if (args.length > 0) {
            final String opt = args[0];
            if (opt.matches("-r|--reset")) {
                this.contents.clear();
                this.contents.addAll((Collection<? extends E>)this.defaults);
                ConsoleIO.write(this.getName() + " reset");
                return false;
            }
        }
        return true;
    }
    
    public Collection<E> contents() {
        return this.contents;
    }
    
    public E get(final Object o) {
        for (final E element : this) {
            if (Objects.equals(element, o)) {
                return element;
            }
        }
        return null;
    }
    
    public int size() {
        return this.contents.size();
    }
    
    public boolean isEmpty() {
        return this.contents.isEmpty();
    }
    
    public boolean contains(final Object o) {
        return this.contents.contains(o);
    }
    
    public Iterator<E> iterator() {
        return this.contents.iterator();
    }
    
    public Object[] toArray() {
        return this.contents.toArray();
    }
    
    public <T> T[] toArray(final T[] a) {
        return this.contents.toArray(a);
    }
    
    public boolean add(final E e) {
        return this.contents.add(e);
    }
    
    public boolean remove(final Object o) {
        return this.contents.remove(o);
    }
    
    public boolean containsAll(final Collection<?> c) {
        return this.contents.containsAll(c);
    }
    
    public boolean addAll(final Collection<? extends E> c) {
        return this.contents.addAll(c);
    }
    
    public boolean removeAll(final Collection<?> c) {
        return this.contents.removeAll(c);
    }
    
    public boolean retainAll(final Collection<?> c) {
        return this.contents.retainAll(c);
    }
    
    public void clear() {
        this.contents.clear();
    }
    
    public void serialize(final JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("data");
        writer.beginObject();
        for (final E element : this.contents) {
            writer.name(element.toString());
            element.serialize(writer);
        }
        writer.endObject();
        writer.endObject();
    }
    
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginObject();
        reader.nextName();
        reader.beginObject();
        this.clear();
        while (reader.hasNext()) {
            final String name = reader.nextName();
            final E element = this.factory.apply(name);
            if (element != null) {
                element.deserialize(reader);
                this.add(element);
            }
        }
        reader.endObject();
        reader.endObject();
    }
    
    public String toString() {
        return this.getAbsoluteName();
    }
}
