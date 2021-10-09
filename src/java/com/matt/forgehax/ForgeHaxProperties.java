//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax;

import java.util.*;
import com.google.common.base.*;
import java.io.*;

public class ForgeHaxProperties
{
    private static final Properties CONFIG_PROPERTIES;
    
    public static Properties getConfigProperties() {
        return ForgeHaxProperties.CONFIG_PROPERTIES;
    }
    
    public static String getVersion() {
        return Strings.nullToEmpty(getConfigProperties().getProperty("forgehax.version"));
    }
    
    public static String getMcVersion() {
        return Strings.nullToEmpty(getConfigProperties().getProperty("forgehax.mc.version"));
    }
    
    public static String getForgeVersion() {
        return Strings.nullToEmpty(getConfigProperties().getProperty("forgehax.forge.version"));
    }
    
    public static String getMcpVersion() {
        return "1.12";
    }
    
    public static String getMcpChannel() {
        return "stable";
    }
    
    public static String getMcpMapping() {
        return "39";
    }
    
    public static String getMcpMappingUrl() {
        return String.format("%s_%s_%s", getMcpVersion(), getMcpChannel(), getMcpMapping());
    }
    
    static {
        CONFIG_PROPERTIES = new Properties();
        InputStream input = null;
        try {
            input = ForgeHaxProperties.class.getClassLoader().getResourceAsStream("config.properties");
            ForgeHaxProperties.CONFIG_PROPERTIES.load(input);
        }
        catch (Throwable t) {}
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (Throwable t2) {}
            }
        }
    }
}
