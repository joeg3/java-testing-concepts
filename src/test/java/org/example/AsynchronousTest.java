package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.Duration;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class AsynchronousTest {
  private Asynchronous asyncApi;

  // Awaitility constant
  private static final ConditionFactory WAIT = await()
      .atMost(Duration.ofSeconds(6L))
      .pollInterval(Duration.ofSeconds(1L))
      .pollDelay(Duration.ofSeconds(1L)); // Initial delay

  @BeforeEach
  void resetAsyncObject() {
    asyncApi = new Asynchronous();
  }

  @Test
  @Timeout(6) // Poll at most 6 seconds
  @DisplayName("Asynchronous polling using JUnit 5 polling mechanism")
  void testAsynchronousMethodUsingJUnit5() throws InterruptedException {
    boolean result = asyncApi.asynchronousMethod();
    while (!result) { // If not expected result, keep trying every second for up to 6 seconds, then give up
      Thread.sleep(1000L); // Custom poll interval
      result = asyncApi.asynchronousMethod();
    }
    assertTrue(result);
  }

  @Test
  @Disabled("This purposely fails to demonstrate timeout trying to call asynchronous method")
  @Timeout(3) // Poll at most 3 seconds
  @DisplayName("Asynchronous polling using JUnit 5 times out and fails test")
  void testAsynchronousMethodUsingJUnit5Timeout() throws InterruptedException {
    boolean result = asyncApi.asynchronousMethod();
    while (!result) { // If not expected result, keep trying every 10ms for up to 3 seconds, then give up
      Thread.sleep(1000L); // Custom poll interval
      result = asyncApi.asynchronousMethod();
    }
    assertTrue(result);
  }

  @Test
  @DisplayName("Asynchronous polling using Awaitility constant")
  void testAsynchronousMethodUsingAwaitility() {
    WAIT.untilAsserted(() -> {
      assertTrue(asyncApi.asynchronousMethod());
    });
  }

  @Test
  @DisplayName("Asynchronous polling using Awaitility and AssertJ")
  void testAsynchronousMethodUsingAwaitilityAndAssertJ() {
    await()
        .atMost(Duration.ofSeconds(6L))
        .pollInterval(Duration.ofSeconds(1L))
        .pollDelay(Duration.ofSeconds(1L))
        .untilAsserted(() -> assertThat(asyncApi.asynchronousMethod()).isTrue());
  }

}
