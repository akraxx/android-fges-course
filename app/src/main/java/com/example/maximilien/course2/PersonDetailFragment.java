package com.example.maximilien.course2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.maximilien.course2.complexlist.Person;


public class PersonDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PERSON = "person_id";

    // TODO: Rename and change types of parameters
    private Person mPerson;

    public PersonDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_PERSON)) {
            mPerson = getArguments().getParcelable(ARG_PERSON);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person_detail, container, false);

        if(mPerson != null) {
            ((TextView) view.findViewById(R.id.firstname_textview_details)).setText(mPerson.getFirstName());
            ((TextView) view.findViewById(R.id.lastname_textview_details)).setText(mPerson.getLastName());
            view.findViewById(R.id.color_view_details).setBackgroundColor(mPerson.getFavoriteColor());
        }

        return view;
    }
}
