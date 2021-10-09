//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

public class FieldSrgData extends MemberSrgData implements Comparable<FieldSrgData>
{
    public FieldSrgData(final String obfOwner, final String obfName, final String srgOwner, final String srgPkg, final String srgName, final boolean isClientOnly) {
        super(obfOwner, obfName, srgOwner, srgPkg, srgName, isClientOnly);
    }
    
    @Override
    public int compareTo(final FieldSrgData o) {
        if (o != null) {
            return this.getSrgName().compareTo(o.getSrgName());
        }
        return 1;
    }
}
