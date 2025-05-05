package com.itc.onlinegameitc.storeController;

import javafx.beans.property.*;
import javafx.scene.image.Image;

// Clase para los elementos de la tabla
public class Item {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty level;
    private final SimpleStringProperty category;
    private final SimpleDoubleProperty price;
    private final SimpleDoubleProperty avgPrice;
    private final ObjectProperty<Image> icon;

    public Item(Image icon,String name, int level, String category, double price, double avgPrice) {
        this.icon = new SimpleObjectProperty<>(icon);
        this.name = new SimpleStringProperty(name);
        this.level = new SimpleIntegerProperty(level);
        this.category = new SimpleStringProperty(category);
        this.price = new SimpleDoubleProperty(price);
        this.avgPrice = new SimpleDoubleProperty(avgPrice);
    }

    // Getters y propiedades (necesarios para el TableView)
    public String getName() { return name.get(); }
    public SimpleStringProperty nameProperty() { return name; }

    public int getLevel() { return level.get(); }
    public SimpleIntegerProperty levelProperty() { return level; }

    public String getCategory() { return category.get(); }
    public SimpleStringProperty categoryProperty() { return category; }

    public double getPrice() { return price.get(); }
    public SimpleDoubleProperty priceProperty() { return price; }

    public double getAvgPrice() { return avgPrice.get(); }
    public SimpleDoubleProperty avgPriceProperty() { return avgPrice; }

    public Image getIcon() { return icon.get(); }
    public ObjectProperty<Image> iconProperty() { return icon; }
}