# java-testing-concepts

###  Run tests
From Command Line:
- All tests: `./gradlew clean test`
- Integration tests: `./gradlew clean test -Ptype=it`
- Run only Checkstyle and SpotBugs: `./gradle check`.  This runs the `checkstyleMain, checkstyleTest, spotbugsMain, spotbugsTest` Gradle tasks. Gradle will not run the `check` task on subsequent runs if there haven't been any code changes.
- Run only Checkstyle and SpotBugs even if already run, and without tests: `./gradlew clean check -x test`.

From IntelliJ:
- All tests: Create a run configuration for the `test` folder by right-clicking on `src/test` and selecting `Run...`.
- Integration tests: Same as for All Tests, but add `-Ptype=it` to arguments in run configuration.

### Topics Covered
## Annotations
- @Test
- @BeforeAll
- @AfterAll
- @BeforeEach
- @AfterEach
- @Disabled
- @DisplayName
- @TestInstance
- @EnabledOnOs(OS.MAC)
- @DisabledOnOs(OS.WINDOWS)
- @EnabledOnJre(JRE.JAVA_11)
- @EnabledIf
- @EnabledIfSystemProperty
- @EnabledIfEnvironmentVariable
- @Nested
- @RepeatedTest 

### Helpful Links
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [JUnit 5 Example Projects](https://github.com/junit-team/junit5-samples)
- https://www.baeldung.com/junit-5-conditional-test-execution
- https://www.youtube.com/watch?v=2E3WqYupx7c&list=PLqq-6Pq4lTTa4ad5JISViSb2FVG8Vwa4o
- https://github.com/koushikkothagal/junit-5-basics-course
