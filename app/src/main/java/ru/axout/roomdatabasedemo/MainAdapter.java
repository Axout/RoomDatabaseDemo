package ru.axout.roomdatabasedemo;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    // Инициализируем переменные
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    // Конструктор
    public MainAdapter(Activity context, List<MainData> dataList) {
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        // Initialize main data
        final MainData data = dataList.get(position);
        // Инициализация БД
        database = RoomDB.getInstance(context);
        // Установка текса в text view
        holder.textView.setText(data.getText());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize main data
                MainData d = dataList.get(holder.getAdapterPosition());
                final int sID = d.getID();
                String sText = d.getText();

                // Диалоговое окно
                final Dialog dialog = new Dialog(context);
                // Set content view
                dialog.setContentView(R.layout.dialog_update);
                // Определяем размеры экрана
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;
                // Set layout
                dialog.getWindow().setLayout(width, height);
                // Показ диалогового окна
                dialog.show();

                // Инициализация и назначение переменных
                final EditText editText = dialog.findViewById(R.id.edit_text);
                Button btUpdate = dialog.findViewById(R.id.bt_update);

                // Установка текста в edit text
                editText.setText(sText);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Скрытие диалогового окна
                        dialog.dismiss();
                        // Получение обновлённого текста из edit text
                        String uText = editText.getText().toString().trim(); // trim() -
                        // Обновление текста в БД
                        database.mainDao().update(sID, uText);
                        // Уведомление после обновления данных
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize main data
                MainData d = dataList.get(holder.getAdapterPosition());
                // Удаление текста из БД
                database.mainDao().delete(d);
                // Уведомление после обновления данных
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);
        }
    }
}
