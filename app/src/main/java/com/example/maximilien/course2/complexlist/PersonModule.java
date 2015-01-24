package com.example.maximilien.course2.complexlist;

import android.graphics.Color;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import org.roboguice.shaded.goole.common.collect.ImmutableList;

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
        return ImmutableList.of(
                new Person("Jean", "Marc", Color.BLUE),
                new Person("John", "Doe", Color.RED));
    }
}
