package nz.ac.auckland.se281;

public class LifePolicy extends Policies {
  Profiles profiles = new Profiles();

  @Override
  public int basePremium(String[] options) {
    int age = profiles.userloadAge();
    int sumInsured = Integer.parseInt(options[0]);
    int baseP = ((1 + age / 100) / 100) * sumInsured;

    return baseP;
  }
}
