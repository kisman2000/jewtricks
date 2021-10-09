//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.gui;

import com.matt.forgehax.*;
import net.minecraft.client.gui.*;
import java.io.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.matt.forgehax.util.draw.*;
import com.matt.forgehax.util.color.*;
import java.util.*;
import com.mojang.realmsclient.gui.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.base.*;

public class ConsoleGui extends GuiScreen implements Globals
{
    private static ConsoleGui INSTANCE;
    public static ScaledResolution scaledRes;
    private String text;
    private boolean shouldtab;
    
    private ConsoleGui() {
        this.shouldtab = false;
        this.text = "";
    }
    
    public static ConsoleGui getInstance() {
        return (ConsoleGui.INSTANCE == null) ? (ConsoleGui.INSTANCE = new ConsoleGui()) : ConsoleGui.INSTANCE;
    }
    
    public void reset() {
        this.text = "";
        this.shouldtab = false;
        ConsoleGui.MC.displayGuiScreen((GuiScreen)null);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (this.shouldtab) {
            this.shouldtab = false;
        }
        if (keyCode == 1) {
            this.reset();
        }
        else if (keyCode == 14) {
            if (this.text.length() > 0) {
                this.text = this.text.substring(0, this.text.length() - 1);
            }
        }
        else if (keyCode == 28 || keyCode == 156) {
            this.handleCommand();
            this.reset();
        }
        else if (keyCode == 15) {
            this.shouldtab = true;
        }
        else if ((keyCode >= 2 && keyCode <= 13) || (keyCode >= 16 && keyCode <= 27) || (keyCode >= 30 && keyCode <= 40) || (keyCode >= 43 && keyCode <= 53) || (keyCode >= 71 && keyCode <= 83) || keyCode == 181 || keyCode == 55 || keyCode == 57) {
            this.text += typedChar;
        }
    }
    
    @SubscribeEvent
    public void onScreenUpdated(final GuiScreenEvent.InitGuiEvent.Post ev) {
        ConsoleGui.scaledRes = new ScaledResolution(ConsoleGui.MC);
    }
    
