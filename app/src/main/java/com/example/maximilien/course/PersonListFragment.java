package com.example.maximilien.course;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.maximilien.course.common.view.SlidingTabLayout;
import com.example.maximilien.course.complexlist.Person;
import com.example.maximilien.course.complexlist.PersonFragmentPagerAdapter;
import com.example.maximilien.course.complexlist.PersonListAdapter;

import java.util.ArrayList;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;


public class PersonListFragment extends Fragment {
    public static final String LIST_PERSONS = "list_persons";

    private ViewPager viewPager;
    private PersonFragmentPagerAdapter adapter;
    private GridView mListView;
    private Button mAddPersonButton;
    private Button mNextPerson;
    private Button mPrevPerson;
    private SlidingTabLayout slidingTabLayout;

    private ArrayList<Person> personList = new ArrayList<>();

    private ListCallBack mCallback;

    public PersonListFragment() {
        System.out.println("Construct fragment");
        // Required empty public constructor
    }

    public void buildPersonList() {
        personList.add(new Person("Jean", "Marc", Color.BLUE));
        personList.add(new Person("John", "Doe", Color.RED));
        personList.add(new Person("Juan", "Marco", Color.YELLOW));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null) {
            personList = savedInstanceState.getParcelableArrayList(LIST_PERSONS);
        } else {
            buildPersonList();
        }

        final View view = inflater.inflate(R.layout.fragment_person_list, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
//        mListView = (GridView) view.findViewById(R.id.mylistview);
        mAddPersonButton = (Button) view.findViewById(R.id.addpersonbutton);
        mNextPerson = (Button) view.findViewById(R.id.next_button);
        mPrevPerson = (Button) view.findViewById(R.id.prev_button);

        adapter = new PersonFragmentPagerAdapter(getChildFragmentManager(), personList);
        viewPager.setAdapter(adapter);
        slidingTabLayout.setViewPager(viewPager);

        mNextPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextItem = viewPager.getCurrentItem()+1;
                viewPager.setCurrentItem(nextItem);
            }
        });

        mPrevPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem-1);
            }
        });

        mAddPersonButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openAlert(v);
            }
        });

        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return personList.get(position).getFavoriteColor();
            }

            @Override
            public int getDividerColor(int position) {
                return Color.GRAY;
            }
        });

        return view;
    }

    private void openAlert(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setTitle("Enter your informations");

        final View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_person, null);
        alertDialogBuilder.setView(dialogView);



        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                EditText firstname = (EditText) dialogView.findViewById(R.id.firstname_editview);
                EditText lastname = (EditText) dialogView.findViewById(R.id.lastname_editview);

                Random rnd = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                personList.add(new Person(firstname.getText().toString(), lastname.getText().toString(), color));
                adapter.notifyDataSetChanged();
                slidingTabLayout.setViewPager(viewPager);
                viewPager.setCurrentItem(personList.size()-1);
            }
        });

        // set negative button: No message
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ListCallBack) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_PERSONS, personList);
    }

    public interface ListCallBack {
        void onItemSelected(Person person);
    }

}
