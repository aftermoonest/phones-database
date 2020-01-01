package com.aftermoonest.gui.dialog.add_field;

import com.aftermoonest.entity.Phone;
import com.aftermoonest.entity.PrimaryPhone;
import com.aftermoonest.entity.SmartPhone;
import com.aftermoonest.gui.dialog.ColorChooser;
import com.aftermoonest.repository.PhoneRepository;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.MessageDigest;
import java.util.Date;

public class AddNewPhone extends JDialog {
    private JDialog dialog;
    private JPanel contentPane;
    private JRadioButton phoneRadioButton;
    private JRadioButton smartPhoneRadioButton;
    private JTabbedPane tabbedPane1;
    private JButton OKButton;
    private JButton cancelButton;
    private JTextField manufacturerPhone;
    private JTextField modelPhone;
    private JButton pickColorPhoneButton;
    private JSpinner countOfSimPhone;
    private JScrollPane phoneTab;
    private JScrollPane smartPhoneTab;
    private JXDatePicker dateOfProductionPhone;
    private JXDatePicker dateOfProductionSmartPhone;
    private JTextField manufacturerSmartPhone;
    private JTextField modelSmartPhone;
    private JButton pickColorSmartPhoneButton;
    private JSpinner memorySmartPhone;

    private Color color = Color.WHITE;

    private boolean isPhone = true;

    private PrimaryPhone phone;

    public PrimaryPhone getPhone() {
        return phone;
    }

    public AddNewPhone() {
        setResizable(false);
        setTitle("Adding new phone");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(OKButton);

        setDefaults();

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

        phoneRadioButton.setSelected(true);

        tabbedPaneSetDefaults();

        actionListener();
    }

    private void actionListener(){
        pickColorPhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = pickColor();
            }
        });
        pickColorSmartPhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = pickColor();
            }
        });
        OKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        buttonGroup();
        phoneRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (phoneRadioButton.isSelected()) {
                    isPhone = true;
                    tabbedPane1.setSelectedIndex(0);
                    tabbedPane1.setEnabledAt(0, true);
                    tabbedPane1.setEnabledAt(1, false);
                }
            }
        });
        smartPhoneRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (smartPhoneRadioButton.isSelected()) {
                    isPhone = false;
                    tabbedPane1.setSelectedIndex(1);
                    tabbedPane1.setEnabledAt(0, false);
                    tabbedPane1.setEnabledAt(1, true);
                }
            }
        });
    }

    private Color pickColor(){
        ColorChooser colorChooser = new ColorChooser();
        colorChooser.pack();
        colorChooser.setLocationRelativeTo(null);
        colorChooser.setVisible(true);
        colorChooser.setResizable(false);

        return colorChooser.getColor();
    }

    private void setDefaults(){
        dateOfProductionPhone.setDate(new Date());
        dateOfProductionSmartPhone.setDate(new Date());

        manufacturerPhone.setText("default");
        manufacturerSmartPhone.setText("default");

        modelPhone.setText("default");
        modelSmartPhone.setText("default");

        color = Color.WHITE;

        countOfSimPhone.setValue(2);

        memorySmartPhone.setValue(4);
    }

    private void tabbedPaneSetDefaults(){
        tabbedPane1.setEnabledAt(0, true);
        tabbedPane1.setEnabledAt(1, false);
    }

    private void buttonGroup(){
        ButtonGroup group = new ButtonGroup();
        group.add(phoneRadioButton);
        group.add(smartPhoneRadioButton);
    }

    private void onOK() {

        if (isPhone) {
            if((int)countOfSimPhone.getValue() <= 0){
                JOptionPane.showMessageDialog(getContentPane(),
                        "Count of sim must be more than 0. Will set 1",
                        "Error messege",
                        JOptionPane.ERROR_MESSAGE);
                countOfSimPhone.setValue(1);
            }
            phone = new Phone(dateOfProductionPhone.getDate(),
                    manufacturerPhone.getText(),
                    modelPhone.getText(),
                    color,
                    (int) countOfSimPhone.getValue());
            PhoneRepository.insert(phone);
        } else {
            if ((int) memorySmartPhone.getValue() <= 0) {
                JOptionPane.showMessageDialog(getContentPane(),
                        "Count of memory must be more than 0. Will set 1",
                        "Error messege",
                        JOptionPane.ERROR_MESSAGE);
                memorySmartPhone.setValue(1);
            }
            phone = new SmartPhone(dateOfProductionSmartPhone.getDate(),
                    manufacturerSmartPhone.getText(),
                    modelSmartPhone.getText(),
                    color,
                    (int) memorySmartPhone.getValue());
            PhoneRepository.insert(phone);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
