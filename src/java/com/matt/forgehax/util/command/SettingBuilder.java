//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import com.matt.forgehax.*;
import java.util.function.*;
import com.matt.forgehax.util.command.callbacks.*;
import java.util.*;
import com.matt.forgehax.util.typeconverter.*;

public class SettingBuilder<E> extends BaseCommandBuilder<SettingBuilder<E>, Setting<E>> implements Globals
{
    public SettingBuilder<E> changed(final Consumer<OnChangeCallback<E>> consumer) {
        this.getCallbacks(CallbackType.CHANGE).add(consumer);
        return this;
    }
    
    public SettingBuilder<E> defaultTo(final E defaultValue) {
        return (SettingBuilder<E>)((SettingBuilder)this.insert("Setting.defaultValue", (Object)defaultValue)).type(defaultValue.getClass());
    }
    
    public SettingBuilder<E> converter(final TypeConverter<E> converter) {
        return (SettingBuilder<E>)((SettingBuilder)this.insert("Setting.converter", (Object)converter)).comparator(converter.comparator());
    }
    
    public SettingBuilder<E> comparator(final Comparator<E> comparator) {
        return (SettingBuilder)this.insert("Setting.comparator", (Object)comparator);
    }
    
    public SettingBuilder<E> min(final E minValue) {
        return (SettingBuilder)this.insert("Setting.minvalue", (Object)minValue);
    }
    
    public SettingBuilder<E> max(final E maxValue) {
        return (SettingBuilder)this.insert("Setting.maxvalue", (Object)maxValue);
    }
    
    public SettingBuilder<E> type(final Class<?> clazz) {
        if (this.has("Setting.converter")) {
            return this;
        }
        return this.converter(TypeConverterRegistry.get(clazz));
    }
    
    public SettingBuilder<E> customProcessor() {
        return (SettingBuilder)this.insert("Setting.defaultProcessor", (Object)false);
    }
    
    public Setting<E> build() {
        final Setting<E> ret = (Setting<E>)new Setting(this.has("Command.requiredArgs") ? this.data : ((SettingBuilder)this.requiredArgs(1)).data);
        System.out.println("--------------------");
        System.out.println("cmd.getAbsoluteName() > " + ret.getAbsoluteName());
        System.out.println("cmd.getDescription() > " + ret.getDescription());
        System.out.println("cmd.getName() > " + ret.getName());
        System.out.println("cmd.getOptionHelpText() > " + ret.getOptionHelpText());
        System.out.println("cmd.getPrintText() > " + ret.getPrintText());
        return ret;
    }
}
