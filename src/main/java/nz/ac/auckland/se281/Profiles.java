package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import nz.ac.auckland.se281.Main.PolicyType;

public class Profiles {
  // Crecating two array lists for Usernames and the Ages to be stored in
  private ArrayList<String> Usernames = new ArrayList<String>();
  private ArrayList<Integer> Ages = new ArrayList<Integer>();

  private ArrayList<String> loadedUsers = new ArrayList<String>();
  private ArrayList<Integer> loadedUsersAge = new ArrayList<Integer>();

  private ArrayList<LifePolicy> lifePolicies = new ArrayList<LifePolicy>();
  private ArrayList<CarPolicy> carPolicies = new ArrayList<CarPolicy>();
  private ArrayList<HomePolicy> homePolicies = new ArrayList<HomePolicy>();

  private ArrayList<String> lifeUsers = new ArrayList<String>();
  private ArrayList<String> policyUsers = new ArrayList<String>();
  private ArrayList<String> policyOrder = new ArrayList<String>();

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
    ArrayList<Integer> numPolicies = new ArrayList<Integer>();
    int num = Usernames.size();
    int loadnum = loadedUsers.size();
    int rank = 1;

    // Getting the number of policies for each user
    for (int i = 0; i < num; i++) {
      int counter = 0;
      if (policyUsers.contains(Usernames.get(i)) == true) {
        counter = counter + Collections.frequency(policyUsers, Usernames.get(i));
      } else {
      }

      numPolicies.add(counter);
    }
    // Printing
    for (int i = 0; i < num; i++)
      if (((loadnum == 1) && (loadedUsers.get(0)).equals(Usernames.get(i)))) {
        if (numPolicies.get(i) == 1) {
          int Discount = 0;
          for (LifePolicy life : lifePolicies) {
            if (life.getUser().equals(Usernames.get(i))) {
              Discount = Discount + life.getBasePremium();
            }
          }

          for (CarPolicy car : carPolicies) {
            if (car.getUser().equals(Usernames.get(i))) {
              Discount = Discount + car.getBasePremium();
            }
          }

          for (HomePolicy home : homePolicies) {
            if (home.getUser().equals(Usernames.get(i))) {
              Discount = Discount + home.getBasePremium();
            }
          }

          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              Integer.toString(rank),
              Usernames.get(i),
              Integer.toString(Ages.get(i)),
              Integer.toString(numPolicies.get(i)),
              "y",
              Integer.toString(Discount));

          for (int k = 0; i < lifePolicies.size(); i++) {
            if (lifePolicies.get(k).getUser().equals(Usernames.get(i))) {
              MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                  Integer.toString(lifePolicies.get(k).getSumInsured()),
                  Integer.toString(lifePolicies.get(k).getBasePremium()),
                  Integer.toString(lifePolicies.get(k).getBasePremium()));
            }
          }

        } else {

        }
      } else {
        if (numPolicies.get(i) == 1) {

        } else {

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

  public String loadedUser() {
    if (loadedUsersAge.size() == 1) {
      return loadedUsers.get(0);
    } else {
      return "";
    }
  }

  public int loadAge() {
    if (loadedUsersAge.size() == 1) {
      return loadedUsersAge.get(0);
    } else {
      return 0;
    }
  }

  public void storePolicy(PolicyType type, String[] options) {
    String user = loadedUser();
    int age = loadAge();
    if (age != 0) {
      if (type == PolicyType.CAR) {
        CarPolicy car = new CarPolicy(options, user, age);
        carPolicies.add(car);
        policyUsers.add(user);
        policyOrder.add("car");
      } else if (type == PolicyType.HOME) {
        HomePolicy home = new HomePolicy(options, user, age);
        homePolicies.add(home);
        policyUsers.add(user);
        policyOrder.add("home");
      } else if (type == PolicyType.LIFE && age < 100 && lifeUsers.contains(user) == false) {
        LifePolicy life = new LifePolicy(options, user, age);
        lifePolicies.add(life);
        lifeUsers.add(user);
        policyUsers.add(user);
        policyOrder.add("life");
      } else if (type == PolicyType.LIFE && age > 100) {
        MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(user);
      } else if (type == PolicyType.LIFE && lifeUsers.contains(user) == true) {
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(user);
      }
    } else {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
    }
  }
}
