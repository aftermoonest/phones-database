package com.aftermoonest;

import com.aftermoonest.gui.Frame;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.util.prefs.Preferences;

public class Executor {

    public static void main(String[] args) {
//        new Frame();
        SwingUtilities.invokeLater( () -> {
            Preferences prefs = Preferences.userRoot().node("/flatlaf-swingx");
            try {
                if( args.length > 0 )
                    UIManager.setLookAndFeel( args[0] );
                else {
                    String lafClassName = prefs.get("laf", FlatLightLaf.class.getName());
                    UIManager.setLookAndFeel( lafClassName );
                }
            } catch( Exception ex ) {
                ex.printStackTrace();
                FlatLightLaf.install();
            }
            new Frame();
        } );
    }
}
