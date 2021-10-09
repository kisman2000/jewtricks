//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

public class CsvData implements Comparable<CsvData>
{
    private final String srgName;
    private String mcpName;
    private final int side;
    private String comment;
    private boolean needsQuoted;
    
    public CsvData(final String srgName, final String mcpName, final int side, final String comment) {
        this.srgName = srgName;
        this.mcpName = mcpName;
        this.side = side;
        if (comment.contains(",") || (!comment.isEmpty() && comment.charAt(0) == '\"' && comment.charAt(comment.length() - 1) == '\"')) {
            this.needsQuoted = true;
            if (comment.charAt(0) == '\"' && comment.charAt(comment.length() - 1) == '\"') {
                this.comment = comment.substring(1, comment.length() - 1);
            }
            else {
                this.comment = comment;
            }
        }
        else {
            this.comment = comment;
            this.needsQuoted = false;
        }
    }
    
    public String toCsv() {
        return this.srgName + "," + this.mcpName + "," + this.side + "," + (this.needsQuoted ? ("\"" + this.comment + "\"") : this.comment);
    }
    
    public String getSrgName() {
        return this.srgName;
    }
    
    public String getMcpName() {
        return this.mcpName;
    }
    
    public CsvData setMcpName(final String mcpName) {
        this.mcpName = mcpName;
        return this;
    }
    
    public int getSide() {
        return this.side;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public CsvData setComment(final String comment) {
        this.comment = comment;
        return this;
    }
    
    @Override
    public int compareTo(final CsvData o) {
        if (o != null) {
            return this.srgName.compareTo(o.srgName);
        }
        return 1;
    }
    
    public boolean contains(final String s) {
        return this.mcpName.contains(s) || this.comment.contains(s);
    }
}
