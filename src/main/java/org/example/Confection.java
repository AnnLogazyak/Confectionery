package org.example;

public class Confection {
    private final String _manufacturer;
    private final double _price;
    private final double _weight;

    public Confection(String manufacturer, double price, double weight) {
        _manufacturer = manufacturer;
        _price = price;
        _weight = weight;
    }

    public String get_manufacturer() {
        return _manufacturer;
    }

    public double get_price() {
        return _price;
    }

    public double get_weight() {
        return _weight;
    }

    public String toString() {
        return "Производитель" + ": " + get_manufacturer() + ", " + "Цена" + ": " + get_price() + ", " +
                "Вес" + ": " + get_weight();
    }
}