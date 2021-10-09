//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entry;

import com.matt.forgehax.util.serialization.*;
import com.google.common.collect.*;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import com.google.gson.stream.*;

public class CustomMessageEntry implements ISerializableJson
{
    private final UUID player;
    private final List<MessageEntry> messages;
    
    public CustomMessageEntry(final UUID name) {
        this.messages = (List<MessageEntry>)Lists.newCopyOnWriteArrayList();
        this.player = name;
    }
    
    public CustomMessageEntry(final String uuid) {
        this(UUID.fromString(uuid));
    }
    
    public UUID getPlayer() {
        return this.player;
    }
    
    public List<MessageEntry> getMessages() {
        return Collections.unmodifiableList((List<? extends MessageEntry>)this.messages);
    }
    
    public MessageEntry getEntry(final UUID owner) {
        for (final MessageEntry entry : this.messages) {
            if (entry.getOwner().equals(owner)) {
                return entry;
            }
        }
        return null;
    }
    
    public boolean containsEntry(final UUID owner) {
        return this.getEntry(owner) != null;
    }
    
    public void addMessage(final UUID owner, final String message) {
        MessageEntry entry = this.getEntry(owner);
        if (entry == null) {
            entry = new MessageEntry(owner);
            this.messages.add(entry);
        }
        entry.setMessage(message);
    }
    
    protected MessageEntry getRandom() {
        return this.messages.get(ThreadLocalRandom.current().nextInt(this.messages.size()));
    }
    
    public String getRandomMessage() {
        return this.getRandom().getMessage();
    }
    
    public int getSize() {
        return this.messages.size();
    }
    
    public void setSize(final int size) {
        while (this.messages.size() > size) {
            this.messages.remove(this.getRandom());
        }
    }
    
    @Override
    public void serialize(final JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("messages");
        writer.beginArray();
        for (final MessageEntry entry : this.messages) {
            writer.beginObject();
            writer.name(entry.toString());
            entry.serialize(writer);
            writer.endObject();
        }
        writer.endArray();
        writer.endObject();
    }
    
    @Override
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            final String nextName = reader.nextName();
            switch (nextName) {
                case "messages": {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        reader.beginObject();
                        final MessageEntry entry = new MessageEntry(UUID.fromString(reader.nextName()));
                        entry.deserialize(reader);
                        this.messages.add(entry);
                        reader.endObject();
                    }
                    reader.endArray();
                    continue;
                }
            }
        }
        reader.endObject();
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj == this || (obj instanceof CustomMessageEntry && this.player.equals(((CustomMessageEntry)obj).getPlayer())) || (obj instanceof UUID && this.player.equals(obj));
    }
    
    @Override
    public int hashCode() {
        return this.player.hashCode();
    }
    
    @Override
    public String toString() {
        return this.player.toString();
    }
    
    public static class MessageEntry implements ISerializableJson
    {
        private final UUID owner;
        private String message;
        
        public MessageEntry(final UUID owner) {
            this.owner = owner;
        }
        
        public UUID getOwner() {
            return this.owner;
        }
        
        public String getMessage() {
            return this.message;
        }
        
        public void setMessage(final String message) {
            this.message = message;
        }
        
        @Override
        public void serialize(final JsonWriter writer) throws IOException {
            writer.beginObject();
            writer.name("msg");
            writer.value(this.message);
            writer.endObject();
        }
        
        @Override
        public void deserialize(final JsonReader reader) throws IOException {
            reader.beginObject();
            while (reader.hasNext()) {
                final String nextName = reader.nextName();
                switch (nextName) {
                    case "msg": {
                        this.setMessage(reader.nextString());
                        continue;
                    }
                }
            }
            reader.endObject();
        }
        
        @Override
        public boolean equals(final Object obj) {
            return (obj instanceof MessageEntry && this.owner.equals(((MessageEntry)obj).owner)) || (obj instanceof UUID && this.owner.equals(obj));
        }
        
        @Override
        public int hashCode() {
            return this.owner.hashCode();
        }
        
        @Override
        public String toString() {
            return this.owner.toString();
        }
    }
}
