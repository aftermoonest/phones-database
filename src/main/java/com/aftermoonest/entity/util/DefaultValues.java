package com.aftermoonest.entity.util;

import com.aftermoonest.entity.Phone;
import com.aftermoonest.entity.PrimaryPhone;
import com.aftermoonest.entity.SmartPhone;
import com.aftermoonest.repository.PhoneRepository;

import java.awt.*;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultValues {
    public static void generate(int length) {
        List<PrimaryPhone> primaryPhones = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            boolean isPhone = random.nextBoolean();
            primaryPhones.add(isPhone ? generatePhone() : generateSmartPhone());
        }
    }

    //Nokia    //Alcatel    //Motorolla    //Siemens    //Sony Ericsson    //Panasonic
    private static Phone generatePhone() {
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone(getRandomDate(), "Samsung", getRandomSamsungModel(), getRandomColor(), getRandomSim()));
        phones.add(new Phone(getRandomDate(), "Apple", getRandomAppleModel(), getRandomColor(), getRandomSim()));
        phones.add(new Phone(getRandomDate(), "LG", getRandomLGModel(), getRandomColor(), getRandomSim()));
        phones.add(new Phone(getRandomDate(), "Google", getRandomGoogleModel(), getRandomColor(), getRandomSim()));
        phones.add(new Phone(getRandomDate(), "Xiaomi", getRandomXiaomiModel(), getRandomColor(), getRandomSim()));
        phones.add(new Phone(getRandomDate(), "Huawei", getRandomHuaweiModel(), getRandomColor(), getRandomSim()));

        int random = (int) (Math.random() * phones.size());
        PhoneRepository.insert(phones.get(random));
        return phones.get(random);
    }

    //Samsung    //Apple    //LG    //Google    //Xiaomi    //Huawei
    private static SmartPhone generateSmartPhone() {
        List<SmartPhone> phones = new ArrayList<>();
        phones.add(new SmartPhone(getRandomDate(), "Samsung", getRandomSamsungModel(), getRandomColor(), getRandomMemory()));
        phones.add(new SmartPhone(getRandomDate(), "Apple", getRandomAppleModel(), getRandomColor(), getRandomMemory()));
        phones.add(new SmartPhone(getRandomDate(), "LG", getRandomLGModel(), getRandomColor(), getRandomMemory()));
        phones.add(new SmartPhone(getRandomDate(), "Google", getRandomGoogleModel(), getRandomColor(), getRandomMemory()));
        phones.add(new SmartPhone(getRandomDate(), "Xiaomi", getRandomXiaomiModel(), getRandomColor(), getRandomMemory()));
        phones.add(new SmartPhone(getRandomDate(), "Huawei", getRandomHuaweiModel(), getRandomColor(), getRandomMemory()));

        int random = (int) (Math.random() * phones.size());
        PhoneRepository.insert(phones.get(random));
        return phones.get(random);
    }

    private static int getRandomMemory() {
        List<Integer> memories = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            memories.add((i + 1) * 2);
        }
        int random = new Random().nextInt(memories.size());
        return memories.get(random);
    }

    private static int getRandomSim() {
        List<Integer> countOfSims = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            countOfSims.add(i + 1);
        }
        int random = new Random().nextInt(countOfSims.size());
        return countOfSims.get(random);
    }

    private static Color getRandomColor() {
        List<Color> colors = new ArrayList<>();
        colors.add(Color.WHITE);
        colors.add(Color.BLACK);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        colors.add(Color.RED);
        colors.add(Color.PINK);
        colors.add(Color.GREEN);
        colors.add(Color.GRAY);

        int random = new Random().nextInt(colors.size());
        return colors.get(random);
    }

    private static String getRandomSamsungModel() {
        List<String> names = new ArrayList<>();
        names.add("Galaxy S10");
        names.add("Galaxy S9");
        names.add("Galaxy S8");
        names.add("Galaxy S7");
        names.add("Galaxy S6");
        names.add("Galaxy S5");
        names.add("Galaxy S4");
        names.add("Galaxy S3");
        names.add("Galaxy S2");
        names.add("Galaxy S1");
        names.add("Galaxy SL");
        names.add("Galaxy SL+");

        int random = new Random().nextInt(names.size());
        return names.get(random);
    }

    private static String getRandomAppleModel() {
        List<String> names = new ArrayList<>();
        names.add("iPhone");
        names.add("iPhone 3G");
        names.add("iPhone 4");
        names.add("iPhone 5");
        names.add("iPhone 6");
        names.add("iPhone 7");
        names.add("iPhone 8");
        names.add("iPhone X");
        names.add("iPhone XL");
        names.add("iPhone XS");
        names.add("iPhone 11 Pro");

        int random = new Random().nextInt(names.size());
        return names.get(random);
    }

    private static String getRandomLGModel() {
        List<String> names = new ArrayList<>();
        names.add("Optimus G");
        names.add("Optimus G Pro");
        names.add("G2");
        names.add("G3");
        names.add("G4");
        names.add("G5");
        names.add("G6");
        names.add("G7");
        names.add("G8");
        names.add("G9");
        names.add("G10");

        int random = new Random().nextInt(names.size());
        return names.get(random);
    }

    private static String getRandomGoogleModel() {
        List<String> names = new ArrayList<>();
        names.add("Pixel");
        names.add("Pixel 2");
        names.add("Pixel 3");
        names.add("Pixel 4");

        int random = new Random().nextInt(names.size());
        return names.get(random);
    }

    private static String getRandomXiaomiModel() {
        List<String> names = new ArrayList<>();
        names.add("Redmi Note");
        names.add("Redmi Note 2");
        names.add("Redmi Note 3");
        names.add("Redmi Note 4");
        names.add("Redmi Note 5");
        names.add("Redmi Note 6");
        names.add("Redmi Note 7");
        names.add("Mi");
        names.add("Mi 2");
        names.add("Mi 3");
        names.add("Mi 4");
        names.add("Mi 5");
        names.add("Mi 6");
        names.add("Mi 7");
        names.add("Mi 8");

        int random = new Random().nextInt(names.size());
        return names.get(random);
    }

    private static String getRandomHuaweiModel() {
        List<String> names = new ArrayList<>();
        names.add("Honor X1");
        names.add("Honor X2");
        names.add("Honor X3");
        names.add("Honor X4");
        names.add("Honor X5");
        names.add("Honor X6");
        names.add("Honor X7");
        names.add("Honor X8");

        int random = new Random().nextInt(names.size());
        return names.get(random);
    }

    private static Date getDate() {
        long beginTime = Timestamp.valueOf("2000-01-01 00:00:00").getTime();
        long endTime = new Date().getTime();

        long diff = endTime - beginTime + 1;
        long randDate = beginTime + (long) (Math.random() * diff);
        return new Date(randDate);
    }

    private static Date getRandomDate() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        boolean isDateNow = new Random().nextBoolean();
        if (isDateNow) {
            Date date = new Date();
            Date todayWithZeroTime = null;
            try{
                todayWithZeroTime = formatter.parse(formatter.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return todayWithZeroTime;
        } else {
            Date date = getDate();
            Date todayWithZeroTime = null;
            try{
                todayWithZeroTime = formatter.parse(formatter.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return todayWithZeroTime;
        }
    }
}
