package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// @TestInstance(TestInstance.Lifecycle.PER_METHOD) is the default where the a
// new instance of TestInstanceTest is created for each @Test method.
// You can change the default and have only one instance of TestInstanceTest created for all @Test methods
// This is generally bad practice. However, if you do it, then the @BeforeAll method doesn't have to be static
// and the count instance variable maintains state between each test.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestInstanceTest {
    int count = 0;

    @Test
    void test1() {
        count++;
        System.out.println("In test1(), count = " + count);
    }

    @Test
    void test2() {
        count++;
        System.out.println("In test2(), count = " + count);
    }

    @Test
    void test3() {
        count++;
        System.out.println("In test3(), count = " + count);
    }

    @Test
    void test4() {
        count++;
        System.out.println("In test4(), count = " + count);
    }
}