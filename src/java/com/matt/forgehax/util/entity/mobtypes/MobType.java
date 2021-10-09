//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entity.mobtypes;

import com.matt.forgehax.util.common.*;
import net.minecraft.entity.*;

public abstract class MobType implements Comparable<MobType>
{
    protected PriorityEnum getPriority() {
        return PriorityEnum.LOWEST;
    }
    
    public boolean isNeutralMob(final Entity entity) {
        return false;
    }
    
    public abstract boolean isMobType(final Entity p0);
    
    protected abstract MobTypeEnum getMobTypeUnchecked(final Entity p0);
    
    public MobTypeEnum getMobType(final Entity entity) {
        try {
            return this.getMobTypeUnchecked(entity);
        }
        catch (Throwable t) {
            return MobTypeEnum.HOSTILE;
        }
    }
    
    @Override
    public int compareTo(final MobType o) {
        return this.getPriority().compareTo((Enum)o.getPriority());
    }
}
