//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.name;

public class NameBuilder
{
    public static <E> IName<E> create(final E real, final E srg, final E obf) {
        if (srg == null && obf == null) {
            return createSingleName(real);
        }
        return (IName<E>)createMcMultiName(real, srg, (Object)obf);
    }
    
    public static <E> IName<E> createSingleName(final E real) {
        return (IName<E>)new SingleName(real);
    }
    
    public static <E> IName<E> createMcMultiName(final E real, final E srg, final E obf) {
        return (IName<E>)new McMultiName((Object)real, (Object)srg, (Object)obf);
    }
}
