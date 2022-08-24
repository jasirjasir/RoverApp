package com.schneewittchen.rosandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.schneewittchen.rosandroid.utility.Constants;
import com.schneewittchen.rosandroid.utility.Utils;

import java.util.ArrayList;

public class NavViewModel extends AndroidViewModel {
    public NavViewModel(@NonNull Application application) {
        super(application);
    }
    public ArrayList<String> getLocationList() {
        return Constants.LOCATION_LIST;
    }
}
