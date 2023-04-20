package nz.ac.auckland.se281;

public class HomePolicy extends Policies {

  @Override
  public int basePremium(String[] options) {
    int sumInsured = Integer.parseInt(options[0]);
    String answer = options[2].toLowerCase();
    double baseP;
    if (answer.equals("yes")) {
      baseP = 0.02 * sumInsured;

    } else {
      baseP = 0.01 * sumInsured;
    }
    int basep = (int) baseP;
    return basep;
  }
}
