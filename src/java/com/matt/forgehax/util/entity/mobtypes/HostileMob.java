//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entity.mobtypes;

import net.minecraft.entity.*;

public class HostileMob extends MobType
{
    @Override
    public boolean isMobType(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false);
    }
    
    @Override
    protected MobTypeEnum getMobTypeUnchecked(final Entity entity) {
        return MobTypeEnum.HOSTILE;
    }
}
