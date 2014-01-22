package com.icejoywoo.commons.entity;

import com.icejoywoo.commons.entity.Person.Gender;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void testToString() {
        Person person = new Person("John", Gender.male, 12);
        System.out.println(person);
    }

    @Test
    public void testEquals() {
        Person person1 = new Person("John", Gender.male, 12);
        Person person2 = new Person("John", Gender.male, 12);
        assertTrue(person1.equals(person2));
    }

    @Test
    public void testEqualsNotEquals() {
        Person person1 = new Person("John", Gender.female, 12);
        Person person2 = new Person("John1", Gender.male, 12);
        assertFalse(person1.equals(person2));
    }

    @Test
    public void testHashCode() {
        Person person1 = new Person("John", Gender.male, 12);
        Person person2 = new Person("John", Gender.male, 12);
        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    public void testHashCodeNotEqual() {
        Person person1 = new Person("John1", Gender.male, 12);
        Person person2 = new Person("John", Gender.female, 12);
        assertNotEquals(person1.hashCode(), person2.hashCode());
    }
}
