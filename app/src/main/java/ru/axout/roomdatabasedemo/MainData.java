package ru.axout.roomdatabasedemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Присваиваем таблице имя
@Entity(tableName = "table_name")
public class MainData implements Serializable {
    // Создание первичного ключа, автоматическое
    @PrimaryKey // @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "sort")
    private String sort;

    @ColumnInfo(name = "color")
    private String color;

    @ColumnInfo(name = "quantity")
    private String quantity;

    // Генерируем геттеры и сеттеры

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
