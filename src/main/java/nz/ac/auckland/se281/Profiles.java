package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profiles {
  // Creating two array lists for Usernames and the Ages to be stored in
  private ArrayList<String> Usernames = new ArrayList<String>();

  private ArrayList<Integer> Ages = new ArrayList<Integer>();

  private ArrayList<String> loadedUsers = new ArrayList<String>();

  // Creating a method to store the Usernames and Ages in the array lists
  public void addUserAges(String userName, int age) {
    // Using a for loop to check if the inputted username is unique
    int truth = 1;

    for (int i = 0; i < Usernames.size(); i++) {
      // If it is not unique, we change the truth value to 0, and it will not be added
      if (userName.equals(Usernames.get(i)) == true) {
        truth = 0;
      }
    }
    // Checking the truth value, if 1, we add the usernames and ages. if 0, we print the error
    // message.
    if (truth == 1) {
      Usernames.add(userName);
      Ages.add(age);
      MessageCli.PROFILE_CREATED.printMessage(userName, Integer.toString(age));
    } else {
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
    }
  }
  // Creating a new method to count the number of profiles in the database
  public void printProfiles() {

    int num = Usernames.size();

    int rank = 1;
    // Using a for loop to run through each element in the array list and printing it out one by one
    // in the database
    for (int i = 0; i < num; i++) {

      MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
          Integer.toString(rank), Usernames.get(i), Integer.toString(Ages.get(i)));
      rank = rank + 1;
    }
  }

  // Created a method to count the number of profiles in the database
  public int findNum() {
    int number = Usernames.size();

    return number;
  }
  // Created a method to check if the inputted age does not have a decimal point or character.
  public int ageFormat(String age) {
    // Intialising a truth value
    int truthv = 1;
    for (int i = 0; i < age.length(); i++) {
      // If not a digit, turth value is changed to zero
      if (!Character.isDigit(age.charAt(i))) {
        truthv = 0;
      }
    }
    // Truth value is returned, and if 1, the age is passed.
    return truthv;
  }

  public void profileLoad(String userName) {
    int truth = 0;
    for (int i = 0; i < Usernames.size(); i++) {

      if (userName.equals(Usernames.get(i)) == true) {
        truth = 1;
      }
    }
    if (truth == 1) {
      loadedUsers.add(userName);
      MessageCli.PROFILE_LOADED.printMessage(userName);
    } else {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
    }
  }
}
