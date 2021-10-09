//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.reflection;

import com.matt.forgehax.asm.*;
import java.nio.*;
import net.minecraft.network.datasync.*;
import net.minecraft.network.*;
import net.minecraft.util.text.*;
import java.util.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.tileentity.*;
import net.minecraft.potion.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.network.play.server.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.passive.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.nbt.*;
import net.minecraft.client.settings.*;
import net.minecraft.item.*;
import net.minecraft.world.chunk.*;
import com.matt.forgehax.asm.utils.fasttype.*;
import net.minecraft.world.chunk.storage.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import net.minecraft.block.state.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;

public interface FastReflection extends ASMCommon
{
    public interface Fields
    {
        public static final FastField<FloatBuffer> ActiveRenderInfo_MODELVIEW = FastTypeBuilder.create().setInsideClass(ActiveRenderInfo.class).setName("MODELVIEW").autoAssign().asField();
        public static final FastField<FloatBuffer> ActiveRenderInfo_PROJECTION = FastTypeBuilder.create().setInsideClass(ActiveRenderInfo.class).setName("PROJECTION").autoAssign().asField();
        public static final FastField<Vec3d> ActiveRenderInfo_position = FastTypeBuilder.create().setInsideClass(ActiveRenderInfo.class).setName("position").autoAssign().asField();
        public static final FastField<Float> CPacketPlayer_pitch = FastTypeBuilder.create().setInsideClass(CPacketPlayer.class).setName("pitch").autoAssign().asField();
        public static final FastField<Float> CPacketPlayer_yaw = FastTypeBuilder.create().setInsideClass(CPacketPlayer.class).setName("yaw").autoAssign().asField();
        public static final FastField<Boolean> CPacketPlayer_moving = FastTypeBuilder.create().setInsideClass(CPacketPlayer.class).setName("moving").autoAssign().asField();
        public static final FastField<Boolean> CPacketPlayer_rotating = FastTypeBuilder.create().setInsideClass(CPacketPlayer.class).setName("rotating").autoAssign().asField();
        public static final FastField<Boolean> CPacketPlayer_onGround = FastTypeBuilder.create().setInsideClass(CPacketPlayer.class).setName("onGround").autoAssign().asField();
        public static final FastField<Double> CPacketPlayer_x = FastTypeBuilder.create().setInsideClass(CPacketPlayer.class).setName("x").autoAssign().asField();
        public static final FastField<Double> CPacketPlayer_y = FastTypeBuilder.create().setInsideClass(CPacketPlayer.class).setName("y").autoAssign().asField();
        public static final FastField<Double> CPacketPlayer_z = FastTypeBuilder.create().setInsideClass(CPacketPlayer.class).setName("z").autoAssign().asField();
        public static final FastField<Float> CPacketVehicleMove_yaw = FastTypeBuilder.create().setInsideClass(CPacketVehicleMove.class).setName("yaw").autoAssign().asField();
        public static final FastField<Double> CPacketVehicleMove_y = FastTypeBuilder.create().setInsideClass(CPacketVehicleMove.class).setName("y").autoAssign().asField();
        public static final FastField<Integer> CPacketCloseWindow_windowId = FastTypeBuilder.create().setInsideClass(CPacketCloseWindow.class).setName("windowId").autoAssign().asField();
        public static final FastField<Integer> CPacketEntityAction_entityID = FastTypeBuilder.create().setInsideClass(CPacketEntityAction.class).setName("entityID").autoAssign().asField();
        public static final FastField<Float> SPacketPlayer_pitch = FastTypeBuilder.create().setInsideClass(SPacketPlayerPosLook.class).setName("pitch").autoAssign().asField();
        public static final FastField<Float> SPacketPlayer_yaw = FastTypeBuilder.create().setInsideClass(SPacketPlayerPosLook.class).setName("yaw").autoAssign().asField();
        public static final FastField<Double> SPacketPlayer_x = FastTypeBuilder.create().setInsideClass(SPacketPlayerPosLook.class).setName("x").autoAssign().asField();
        public static final FastField<Double> SPacketPlayer_y = FastTypeBuilder.create().setInsideClass(SPacketPlayerPosLook.class).setName("y").autoAssign().asField();
        public static final FastField<Double> SPacketPlayer_z = FastTypeBuilder.create().setInsideClass(SPacketPlayerPosLook.class).setName("z").autoAssign().asField();
        public static final FastField<EntityDataManager> Entity_dataManager = FastTypeBuilder.create().setInsideClass(Entity.class).setName("dataManager").autoAssign().asField();
        public static final FastField<Boolean> Entity_inPortal = FastTypeBuilder.create().setInsideClass(Entity.class).setName("inPortal").autoAssign().asField();
        public static final FastField<Integer> EntityPigZombie_angerLevel = FastTypeBuilder.create().setInsideClass(EntityPigZombie.class).setName("angerLevel").autoAssign().asField();
        public static final FastField<Boolean> EntityPlayer_sleeping = FastTypeBuilder.create().setInsideClass(EntityPlayer.class).setName("sleeping").autoAssign().asField();
        public static final FastField<Integer> EntityPlayer_sleepTimer = FastTypeBuilder.create().setInsideClass(EntityPlayer.class).setName("sleepTimer").autoAssign().asField();
        public static final FastField<Float> EntityPlayerSP_horseJumpPower = FastTypeBuilder.create().setInsideClass(EntityPlayerSP.class).setName("horseJumpPower").autoAssign().asField();
        public static final FastField<NetworkManager> GuiConnecting_networkManager = FastTypeBuilder.create().setInsideClass(GuiConnecting.class).setName("networkManager").autoAssign().asField();
        public static final FastField<GuiScreen> GuiDisconnected_parentScreen = FastTypeBuilder.create().setInsideClass(GuiDisconnected.class).setName("parentScreen").autoAssign().asField();
        public static final FastField<ITextComponent> GuiDisconnected_message = FastTypeBuilder.create().setInsideClass(GuiDisconnected.class).setName("message").autoAssign().asField();
        public static final FastField<String> GuiDisconnected_reason = FastTypeBuilder.create().setInsideClass(GuiDisconnected.class).setName("reason").autoAssign().asField();
        public static final FastField<Integer> Minecraft_leftClickCounter = FastTypeBuilder.create().setInsideClass(Minecraft.class).setName("leftClickCounter").autoAssign().asField();
        public static final FastField<Integer> Minecraft_rightClickDelayTimer = FastTypeBuilder.create().setInsideClass(Minecraft.class).setName("rightClickDelayTimer").autoAssign().asField();
        public static final FastField<Timer> Minecraft_timer = FastTypeBuilder.create().setInsideClass(Minecraft.class).setName("timer").autoAssign().asField();
        public static final FastField<Integer> PlayerControllerMP_blockHitDelay = FastTypeBuilder.create().setInsideClass(PlayerControllerMP.class).setName("blockHitDelay").autoAssign().asField();
        public static final FastField<Float> PlayerControllerMP_curBlockDamageMP = FastTypeBuilder.create().setInsideClass(PlayerControllerMP.class).setName("curBlockDamageMP").autoAssign().asField();
        public static final FastField<Integer> PlayerControllerMP_currentPlayerItem = FastTypeBuilder.create().setInsideClass(PlayerControllerMP.class).setName("currentPlayerItem").autoAssign().asField();
        public static final FastField<Integer> SPacketEntityVelocity_motionX = FastTypeBuilder.create().setInsideClass(SPacketEntityVelocity.class).setName("motionX").autoAssign().asField();
        public static final FastField<Integer> SPacketEntityVelocity_motionY = FastTypeBuilder.create().setInsideClass(SPacketEntityVelocity.class).setName("motionY").autoAssign().asField();
        public static final FastField<Integer> SPacketEntityVelocity_motionZ = FastTypeBuilder.create().setInsideClass(SPacketEntityVelocity.class).setName("motionZ").autoAssign().asField();
        public static final FastField<Float> SPacketExplosion_motionX = FastTypeBuilder.create().setInsideClass(SPacketExplosion.class).setName("motionX").autoAssign().asField();
        public static final FastField<Float> SPacketExplosion_motionY = FastTypeBuilder.create().setInsideClass(SPacketExplosion.class).setName("motionY").autoAssign().asField();
        public static final FastField<Float> SPacketExplosion_motionZ = FastTypeBuilder.create().setInsideClass(SPacketExplosion.class).setName("motionZ").autoAssign().asField();
        public static final FastField<Boolean> BufferBuilder_isDrawing = FastTypeBuilder.create().setInsideClass(BufferBuilder.class).setName("isDrawing").autoAssign().asField();
        public static final FastField<String> Session_username = FastTypeBuilder.create().setInsideClass(Session.class).setName("username").autoAssign().asField();
        public static final FastField<Map<ResourceLocation, ITextureObject>> TextureManager_mapTextureObjects = FastTypeBuilder.create().setInsideClass(TextureManager.class).setName("mapTextureObjects").autoAssign().asField();
        public static final FastField<ItemStack> EntityRenderer_itemActivationItem = FastTypeBuilder.create().setInsideClass(EntityRenderer.class).setName("itemActivationItem").autoAssign().asField();
        public static final FastField<IAttribute> AbstractHorse_JUMP_STRENGTH = FastTypeBuilder.create().setInsideClass(AbstractHorse.class).setName("JUMP_STRENGTH").autoAssign().asField();
        public static final FastField<IAttribute> SharedMonsterAttributes_MOVEMENT_SPEED = FastTypeBuilder.create().setInsideClass(SharedMonsterAttributes.class).setName("MOVEMENT_SPEED").autoAssign().asField();
        public static final FastField<TileEntitySign> GuiEditSign_tileSign = FastTypeBuilder.create().setInsideClass(GuiEditSign.class).setName("tileSign").autoAssign().asField();
        public static final FastField<Map<String, NBTBase>> NBTTag_tagMap = FastTypeBuilder.create().setInsideClass(NBTTagCompound.class).setName("tagMap").autoAssign().asField();
        public static final FastField<Float> Timer_tickLength = FastTypeBuilder.create().setInsideClass(Timer.class).setName("tickLength").autoAssign().asField();
        public static final FastField<Integer> Binding_pressTime = FastTypeBuilder.create().setInsideClass(KeyBinding.class).setName("pressTime").autoAssign().asField();
        public static final FastField<Boolean> Binding_pressed = FastTypeBuilder.create().setInsideClass(KeyBinding.class).setName("pressed").autoAssign().asField();
        public static final FastField<Float> ItemSword_attackDamage = FastTypeBuilder.create().setInsideClass(ItemSword.class).setName("attackDamage").autoAssign().asField();
        public static final FastField<Float> ItemTool_damageVsEntity = FastTypeBuilder.create().setInsideClass(ItemTool.class).setName("damageVsEntity").autoAssign().asField();
        public static final FastField<Float> ItemTool_attackSpeed = FastTypeBuilder.create().setInsideClass(ItemTool.class).setName("attackSpeed").autoAssign().asField();
        public static final FastField<PotionEffect> ItemFood_potionId = FastTypeBuilder.create().setInsideClass(ItemFood.class).setName("potionId").autoAssign().asField();
        public static final FastField<ExtendedBlockStorage[]> Chunk_storageArrays = FastTypeBuilder.create().setInsideClass(Chunk.class).setName("storageArrays").autoAssign().asField();
    }
    
