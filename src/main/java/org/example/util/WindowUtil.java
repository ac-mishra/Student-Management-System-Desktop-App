package org.example.util;

import javax.swing.*;
import java.awt.*;

public class WindowUtil {

    private WindowUtil() {
    }

    public static void center(
            Window window
    ) {

        Dimension screen =
                Toolkit.getDefaultToolkit()
                        .getScreenSize();

        int x =
                (screen.width - window.getWidth()) / 2;

        int y =
                (screen.height - window.getHeight()) / 2;

        window.setLocation(x, y);

    }

    public static void maximize(
            JFrame frame
    ) {

        frame.setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

    }

}