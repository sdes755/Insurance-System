package nz.ac.auckland.se281;

public class HomePolicy extends Policies {

  @Override
  public int sumToInsure(String options[]) {
    return Integer.parseInt(options[0]);
  }

  @Override
  public int basePremium() {
    int basepremium = 1;
    return basepremium;
  }
}
