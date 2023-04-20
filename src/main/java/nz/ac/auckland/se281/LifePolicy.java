package nz.ac.auckland.se281;

import java.util.ArrayList;

public class LifePolicy extends Policies {
  Profiles profiles = new Profiles();

  @Override
  public int basePremium(
      String[] options, ArrayList<String> loadedUsers, ArrayList<Integer> loadedUsersAge) {

    double age = loadedUsersAge.get(0);
    String name = loadedUsers.get(0);

    if (age > 100) {
      MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(name);
    }
    // System.out.println(loadedUsers);
    // System.out.println(age);
    int sumInsured = Integer.parseInt(options[0]);
    double baseP = sumInsured * ((1 + age / 100) / 100);
    int baseP1 = (int) baseP;
    // System.out.println(baseP1);
    MessageCli.NEW_POLICY_CREATED.printMessage("life", name);
    return baseP1;
  }
}
