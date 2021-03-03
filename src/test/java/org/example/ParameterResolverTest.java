package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

/**
 * JUnit test constructors and methods can have parameters that implement
 * the ParameterResolver interface. JUnit has three build in resolvers: TestInfo,
 * TestReporter, and RepetitionInfo. You can also create your own.
 */
public class ParameterResolverTest {

  ParameterResolverTest(TestInfo testInfo) {
    assertEquals("ParameterResolverTest", testInfo.getDisplayName());
  }

  @BeforeEach
  void setUp(TestInfo testInfo) {
    // As setUp() is run for each test method, you can get info for that test method
    String currentTestMethodName = testInfo.getDisplayName();
    System.out.println("Running test: " + currentTestMethodName);
  }

  @Test
  void testInfoGetMethodName(TestInfo testInfo) {
    assertEquals("testInfoGetMethodName(TestInfo)", testInfo.getDisplayName());
  }

  @DisplayName("My favorite test")
  @Test
  void testInfoGetDisplayName(TestInfo testInfo) {
    // If @DisplayName is used, testInfo.getDisplayName() returns it's value
    // instead of method name
    assertEquals("My favorite test", testInfo.getDisplayName());
  }

  @Tag("it")
  @Test
  void testInfoGetTagName(TestInfo testInfo) {
    assertTrue(testInfo.getTags().contains("it"));
  }

  /**
   * Recommended to use TestReporter instead of printing to stdout, however I couldn't
   * get it to output to stdout.  I posted this:
   * https://stackoverflow.com/questions/66441064/where-does-the-output-of-junit5-testreporter-go/66442336#66442336
   * and it turns out Gradle swallows the output of TestReporter. The answer in SO references this:
   * https://github.com/gradle/gradle/issues/4605 and shows some possible options
   */
  @Test
  void testReporterExamples(TestReporter testReporter) {
    System.out.println("Before test reporter");
    testReporter.publishEntry("My message");
    testReporter.publishEntry("key", "value");
    Map<String, String> map = new HashMap<>();
    map.put("k1", "v1");
    map.put("k2", "v2");
    testReporter.publishEntry(map);
    System.out.println("After test reporter");
  }

  @RepeatedTest(value = 3, name = "{displayName} - {currentRepetition}/{totalRepetitions}") // Run this test 3 times
  @DisplayName("Repeated Test") // Each iteration will display name of "Repeated Test 1/3", etc.
  void computeCircleArea(RepetitionInfo repetitionInfo) { // RepetitionInfo param is optional
    int iteration = repetitionInfo.getCurrentRepetition();
    int total = repetitionInfo.getTotalRepetitions();
    assertTrue(iteration <= total, "Iteration " + iteration + " of " + total);
  }
}
