package nz.ac.auckland.se281;

public class CarPolicy extends Policies {
  Profiles profiles = new Profiles();

  @Override
  public int basePremium(String[] options) {
    int age = profiles.userloadAge();
    int sumInsured = Integer.parseInt(options[0]);
    double basePBMW;
    if (age < 25) {
      basePBMW = 0.15 * sumInsured;
    } else {
      basePBMW = 0.1 * sumInsured;
    }
    double baseP;
    if (options[3].equals("Yes")) {
      baseP = 80 + basePBMW;
    } else {
      baseP = basePBMW;
    }
    int basep = (int) baseP;
    return basep;
  }
}
