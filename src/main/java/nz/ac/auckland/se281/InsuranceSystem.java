package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
    
  }

  public void printDatabase() {
    // TODO: Complete this method.
  }

  public void createNewProfile(String userName, String age) {
    
    userName = userName.toLowerCase();
    //userName = userName.toUpperCase(userName.charAt(0));
    String beg = userName.substring(0,1).toUpperCase() + userName.substring(1);
    System.out.println(beg);
    if(beg.length()< 3){
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(beg);
    }
    if(Integer.parseInt(age) < 0){
      MessageCli.INVALID_AGE.printMessage(age,beg);
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
