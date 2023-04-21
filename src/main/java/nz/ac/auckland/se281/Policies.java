package nz.ac.auckland.se281;

public abstract class Policies {

  private int sumInsured;
  private int basePremium;
  private String userName;

  public Policies(String[] options, int basePremium, String userName) {
    this.sumInsured = Integer.parseInt(options[0]);
    this.basePremium = basePremium;
    this.userName = userName;
    setBasePremium();
  }

  public abstract int setBasePremium();
}
