package com.example.maximilien.course;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maximilien.course.complexlist.Person;
import com.example.maximilien.course.complexlist.PersonListAdapter;

import java.util.ArrayList;
import java.util.List;

public class DrawerFragment extends Fragment {

    public DrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Person> personList = PersonContainer.getPersons();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);


        PersonListAdapter adapter = new PersonListAdapter(getActivity(), personList);
        listView.setAdapter(adapter);

        return view;
    }

}
