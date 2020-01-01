package com.aftermoonest.entity.util;

import com.aftermoonest.entity.PrimaryPhone;

import java.util.Comparator;
import java.util.List;

public class Sorting {

    public static List<PrimaryPhone> sorting(List<PrimaryPhone> phones, int sortID) {
        if (sortID == 1) {
            return sortById(phones);
        } else if (sortID == 2) {
            return sortByDateOfProduction(phones);
        } else if (sortID == 3) {
            return sortByManufacturer(phones);
        } else if (sortID == 4) {
            return sortByModel(phones);
        }
        return sortById(phones);
    }

    private static List<PrimaryPhone> sortById(List<PrimaryPhone> phones) {
        phones.sort(Comparator.comparing(PrimaryPhone::getId));
        return phones;
    }

    private static List<PrimaryPhone> sortByDateOfProduction(List<PrimaryPhone> phones) {
        phones.sort(Comparator.comparing(PrimaryPhone::getDateOfProduction));
        return phones;
    }

    private static List<PrimaryPhone> sortByManufacturer(List<PrimaryPhone> phones) {
        phones.sort(Comparator.comparing(PrimaryPhone::getManufacturer));
        return phones;
    }

    private static List<PrimaryPhone> sortByModel(List<PrimaryPhone> phones) {
        phones.sort(Comparator.comparing(PrimaryPhone::getModel));
        return phones;
    }
}
