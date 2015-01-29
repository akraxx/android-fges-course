package com.example.maximilien.course2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class PersonDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);

        if(savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(PersonDetailFragment.ARG_PERSON, getIntent().getParcelableExtra(PersonDetailFragment.ARG_PERSON));
            PersonDetailFragment fragment = new PersonDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction().add(R.id.person_detail_container, fragment).commit();
        }
    }
}
