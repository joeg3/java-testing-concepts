package org.example;

public class Employee {
  private String firstName;
  private String lastName;
  private double salary;

  public Employee(String firstName, String lastName, double salary) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = salary;
  }

  public String getFirstName() {
    return firstName;
  }

  public Employee setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public Employee setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public double getSalary() {
    return salary;
  }

  public Employee setSalary(double salary) {
    this.salary = salary;
    return this;
  }
}
