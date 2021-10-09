//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm;

import com.matt.forgehax.asm.utils.asmtype.builders.*;
import com.matt.forgehax.asm.utils.asmtype.*;
import java.util.*;
import com.google.common.util.concurrent.*;
import io.netty.util.concurrent.*;
import io.netty.channel.*;

public interface TypesMc
{
    public interface Classes
    {
        public static final ASMClass Packet = ASMBuilders.newClassBuilder().setClassName("net/minecraft/network/Packet").autoAssign().build();
        public static final ASMClass AxisAlignedBB = ASMBuilders.newClassBuilder().setClassName("net/minecraft/util/math/AxisAlignedBB").autoAssign().build();
        public static final ASMClass Material = ASMBuilders.newClassBuilder().setClassName("net/minecraft/block/material/Material").autoAssign().build();
        public static final ASMClass Entity = ASMBuilders.newClassBuilder().setClassName("net/minecraft/entity/Entity").autoAssign().build();
        public static final ASMClass EntityLivingBase = ASMBuilders.newClassBuilder().setClassName("net/minecraft/entity/EntityLivingBase").autoAssign().build();
        public static final ASMClass Vec3d = ASMBuilders.newClassBuilder().setClassName("net/minecraft/util/math/Vec3d").autoAssign().build();
        public static final ASMClass BlockRenderLayer = ASMBuilders.newClassBuilder().setClassName("net/minecraft/util/BlockRenderLayer").autoAssign().build();
        public static final ASMClass IBlockState = ASMBuilders.newClassBuilder().setClassName("net/minecraft/block/state/IBlockState").autoAssign().build();
        public static final ASMClass BlockPos = ASMBuilders.newClassBuilder().setClassName("net/minecraft/util/math/BlockPos").autoAssign().build();
        public static final ASMClass Block = ASMBuilders.newClassBuilder().setClassName("net/minecraft/block/Block").autoAssign().build();
        public static final ASMClass ICamera = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/culling/ICamera").autoAssign().build();
        public static final ASMClass VisGraph = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/chunk/VisGraph").autoAssign().build();
        public static final ASMClass SetVisibility = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/chunk/SetVisibility").autoAssign().build();
        public static final ASMClass Minecraft = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/Minecraft").autoAssign().build();
        public static final ASMClass NetworkManager$4 = ASMBuilders.newClassBuilder().setClassName("net/minecraft/network/NetworkManager$4").autoAssign().build();
        public static final ASMClass IBlockAccess = ASMBuilders.newClassBuilder().setClassName("net/minecraft/world/IBlockAccess").autoAssign().build();
        public static final ASMClass BufferBuilder = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/BufferBuilder").autoAssign().build();
        public static final ASMClass MoverType = ASMBuilders.newClassBuilder().setClassName("net/minecraft/entity/MoverType").autoAssign().build();
        public static final ASMClass WorldProvider = ASMBuilders.newClassBuilder().setClassName("net/minecraft/world/WorldProvider").autoAssign().build();
        public static final ASMClass World = ASMBuilders.newClassBuilder().setClassName("net/minecraft/world/World").autoAssign().build();
        public static final ASMClass IBakedModel = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/block/model/IBakedModel").autoAssign().build();
        public static final ASMClass CompiledChunk = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/chunk/CompiledChunk").autoAssign().build();
        public static final ASMClass RenderChunk = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/chunk/RenderChunk").autoAssign().build();
        public static final ASMClass ChunkCompileTaskGenerator = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/chunk/ChunkCompileTaskGenerator").autoAssign().build();
        public static final ASMClass ChunkCache = ASMBuilders.newClassBuilder().setClassName("net/minecraft/world/ChunkCache").autoAssign().build();
        public static final ASMClass ViewFrustum = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/ViewFrustum").autoAssign().build();
        public static final ASMClass ChunkRenderDispatcher = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/chunk/ChunkRenderDispatcher").autoAssign().build();
        public static final ASMClass RenderGlobal = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/RenderGlobal").autoAssign().build();
        public static final ASMClass ChunkRenderContainer = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/ChunkRenderContainer").autoAssign().build();
        public static final ASMClass ChunkRenderWorker = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/chunk/ChunkRenderWorker").autoAssign().build();
        public static final ASMClass EntityPlayer = ASMBuilders.newClassBuilder().setClassName("net/minecraft/entity/player/EntityPlayer").autoAssign().build();
        public static final ASMClass EntityPlayerSP = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/entity/EntityPlayerSP").autoAssign().build();
        public static final ASMClass EntityBoat = ASMBuilders.newClassBuilder().setClassName("net/minecraft/entity/item/EntityBoat").autoAssign().build();
        public static final ASMClass EntityRenderer = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/EntityRenderer").autoAssign().build();
        public static final ASMClass RenderBoat = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/renderer/entity/RenderBoat").autoAssign().build();
        public static final ASMClass NetworkManager = ASMBuilders.newClassBuilder().setClassName("net/minecraft/network/NetworkManager").autoAssign().build();
        public static final ASMClass GuiScreen = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/gui/GuiScreen").autoAssign().build();
        public static final ASMClass GuiMainMenu = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/gui/GuiMainMenu").autoAssign().build();
        public static final ASMClass GuiPlayerTabOverlay = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/gui/GuiPlayerTabOverlay").autoAssign().build();
        public static final ASMClass Scoreboard = ASMBuilders.newClassBuilder().setClassName("net/minecraft/scoreboard/Scoreboard").autoAssign().build();
        public static final ASMClass ScoreObjective = ASMBuilders.newClassBuilder().setClassName("net/minecraft/scoreboard/ScoreObjective").autoAssign().build();
        public static final ASMClass KeyBinding = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/settings/KeyBinding").autoAssign().build();
        public static final ASMClass WorldClient = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/multiplayer/WorldClient").autoAssign().build();
        public static final ASMClass ItemStack = ASMBuilders.newClassBuilder().setClassName("net/minecraft/item/ItemStack").autoAssign().build();
        public static final ASMClass EnumFacing = ASMBuilders.newClassBuilder().setClassName("net/minecraft/util/EnumFacing").autoAssign().build();
        public static final ASMClass EnumHand = ASMBuilders.newClassBuilder().setClassName("net/minecraft/util/EnumHand").autoAssign().build();
        public static final ASMClass EnumSkyBlock = ASMBuilders.newClassBuilder().setClassName("net/minecraft/world/EnumSkyBlock").autoAssign().build();
        public static final ASMClass PlayerControllerMP = ASMBuilders.newClassBuilder().setClassName("net/minecraft/client/multiplayer/PlayerControllerMP").autoAssign().build();
    }
    
