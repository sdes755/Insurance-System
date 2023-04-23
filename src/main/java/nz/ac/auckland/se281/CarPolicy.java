package nz.ac.auckland.se281;

public class CarPolicy extends Policies {
  private String makeModel;
  private boolean mechB;

  public CarPolicy(String[] options, String user, double age) {
    super(options, user, age);

    this.makeModel = options[1];
    if (options[3].compareTo("yes") == 0) {
      this.mechB = true;
    } else {
      this.mechB = false;
    }
    BasePremium();
  }

  public String getUser() {
    return userName;
  }

  public String getMakeModel() {
    return makeModel;
  }

  public int getSumInsured() {
    return sumInsured;
  }

  public int getBasePremium() {
    return basePremium;
  }

  @Override
  public int BasePremium() {
    double baseP;
    if (age > 24) {
      baseP = 0.1 * sumInsured;
    } else {
      baseP = 0.15 * sumInsured;
    }
    if (mechB) {
      basePremium = (int) baseP + 80;
    } else {
      basePremium = (int) baseP;
    }
    MessageCli.NEW_POLICY_CREATED.printMessage("car", userName);
    return basePremium;
  }
}
