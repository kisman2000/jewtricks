//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import com.matt.forgehax.util.key.*;
import com.matt.forgehax.util.serialization.*;
import net.minecraft.client.settings.*;
import java.util.*;
import net.minecraftforge.fml.client.registry.*;
import org.lwjgl.input.*;
import com.google.common.base.*;
import com.matt.forgehax.util.command.exception.*;
import java.io.*;
import com.google.gson.stream.*;
import javax.annotation.*;
import com.matt.forgehax.util.command.callbacks.*;

public class CommandStub extends Command implements IKeyBind, ISerializableJson
{
    public static final String KEYBIND = "Command.keybind";
    public static final String KEYBIND_OPTIONS = "Command.keybind_options";
    private final KeyBinding bind;
    
    protected CommandStub(final Map<String, Object> data) throws CommandBuildException {
        super((Map)data);
        final Integer keyCode = data.getOrDefault("Command.keybind", -1);
        if (keyCode != -1) {
            ClientRegistry.registerKeyBinding(this.bind = new KeyBinding(this.getAbsoluteName(), (int)keyCode, "JewTricks"));
            final Boolean genOptions = data.getOrDefault("Command.keybind_options", true);
            if (genOptions) {
                this.parser.accepts("bind", "Bind to the given key").withRequiredArg();
                this.parser.accepts("unbind", "Sets bind to KEY_NONE");
                String key;
                int kc;
                final CommandExecuteException ex;
                this.processors.add(dt -> {
                    if (dt.hasOption("bind")) {
                        key = dt.getOptionAsString("bind").toUpperCase();
                        kc = Keyboard.getKeyIndex(key);
                        if (Keyboard.getKeyIndex(key) == 0) {
                            new CommandExecuteException(String.format("\"%s\" is not a valid key name", key));
                            throw ex;
                        }
                        else {
                            this.bind(kc);
                            this.serialize();
                            dt.write(String.format("Bound %s to key %s [code=%d]", this.getAbsoluteName(), key, kc));
                            dt.stopProcessing();
                        }
                    }
                    else if (dt.hasOption("unbind")) {
                        this.unbind();
                        this.serialize();
                        dt.write(String.format("Unbound %s", this.getAbsoluteName()));
                        dt.stopProcessing();
                    }
                    return;
                });
                this.processors.add(dt -> {
                    if (!dt.options().hasOptions() && dt.getArgumentCount() > 0) {
                        dt.write(String.format("Unknown command \"%s\"", Strings.nullToEmpty(dt.getArgumentAsString(0))));
                    }
                });
            }
        }
        else {
            this.bind = null;
        }
    }
    
    public void serialize(final JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("bind");
        if (this.bind != null) {
            writer.value((long)this.bind.getKeyCode());
        }
        else {
            writer.value(-1L);
        }
        writer.endObject();
    }
    
    public void deserialize(final JsonReader reader) throws IOException {
        reader.beginObject();
        reader.nextName();
        final int kc = reader.nextInt();
        if (kc > -1) {
            this.bind(kc);
        }
        reader.endObject();
    }
    
    public void bind(final int keyCode) {
        if (this.bind != null) {
            this.bind.setKeyCode(keyCode);
            KeyBinding.resetKeyBindingArrayAndHash();
        }
    }
    
    @Nullable
    public KeyBinding getBind() {
        return this.bind;
    }
    
    public void onKeyPressed() {
        this.invokeCallbacks(CallbackType.KEY_PRESSED, new CallbackData((Command)this));
    }
    
    public void onKeyDown() {
        this.invokeCallbacks(CallbackType.KEY_DOWN, new CallbackData((Command)this));
    }
}
