package nz.ac.auckland.se281;

public class HomePolicy extends Policies {
  // private String address;
  // private Boolean rental;

  // public HomePolicy(){}

  @Override
  public int setPremium(String[] options, int age, String user) {
    int sumInsured = Integer.parseInt(options[0]);
    String answer = options[2].toLowerCase();
    double baseP;
    if (answer.equals("y")) {
      baseP = 0.02 * sumInsured;
    } else {
      baseP = 0.01 * sumInsured;
    }
    int basep = (int) baseP;
    MessageCli.NEW_POLICY_CREATED.printMessage("home", user);
    System.out.println(basep);
    return basep;
  }
}
