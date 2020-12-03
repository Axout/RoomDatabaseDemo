package ru.axout.roomdatabasedemo;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Добавляю объекты БД
@Database(entities = {MainData.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    // Создаю экземпляр БД
    private static RoomDB database;
    // Имя БД
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context) {
        // проверка
        if (database == null) {
            // Если БД нет, то инициализируем её
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    //
    public abstract MainDao mainDao();
}
