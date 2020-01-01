package com.aftermoonest.gui.dialog.change_field;

import com.aftermoonest.entity.Phone;
import com.aftermoonest.entity.PrimaryPhone;
import com.aftermoonest.entity.SmartPhone;
import com.aftermoonest.gui.dialog.ColorChooser;
import com.aftermoonest.repository.PhoneRepository;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class ChangeField extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner spinner1;
    private JButton OKButton;
    private JTabbedPane tabbedPane1;
    private JTextField manufacturerPhone;
    private JTextField modelPhone;
    private JSpinner countOfSimPhone;
    private JButton pickColorButtonPhone;
    private JTextField manufacturerSmartPhone;
    private JTextField modelSmartPhone;
    private JButton pickColorButtonSmartPhone;
    private JSpinner memorySmartPhone;
    private JXDatePicker datePhone;
    private JXDatePicker dateSmartPhone;

    private PrimaryPhone primaryPhone;
    private Phone phone;
    private SmartPhone smartPhone;

    private boolean isPhone = true;

    private Color color = Color.WHITE;

    public ChangeField() {
        setResizable(false);
        setTitle("Change element");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        tabbedPane1.setVisible(false);
        setDefaults();
        actionListener();
        callOnCancel();
    }

    private void actionListener() {
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

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((int) spinner1.getValue() <= 0) {
                    JOptionPane.showMessageDialog(getContentPane(),
                            "Index must be more than 0",
                            "Error message",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (PhoneRepository.getPhone((int) spinner1.getValue()) instanceof Phone) {
                        isPhone = true;
                        phone = PhoneRepository.getPhone((int) spinner1.getValue());
                        takePhone();
                    } else {
                        isPhone = false;
                        smartPhone = PhoneRepository.getSmartPhone((int) spinner1.getValue());
                        takeSmartPhone();
                    }
                }
            }
        });

        pickColorButtonPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = pickColor();
            }
        });

        pickColorButtonSmartPhone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = pickColor();
            }
        });
    }

    private void setDefaults() {
        datePhone.setDate(new Date());
        dateSmartPhone.setDate(new Date());

        manufacturerPhone.setText("default");
        manufacturerSmartPhone.setText("default");

        modelPhone.setText("default");
        modelSmartPhone.setText("default");

        color = Color.WHITE;

        countOfSimPhone.setValue(2);

        memorySmartPhone.setValue(4);
    }

    private Color pickColor() {
        ColorChooser colorChooser = new ColorChooser();
        colorChooser.pack();
        colorChooser.setLocationRelativeTo(null);
        colorChooser.setVisible(true);
        colorChooser.setResizable(false);

        return colorChooser.getColor();
    }

    private Phone setPhoneValues(Phone phone) {
        phone.setDateOfProduction(datePhone.getDate());
        phone.setManufacturer(manufacturerPhone.getText());
        phone.setModel(modelPhone.getText());
        phone.setColor(color);
        phone.setCountOfSim((int) countOfSimPhone.getValue());
        return phone;
    }

    private SmartPhone setSmartPhoneValues(SmartPhone smartPhone) {
        smartPhone.setDateOfProduction(dateSmartPhone.getDate());
        smartPhone.setManufacturer(manufacturerSmartPhone.getText());
        smartPhone.setModel(modelSmartPhone.getText());
        smartPhone.setColor(color);
        smartPhone.setMemory((int) memorySmartPhone.getValue());
        return smartPhone;
    }

    private void takePhone() {
        tabbedPane1.setVisible(true);
        tabbedPane1.setSelectedIndex(0);
        tabbedPane1.setEnabledAt(0, true);
        tabbedPane1.setEnabledAt(1, false);
    }

    private void takeSmartPhone() {
        tabbedPane1.setVisible(true);
        tabbedPane1.setSelectedIndex(1);
        tabbedPane1.setEnabledAt(0, false);
        tabbedPane1.setEnabledAt(1, true);
    }

    private void callOnCancel() {
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
        if (isPhone) {
            setPhoneValues(phone);
            primaryPhone = phone;
        } else {
            setSmartPhoneValues(smartPhone);
            primaryPhone = smartPhone;
        }
        PhoneRepository.update(primaryPhone);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
