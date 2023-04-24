package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import nz.ac.auckland.se281.Main.PolicyType;

public class Profiles {
  // Crecating two array lists for userNames and the userAges to be stored in
  private ArrayList<String> userNames = new ArrayList<String>();
  private ArrayList<Integer> userAges = new ArrayList<Integer>();
  // Creating array lists to store info of the user that is loaded in the database
  private ArrayList<String> loadedUsers = new ArrayList<String>();
  private ArrayList<Integer> loadedUsersAge = new ArrayList<Integer>();
  // Creating array lists for storing policy users,policy details and policies
  private ArrayList<LifePolicy> lifePolicies = new ArrayList<LifePolicy>();
  private ArrayList<CarPolicy> carPolicies = new ArrayList<CarPolicy>();
  private ArrayList<HomePolicy> homePolicies = new ArrayList<HomePolicy>();
  private ArrayList<Policies> allPolicies = new ArrayList<Policies>();
  private ArrayList<String> carUsers = new ArrayList<String>();
  private ArrayList<String> homeUsers = new ArrayList<String>();
  private ArrayList<String> lifeUsers = new ArrayList<String>();

  // Creating a method to store the userNames and userAges in the array lists
  public void addUserAges(String userName, int age) {
    // Using a for loop to check if the inputted username is unique
    int truth = 1;
    if (loadedUsers.size() == 1) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(loadedUsers.get(0));
      return;
    }
    for (int i = 0; i < userNames.size(); i++) {
      // If it is not unique, we change the truth value to 0, and it will not be added
      if (userName.equals(userNames.get(i)) == true) {
        truth = 0;
      }
    }
    // Checking the truth value, if 1, we add the userNames and ages. if 0, we print
    // the error
    // message.
    if (truth == 1) {
      userNames.add(userName);
      userAges.add(age);
      MessageCli.PROFILE_CREATED.printMessage(userName, Integer.toString(age));
    } else {
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
    }
  }

  // Creating a new method to print the profiles in the database along with the
  // user details, number
  // of policies, and policies of details
  public void printProfiles() {
    // Creating an array list to store the number of policies for each user
    ArrayList<Integer> numPolicies = new ArrayList<Integer>();
    // Initialising variables for easy access
    int num = userNames.size();
    int loadnum = loadedUsers.size();
    // Creating a variable to store the rank of the user for printing
    int rank = 1;

    // Getting the number of policies for each user
    for (int i = 0; i < num; i++) {
      // Counts the number of policies for each user
      int counter = 0;
      // 3 if statements to check if the user has a policy of each type and if they
      // do, add the
      // number of policies to the counter
      if (carUsers.contains(userNames.get(i)) && carUsers.size() != 0) {
        counter = counter + Collections.frequency(carUsers, userNames.get(i));
      }
      if (lifeUsers.contains(userNames.get(i)) && lifeUsers.size() != 0) {
        counter = counter + Collections.frequency(lifeUsers, userNames.get(i));
      }
      if (homeUsers.contains(userNames.get(i)) && homeUsers.size() != 0) {
        counter = counter + Collections.frequency(homeUsers, userNames.get(i));
      }
      if (counter == 0) {
        counter = counter;
      }
      // Add the number of policies to the array list which stores the number of
      // policies for each
      // user in the corresponding index to the user array list
      numPolicies.add(counter);
    }
    // Printing the data base
    for (int i = 0; i < num; i++) {
      // If there is a user loaded, we check if the user is the same as the user in
      // the database, if
      // it is, we print the user details and policies
      if (((loadnum == 1) && (loadedUsers.get(0)).equals(userNames.get(i)))) {
        if (numPolicies.get(i) == 1) {
          // If the user only has one policy
          if (numPolicies.get(i) == 1) {

            int totalPay = 0;
            // Running a for-each loop to calculate the base premium for each user and add
            // it to the
            // totalPay variable which stores the total amount needed to be paid, and will
            // be
            // printed in the details
            for (Policies userPolicy : allPolicies) {
              if (userPolicy.getUserName().equals(userNames.get(i))) {
                // Creating if conditions for each instance of the policy to check if the user
                // has a
                // policy of that type, if they do, we add the base premium to the totalPay
                // variable
                if (userPolicy instanceof LifePolicy) {
                  LifePolicy lifePolicy = (LifePolicy) userPolicy;
                  totalPay += lifePolicy.getBasePremium();
                } else if (userPolicy instanceof CarPolicy) {
                  CarPolicy carPolicy = (CarPolicy) userPolicy;
                  totalPay += carPolicy.getBasePremium();
                } else if (userPolicy instanceof HomePolicy) {
                  HomePolicy homePolicy = (HomePolicy) userPolicy;
                  totalPay += homePolicy.getBasePremium();
                }
              }
            }
            // Printing the user details, number of policies and the total amount needed to
            // be paid
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(rank),
                userNames.get(i),
                Integer.toString(userAges.get(i)),
                Integer.toString(numPolicies.get(i)),
                "y",
                Integer.toString(totalPay));
            // Using a for-each loop to print the details of each policy for the user
            for (Policies printPolicy : allPolicies) {
              // Checking if the user has a Policy, if they do, we print the details
              if (printPolicy.getUserName().equals(userNames.get(i))) {
                // Creating an instance of lifePolicy so that we can print the details of the
                // life
                // policy out for the user
                if (printPolicy instanceof LifePolicy) {
                  LifePolicy lifePolicy = (LifePolicy) printPolicy;
                  MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                      Integer.toString(lifePolicy.getSumInsured()),
                      Integer.toString(lifePolicy.getBasePremium()),
                      Integer.toString(lifePolicy.getBasePremium()));
                  // Creating an instance of carPolicy so that we can print the details of the car
                  // policy out for the user
                } else if (printPolicy instanceof CarPolicy) {
                  CarPolicy carPolicy = (CarPolicy) printPolicy;
                  MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                      carPolicy.getMakeModel(),
                      Integer.toString(carPolicy.getSumInsured()),
                      Integer.toString(carPolicy.getBasePremium()),
                      Integer.toString(carPolicy.getBasePremium()));
                  // Creating an instance of homePolicy so that we can print the details of the
                  // home
                  // policy out for the user
                } else if (printPolicy instanceof HomePolicy) {
                  HomePolicy homePolicy = (HomePolicy) printPolicy;
                  MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                      homePolicy.getAddress(),
                      Integer.toString(homePolicy.getSumInsured()),
                      Integer.toString(homePolicy.getBasePremium()),
                      Integer.toString(homePolicy.getBasePremium()));
                }
              }
            }
          }

        } else {
          // If the user has more than one policy
          if (numPolicies.get(i) != 0) {
            int totalPay = 0;
            // Creating a for-each loop to calculate the life base premium for each user and
            // add it
            // to the totalPay. If the user has more than 3 policies, we apply a 20%
            // totalPay, if
            // they have less than 3 policies, we apply a 10% totalPay
            for (LifePolicy life : lifePolicies) {
              if (life.getUser().equals(userNames.get(i))) {
                double totalPaylife;
                if (numPolicies.get(i) < 3) {
                  totalPaylife = life.getBasePremium() * 0.9;
                  totalPay = totalPay + (int) totalPaylife;
                } else {
                  totalPaylife = life.getBasePremium() * 0.8;
                  totalPay = totalPay + (int) totalPaylife;
                }
              }
            }
            // Creating a for-each loop to calculate the car base premium for each user and
            // add it
            // to the totalPay. If the user has more than 3 policies, we apply a 20%
            // totalPay, if
            // they have less than 3 policies, we apply a 10% totalPay
            for (CarPolicy car : carPolicies) {
              if (car.getUser().equals(userNames.get(i))) {
                double totalPaycar;
                if (numPolicies.get(i) < 3) {
                  totalPaycar = car.getBasePremium() * 0.9;
                  totalPay = totalPay + (int) totalPaycar;
                } else {
                  totalPaycar = car.getBasePremium() * 0.8;
                  totalPay = totalPay + (int) totalPaycar;
                }
              }
            }
            // Creating a for-each loop to calculate the home base premium for each user and
            // add it
            // to the totalPay. If the user has more than 3 policies, we apply a 20%
            // totalPay, if
            // they have less than 3 policies, we apply a 10% totalPay
            for (HomePolicy home : homePolicies) {
              if (home.getUser().equals(userNames.get(i))) {
                double totalPayhome;
                if (numPolicies.get(i) < 3) {
                  totalPayhome = home.getBasePremium() * 0.9;
                  totalPay = totalPay + (int) totalPayhome;
                } else {
                  totalPayhome = home.getBasePremium() * 0.8;
                  totalPay = totalPay + (int) totalPayhome;
                }
              }
            }
            // Printing out the user detials, number of policies and the total amount needed
            // to be
            // paid
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(rank),
                userNames.get(i),
                Integer.toString(userAges.get(i)),
                Integer.toString(numPolicies.get(i)),
                "ies",
                Integer.toString(totalPay));
            // Creating a for-each loop to print the details of each policy for the user
            for (Policies printPolicy : allPolicies) {
              // Checking if the user has a Policy, if they do, we print the details
              if (printPolicy.getUserName().equals(userNames.get(i))) {
                // Creating an instance of lifePolicy so that we can print the details of the
                // life
                // policy out for the user
                if (printPolicy instanceof LifePolicy) {
                  LifePolicy lifePolicy = (LifePolicy) printPolicy;
                  // If the user has 3 or more policies, we apply a 20% totalPay, if they have
                  // less
                  // than 3 policies, we apply a 10% totalPay
                  if (numPolicies.get(i) < 3) {
                    int totalPaylife = (int) (lifePolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                        Integer.toString(lifePolicy.getSumInsured()),
                        Integer.toString(lifePolicy.getBasePremium()),
                        Integer.toString(totalPaylife));
                  } else {
                    int totalPaylife = (int) (lifePolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                        Integer.toString(lifePolicy.getSumInsured()),
                        Integer.toString(lifePolicy.getBasePremium()),
                        Integer.toString(totalPaylife));
                  }
                  // Creating an instance of carPolicy so that we can print the details of the car
                } else if (printPolicy instanceof CarPolicy) {
                  CarPolicy carPolicy = (CarPolicy) printPolicy;
                  // If the user has 3 or more policies, we apply a 20% totalPay, if they have
                  // less
                  // than 3 policies, we apply a 10% totalPay
                  if (numPolicies.get(i) < 3) {
                    int totalPaycar = (int) (carPolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                        carPolicy.getMakeModel(),
                        Integer.toString(carPolicy.getSumInsured()),
                        Integer.toString(carPolicy.getBasePremium()),
                        Integer.toString(totalPaycar));
                  } else {
                    int totalPaycar = (int) (carPolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                        carPolicy.getMakeModel(),
                        Integer.toString(carPolicy.getSumInsured()),
                        Integer.toString(carPolicy.getBasePremium()),
                        Integer.toString(totalPaycar));
                  }
                  // Creating an instance of homePolicy so that we can print the details of the
                  // home
                  // policy out for the user
                } else if (printPolicy instanceof HomePolicy) {
                  HomePolicy homePolicy = (HomePolicy) printPolicy;
                  // If the user has 3 or more policies, we apply a 20% totalPay, if they have
                  // less
                  // than 3 policies, we apply a 10% totalPay
                  if (numPolicies.get(i) < 3) {
                    int totalPayhome = (int) (homePolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                        homePolicy.getAddress(),
                        Integer.toString(homePolicy.getSumInsured()),
                        Integer.toString(homePolicy.getBasePremium()),
                        Integer.toString(totalPayhome));
                  } else {
                    int totalPayhome = (int) (homePolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                        homePolicy.getAddress(),
                        Integer.toString(homePolicy.getSumInsured()),
                        Integer.toString(homePolicy.getBasePremium()),
                        Integer.toString(totalPayhome));
                  }
                }
              }
            }

          } else {
            // If the user has no policies, we print out the user details along with a zero
            // tota;
            // amount needed to be paid
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(rank),
                userNames.get(i),
                Integer.toString(userAges.get(i)),
                Integer.toString(numPolicies.get(i)),
                "ies",
                "0");
          }
        }
        // This is for when the user is not loaded in to the system
      } else {
        // If the user only has one policy
        if (numPolicies.get(i) == 1) {
          int totalPay = 0;
          // Using for-each loops to get the total premium for the user from each policy
          for (LifePolicy life : lifePolicies) {
            if (life.getUser().equals(userNames.get(i))) {
              totalPay = totalPay + life.getBasePremium();
            }
          }

          for (CarPolicy car : carPolicies) {
            if (car.getUser().equals(userNames.get(i))) {
              totalPay = totalPay + car.getBasePremium();
            }
          }

          for (HomePolicy home : homePolicies) {
            if (home.getUser().equals(userNames.get(i))) {
              totalPay = totalPay + home.getBasePremium();
            }
          }
          // Printing the user details along with the total premium needed to be paid
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              Integer.toString(rank),
              userNames.get(i),
              Integer.toString(userAges.get(i)),
              Integer.toString(numPolicies.get(i)),
              "y",
              Integer.toString(totalPay));
          // Using for-each loop to print the policy details out for the user
          for (Policies printPolicy : allPolicies) {
            // If the user matches in the policy array, we print out the policy details for
            // the user
            if (printPolicy.getUserName().equals(userNames.get(i))) {
              // Creating an instance of lifePolicy so that we can print the details of the
              // life
              // policy out for the user
              if (printPolicy instanceof LifePolicy) {
                LifePolicy lifePolicy = (LifePolicy) printPolicy;
                MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                    Integer.toString(lifePolicy.getSumInsured()),
                    Integer.toString(lifePolicy.getBasePremium()),
                    Integer.toString(lifePolicy.getBasePremium()));
                // Creating an instance of carPolicy so that we can print the details of the car
                // policy out for the user
              } else if (printPolicy instanceof CarPolicy) {
                CarPolicy carPolicy = (CarPolicy) printPolicy;
                MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                    carPolicy.getMakeModel(),
                    Integer.toString(carPolicy.getSumInsured()),
                    Integer.toString(carPolicy.getBasePremium()),
                    Integer.toString(carPolicy.getBasePremium()));
                // Creating an instance of homePolicy so that we can print the details of the
                // home
                // policy out for the user
              } else if (printPolicy instanceof HomePolicy) {
                HomePolicy homePolicy = (HomePolicy) printPolicy;
                MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                    homePolicy.getAddress(),
                    Integer.toString(homePolicy.getSumInsured()),
                    Integer.toString(homePolicy.getBasePremium()),
                    Integer.toString(homePolicy.getBasePremium()));
              }
            }
          }

        } else {
          // If the user has more than one policy
          if (numPolicies.get(i) != 0) {
            int totalPay = 0;
            // Using for-each loops to get the total premium for the user from each policy
            // and
            // applying totalPays accordingly
            for (LifePolicy life : lifePolicies) {
              if (life.getUser().equals(userNames.get(i))) {
                double totalPaylife;
                if (numPolicies.get(i) < 3) {
                  totalPaylife = life.getBasePremium() * 0.9;
                  totalPay = totalPay + (int) totalPaylife;
                } else {
                  totalPaylife = life.getBasePremium() * 0.8;
                  totalPay = totalPay + (int) totalPaylife;
                }
              }
            }
            for (CarPolicy car : carPolicies) {
              if (car.getUser().equals(userNames.get(i))) {
                double totalPaycar;
                if (numPolicies.get(i) < 3) {
                  totalPaycar = car.getBasePremium() * 0.9;
                  totalPay = totalPay + (int) totalPaycar;
                } else {
                  totalPaycar = car.getBasePremium() * 0.8;
                  totalPay = totalPay + (int) totalPaycar;
                }
              }
            }

            for (HomePolicy home : homePolicies) {
              if (home.getUser().equals(userNames.get(i))) {
                double totalPayhome;
                if (numPolicies.get(i) < 3) {
                  totalPayhome = home.getBasePremium() * 0.9;
                  totalPay = totalPay + (int) totalPayhome;
                } else {
                  totalPayhome = home.getBasePremium() * 0.8;
                  totalPay = totalPay + (int) totalPayhome;
                }
              }
            }
            // Printing the user details, number of policies along with the total premium
            // needed to
            // be paid
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                Integer.toString(rank),
                userNames.get(i),
                Integer.toString(userAges.get(i)),
                Integer.toString(numPolicies.get(i)),
                "ies",
                Integer.toString(totalPay));
            // Using for-each loops to print out the policy details of each type for the
            // user
            // Checking the number of policies the user has and applying totalPays
            // accordingly
            for (Policies printPolicy : allPolicies) {
              if (printPolicy.getUserName().equals(userNames.get(i))) {
                if (printPolicy instanceof LifePolicy) {
                  LifePolicy lifePolicy = (LifePolicy) printPolicy;
                  if (numPolicies.get(i) < 3) {
                    int totalPaylife = (int) (lifePolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                        Integer.toString(lifePolicy.getSumInsured()),
                        Integer.toString(lifePolicy.getBasePremium()),
                        Integer.toString(totalPaylife));
                  } else {
                    int totalPaylife = (int) (lifePolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                        Integer.toString(lifePolicy.getSumInsured()),
                        Integer.toString(lifePolicy.getBasePremium()),
                        Integer.toString(totalPaylife));
                  }
                } else if (printPolicy instanceof CarPolicy) {
                  CarPolicy carPolicy = (CarPolicy) printPolicy;
                  if (numPolicies.get(i) < 3) {
                    int totalPaycar = (int) (carPolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                        carPolicy.getMakeModel(),
                        Integer.toString(carPolicy.getSumInsured()),
                        Integer.toString(carPolicy.getBasePremium()),
                        Integer.toString(totalPaycar));
                  } else {
                    int totalPaycar = (int) (carPolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                        carPolicy.getMakeModel(),
                        Integer.toString(carPolicy.getSumInsured()),
                        Integer.toString(carPolicy.getBasePremium()),
                        Integer.toString(totalPaycar));
                  }
                } else if (printPolicy instanceof HomePolicy) {
                  HomePolicy homePolicy = (HomePolicy) printPolicy;
                  if (numPolicies.get(i) < 3) {
                    int totalPayhome = (int) (homePolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                        homePolicy.getAddress(),
                        Integer.toString(homePolicy.getSumInsured()),
                        Integer.toString(homePolicy.getBasePremium()),
                        Integer.toString(totalPayhome));
                  } else {
                    int totalPayhome = (int) (homePolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                        homePolicy.getAddress(),
                        Integer.toString(homePolicy.getSumInsured()),
                        Integer.toString(homePolicy.getBasePremium()),
                        Integer.toString(totalPayhome));
                  }
                }
              }
            }
            // If the user has zero profiles then the user details are printed out along
            // with the
            // total premium of zero
          } else {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                Integer.toString(rank),
                userNames.get(i),
                Integer.toString(userAges.get(i)),
                Integer.toString(numPolicies.get(i)),
                "ies",
                "0");
          }
        }
      }
      rank = rank + 1;
    }
  }

  // Created a method to count the number of profiles in the database
  public int findNum() {
    int number = userNames.size();

    return number;
  }

  // Created a method to check if the inputted age does not have a decimal point
  // or character.
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

  // Created a method to load the profile of the user in the system
  public void profileLoad(String userName) {
    int truth = 0;
    int profileIndex = 0;
    // For loop to check if the user exists in the database
    for (int i = 0; i < userNames.size(); i++) {
      // If the user exists, the truth value is changed to 1 and the index of the user
      // is stored
      if (userName.equals(userNames.get(i)) == true) {
        truth = 1;
        profileIndex = i;
      }
    }
    // If the user exists, the user is loaded into the system
    if (truth == 1 && loadedUsers.size() == 0) {
      // User is added on to the array list containing the loaded users along with
      // their age in the
      // loaded ages array list
      loadedUsers.add(userName);
      loadedUsersAge.add(userAges.get(profileIndex));
      // Printing message to show that profile is loaded
      MessageCli.PROFILE_LOADED.printMessage(userName);
      // If the user does exist, but there is already a profile loaded
    } else if (truth == 1 && loadedUsers.size() == 1) {
      // First we unload the loaded profile
      profileUnload();
      // Then we load the new profile and add user and age to the array lists
      loadedUsers.add(userName);
      loadedUsersAge.add(userAges.get(profileIndex));
      // Printing message to show that profile is loaded
      MessageCli.PROFILE_LOADED.printMessage(userName);
      // If the user name does not exist in the database
    } else {
      // Print message saying no profile is in the database to load
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
    }
  }

  // Unloading profiles
  public void profileUnload() {
    // Checking if there is a profile loaded
    if (loadedUsers.size() == 0) {
      // If there isn't print the message saying no profile is loaded
      MessageCli.NO_PROFILE_LOADED.printMessage();
    } else {
      // If there is a profile loaded, unload the profile and remove the user and age
      // from the array
      String userUnload = loadedUsers.get(0);
      loadedUsers.remove(0);
      loadedUsersAge.remove(0);
      // Print message to show that the profile is unloaded
      MessageCli.PROFILE_UNLOADED.printMessage(userUnload);
    }
  }

  // Delete profiles
  public void profileDelete(String userName) {

    int loadnum = loadedUsers.size();
    // Checking to see if the user that we are wanting to delete is in the loaded
    // users array, as we
    // can delete a loaded profile
    if (loadnum == 1 && (userName.equals(loadedUsers.get(0)))) {
      // Print message saying that we can't delete a loaded profile
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
      return;
    }
    // Checking to see if the user is in the database, and finding its index in the
    // user array list
    int profileCheck = 0;
    int profileIndex = 0;
    for (int i = 0; i < userNames.size(); i++) {
      if (userName.equals(userNames.get(i)) == true) {
        profileCheck = 1;
        profileIndex = i;
      }
    }
    // If the user is in the database, delete the profile
    if (profileCheck == 1) {
      // Delete the user and their age from the array lists
      userNames.remove(profileIndex);
      userAges.remove(profileIndex);
      // delete all policies associated with the profile
      for (int i = 0; i < allPolicies.size(); i++) {
        if (allPolicies.get(i).getUserName().equals(userName)) {
          allPolicies.remove(i);
          i--;
        }
      }
      // delete all car policies associated with the profile
      for (int i = 0; i < carPolicies.size(); i++) {
        if (carPolicies.get(i).getUserName().equals(userName)) {
          carPolicies.remove(i);
          i--;
        }
      }
      // delete all home policies associated with the profile
      for (int i = 0; i < homePolicies.size(); i++) {
        if (homePolicies.get(i).getUserName().equals(userName)) {
          homePolicies.remove(i);
          i--;
        }
      }
      // delete all life policies associated with the profile
      for (int i = 0; i < lifePolicies.size(); i++) {
        if (lifePolicies.get(i).getUserName().equals(userName)) {
          lifePolicies.remove(i);
          i--;
        }
      }
      // delete username from carUsers
      for (int i = 0; i < carUsers.size(); i++) {
        if (carUsers.get(i).equals(userName)) {
          carUsers.remove(i);
          i--;
        }
      }
      // delete username from lifeUsers
      for (int i = 0; i < lifeUsers.size(); i++) {
        if (lifeUsers.get(i).equals(userName)) {
          lifeUsers.remove(i);
          i--;
        }
      }
      // delete username from homeUsers
      for (int i = 0; i < homeUsers.size(); i++) {
        if (homeUsers.get(i).equals(userName)) {
          homeUsers.remove(i);
          i--;
        }
      }
      // Print message saying profile is deleted
      MessageCli.PROFILE_DELETED.printMessage(userName);
    } else {
      // If the user is not in the database, print message saying no profile is in the
      // database to
      // delete
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
    }
  }

  // Finding the username of the loaded user
  public String loadedUser() {
    // finding the username of the loaded user using an if condition and returning
    // the username
    if (loadedUsersAge.size() == 1) {
      return loadedUsers.get(0);
    } else {
      return "";
    }
  }

  // Finding the age of the loaded user
  public int loadAge() {
    // Finding the age of the loaded user using an if condition and returning the
    // age
    if (loadedUsersAge.size() == 1) {
      return loadedUsersAge.get(0);
    } else {
      return 0;
    }
  }

  // Creating and storing policies for the users
  public void storePolicy(PolicyType type, String[] options) {
    // Initialising the username and age of the loaded user
    String user = loadedUser();
    int age = loadAge();
    // If the user age is not zero, create a new policy and add it to the array list
    if (age != 0) {
      // Checking the type of policy wanting to be created and storing the policy
      // details obtained
      // from the Abstract and sub classes
      // Storing it in array lists made for all policies, for each policy type, and
      // storing the
      // users in the respective policyuser array
      if (type == PolicyType.CAR) {
        CarPolicy car = new CarPolicy(options, user, age);
        carPolicies.add(car);
        allPolicies.add(car);
        carUsers.add(user);
      } else if (type == PolicyType.HOME) {
        HomePolicy home = new HomePolicy(options, user, age);
        homePolicies.add(home);
        allPolicies.add(home);
        homeUsers.add(user);
      } else if (type == PolicyType.LIFE && age < 100 && lifeUsers.contains(user) == false) {
        LifePolicy life = new LifePolicy(options, user, age);
        lifePolicies.add(life);
        allPolicies.add(life);
        lifeUsers.add(user);
        // Checking to see if a user is wanting to create a life array and to check
        // their age is
        // below 100
      } else if (type == PolicyType.LIFE && age > 100) {
        // If they age is above a 100, print message saying age is over limit
        MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(user);
      } else if (type == PolicyType.LIFE && lifeUsers.contains(user) == true) {
        // If the user already has a life policy, print message saying they already have
        // a life
        // policy
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(user);
      }
    } else {
      // If the user is not loaded, print message saying no profile is loaded and the
      // policy cannot
      // be created
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
    }
  }
}
