//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.remapping;

import java.io.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import java.util.*;
import java.util.function.*;
import com.matt.forgehax.asm.*;
import com.google.common.collect.*;
import java.lang.reflect.*;

public class FileDumper
{
    private static void dump(final File dumpLocation, final Consumer<PrintWriter> consumer) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(dumpLocation, "UTF-8");
            consumer.accept(writer);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    private static void dumpMap(final File dumpLocation, final Map<String, String> mapIn) {
        final Iterator<Map.Entry<String, String>> iterator;
        Map.Entry<String, String> entry;
        dump(dumpLocation, writer -> {
            new TreeMap<String, String>(mapIn).entrySet().iterator();
            while (iterator.hasNext()) {
                entry = iterator.next();
                writer.println(entry.getKey() + "->" + entry.getValue());
            }
        });
    }
    
    private static void dumpMaps(final File dumpLocation, final Map<String, Map<String, String>> mapIn) {
        final Iterator<Map.Entry<String, Map<String, String>>> iterator;
        Map.Entry<String, Map<String, String>> entry;
        final Iterator<Map.Entry<String, String>> iterator2;
        Map.Entry<String, String> subEntry;
        dump(dumpLocation, writer -> {
            new TreeMap<String, Map<String, String>>(mapIn).entrySet().iterator();
            while (iterator.hasNext()) {
                entry = iterator.next();
                writer.println(entry.getKey());
                new TreeMap<String, String>(entry.getValue()).entrySet().iterator();
                while (iterator2.hasNext()) {
                    subEntry = iterator2.next();
                    writer.println("\t" + subEntry.getKey() + "->" + subEntry.getValue());
                }
            }
        });
    }
    
    private static void dumpASMTypes(final File dumpLocation, final Map<String, IASMType> mapIn) {
        final StringBuilder builder;
        final StringBuilder sb;
        dump(dumpLocation, writer -> {
            builder = new StringBuilder();
            mapIn.forEach((k, v) -> {
                sb.append(k);
                sb.append(":");
                sb.append(v.toString());
                sb.append("\n");
                return;
            });
            writer.println(builder.toString());
        });
    }
    
    private static void dumpMcpTypeMap(final File dumpLocation, final Map<String, Map<String, ObfuscatedStateMapper.McpTypeData>> mapIn) {
        final StringBuilder builder;
        final StringBuilder sb;
        final StringBuilder sb2;
        dump(dumpLocation, writer -> {
            builder = new StringBuilder();
            mapIn.entrySet().stream().sorted(Comparator.comparing((Function<? super Object, ? extends Comparable>)Map.Entry::getKey)).forEach(entry -> {
                sb.append(entry.getKey());
                sb.append("\n{\n");
                ((Map)entry.getValue()).entrySet().stream().sorted(Comparator.comparing((Function<? super T, ? extends Comparable>)Map.Entry::getKey)).forEach(dataEntry -> {
                    sb2.append("\t");
                    sb2.append(dataEntry.getKey());
                    sb2.append("\n\t{\n\t\tSrgName: ");
                    sb2.append(((ObfuscatedStateMapper.McpTypeData)dataEntry.getValue()).getSrgName());
                    sb2.append("\n\t\tObfName: ");
                    sb2.append(((ObfuscatedStateMapper.McpTypeData)dataEntry.getValue()).getObfName());
                    sb2.append("\n\t\tMcpName: ");
                    sb2.append(((ObfuscatedStateMapper.McpTypeData)dataEntry.getValue()).getMcpName());
                    sb2.append("\n\t}\n");
                    return;
                });
                sb.append("}\n");
                return;
            });
            writer.println(builder.toString());
        });
    }
    
    public static void dumpAllFiles() {
        final ObfuscatedStateMapper obfuscatedRemapper = ObfuscatedStateMapper.getInstance();
        final File dumpDir = new File("debuglog");
        dumpDir.mkdirs();
        dumpMap(new File(dumpDir, "classes.txt"), obfuscatedRemapper.getMcClasses());
        dumpMcpTypeMap(new File(dumpDir, "methods.txt"), obfuscatedRemapper.getMcpMethodData());
        dumpMcpTypeMap(new File(dumpDir, "fields.txt"), obfuscatedRemapper.getMcpFieldData());
        final Class<?>[] constants = (Class<?>[])new Class[] { TypesMc.Classes.class, TypesMc.Fields.class, TypesMc.Methods.class, TypesHook.Classes.class, TypesHook.Fields.class, TypesHook.Methods.class };
        final File typeDumpDir = new File(dumpDir, "typedump");
        typeDumpDir.mkdirs();
        for (final Class<?> clazz : constants) {
            try {
                final Map<String, IASMType> types = (Map<String, IASMType>)Maps.newHashMap();
                for (final Field field : clazz.getFields()) {
                    try {
                        final Object instance = field.get(null);
                        if (instance instanceof IASMType) {
                            types.put(field.getName(), (IASMType)instance);
                        }
                    }
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                dumpASMTypes(new File(typeDumpDir, clazz.getName().replaceAll("[^a-zA-Z0-9.-]", "_") + ".txt"), types);
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
