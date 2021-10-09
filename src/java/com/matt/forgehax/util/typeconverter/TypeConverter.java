//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util.typeconverter;

import javax.annotation.*;
import java.util.*;

public abstract class TypeConverter<E>
{
    public abstract String label();
    
    public abstract Class<E> type();
    
    public abstract E parse(final String p0);
    
    public E parse(final String value, @Nullable final E defaultTo) {
        try {
            return this.parse(value);
        }
        catch (Throwable t) {
            return defaultTo;
        }
    }
    
    public abstract String toString(final E p0);
    
    public String toString(final E value, @Nonnull final String defaultTo) {
        try {
            return this.toString(value);
        }
        catch (Throwable t) {
            return defaultTo;
        }
    }
    
    @Nullable
    public E parseSafe(final String value) {
        return this.parse(value, null);
    }
    
    @Nonnull
    public String toStringSafe(final E value) {
        return this.toString(value, String.valueOf((Object)null));
    }
    
    public boolean isType(final Class<?> clazz) {
        return Objects.equals(this.type(), clazz);
    }
    
    public boolean isAssignableFrom(final Class<?> clazz) {
        return this.isType(clazz) || (this.type() != null && clazz != null && this.type().isAssignableFrom(clazz));
    }
    
    @Nullable
    public Comparator<E> comparator() {
        return null;
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj instanceof TypeConverter && Objects.equals(this.label(), ((TypeConverter)obj).label());
    }
}
