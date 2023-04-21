package nz.ac.auckland.se281;

public abstract class Policies {
  public int sumToInsure;
  public int basePremium;

  public abstract int setPremium(String[] options, int age, String user);
}
