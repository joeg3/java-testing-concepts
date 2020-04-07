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
    class AddTest { // If an inner fails, then AddTest also fails

        @BeforeEach
        void youCanHaveBeforeEachInNestedTest() {}

        @Test
        @DisplayName("when adding two positive numbers")
        // If this fails, it will display:  When running library test > add method > when adding two positive numbers should return the right sum
        void testAddPositive() {
            assertEquals(5, sut.addNumbers(2, 3), "should return the right sum");
        }

        @Test
        @DisplayName("when adding two negative numbers")
        void testAddNegative() {
            assertEquals(-5, sut.addNumbers(-2, -3), "should return the right sum");
        }

        @Test
        @DisplayName("when adding zero to number")
        void testAddZero() {
            assertEquals(5, sut.addNumbers(0, 5), "should return the right sum");
        }
    }

    @Test
    @DisplayName("Demonstrating @DisplayName") // This string is displayed instead of "testSomeLibraryMethod()"
    void useDisplayNameAnnotation() {
        assertTrue(sut.libraryMethodReturnsTrue());
    }

    @Test
    @DisplayName("Examples of all the basic assertions")
    void basicAssertions() {
        int expected = -5;
        int actual = sut.addNumbers(-2, -3);

        // Can optionally add message on end of assertion
        assertEquals(5, sut.addNumbers(2, 3), "This message only displayed if failure");

        // The message for assertions is processed regardless if test passes or fails (but only displayed if failure).
        // For messages that are expensive to process, change to a lambda that returns a string, lambda will only be run if test fails
        assertEquals(expected, actual, () -> "should return sum " + expected + " but returned " + actual);

        assertNotEquals(6, sut.addNumbers(2, 3));
        assertNull(null);
        assertNotNull("This string is not null");
        assertTrue(true);
        assertFalse(false);
    }

    //@Disabled("You can disable entire classes too")
    @Test
    void testExpectedException() {
        Exception exception = assertThrows(ArithmeticException.class, () -> sut.divideNumbers(3, 0), "Divide by zero should throw exception");
        assertEquals("/ by zero", exception.getMessage());
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
    @DisplayName("Repeated Test")
    void computeCircleArea(RepetitionInfo repetitionInfo) { // RepetitionInfo param is optional
        assertEquals(314.1592653589793, sut.computeCircleArea(10), "Iteration " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions() + " should return correct circle area");
    }

    @Tag("it")
    @Test
    void expensiveIntegrationTest() {

    }

    @Tag("it")
    @Test
    void testReportingDemo(TestInfo testInfo) {
        assertEquals("testReportingDemo(TestInfo)", testInfo.getDisplayName());
        assertTrue(testInfo.getTags().contains("it"));
    }

    @Test
    void reportSingleValue(TestReporter testReporter) {
        testReporter.publishEntry("Not sure why this text doesn't go to std out. Maybe because invoking test with Gradle, both from command line and Intellij");
    }

}