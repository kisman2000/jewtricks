//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

public class MemberSrgData
{
    private final String obfOwner;
    private final String obfName;
    private final String srgOwner;
    private final String srgPkg;
    private final String srgName;
    private final boolean isClientOnly;
    
    public MemberSrgData(final String obfOwner, final String obfName, final String srgOwner, final String srgPkg, final String srgName, final boolean isClientOnly) {
        this.obfOwner = obfOwner;
        this.obfName = obfName;
        this.srgOwner = srgOwner;
        this.srgPkg = srgPkg;
        this.srgName = srgName;
        this.isClientOnly = isClientOnly;
    }
    
    public String getObfOwner() {
        return this.obfOwner;
    }
    
    public String getObfName() {
        return this.obfName;
    }
    
    public String getSrgOwner() {
        return this.srgOwner;
    }
    
    public String getSrgName() {
        return this.srgName;
    }
    
    public boolean isClientOnly() {
        return this.isClientOnly;
    }
    
    public String getSrgPkg() {
        return this.srgPkg;
    }
    
    public boolean contains(final String s) {
        return this.srgName.contains(s) || this.obfName.contains(s);
    }
}
