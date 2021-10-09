//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

import java.text.*;
import java.io.*;
import java.util.*;

public class ParamCsvFile
{
    private final File file;
    private final Map<String, ParamCsvData> srgParamName2ParamCsvData;
    private boolean isDirty;
    private String headerLine;
    
    public ParamCsvFile(final File file) throws IOException {
        this.file = file;
        this.srgParamName2ParamCsvData = new TreeMap<String, ParamCsvData>();
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
                final String side = in.nextLine().substring(1);
                this.srgParamName2ParamCsvData.put(srgName, new ParamCsvData(srgName, mcpName, (int)Integer.valueOf(side)));
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
            for (final ParamCsvData data : this.srgParamName2ParamCsvData.values()) {
                out.println(data.toCsv());
            }
            out.close();
            this.isDirty = false;
        }
    }
    
    public boolean hasCsvDataForKey(final String srgName) {
        return this.srgParamName2ParamCsvData.containsKey(srgName);
    }
    
    public ParamCsvData getCsvDataForKey(final String srgName) {
        return this.srgParamName2ParamCsvData.get(srgName);
    }
    
    public void updateCsvDataForKey(final String srgName, final ParamCsvData csvData) {
        this.srgParamName2ParamCsvData.put(srgName, csvData);
        this.isDirty = true;
    }
    
    public boolean isDirty() {
        return this.isDirty;
    }
    
    public void setIsDirty(final boolean bol) {
        this.isDirty = bol;
    }
}
