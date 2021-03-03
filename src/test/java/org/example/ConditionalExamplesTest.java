package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@DisplayName("ConditionalExamplesTest: Demonstrate how to conditionally run tests in JUnit 5")
public class ConditionalExamplesTest {

    @EnabledOnOs({OS.MAC, OS.LINUX})
    @Test
    void enabledOnOs() { /* Disabled on non-mac/linux operating systems */ }

    @DisabledOnOs(OS.WINDOWS)
    @Test
    void disabledOnOs() { /* Enabled on non-windows operating systems */ }

    @EnabledOnJre({JRE.JAVA_10, JRE.JAVA_11})
    @Test
    void enabledOnJre() { /* Enabled only for Java 10, 11 */ }

    @DisabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_10)
    @Test
    void disabledOnJreRange() { /* Disabled for Java 8-10 */ }

    @Test
    @EnabledIfSystemProperty(named = "user.home", matches = "/Users/j.*")
    void enabledIfJvmSystemProperty() { /* Note use of regex */ }

    @Test
    @DisabledIfSystemProperty(named = "file.separator", matches = "[\\\\]")
    void disabledIfJvmFileSeparatorPropertyIsBackslash() { }

    @Test
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "staging")
    public void enabledIfEnvironmentVariable() { }

    @Test
    @DisabledIfEnvironmentVariable(named = "SHELL", matches = "/bin/zsh")
    public void disabledIfEnvironmentVariable() { }

    @Test
    @DisplayName("Show assumeTrue() with value of false will skip test")
    void runTestBasedOnAssumption() {
      // Instead of disabling a test with an annotation, we can also do it programmatically
      boolean isServerUp = false;
      assumeTrue(isServerUp); // If server up, run test, otherwise skip it
      fail("If assumeTrue() is false, we won't get to this line.");
    }

    @Test
    @DisplayName("Show assumingThat() with value of true runs code and test will pass/fail")
    void runCodeBasedOnAssumption() {
        boolean isServerUp = true;
        assumingThat(isServerUp, () -> assertTrue(true, "If server is up, run this check in lambda"));
        // Regardless if assumption valid or not, code execution will always continue here
        assertEquals(4,4, "This will run even if assumingThat() is false");
    }



}