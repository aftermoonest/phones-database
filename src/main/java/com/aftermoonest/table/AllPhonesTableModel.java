package com.aftermoonest.table;

import com.aftermoonest.entity.Phone;
import com.aftermoonest.entity.PrimaryPhone;
import com.aftermoonest.entity.SmartPhone;

import java.util.List;

public class AllPhonesTableModel extends CustomAbstractTableModel {

    private List<PrimaryPhone> primaryPhones;

    private List<SmartPhone> smartPhones;
    private List<Phone> phones;

    public AllPhonesTableModel(List<PrimaryPhone> phones) {
        this.primaryPhones = phones;
    }

    @Override
    public int getRowCount() {
        return primaryPhones.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "class";
            case 2:
                return "dateOfProduction";
            case 3:
                return "manufacturer";
            case 4:
                return "model";
            case 5:
                return "color";
            case 6:
                return "countOfSim";
            case 7:
                return "memory";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PrimaryPhone primaryPhone = primaryPhones.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return primaryPhone.getId();
            case 1:
                if (primaryPhone instanceof Phone) {
                    return "Phone";
                } else {
                    return "SmartPhone";
                }
            case 2:
                return primaryPhone.getDateOfProduction();
            case 3:
                return primaryPhone.getManufacturer();
            case 4:
                return primaryPhone.getModel();
            case 5:
                return "[" + primaryPhone.getColor().getRed() + "-" +
                        "" + primaryPhone.getColor().getGreen() + "-" +
                        "" + primaryPhone.getColor().getBlue() + "]";
            case 6:
                if (primaryPhone instanceof Phone) {
                    return ((Phone) primaryPhone).getCountOfSim();
                } else return null;
            case 7:
                if (primaryPhone instanceof SmartPhone) {
                    return ((SmartPhone) primaryPhone).getMemory();
                } else return null;
        }
        return "";
    }
}
