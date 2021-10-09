//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

import java.util.regex.*;
import java.util.*;

public class ExcData implements Comparable<ExcData>
{
    private final String srgOwner;
    private final String srgName;
    private final String descriptor;
    private final String[] exceptions;
    private final String[] parameters;
    private final String[] paramTypes;
    
    public ExcData(final String srgOwner, final String srgName, final String descriptor, final String[] exceptions, final String[] parameters) {
        this.srgOwner = srgOwner;
        this.srgName = srgName;
        this.descriptor = descriptor;
        this.exceptions = exceptions;
        this.paramTypes = splitMethodDesc(descriptor);
        this.parameters = parameters;
    }
    
    public ExcData(final String srgOwner, final String srgName, final String descriptor, final String[] exceptions, final boolean isStatic) {
        this.srgOwner = srgOwner;
        this.srgName = srgName;
        this.descriptor = descriptor;
        this.exceptions = exceptions;
        this.paramTypes = splitMethodDesc(descriptor);
        this.parameters = genParamNames(getSrgId(srgName), this.paramTypes, isStatic);
    }
    
    public String getSrgClassOwner() {
        return this.srgOwner;
    }
    
    public String getSrgMethodName() {
        return this.srgName;
    }
    
    public String getDescriptor() {
        return this.descriptor;
    }
    
    public String[] getExceptions() {
        return this.exceptions;
    }
    
    public String[] getParameters() {
        return this.parameters;
    }
    
    public String[] getParamTypes() {
        return this.paramTypes;
    }
    
    public boolean contains(final String s) {
        if (this.srgName.contains(s)) {
            return true;
        }
        for (final String param : this.parameters) {
            if (param.contains(s)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int compareTo(final ExcData o) {
        return this.srgName.compareTo(o.srgName);
    }
    
    public static String[] splitMethodDesc(final String desc) {
        final int beginIndex = desc.indexOf(40);
        final int endIndex = desc.lastIndexOf(41);
        if ((beginIndex == -1 && endIndex != -1) || (beginIndex != -1 && endIndex == -1)) {
            System.err.println(beginIndex);
            System.err.println(endIndex);
            throw new RuntimeException();
        }
        String x0;
        if (beginIndex == -1 && endIndex == -1) {
            x0 = desc;
        }
        else {
            x0 = desc.substring(beginIndex + 1, endIndex);
        }
        final Pattern pattern = Pattern.compile("\\[*L[^;]+;|\\[[ZBCSIFDJ]|[ZBCSIFDJ]");
        final Matcher matcher = pattern.matcher(x0);
        final ArrayList<String> listMatches = new ArrayList<String>();
        while (matcher.find()) {
            listMatches.add(matcher.group());
        }
        return listMatches.toArray(new String[listMatches.size()]);
    }
    
    public static String[] genParamNames(final String srgId, final String[] paramTypes, final boolean isStatic) {
        final boolean skip2 = paramTypes.length >= 4 && paramTypes[0].equals("Ljava/lang/String;") && paramTypes[1].equals('I') && paramTypes[0].equals(paramTypes[2]) && paramTypes[1].equals(paramTypes[3]);
        final String[] ret = new String[paramTypes.length];
        int idOffset = isStatic ? 0 : 1;
        if (skip2) {
            idOffset += 2;
        }
        for (int i = 0; i < paramTypes.length; ++i) {
            ret[i] = "p_" + srgId + "_" + (i + idOffset) + "_";
            if (paramTypes[i].equals("D") || paramTypes[i].equals("J")) {
                ++idOffset;
            }
        }
        return ret;
    }
    
    public static String getSrgId(final String srgName) {
        final Pattern pattern = Pattern.compile("func_(i?[0-9]+)_");
        final Matcher matcher = pattern.matcher(srgName);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return srgName;
    }
    
    @Override
    public String toString() {
        return String.format("  Owner: %s\n  SRG Name: %s\n  Descriptor: %s\n  Exceptions: %s\n  Parameters: %s\n  Param Types: %s", this.srgOwner, this.srgName, this.descriptor, Arrays.toString(this.exceptions), Arrays.toString(this.parameters), Arrays.toString(this.paramTypes));
    }
}
