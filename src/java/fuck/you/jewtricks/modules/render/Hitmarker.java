//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.render;

import com.matt.forgehax.util.mod.loader.*;
import net.minecraft.client.gui.*;
import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.command.*;
import fuck.you.jewtricks.base.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import fuck.you.jewtricks.events.*;
import com.matt.forgehax.util.color.*;
import com.matt.forgehax.util.draw.*;
import net.minecraftforge.client.event.sound.*;
import net.minecraftforge.client.event.*;
import fuck.you.jewtricks.*;
import net.minecraft.init.*;

@RegisterMod
public class Hitmarker extends ToggleMod
{
    public final Setting<DrawType> draw;
    public final Setting<SoundType> sound;
    public final Setting<CritSoundType> crit;
    public final Setting<Integer> time;
    public final Setting<Integer> fadeofftime;
    private ScaledResolution scaledres;
    private long lasthit;
    private int alpha;
    
    public Hitmarker() {
        super(Category.RENDER, "Hitmarker", false, "Draw something when you hit something");
        this.draw = (Setting<DrawType>)((SettingEnumBuilder)((SettingEnumBuilder)this.getCommandStub().builders().newSettingEnumBuilder().name("draw")).description("[OFF/COD/RECT/ANIMRECT]")).defaultTo((Enum)DrawType.COD).build();
        this.sound = (Setting<SoundType>)((SettingEnumBuilder)((SettingEnumBuilder)this.getCommandStub().builders().newSettingEnumBuilder().name("sound")).description("[OFF/COD/WARNING/QUAKE/EXPERIENCE]")).defaultTo((Enum)SoundType.OFF).build();
        this.crit = (Setting<CritSoundType>)((SettingEnumBuilder)((SettingEnumBuilder)this.getCommandStub().builders().newSettingEnumBuilder().name("crit")).description("[OFF/TF2]")).defaultTo((Enum)CritSoundType.OFF).build();
        this.time = (Setting<Integer>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("time")).description("Draw time (in ms)")).defaultTo((Object)300).build();
        this.fadeofftime = (Setting<Integer>)((SettingBuilder)((SettingBuilder)this.getCommandStub().builders().newSettingBuilder().name("fadeoff")).description("Fade off time (in ms)")).defaultTo((Object)200).build();
        this.scaledres = new ScaledResolution(Wrapper.getMinecraft());
        this.lasthit = -1L;
        this.alpha = 255;
    }
    
    @SubscribeEvent
    public void onLivingAttack(final LivingAttackEvent event) {
        try {
            if (event.getSource() == null) {
                return;
            }
            if (!event.getEntity().world.isRemote) {
                return;
            }
            if ((event.getSource().getImmediateSource() != null && event.getSource().getImmediateSource().equals((Object)Wrapper.getLocalPlayer())) || (event.getSource().getTrueSource() != null && event.getSource().getTrueSource().equals((Object)Wrapper.getLocalPlayer()))) {
                if (this.draw.get() != DrawType.OFF) {
                    this.lasthit = System.currentTimeMillis();
                    this.alpha = 255;
                }
                if (this.sound.get() != SoundType.OFF) {
                    final SoundEvent soundevent = this.getSound();
                    if (soundevent != null) {
                        Wrapper.getLocalPlayer().playSound(soundevent, 1.0f, 1.0f);
                    }
                }
                if (this.crit.get() != CritSoundType.OFF && this.checkCrit()) {
                    final SoundEvent critsoundevent = this.getCritSound();
                    if (critsoundevent != null) {
                        Wrapper.getLocalPlayer().playSound(critsoundevent, 1.0f, 1.0f);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @SubscribeEvent
    public void onRender(final RenderEvent event) {
        if (this.lasthit == -1L) {
            return;
        }
        final long timediff = System.currentTimeMillis() - this.lasthit;
        if (((DrawType)this.draw.get()).equals(DrawType.OFF) || timediff > this.time.getAsLong()) {
            this.lasthit = -1L;
            return;
        }
        this.updateAlpha(timediff);
        final int x = this.scaledres.getScaledWidth() / 2;
        final int y = this.scaledres.getScaledHeight() / 2;
        if (this.alpha > 0) {
            SurfaceHelper.drawLine(x - 6, y - 6, x - 2, y - 2, Color.of(0, 0, 0, this.alpha).toBuffer(), 2.0f);
            SurfaceHelper.drawLine(x - 6, y - 6, x - 2, y - 2, Color.of(255, 255, 255, this.alpha).toBuffer(), 1.0f);
            SurfaceHelper.drawLine(x - 6, y + 6, x - 2, y + 2, Color.of(0, 0, 0, this.alpha).toBuffer(), 2.0f);
            SurfaceHelper.drawLine(x - 6, y + 6, x - 2, y + 2, Color.of(255, 255, 255, this.alpha).toBuffer(), 1.0f);
            SurfaceHelper.drawLine(x + 2, y - 2, x + 6, y - 6, Color.of(0, 0, 0, this.alpha).toBuffer(), 2.0f);
            SurfaceHelper.drawLine(x + 2, y - 2, x + 6, y - 6, Color.of(255, 255, 255, this.alpha).toBuffer(), 1.0f);
            SurfaceHelper.drawLine(x + 2, y + 2, x + 6, y + 6, Color.of(0, 0, 0, this.alpha).toBuffer(), 2.0f);
            SurfaceHelper.drawLine(x + 2, y + 2, x + 6, y + 6, Color.of(255, 255, 255, this.alpha).toBuffer(), 1.0f);
        }
    }
    
    @SubscribeEvent
    public void playSound(final PlaySoundEvent event) {
        if (event.getName().equals("entity.player.attack.crit")) {}
    }
    
    @SubscribeEvent
    public void onScreenUpdated(final GuiScreenEvent.InitGuiEvent.Post event) {
        this.scaledres = new ScaledResolution(Wrapper.getMinecraft());
    }
    
    public SoundEvent getSound() {
        if (this.sound.get() == SoundType.OFF) {
            return null;
        }
        switch ((SoundType)this.sound.get()) {
            case COD: {
                return Sounds.INSTANCE.HITMARKER_COD;
            }
            case WARNING: {
                return Sounds.INSTANCE.HITMARKER_WARNING;
            }
            case QUAKE: {
                return Sounds.INSTANCE.HITMARKER_QUAKE;
            }
            case EXPERIENCE: {
                return SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP;
            }
            default: {
                return null;
            }
        }
    }
    
    public SoundEvent getCritSound() {
        if (this.crit.get() == CritSoundType.OFF) {
            return null;
        }
        switch ((CritSoundType)this.crit.get()) {
            case TF2: {
                return Sounds.INSTANCE.CRITSOUND_TF2;
            }
            default: {
                return null;
            }
        }
    }
    
    public boolean checkCrit() {
        return Wrapper.getLocalPlayer().fallDistance > 0.0f && !Wrapper.getLocalPlayer().onGround && !Wrapper.getLocalPlayer().isPotionActive(MobEffects.BLINDNESS) && !Wrapper.getLocalPlayer().isRiding();
    }
    
    public void updateAlpha(final long timediff) {
        if (this.fadeofftime.getAsInteger() == 0 || timediff < this.time.getAsLong() - this.fadeofftime.getAsLong()) {
            this.alpha = 255;
            return;
        }
        final float frequency = 255.0f / (this.fadeofftime.getAsFloat() / 1000.0f);
        this.alpha -= (int)(frequency * Wrapper.getFrametime());
        if (this.alpha < 0) {
            this.alpha = 0;
        }
        else if (this.alpha > 255) {
            this.alpha = 255;
        }
    }
    
    enum DrawType
    {
        OFF, 
        COD, 
        RECT, 
        ANIMRECT;
    }
    
    enum SoundType
    {
        OFF, 
        COD, 
        WARNING, 
        QUAKE, 
        EXPERIENCE;
    }
    
    enum CritSoundType
    {
        OFF, 
        TF2;
    }
}
