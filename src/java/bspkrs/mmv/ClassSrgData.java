//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

public class ClassSrgData implements Comparable<ClassSrgData>
{
    private final String obfName;
    private final String srgName;
    private String srgPkgName;
    private final boolean isClientOnly;
    public static SortType sortType;
    
    public ClassSrgData(final String obfName, final String srgName, final String srgPkgName, final boolean isClientOnly) {
        this.obfName = obfName;
        this.srgName = srgName;
        this.srgPkgName = srgPkgName;
        this.isClientOnly = isClientOnly;
    }
    
    public String getObfName() {
        return this.obfName;
    }
    
    public String getSrgName() {
        return this.srgName;
    }
    
    public String getSrgPkgName() {
        return this.srgPkgName;
    }
    
    public ClassSrgData setSrgPkgName(final String pkg) {
        this.srgPkgName = pkg;
        return this;
    }
    
    public boolean isClientOnly() {
        return this.isClientOnly;
    }
    
    public String getFullyQualifiedSrgName() {
        return this.srgPkgName + "/" + this.srgName;
    }
    
    @Override
    public int compareTo(final ClassSrgData o) {
        if (ClassSrgData.sortType == SortType.PKG) {
            if (o != null) {
                return this.getFullyQualifiedSrgName().compareTo(o.getFullyQualifiedSrgName());
            }
            return 1;
        }
        else {
            if (o == null) {
                return 1;
            }
            if (this.obfName.length() != o.obfName.length()) {
                return this.obfName.length() - o.obfName.length();
            }
            return this.obfName.compareTo(o.obfName);
        }
    }
    
    public boolean contains(final String s) {
        return this.srgName.contains(s) || this.obfName.contains(s) || this.srgPkgName.contains(s);
    }
    
    static {
        ClassSrgData.sortType = SortType.PKG;
    }
    
    public enum SortType
    {
        PKG, 
        OBF;
    }
}
