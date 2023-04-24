package nz.ac.auckland.se281;

public class HomePolicy extends Policies {
  // Creating protected variables that is unique to the home policy
  private String address;
  private boolean rental;

  // Creating the constructor that takes in the options array, the user name and
  // the age
  public HomePolicy(String[] options, String user, int age) {
    // Calling the super constructor from the Policies class
    super(options, user, age);
    // Setting the address and rental variables using 'this' keyword
    this.address = options[1];
    // Setting the boolean to check if it is a rental or not
    if (options[2].compareTo("yes") == 0) {
      this.rental = true;
    } else {
      this.rental = false;
    }
    // Calling the abstract method from the Policies class
    BasePremium();
  }

  // Creating getters for user, Address, sum insured and base premium
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

  // Overriding the abstract method from the parent class to calculate the base
  // premium for the home
  // policy
  @Override
  public int BasePremium() {
    // Checking if it is a rental property or not and calculating the base premium
    // accordingly
    if (rental) {
      basePremium = (int) (sumInsured * 0.02);
    } else {
      basePremium = (int) (sumInsured * 0.01);
    }
    // Printing out the message when a new home policy is created
    MessageCli.NEW_POLICY_CREATED.printMessage("home", userName);
    return basePremium;
  }
}
