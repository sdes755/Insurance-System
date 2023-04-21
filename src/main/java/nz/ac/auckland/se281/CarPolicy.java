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
    setBasePremium();
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
  public int setBasePremium() {
    double baseP;
    if (age > 25) {
      baseP = 0.1 * sumInsured;
    } else {
      baseP = 0.15 * sumInsured;
    }
    if (mechB) {
      basePremium = (int) baseP + 80;
    } else {
      basePremium = (int) baseP;
    }
    System.out.println(basePremium);
    MessageCli.NEW_POLICY_CREATED.printMessage("car", userName);
    return basePremium;
  }
}
