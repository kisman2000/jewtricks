//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entity.mobtypes;

import com.matt.forgehax.util.common.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;

public class PigZombieMob extends MobType
{
    protected PriorityEnum getPriority() {
        return PriorityEnum.LOW;
    }
    
    public boolean isMobType(final Entity entity) {
        return entity instanceof EntityPigZombie;
    }
    
    protected MobTypeEnum getMobTypeUnchecked(final Entity entity) {
        final EntityPigZombie zombie = (EntityPigZombie)entity;
        return (zombie.isArmsRaised() || zombie.isAngry()) ? MobTypeEnum.HOSTILE : MobTypeEnum.NEUTRAL;
    }
}
