package nz.ac.auckland.se281;

public class CarPolicy extends Policies {
  // Creating vairables that are unique to the car policy
  private String makeModel;
  private boolean mechB;
  // Creating the constructor that takes in the options array, the user name and the age
  public CarPolicy(String[] options, String user, double age) {
    // Calling the super constructor from the Policies class
    super(options, user, age);
    // Setting the makeModel and mechB variables using 'this' keyword
    this.makeModel = options[1];
    // Creating if conditions to check if the user has mechanical breakdown cover or not
    if (options[3].compareTo("yes") == 0) {
      this.mechB = true;
    } else {
      this.mechB = false;
    }
    // Calling the abstract method from the Policies class
    BasePremium();
  }
  // Creating getters for user, makeModel, sum insured and base premium
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
  // Overriding the abstract method from the parent class to calculate the base premium for the car
  // policy
  @Override
  public int BasePremium() {
    double baseP;
    // Checking if the user the policies being created for is above 24, and calculating the base
    // premium accordingly
    if (age > 24) {
      baseP = 0.1 * sumInsured;
    } else {
      baseP = 0.15 * sumInsured;
    }
    // Checking if the user has mechanical breakdown cover or not, and calculating the base premium
    // accordingly
    if (mechB) {
      basePremium = (int) baseP + 80;
    } else {
      basePremium = (int) baseP;
    }
    // Printing the message when a new car policy is created
    MessageCli.NEW_POLICY_CREATED.printMessage("car", userName);
    return basePremium;
  }
}
