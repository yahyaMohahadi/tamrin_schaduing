package org.maktab.tamrinshaduing.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.tamrinshaduing.R;
import org.maktab.tamrinshaduing.moodel.Data;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holdeler> {

    private List<Data> mData;

    public Adapter(List<Data> data) {
        mData = data;
    }

    public void setData(List<Data> data) {
        mData = data;
    }

    @NonNull
    @Override
    public Holdeler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new Holdeler(inflater.inflate(R.layout.item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull Holdeler holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    private TextView mTextViewID;
    private TextView mTextViewName;
    private TextView mTextViewWifi;
    private TextView mTextViewDate;

    class Holdeler extends RecyclerView.ViewHolder {

        public Holdeler(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
        }

        private void findViews(View view) {
            mTextViewID = view.findViewById(R.id.textView_id);
            mTextViewName = view.findViewById(R.id.textView_name);
            mTextViewWifi = view.findViewById(R.id.textView_wifi);
            mTextViewDate = view.findViewById(R.id.textView_time);
        }

        public void bind(Data data) {
            mTextViewID.setText("ID : " + data.getId());
            mTextViewName.setText("Name : " + data.getStringName());
            mTextViewWifi.setText("Wifi :" + data.isBooleanWifi());
            mTextViewDate.setText("Time : " + data.getDate().toString());
        }
    }
}