    public void handleCommand() {
        try {
            ConsoleGui.GLOBAL_COMMAND.run(CommandHelper.translate(this.text));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Command getCommand(final String name) {
        for (final Command cmd : ConsoleGui.GLOBAL_COMMAND.getChildren()) {
            if (cmd.getNameLowerCase().equalsIgnoreCase(name)) {
                return cmd;
            }
        }
        return null;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        try {
            SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6, 0, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3, 17, -2139062144);
            SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6 + 2, 0, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3 - 4, 15, Color.of(0, 0, 0, 200).toBuffer());
            SurfaceHelper.drawText(this.text, ConsoleGui.scaledRes.getScaledWidth() / 6 + 5, 5, Integer.MAX_VALUE);
            if (this.text.length() > 0) {
                final String[] split = this.text.split(" ");
                if (split == null || split[0] == null) {
                    return;
                }
                final ArrayList<String> names = new ArrayList<String>();
                for (final Command cmd : ConsoleGui.GLOBAL_COMMAND.getChildren()) {
                    if (names.size() > 5) {
                        break;
                    }
                    if (!cmd.getNameLowerCase().startsWith(split[0].toLowerCase())) {
                        continue;
                    }
                    names.add(cmd.getNameLowerCase());
                }
                if (names.size() == 0) {
                    SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6, 20, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3, 20, -2139062144);
                    SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6 + 2, 22, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3 - 4, 16, Color.of(0, 0, 0, 200).toBuffer());
                    SurfaceHelper.drawText("The command \"" + ChatFormatting.GREEN + this.text + ChatFormatting.WHITE + "\" is not valid.", ConsoleGui.scaledRes.getScaledWidth() / 6 + 5, 26, Integer.MAX_VALUE);
                }
                else {
                    final Command cmd2 = this.getCommand(split[0]);
                    if (cmd2 != null) {
                        if (split.length == 2 && !this.text.endsWith(" ")) {
                            if (cmd2.getChildren() != null) {
                                final ArrayList<Command> children = new ArrayList<Command>();
                                for (final Command childrencmd : cmd2.getChildren()) {
                                    if (childrencmd.getNameLowerCase().startsWith(split[1].toLowerCase())) {
                                        children.add(childrencmd);
                                    }
                                }
                                if (children.size() != 0) {
                                    if (this.shouldtab) {
                                        this.text = cmd2.getNameLowerCase() + " " + children.get(0).getNameLowerCase();
                                        this.shouldtab = false;
                                    }
                                    SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6, 20, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3, 20 + (children.size() - 1) * 15, -2139062144);
                                    SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6 + 2, 22, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3 - 4, 16 + (children.size() - 1) * 15, Color.of(0, 0, 0, 200).toBuffer());
                                    int i = 0;
                                    for (final Command ccmd : children) {
                                        final StringBuilder csb = new StringBuilder(ChatFormatting.GREEN.toString() + split[0] + " " + ccmd.getNameLowerCase());
                                        csb.insert(this.text.length() + 2, ChatFormatting.WHITE.toString());
                                        if (ccmd instanceof Setting) {
                                            final Setting s = (Setting)ccmd;
                                            if (s != null && s.get() != null) {
                                                csb.append(" (" + s.get() + ")");
                                            }
                                        }
                                        if (ccmd.getDescription() != null && !ccmd.getDescription().equals("")) {
                                            csb.append(" - " + ccmd.getDescription());
                                        }
                                        SurfaceHelper.drawText(csb.toString(), ConsoleGui.scaledRes.getScaledWidth() / 6 + 5, 26 + i * 15, Integer.MAX_VALUE);
                                        ++i;
                                    }
                                    return;
                                }
                            }
                            SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6, 20, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3, 20, -2139062144);
                            SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6 + 2, 22, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3 - 4, 16, Color.of(0, 0, 0, 200).toBuffer());
                            SurfaceHelper.drawText("The command \"" + ChatFormatting.GREEN + this.text + ChatFormatting.WHITE + "\" is not valid.", ConsoleGui.scaledRes.getScaledWidth() / 6 + 5, 26, Integer.MAX_VALUE);
                        }
                        else if (split.length == 1) {
                            int height = 35;
                            if (cmd2.getChildren() != null) {
                                height += (cmd2.getChildren().size() - 1) * 15;
                            }
                            SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6, 20, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3, height, -2139062144);
                            SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6 + 2, 22, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3 - 4, height - 4, Color.of(0, 0, 0, 200).toBuffer());
                            SurfaceHelper.drawText(ChatFormatting.GREEN + split[0] + ChatFormatting.WHITE + ((cmd2.getDescription() != null && !cmd2.getDescription().equals("")) ? (" - " + cmd2.getDescription()) : ""), ConsoleGui.scaledRes.getScaledWidth() / 6 + 5, 26, Integer.MAX_VALUE);
                            if (cmd2.getChildren() != null) {
                                int i = 0;
                                for (final Command childrencmd2 : cmd2.getChildren()) {
                                    if (this.shouldtab) {
                                        this.text = childrencmd2.getNameLowerCase();
                                        this.shouldtab = false;
                                        break;
                                    }
                                    final StringBuilder sb = new StringBuilder(ChatFormatting.GREEN.toString() + split[0] + " " + childrencmd2.getNameLowerCase());
                                    sb.insert(this.text.length() + 2, ChatFormatting.WHITE.toString());
                                    if (childrencmd2 instanceof Setting) {
                                        final Setting s = (Setting)childrencmd2;
                                        if (s != null && s.get() != null) {
                                            sb.append(" (" + s.get() + ")");
                                        }
                                    }
                                    if (childrencmd2.getDescription() != null && !childrencmd2.getDescription().equals("")) {
                                        sb.append(" - " + childrencmd2.getDescription());
                                    }
                                    SurfaceHelper.drawText(sb.toString(), ConsoleGui.scaledRes.getScaledWidth() / 6 + 5, 41 + i * 15, Integer.MAX_VALUE);
                                    ++i;
                                }
                            }
                        }
                    }
                    else {
                        if (this.shouldtab) {
                            this.text = names.get(0);
                            this.shouldtab = false;
                        }
                        SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6, 20, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3, 20 + (names.size() - 1) * 15, -2139062144);
                        SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6 + 2, 22, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3 - 4, 16 + (names.size() - 1) * 15, Color.of(0, 0, 0, 200).toBuffer());
                        int j = 0;
                        for (final String name : names) {
                            final StringBuilder sb2 = new StringBuilder(ChatFormatting.GREEN.toString() + name);
                            sb2.insert(this.text.length() + 2, ChatFormatting.WHITE.toString());
                            SurfaceHelper.drawText(sb2.toString(), ConsoleGui.scaledRes.getScaledWidth() / 6 + 5, 26 + j * 15, Integer.MAX_VALUE);
                            ++j;
                        }
                    }
                }
            }
            else {
                SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6, 20, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3, 20, -2139062144);
                SurfaceHelper.drawRect(ConsoleGui.scaledRes.getScaledWidth() / 6 + 2, 22, ConsoleGui.scaledRes.getScaledWidth() - ConsoleGui.scaledRes.getScaledWidth() / 3 - 4, 16, Color.of(0, 0, 0, 200).toBuffer());
                SurfaceHelper.drawText("Type a " + ChatFormatting.GREEN + "command " + ChatFormatting.WHITE + "to get help.", ConsoleGui.scaledRes.getScaledWidth() / 6 + 5, 26, Integer.MAX_VALUE);
            }
        }
        catch (Exception e) {
            Wrapper.printChat("Exception in ConsoleGui::drawScreen");
            e.printStackTrace();
        }
    }
    
    static {
        ConsoleGui.scaledRes = new ScaledResolution(ConsoleGui.MC);
    }
}
