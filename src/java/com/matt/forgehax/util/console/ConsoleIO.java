//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.console;

import com.matt.forgehax.*;
import java.util.concurrent.atomic.*;
import joptsimple.internal.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.util.text.*;

public class ConsoleIO implements Globals
{
    public static final Style HEADING;
    private static final ThreadLocal<AtomicInteger> INDENTATION;
    private static final int MIN_INDENT = 1;
    
    private static AtomicInteger getOrCreate() {
        AtomicInteger count = ConsoleIO.INDENTATION.get();
        if (count == null) {
            count = new AtomicInteger(1);
            ConsoleIO.INDENTATION.set(count);
        }
        return count;
    }
    
    public static void start() {
        getOrCreate().set(1);
    }
    
    public static void write(final String msg, final Style style) {
        final String tab = Strings.repeat('>', Math.max(getOrCreate().get(), 1)) + " ";
        if (style == null) {
            Wrapper.printMessageNaked(tab, msg);
        }
        else {
            Wrapper.printMessageNaked(tab, msg, style);
        }
    }
    
    public static void write(final String msg) {
        write(msg, null);
    }
    
    public static void incrementIndent() {
        getOrCreate().incrementAndGet();
    }
    
    public static void decrementIndent() {
        getOrCreate().decrementAndGet();
    }
    
    public static int getIndents() {
        return getOrCreate().get();
    }
    
    public static void setIndents(final int indents) {
        getOrCreate().set(indents);
    }
    
    public static void finished() {
        ConsoleIO.INDENTATION.remove();
    }
    
    static {
        HEADING = new Style().setColor(TextFormatting.GRAY).setItalic(Boolean.valueOf(true));
        INDENTATION = new ThreadLocal<AtomicInteger>();
    }
}
