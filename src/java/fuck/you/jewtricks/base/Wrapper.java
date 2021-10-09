//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package fuck.you.jewtricks.base;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.world.*;
import com.matt.forgehax.util.command.*;
import com.matt.forgehax.util.mod.loader.*;
import com.matt.forgehax.util.*;
import net.minecraft.entity.*;
import javax.annotation.*;
import net.minecraft.tileentity.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.client.multiplayer.*;
import java.util.*;
import net.minecraft.util.text.*;
import com.google.common.base.*;
import com.matt.forgehax.util.mod.*;
import java.util.function.*;
import fuck.you.jewtricks.modules.services.*;
import org.apache.logging.log4j.*;

public class Wrapper
{
    private static Logger LOGGER;
    
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    
    public static EntityPlayerSP getLocalPlayer() {
        return getMinecraft().player;
    }
    
    public static World getWorld() {
        return (World)getMinecraft().world;
    }
    
    public static CommandGlobal getGlobalCommand() {
        return CommandGlobal.getInstance();
    }
    
    public static ModManager getModManager() {
        return ModManager.getInstance();
    }
    
    public static FileManager getFileManager() {
        return FileManager.getInstance();
    }
    
    public static Logger getLog() {
        return Wrapper.LOGGER;
    }
    
    public static Entity getRenderEntity() {
        return getMinecraft().getRenderViewEntity();
    }
    
    @Nullable
    public static Entity getRidingEntity() {
        if (getLocalPlayer() != null) {
            return getLocalPlayer().getRidingEntity();
        }
        return null;
    }
    
    public static Optional<Entity> getOptionalRidingEntity() {
        return Optional.ofNullable(getRidingEntity());
    }
    
    @Nullable
    public static Entity getRidingOrPlayer() {
        return (Entity)((getRidingEntity() != null) ? getRidingEntity() : getLocalPlayer());
    }
    
    public static World getWorld(final Entity entity) {
        return entity.getEntityWorld();
    }
    
    public static World getWorld(final TileEntity tileEntity) {
        return tileEntity.getWorld();
    }
    
    @Nullable
    public static NetworkManager getNetworkManager() {
        return FMLClientHandler.instance().getClientToServerNetworkManager();
    }
    
    public static PlayerControllerMP getPlayerController() {
        return getMinecraft().playerController;
    }
    
    public static void printMessageNaked(final String startWith, final String message, final Style firstStyle, final Style secondStyle) {
        if (!Strings.isNullOrEmpty(message)) {
            if (message.contains("\n")) {
                final Scanner scanner = new Scanner(message);
                scanner.useDelimiter("\n");
                Style s1 = firstStyle;
                Style s2 = secondStyle;
                while (scanner.hasNext()) {
                    printMessageNaked(startWith, scanner.next(), s1, s2);
                    final Style cpy = s1;
                    s1 = s2;
                    s2 = cpy;
                }
            }
            else {
                final TextComponentString string = new TextComponentString(startWith + message.replaceAll("\r", ""));
                string.setStyle(firstStyle);
                outputMessage(string.getFormattedText());
            }
        }
    }
    
    private static void outputMessage(final String text) {
        if (getLocalPlayer() != null) {
            getLocalPlayer().sendMessage((ITextComponent)new TextComponentString(text));
        }
    }
    
    public static void printMessageNaked(final String append, final String message, final Style style) {
        printMessageNaked(append, message, style, style);
    }
    
    public static void printMessageNaked(final String append, final String message) {
        printMessageNaked(append, message, new Style().setColor(TextFormatting.WHITE), new Style().setColor(TextFormatting.GRAY));
    }
    
    public static void printMessageNaked(final String message) {
        printMessageNaked("", message);
    }
    
    public static void printMessage(final String message) {
        if (!Strings.isNullOrEmpty(message)) {
            printMessageNaked("[FH] " + message);
        }
    }
    
    public static void printMessage(final String format, final Object... args) {
        printMessage(String.format(format, args));
    }
    
    private static ITextComponent getFormattedText(final String text, final TextFormatting color, final boolean bold, final boolean italic) {
        return new TextComponentString(text.replaceAll("\r", "")).setStyle(new Style().setColor(color).setBold(Boolean.valueOf(bold)).setItalic(Boolean.valueOf(italic)));
    }
    
    public static void printInform(final String format, final Object... args) {
        outputMessage(getFormattedText("[ForgeHax]", TextFormatting.GREEN, true, false).appendSibling(getFormattedText(" " + String.format(format, args).trim(), TextFormatting.GRAY, false, false)).getFormattedText());
    }
    
    public static void printWarning(final String format, final Object... args) {
        outputMessage(getFormattedText("[ForgeHax]", TextFormatting.YELLOW, true, false).appendSibling(getFormattedText(" " + String.format(format, args).trim(), TextFormatting.GRAY, false, false)).getFormattedText());
    }
    
    public static void printError(final String format, final Object... args) {
        outputMessage(getFormattedText("[ForgeHax]", TextFormatting.RED, true, false).appendSibling(getFormattedText(" " + String.format(format, args).trim(), TextFormatting.GRAY, false, false)).getFormattedText());
    }
    
    public static void printStackTrace(final Throwable t) {
        getLog().error(Throwables.getStackTraceAsString(t));
    }
    
    public static void handleThrowable(final Throwable t) {
        getLog().error(String.format("[%s] %s", t.getClass().getSimpleName(), Strings.nullToEmpty(t.getMessage())));
        if (t.getCause() != null) {
            handleThrowable(t.getCause());
        }
        printStackTrace(t);
    }
    
    public static void reloadChunks() {
        if (getWorld() != null && getLocalPlayer() != null) {
            final int x;
            final int y;
            final int z;
            final int distance;
            getMinecraft().addScheduledTask(() -> {
                x = (int)getLocalPlayer().posX;
                y = (int)getLocalPlayer().posY;
                z = (int)getLocalPlayer().posZ;
                distance = getMinecraft().gameSettings.renderDistanceChunks * 16;
                getMinecraft().renderGlobal.markBlockRangeForRenderUpdate(x - distance, y - distance, z - distance, x + distance, y + distance, z + distance);
            });
        }
    }
    
    public static void reloadChunksHard() {
        getMinecraft().addScheduledTask(() -> {
            if (getWorld() != null && getLocalPlayer() != null) {
                getMinecraft().renderGlobal.loadRenderers();
            }
        });
    }
    
    public static void printChat(final String str) {
        try {
            getMinecraft().ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(TextFormatting.WHITE + "[" + TextFormatting.GREEN + "jewtricks" + TextFormatting.WHITE + "]: " + str));
        }
        catch (Exception ex) {}
    }
    
    public static boolean isModEnabled(final Class<? extends BaseMod> clazz) {
        return getModManager().get((Class)clazz).map(BaseMod::isEnabled).orElse(false);
    }
    
    public static int getFPS() {
        return FPSService.INSTANCE.getFPS();
    }
    
    public static float getFrametime() {
        return FPSService.INSTANCE.getFrametime();
    }
    
    static {
        Wrapper.LOGGER = LogManager.getLogger("JewTricks");
    }
}
