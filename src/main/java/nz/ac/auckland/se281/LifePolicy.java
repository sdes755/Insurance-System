package nz.ac.auckland.se281;

public class LifePolicy extends Policies {
  Profiles profiles = new Profiles();

  @Override
  public int basePremium(String[] options) {
    String name = profiles.userLoaded();
    int age = profiles.userloadAge();
    if (age > 100) {
      MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(name);
    }
    int sumInsured = Integer.parseInt(options[0]);
    int baseP = ((1 + age / 100) / 100) * sumInsured;

    return baseP;
  }
}
