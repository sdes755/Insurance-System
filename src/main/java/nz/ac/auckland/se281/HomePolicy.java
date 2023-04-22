package nz.ac.auckland.se281;

public class HomePolicy extends Policies {

  private String address;
  private boolean rental;

  public HomePolicy(String[] options, String userName, double age) {
    super(options, userName, age);
    this.address = options[1];
    if (options[2].compareTo("yes") == 0) {
      this.rental = true;
    } else {
      this.rental = false;
    }
    setBasePremium();
  }

  public String getUser() {
    return userName;
  }

  public String getAddress() {
    return address;
  }

  public int getSumInsured() {
    return sumInsured;
  }

  public int getBasePremium() {
    return basePremium;
  }

  @Override
  public int setBasePremium() {

    if (rental) {
      basePremium = (int) (sumInsured * 0.02);
    } else {
      basePremium = (int) (sumInsured * 0.01);
    }
    MessageCli.NEW_POLICY_CREATED.printMessage("home", userName);
    return basePremium;
  }
}
