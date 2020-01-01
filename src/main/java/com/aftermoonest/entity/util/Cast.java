package com.aftermoonest.entity.util;

import com.aftermoonest.entity.Phone;
import com.aftermoonest.entity.PrimaryPhone;
import com.aftermoonest.entity.SmartPhone;

import java.util.ArrayList;
import java.util.List;

public class Cast {
    public static List<PrimaryPhone> castPhone(List<Phone> phones) {
        return new ArrayList<>(phones);
    }

    public static List<PrimaryPhone> castSmartPhone(List<SmartPhone> smartPhones) {
        return new ArrayList<>(smartPhones);
    }
}
