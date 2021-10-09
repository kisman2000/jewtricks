//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package bspkrs.mmv;

public class McpBotCommand
{
    private final BotCommand command;
    private final String srgName;
    private final String newName;
    private final String comment;
    
    public static BotCommand getCommand(final MemberType type, final boolean isForced) {
        switch (type) {
            case METHOD: {
                return isForced ? BotCommand.FSM : BotCommand.SM;
            }
            case PARAM: {
                return isForced ? BotCommand.FSP : BotCommand.SP;
            }
            default: {
                return isForced ? BotCommand.FSF : BotCommand.SF;
            }
        }
    }
    
    public McpBotCommand(final BotCommand command, final String srgName, final String newName, final String comment) {
        this.command = command;
        this.srgName = srgName;
        this.newName = newName;
        this.comment = comment;
    }
    
    public McpBotCommand(final BotCommand command, final String srgName, final String newName) {
        this(command, srgName, newName, "");
    }
    
    public static McpBotCommand getMcpBotCommand(final MemberType type, final boolean isForced, final String srgName, final String newName, final String comment) {
        return new McpBotCommand(getCommand(type, isForced), srgName, newName, comment);
    }
    
    public BotCommand getCommand() {
        return this.command;
    }
    
    public String getNewName() {
        return this.newName;
    }
    
    @Override
    public String toString() {
        return String.format("!%s %s %s %s", this.command.toString().toLowerCase(), this.srgName, this.newName, this.comment);
    }
    
    public enum BotCommand
    {
        SF, 
        SM, 
        SP, 
        FSF, 
        FSM, 
        FSP;
    }
    
    public enum MemberType
    {
        FIELD, 
        METHOD, 
        PARAM;
    }
}
