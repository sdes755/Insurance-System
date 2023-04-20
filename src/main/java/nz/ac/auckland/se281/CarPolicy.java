package nz.ac.auckland.se281;

public class CarPolicy extends Policies {

    @Override
  public int sumToInsure(String options[]) {
    return Integer.parseInt(options[0]);
  }

  public int basePremium() {
    int x = 1;
    return x;
  }
}
