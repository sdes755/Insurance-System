package nz.ac.auckland.se281;

import java.util.ArrayList;

public class CarPolicy extends Policies {
  Profiles profiles = new Profiles();

  @Override
  public int basePremium(
      String[] options, ArrayList<String> loadedUsers, ArrayList<Integer> loadedUsersAge) {
    int age = loadedUsersAge.get(0);
    String name = loadedUsers.get(0);
    int sumInsured = Integer.parseInt(options[0]);
    double basePBMW;
    String answer = options[3].toLowerCase();
    System.out.println(age);
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
    // System.out.println(basep);
    MessageCli.NEW_POLICY_CREATED.printMessage("car", name);
    return basep;
  }
}
