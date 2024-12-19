package org.example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        var cakes = CakeHandler.generateCake(10);
//        for (var cake : cakes)
//            System.out.println(cake.toString());
//
//        var maxPrice = CakeHandler.getMaxPriceCake(cakes).get_price();
//        System.out.println("Максимальная цена: " + maxPrice);
//
//        var filteredCakes = CakeHandler.getFilteredByManufacturerCakes(cakes, "Mirel");
//        for (var cake : filteredCakes)
//            System.out.println(cake.toString());
//
//        //Запись и чтение файла
//        CakeHandler.writeFile(cakes, "C:\\temp\\cakes.txt");
//        var rCakes = CakeHandler.readFile("C:\\temp\\cakes.txt");
//        for (var cake : rCakes){
//            System.out.println(cake.toString());
//        }
//
//        //Работа с базой данных
//        ArrayList<Confection> newConfections;
//        ArrayList<Confection> confectionsFilteredByPrice;
//        ArrayList<Confection> confectionsFilteredByManufacturer;
//        try {
//            Database.connectDB();
//            Database.deleteData();
//            Database.createTableConfection();
//            var confections = CakeHandler.generateConfection(10);
//            Database.inputDataConfection(confections);
//            newConfections = Database.readData();
//            confectionsFilteredByPrice = Database.getConfectionsFilteredByPrice(4000);
//            confectionsFilteredByManufacturer = Database.getConfectionsFilteredByManufacturer("Mirel");
//            Database.closeDb();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        for (var confection : newConfections){
//            System.out.println(confection);
//        }
//
//        for (var confection : confectionsFilteredByPrice){
//            System.out.println(confection);
//        }
//
//        for (var confection : confectionsFilteredByManufacturer){
//            System.out.println(confection);
//        }

        //Круговая диаграмма
        ArrayList<Confection> confections = CakeHandler.generateConfection(100);
        Map<String, Integer> map = CakeHandler.getManufacturerCount(confections);
        Graph graph = new Graph(map);
        graph.setVisible(true);
    }
}