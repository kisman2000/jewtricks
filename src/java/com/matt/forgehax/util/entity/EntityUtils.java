//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.entity;

import com.matt.forgehax.*;
import net.minecraft.client.entity.*;
import com.matt.forgehax.util.entity.mobtypes.*;
import com.matt.forgehax.asm.reflection.*;
import net.minecraft.entity.player.*;
import fuck.you.jewtricks.base.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.monster.*;
import net.minecraft.world.*;
import java.util.*;
import com.matt.forgehax.util.color.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class EntityUtils implements Globals
{
    public static boolean isBatsDisabled;
    
    public static MobTypeEnum getRelationship(final Entity entity) {
        if (entity instanceof AbstractClientPlayer) {
            return MobTypeEnum.PLAYER;
        }
        for (final MobType type : MobTypeRegistry.getSortedSpecialMobTypes()) {
            if (type.isMobType(entity)) {
                return type.getMobType(entity);
            }
        }
        if (MobTypeRegistry.HOSTILE.isMobType(entity)) {
            return MobTypeEnum.HOSTILE;
        }
        if (MobTypeRegistry.FRIENDLY.isMobType(entity)) {
            return MobTypeEnum.FRIENDLY;
        }
        return MobTypeEnum.HOSTILE;
    }
    
    public static boolean isMobAggressive(final Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                if (!((EntityPigZombie)entity).isAngry()) {
                    FastReflection.Fields.EntityPigZombie_angerLevel.set((Object)entity, (Object)400);
                }
                return true;
            }
        }
        else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !EntityUtils.MC.player.equals((Object)((EntityWolf)entity).getOwner());
            }
            if (entity instanceof EntityEnderman) {
                return ((EntityEnderman)entity).isScreaming();
            }
        }
        return false;
    }
    
    public static boolean isLiving(final Entity entity) {
        return entity instanceof EntityLivingBase;
    }
    
    public static boolean isPlayer(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
    
    public static boolean isLocalPlayer(final Entity entity) {
        return Objects.equals(Wrapper.getLocalPlayer(), entity);
    }
    
    public static boolean isFakeLocalPlayer(final Entity entity) {
        return entity != null && entity.getEntityId() == -100;
    }
    
    public static boolean isValidEntity(final Entity entity) {
        final Entity riding = Wrapper.getLocalPlayer().getRidingEntity();
        return entity.ticksExisted > 1 && !isFakeLocalPlayer(entity) && (riding == null || !riding.equals((Object)entity));
    }
    
    public static boolean isAlive(final Entity entity) {
        return isLiving(entity) && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }
    
    public static boolean isNeutralMob(final Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }
    
    public static boolean isFriendlyMob(final Entity entity) {
        return (entity.isCreatureType(EnumCreatureType.CREATURE, false) && !isNeutralMob(entity)) || (entity.isCreatureType(EnumCreatureType.AMBIENT, false) && !EntityUtils.isBatsDisabled) || entity instanceof EntityVillager || entity instanceof EntityIronGolem || (isNeutralMob(entity) && !isMobAggressive(entity));
    }
    
    public static boolean isHostileMob(final Entity entity) {
        return (entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity)) || isMobAggressive(entity);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double x, final double y, final double z) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * x, (entity.posY - entity.lastTickPosY) * y, (entity.posZ - entity.lastTickPosZ) * z);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final Vec3d vec) {
        return getInterpolatedAmount(entity, vec.x, vec.y, vec.z);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double ticks) {
        return getInterpolatedAmount(entity, ticks, ticks, ticks);
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final double ticks) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, ticks));
    }
    
    public static Vec3d getInterpolatedEyePos(final Entity entity, final double ticks) {
        return getInterpolatedPos(entity, ticks).add(0.0, (double)entity.getEyeHeight(), 0.0);
    }
    
    public static Vec3d getEyePos(final Entity entity) {
        return new Vec3d(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
    }
    
    public static Vec3d getOBBCenter(final Entity entity) {
        final AxisAlignedBB obb = entity.getEntityBoundingBox();
        return new Vec3d((obb.maxX + obb.minX) / 2.0, (obb.maxY + obb.minY) / 2.0, (obb.maxZ + obb.minZ) / 2.0);
    }
    
    public static RayTraceResult traceEntity(final World world, final Vec3d start, final Vec3d end, final List<Entity> filter) {
        RayTraceResult result = null;
        double hitDistance = -1.0;
        for (final Entity ent : world.loadedEntityList) {
            if (filter.contains(ent)) {
                continue;
            }
            final double distance = start.distanceTo(ent.getPositionVector());
            final RayTraceResult trace = ent.getEntityBoundingBox().calculateIntercept(start, end);
            if (trace == null || (hitDistance != -1.0 && distance >= hitDistance)) {
                continue;
            }
            hitDistance = distance;
            result = trace;
            result.entityHit = ent;
        }
        return result;
    }
    
    public static int getDrawColor(final EntityLivingBase living) {
        if (isPlayer((Entity)living)) {
            return Colors.RED.toBuffer();
        }
        if (isHostileMob((Entity)living)) {
            return Colors.ORANGE.toBuffer();
        }
        if (isFriendlyMob((Entity)living)) {
            return Colors.GREEN.toBuffer();
        }
        return Colors.WHITE.toBuffer();
    }
    
    public static boolean isDrivenByPlayer(final Entity entityIn) {
        return Wrapper.getLocalPlayer() != null && entityIn != null && entityIn == Wrapper.getRidingEntity();
    }
    
    public static boolean isAboveWater(final Entity entity) {
        return isAboveWater(entity, false);
    }
    
    public static boolean isAboveWater(final Entity entity, final boolean packet) {
        if (entity == null) {
            return false;
        }
        final double y = entity.posY - (packet ? 0.03 : (isPlayer(entity) ? 0.2 : 0.5));
        for (int x = MathHelper.floor(entity.posX); x < MathHelper.ceil(entity.posX); ++x) {
            for (int z = MathHelper.floor(entity.posZ); z < MathHelper.ceil(entity.posZ); ++z) {
                final BlockPos pos = new BlockPos(x, MathHelper.floor(y), z);
                if (Wrapper.getWorld().getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isInWater(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double y = entity.posY + 0.01;
        for (int x = MathHelper.floor(entity.posX); x < MathHelper.ceil(entity.posX); ++x) {
            for (int z = MathHelper.floor(entity.posZ); z < MathHelper.ceil(entity.posZ); ++z) {
                final BlockPos pos = new BlockPos(x, (int)y, z);
                if (Wrapper.getWorld().getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static {
        EntityUtils.isBatsDisabled = false;
    }
}
