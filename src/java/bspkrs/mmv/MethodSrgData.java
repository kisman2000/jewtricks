//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

public class MethodSrgData extends MemberSrgData implements Comparable<MethodSrgData>
{
    private final String obfDescriptor;
    private final String srgDescriptor;
    
    public MethodSrgData(final String obfOwner, final String obfName, final String obfDescriptor, final String srgOwner, final String srgPkg, final String srgName, final String srgDescriptor, final boolean isClientOnly) {
        super(obfOwner, obfName, srgOwner, srgPkg, srgName, isClientOnly);
        this.obfDescriptor = obfDescriptor;
        this.srgDescriptor = srgDescriptor;
    }
    
    public String getObfDescriptor() {
        return this.obfDescriptor;
    }
    
    public String getSrgDescriptor() {
        return this.srgDescriptor;
    }
    
    public int compareTo(final MethodSrgData o) {
        if (o != null) {
            return this.getSrgName().compareTo(o.getSrgName());
        }
        return 1;
    }
}
