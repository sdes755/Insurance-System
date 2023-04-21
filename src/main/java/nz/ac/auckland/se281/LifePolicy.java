package nz.ac.auckland.se281;



public class LifePolicy extends Policies{

  //public LifePolicy(String[] options, int age, String user) {


    //this.sumToInsure = sumToInsure;
    //this.basePremium = basePremium;

   // int getSumToInsure(){
        //this.sumToInsure = Integer.parseInt(options[0]);
   // }

//}

  @Override
  public int setPremium(String[] options, int age, String user) {
    if (age > 100) {
      MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(user);
    }
    int sumInsured = Integer.parseInt(options[0]);
    double baseP = sumInsured * ((1 + age / 100) / 100);
    int baseP1 = (int) baseP;
    System.out.println(baseP1);
    MessageCli.NEW_POLICY_CREATED.printMessage("life", user);
    return baseP1;
  }
}


