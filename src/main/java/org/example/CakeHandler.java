package org.example;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class CakeHandler {
    public static ArrayList<Confection> generateConfection(int count){
        var confections = new ArrayList<Confection>();
        for (var i = 0; i < count; i++){
            var rand = new Random();
            String[] manufacturers = {"Mirel", "Бисквитный двор", "Махаон", "Fantel", "Лиронас", "Jakov", "Корица"};
            var manufacturer = manufacturers[rand.nextInt(manufacturers.length)];
            var price = Math.round((1000 + 100 * rand.nextDouble(50)) * 100.0) / 100.0;
            var weight = Math.round(rand.nextDouble(1,3) * 100.0) / 100.0;
            confections.add(new Confection(manufacturer, price, weight));
        }
        return confections;
    }

    public static ArrayList<Cake> generateCake(int count){
        var cakes = new ArrayList<Cake>();
        for (var i = 0; i < count; i++){
            var rand = new Random();
            String[] manufacturers = {"9 Островов", "Mirel", "Бисквитный двор", "Fantel", "Jakov"};
            String[] doughTypes = {"Бисквит", "Песочное", "Слоеное", "Заварное"};
            String[] fillingTypes = {"Манго-малина", "Груша-кофе-шоколад", "Вишня", "Морковный", "Лимонный"};
            var manufacturer = manufacturers[rand.nextInt(manufacturers.length)];
            var price = Math.round((1000 + 100 * rand.nextDouble(50)) * 100.0) / 100.0;
            var weight = Math.round(rand.nextDouble(1,3) * 100.0) / 100.0;
            var diameter = Math.round(rand.nextDouble(10, 30) * 100.0) / 100.0;
            var dough = doughTypes[rand.nextInt(4)];
            var filling = fillingTypes[rand.nextInt(5)];
            cakes.add(new Cake(manufacturer, price, weight, diameter, dough, filling));
        }
        return cakes;
    }

    public static Cake getMaxPriceCake(ArrayList<Cake> cakes){
        var maxPrice = Double.MIN_VALUE;
        Cake maxPriceCake = null;
        for (var cake : cakes){
            if (cake.get_price() > maxPrice){
                maxPrice = cake.get_price();
                maxPriceCake = cake;
            }
        }
        return maxPriceCake;
    }

    public  static ArrayList<Cake> getFilteredByManufacturerCakes(ArrayList<Cake> cakes, String manufacturer){
        var filteredCakes = new ArrayList<Cake>();

        for (var cake : cakes)
            if (cake.get_manufacturer().equals(manufacturer))
                filteredCakes.add(cake);

        return filteredCakes;
    }

    public static void writeFile(ArrayList<Cake> cakes, String path){
        try (var writer = new BufferedWriter(new FileWriter(path))) {
            for (var cake : cakes){
                writer.write(cake.toString());
                writer.newLine();
            }
        }
        catch (IOException e){
                System.out.println(e.getMessage());
        }
        System.out.println("Данные сохранены в файл");
    }

    public static ArrayList<Cake> readFile(String path) {
        var cakes = new ArrayList<Cake>();
        try (var reader = new BufferedReader(new FileReader(path))){
            var line = reader.readLine();
            while (line != null) {
                cakes.add(convertStringToCake(line));
                line = reader.readLine();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return cakes;
    }

    private static Cake convertStringToCake(String st){
        var regex = ": (.*?)(, |$)";
        var pattern = Pattern.compile(regex);
        var matcher = pattern.matcher(st);

        var list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group(1));
        }

        return new Cake(list.get(0), Double.parseDouble(list.get(1)), Double.parseDouble(list.get(2)),
                Double.parseDouble(list.get(3)), list.get(4), list.get(5));
    }

    public static Map<String, Integer> getManufacturerCount(ArrayList<Confection> confections){
        Map<String, Integer> map = new HashMap<>();
        for (var confection : confections){
            if (map.containsKey(confection.get_manufacturer()))
                map.put(confection.get_manufacturer(), map.get(confection.get_manufacturer()) + 1);
            else
                map.put(confection.get_manufacturer(), 1);
        }
        return map;
    }
}