//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.remapping;

import com.matt.forgehax.asm.*;
import com.matt.forgehax.*;
import com.matt.forgehax.asm.utils.*;
import com.matt.forgehax.asm.reflection.*;
import net.minecraftforge.fml.common.asm.transformers.deobf.*;
import com.matt.forgehax.asm.utils.environment.*;
import com.google.common.base.*;
import javax.annotation.*;
import org.objectweb.asm.*;
import java.util.*;
import java.util.function.*;
import com.google.common.collect.*;
import bspkrs.mmv.*;

public class ObfuscatedStateMapper implements ASMCommon, IStateMapper
{
    private static ObfuscatedStateMapper INSTANCE;
    private final BiMap<String, String> mcClasses;
    private final Map<String, Map<String, McpTypeData>> mcpMethodData;
    private final Map<String, Map<String, McpTypeData>> mcpFieldData;
    
    public static ObfuscatedStateMapper getInstance() {
        return (ObfuscatedStateMapper.INSTANCE == null) ? (ObfuscatedStateMapper.INSTANCE = new ObfuscatedStateMapper()) : ObfuscatedStateMapper.INSTANCE;
    }
    
    protected ObfuscatedStateMapper() {
        ObfuscatedStateMapper.LOGGER.info("Using build mapping \"" + ForgeHaxProperties.getMcpMappingUrl() + "\"");
        MCPMappingLoader mcpMappingLoader = null;
        try {
            mcpMappingLoader = new MCPMappingLoader(ForgeHaxProperties.getMcpMappingUrl());
        }
        catch (Exception e) {
            ObfuscatedStateMapper.LOGGER.error(e.getMessage());
            ASMStackLogger.printStackTrace((Throwable)e);
        }
        ObfuscatedStateMapper.LOGGER.info("Mapping data successfully initialize");
        Objects.requireNonNull(mcpMappingLoader, "MCPMappingLoader failed to lookup obfuscation data");
        if (this.isObfuscated()) {
            ObfuscatedStateMapper.LOGGER.info("initializing ObfuscatedStateMapper WITH obfuscation");
        }
        else {
            ObfuscatedStateMapper.LOGGER.info("initializing ObfuscatedStateMapper WITHOUT obfuscation");
        }
        this.mcClasses = (BiMap<String, String>)ImmutableBiMap.copyOf((Map)FastReflectionForge.Fields.FMLDeobfuscatingRemapper_classNameBiMap.get((Object)FMLDeobfuscatingRemapper.INSTANCE));
        this.mcpMethodData = buildMcpTypeData(mcpMappingLoader.getCsvMethodData(), mcpMappingLoader.getSrgFileData().class2MethodDataSet, this.getConvertedMap((Map<String, Map<String, String>>)FastReflectionForge.Fields.FMLDeobfuscatingRemapper_rawMethodMaps.get((Object)FMLDeobfuscatingRemapper.INSTANCE), str -> str.split("\\(")[0]), (csvData, data) -> csvData.getMcpName() + data.getSrgDescriptor());
        this.mcpFieldData = buildMcpTypeData(mcpMappingLoader.getCsvFieldData(), mcpMappingLoader.getSrgFileData().class2FieldDataSet, this.getConvertedMap((Map<String, Map<String, String>>)FastReflectionForge.Fields.FMLDeobfuscatingRemapper_rawFieldMaps.get((Object)FMLDeobfuscatingRemapper.INSTANCE), str -> str.split(":")[0]), (csvData, data) -> csvData.getMcpName());
    }
    
    public boolean isObfuscated() {
        return RuntimeState.isObfuscated();
    }
    
