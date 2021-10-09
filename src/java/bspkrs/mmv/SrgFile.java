//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

import java.util.*;
import java.io.*;

public class SrgFile
{
    public final Map<String, ClassSrgData> srgClassName2ClassData;
    public final Map<String, Set<ClassSrgData>> srgPkg2ClassDataSet;
    public final Map<String, FieldSrgData> srgFieldName2FieldData;
    public final Map<String, MethodSrgData> srgMethodName2MethodData;
    public final Map<ClassSrgData, Set<MethodSrgData>> class2MethodDataSet;
    public final Map<ClassSrgData, Set<FieldSrgData>> class2FieldDataSet;
    public final Map<String, ClassSrgData> srgMethodName2ClassData;
    public final Map<String, ClassSrgData> srgFieldName2ClassData;
    
    public static String getLastComponent(final String s) {
        final String[] parts = s.split("/");
        return parts[parts.length - 1];
    }
    
    public SrgFile(final File f, final ExcFile excFile, final StaticMethodsFile staticMethods) throws IOException {
        this.srgClassName2ClassData = new TreeMap<String, ClassSrgData>();
        this.srgPkg2ClassDataSet = new TreeMap<String, Set<ClassSrgData>>();
        this.srgFieldName2FieldData = new TreeMap<String, FieldSrgData>();
        this.srgMethodName2MethodData = new TreeMap<String, MethodSrgData>();
        this.class2MethodDataSet = new TreeMap<ClassSrgData, Set<MethodSrgData>>();
        this.class2FieldDataSet = new TreeMap<ClassSrgData, Set<FieldSrgData>>();
        this.srgMethodName2ClassData = new TreeMap<String, ClassSrgData>();
        this.srgFieldName2ClassData = new TreeMap<String, ClassSrgData>();
        final Scanner in = new Scanner(new BufferedReader(new FileReader(f)));
        try {
            while (in.hasNextLine()) {
                if (in.hasNext("CL:")) {
                    in.next();
                    final String obf = in.next();
                    final String deobf = in.next();
                    final String srgName = getLastComponent(deobf);
                    final String pkgName = deobf.substring(0, deobf.lastIndexOf(47));
                    final ClassSrgData classData = new ClassSrgData(obf, srgName, pkgName, in.hasNext("#C"));
                    if (!this.srgPkg2ClassDataSet.containsKey(pkgName)) {
                        this.srgPkg2ClassDataSet.put(pkgName, new TreeSet<ClassSrgData>());
                    }
                    this.srgPkg2ClassDataSet.get(pkgName).add(classData);
                    this.srgClassName2ClassData.put(pkgName + "/" + srgName, classData);
                    if (!this.class2MethodDataSet.containsKey(classData)) {
                        this.class2MethodDataSet.put(classData, new TreeSet<MethodSrgData>());
                    }
                    if (this.class2FieldDataSet.containsKey(classData)) {
                        continue;
                    }
                    this.class2FieldDataSet.put(classData, new TreeSet<FieldSrgData>());
                }
                else if (in.hasNext("FD:")) {
                    in.next();
                    final String[] obf2 = in.next().split("/");
                    final String obfOwner = obf2[0];
                    final String obfName = obf2[1];
                    final String deobf2 = in.next();
                    final String srgName2 = getLastComponent(deobf2);
                    String srgPkg = deobf2.substring(0, deobf2.lastIndexOf(47));
                    final String srgOwner = getLastComponent(srgPkg);
                    srgPkg = srgPkg.substring(0, srgPkg.lastIndexOf(47));
                    final FieldSrgData fieldData = new FieldSrgData(obfOwner, obfName, srgOwner, srgPkg, srgName2, in.hasNext("#C"));
                    this.srgFieldName2FieldData.put(srgName2, fieldData);
                    this.class2FieldDataSet.get(this.srgClassName2ClassData.get(srgPkg + "/" + srgOwner)).add(fieldData);
                    this.srgFieldName2ClassData.put(srgName2, this.srgClassName2ClassData.get(srgPkg + "/" + srgOwner));
                }
                else if (in.hasNext("MD:")) {
                    in.next();
                    final String[] obf2 = in.next().split("/");
                    final String obfOwner = obf2[0];
                    final String obfName = obf2[1];
                    final String obfDescriptor = in.next();
                    final String deobf3 = in.next();
                    final String srgName3 = getLastComponent(deobf3);
                    String srgPkg2 = deobf3.substring(0, deobf3.lastIndexOf(47));
                    final String srgOwner2 = getLastComponent(srgPkg2);
                    srgPkg2 = srgPkg2.substring(0, srgPkg2.lastIndexOf(47));
                    final String srgDescriptor = in.next();
                    final MethodSrgData methodData = new MethodSrgData(obfOwner, obfName, obfDescriptor, srgOwner2, srgPkg2, srgName3, srgDescriptor, in.hasNext("#C"));
                    this.srgMethodName2MethodData.put(srgName3, methodData);
                    this.class2MethodDataSet.get(this.srgClassName2ClassData.get(srgPkg2 + "/" + srgOwner2)).add(methodData);
                    this.srgMethodName2ClassData.put(srgName3, this.srgClassName2ClassData.get(srgPkg2 + "/" + srgOwner2));
                    final ExcData toAdd = new ExcData(srgOwner2, srgName3, srgDescriptor, new String[0], staticMethods.contains(srgName3));
                    final ExcData existing = excFile.srgMethodName2ExcData.get(srgName3);
                    if (existing != null && existing.getParameters().length >= toAdd.getParameters().length) {
                        continue;
                    }
                    excFile.srgMethodName2ExcData.put(srgName3, toAdd);
                    for (final String parameter : toAdd.getParameters()) {
                        excFile.srgParamName2ExcData.put(parameter, toAdd);
                    }
                }
                else {
                    in.nextLine();
                }
            }
        }
        finally {
            in.close();
        }
    }
}
