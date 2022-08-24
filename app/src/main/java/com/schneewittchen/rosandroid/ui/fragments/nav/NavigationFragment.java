package com.schneewittchen.rosandroid.ui.fragments.nav;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.DefaultRetryPolicy;
import com.schneewittchen.rosandroid.R;
import com.schneewittchen.rosandroid.databinding.FragmentMasterBinding;
import com.schneewittchen.rosandroid.databinding.FragmentNavBinding;
import com.schneewittchen.rosandroid.utility.Constants;
import com.schneewittchen.rosandroid.viewmodel.MasterViewModel;
import com.schneewittchen.rosandroid.viewmodel.NavViewModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NavigationFragment extends Fragment {

    private FragmentNavBinding binding;
    private NavViewModel mViewModel;
    protected AutoCompleteTextView sourceField;
    protected AutoCompleteTextView destinationField;
    private Button gobtn;

    private String source,destination;

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "http://www.mocky.io/v2/597c41390f0000d002f4dbd1";




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNavBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(Constants.LOGTAG,"view created");
        mViewModel = new ViewModelProvider(requireActivity()).get(NavViewModel.class);

        // Define Views --------------------------------------------------------------
        sourceField = getView().findViewById(R.id.source_loc_editText);
        destinationField =  getView().findViewById(R.id.destination_loc_editText);
        gobtn = getView().findViewById(R.id.gobutton);

        ArrayAdapter locSpinnerAdapter = new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_dropdown_item_1line,Constants.LOCATION_LIST);
        sourceField.setAdapter(locSpinnerAdapter);
        destinationField.setAdapter(locSpinnerAdapter);

        sourceField.setText(locSpinnerAdapter.getItem(0).toString(),false);
        sourceField.setEnabled(false);

        sourceField.setOnClickListener(clickedView -> {
            sourceField.showDropDown();
        });

        destinationField.setOnClickListener(clickedView -> {
            destinationField.showDropDown();
        });
        gobtn.setOnClickListener(v -> {
            source = sourceField.getText().toString();
            destination = destinationField.getText().toString();
            if (!(source.isEmpty()||destination.isEmpty())){
                if (Constants.IS_MASTER_CONNECTED){
                    sendRequestToRobot();
                }else {
                    Toast.makeText(this.getContext(), "Please connect to ROS master before executing", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(this.getContext(), "Please provide Source and Destination", Toast.LENGTH_SHORT).show();
        });

    }
    private void sendRequestToRobot() {

        Toast.makeText(getContext(),"Executing your goal!" , Toast.LENGTH_LONG).show();
        //String url = "http://"+Constants.MASTER_IP+":"+Constants.API_PORT+"/navigation"+"?source="+source+"&destination="+destination;
        String url = "http://"+Constants.API_SERVER_IP+":"+Constants.API_PORT+"/navigation"+"?source="+source+"&destination="+destination;

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this.getContext());

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                Toast.makeText(getContext(),"Goal Reached !", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(Constants.LOGTAG,"Error :" + error.toString());
                Toast.makeText(getContext(),"Error :" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mRequestQueue.add(mStringRequest);
    }

}
