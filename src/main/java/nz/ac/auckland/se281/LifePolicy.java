package nz.ac.auckland.se281;

public class LifePolicy extends Policies {
  
  public LifePolicy( String[] options, String userName, int age) {
    super(options, userName, age);

  
  }
   
  @Override
  public int setBasePremium() {
  

    return 0;
}
}
