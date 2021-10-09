//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.asm.utils.environment;

import com.matt.forgehax.asm.utils.remapping.*;
import java.util.function.*;

public class RuntimeState
{
    private static IStateMapper remapper;
    private static ThreadLocal<State> localState;
    private static State defaultState;
    
    public static State getDefaultState() {
        return RuntimeState.defaultState;
    }
    
    public static State getState() {
        return RuntimeState.localState.get();
    }
    
    public static void setState(final State state) {
        RuntimeState.localState.set(state);
    }
    
    public static void releaseState() {
        RuntimeState.localState.remove();
    }
    
    public static void markDefaultAsNormal() {
        RuntimeState.defaultState = State.NORMAL;
    }
    
    public static void markDefaultAsSrg() {
        RuntimeState.defaultState = State.SRG;
    }
    
    public static void markDefaultAsObfuscated() {
        RuntimeState.defaultState = State.OBFUSCATED;
    }
    
    public static boolean isNormal() {
        return getState().equals(State.NORMAL);
    }
    
    public static boolean isSrg() {
        return getState().equals(State.SRG);
    }
    
    public static boolean isObfuscated() {
        return getState().equals(State.OBFUSCATED);
    }
    
    public static IStateMapper getMapper() {
        return (RuntimeState.remapper == null) ? (RuntimeState.remapper = (IStateMapper)(isObfuscated() ? ObfuscatedStateMapper.getInstance() : NonObfuscatedStateMapper.getInstance())) : RuntimeState.remapper;
    }
    
    static {
        RuntimeState.remapper = null;
        RuntimeState.localState = ThreadLocal.withInitial((Supplier<? extends State>)RuntimeState::getDefaultState);
        RuntimeState.defaultState = State.OBFUSCATED;
    }
}
