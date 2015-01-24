package com.example.maximilien.course2.complexlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Maximilien on 22/01/2015.
 */
@AllArgsConstructor(suppressConstructorProperties=true)
@NoArgsConstructor
@Data
public class Person {
    private String lastName;
    private String firstName;
    private int favoriteColor;
}
