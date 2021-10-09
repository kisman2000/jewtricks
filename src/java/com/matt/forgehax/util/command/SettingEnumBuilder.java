//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import java.util.function.*;
import com.matt.forgehax.util.command.callbacks.*;
import com.matt.forgehax.util.typeconverter.*;
import java.util.*;
import org.apache.commons.lang3.*;
import net.minecraft.util.math.*;
import javax.annotation.*;

public class SettingEnumBuilder<E extends Enum<E>> extends BaseCommandBuilder<SettingEnumBuilder<E>, Setting<E>>
{
    public SettingEnumBuilder<E> changed(final Consumer<OnChangeCallback<E>> consumer) {
        this.getCallbacks(CallbackType.CHANGE).add(consumer);
        return this;
    }
    
    public SettingEnumBuilder<E> defaultTo(final E defaultValue) {
        return (SettingEnumBuilder<E>)((SettingEnumBuilder)this.insert("Setting.defaultValue", (Object)defaultValue)).convertFrom(defaultValue.getClass());
    }
    
    private SettingEnumBuilder<E> converter(final TypeConverter<E> converter) {
        return (SettingEnumBuilder<E>)((SettingEnumBuilder)this.insert("Setting.converter", (Object)converter)).comparator(converter.comparator());
    }
    
    private SettingEnumBuilder<E> comparator(final Comparator<E> comparator) {
        return (SettingEnumBuilder)this.insert("Setting.comparator", (Object)comparator);
    }
    
    private SettingEnumBuilder<E> min(final E minValue) {
        return (SettingEnumBuilder)this.insert("Setting.minvalue", (Object)minValue);
    }
    
    private SettingEnumBuilder<E> max(final E maxValue) {
        return (SettingEnumBuilder)this.insert("Setting.maxvalue", (Object)maxValue);
    }
    
    private SettingEnumBuilder<E> convertFrom(final Class<?> clazz) {
        TypeConverter<E> converter = TypeConverterRegistry.get(clazz);
        if (converter == null) {
            converter = new TypeConverter<E>() {
                @Override
                public String label() {
                    return clazz.getName();
                }
                
                @Override
                public Class<E> type() {
                    return (Class<E>)clazz;
                }
                
                @Override
                public E parse(final String value) {
                    final E[] values;
                    int index;
                    return Arrays.stream(this.type().getEnumConstants()).filter(e -> e.name().toLowerCase().contains(value.toLowerCase())).min(Comparator.comparing(e -> StringUtils.getLevenshteinDistance((CharSequence)e.name().toLowerCase(), (CharSequence)value.toLowerCase()))).orElseGet(() -> {
                        values = this.type().getEnumConstants();
                        try {
                            index = Integer.valueOf(value);
                            return values[MathHelper.clamp(index, 0, values.length - 1)];
                        }
                        catch (NumberFormatException e2) {
                            return values[0];
                        }
                    });
                }
                
                @Override
                public String toString(final E value) {
                    return value.name();
                }
                
                @Nullable
                @Override
                public Comparator<E> comparator() {
                    return Enum::compareTo;
                }
            };
        }
        E min;
        E max;
        try {
            final E[] constants = (E[])clazz.getEnumConstants();
            min = constants[0];
            max = constants[constants.length - 1];
        }
        catch (Throwable t) {
            min = null;
            max = null;
        }
        return this.converter(converter).comparator(converter.comparator()).min(min).max(max);
    }
    
    public Setting<E> build() {
        return (Setting<E>)new Setting(this.has("Command.requiredArgs") ? this.data : ((SettingEnumBuilder)this.requiredArgs(1)).data);
    }
}