    public interface Methods
    {
        public static final FastMethod<Boolean> Block_onBlockActivated = FastTypeBuilder.create().setInsideClass(Block.class).setName("onBlockActivated").setParameters(World.class, BlockPos.class, IBlockState.class, EntityPlayer.class, EnumHand.class, EnumFacing.class, Float.TYPE, Float.TYPE, Float.TYPE).setReturnType(Boolean.TYPE).autoAssign().asMethod();
        public static final FastMethod<Boolean> Entity_getFlag = FastTypeBuilder.create().setInsideClass(Entity.class).setName("getFlag").setParameters(Integer.TYPE).setReturnType(Boolean.TYPE).autoAssign().asMethod();
        public static final FastMethod<Void> Entity_setFlag = FastTypeBuilder.create().setInsideClass(Entity.class).setName("setFlag").setParameters(Integer.TYPE, Boolean.TYPE).setReturnType(Void.TYPE).autoAssign().asMethod();
        public static final FastMethod<Void> EntityLivingBase_resetPotionEffectMetadata = FastTypeBuilder.create().setInsideClass(EntityLivingBase.class).setName("resetPotionEffectMetadata").setParameters((Class<?>[])new Class[0]).setReturnType(Void.TYPE).autoAssign().asMethod();
        public static final FastMethod<Void> Minecraft_clickMouse = FastTypeBuilder.create().setInsideClass(Minecraft.class).setName("clickMouse").setParameters((Class<?>[])new Class[0]).setReturnType(Void.TYPE).autoAssign().asMethod();
        public static final FastMethod<Void> Minecraft_rightClickMouse = FastTypeBuilder.create().setInsideClass(Minecraft.class).setName("rightClickMouse").setParameters((Class<?>[])new Class[0]).setReturnType(Void.TYPE).autoAssign().asMethod();
        public static final FastMethod<Void> KeyBinding_unPress = FastTypeBuilder.create().setInsideClass(KeyBinding.class).setName("unpressKey").setParameters((Class<?>[])new Class[0]).setReturnType(Void.TYPE).autoAssign().asMethod();
        public static final FastMethod<AnvilChunkLoader> AnvilChunkLoader_writeChunkToNBT = FastTypeBuilder.create().setInsideClass(AnvilChunkLoader.class).setName("writeChunkToNBT").setParameters(Chunk.class, World.class, NBTTagCompound.class).setReturnType(Void.TYPE).autoAssign().asMethod();
    }
}
