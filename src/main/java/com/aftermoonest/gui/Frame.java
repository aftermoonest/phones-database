package com.aftermoonest.gui;

import com.aftermoonest.entity.*;
import com.aftermoonest.entity.util.Cast;
import com.aftermoonest.entity.util.DefaultValues;
import com.aftermoonest.entity.util.Sorting;
import com.aftermoonest.gui.dialog.add_field.AddNewPhone;
import com.aftermoonest.gui.dialog.change_field.ChangeField;
import com.aftermoonest.gui.dialog.delete_field.DeleteField;
import com.aftermoonest.gui.dialog.search_fields.SearchFields;
import com.aftermoonest.gui.dialog.sort_fields.SortFields;
import com.aftermoonest.repository.PhoneRepository;
import com.aftermoonest.table.AllPhonesTableModel;
import com.aftermoonest.table.PhonesTableModel;
import com.aftermoonest.table.SmartPhonesTableModel;
import com.aftermoonest.util.DataBaseManagement;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;

public class Frame extends FrameMain {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton exitButton;
    private JButton addFieldInDBButton;
    private JButton changeFieldInDBButton;
    private JButton deleteFieldFromDBButton;
    private JButton searchFieldButton;
    private JButton addDefaultFieldsInButton;
    private JButton sortFieldsButton;
    private JScrollPane phonesDBTab;
    private JScrollPane smartPhonesDB;
    private JPanel phonesDBPanel;
    private JPanel smartPhonesDBPanel;
    private JPanel allPhonesDBPanel;
    private JScrollPane allPhonesDB;
    private JButton updateTableButton;
    private JPanel searchPhonesBDPanel;
    private JScrollPane searchPhonesBD;
    private JButton saveButton;
    private JCheckBox blackThemeCheckBox;
    private JButton loadButton;

    private JTable tablePhones;
    private JTable tableSmartPhones;
    private JTable tableAllPhones;
    private JTable tableSearch;
    private boolean isTableSearchCreated = false;

    private TableModel tableModelPhones;
    private TableModel tableModelSmartPhones;
    private TableModel tableModelAllPhones;

    private JScrollPane tablePhoneScrollPane;
    private JScrollPane tableSmartPhoneScrollPane;
    private JScrollPane tableAllPhonesScrollPane;
    private JScrollPane tableSearchScrollPane;

    public Frame() {
        frame.add(panel1);
        run();
    }

    @Override
    void createView() {
        setTables();
        blackThemeCheckBox.setSelected(false);
    }

