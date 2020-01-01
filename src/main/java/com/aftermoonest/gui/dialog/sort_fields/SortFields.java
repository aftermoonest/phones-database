package com.aftermoonest.gui.dialog.sort_fields;

import javax.swing.*;
import java.awt.event.*;

public class SortFields extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton byIdRadioButton;
    private JRadioButton byDateOfProductionRadioButton;
    private JRadioButton byManufacturerRadioButton;
    private JRadioButton byModelRadioButton;

    private int choice = 1;

    public int getChoice() {
        return choice;
    }

    public SortFields() {
        byIdRadioButton.setSelected(true);

        setResizable(false);
        setTitle("Sort fields");
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

        createGroup();
        actionListener();
    }

    private void actionListener(){
        byIdRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (byIdRadioButton.isSelected()) {
                    choice = 1;
                }
            }
        });
        byDateOfProductionRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (byDateOfProductionRadioButton.isSelected()) {
                    choice = 2;
                }
            }
        });
        byManufacturerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (byManufacturerRadioButton.isSelected()) {
                    choice = 3;
                }
            }
        });
        byModelRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (byModelRadioButton.isSelected()) {
                    choice = 4;
                }
            }
        });
    }

    private void createGroup(){
        ButtonGroup group = new ButtonGroup();
        group.add(byIdRadioButton);
        group.add(byDateOfProductionRadioButton);
        group.add(byManufacturerRadioButton);
        group.add(byModelRadioButton);

    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
