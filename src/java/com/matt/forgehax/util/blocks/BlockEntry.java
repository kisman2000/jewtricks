//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.blocks;

import com.matt.forgehax.util.serialization.*;
import com.matt.forgehax.*;
import net.minecraft.block.*;
import com.google.common.collect.*;
import com.matt.forgehax.util.blocks.exceptions.*;
import javax.annotation.*;
import com.matt.forgehax.util.blocks.properties.*;
import java.util.*;
import java.io.*;
import com.google.gson.stream.*;
import net.minecraft.block.state.*;

public class BlockEntry implements ISerializableJson, Globals
{
    private final Map<Class<? extends IBlockProperty>, IBlockProperty> properties;
    private final String uniqueId;
    private final Block block;
    private final int meta;
    
    public BlockEntry(final String uniqueId) {
        this.properties = (Map<Class<? extends IBlockProperty>, IBlockProperty>)Maps.newHashMap();
        this.uniqueId = uniqueId;
        Block block;
        int meta;
        try {
            final BlockOptionHelper.BlockData data = BlockOptionHelper.fromUniqueName(uniqueId);
            block = data.block;
            meta = data.meta;
        }
        catch (Throwable t) {
            block = null;
            meta = -1;
        }
        this.block = block;
        this.meta = meta;
    }
    
    public BlockEntry(final Block block, int meta, final boolean check) throws BlockDoesNotExistException {
        this.properties = (Map<Class<? extends IBlockProperty>, IBlockProperty>)Maps.newHashMap();
        meta = Math.max(meta, 0);
        if (check) {
            BlockOptionHelper.requiresValidBlock(block, meta);
        }
        this.block = block;
        this.meta = ((BlockOptionHelper.getAllBlocks(block).size() > 1) ? meta : -1);
        this.uniqueId = this.getResourceName() + (this.isMetadata() ? ("::" + this.getMetadata()) : "");
    }
    
    public BlockEntry(final String name, final int meta) throws BlockDoesNotExistException {
        this(Block.getBlockFromName(name), meta, !BlockOptionHelper.isAir(name));
    }
    
    public BlockEntry(final int id, final int meta) throws BlockDoesNotExistException {
        this(Block.getBlockById(id), meta, !BlockOptionHelper.isAir(id));
    }
    
    protected void registerProperty(final IBlockProperty property) {
        this.properties.put(property.getClass(), property);
    }
    
    protected void unregisterProperty(final IBlockProperty property) {
        this.properties.remove(property.getClass());
    }
    
    public Collection<IBlockProperty> getProperties() {
        return Collections.unmodifiableCollection((Collection<? extends IBlockProperty>)this.properties.values());
    }
    
    public String getUniqueName() {
        return this.uniqueId;
    }
    
    public String getResourceName() {
        return (this.block != null) ? ((this.block.getRegistryName() != null) ? this.block.getRegistryName().toString() : this.block.toString()) : "";
    }
    
    public String getPrettyName() {
        return (this.block != null) ? (((this.block.getRegistryName() != null) ? this.block.getRegistryName().getPath() : this.block.toString()) + (this.isMetadata() ? (":" + this.meta) : "")) : "";
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public int getMetadata() {
        return this.meta;
    }
    
    public boolean isMetadata() {
        return this.meta > -1;
    }
    
    private <T extends IBlockProperty> T getProperty(@Nonnull final Class<T> clazz, final OpenMode mode) {
        switch (mode) {
            case READ: {
                return this.properties.getOrDefault(clazz, PropertyFactory.getImmutableInstance(clazz)).cast();
            }
            case WRITE: {
                final IBlockProperty property;
                return this.properties.computeIfAbsent(clazz, c -> {
                    property = PropertyFactory.newInstance(c);
                    this.registerProperty(property);
                    return property;
                }).cast();
            }
            default: {
                throw new IllegalArgumentException(String.format("No such property \"%s\" (Possibly not registered?)", clazz.getSimpleName()));
            }
        }
    }
    
    @Nonnull
    public <T extends IBlockProperty> T getReadableProperty(@Nonnull final Class<T> clazz) {
        return this.getProperty(clazz, OpenMode.READ);
    }
    
    @Nonnull
    public <T extends IBlockProperty> T getWritableProperty(@Nonnull final Class<T> clazz) {
        return this.getProperty(clazz, OpenMode.WRITE);
    }
    
    public void cleanupProperties() {
        for (final IBlockProperty property : this.properties.values()) {
            if (!property.isNecessary()) {
                this.unregisterProperty(property);
            }
        }
    }
    
    boolean isEqual(final Block block, final int meta) {
        return Objects.equals(this.getBlock(), block) && (!this.isMetadata() || this.getMetadata() == meta);
    }
    
    public String helpText() {
        final StringBuilder builder = new StringBuilder(this.getPrettyName());
        builder.append(" {");
        final Iterator<? extends IBlockProperty> it = this.getProperties().iterator();
        while (it.hasNext()) {
            final IBlockProperty option = (IBlockProperty)it.next();
            builder.append(option.toString());
            builder.append("=");
            builder.append(option.helpText());
            if (it.hasNext()) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }
    
    @Override
    public void serialize(final JsonWriter writer) throws IOException {
        this.cleanupProperties();
        writer.beginObject();
        for (final IBlockProperty property : this.properties.values()) {
            writer.name(property.toString());
            property.serialize(writer);
        }
        writer.endObject();
    }
    
    @Override
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            final String name = reader.nextName();
            final IBlockProperty property = PropertyFactory.newInstance(name);
            if (property != null) {
                property.deserialize(reader);
                this.registerProperty(property);
            }
            else {
                BlockEntry.LOGGER.warn(String.format("\"%s\" is not a valid property name", name));
            }
        }
        reader.endObject();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof BlockEntry) {
            return this.getUniqueName().compareTo(((BlockEntry)obj).getUniqueName()) == 0;
        }
        if (obj instanceof IBlockState) {
            return this.isEqual(((IBlockState)obj).getBlock(), ((IBlockState)obj).getBlock().getMetaFromState((IBlockState)obj));
        }
        return this.hashCode() == obj.hashCode();
    }
    
    @Override
    public int hashCode() {
        return this.getUniqueName().hashCode();
    }
    
    @Override
    public String toString() {
        return this.getUniqueName();
    }
    
    private enum OpenMode
    {
        READ, 
        WRITE;
    }
}
