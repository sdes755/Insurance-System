package nz.ac.auckland.se281;

import java.util.ArrayList;

public abstract class Policies {

  public abstract int basePremium(
      String[] options, ArrayList<String> loadedUsers, ArrayList<Integer> loadedUsersAge);
}