    public Map<String, String> getMcClasses() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends String>)this.mcClasses);
    }
    
    public Map<String, Map<String, McpTypeData>> getMcpFieldData() {
        return this.mcpFieldData;
    }
    
    public Map<String, Map<String, McpTypeData>> getMcpMethodData() {
        return this.mcpMethodData;
    }
    
    protected String getClassName(final String className, final Map<String, String> map) {
        final String name = map.get(className);
        if (Strings.isNullOrEmpty(name)) {
            ObfuscatedStateMapper.LOGGER.warn("Could not lookup name for class '" + className + "'");
            return null;
        }
        return name;
    }
    
    @Nullable
    public String getObfClassName(final String className) {
        return this.getClassName(className, (Map<String, String>)this.mcClasses.inverse());
    }
    
    @Nullable
    public String getMcpClassName(final String className) {
        return this.getClassName(className, (Map<String, String>)this.mcClasses);
    }
    
    @Nullable
    public String getSrgMethodName(final String parentClassName, final String methodName, final String methodDescriptor) {
        try {
            return this.getMcpMethodData().get(parentClassName).get(methodName + methodDescriptor).getSrgName();
        }
        catch (Exception e) {
            ObfuscatedStateMapper.LOGGER.warn("Could not lookup srg name for method '" + parentClassName + "::" + methodName + methodDescriptor + "'");
            return null;
        }
    }
    
    @Nullable
    public String getObfMethodName(final String parentClassName, final String methodName, final String methodDescriptor) {
        try {
            return this.getMcpMethodData().get(parentClassName).get(methodName + methodDescriptor).getObfName();
        }
        catch (Exception e) {
            ObfuscatedStateMapper.LOGGER.warn("Could not lookup obf name for method '" + parentClassName + "::" + methodName + methodDescriptor + "'");
            return null;
        }
    }
    
    @Nullable
    public String getSrgFieldName(final String parentClassName, final String fieldName) {
        try {
            return this.getMcpFieldData().get(parentClassName).get(fieldName).getSrgName();
        }
        catch (Exception e) {
            ObfuscatedStateMapper.LOGGER.warn("Could not lookup srg name for field '" + parentClassName + "." + fieldName + "'");
            return null;
        }
    }
    
    @Nullable
    public String getObfFieldName(final String parentClassName, final String fieldName) {
        try {
            return this.getMcpFieldData().get(parentClassName).get(fieldName).getObfName();
        }
        catch (Exception e) {
            ObfuscatedStateMapper.LOGGER.warn("Could not lookup obf name for field '" + parentClassName + "." + fieldName + "'");
            return null;
        }
    }
    
    public Type translateMethodType(final Type methodType) {
        final Type[] translated = this.translateTypes((Map<String, String>)this.mcClasses, (Type[])Lists.asList((Object)methodType.getReturnType(), (Object[])methodType.getArgumentTypes()).toArray(new Type[0]));
        return Type.getMethodType(translated[0], (Type[])Arrays.copyOfRange(translated, 1, translated.length));
    }
    
    public Type translateFieldType(final Type fieldType) {
        return this.translateTypes((Map<String, String>)this.mcClasses, fieldType)[0];
    }
    
    private Type[] translateTypes(final Map<String, String> mapIn, final Type... types) {
        int index = 0;
        final Type[] translated = new Type[types.length];
        for (Type arg : types) {
            switch (arg.getSort()) {
                case 9: {
                    if (arg.getElementType().getSort() != 10) {
                        break;
                    }
                }
                case 10: {
                    final String desc = arg.getDescriptor();
                    final String heading = desc.substring(0, desc.indexOf(76) + 1);
                    final String name = desc.substring(heading.length(), desc.indexOf(59));
                    final String newName = mapIn.get(name);
                    arg = Type.getType(heading + (Strings.isNullOrEmpty(newName) ? name : newName) + ";");
                    break;
                }
            }
            translated[index++] = arg;
        }
        return translated;
    }
    
    private Map<String, Map<String, String>> getConvertedMap(final Map<String, Map<String, String>> mapIn, final Function<String, String> getNameFunction) {
        final Map<String, Map<String, String>> mapOut = (Map<String, Map<String, String>>)Maps.newHashMap();
        final String realName;
        Map<String, String> subMap;
        String key;
        String value;
        final Map<String, String> map;
        final Map<String, Map<Object, Object>> map2;
        mapIn.entrySet().forEach(entry -> {
            realName = this.getMcpClassName(entry.getKey());
            if (!Strings.isNullOrEmpty(realName)) {
                subMap = (Map<String, String>)Maps.newHashMap();
                ((Map)entry.getValue()).entrySet().forEach(subEntry -> {
                    key = getNameFunction.apply(subEntry.getKey());
                    value = getNameFunction.apply((String)subEntry.getValue());
                    map.put(this.isObfuscated() ? value : key, this.isObfuscated() ? key : value);
                    return;
                });
                map2.put(realName, Collections.unmodifiableMap((Map<?, ?>)subMap));
            }
            return;
        });
        return mapOut;
    }
    
    private static <E extends MemberSrgData> Map<String, Map<String, McpTypeData>> buildMcpTypeData(final CsvFile csvFile, final Map<ClassSrgData, Set<E>> mcpMappings, final Map<String, Map<String, String>> runtimeMappings, final NamingFunction<E> mcpNameFunction) {
        final Map<String, Map<String, McpTypeData>> output = (Map<String, Map<String, McpTypeData>>)Maps.newHashMap();
        final Map<String, McpTypeData> typeDataMap;
        final Map<String, String> runtimeClass;
        String srgName;
        final Map<K, String> map;
        String obfName;
        CsvData csvData;
        String mcpName;
        McpTypeData typeData;
        final Map<String, McpTypeData> map2;
        final Map<String, Map<Object, Object>> map3;
        mcpMappings.entrySet().forEach(parentClassEntry -> {
            typeDataMap = (Map<String, McpTypeData>)Maps.newHashMap();
            runtimeClass = runtimeMappings.get(parentClassEntry.getKey().getFullyQualifiedSrgName());
            if (!Objects.isNull(runtimeClass)) {
                ((Set)parentClassEntry.getValue()).forEach(data -> {
                    srgName = data.getSrgName();
                    obfName = map.get(srgName);
                    csvData = csvFile.getCsvDataForKey(srgName);
                    mcpName = (Objects.isNull(csvData) ? null : csvData.getMcpName());
                    typeData = new McpTypeData(mcpName, srgName, obfName);
                    map2.put(srgName, typeData);
                    if (!Strings.isNullOrEmpty(mcpName)) {
                        map2.put(mcpNameFunction.apply(csvData, (E)data), typeData);
                    }
                    return;
                });
            }
            map3.put(parentClassEntry.getKey().getFullyQualifiedSrgName(), Collections.unmodifiableMap((Map<?, ?>)typeDataMap));
            return;
        });
        return Collections.unmodifiableMap((Map<? extends String, ? extends Map<String, McpTypeData>>)output);
    }
    
    static {
        ObfuscatedStateMapper.INSTANCE = null;
    }
    
    public static class McpTypeData
    {
        private final String mcpName;
        private final String srgName;
        private final String obfName;
        
        public McpTypeData(final String mcpName, final String srgName, final String obfName) {
            this.mcpName = mcpName;
            this.srgName = srgName;
            this.obfName = obfName;
        }
        
        public String getMcpName() {
            return this.mcpName;
        }
        
        public String getSrgName() {
            return this.srgName;
        }
        
        public String getObfName() {
            return this.obfName;
        }
    }
    
    private interface NamingFunction<E extends MemberSrgData>
    {
        String apply(final CsvData p0, final E p1);
    }
}
