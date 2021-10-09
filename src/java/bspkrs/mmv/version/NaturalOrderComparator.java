//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv.version;

import java.util.*;

public class NaturalOrderComparator implements Comparator<Object>
{
    int compareRight(final String a, final String b) {
        int bias = 0;
        int ia = 0;
        int ib = 0;
        while (true) {
            final char ca = charAt(a, ia);
            final char cb = charAt(b, ib);
            if (!Character.isDigit(ca) && !Character.isDigit(cb)) {
                return bias;
            }
            if (!Character.isDigit(ca)) {
                return -1;
            }
            if (!Character.isDigit(cb)) {
                return 1;
            }
            if (ca < cb) {
                if (bias == 0) {
                    bias = -1;
                }
            }
            else if (ca > cb) {
                if (bias == 0) {
                    bias = 1;
                }
            }
            else if (ca == '\0' && cb == '\0') {
                return bias;
            }
            ++ia;
            ++ib;
        }
    }
    
    @Override
    public int compare(final Object o1, final Object o2) {
        final String a = o1.toString();
        final String b = o2.toString();
        int ia = 0;
        int ib = 0;
        int nza = 0;
        int nzb = 0;
        while (true) {
            nzb = (nza = 0);
            char ca = charAt(a, ia);
            char cb = charAt(b, ib);
            while (Character.isSpaceChar(ca) || ca == '0') {
                if (ca == '0') {
                    ++nza;
                }
                else {
                    nza = 0;
                }
                ca = charAt(a, ++ia);
            }
            while (Character.isSpaceChar(cb) || cb == '0') {
                if (cb == '0') {
                    ++nzb;
                }
                else {
                    nzb = 0;
                }
                cb = charAt(b, ++ib);
            }
            final int result;
            if (Character.isDigit(ca) && Character.isDigit(cb) && (result = this.compareRight(a.substring(ia), b.substring(ib))) != 0) {
                return result;
            }
            if (ca == '\0' && cb == '\0') {
                return nza - nzb;
            }
            if (ca < cb) {
                return -1;
            }
            if (ca > cb) {
                return 1;
            }
            ++ia;
            ++ib;
        }
    }
    
    static char charAt(final String s, final int i) {
        if (i >= s.length()) {
            return '\0';
        }
        return s.charAt(i);
    }
}
