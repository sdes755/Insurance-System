package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).

  }

  Profiles profiles = new Profiles();

  public void printDatabase() {
    // Initialising a number variable for the for print-db statements using the method in Profiles
    // class
    int num = profiles.findNum();
    // Creating IF conditions to check which print-db message gets printed
    if (num == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(num), "", ":");
    } else if (num > 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(num), "s", ":");
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    }
    // After the header line gets printed, we print the databse using the method made in profiles
    // class
    profiles.printProfiles();
  }

  public void createNewProfile(String userName, String age) {
    // Made the input username into lowerstring, then capitalised the first letter of the Username
    userName = userName.toLowerCase();
    String beg = userName.substring(0, 1).toUpperCase() + userName.substring(1);

    // Used IF conditions to check for the correct formatting of the argument inputs

    // Using the method implemented in Profiles to check if its only numeric values in the string
    if (profiles.ageFormat(age) == 0) {
      MessageCli.INVALID_AGE.printMessage(age, beg);
    } else if (beg.length() < 3) {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(beg);
    } else if (Integer.parseInt(age) < 0) {
      MessageCli.INVALID_AGE.printMessage(age, beg);
    } else {
      // If the input passes the parameters, we print the username and age.
      profiles.addUserAges(beg, Integer.parseInt(age));
    }
  }

  public void loadProfile(String userName) {
    userName = userName.toLowerCase();
    String correctUser = userName.substring(0, 1).toUpperCase() + userName.substring(1);

    profiles.profileLoad(correctUser);
  }

  public void unloadProfile() {
    profiles.profileUnload();
  }

  public void deleteProfile(String userName) {
    userName = userName.toLowerCase();
    String correctUser = userName.substring(0, 1).toUpperCase() + userName.substring(1);
    profiles.profileDelete(correctUser);
  }

  HomePolicy home = new HomePolicy();
  CarPolicy car = new CarPolicy();
  LifePolicy life = new LifePolicy();

  public void createPolicy(PolicyType type, String[] options) {

    int truth = profiles.loadProfCheck();
    ArrayList<String> loadedUsers = profiles.getList();
    ArrayList<Integer> loadedUsersAge = profiles.getList2();
    if (truth == 0) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
    }
    if ((type.toString()).equals("HOME")) {
      home.basePremium(options, loadedUsers, loadedUsersAge);
    } else if ((type.toString()).equals("CAR")) {
      car.basePremium(options, loadedUsers, loadedUsersAge);
    } else {
      life.basePremium(options, loadedUsers, loadedUsersAge);
    }
  }
}
