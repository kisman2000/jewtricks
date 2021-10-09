//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

public class ParamCsvData implements Comparable<ParamCsvData>
{
    private final String srgName;
    private String mcpName;
    private final int side;
    
    public ParamCsvData(final String srgName, final String mcpName, final int side) {
        this.srgName = srgName;
        this.mcpName = mcpName;
        this.side = side;
    }
    
    public String toCsv() {
        return this.srgName + "," + this.mcpName + "," + this.side;
    }
    
    public String getSrgName() {
        return this.srgName;
    }
    
    public String getMcpName() {
        return this.mcpName;
    }
    
    public ParamCsvData setMcpName(final String mcpName) {
        this.mcpName = mcpName;
        return this;
    }
    
    public int getSide() {
        return this.side;
    }
    
    @Override
    public int compareTo(final ParamCsvData o) {
        if (o != null) {
            return this.srgName.compareTo(o.srgName);
        }
        return 1;
    }
    
    public boolean contains(final String s) {
        return this.mcpName.contains(s);
    }
}
