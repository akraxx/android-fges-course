package com.example.maximilien.course;

import android.graphics.Color;

import com.example.maximilien.course.complexlist.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maximilien on 05/02/2015.
 */
public class PersonContainer {

    private static List<Person> persons = new ArrayList<>();

    public static List<Person> getPersons() {
        if(persons.size() < 1) {
            buildPersonList();
        }

        return persons;
    }

    public static void addPersons(Person... personsToAdd) {
        persons.addAll(Arrays.asList(personsToAdd));
    }

    private static void buildPersonList() {
        persons.add(new Person("Jean", "Marc", Color.BLUE));
        persons.add(new Person("John", "Doe", Color.RED));
        persons.add(new Person("Juan", "Marco", Color.YELLOW));
    }

}
