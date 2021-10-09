//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entity.mobtypes;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;

public class FriendlyMob extends MobType
{
    @Override
    public boolean isMobType(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.CREATURE, false) || entity.isCreatureType(EnumCreatureType.AMBIENT, false) || entity.isCreatureType(EnumCreatureType.WATER_CREATURE, false) || entity instanceof EntityVillager || entity instanceof EntityGolem;
    }
    
    @Override
    protected MobTypeEnum getMobTypeUnchecked(final Entity entity) {
        return MobTypeEnum.FRIENDLY;
    }
}
