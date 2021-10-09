//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.events;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.text.*;

public class ReadPlayerListEvent extends Event
{
    private ITextComponent header;
    private ITextComponent footer;
    
    public ReadPlayerListEvent(final ITextComponent header, final ITextComponent footer) {
        this.header = header;
        this.footer = footer;
    }
    
    public ITextComponent getHeader() {
        return this.header;
    }
    
    public void setHeader(final ITextComponent header) {
        this.header = header;
    }
    
    public ITextComponent getFooter() {
        return this.footer;
    }
    
    public void setFooter(final ITextComponent footer) {
        this.footer = footer;
    }
}
