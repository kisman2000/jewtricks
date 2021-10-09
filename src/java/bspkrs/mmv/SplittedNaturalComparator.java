//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

import java.util.*;
import bspkrs.mmv.version.*;

public class SplittedNaturalComparator implements Comparator<Object>
{
    private final String splitter;
    
    public SplittedNaturalComparator(final String splitter) {
        this.splitter = splitter;
    }
    
    @Override
    public int compare(final Object o1, final Object o2) {
        final String[] a = o1.toString().split(this.splitter);
        final String[] b = o2.toString().split(this.splitter);
        if (a.length != b.length) {
            return b.length - a.length;
        }
        final NaturalOrderComparator comp = new NaturalOrderComparator();
        for (int i = 0; i < a.length; ++i) {
            final int comparison = comp.compare(a[i], b[i]);
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }
}
