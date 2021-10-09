//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.command;

import com.google.common.base.*;
import com.matt.forgehax.util.command.exception.*;
import java.util.*;

public class CommandHelper
{
    private static final String[] EMPTY_STRING_ARRAY;
    public static final String MOD_PROPERTY_SEPARATOR = ":";
    
    public static String[] forward(final String[] args) {
        return (args.length > 0) ? Arrays.copyOfRange(args, 1, args.length) : CommandHelper.EMPTY_STRING_ARRAY;
    }
    
    public static String join(final String[] args, final String separator, final int startIndex, final int endIndex) {
        return joptsimple.internal.Strings.join((String[])Arrays.copyOfRange(args, startIndex, endIndex), Strings.nullToEmpty(separator));
    }
    
    public static String join(final String[] args, final String separator) {
        return join(args, separator, 0, args.length);
    }
    
    public static String toUniqueId(final String parent, final String child) {
        return makeParserFriendly(joptsimple.internal.Strings.isNullOrEmpty(parent) ? child : (parent + ":" + child));
    }
    
    public static String makeParserFriendly(final String string) {
        return string.replaceAll(" ", "_");
    }
    
    public static void requireArguments(final List<?> args, final int requiredArguments) throws CommandExecuteException {
        if (args.size() < requiredArguments) {
            throw new CommandExecuteException("Missing argument(s)");
        }
    }
    
    public static String[] translate(final String toProcess) {
        if (toProcess == null || toProcess.length() == 0) {
            return new String[0];
        }
        final int normal = 0;
        final int inQuote = 1;
        final int inDoubleQuote = 2;
        int state = 0;
        final StringTokenizer tok = new StringTokenizer(toProcess, "\"' ", true);
        final ArrayList<String> result = new ArrayList<String>();
        final StringBuilder current = new StringBuilder();
        boolean lastTokenHasBeenQuoted = false;
        while (tok.hasMoreTokens()) {
            final String nextTok = tok.nextToken();
            switch (state) {
                case 1: {
                    if ("'".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = 0;
                        continue;
                    }
                    current.append(nextTok);
                    continue;
                }
                case 2: {
                    if ("\"".equals(nextTok)) {
                        lastTokenHasBeenQuoted = true;
                        state = 0;
                        continue;
                    }
                    current.append(nextTok);
                    continue;
                }
                default: {
                    if ("'".equals(nextTok)) {
                        state = 1;
                    }
                    else if ("\"".equals(nextTok)) {
                        state = 2;
                    }
                    else if (" ".equals(nextTok)) {
                        if (lastTokenHasBeenQuoted || current.length() != 0) {
                            result.add(current.toString());
                            current.setLength(0);
                        }
                    }
                    else {
                        current.append(nextTok);
                    }
                    lastTokenHasBeenQuoted = false;
                    continue;
                }
            }
        }
        if (lastTokenHasBeenQuoted || current.length() != 0) {
            result.add(current.toString());
        }
        if (state == 1 || state == 2) {
            throw new RuntimeException("unbalanced quotes in " + toProcess);
        }
        return result.toArray(new String[result.size()]);
    }
    
    static {
        EMPTY_STRING_ARRAY = new String[0];
    }
}
