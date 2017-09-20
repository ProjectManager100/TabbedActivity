package com.example.android.tabbedactivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorFragment extends Fragment {

    TextView textView;
    private Context context;
    public DoctorFragment() {
        // Required empty public constructor
        context=getContext();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_doctor, container, false);
        floatingActionBar(view);
        return view;
    }

    private void floatingActionBar(View view){
        FloatingActionButton fab = view.findViewById(R.id.add_doctor_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(context,"doctorfb",Toast.LENGTH_LONG).show();
                Log.d("shanto","done");
                Intent intent=new Intent(getActivity(),DoctorAddActivity.class);
                startActivity(intent);

            }
        });
    }

}
