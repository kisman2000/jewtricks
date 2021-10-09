//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entity.mobtypes;

import java.util.*;
import com.google.common.collect.*;

public class MobTypeRegistry
{
    public static final MobType HOSTILE;
    public static final MobType FRIENDLY;
    private static final List<MobType> MOB_TYPES_SPECIAL;
    private static List<MobType> readOnly;
    
    public static void register(final MobType type) {
        synchronized (MobTypeRegistry.MOB_TYPES_SPECIAL) {
            MobTypeRegistry.MOB_TYPES_SPECIAL.add(type);
            Collections.sort(MobTypeRegistry.MOB_TYPES_SPECIAL);
            MobTypeRegistry.readOnly = (List<MobType>)ImmutableList.copyOf((Collection)MobTypeRegistry.MOB_TYPES_SPECIAL);
        }
    }
    
    public static void unregister(final MobType type) {
        synchronized (MobTypeRegistry.MOB_TYPES_SPECIAL) {
            MobTypeRegistry.MOB_TYPES_SPECIAL.remove(type);
            Collections.sort(MobTypeRegistry.MOB_TYPES_SPECIAL);
            MobTypeRegistry.readOnly = (List<MobType>)ImmutableList.copyOf((Collection)MobTypeRegistry.MOB_TYPES_SPECIAL);
        }
    }
    
    public static List<MobType> getSortedSpecialMobTypes() {
        return MobTypeRegistry.readOnly;
    }
    
    static {
        HOSTILE = (MobType)new HostileMob();
        FRIENDLY = (MobType)new FriendlyMob();
        MOB_TYPES_SPECIAL = Lists.newArrayList();
        MobTypeRegistry.readOnly = (List<MobType>)ImmutableList.of();
        register((MobType)new EndermanMob());
        register(new PigZombieMob());
        register(new WolfMob());
    }
}
