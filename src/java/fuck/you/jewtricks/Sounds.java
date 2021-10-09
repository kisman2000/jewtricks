//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks;

import fuck.you.jewtricks.base.*;
import net.minecraft.util.*;

public class Sounds
{
    public static Sounds INSTANCE;
    public SoundEvent HITMARKER_COD;
    public SoundEvent HITMARKER_WARNING;
    public SoundEvent HITMARKER_QUAKE;
    public SoundEvent CRITSOUND_TF2;
    public SoundEvent PMSOUND_OLDSTEAM;
    public SoundEvent KILLSTREAK_DOMINATING;
    public SoundEvent KILLSTREAK_DOUBLEKILL;
    public SoundEvent KILLSTREAK_KILLINGSPREE;
    public SoundEvent KILLSTREAK_MEGAKILL;
    public SoundEvent KILLSTREAK_UNSTOPPABLE;
    public SoundEvent KILLSTREAK_WICKEDSICK;
    
    public void init() {
        try {
            this.HITMARKER_COD = this.getSound("hitmarker_cod");
            this.HITMARKER_WARNING = this.getSound("hitmarker_warning");
            this.HITMARKER_QUAKE = this.getSound("hitmarker_quake");
            this.CRITSOUND_TF2 = this.getSound("critsound_tf2");
            this.PMSOUND_OLDSTEAM = this.getSound("pmsound_oldsteam");
            this.KILLSTREAK_DOMINATING = this.getSound("killstreak_dominating");
            this.KILLSTREAK_DOUBLEKILL = this.getSound("killstreak_doublekill");
            this.KILLSTREAK_KILLINGSPREE = this.getSound("killstreak_killingspree");
            this.KILLSTREAK_MEGAKILL = this.getSound("killstreak_megakill");
            this.KILLSTREAK_UNSTOPPABLE = this.getSound("killstreak_unstoppable");
            this.KILLSTREAK_WICKEDSICK = this.getSound("killstreak_wickedsick");
        }
        catch (Exception e) {
            Wrapper.getLog().warn("!!! FAILED TO CREATE SOUND(S) !!!");
            e.printStackTrace();
        }
    }
    
    public SoundEvent getSound(final String name) {
        return new SoundEvent(new ResourceLocation("jewtricks", name));
    }
    
    static {
        Sounds.INSTANCE = new Sounds();
    }
}
