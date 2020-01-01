package com.aftermoonest.gui.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ColorChooser extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JColorChooser colorChooser;
    private Color color;

    public ColorChooser() {
        setTitle("Choose color");
        JFrame frame = new JFrame();
        frame.add(contentPane);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOK() {
        color = colorChooser.getColor();
        System.out.println(color);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public Color getColor(){
        return color;
    }
}
