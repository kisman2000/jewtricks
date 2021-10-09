//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import com.google.common.collect.*;
import java.util.*;
import java.util.stream.*;

public class Streamables
{
    public static <T> Stream<T> enumerationStream(final Enumeration<T> enumeration) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize((Iterator<? extends T>)Iterators.forEnumeration((Enumeration)enumeration), 16), false);
    }
}
