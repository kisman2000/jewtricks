//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import com.matt.forgehax.util.serialization.*;

public class CommandBuilders
{
    private static final CommandBuilders INSTANCE;
    private final Command parent;
    
    public static CommandBuilders getInstance() {
        return CommandBuilders.INSTANCE;
    }
    
    public static CommandBuilders newInstance(final Command parent) {
        return new CommandBuilders(parent);
    }
    
    public CommandBuilders(final Command parent) {
        this.parent = parent;
    }
    
    public CommandBuilders() {
        this(null);
    }
    
    public CommandBuilder newCommandBuilder() {
        return (CommandBuilder)new CommandBuilder().parent(this.parent);
    }
    
    public StubBuilder newStubBuilder() {
        return (StubBuilder)new StubBuilder().parent(this.parent);
    }
    
    public <T> SettingBuilder<T> newSettingBuilder() {
        return (SettingBuilder<T>)new SettingBuilder().parent(this.parent);
    }
    
    public <T extends Enum<T>> SettingEnumBuilder<T> newSettingEnumBuilder() {
        return (SettingEnumBuilder<T>)new SettingEnumBuilder().parent(this.parent);
    }
    
    public <T extends ISerializableJson> OptionsBuilder<T> newOptionsBuilder() {
        return (OptionsBuilder<T>)new OptionsBuilder().parent(this.parent);
    }
    
    static {
        INSTANCE = new CommandBuilders();
    }
}
