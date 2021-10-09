//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.remapping;

import java.io.*;
import java.security.*;
import bspkrs.mmv.*;
import com.matt.forgehax.asm.utils.*;

public class MCPMappingLoader
{
    private final File baseDir;
    private final String baseSrgDir = "{mc_ver}";
    private final String baseMappingDir = "{mc_ver}/{channel}_{map_ver}";
    private final String baseMappingUrl = "http://export.mcpbot.bspk.rs/mcp_{channel}/{map_ver}-{mc_ver}/mcp_{channel}-{map_ver}-{mc_ver}.zip";
    private final String baseSrgUrl = "http://export.mcpbot.bspk.rs/mcp/{mc_ver}/mcp-{mc_ver}-srg.zip";
    private final File srgDir;
    private final File mappingDir;
    private final File srgFile;
    private final File excFile;
    private final File staticMethodsFile;
    private SrgFile srgFileData;
    private ExcFile excFileData;
    private StaticMethodsFile staticMethods;
    private CsvFile csvFieldData;
    private CsvFile csvMethodData;
    private ParamCsvFile csvParamData;
    
    public MCPMappingLoader(final String mapping) throws IOException, CantLoadMCPMappingException, NoSuchAlgorithmException, DigestException {
        this.baseDir = new File(new File(System.getProperty("user.home")), ".cache/MCPMappingViewer");
        final String[] tokens = mapping.split("_");
        if (tokens.length < 3) {
            throw new CantLoadMCPMappingException("Invalid mapping string specified.");
        }
        this.srgDir = this.getSubDirForZip(tokens, "http://export.mcpbot.bspk.rs/mcp/{mc_ver}/mcp-{mc_ver}-srg.zip", "{mc_ver}");
        this.mappingDir = this.getSubDirForZip(tokens, "http://export.mcpbot.bspk.rs/mcp_{channel}/{map_ver}-{mc_ver}/mcp_{channel}-{map_ver}-{mc_ver}.zip", "{mc_ver}/{channel}_{map_ver}");
        this.srgFile = new File(this.srgDir, "joined.srg");
        this.excFile = new File(this.srgDir, "joined.exc");
        this.staticMethodsFile = new File(this.srgDir, "static_methods.txt");
        if (!this.srgFile.exists()) {
            throw new CantLoadMCPMappingException("Unable to find joined.srg. Your MCP conf folder may be corrupt.");
        }
        if (!this.excFile.exists()) {
            throw new CantLoadMCPMappingException("Unable to find joined.exc. Your MCP conf folder may be corrupt.");
        }
        if (!this.staticMethodsFile.exists()) {
            throw new CantLoadMCPMappingException("Unable to find static_methods.txt. Your MCP conf folder may be corrupt.");
        }
        this.staticMethods = new StaticMethodsFile(this.staticMethodsFile);
        this.excFileData = new ExcFile(this.excFile);
        this.srgFileData = new SrgFile(this.srgFile, this.excFileData, this.staticMethods);
        this.csvFieldData = new CsvFile(new File(this.mappingDir, "fields.csv"));
        this.csvMethodData = new CsvFile(new File(this.mappingDir, "methods.csv"));
        this.csvParamData = new ParamCsvFile(new File(this.mappingDir, "params.csv"));
    }
    
    public CsvFile getCsvMethodData() {
        return this.csvMethodData;
    }
    
    public CsvFile getCsvFieldData() {
        return this.csvFieldData;
    }
    
    public SrgFile getSrgFileData() {
        return this.srgFileData;
    }
    
    private File getSubDirForZip(final String[] tokens, final String baseZipUrl, final String baseSubDir) throws CantLoadMCPMappingException, NoSuchAlgorithmException, DigestException, IOException {
        if (!this.baseDir.exists() && !this.baseDir.mkdirs()) {
            throw new CantLoadMCPMappingException("Application data folder does not exist and cannot be created.");
        }
        final File subDir = new File(this.baseDir, this.replaceTokens(baseSubDir, tokens));
        if (!subDir.exists() && !subDir.mkdirs()) {
            throw new CantLoadMCPMappingException("Data folder does not exist and cannot be created.");
        }
        try {
            final RemoteZipHandler rzh = new RemoteZipHandler(this.replaceTokens(baseZipUrl, tokens), subDir, "SHA1");
            rzh.checkRemoteZip();
        }
        catch (Throwable t) {
            ASMStackLogger.printStackTrace(t);
        }
        return subDir;
    }
    
    private String replaceTokens(final String s, final String[] tokens) {
        return s.replace("{mc_ver}", tokens[0]).replace("{channel}", tokens[1]).replace("{map_ver}", tokens[2]);
    }
    
    public static class CantLoadMCPMappingException extends Exception
    {
        public CantLoadMCPMappingException(final String msg) {
            super(msg);
        }
    }
}