    @Override
    void actionListener() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        addFieldInDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPhone();
                updateTables(1);
            }
        });
        updateTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTables(1);
            }
        });
        deleteFieldFromDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletingElement();
                updateTables(1);
            }
        });
        changeFieldInDBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeField();
                updateTables(1);
            }
        });
        sortFieldsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortFields();
            }
        });
        addDefaultFieldsInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultValues.generate(10);
                updateTables(1);
            }
        });
        searchFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFields();
                tabbedPane1.setSelectedIndex(3);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBaseManagement.backUp();
                JOptionPane.showMessageDialog(frame, "Saved!");
            }
        });
        blackThemeCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!blackThemeCheckBox.isSelected()) {
                    try {
                        try {
                            UIManager.setLookAndFeel(FlatLightLaf.class.getName());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            FlatLightLaf.install();
                        }
                        SwingUtilities.updateComponentTreeUI(frame);
                        frame.pack();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        FlatLightLaf.install();
                    }
                } else {
                    try {
                        try {
                            UIManager.setLookAndFeel(FlatDarkLaf.class.getName());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            FlatLightLaf.install();
                        }
                        SwingUtilities.updateComponentTreeUI(frame);
                        frame.pack();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        FlatLightLaf.install();
                    }
                }
            }
        });

    }

    @Override
    void mouseListener() {

    }

    //region JDialogs
    private void sortFields() {
        SortFields sortFields = new SortFields();
        sortFields.pack();
        sortFields.setSize(450, 250);
        sortFields.setLocationRelativeTo(null);
        sortFields.setVisible(true);
        sortFields.setResizable(false);

        updateTables(sortFields.getChoice());
    }

    private void searchFields() {
        SearchFields searchFields = new SearchFields();
        searchFields.pack();
        searchFields.setSize(450, 250);
        searchFields.setLocationRelativeTo(null);
        searchFields.setVisible(true);
        searchFields.setResizable(false);

        search(searchFields);
    }

    private void search(SearchFields searchFields) {
        List<PrimaryPhone> primaryPhones = new ArrayList<>();
        List<PrimaryPhone> criteriaPhones = new ArrayList<>();

        List<SmartPhone> smartPhoneList = PhoneRepository.getSmartPhones();
        List<Phone> phones = PhoneRepository.getPhones();
        primaryPhones.addAll(smartPhoneList);
        primaryPhones.addAll(phones);

        primaryPhones = Sorting.sorting(primaryPhones, 1);

        for (int i = 0; i < primaryPhones.size(); i++) {
            if (searchFields.criteriaCheck(primaryPhones.get(i))) {
                criteriaPhones.add(primaryPhones.get(i));
            }
        }

        updateSearchTable(criteriaPhones, 1);
    }

    private void changeField() {
        ChangeField changeField = new ChangeField();
        changeField.pack();
        changeField.setSize(400, 350);
        changeField.setLocationRelativeTo(null);
        changeField.setVisible(true);
        changeField.setResizable(false);
    }

    private void deletingElement() {
        DeleteField deleteField = new DeleteField();
        deleteField.pack();
        deleteField.setLocationRelativeTo(null);
        deleteField.setVisible(true);
        deleteField.setResizable(false);
    }

    private void addNewPhone() {
        AddNewPhone addNewPhone = new AddNewPhone();
        addNewPhone.pack();
        addNewPhone.setLocationRelativeTo(null);
        addNewPhone.setVisible(true);
        addNewPhone.setResizable(false);
    }
    //endregion

    //region table
    private void setTables() {
        createPhonesTable();
        createSmartPhonesTable();
        createAllPhonesTable();
        createSearchTable();
    }

    //region updateTable
    private void updateTables(int sortID) {
        updatePhonesTable(sortID);
        updateSmartPhonesTable(sortID);
        updateAllPhonesTable(sortID);
    }

    private void updatePhonesTable(int sortID) {
        List<Phone> phones = PhoneRepository.getPhones();
        List<PrimaryPhone> primaryPhones;
        primaryPhones = Sorting.sorting(Cast.castPhone(phones), sortID);
        //primaryPhones = Sorting.sortById(Cast.castPhone(phones));

        tableModelPhones = new PhonesTableModel(primaryPhones);
        tablePhones.setModel(tableModelPhones);
        tablePhones.revalidate();
    }

    private void updateSmartPhonesTable(int sortID) {
        List<SmartPhone> phones = PhoneRepository.getSmartPhones();
        List<PrimaryPhone> primaryPhones;
        primaryPhones = Sorting.sorting(Cast.castSmartPhone(phones), sortID);

        tableModelSmartPhones = new SmartPhonesTableModel(primaryPhones);
        tableSmartPhones.setModel(tableModelSmartPhones);
        tableSmartPhones.revalidate();
    }

    private void updateAllPhonesTable(int sortID) {
        List<PrimaryPhone> primaryPhones = new ArrayList<>();

        List<SmartPhone> smartPhoneList = PhoneRepository.getSmartPhones();
        List<Phone> phones = PhoneRepository.getPhones();
        primaryPhones.addAll(smartPhoneList);
        primaryPhones.addAll(phones);

        primaryPhones = Sorting.sorting(primaryPhones, sortID);

        tableModelAllPhones = new AllPhonesTableModel(primaryPhones);
        tableAllPhones.setModel(tableModelAllPhones);
        tableAllPhones.revalidate();
    }

    private void updateSearchTable(List<PrimaryPhone> primaryPhones, int sortID) {
        if (!isTableSearchCreated) {
            //createSearchTable(primaryPhones);
        } else {
            primaryPhones = Sorting.sorting(primaryPhones, sortID);

            tableModelAllPhones = new AllPhonesTableModel(primaryPhones);
            tableSearch.setModel(tableModelAllPhones);
            tableSearch.revalidate();
        }
    }
    //endregion

    //region createTable
    private void createPhonesTable() {
        List<Phone> phones = PhoneRepository.getPhones();
        List<PrimaryPhone> primaryPhones;

        primaryPhones = Sorting.sorting(Cast.castPhone(phones), 1);

        tableModelPhones = new PhonesTableModel(primaryPhones);
        tablePhones = new JTable(tableModelPhones);
        tablePhones.setFillsViewportHeight(true);
        setFontToTable(tablePhones);

        phonesDBPanel.add(new JScrollPane(tablePhones));
    }

    private void createSmartPhonesTable() {
        List<SmartPhone> phones = PhoneRepository.getSmartPhones();
        List<PrimaryPhone> primaryPhones;

        primaryPhones = Sorting.sorting(Cast.castSmartPhone(phones), 1);

        tableModelSmartPhones = new SmartPhonesTableModel(primaryPhones);
        tableSmartPhones = new JTable(tableModelSmartPhones);
        tableSmartPhones.setFillsViewportHeight(true);
        setFontToTable(tableSmartPhones);
        smartPhonesDBPanel.add(new JScrollPane(tableSmartPhones));
    }

    private void createAllPhonesTable() {
        List<PrimaryPhone> primaryPhones = new ArrayList<>();

        List<SmartPhone> smartPhoneList = PhoneRepository.getSmartPhones();
        List<Phone> phones = PhoneRepository.getPhones();
        primaryPhones.addAll(smartPhoneList);
        primaryPhones.addAll(phones);

        primaryPhones = Sorting.sorting(primaryPhones, 1);

        tableModelAllPhones = new AllPhonesTableModel(primaryPhones);
        tableAllPhones = new JTable(tableModelAllPhones);
        tableAllPhones.setFillsViewportHeight(true);
        setFontToTable(tableAllPhones);

        allPhonesDBPanel.add(new JScrollPane(tableAllPhones));
    }

    private void createSearchTable() {
        //primaryPhones = Sorting.sorting(primaryPhones, 1);
        List<PrimaryPhone> primaryPhones = new ArrayList<>();
        isTableSearchCreated = true;

        tableModelAllPhones = new AllPhonesTableModel(primaryPhones);
        tableSearch = new JTable(tableModelPhones);
        tableSearch.setFillsViewportHeight(true);
        setFontToTable(tableSearch);

        searchPhonesBDPanel.add(new JScrollPane((tableSearch)));
    }

    private void setFontToTable(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }
    //endregion
    //endregion
}