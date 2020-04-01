package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ParameterizedTests: ")
public class ParameterizedExampleTest {
  enum Color {RED, GREEN, YELLOW, BLUE, ORANGE}

  @BeforeEach
  void beforeEachCalledBeforeEachParameterizedTestInvocation() {}

  @AfterEach
  void afterEachCalledAfterEachParameterizedTestInvocation() {}

  @ParameterizedTest(name = "test #{index} arg {0}}") // Displays: test #1 arg 0, test #2 arg -20, etc.
  @ValueSource(ints = { 0, -20, 30 }) // Value source can only be used on test methods with a single parameter
  @DisplayName("with @ValueSource")
  void parameterizedTestWithValueSource(int num) {
    assertEquals(0, num % 10);
  }

  @ParameterizedTest(name = "{index} => color=''{0}''") // Pass in values of an enumeration
  @EnumSource(Color.class)
  @DisplayName("with @EnumSource")
  void parameterizedTestWithEnumSource(Color color) {
    assertNotNull(color);
  }

  @ParameterizedTest // Pass in more than one arg per test, but overhead of another method to generate args
  @MethodSource("generateNumbersAndSquareRoots")
  @DisplayName("with @MethodSource")
  void parameterizedTestWithMethodSource(double num, double squareRoot) {
    assertEquals(squareRoot, Math.sqrt(num));
  }

  private static Stream<Arguments> generateNumbersAndSquareRoots() {
    return Stream.of(
      Arguments.of(9, 3),
      Arguments.of(25, 5));
  }

  @ParameterizedTest // Pass arguments in strings separated by commas
  @CsvSource({ "3, 9", "4, 16", "5, 25" }) // For large data sets, use a file: @CsvFileSource(resources = "test-data.csv")
  @DisplayName("with @CsvSource")
  void parameterizedTestWithCsvSource(double num, double square) {
    assertEquals(square, num * num);
    assertEquals(num, Math.sqrt(square));
  }
}
