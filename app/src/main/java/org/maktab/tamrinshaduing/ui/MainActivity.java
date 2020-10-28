package org.maktab.tamrinshaduing.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab.tamrinshaduing.R;
import org.maktab.tamrinshaduing.adapter.Adapter;
import org.maktab.tamrinshaduing.moodel.Data;
import org.maktab.tamrinshaduing.room.DataBase;
import org.maktab.tamrinshaduing.service.UploadWork;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mButtonStart;
    private Button mButtonStop;
    private RecyclerView mRecyclerView;
    private LiveData<List<Data>> mDataLiveData;
    private Adapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        mDataLiveData =DataBase.getDataBase(this).getDao().getAll();
       // Log.d("QQQ",mDataLiveData.getValue().size()+"");
        mDataLiveData.observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                Log.d("QQQ","OBSERVER CALLSE");
                if(mAdapter!=null){
                    mAdapter.setData(mDataLiveData.getValue());
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        initRecycler();
        onCklick();
    }

    private void initRecycler() {
        mAdapter=new Adapter(mDataLiveData.getValue());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void findViews() {
        mButtonStart = findViewById(R.id.button_start);
        mButtonStop = findViewById(R.id.button_stop);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void onCklick() {
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSchaduing();
            }
        });

        mButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopSchaduing();
            }
        });
    }

    private void stopSchaduing() {
        if (UploadWork.isWorkScheduled(this)) {
            UploadWork.getDataReguarly(this, false);
        }
    }

    private void startSchaduing() {
        if (!UploadWork.isWorkScheduled(this)) {
            UploadWork.getDataReguarly(this, true);
        }
    }
}