//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import java.util.concurrent.*;
import java.text.*;
import java.util.*;

public class SimpleTimer
{
    private static final DateFormat TIME_FORMATTER;
    private static final DateFormat DATE_FORMATTER;
    private long timeStarted;
    private long timeStopped;
    
    public static String toFormattedTime(final long time) {
        return SimpleTimer.TIME_FORMATTER.format(new Date(time));
    }
    
    public static String toFormattedDate(final long time) {
        return SimpleTimer.DATE_FORMATTER.format(new Date(time));
    }
    
    public SimpleTimer(final boolean startOnInit) {
        this.timeStarted = -1L;
        this.timeStopped = -1L;
        if (startOnInit) {
            this.start();
        }
    }
    
    public SimpleTimer() {
        this(false);
    }
    
    public void start() {
        this.timeStarted = System.currentTimeMillis();
    }
    
    public void stop() {
        this.timeStopped = System.currentTimeMillis();
    }
    
    public void reset() {
        final long n = -1L;
        this.timeStopped = n;
        this.timeStarted = n;
    }
    
    public boolean isStarted() {
        return this.timeStarted > -1L;
    }
    
    public boolean isStopped() {
        return this.timeStopped > -1L;
    }
    
    public long getTimeStarted() {
        return Math.max(this.timeStarted, 0L);
    }
    
    public long getTimeStopped() {
        return Math.max(this.timeStopped, 0L);
    }
    
    private long _time() {
        return this.isStopped() ? this.getTimeStopped() : System.currentTimeMillis();
    }
    
    public long getTimeElapsed() {
        return this._time() - this.getTimeStarted();
    }
    
    public boolean hasTimeElapsed(final long time) {
        return time <= this.getTimeElapsed();
    }
    
    public String getFormattedTimeStarted() {
        return SimpleTimer.DATE_FORMATTER.format(new Date(this.getTimeStarted()));
    }
    
    public String getFormattedTimeStopped() {
        return SimpleTimer.DATE_FORMATTER.format(new Date(this.getTimeStopped()));
    }
    
    public String getFormattedTimeElapsed() {
        return formatInterval(Math.max(this.getTimeElapsed(), 0L));
    }
    
    protected static String formatInterval(final long delta) {
        final long hr = TimeUnit.MILLISECONDS.toHours(delta);
        final long min = TimeUnit.MILLISECONDS.toMinutes(delta - TimeUnit.HOURS.toMillis(hr));
        final long sec = TimeUnit.MILLISECONDS.toSeconds(delta - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));
        final long ms = TimeUnit.MILLISECONDS.toMillis(delta - TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec));
        return String.format("%02d:%02d:%02d.%03d", hr, min, sec, ms);
    }
    
    static {
        TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss.SSS");
        DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleTimer.TIME_FORMATTER.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
}
