package com.example.maximilien.course2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.maximilien.course2.complexlist.Person;
import com.example.maximilien.course2.complexlist.PersonListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PersonListFragment extends Fragment {
    public static final String LIST_PERSONS = "list_persons";
    private GridView mListView;
    private Button mAddPersonButton;

    private PersonListAdapter adapter;

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

        View view = inflater.inflate(R.layout.fragment_person_list, container, false);
        mListView = (GridView) view.findViewById(R.id.mylistview);
        mAddPersonButton = (Button) view.findViewById(R.id.addpersonbutton);

        adapter = new PersonListAdapter(getActivity(), personList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person person = personList.get(position);
                mCallback.onItemSelected(person);
            }
        });

        mAddPersonButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openAlert(v);
//                personList.add(new Person("Nom", "Prenom", Color.MAGENTA));
//                adapter.notifyDataSetChanged();
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
