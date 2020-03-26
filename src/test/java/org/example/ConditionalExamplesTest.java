package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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
    void showAssumptions() {
      // Instead of disabling a test with an annotation, we can also do it programmatically
      boolean isServerUp = true;
      assumeTrue(isServerUp); // If server up, run test, otherwise skip it
    }



}