package com.example.maximilien.course;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.maximilien.course.common.view.SlidingTabLayout;
import com.example.maximilien.course.complexlist.Person;
import com.example.maximilien.course.complexlist.PersonFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Random;


public class PersonListFragment extends Fragment {
    public static final String LIST_PERSONS = "list_persons";

    private PersonFragmentPagerAdapter adapter;

    private ArrayList<Person> personList = new ArrayList<>();

    public PersonListFragment() {
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
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);

        Button mAddPersonButton = (Button) view.findViewById(R.id.addpersonbutton);
        Button mNextPerson = (Button) view.findViewById(R.id.next_button);
        Button mPrevPerson = (Button) view.findViewById(R.id.prev_button);

        adapter = new PersonFragmentPagerAdapter(getChildFragmentManager(), personList);
        viewPager.setAdapter(adapter);
        slidingTabLayout.setViewPager(viewPager);

        mNextPerson.setOnClickListener(mNextPersonListener(viewPager));

        mPrevPerson.setOnClickListener(mPrevPersonListener(viewPager));

        mAddPersonButton.setOnClickListener(mAddPersonListener(slidingTabLayout, viewPager));

        slidingTabLayout.setCustomTabColorizer(getTabColorizer());

        return view;
    }

    private SlidingTabLayout.TabColorizer getTabColorizer() {
        return new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return personList.get(position).getFavoriteColor();
            }

            @Override
            public int getDividerColor(int position) {
                return Color.GRAY;
            }
        };
    }

    private View.OnClickListener mNextPersonListener(final ViewPager viewPager) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextItem = viewPager.getCurrentItem()+1;
                viewPager.setCurrentItem(nextItem);
            }
        };
    }

    private View.OnClickListener mAddPersonListener(final SlidingTabLayout slidingTabLayout, final ViewPager viewPager) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openAlert(slidingTabLayout, viewPager);
            }
        };
    }

    private View.OnClickListener mPrevPersonListener(final ViewPager viewPager) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = viewPager.getCurrentItem();
                viewPager.setCurrentItem(currentItem-1);
            }
        };
    }

    private void openAlert(final SlidingTabLayout slidingTabLayout, final ViewPager viewPager) {
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_PERSONS, personList);
    }

}
