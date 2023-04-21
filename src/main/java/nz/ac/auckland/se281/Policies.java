package nz.ac.auckland.se281;

public abstract class Policies {

  protected int sumInsured;
  protected int basePremium;
  protected String userName;
  protected double age;

  public Policies(String[] options, String userName, double age) {
    this.sumInsured = Integer.parseInt(options[0]);
    this.userName = userName;
    this.age = age;
  }

  public abstract int setBasePremium();
}
