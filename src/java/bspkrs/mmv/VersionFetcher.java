//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

import com.google.gson.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class VersionFetcher
{
    private final String jsonUrl = "http://export.mcpbot.bspk.rs/versions.json";
    private List<String> versions;
    
    public List<String> getVersions(final boolean force) throws IOException {
        if (this.versions == null || force) {
            final URL url = new URL("http://export.mcpbot.bspk.rs/versions.json");
            final URLConnection connection = url.openConnection();
            connection.addRequestProperty("User-Agent", "MMV/1.0.0");
            final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final Map<String, Object> json = (Map<String, Object>)new Gson().fromJson((Reader)br, (Class)Map.class);
            this.versions = new ArrayList<String>();
            for (final String mcVer : json.keySet()) {
                for (final String channel : json.get(mcVer).keySet()) {
                    for (final Double ver : json.get(mcVer).get(channel)) {
                        this.versions.add(mcVer + "_" + channel + "_" + String.format("%.0f", ver));
                    }
                }
            }
            Collections.sort(this.versions, (Comparator<? super String>)Collections.reverseOrder((Comparator<? super T>)new SplittedNaturalComparator("_")));
            return this.versions;
        }
        return this.versions;
    }
}
