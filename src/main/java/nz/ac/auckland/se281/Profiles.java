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
  private ArrayList<Policies> allPolicies = new ArrayList<Policies>();
  private ArrayList<String> carUsers = new ArrayList<String>();
  private ArrayList<String> homeUsers = new ArrayList<String>();
  private ArrayList<String> lifeUsers = new ArrayList<String>();

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
  // Creating a new method to print the profiles in the database along with the user details, number
  // of policies, and policies of details
  public void printProfiles() {
    // Creating an array list to store the number of policies for each user
    ArrayList<Integer> numPolicies = new ArrayList<Integer>();
    // Initialising variables for easy access
    int num = Usernames.size();
    int loadnum = loadedUsers.size();
    // Creating a variable to store the rank of the user for printing
    int rank = 1;

    // Getting the number of policies for each user
    for (int i = 0; i < num; i++) {
      // Counts the number of policies for each user
      int counter = 0;
      // 3 if statements to check if the user has a policy of each type and if they do, add the
      // number of policies to the counter
      if (carUsers.contains(Usernames.get(i)) && carUsers.size() != 0) {
        counter = counter + Collections.frequency(carUsers, Usernames.get(i));
      }
      if (lifeUsers.contains(Usernames.get(i)) && lifeUsers.size() != 0) {
        counter = counter + Collections.frequency(lifeUsers, Usernames.get(i));
      }
      if (homeUsers.contains(Usernames.get(i)) && homeUsers.size() != 0) {
        counter = counter + Collections.frequency(homeUsers, Usernames.get(i));
      }
      if (counter == 0) {
        counter = counter;
      }
      // Add the number of policies to the array list which stores the number of policies for each
      // user in the corresponding index to the user array list
      numPolicies.add(counter);
    }
    // Printing the data base
    for (int i = 0; i < num; i++) {
      // If there is a user loaded, we check if the user is the same as the user in the database, if
      // it is, we print the user details and policies
      if (((loadnum == 1) && (loadedUsers.get(0)).equals(Usernames.get(i)))) {
        if (numPolicies.get(i) == 1) {
          // If the user only has one policy
          if (numPolicies.get(i) == 1) {

            int Discount = 0;
            // Running a for-each loop to calculate the base premium for each user and add it to the
            // discount variable which stores the total amount needed to be paid, and will be
            // printed in the details
            for (Policies userPolicy : allPolicies) {
              if (userPolicy.getUserName().equals(Usernames.get(i))) {
                // Creating if conditions for each instance of the policy to check if the user has a
                // policy of that type, if they do, we add the base premium to the discount variable
                if (userPolicy instanceof LifePolicy) {
                  LifePolicy lifePolicy = (LifePolicy) userPolicy;
                  Discount += lifePolicy.getBasePremium();
                } else if (userPolicy instanceof CarPolicy) {
                  CarPolicy carPolicy = (CarPolicy) userPolicy;
                  Discount += carPolicy.getBasePremium();
                } else if (userPolicy instanceof HomePolicy) {
                  HomePolicy homePolicy = (HomePolicy) userPolicy;
                  Discount += homePolicy.getBasePremium();
                }
              }
            }
            // Printing the user details, number of policies and the total amount needed to be paid
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(rank),
                Usernames.get(i),
                Integer.toString(Ages.get(i)),
                Integer.toString(numPolicies.get(i)),
                "y",
                Integer.toString(Discount));
            // Using a for-each loop to print the details of each policy for the user
            for (Policies printPolicy : allPolicies) {
              // Checking if the user has a Policy, if they do, we print the details
              if (printPolicy.getUserName().equals(Usernames.get(i))) {
                // Creating an instance of lifePolicy so that we can print the details of the life
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
                  // Creating an instance of homePolicy so that we can print the details of the home
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
            int Discount = 0;
            // Creating a for-each loop to calculate the life base premium for each user and add it
            // to the discount. If the user has more than 3 policies, we apply a 20% discount, if
            // they have less than 3 policies, we apply a 10% discount
            for (LifePolicy life : lifePolicies) {
              if (life.getUser().equals(Usernames.get(i))) {
                double discountlife;
                if (numPolicies.get(i) < 3) {
                  discountlife = life.getBasePremium() * 0.9;
                  Discount = Discount + (int) discountlife;
                } else {
                  discountlife = life.getBasePremium() * 0.8;
                  Discount = Discount + (int) discountlife;
                }
              }
            }
            // Creating a for-each loop to calculate the car base premium for each user and add it
            // to the discount. If the user has more than 3 policies, we apply a 20% discount, if
            // they have less than 3 policies, we apply a 10% discount
            for (CarPolicy car : carPolicies) {
              if (car.getUser().equals(Usernames.get(i))) {
                double discountcar;
                if (numPolicies.get(i) < 3) {
                  discountcar = car.getBasePremium() * 0.9;
                  Discount = Discount + (int) discountcar;
                } else {
                  discountcar = car.getBasePremium() * 0.8;
                  Discount = Discount + (int) discountcar;
                }
              }
            }
            // Creating a for-each loop to calculate the home base premium for each user and add it
            // to the discount. If the user has more than 3 policies, we apply a 20% discount, if
            // they have less than 3 policies, we apply a 10% discount
            for (HomePolicy home : homePolicies) {
              if (home.getUser().equals(Usernames.get(i))) {
                double discounthome;
                if (numPolicies.get(i) < 3) {
                  discounthome = home.getBasePremium() * 0.9;
                  Discount = Discount + (int) discounthome;
                } else {
                  discounthome = home.getBasePremium() * 0.8;
                  Discount = Discount + (int) discounthome;
                }
              }
            }
            // Printing out the user detials, number of policies and the total amount needed to be
            // paid
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(rank),
                Usernames.get(i),
                Integer.toString(Ages.get(i)),
                Integer.toString(numPolicies.get(i)),
                "ies",
                Integer.toString(Discount));
            // Creating a for-each loop to print the details of each policy for the user
            for (Policies printPolicy : allPolicies) {
              // Checking if the user has a Policy, if they do, we print the details
              if (printPolicy.getUserName().equals(Usernames.get(i))) {
                // Creating an instance of lifePolicy so that we can print the details of the life
                // policy out for the user
                if (printPolicy instanceof LifePolicy) {
                  LifePolicy lifePolicy = (LifePolicy) printPolicy;
                  // If the user has 3 or more policies, we apply a 20% discount, if they have less
                  // than 3 policies, we apply a 10% discount
                  if (numPolicies.get(i) < 3) {
                    int discountlife = (int) (lifePolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                        Integer.toString(lifePolicy.getSumInsured()),
                        Integer.toString(lifePolicy.getBasePremium()),
                        Integer.toString(discountlife));
                  } else {
                    int discountlife = (int) (lifePolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                        Integer.toString(lifePolicy.getSumInsured()),
                        Integer.toString(lifePolicy.getBasePremium()),
                        Integer.toString(discountlife));
                  }
                  // Creating an instance of carPolicy so that we can print the details of the car
                } else if (printPolicy instanceof CarPolicy) {
                  CarPolicy carPolicy = (CarPolicy) printPolicy;
                  // If the user has 3 or more policies, we apply a 20% discount, if they have less
                  // than 3 policies, we apply a 10% discount
                  if (numPolicies.get(i) < 3) {
                    int discountcar = (int) (carPolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                        carPolicy.getMakeModel(),
                        Integer.toString(carPolicy.getSumInsured()),
                        Integer.toString(carPolicy.getBasePremium()),
                        Integer.toString(discountcar));
                  } else {
                    int discountcar = (int) (carPolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                        carPolicy.getMakeModel(),
                        Integer.toString(carPolicy.getSumInsured()),
                        Integer.toString(carPolicy.getBasePremium()),
                        Integer.toString(discountcar));
                  }
                  // Creating an instance of homePolicy so that we can print the details of the home
                  // policy out for the user
                } else if (printPolicy instanceof HomePolicy) {
                  HomePolicy homePolicy = (HomePolicy) printPolicy;
                  // If the user has 3 or more policies, we apply a 20% discount, if they have less
                  // than 3 policies, we apply a 10% discount
                  if (numPolicies.get(i) < 3) {
                    int discounthome = (int) (homePolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                        homePolicy.getAddress(),
                        Integer.toString(homePolicy.getSumInsured()),
                        Integer.toString(homePolicy.getBasePremium()),
                        Integer.toString(discounthome));
                  } else {
                    int discounthome = (int) (homePolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                        homePolicy.getAddress(),
                        Integer.toString(homePolicy.getSumInsured()),
                        Integer.toString(homePolicy.getBasePremium()),
                        Integer.toString(discounthome));
                  }
                }
              }
            }

          } else {
            // If the user has no policies, we print out the user details along with a zero tota;
            // amount needed to be paid
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "*** ",
                Integer.toString(rank),
                Usernames.get(i),
                Integer.toString(Ages.get(i)),
                Integer.toString(numPolicies.get(i)),
                "ies",
                "0");
          }
        }
        // This is for when the user is not loaded in to the system
      } else {
        // If the user only has one policy
        if (numPolicies.get(i) == 1) {
          int Discount = 0;
          // Using for-each loops to get the total premium for the user from each policy
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
          // Printing the user details along with the total premium needed to be paid
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              Integer.toString(rank),
              Usernames.get(i),
              Integer.toString(Ages.get(i)),
              Integer.toString(numPolicies.get(i)),
              "y",
              Integer.toString(Discount));
          // Using for-each loop to print the policy details out for the user
          for (Policies printPolicy : allPolicies) {
            // If the user matches in the policy array, we print out the policy details for the user
            if (printPolicy.getUserName().equals(Usernames.get(i))) {
              // Creating an instance of lifePolicy so that we can print the details of the life
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
                // Creating an instance of homePolicy so that we can print the details of the home
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
            int Discount = 0;
            // Using for-each loops to get the total premium for the user from each policy and
            // applying discounts accordingly
            for (LifePolicy life : lifePolicies) {
              if (life.getUser().equals(Usernames.get(i))) {
                double discountlife;
                if (numPolicies.get(i) < 3) {
                  discountlife = life.getBasePremium() * 0.9;
                  Discount = Discount + (int) discountlife;
                } else {
                  discountlife = life.getBasePremium() * 0.8;
                  Discount = Discount + (int) discountlife;
                }
              }
            }
            for (CarPolicy car : carPolicies) {
              if (car.getUser().equals(Usernames.get(i))) {
                double discountcar;
                if (numPolicies.get(i) < 3) {
                  discountcar = car.getBasePremium() * 0.9;
                  Discount = Discount + (int) discountcar;
                } else {
                  discountcar = car.getBasePremium() * 0.8;
                  Discount = Discount + (int) discountcar;
                }
              }
            }

            for (HomePolicy home : homePolicies) {
              if (home.getUser().equals(Usernames.get(i))) {
                double discounthome;
                if (numPolicies.get(i) < 3) {
                  discounthome = home.getBasePremium() * 0.9;
                  Discount = Discount + (int) discounthome;
                } else {
                  discounthome = home.getBasePremium() * 0.8;
                  Discount = Discount + (int) discounthome;
                }
              }
            }
            // Printing the user details, number of policies along with the total premium needed to
            // be paid
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                Integer.toString(rank),
                Usernames.get(i),
                Integer.toString(Ages.get(i)),
                Integer.toString(numPolicies.get(i)),
                "ies",
                Integer.toString(Discount));
            // Using for-each loops to print out the policy details of each type for the user
            // Checking the number of policies the user has and applying discounts accordingly
            for (Policies printPolicy : allPolicies) {
              if (printPolicy.getUserName().equals(Usernames.get(i))) {
                if (printPolicy instanceof LifePolicy) {
                  LifePolicy lifePolicy = (LifePolicy) printPolicy;
                  if (numPolicies.get(i) < 3) {
                    int discountlife = (int) (lifePolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                        Integer.toString(lifePolicy.getSumInsured()),
                        Integer.toString(lifePolicy.getBasePremium()),
                        Integer.toString(discountlife));
                  } else {
                    int discountlife = (int) (lifePolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
                        Integer.toString(lifePolicy.getSumInsured()),
                        Integer.toString(lifePolicy.getBasePremium()),
                        Integer.toString(discountlife));
                  }
                } else if (printPolicy instanceof CarPolicy) {
                  CarPolicy carPolicy = (CarPolicy) printPolicy;
                  if (numPolicies.get(i) < 3) {
                    int discountcar = (int) (carPolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                        carPolicy.getMakeModel(),
                        Integer.toString(carPolicy.getSumInsured()),
                        Integer.toString(carPolicy.getBasePremium()),
                        Integer.toString(discountcar));
                  } else {
                    int discountcar = (int) (carPolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
                        carPolicy.getMakeModel(),
                        Integer.toString(carPolicy.getSumInsured()),
                        Integer.toString(carPolicy.getBasePremium()),
                        Integer.toString(discountcar));
                  }
                } else if (printPolicy instanceof HomePolicy) {
                  HomePolicy homePolicy = (HomePolicy) printPolicy;
                  if (numPolicies.get(i) < 3) {
                    int discounthome = (int) (homePolicy.getBasePremium() * 0.9);
                    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                        homePolicy.getAddress(),
                        Integer.toString(homePolicy.getSumInsured()),
                        Integer.toString(homePolicy.getBasePremium()),
                        Integer.toString(discounthome));
                  } else {
                    int discounthome = (int) (homePolicy.getBasePremium() * 0.8);
                    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
                        homePolicy.getAddress(),
                        Integer.toString(homePolicy.getSumInsured()),
                        Integer.toString(homePolicy.getBasePremium()),
                        Integer.toString(discounthome));
                  }
                }
              }
            }
            // If the user has zero profiles then the user details are printed out along with the
            // total premium of zero
          } else {
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
                "",
                Integer.toString(rank),
                Usernames.get(i),
                Integer.toString(Ages.get(i)),
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

      profileUnload();
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
      } else if (type == PolicyType.LIFE && age > 100) {
        MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(user);
      } else if (type == PolicyType.LIFE && lifeUsers.contains(user) == true) {
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(user);
      }
    } else {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
    }

    // for (LifePolicy tempLifePolicy : lifePolicies) {
    //   System.out.println(tempLifePolicy.getBasePremium());
    // }
    // }
  }
}
