package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profiles {
  // Crecating two array lists for Usernames and the Ages to be stored in
  private ArrayList<String> Usernames = new ArrayList<String>();

  private ArrayList<Integer> Ages = new ArrayList<Integer>();

  private ArrayList<String> loadedUsers = new ArrayList<String>();

  private ArrayList<Integer> loadedUsersAge = new ArrayList<Integer>();

  // Creating a method to store the Usernames and Ages in the array lists
  public void addUserAges(String userName, int age) {
    // Using a for loop to check if the inputted username is unique
    int truth = 1;
    if (loadedUsers.size() == 1) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(loadedUsers.get(0));
      return;
    }
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
    int loadnum = loadedUsers.size();
    int rank = 1;
    // Using a for loop to run through each element in the array list and printing it out one by one
    // in the database
    for (int i = 0; i < num; i++) {
      if (((loadnum == 1) && (loadedUsers.get(0)).equals(Usernames.get(i)))) {
        MessageCli.PRINT_DB_PROFILE_HEADER_SHORT.printMessage(
            "*** ", Integer.toString(rank), Usernames.get(i), Integer.toString(Ages.get(i)));
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
            Integer.toString(rank), Usernames.get(i), Integer.toString(Ages.get(i)));
      }
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
    int profileIndex = 0;

    for (int i = 0; i < Usernames.size(); i++) {

      if (userName.equals(Usernames.get(i)) == true) {
        truth = 1;
        profileIndex = i;
      }
    }
    if (truth == 1 && loadedUsers.size() == 0) {
      loadedUsers.add(userName);
      loadedUsersAge.add(Ages.get(profileIndex));
      MessageCli.PROFILE_LOADED.printMessage(userName);
    } else if (truth == 1 && loadedUsers.size() == 1) {

      loadedUsers.remove(0);
      loadedUsersAge.remove(0);
      loadedUsers.add(userName);
      loadedUsersAge.add(Ages.get(profileIndex));
      MessageCli.PROFILE_LOADED.printMessage(userName);
    } else {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
    }
  }

  public void profileUnload() {
    if (loadedUsers.size() == 0) {
      MessageCli.NO_PROFILE_LOADED.printMessage();
    } else {
      String userUnload = loadedUsers.get(0);
      loadedUsers.remove(0);
      loadedUsersAge.remove(0);
      MessageCli.PROFILE_UNLOADED.printMessage(userUnload);
    }
  }

  public void profileDelete(String userName) {
    int loadnum = loadedUsers.size();
    if (loadnum == 1 && (userName.equals(loadedUsers.get(0)))) {
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
      return;
    }
    int profileCheck = 0;
    int profileIndex = 0;
    for (int i = 0; i < Usernames.size(); i++) {
      if (userName.equals(Usernames.get(i)) == true) {
        profileCheck = 1;
        profileIndex = i;
      }
    }

    if (profileCheck == 1) {
      Usernames.remove(profileIndex);
      Ages.remove(profileIndex);
      MessageCli.PROFILE_DELETED.printMessage(userName);
    } else {
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
    }
  }

  public int loadProfCheck() {
    int truth = 0;
    if (loadedUsers.size() == 1) {
      truth = 1;
    } else {
      truth = 0;
    }
    return truth;
  }

  public ArrayList<String> getList() {
    return loadedUsers;
  }

  public ArrayList<Integer> getList2() {
    return loadedUsersAge;
  }
}
