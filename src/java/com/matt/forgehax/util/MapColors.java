//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import net.minecraft.block.material.*;
import com.matt.forgehax.util.color.*;

public class MapColors
{
    private static final int[] COLOR_LIST;
    private static final int[] BASE_COLORS;
    
    public static int getColor(final int index) {
        return MapColors.COLOR_LIST[index];
    }
    
    public static int colorListLength() {
        return MapColors.COLOR_LIST.length;
    }
    
    public static int getBaseColor(final int index) {
        return MapColors.BASE_COLORS[index];
    }
    
    public static int baseColorListLength() {
        return MapColors.BASE_COLORS.length;
    }
    
    static {
        int baseColorsLength = 0;
        for (int i = MapColor.COLORS.length - 1; i >= 0; --i) {
            if (MapColor.COLORS[i] != null) {
                baseColorsLength = i + 1;
                break;
            }
        }
        BASE_COLORS = new int[baseColorsLength];
        COLOR_LIST = new int[baseColorsLength * 4];
        for (int i = 0; i < MapColors.BASE_COLORS.length; ++i) {
            MapColors.BASE_COLORS[i] = MapColor.COLORS[i].colorValue;
        }
        for (int i = 0; i < MapColors.BASE_COLORS.length; ++i) {
            final int[] rgb = Color.of(MapColors.BASE_COLORS[i]).toIntegerArray();
            MapColors.COLOR_LIST[i * 4] = Color.of(rgb[0] * 180 / 255, rgb[1] * 180 / 255, rgb[2] * 180 / 255, 0).toBuffer();
            MapColors.COLOR_LIST[i * 4 + 1] = Color.of(rgb[0] * 220 / 255, rgb[1] * 220 / 255, rgb[2] * 220 / 255, 0).toBuffer();
            MapColors.COLOR_LIST[i * 4 + 2] = MapColors.BASE_COLORS[i];
            MapColors.COLOR_LIST[i * 4 + 3] = Color.of(rgb[0] * 135 / 255, rgb[1] * 135 / 255, rgb[2] * 135 / 255, 0).toBuffer();
        }
    }
}
