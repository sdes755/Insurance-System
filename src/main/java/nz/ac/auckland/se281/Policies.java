package nz.ac.auckland.se281;

public abstract class Policies {
  // Creating protected variables that each policy have in common and are used in the abstract
  // method and for getters
  protected int sumInsured;
  protected int basePremium;
  protected String userName;
  protected double age;
  // Creating a constructor that takes in the options array, the user name and the age
  public Policies(String[] options, String userName, double age) {
    this.sumInsured = Integer.parseInt(options[0]);
    this.userName = userName;
    this.age = age;
  }
  // Creating an abstract method that will be implemented in the sub classes to calculate the base
  // premium
  public abstract int BasePremium();
  // Creating a getting to return the user name for the policies
  public String getUserName() {
    return userName;
  }
}
