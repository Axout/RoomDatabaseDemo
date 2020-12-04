package ru.axout.roomdatabasedemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao {
    // Вставка запроса
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    // Удаление запроса
    @Delete
    void delete(MainData mainData);

    // Удаление всех запросов
    @Delete
    void reset(List<MainData> mainData);

    // Обновление запросов
    @Query("UPDATE table_name SET quantity = :sQuantity WHERE ID = :sID")
    void update(int sID, String sQuantity);

    // Выдача всех запросов
    @Query("SELECT * FROM table_name")
    List<MainData> getAll();
}
