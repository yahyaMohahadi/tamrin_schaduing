package org.maktab.tamrinshaduing.service;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Parcel;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.maktab.tamrinshaduing.moodel.Data;
import org.maktab.tamrinshaduing.retrofit.APIClient;
import org.maktab.tamrinshaduing.room.DataBase;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static android.net.ConnectivityManager.TYPE_WIFI;

public class UploadWork extends Worker {


    public static final String DATA_ON_PERIODIC = "GET_DATA_ON_PERIODIC";

    public UploadWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data = getData();
        DataBase.getDataBase(getApplicationContext()).getDao().insert(data);
        Log.d("QQQ",DataBase.getDataBase(getApplicationContext()).getDao().getAll().getValue().size()+"sosss");

        return Result.retry();
    }

    private Data getData() {
        Data data = new Data();
        data.setBooleanWifi(getWifiInfo());
        data.setStringName(APIClient.getName());
        data.setDate(new Date());
        return data;
    }


    private boolean getWifiInfo() {
        NetworkCapabilities capabilities = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            capabilities = NetworkCapabilities.CREATOR.createFromParcel(Parcel.obtain());
            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        } else {
            ConnectivityManager connManager = (ConnectivityManager)
                    getApplicationContext().getSystemService(Application.CONNECTIVITY_SERVICE);
            NetworkInfo net = connManager.getActiveNetworkInfo();
            return net.getType() == TYPE_WIFI;
        }
    }


    public static void getDataReguarly(Context context, boolean makeOn) {
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(UploadWork.class, 15, TimeUnit.MINUTES)
                .addTag(DATA_ON_PERIODIC)
                .build();
        if (makeOn)
            WorkManager.getInstance(context)
                    .enqueueUniquePeriodicWork(DATA_ON_PERIODIC, ExistingPeriodicWorkPolicy.KEEP, request);
        else
            WorkManager.getInstance(context).cancelAllWorkByTag(DATA_ON_PERIODIC);
    }


    public static boolean isWorkScheduled(Context context) {
        try {
            WorkManager workManager = WorkManager.getInstance(context);
            List<WorkInfo> workInfos = workManager.getWorkInfosForUniqueWork(DATA_ON_PERIODIC).get();

            for (WorkInfo workInfo : workInfos) {
                if (workInfo.getState() == WorkInfo.State.ENQUEUED ||
                        workInfo.getState() == WorkInfo.State.RUNNING)
                    return true;
            }
            return false;
        } catch (ExecutionException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }
    }
}
