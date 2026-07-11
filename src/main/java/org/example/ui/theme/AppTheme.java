package org.example.ui.theme;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.UIManager;

public final class AppTheme {

    private AppTheme(){}

    public static void setup(){

        try{

            FlatLightLaf.setup();

            UIManager.put("Button.arc",18);

            UIManager.put("Component.arc",18);

            UIManager.put("TextComponent.arc",18);

            UIManager.put("ProgressBar.arc",18);

            UIManager.put("ScrollBar.width",12);

        }

        catch(Exception e){

            e.printStackTrace();

        }

    }

}