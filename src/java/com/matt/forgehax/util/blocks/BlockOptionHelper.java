//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.blocks;

import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.creativetab.*;
import java.util.regex.*;
import com.google.common.collect.*;
import java.util.*;
import com.matt.forgehax.util.blocks.exceptions.*;
import com.matt.forgehax.util.*;

public class BlockOptionHelper
{
    public static boolean isAir(final String name) {
        return Objects.equals(Blocks.AIR.getRegistryName(), new ResourceLocation(name));
    }
    
    public static boolean isAir(final int id) {
        return id == 0;
    }
    
    public static Collection<ItemStack> getAllBlocks(final Block block) {
        final NonNullList<ItemStack> list = (NonNullList<ItemStack>)NonNullList.create();
        if (block != null) {
            block.getSubBlocks((CreativeTabs)null, (NonNullList)list);
        }
        return Collections.unmodifiableCollection((Collection<? extends ItemStack>)list);
    }
    
    public static void getAllBlocksMatchingByUnlocalized(final Collection<BlockEntry> found, final String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher;
        Block.REGISTRY.forEach(block -> getAllBlocks(block).forEach(stack -> {
            matcher = pattern.matcher(stack.getDisplayName().toLowerCase());
            if (matcher.find()) {
                try {
                    found.add(new BlockEntry(block, stack.getMetadata(), false));
                }
                catch (BlockDoesNotExistException ex) {}
            }
        }));
    }
    
    public static Collection<BlockEntry> getAllBlocksMatchingByUnlocalized(final String regex) {
        final Collection<BlockEntry> map = (Collection<BlockEntry>)Sets.newHashSet();
        getAllBlocksMatchingByUnlocalized(map, regex);
        return map;
    }
    
    public static void getAllBlocksMatchingByLocalized(final Collection<BlockEntry> found, final String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher;
        Block.REGISTRY.forEach(block -> getAllBlocks(block).forEach(stack -> {
            matcher = pattern.matcher(stack.getDisplayName().replaceAll(" ", "_").toLowerCase());
            if (matcher.find()) {
                try {
                    found.add(new BlockEntry(block, stack.getMetadata(), false));
                }
                catch (BlockDoesNotExistException ex) {}
            }
        }));
    }
    
    public static Collection<BlockEntry> getAllBlocksMatchingByLocalized(final String regex) {
        final Collection<BlockEntry> map = (Collection<BlockEntry>)Sets.newHashSet();
        getAllBlocksMatchingByLocalized(map, regex);
        return map;
    }
    
    public static Collection<BlockEntry> getAllBlockMatching(final String regex) {
        final Collection<BlockEntry> map = (Collection<BlockEntry>)Sets.newHashSet();
        getAllBlocksMatchingByUnlocalized(map, regex);
        getAllBlocksMatchingByLocalized(map, regex);
        return map;
    }
    
    public static boolean isValidMetadataValue(final Block block, final int meta) {
        for (final ItemStack stack : getAllBlocks(block)) {
            if (stack.getMetadata() == meta) {
                return true;
            }
        }
        return false;
    }
    
    public static BlockData fromUniqueName(final String uniqueName) throws BlockDoesNotExistException, BadBlockEntryFormatException {
        final String[] split = uniqueName.split("::");
        if (split.length < 1) {
            throw new BadBlockEntryFormatException();
        }
        final String name = split[0];
        final int meta = SafeConverter.toInteger((split.length > 1) ? split[1] : Integer.valueOf(-1), -1);
        final Block block = Block.getBlockFromName(name);
        if (block == null) {
            throw new BlockDoesNotExistException(uniqueName + " is not a valid block");
        }
        final BlockData data = new BlockData();
        data.block = block;
        data.meta = meta;
        return data;
    }
    
    public static void requiresValidBlock(final Block block, final int metadataId) throws BlockDoesNotExistException {
        if (block == null || block.equals(Blocks.AIR)) {
            throw new BlockDoesNotExistException("Attempted to create entry for a non-existent block");
        }
        if (!isValidMetadataValue(block, metadataId)) {
            throw new BlockDoesNotExistException(String.format("Attempted to create entry for block \"%s\" with a invalid meta id of \"%d\"", block.getRegistryName().toString(), metadataId));
        }
    }
    
    public static class BlockData
    {
        public Block block;
        public int meta;
        
        public BlockData() {
            this.block = null;
            this.meta = -1;
        }
    }
}
