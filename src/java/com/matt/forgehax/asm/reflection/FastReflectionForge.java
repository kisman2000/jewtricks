//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.reflection;

import com.google.common.collect.*;
import java.util.*;
import com.matt.forgehax.asm.utils.fasttype.*;
import net.minecraftforge.fml.common.asm.transformers.deobf.*;

public interface FastReflectionForge
{
    public interface Fields
    {
        public static final FastField<BiMap<String, String>> FMLDeobfuscatingRemapper_classNameBiMap = FastTypeBuilder.create().setInsideClass(FMLDeobfuscatingRemapper.class).setName("classNameBiMap").asField();
        public static final FastField<Map<String, Map<String, String>>> FMLDeobfuscatingRemapper_rawFieldMaps = FastTypeBuilder.create().setInsideClass(FMLDeobfuscatingRemapper.class).setName("rawFieldMaps").asField();
        public static final FastField<Map<String, Map<String, String>>> FMLDeobfuscatingRemapper_rawMethodMaps = FastTypeBuilder.create().setInsideClass(FMLDeobfuscatingRemapper.class).setName("rawMethodMaps").asField();
        public static final FastField<Map<String, Map<String, String>>> FMLDeobfuscatingRemapper_fieldNameMaps = FastTypeBuilder.create().setInsideClass(FMLDeobfuscatingRemapper.class).setName("fieldNameMaps").asField();
        public static final FastField<Map<String, Map<String, String>>> FMLDeobfuscatingRemapper_methodNameMaps = FastTypeBuilder.create().setInsideClass(FMLDeobfuscatingRemapper.class).setName("methodNameMaps").asField();
    }
}
