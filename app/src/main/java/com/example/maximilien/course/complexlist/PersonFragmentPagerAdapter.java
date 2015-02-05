package com.example.maximilien.course.complexlist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.maximilien.course.PersonDetailFragment;

import java.util.List;

/**
 * Created by Maximilien on 05/02/2015.
 */
public class PersonFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Person> dataSource;

    public PersonFragmentPagerAdapter(FragmentManager fragmentManager,
                                      List<Person> personList) {
        super(fragmentManager);
        dataSource = personList;
    }

    @Override
    public Fragment getItem(int position) {
        return PersonDetailFragment.newInstance(dataSource.get(position));
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }
}
