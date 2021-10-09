//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.modules.services;

import com.matt.forgehax.util.mod.*;
import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.command.callbacks.*;
import fuck.you.jewtricks.base.*;
import com.matt.forgehax.gui.*;
import net.minecraft.client.gui.*;
import com.matt.forgehax.util.command.*;
import java.util.function.*;

@RegisterMod
public class GuiService extends ServiceMod
{
    public GuiService() {
        super("GUI");
    }
    
    public void onBindPressed(final CallbackData cb) {
        if (Wrapper.getLocalPlayer() != null) {
            Wrapper.getMinecraft().displayGuiScreen((GuiScreen)ClickGui.getInstance());
        }
    }
    
    protected StubBuilder buildStubCommand(final StubBuilder builder) {
        return builder.kpressed((Consumer)this::onBindPressed).kdown((Consumer)this::onBindKeyDown).bind(21);
    }
}
