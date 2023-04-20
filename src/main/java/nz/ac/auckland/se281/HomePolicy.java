package nz.ac.auckland.se281;

import java.util.ArrayList;

public class HomePolicy extends Policies {

  @Override
  public int basePremium(
      String[] options, ArrayList<String> loadedUsers, ArrayList<Integer> loadedUsersAge) {
    int sumInsured = Integer.parseInt(options[0]);
    String name = loadedUsers.get(0);
    String answer = options[2].toLowerCase();
    double baseP;
    if (answer.equals("y")) {
      baseP = 0.02 * sumInsured;

    } else {
      baseP = 0.01 * sumInsured;
    }
    int basep = (int) baseP;
    System.out.println(basep);
    MessageCli.NEW_POLICY_CREATED.printMessage("home", name);
    return basep;
  }
}
