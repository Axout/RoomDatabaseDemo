package ru.axout.roomdatabasedemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Присваиваем таблице имя
@Entity(tableName = "table_name")
public class MainData implements Serializable {
    // Создаём id столбца (атрибута)
    @PrimaryKey(autoGenerate = true)
    private int ID;

    // Создаём текст столбца
    @ColumnInfo(name = "text")
    private String text;

    // Генерируем геттеры и сеттеры

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
