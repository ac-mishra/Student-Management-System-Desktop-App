package org.example.ui.component;

import org.example.ui.theme.AppColors;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CardPanel extends JPanel {

    public CardPanel() {

        setBackground(AppColors.CARD);

        setBorder(new EmptyBorder(25,25,25,25));

        setOpaque(true);

    }

}