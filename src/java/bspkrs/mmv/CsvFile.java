//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

import java.text.*;
import java.io.*;
import java.util.*;

public class CsvFile
{
    private final File file;
    private final Map<String, CsvData> srgMemberName2CsvData;
    private boolean isDirty;
    private String headerLine;
    
    public CsvFile(final File file) throws IOException {
        this.file = file;
        this.srgMemberName2CsvData = new TreeMap<String, CsvData>();
        this.readFromFile();
        this.isDirty = false;
    }
    
    public void readFromFile() throws IOException {
        final Scanner in = new Scanner(new BufferedReader(new FileReader(this.file)));
        try {
            in.useDelimiter(",");
            this.headerLine = in.nextLine();
            while (in.hasNextLine()) {
                final String srgName = in.next();
                final String mcpName = in.next();
                final String side = in.next();
                final String comment = in.nextLine().substring(1);
                this.srgMemberName2CsvData.put(srgName, new CsvData(srgName, mcpName, (int)Integer.valueOf(side), comment));
            }
        }
        finally {
            in.close();
        }
    }
    
    public void writeToFile() throws IOException {
        if (this.isDirty) {
            if (this.file.exists()) {
                final File fileBak = new File(this.file.getAbsolutePath() + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".bak");
                this.file.renameTo(fileBak);
            }
            this.file.createNewFile();
            final PrintWriter out = new PrintWriter(new FileWriter(this.file));
            out.println(this.headerLine);
            for (final CsvData data : this.srgMemberName2CsvData.values()) {
                out.println(data.toCsv());
            }
            out.close();
            this.isDirty = false;
        }
    }
    
    public boolean hasCsvDataForKey(final String srgName) {
        return this.srgMemberName2CsvData.containsKey(srgName);
    }
    
    public CsvData getCsvDataForKey(final String srgName) {
        return this.srgMemberName2CsvData.get(srgName);
    }
    
    public void updateCsvDataForKey(final String srgName, final CsvData csvData) {
        this.srgMemberName2CsvData.put(srgName, csvData);
        this.isDirty = true;
    }
    
    public boolean isDirty() {
        return this.isDirty;
    }
    
    public void setIsDirty(final boolean bol) {
        this.isDirty = bol;
    }
}
