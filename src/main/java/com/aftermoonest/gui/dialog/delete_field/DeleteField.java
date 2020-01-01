package com.aftermoonest.gui.dialog.delete_field;

import com.aftermoonest.repository.PhoneRepository;

import javax.swing.*;
import java.awt.event.*;

public class DeleteField extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner spinner1;

    private int numOfElement;
    public int getNumOfElement() {
        return numOfElement;
    }

    public DeleteField() {
        setResizable(false);
        setTitle("Deleting Element");
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
        if ((int) spinner1.getValue() <= 0) {
            JOptionPane.showMessageDialog(getContentPane(),
                    "Index must be more than 0",
                    "Error messege",
                    JOptionPane.ERROR_MESSAGE);
        }
        numOfElement = (int) spinner1.getValue();
        PhoneRepository.delete(numOfElement);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
