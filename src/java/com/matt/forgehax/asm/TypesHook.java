//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm;

import com.matt.forgehax.asm.utils.asmtype.builders.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public interface TypesHook
{
    public interface Classes
    {
        public static final ASMClass ForgeHaxHooks = ASMBuilders.newClassBuilder().setClassName("com/matt/forgehax/asm/ForgeHaxHooks").build();
    }
    
    public interface Fields
    {
        public static final ASMField ForgeHaxHooks_isSafeWalkActivated = Classes.ForgeHaxHooks.childField().setName("isSafeWalkActivated").setType(Boolean.TYPE).build();
        public static final ASMField ForgeHaxHooks_isNoSlowDownActivated = Classes.ForgeHaxHooks.childField().setName("isNoSlowDownActivated").setType(Boolean.TYPE).build();
        public static final ASMField ForgeHaxHooks_isNoBoatGravityActivated = Classes.ForgeHaxHooks.childField().setName("isNoBoatGravityActivated").setType(Boolean.TYPE).build();
        public static final ASMField ForgeHaxHooks_isNoClampingActivated = Classes.ForgeHaxHooks.childField().setName("isNoClampingActivated").setType(Boolean.TYPE).build();
        public static final ASMField ForgeHaxHooks_isBoatSetYawActivated = Classes.ForgeHaxHooks.childField().setName("isBoatSetYawActivated").setType(Boolean.TYPE).build();
        public static final ASMField ForgeHaxHooks_isNotRowingBoatActivated = Classes.ForgeHaxHooks.childField().setName("isNotRowingBoatActivated").setType(Boolean.TYPE).build();
        public static final ASMField ForgeHaxHooks_doIncreaseTabListSize = Classes.ForgeHaxHooks.childField().setName("doIncreaseTabListSize").setType(Boolean.TYPE).build();
    }
    
    public interface Methods
    {
        public static final ASMMethod ForgeHaxHooks_onHurtcamEffect = Classes.ForgeHaxHooks.childMethod().setName("onHurtcamEffect").setReturnType(Boolean.TYPE).beginParameters().add(Float.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_onSendingPacket = Classes.ForgeHaxHooks.childMethod().setName("onSendingPacket").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.Packet).finish().build();
        public static final ASMMethod ForgeHaxHooks_onSentPacket = Classes.ForgeHaxHooks.childMethod().setName("onSentPacket").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.Packet).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPreReceived = Classes.ForgeHaxHooks.childMethod().setName("onPreReceived").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.Packet).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPostReceived = Classes.ForgeHaxHooks.childMethod().setName("onPostReceived").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.Packet).finish().build();
        public static final ASMMethod ForgeHaxHooks_onWaterMovement = Classes.ForgeHaxHooks.childMethod().setName("onWaterMovement").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.Entity).add(TypesMc.Classes.Vec3d).finish().build();
        public static final ASMMethod ForgeHaxHooks_onApplyCollisionMotion = Classes.ForgeHaxHooks.childMethod().setName("onApplyCollisionMotion").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.Entity).add(TypesMc.Classes.Entity).add(Double.TYPE).add(Double.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPutColorMultiplier = Classes.ForgeHaxHooks.childMethod().setName("onPutColorMultiplier").setReturnType(Integer.TYPE).beginParameters().add(Float.TYPE).add(Float.TYPE).add(Float.TYPE).add(Integer.TYPE).add(boolean[].class).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPreRenderBlockLayer = Classes.ForgeHaxHooks.childMethod().setName("onPreRenderBlockLayer").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.BlockRenderLayer).add(Double.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPostRenderBlockLayer = Classes.ForgeHaxHooks.childMethod().setName("onPostRenderBlockLayer").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.BlockRenderLayer).add(Double.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_onRenderBlockInLayer = Classes.ForgeHaxHooks.childMethod().setName("onRenderBlockInLayer").setReturnType(TypesMc.Classes.BlockRenderLayer).beginParameters().add(TypesMc.Classes.Block).add(TypesMc.Classes.IBlockState).add(TypesMc.Classes.BlockRenderLayer).add(TypesMc.Classes.BlockRenderLayer).finish().build();
        public static final ASMMethod ForgeHaxHooks_onSetupTerrain = Classes.ForgeHaxHooks.childMethod().setName("onSetupTerrain").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.Entity).add(Boolean.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_isBlockFiltered = Classes.ForgeHaxHooks.childMethod().setName("isBlockFiltered").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.Entity).add(TypesMc.Classes.IBlockState).finish().build();
        public static final ASMMethod ForgeHaxHooks_onAddCollisionBoxToList = Classes.ForgeHaxHooks.childMethod().setName("onAddCollisionBoxToList").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.Block).add(TypesMc.Classes.IBlockState).add(TypesMc.Classes.World).add(TypesMc.Classes.BlockPos).add(TypesMc.Classes.AxisAlignedBB).add(List.class).add(TypesMc.Classes.Entity).add(Boolean.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_onBlockRenderInLoop = Classes.ForgeHaxHooks.childMethod().setName("onBlockRenderInLoop").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.RenderChunk).add(TypesMc.Classes.Block).add(TypesMc.Classes.IBlockState).add(TypesMc.Classes.BlockPos).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPreBuildChunk = Classes.ForgeHaxHooks.childMethod().setName("onPreBuildChunk").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.RenderChunk).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPostBuildChunk = Classes.ForgeHaxHooks.childMethod().setName("onPostBuildChunk").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.RenderChunk).finish().build();
        public static final ASMMethod ForgeHaxHooks_onDeleteGlResources = Classes.ForgeHaxHooks.childMethod().setName("onDeleteGlResources").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.RenderChunk).finish().build();
        public static final ASMMethod ForgeHaxHooks_onChunkUploaded = Classes.ForgeHaxHooks.childMethod().setName("onChunkUploaded").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.RenderChunk).add(TypesMc.Classes.BufferBuilder).finish().build();
        public static final ASMMethod ForgeHaxHooks_onAddRenderChunk = Classes.ForgeHaxHooks.childMethod().setName("onAddRenderChunk").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.RenderChunk).add(TypesMc.Classes.BlockRenderLayer).finish().build();
        public static final ASMMethod ForgeHaxHooks_onLoadRenderers = Classes.ForgeHaxHooks.childMethod().setName("onLoadRenderers").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.ViewFrustum).add(TypesMc.Classes.ChunkRenderDispatcher).finish().build();
        public static final ASMMethod ForgeHaxHooks_onWorldRendererDeallocated = Classes.ForgeHaxHooks.childMethod().setName("onWorldRendererDeallocated").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.ChunkCompileTaskGenerator).finish().build();
        public static final ASMMethod ForgeHaxHooks_shouldDisableCaveCulling = Classes.ForgeHaxHooks.childMethod().setName("shouldDisableCaveCulling").setReturnType(Boolean.TYPE).emptyParameters().build();
        public static final ASMMethod ForgeHaxHooks_onJournyMapSetStratumColor = Classes.ForgeHaxHooks.childMethod().setName("onJournyMapSetStratumColor").setReturnType(Boolean.TYPE).beginParameters().add(Object.class).add(Object.class).add(Integer.TYPE).add(Integer.class).add(Boolean.TYPE).add(Boolean.TYPE).add(Boolean.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_onUpdateWalkingPlayerPre = Classes.ForgeHaxHooks.childMethod().setName("onUpdateWalkingPlayerPre").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.EntityPlayerSP).finish().build();
        public static final ASMMethod ForgeHaxHooks_onUpdateWalkingPlayerPost = Classes.ForgeHaxHooks.childMethod().setName("onUpdateWalkingPlayerPost").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.EntityPlayerSP).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPushOutOfBlocks = Classes.ForgeHaxHooks.childMethod().setName("onPushOutOfBlocks").setReturnType(Boolean.TYPE).emptyParameters().build();
        public static final ASMMethod ForgeHaxHooks_onRenderBoat = Classes.ForgeHaxHooks.childMethod().setName("onRenderBoat").setReturnType(Float.TYPE).beginParameters().add(TypesMc.Classes.EntityBoat).add(Float.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_onSchematicaPlaceBlock = Classes.ForgeHaxHooks.childMethod().setName("onSchematicaPlaceBlock").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.ItemStack).add(TypesMc.Classes.BlockPos).add(TypesMc.Classes.Vec3d).add(TypesMc.Classes.EnumFacing).finish().build();
        public static final ASMMethod ForgeHaxHooks_onWorldCheckLightFor = Classes.ForgeHaxHooks.childMethod().setName("onWorldCheckLightFor").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.EnumSkyBlock).add(TypesMc.Classes.BlockPos).finish().build();
        public static final ASMMethod ForgeHaxHooks_onLeftClickCounterSet = Classes.ForgeHaxHooks.childMethod().setName("onLeftClickCounterSet").setReturnType(Integer.TYPE).beginParameters().add(Integer.TYPE).add(TypesMc.Classes.Minecraft).finish().build();
        public static final ASMMethod ForgeHaxHooks_onSendClickBlockToController = Classes.ForgeHaxHooks.childMethod().setName("onSendClickBlockToController").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.Minecraft).add(Boolean.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPlayerItemSync = Classes.ForgeHaxHooks.childMethod().setName("onPlayerItemSync").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.PlayerControllerMP).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPlayerBreakingBlock = Classes.ForgeHaxHooks.childMethod().setName("onPlayerBreakingBlock").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.PlayerControllerMP).add(TypesMc.Classes.BlockPos).add(TypesMc.Classes.EnumFacing).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPlayerAttackEntity = Classes.ForgeHaxHooks.childMethod().setName("onPlayerAttackEntity").setReturnType(Void.TYPE).beginParameters().add(TypesMc.Classes.PlayerControllerMP).add(TypesMc.Classes.EntityPlayer).add(TypesMc.Classes.Entity).finish().build();
        public static final ASMMethod ForgeHaxHooks_onPlayerStopUse = Classes.ForgeHaxHooks.childMethod().setName("onPlayerStopUse").setReturnType(Boolean.TYPE).beginParameters().add(TypesMc.Classes.PlayerControllerMP).add(TypesMc.Classes.EntityPlayer).finish().build();
        public static final ASMMethod ForgeHaxHooks_onEntityBlockSlipApply = Classes.ForgeHaxHooks.childMethod().setName("onEntityBlockSlipApply").setReturnType(Float.TYPE).beginParameters().add(Float.TYPE).add(TypesMc.Classes.EntityLivingBase).add(TypesMc.Classes.IBlockState).add(Integer.TYPE).finish().build();
        public static final ASMMethod ForgeHaxHooks_fireEvent_v = Classes.ForgeHaxHooks.childMethod().setName("fireEvent_v").setReturnType(Void.TYPE).beginParameters().add(Event.class).finish().build();
        public static final ASMMethod ForgeHaxHooks_fireEvent_b = Classes.ForgeHaxHooks.childMethod().setName("fireEvent_b").setReturnType(Boolean.TYPE).beginParameters().add(Event.class).finish().build();
        public static final ASMMethod ForgeHaxHooks_onDrawBoundingBox_Post = Classes.ForgeHaxHooks.childMethod().setName("onDrawBoundingBoxPost").setReturnType(Void.TYPE).emptyParameters().build();
    }
}
