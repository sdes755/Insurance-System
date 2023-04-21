package nz.ac.auckland.se281;

public class HomePolicy extends Policies {
  
  private String address;
  private boolean rental;

  public HomePolicy(String[] options, String userName, int age) {
    super(options, userName, age);
    this.address = options[1];
    this.rental = Boolean.parseBoolean(options[2]);
  }

  @Override
  public int setBasePremium() {
    int basePremium = 0;
    if (rental) {
      
    } else {
      ;
    }
    return basePremium;
  }
}
