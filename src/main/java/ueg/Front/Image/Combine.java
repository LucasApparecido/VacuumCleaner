package ueg.Front.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Combine {
    public Image getOverlayedImage(Image background, Image overlay) {
        BufferedImage combined = new BufferedImage(background.getWidth(null), background.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = combined.getGraphics();
        g.drawImage(background, 0, 0, null);
        g.drawImage(overlay, 0, 0, null);
        return combined;
    }
}
