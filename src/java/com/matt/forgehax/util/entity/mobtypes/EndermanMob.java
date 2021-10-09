//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entity.mobtypes;

import com.matt.forgehax.util.common.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;

public class EndermanMob extends MobType
{
    @Override
    protected PriorityEnum getPriority() {
        return PriorityEnum.LOW;
    }
    
    @Override
    public boolean isMobType(final Entity entity) {
        return entity instanceof EntityEnderman;
    }
    
    @Override
    protected MobTypeEnum getMobTypeUnchecked(final Entity entity) {
        final EntityEnderman enderman = (EntityEnderman)entity;
        return enderman.isScreaming() ? MobTypeEnum.HOSTILE : MobTypeEnum.NEUTRAL;
    }
}