    public interface Fields
    {
        public static final ASMField NetworkManager$4_val$inPacket = Classes.NetworkManager$4.childField().setName("val$inPacket").setType(Classes.Packet).build();
        public static final ASMField RenderGlobal_viewFrustum = Classes.RenderGlobal.childField().setName("viewFrustum").setType(Classes.ViewFrustum).autoAssign().build();
        public static final ASMField RenderGlobal_renderDispatcher = Classes.RenderGlobal.childField().setName("renderDispatcher").setType(Classes.ChunkRenderDispatcher).autoAssign().build();
    }
    
    public interface Methods
    {
        public static final ASMMethod Block_canRenderInLayer = Classes.Block.childMethod().setName("canRenderInLayer").setReturnType(Boolean.TYPE).beginParameters().add(Classes.IBlockState).add(Classes.BlockRenderLayer).finish().autoAssign().build();
        public static final ASMMethod Block_addCollisionBoxToList = Classes.Block.childMethod().setName("addCollisionBoxToList").setReturnType(Void.TYPE).beginParameters().add(Classes.IBlockState).add(Classes.World).add(Classes.BlockPos).add(Classes.AxisAlignedBB).add(List.class).add(Classes.Entity).add(Boolean.TYPE).finish().autoAssign().build();
        public static final ASMMethod ChunkRenderContainer_addRenderChunk = Classes.ChunkRenderContainer.childMethod().setName("addRenderChunk").setReturnType(Void.TYPE).beginParameters().add(Classes.RenderChunk).add(Classes.BlockRenderLayer).finish().autoAssign().build();
        public static final ASMMethod ChunkRenderDispatcher_uploadChunk = Classes.ChunkRenderDispatcher.childMethod().setName("uploadChunk").setReturnType(ListenableFuture.class).beginParameters().add(Classes.BlockRenderLayer).add(Classes.BufferBuilder).add(Classes.RenderChunk).add(Classes.CompiledChunk).add(Double.TYPE).finish().autoAssign().build();
        public static final ASMMethod ChunkRenderWorker_freeRenderBuilder = Classes.ChunkRenderWorker.childMethod().setName("freeRenderBuilder").setReturnType(Void.TYPE).beginParameters().add(Classes.ChunkCompileTaskGenerator).finish().autoAssign().build();
        public static final ASMMethod Entity_applyEntityCollision = Classes.Entity.childMethod().setName("applyEntityCollision").setReturnType(Void.TYPE).beginParameters().add(Classes.Entity).finish().autoAssign().build();
        public static final ASMMethod Entity_move = Classes.Entity.childMethod().setName("move").setReturnType(Void.TYPE).beginParameters().add(Classes.MoverType).add(Double.TYPE).add(Double.TYPE).add(Double.TYPE).finish().autoAssign().build();
        public static final ASMMethod Entity_doBlockCollisions = Classes.Entity.childMethod().setName("doBlockCollisions").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod EntityPlayerSP_onLivingUpdate = Classes.EntityPlayerSP.childMethod().setName("onLivingUpdate").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod EntityPlayerSP_onUpdate = Classes.EntityPlayerSP.childMethod().setName("onUpdate").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod EntityPlayerSP_onUpdateWalkingPlayer = Classes.EntityPlayerSP.childMethod().setName("onUpdateWalkingPlayer").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod EntityPlayerSP_pushOutOfBlocks = Classes.EntityPlayerSP.childMethod().setName("pushOutOfBlocks").setReturnType(Boolean.TYPE).beginParameters().add(Double.TYPE).add(Double.TYPE).add(Double.TYPE).finish().autoAssign().build();
        public static final ASMMethod EntityPlayerSP_isRowingBoat = Classes.EntityPlayerSP.childMethod().setName("isRowingBoat").setReturnType(Boolean.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod EntityLivingBase_travel = Classes.EntityLivingBase.childMethod().setName("travel").setReturnType(Void.TYPE).beginParameters().add(Float.TYPE).add(Float.TYPE).add(Float.TYPE).finish().autoAssign().build();
        public static final ASMMethod EntityRenderer_hurtCameraEffect = Classes.EntityRenderer.childMethod().setName("hurtCameraEffect").setReturnType(Void.TYPE).beginParameters().add(Float.TYPE).finish().autoAssign().build();
        public static final ASMMethod Minecraft_setIngameFocus = Classes.Minecraft.childMethod().setName("setIngameFocus").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod Minecraft_runTick = Classes.Minecraft.childMethod().setName("runTick").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod Minecraft_sendClickBlockToController = Classes.Minecraft.childMethod().setName("sendClickBlockToController").setReturnType(Void.TYPE).beginParameters().add(Boolean.TYPE).finish().autoAssign().build();
        public static final ASMMethod NetworkManager$4_run = Classes.NetworkManager$4.childMethod().setName("run").setReturnType(Void.TYPE).emptyParameters().build();
        public static final ASMMethod NetworkManager_dispatchPacket = Classes.NetworkManager.childMethod().setName("dispatchPacket").setReturnType(Void.TYPE).beginParameters().add(Classes.Packet).add(GenericFutureListener[].class).finish().autoAssign().build();
        public static final ASMMethod NetworkManager_channelRead0 = Classes.NetworkManager.childMethod().setName("channelRead0").setObfuscatedName("a").setReturnType(Void.TYPE).beginParameters().add(ChannelHandlerContext.class).add(Classes.Packet).finish().build();
        public static final ASMMethod RenderChunk_rebuildChunk = Classes.RenderChunk.childMethod().setName("rebuildChunk").setReturnType(Void.TYPE).beginParameters().add(Float.TYPE).add(Float.TYPE).add(Float.TYPE).add(Classes.ChunkCompileTaskGenerator).finish().autoAssign().build();
        public static final ASMMethod RenderChunk_deleteGlResources = Classes.RenderChunk.childMethod().setName("deleteGlResources").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod RenderGlobal_loadRenderers = Classes.RenderGlobal.childMethod().setName("loadRenderers").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod RenderGlobal_renderBlockLayer = Classes.RenderGlobal.childMethod().setName("renderBlockLayer").setReturnType(Integer.TYPE).beginParameters().add(Classes.BlockRenderLayer).add(Double.TYPE).add(Integer.TYPE).add(Classes.Entity).finish().autoAssign().build();
        public static final ASMMethod RenderGlobal_setupTerrain = Classes.RenderGlobal.childMethod().setName("setupTerrain").setReturnType(Void.TYPE).beginParameters().add(Classes.Entity).add(Double.TYPE).add(Classes.ICamera).add(Integer.TYPE).add(Boolean.TYPE).finish().autoAssign().build();
        public static final ASMMethod RenderGlobal_drawBoundingBox = Classes.RenderGlobal.childMethod().setName("drawBoundingBox").setReturnType(Void.TYPE).beginParameters().add(Double.TYPE).add(Double.TYPE).add(Double.TYPE).add(Double.TYPE).add(Double.TYPE).add(Double.TYPE).add(Float.TYPE).add(Float.TYPE).add(Float.TYPE).add(Float.TYPE).finish().autoAssign().build();
        public static final ASMMethod BufferBuilder_putColorMultiplier = Classes.BufferBuilder.childMethod().setName("putColorMultiplier").setReturnType(Void.TYPE).beginParameters().add(Float.TYPE).add(Float.TYPE).add(Float.TYPE).add(Integer.TYPE).finish().autoAssign().build();
        public static final ASMMethod VisGraph_setOpaqueCube = Classes.VisGraph.childMethod().setName("setOpaqueCube").setReturnType(Void.TYPE).beginParameters().add(Classes.BlockPos).finish().autoAssign().build();
        public static final ASMMethod VisGraph_computeVisibility = Classes.VisGraph.childMethod().setName("computeVisibility").setReturnType(Classes.SetVisibility).emptyParameters().autoAssign().build();
        public static final ASMMethod World_handleMaterialAcceleration = Classes.World.childMethod().setName("handleMaterialAcceleration").setReturnType(Boolean.TYPE).beginParameters().add(Classes.AxisAlignedBB).add(Classes.Material).add(Classes.Entity).finish().autoAssign().build();
        public static final ASMMethod World_checkLightFor = Classes.World.childMethod().setName("checkLightFor").setReturnType(Boolean.TYPE).beginParameters().add(Classes.EnumSkyBlock).add(Classes.BlockPos).finish().autoAssign().build();
        public static final ASMMethod EntityBoat_updateMotion = Classes.EntityBoat.childMethod().setName("updateMotion").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod EntityBoat_controlBoat = Classes.EntityBoat.childMethod().setName("controlBoat").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod EntityBoat_applyYawToEntity = Classes.EntityBoat.childMethod().setName("applyYawToEntity").setReturnType(Void.TYPE).beginParameters().add(Classes.Entity).finish().autoAssign().build();
        public static final ASMMethod RenderBoat_doRender = Classes.RenderBoat.childMethod().setName("doRender").setReturnType(Void.TYPE).beginParameters().add(Classes.EntityBoat).add(Double.TYPE).add(Double.TYPE).add(Double.TYPE).add(Float.TYPE).add(Float.TYPE).finish().autoAssign().build();
        public static final ASMMethod PlayerTabOverlay_renderPlayerList = Classes.GuiPlayerTabOverlay.childMethod().setName("renderPlayerlist").setReturnType(Void.TYPE).beginParameters().add(Integer.TYPE).add(Classes.Scoreboard).add(Classes.ScoreObjective).finish().autoAssign().build();
        public static final ASMMethod KeyBinding_isKeyDown = Classes.KeyBinding.childMethod().setName("isKeyDown").setReturnType(Boolean.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod PlayerControllerMC_syncCurrentPlayItem = Classes.PlayerControllerMP.childMethod().setName("syncCurrentPlayItem").setReturnType(Void.TYPE).emptyParameters().autoAssign().build();
        public static final ASMMethod PlayerControllerMC_attackEntity = Classes.PlayerControllerMP.childMethod().setName("attackEntity").setReturnType(Void.TYPE).beginParameters().add(Classes.EntityPlayer).add(Classes.Entity).finish().autoAssign().build();
        public static final ASMMethod PlayerControllerMC_onPlayerDamageBlock = Classes.PlayerControllerMP.childMethod().setName("onPlayerDamageBlock").setReturnType(Boolean.TYPE).beginParameters().add(Classes.BlockPos).add(Classes.EnumFacing).finish().autoAssign().build();
        public static final ASMMethod PlayerControllerMC_onStoppedUsingItem = Classes.PlayerControllerMP.childMethod().setName("onStoppedUsingItem").setReturnType(Void.TYPE).beginParameters().add(Classes.EntityPlayer).finish().autoAssign().build();
    }
}
