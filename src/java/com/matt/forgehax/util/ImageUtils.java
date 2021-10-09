//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.matt.forgehax.util;

import com.matt.forgehax.*;
import java.awt.image.*;
import java.awt.*;
import java.net.*;
import javax.imageio.*;

public class ImageUtils implements Globals
{
    public static BufferedImage createResizedCopy(final Image originalImage, final int scaledWidth, final int scaledHeight, final boolean preserveAlpha) {
        final int imageType = preserveAlpha ? 1 : 2;
        final BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        final Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
    
    public static BufferedImage getImageFromUrl(final String link) {
        BufferedImage image = null;
        try {
            final URL url = new URL(link);
            image = ImageIO.read(url);
        }
        catch (Exception e) {
            ImageUtils.LOGGER.error("Failed to download Image");
        }
        return image;
    }
    
    public static int[][] imageToArray(final BufferedImage imageIn) {
        final int width = imageIn.getWidth();
        final int height = imageIn.getHeight();
        final int[][] data = new int[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                data[i][j] = imageIn.getRGB(i, j);
            }
        }
        return data;
    }
}
