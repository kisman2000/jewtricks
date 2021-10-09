//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.task;

import com.google.common.collect.*;
import java.util.*;

public interface IProcess
{
    void process(final DataEntry p0);
    
    public static class DataEntry
    {
        private final Map<String, Object> data;
        
        public DataEntry() {
            this.data = (Map<String, Object>)Maps.newTreeMap((Comparator)String.CASE_INSENSITIVE_ORDER);
        }
        
        public <T> T getOrDefault(final String o, final T defaultValue) {
            try {
                return (T)this.data.get(o);
            }
            catch (Throwable t) {
                return defaultValue;
            }
        }
        
        public <T> T get(final String o) {
            return this.getOrDefault(o, (T)null);
        }
        
        public void set(final String name, final Object o) {
            this.data.put(name, o);
        }
    }
}
