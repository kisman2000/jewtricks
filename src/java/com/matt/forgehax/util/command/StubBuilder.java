//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import java.util.function.*;
import com.matt.forgehax.util.command.callbacks.*;

public class StubBuilder extends BaseCommandBuilder<StubBuilder, CommandStub>
{
    public StubBuilder kpressed(final Consumer<CallbackData> consumer) {
        this.getCallbacks(CallbackType.KEY_PRESSED).add(consumer);
        return this;
    }
    
    public StubBuilder kdown(final Consumer<CallbackData> consumer) {
        this.getCallbacks(CallbackType.KEY_DOWN).add(consumer);
        return this;
    }
    
    public StubBuilder bind(final int keyCode) {
        return (StubBuilder)this.insert("Command.keybind", (Object)keyCode);
    }
    
    public StubBuilder bind() {
        return this.bind(0);
    }
    
    public StubBuilder nobind() {
        return this.bind(-1);
    }
    
    public StubBuilder bindOptions(final boolean b) {
        return (StubBuilder)this.insert("Command.keybind_options", (Object)b);
    }
    
    public CommandStub build() {
        return new CommandStub(this.data);
    }
}
