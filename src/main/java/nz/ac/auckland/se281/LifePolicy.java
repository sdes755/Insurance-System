package nz.ac.auckland.se281;

public class LifePolicy extends Policies {

  public LifePolicy(String[] options, String userName, double age) {
    super(options, userName, age);
    setBasePremium();
  }

  public int getSumInsured() {
    return sumInsured;
  }

  public int getBasePremium() {
    return basePremium;
  }

  @Override
  public int setBasePremium() {
    basePremium = (int) (sumInsured * 0.01 * (1 + (age / 100)));
    MessageCli.NEW_POLICY_CREATED.printMessage("life", userName);
    return basePremium;
  }
}
