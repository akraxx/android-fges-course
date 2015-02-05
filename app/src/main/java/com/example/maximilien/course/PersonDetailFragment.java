package com.example.maximilien.course;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maximilien.course.complexlist.Person;


public class PersonDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PERSON = "PERSON_KEY";

    private Person mPerson;



    public PersonDetailFragment() {
        // Required empty public constructor
    }

    public static PersonDetailFragment newInstance(Person person) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_PERSON, person);

        PersonDetailFragment fragment = new PersonDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
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

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        Bundle args = getArguments();
//
//        if(args != null) {
//            Person person = args.getParcelable(ARG_PERSON);
//        }
//    }
}
