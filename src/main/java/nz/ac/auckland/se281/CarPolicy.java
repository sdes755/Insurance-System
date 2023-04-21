package nz.ac.auckland.se281;

public class CarPolicy extends Policies {
  private String makeModel;
  private boolean mechB;

  public CarPolicy(String[] options, String user, double age) {
    super(options, user, age);

    this.makeModel = options[1];
    this.mechB = Boolean.parseBoolean(options[3]);
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

    if (mechB) {

    } else {
      ;
    }
    return basePremium;
  }
}
