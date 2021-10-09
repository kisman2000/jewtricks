//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

import java.io.*;
import java.util.*;

public class StaticMethodsFile
{
    private final File file;
    public List<String> staticMethods;
    
    public StaticMethodsFile(final File file) throws IOException {
        this.file = file;
        this.staticMethods = new ArrayList<String>();
        this.readFromFile();
    }
    
    public void readFromFile() throws IOException {
        final Scanner in = new Scanner(new BufferedReader(new FileReader(this.file)));
        try {
            while (in.hasNextLine()) {
                this.staticMethods.add(in.nextLine());
            }
        }
        finally {
            in.close();
        }
    }
    
    public boolean contains(final String srgName) {
        return this.staticMethods.contains(srgName);
    }
}
