package com.example.maximilien.course.complexlist;

import android.graphics.Color;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maximilien on 22/01/2015.
 */
public class PersonModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    public List<Person> getPersonList() {
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("Jean", "Marc", Color.BLUE));
        persons.add(new Person("John", "Doe", Color.RED));
        persons.add(new Person("Juan", "Marco", Color.YELLOW));
        return persons;
    }
}
