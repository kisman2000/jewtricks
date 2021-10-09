//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm;

import com.matt.forgehax.asm.utils.asmtype.builders.*;
import com.matt.forgehax.asm.utils.asmtype.*;

public interface TypesSpecial
{
    public interface Classes
    {
        public static final ASMClass SchematicPrinter = ASMBuilders.newClassBuilder().setClassName("com/github/lunatrius/schematica/client/printer/SchematicPrinter").build();
    }
    
    public interface Methods
    {
        public static final ASMMethod SchematicPrinter_placeBlock = Classes.SchematicPrinter.childMethod().setName("placeBlock").setReturnType(Boolean.TYPE).beginParameters().unobfuscated().add(TypesMc.Classes.WorldClient).add(TypesMc.Classes.EntityPlayerSP).add(TypesMc.Classes.ItemStack).add(TypesMc.Classes.BlockPos).add(TypesMc.Classes.EnumFacing).add(TypesMc.Classes.Vec3d).add(TypesMc.Classes.EnumHand).finish().build();
    }
    
    public interface Fields
    {
    }
}
