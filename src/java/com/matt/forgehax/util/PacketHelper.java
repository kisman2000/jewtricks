//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import net.minecraft.network.*;
import fuck.you.jewtricks.base.*;
import java.util.concurrent.*;
import com.google.common.cache.*;

public class PacketHelper
{
    private static final LoadingCache<Packet, Boolean> CACHE;
    
    public static void ignore(final Packet packet) {
        PacketHelper.CACHE.put((Object)packet, (Object)true);
    }
    
    public static void ignoreAndSend(final Packet packet) {
        ignore(packet);
        Wrapper.getNetworkManager().sendPacket(packet);
    }
    
    public static boolean isIgnored(final Packet packet) {
        try {
            return (boolean)PacketHelper.CACHE.get((Object)packet);
        }
        catch (ExecutionException e) {
            return false;
        }
    }
    
    public static void remove(final Packet packet) {
        PacketHelper.CACHE.invalidate((Object)packet);
    }
    
    static {
        CACHE = CacheBuilder.newBuilder().expireAfterWrite(15L, TimeUnit.SECONDS).build((CacheLoader)new CacheLoader<Packet, Boolean>() {
            public Boolean load(final Packet key) throws Exception {
                return false;
            }
        });
    }
}
