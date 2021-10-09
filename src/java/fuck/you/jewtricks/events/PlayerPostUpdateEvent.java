//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.events;

import net.minecraftforge.fml.common.eventhandler.*;

@Cancelable
public class PlayerPostUpdateEvent extends Event
{
    private double posX;
    private double posY;
    private double posZ;
    private float rotationYaw;
    private float rotationPitch;
    private boolean onGround;
    
    public PlayerPostUpdateEvent(final double posX, final double posY, final double posZ, final float rotationYaw, final float rotationPitch, final boolean onGround) {
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.rotationYaw = rotationYaw;
        this.rotationPitch = rotationPitch;
        this.onGround = onGround;
    }
    
    public double getX() {
        return this.posX;
    }
    
    public void setX(final double posX) {
        this.posX = posX;
    }
    
    public double getY() {
        return this.posY;
    }
    
    public void setY(final double posY) {
        this.posY = posY;
    }
    
    public double getZ() {
        return this.posZ;
    }
    
    public void setZ(final double posZ) {
        this.posZ = posZ;
    }
    
    public float getYaw() {
        return this.rotationYaw;
    }
    
    public void setYaw(final float rotationYaw) {
        this.rotationYaw = rotationYaw;
    }
    
    public float getPitch() {
        return this.rotationPitch;
    }
    
    public void setPitch(final float rotationPitch) {
        this.rotationPitch = rotationPitch;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
}
