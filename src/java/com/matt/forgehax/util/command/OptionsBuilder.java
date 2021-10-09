//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import com.matt.forgehax.util.serialization.*;
import java.util.*;
import java.util.function.*;

public class OptionsBuilder<E extends ISerializableJson> extends BaseCommandBuilder<OptionsBuilder<E>, Options<E>>
{
    public OptionsBuilder<E> supplier(final Supplier<Collection<E>> supplier) {
        return (OptionsBuilder)this.insert("Options.supplier", (Object)supplier);
    }
    
    public OptionsBuilder<E> factory(final Function<String, E> factory) {
        return (OptionsBuilder)this.insert("Options.factory", (Object)factory);
    }
    
    public OptionsBuilder<E> defaults(final Supplier<Collection<E>> defaults) {
        return (OptionsBuilder)this.insert("Options.defaults", (Object)defaults);
    }
    
    public Options<E> build() {
        return (Options<E>)new Options(this.data);
    }
}
