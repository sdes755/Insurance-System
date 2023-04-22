package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).

  }

  Profiles profiles = new Profiles();

  // ArrayList<LifePolicy> lifePolicies = new ArrayList<LifePolicy>();
  // ArrayList<CarPolicy> carPolicies = new ArrayList<CarPolicy>();
  // ArrayList<HomePolicy> homePolicies = new ArrayList<HomePolicy>();

  // ArrayList<String> carUsers = new ArrayList<String>();
  // ArrayList<String> homeUsers = new ArrayList<String>();
  // ArrayList<String> lifeUsers = new ArrayList<String>();

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

  public void createPolicy(PolicyType type, String[] options) {

    profiles.storePolicy(type, options);

    // String user = profiles.loadedUser();
    // int age = profiles.loadAge();
    // if (age != 0) {
    //   if (type == PolicyType.CAR) {
    //     CarPolicy car = new CarPolicy(options, user, age);
    //     carPolicies.add(car);
    //     carUsers.add(user);
    //   } else if (type == PolicyType.HOME) {
    //     HomePolicy home = new HomePolicy(options, user, age);
    //     homePolicies.add(home);
    //     homeUsers.add(user);
    //   } else if (type == PolicyType.LIFE && age < 100 && lifeUsers.contains(user) == false) {
    //     LifePolicy life = new LifePolicy(options, user, age);
    //     lifePolicies.add(life);
    //     lifeUsers.add(user);
    //   } else if (type == PolicyType.LIFE && age > 100) {
    //     MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(user);
    //   } else if (type == PolicyType.LIFE && lifeUsers.contains(user) == true) {
    //     MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(user);
    //   }
    // } else {
    //   MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
    // }

    // for (LifePolicy tempLifePolicy : lifePolicies) {
    //   System.out.println(tempLifePolicy.getBasePremium());
    // }
    // for (CarPolicy tempCarPolicy : carPolicies) {
    //   System.out.println(tempCarPolicy.getMakeModel());
    // }
    // for (HomePolicy tempHomePolicy : homePolicies) {
    //   System.out.println(tempHomePolicy.getBasePremium());
    //   ;
    // }

    // for (String tempLifeUser : LifeUsers) {
    //   System.out.println(tempLifeUser);
    // }
    // for (String tempCarUser : CarUsers) {
    //   System.out.println(tempCarUser);
    // }
  }
}
