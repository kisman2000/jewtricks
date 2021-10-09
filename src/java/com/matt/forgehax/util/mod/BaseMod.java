//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.mod;

import com.matt.forgehax.*;
import fuck.you.jewtricks.base.*;
import java.util.function.*;
import net.minecraftforge.common.*;
import java.util.*;
import joptsimple.internal.*;
import com.matt.forgehax.util.command.*;
import com.matt.forgehax.util.command.callbacks.*;

public abstract class BaseMod implements Globals
{
    private final String modName;
    private final String modDescription;
    private final Category category;
    protected final Command stubCommand;
    private boolean registered;
    
    public BaseMod(final Category category, final String name, final String desc) {
        this.registered = false;
        this.modName = name;
        this.modDescription = desc;
        this.category = category;
        this.stubCommand = (Command)this.buildStubCommand((StubBuilder)((StubBuilder)((StubBuilder)Wrapper.getGlobalCommand().builders().newStubBuilder().name(name)).description(desc)).processor((Consumer)this::onProcessCommand)).build();
    }
    
    public BaseMod(final Category category, final String name) {
        this(category, name, "");
    }
    
    public final void load() {
        if (this.stubCommand != null) {
            this.stubCommand.deserializeAll();
        }
        if (this.isEnabled()) {
            this.start();
        }
        this.onLoad();
    }
    
    public final void unload() {
        this.stop();
        this.onUnload();
        if (this.stubCommand != null) {
            this.stubCommand.serializeAll();
            this.stubCommand.leaveParent();
        }
    }
    
    protected final void start() {
        if (this.register()) {
            this.onEnabled();
            BaseMod.LOGGER.info(String.format("%s enabled", this.getModName()));
        }
    }
    
    protected final void stop() {
        if (this.unregister()) {
            this.onDisabled();
            BaseMod.LOGGER.info(String.format("%s disabled", this.getModName()));
        }
    }
    
    public void enable() {
        this.start();
    }
    
    public void disable() {
        this.stop();
    }
    
    public final String getModName() {
        return this.modName;
    }
    
    public final String getModDescription() {
        return this.modDescription;
    }
    
    public Category getModCategory() {
        return this.category;
    }
    
    public Command getCommandStub() {
        return this.stubCommand;
    }
    
    public final boolean isRegistered() {
        return this.registered;
    }
    
    public final boolean register() {
        if (!this.registered) {
            MinecraftForge.EVENT_BUS.register((Object)this);
            return this.registered = true;
        }
        return false;
    }
    
    public final boolean unregister() {
        if (this.registered) {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
            this.registered = false;
            return true;
        }
        return false;
    }
    
    protected StubBuilder buildStubCommand(final StubBuilder builder) {
        return builder;
    }
    
    public final <T extends Command> T getCommand(final String commandName) {
        try {
            return (T)this.stubCommand.getChild(commandName);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public final Setting<?> getSetting(final String settingName) {
        return (Setting<?>)this.getCommand(settingName);
    }
    
    public final Collection<Command> getCommands() {
        if (this.stubCommand != null) {
            return (Collection<Command>)this.stubCommand.getChildren();
        }
        return (Collection<Command>)Collections.emptyList();
    }
    
    public abstract boolean isHidden();
    
    public abstract boolean isEnabled();
    
    private void writeChildren(final StringBuilder builder, final Command command, final boolean deep, final String append) {
        final boolean invalid;
        String app;
        command.getChildren().forEach(child -> {
            invalid = Strings.isNullOrEmpty(append);
            if (!invalid) {
                builder.append(append);
                builder.append(' ');
            }
            builder.append(child.getPrintText());
            builder.append('\n');
            if (deep) {
                app = (invalid ? "" : append);
                this.writeChildren(builder, child, deep, app + ">");
            }
        });
    }
    
    protected void onProcessCommand(final ExecuteData data) {
        if (data.getArgumentCount() == 0 && !data.options().hasOptions()) {
            final StringBuilder builder = new StringBuilder();
            this.writeChildren(builder, this.getCommandStub(), true, "");
            data.write(builder.toString());
        }
    }
    
    protected abstract void onLoad();
    
    protected abstract void onUnload();
    
    protected abstract void onEnabled();
    
    protected abstract void onDisabled();
    
    protected abstract void onBindPressed(final CallbackData p0);
    
    protected abstract void onBindKeyDown(final CallbackData p0);
    
    public String getDisplayText() {
        return this.getModName();
    }
    
    public String getDebugDisplayText() {
        return this.getDisplayText();
    }
    
    @Override
    public String toString() {
        return this.getModName() + ": " + this.getModDescription();
    }
}
