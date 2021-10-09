//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entity.mobtypes;

import com.matt.forgehax.util.common.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;

public class WolfMob extends MobType
{
    protected PriorityEnum getPriority() {
        return PriorityEnum.LOW;
    }
    
    protected MobTypeEnum getMobTypeUnchecked(final Entity entity) {
        final EntityWolf wolf = (EntityWolf)entity;
        return wolf.isAngry() ? MobTypeEnum.HOSTILE : MobTypeEnum.NEUTRAL;
    }
    
    public boolean isMobType(final Entity entity) {
        return entity instanceof EntityWolf;
    }
}
