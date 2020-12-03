package ru.axout.roomdatabasedemo;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Инициализация переменных
    EditText editText;
    Button btAdd, btReset;
    RecyclerView recyclerView;

    // Список с данными (картежи)
    // БД создаётся в MainData
    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Присваивание переменным (Assign variables)
        editText = findViewById(R.id.edit_text);
        btAdd = findViewById(R.id.bt_add);
        btReset = findViewById(R.id.bt_reset);
        recyclerView = findViewById(R.id.recycler_view);

        // Initialize BD
        database = RoomDB.getInstance(this);
        // Хранение данных БД в data list (Store database value in data list)
        dataList = database.mainDao().getAll();

        // Инициализация менеджера линейного макета (Initialize linear layout manager)
        linearLayoutManager = new LinearLayoutManager(this);
        // Установка менеджера макета
        recyclerView.setLayoutManager(linearLayoutManager);
        // Инициализация adapter
        adapter = new MainAdapter(MainActivity.this, dataList);
        // Set adapter
        recyclerView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение строки из edit text
                String sText = editText.getText().toString().trim();
                // Проверка пустой строки
                if (!sText.equals("")) {
                    // Если строка не пустая
                    // Initialize main data
                    MainData data = new MainData();
                    // Set text on main data
                    data.setText(sText);
                    // Вставка текста в БД (Insert text in database)
                    database.mainDao().insert(data);
                    // Очитка edit text
                    editText.setText("");
                    // Уведомление после вставки данных (Notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Удаление всех данных из БД (Delete all data from database)
                database.mainDao().reset(dataList);
                // Уведомление после удаления данных
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter.notifyDataSetChanged();
            }
        });
    }
}