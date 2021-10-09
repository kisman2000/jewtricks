//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

import java.util.*;
import java.io.*;

public class ExcFile
{
    public final Map<String, ExcData> srgMethodName2ExcData;
    public final Map<String, ExcData> srgParamName2ExcData;
    
    public ExcFile(final File f) throws IOException {
        this.srgMethodName2ExcData = new HashMap<String, ExcData>();
        this.srgParamName2ExcData = new HashMap<String, ExcData>();
        final Scanner in = new Scanner(new FileReader(f));
        try {
            while (in.hasNextLine()) {
                if (in.hasNext("#")) {
                    in.nextLine();
                }
                else {
                    in.useDelimiter("\\.");
                    final String srgOwner = in.next();
                    in.useDelimiter("\\(");
                    if (!in.hasNext()) {
                        if (!in.hasNextLine()) {
                            break;
                        }
                        in.nextLine();
                    }
                    final String srgName = in.next().substring(1);
                    in.useDelimiter("=");
                    final String descriptor = in.next();
                    in.useDelimiter("\\|");
                    final String excs = in.next().substring(1);
                    final String params = in.nextLine().substring(1);
                    final ExcData toAdd = new ExcData(srgOwner, srgName, descriptor, (excs.length() > 0) ? excs.split(",") : new String[0], (params.length() > 0) ? params.split(",") : new String[0]);
                    final ExcData existing = this.srgMethodName2ExcData.get(srgName);
                    if (existing != null && existing.getParameters().length >= toAdd.getParameters().length) {
                        continue;
                    }
                    this.srgMethodName2ExcData.put(srgName, toAdd);
                    for (final String parameter : toAdd.getParameters()) {
                        this.srgParamName2ExcData.put(parameter, toAdd);
                    }
                }
            }
        }
        finally {
            in.close();
        }
    }
}
