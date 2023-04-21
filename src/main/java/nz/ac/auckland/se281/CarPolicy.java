package nz.ac.auckland.se281;

public class CarPolicy extends Policies {
  private String makeModel;
  private boolean mechB;
  public CarPolicy(String[] options, String user, int age) {
    super(options, user, age);

    this.makeModel = options[1];
    this.mechB = Boolean.parseBoolean(options[3]);
  }

  @Override
  public int setBasePremium() {
    int basePremium = 0;
    if (mechB) {
      ;
    } else {
      ;
    }
    return basePremium;
  }

}
