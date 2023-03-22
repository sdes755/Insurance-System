package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
    
  }
  Profiles profiles = new Profiles();
  public void printDatabase() {
    // TODO: Complete this method.
    int num = profiles.NumProfiles();
x
    for(int i = 0; i< num; i++){
      MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(num, userName, age);
    }
  }

  public void createNewProfile(String userName, String age) {
    
    userName = userName.toLowerCase();
    String beg = userName.substring(0,1).toUpperCase() + userName.substring(1);
    //System.out.println(beg);
    if(beg.length()< 3){
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(beg);
    }else if(Integer.parseInt(age) < 0){
      MessageCli.INVALID_AGE.printMessage(age,beg);
    }else{
     profiles.AddUsernamesandAges(beg, Integer.parseInt(age));
    }
  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
  }

  public void unloadProfile() {
    // TODO: Complete this method.
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}
