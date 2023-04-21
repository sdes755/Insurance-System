package nz.ac.auckland.se281;

public class CarPolicy extends Policies {
  // private String makeModel;
  // private String licensePlate;
  // private Boolean mechBreak;

  // public CarPolicy() {}

  @Override
  public int setPremium(String[] options, int age, String user) {
    int sumInsured = Integer.parseInt(options[0]);
    double basePBMW;
    String answer = options[3].toLowerCase();
    if (age < 25) {
      basePBMW = 0.15 * sumInsured;
    } else {
      basePBMW = 0.1 * sumInsured;
    }
    double baseP;
    if (answer.equals("y")) {
      baseP = 80 + basePBMW;
    } else {
      baseP = basePBMW;
    }
    int basep = (int) baseP;
    System.out.println(basep);
    MessageCli.NEW_POLICY_CREATED.printMessage("car", user);
    return basep;
  }
}
