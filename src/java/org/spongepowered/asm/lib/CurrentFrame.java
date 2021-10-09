//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib;

class CurrentFrame extends Frame
{
    @Override
    void execute(final int opcode, final int arg, final ClassWriter cw, final Item item) {
        super.execute(opcode, arg, cw, item);
        final Frame successor = new Frame();
        this.merge(cw, successor, 0);
        this.set(successor);
        this.owner.inputStackTop = 0;
    }
}
