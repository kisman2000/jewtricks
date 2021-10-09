//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.mod;

import com.matt.forgehax.util.command.*;
import java.util.function.*;
import com.matt.forgehax.util.command.callbacks.*;

public class ToggleMod extends BaseMod
{
    private final Setting<Boolean> enabled;
    private final Setting<Boolean> hidden;
    
    public ToggleMod(final Category category, final String modName, final boolean defaultValue, final String description) {
        super(category, modName, description);
        this.enabled = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("enabled")).description("Enables the mod")).defaultTo((Object)defaultValue).changed(cb -> {
            if (cb.getTo()) {
                this.start();
            }
            else {
                this.stop();
            }
            return;
        }).build();
        this.hidden = (Setting<Boolean>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("hidden")).description("Hides the mod from active mods list")).defaultTo((Object)false).build();
    }
    
    public final void toggle() {
        if (this.isEnabled()) {
            this.disable();
        }
        else {
            this.enable();
        }
    }
    
    public void enable() {
        this.enabled.set((Object)true);
    }
    
    public void disable() {
        this.enabled.set((Object)false);
    }
    
    protected StubBuilder buildStubCommand(final StubBuilder builder) {
        return builder.kpressed((Consumer)this::onBindPressed).kdown((Consumer)this::onBindKeyDown).bind();
    }
    
    public String getDebugDisplayText() {
        return super.getDebugDisplayText();
    }
    
    public boolean isHidden() {
        return false;
    }
    
    public final boolean isEnabled() {
        return (boolean)this.enabled.get();
    }
    
    protected void onLoad() {
    }
    
    protected void onUnload() {
    }
    
    protected void onEnabled() {
    }
    
    protected void onDisabled() {
    }
    
    public void onBindPressed(final CallbackData cb) {
        this.toggle();
    }
    
    protected void onBindKeyDown(final CallbackData cb) {
    }
}
