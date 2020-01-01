package com.aftermoonest.gui.dialog.search_fields;

import com.aftermoonest.entity.PrimaryPhone;
import com.aftermoonest.gui.dialog.ColorChooser;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchFields extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox searchByDateCheckBox;
    private JCheckBox searchByManufactorerCheckBox;
    private JCheckBox searchByModelCheckBox;
    private JTextField manufacturer;
    private JTextField model;
    private JCheckBox searchByColorCheckBox;
    private JButton pickColorButton;
    private JXDatePicker date;

    private Color color;

    private boolean searchByDate;
    private boolean searchByManufacturer;
    private boolean searchByModel;
    private boolean searchByColor;

    public SearchFields() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        setDefaults();

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

        pickColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = pickColor();
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

        actionListener();
    }

    private void actionListener() {
        searchByDateCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchByDateCheckBox.isSelected()) {
                    searchByDate = true;
                } else {
                    searchByDate = false;
                }
            }
        });
        searchByManufactorerCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchByManufactorerCheckBox.isSelected()) {
                    searchByManufacturer = true;
                } else {
                    searchByManufacturer = false;
                }
            }
        });
        searchByModelCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchByModelCheckBox.isSelected()) {
                    searchByModel = true;
                } else {
                    searchByModel = false;
                }
            }
        });
        searchByColorCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchByColorCheckBox.isSelected()) {
                    searchByColor = true;
                } else {
                    searchByColor = false;
                }
            }
        });
    }

    private Color pickColor() {
        ColorChooser colorChooser = new ColorChooser();
        colorChooser.pack();
        colorChooser.setLocationRelativeTo(null);
        colorChooser.setVisible(true);
        colorChooser.setResizable(false);

        return colorChooser.getColor();
    }

    private void setDefaults() {
        date.setDate(new Date());
        manufacturer.setText("Apple");
        model.setText("iPhone XS");
        color = Color.WHITE;
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    //need to write it
    public boolean criteriaCheck(PrimaryPhone phone) {
        boolean isData = false;
        boolean isManufacturer = false;
        boolean isModel = false;
        boolean isColor = false;

        boolean isCriteria = true;

        if (searchByDate) {
            Date date1 = getFormatedDate(phone.getDateOfProduction());
            System.out.println("Date 1: " + date1);
            Date date2 = getFormatedDate(date.getDate());
            System.out.println("Date 2: " + date2);

            if (date1.compareTo(date2) == 0) {
                isData = true;
            }
            isCriteria = isCriteria & isData;
        }
        if (searchByManufacturer) {
            if (phone.getManufacturer().equals(manufacturer.getText())) {
                isManufacturer = true;
            }
            isCriteria = isCriteria & isManufacturer;
        }
        if (searchByModel) {
            if (phone.getModel().equals(model.getText())) {
                isModel = true;
            }
            isCriteria = isCriteria & isModel;
        }
        if (searchByColor) {
            if (phone.getColor().equals(color)) {
                isColor = true;
            }
            isCriteria = isCriteria & isColor;
        }
        return isCriteria;
    }

    private Date getFormatedDate(Date date) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date newDate = null;
        try{
            newDate = formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
