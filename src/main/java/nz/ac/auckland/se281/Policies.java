package nz.ac.auckland.se281;

public abstract class Policies {

  protected int sumInsured;
  private int basePremium;
  protected String userName;
  protected int age;

  public Policies(String[] options, String userName, int age) {
    this.sumInsured = Integer.parseInt(options[0]);
    this.userName = userName;
    this.age = age;
    setBasePremium();
  }

  public abstract int setBasePremium();
}
