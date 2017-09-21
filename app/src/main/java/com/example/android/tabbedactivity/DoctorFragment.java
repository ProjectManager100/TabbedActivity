package com.example.android.tabbedactivity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.tabbedactivity.Util.LogToast;

import org.w3c.dom.Text;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorFragment extends Fragment {

    TextView textView;
    ListView listView;
    private Context context;
    public static final String TAG="Tag";
    ContactsDatabaseAdapter contactsDatabaseAdapter;
    public DoctorFragment() {
        // Required empty public constructor


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_doctor, container, false);
        floatingActionBar(view);

        textView=view.findViewById(R.id.testtextView);
        listView=view.findViewById(R.id.doctor_details_list_view);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context=getContext();
        contactsDatabaseAdapter=new ContactsDatabaseAdapter(context);

        LogToast.L(TAG,"form activity created");
        //textView.setText(contactsDatabaseAdapter.loadData());

        try{
            listView.setAdapter(contactsDatabaseAdapter.populateView());
        }catch (Exception e){
            LogToast.L(TAG,"in catch statement"+e.toString());
        }

        LogToast.L(TAG,"form activity created 2");


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
