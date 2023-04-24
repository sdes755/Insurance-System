package nz.ac.auckland.se281;

public class LifePolicy extends Policies {
  // Creating a constructor with the super constructor from the Policies class
  public LifePolicy(String[] options, String userName, double age) {
    super(options, userName, age);
    // Calling the abstract method from the Policies class
    BasePremium();
  }

  // Creating getters for the user, sum insured and base premium, which is
  // required for printing out
  // the policy details
  public String getUser() {
    return userName;
  }

  public int getSumInsured() {
    return sumInsured;
  }

  public int getBasePremium() {
    return basePremium;
  }

  // Overriding the abstract method from the Policies class to calculate the base
  // premium for the
  // life
  @Override
  public int BasePremium() {
    // Base premium is 1% of the sum insured, plus 1% for each year of age
    basePremium = (int) (sumInsured * 0.01 * (1 + (age / 100)));
    // Printing out the message when a new life policy is created
    MessageCli.NEW_POLICY_CREATED.printMessage("life", userName);
    return basePremium;
  }
}
