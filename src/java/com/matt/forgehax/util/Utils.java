//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import com.matt.forgehax.*;
import java.util.regex.*;
import net.minecraft.util.math.*;
import com.matt.forgehax.util.math.*;
import fuck.you.jewtricks.base.*;
import com.matt.forgehax.util.entity.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.util.*;
import net.minecraft.inventory.*;
import net.minecraft.nbt.*;

public class Utils implements Globals
{
    public static <E extends Enum<?>> String[] toArray(final E[] o) {
        final String[] output = new String[o.length];
        for (int i = 0; i < output.length; ++i) {
            output[i] = o[i].name();
        }
        return output;
    }
    
    public static UUID stringToUUID(final String uuid) {
        if (uuid.contains("-")) {
            return UUID.fromString(uuid);
        }
        final Pattern pattern = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");
        final Matcher matcher = pattern.matcher(uuid);
        return UUID.fromString(matcher.replaceAll("$1-$2-$3-$4-$5"));
    }
    
    public static double normalizeAngle(double angle) {
        while (angle <= -180.0) {
            angle += 360.0;
        }
        while (angle > 180.0) {
            angle -= 360.0;
        }
        return angle;
    }
    
    public static double clamp(final double value, final double min, final double max) {
        return Math.max(min, Math.min(max, value));
    }
    
    public static float clamp(final float value, final float min, final float max) {
        return Math.max(min, Math.min(max, value));
    }
    
    public static Angle getLookAtAngles(final Vec3d start, final Vec3d end) {
        return AngleHelper.getAngleFacingInDegrees(end.subtract(start)).normalize();
    }
    
    public static Angle getLookAtAngles(final Vec3d end) {
        return getLookAtAngles(EntityUtils.getEyePos((Entity)Wrapper.getLocalPlayer()), end);
    }
    
    public static Angle getLookAtAngles(final Entity entity) {
        return getLookAtAngles(EntityUtils.getOBBCenter(entity));
    }
    
    public static double scale(final double x, final double from_min, final double from_max, final double to_min, final double to_max) {
        return to_min + (to_max - to_min) * ((x - from_min) / (from_max - from_min));
    }
    
    public static <T> boolean isInRange(final Collection<T> list, final int index) {
        return list != null && index >= 0 && index < list.size();
    }
    
    public static <T> T defaultTo(final T value, final T defaultTo) {
        return (value == null) ? defaultTo : value;
    }
    
    public static List<ItemStack> getShulkerContents(final ItemStack stack) {
        final NonNullList<ItemStack> contents = (NonNullList<ItemStack>)NonNullList.withSize(27, (Object)ItemStack.EMPTY);
        final NBTTagCompound compound = stack.getTagCompound();
        if (compound != null && compound.hasKey("BlockEntityTag", 10)) {
            final NBTTagCompound tags = compound.getCompoundTag("BlockEntityTag");
            if (tags.hasKey("Items", 9)) {
                ItemStackHelper.loadAllItems(tags, (NonNullList)contents);
            }
        }
        return (List<ItemStack>)contents;
    }
}
