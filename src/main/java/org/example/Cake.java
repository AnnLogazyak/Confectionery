package org.example;

public class Cake extends Confection {
    private double _diameter;
    private String _dough;
    private String _filling;

    public Cake(String manufacturer, double price, double weight,
                double diameter, String dough, String filling) {
        super(manufacturer, price, weight);
        _diameter = diameter;
        _dough = dough;
        _filling = filling;
    }

    public String get_filling() {
        return _filling;
    }

    public void set_filling(String _filling) {
        this._filling = _filling;
    }

    public String get_dough() {
        return _dough;
    }

    public void set_dough(String _dough) {
        this._dough = _dough;
    }

    public double get_diameter() {
        return _diameter;
    }

    public void set_diameter(double _diameter) {
        this._diameter = _diameter;
    }

    public String toString() {
        return "Производитель" + ": " + get_manufacturer() + ", " + "Цена" + ": " + get_price() + ", " +
                "Вес" + ": " + get_weight() + ", " + "Диаметр" + ": " + get_diameter() + ", " +
                "Тесто" + ": " + get_dough() + ", " + "Начинка" + ": " + get_filling();
    }
}