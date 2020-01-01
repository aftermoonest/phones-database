package com.aftermoonest.table;

import com.aftermoonest.entity.Phone;
import com.aftermoonest.entity.PrimaryPhone;

import java.util.List;

public class PhonesTableModel extends CustomAbstractTableModel {

    private List<PrimaryPhone> phones;

    public PhonesTableModel(List<PrimaryPhone> phones) {
        this.phones = phones;
    }

    @Override
    public int getRowCount() {
        return phones.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "dateOfProduction";
            case 2:
                return "manufacturer";
            case 3:
                return "model";
            case 4:
                return "color";
            case 5:
                return "countOfSim";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Phone phone = (Phone) phones.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return phone.getId();
            case 1:
                return phone.getDateOfProduction();
            case 2:
                return phone.getManufacturer();
            case 3:
                return phone.getModel();
            case 4:
                return "[" + phone.getColor().getRed() + "-" +
                        "" + phone.getColor().getGreen() + "-" +
                        "" + phone.getColor().getBlue() + "]";
            case 5:
                return phone.getCountOfSim();
        }
        return "";
    }
}
