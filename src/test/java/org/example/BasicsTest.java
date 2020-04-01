package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("When running library test")
public class BasicsTest {
    Library sut;

    @BeforeAll
    static void initBeforeAllTestsInThisClass() { }

    @AfterAll
    static void cleanupAfterAllTestsInThisClass() { }

    @BeforeEach
    void initBeforeEachTestInThisClass() {
        sut = new Library();
    }

    @AfterEach
    void cleanupAfterEachTestInThisClass() { }

    @Nested // Groups the tests within nested class AddTest as one test
    @DisplayName("add method")
    class AddTest { // If say testAddNegative() fails, then AddTest also fails
        @Test
        @DisplayName("when adding two positive numbers")
        // If this fails, it will display:  When running library test > add method > when adding two positive numbers should return the right sum
        void testAddPositive() {
            assertEquals(5, sut.addNumbers(2, 3), "should return the right sum");
        }

        @Test
        @DisplayName("when adding two negative numbers")
        void testAddNegative() {
            // The message to assertEquals is processed regardless if test passes or fails. By changing it to a Lambda that
            // returns a string, it will only be run if test fails, which is good if composing the string is an expensive operation
            int expected = -5;
            int actual = sut.addNumbers(-2, -3);
            assertEquals(expected, actual, () -> "should return sum " + expected + " but returned " + actual);
        }

        @Test
        @DisplayName("when adding zero to number")
        void testAddZero() {
            assertEquals(5, sut.addNumbers(0, 5), "should return the right sum");
        }
    }

    @Test
    @DisplayName("Testing a library method that returns true") // This string is displayed instead of "testSomeLibraryMethod()"
    void testSomeLibraryMethod() {
        assertTrue(sut.libraryMethodReturnsTrue());
    }

    @Test
    void testAddNumbers() {
        assertEquals(5, sut.addNumbers(2, 3), "This message only displayed if failure");
    }

    @Test
    void testExpectedException() {
        assertThrows(ArithmeticException.class, () -> sut.divideNumbers(3, 0), "Divide by zero should throw exception");
    }

    @Disabled
    @Test
    void testDisabled() {
        fail("This test would normally fail, but we have it disabled");
    }

    @Test
    void assertAllExample() {
        // Group similar tests together. Also, instead of separate assert statements where
        // if the first one fails, the others aren't run, here all three asserts are run
        // so you can see if later asserts pass or fail.


        Employee employee = new Employee("Jane", "Doe", 100000);

        assertAll(
          () -> assertEquals("Jane", employee.getFirstName()),
          () -> assertEquals("Doe", employee.getLastName()),
          () -> assertEquals(100000, employee.getSalary())
        );
    }

    @Test
    void verifyNotTheSameInstance() {
        String x = new String("Hi");
        String y = new String("Hi");
        assertEquals(x, y, "Variables x and y should have the same value.");
        assertNotSame(x, y, "Variables x and y should not be the same instance.");
    }

    @RepeatedTest(3) // Run this test 3 times
    void computeCircleArea(RepetitionInfo repetitionInfo) { // RepetitionInfo param is optional
        repetitionInfo.getCurrentRepetition(); // You can use RepetitionInfo object if you want
        assertEquals(314.1592653589793, sut.computeCircleArea(10),
          "Should return correct circle area");
    }

    @Tag("it")
    @Test
    void expensiveIntegrationTest() {

    }

    @Tag("it")
    @Test
    void testReportingDemo(TestInfo info, TestReporter reporter) {
        // Not sure why I can't get this to work!
        //System.out.println("Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
        System.out.println("Before");
        reporter.publishEntry(info.getDisplayName());
        System.out.println("After");
        //testReporter.publishEntry("Test Reporter: Running " + testInfo.getDisplayName() + " with tags " + testInfo.getTags());
    }
}