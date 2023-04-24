package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).

  }
  // Calling the profiles class to use the methods in it
  Profiles profiles = new Profiles();
  // Printing the data base
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
    ;
  }
  // Creating a new profile and adding it to the database
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
  // Loading the profile from the data base for policy creating
  public void loadProfile(String userName) {
    // Formatting the username which we want to load into the correct format
    userName = userName.toLowerCase();
    String correctUser = userName.substring(0, 1).toUpperCase() + userName.substring(1);
    // Calling the method in Profiles class to load the profile
    profiles.profileLoad(correctUser);
  }
  // Unloading the profile from the database
  public void unloadProfile() {
    // Calling the method in Profiles class to unload the profile
    profiles.profileUnload();
  }
  // Deleting the profile from the database
  public void deleteProfile(String userName) {
    // Formatting the username of the profile we want to delete into the correct format so it's
    // easier to match with the database and delete
    userName = userName.toLowerCase();
    String correctUser = userName.substring(0, 1).toUpperCase() + userName.substring(1);
    // Calling the method in Profiles class to delete the profile
    profiles.profileDelete(correctUser);
  }
  // Creating and storing policies for users in the database
  public void createPolicy(PolicyType type, String[] options) {
    // Calling the method in Profiles class to store the policy
    profiles.storePolicy(type, options);
  }
}
