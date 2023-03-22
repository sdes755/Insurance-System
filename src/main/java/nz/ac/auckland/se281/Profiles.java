
package nz.ac.auckland.se281; 

import java.util.ArrayList;


public class Profiles{
//Creating two array lists for Usernames and the Ages to be stored in
    private ArrayList <String> Usernames = new ArrayList<String>();
    private ArrayList<Integer> Ages = new ArrayList<Integer>();



//Creating a method to store the Usernames and Ages in the array lists
    public void AddUsernamesandAges(String userName, int age){
//Using a for loop to check if the inputted username is unique
        int truth = 1;
        for(int i = 0; i<Usernames.size(); i++){
//If it is unique, we change the truth value to 0, and it will not be added
            if(userName.equals(Usernames.get(i)) == true ){
                truth = 0;
            }
        }
//Checking the truth value, if 1, we add the usernames and ages. if 0, we print the error message.
        if(truth == 1){
            Usernames.add(userName);
        Ages.add(age);
        }  else{
            MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
        } 

}
//Creating a new method to count the number of profiles in the database
    public int NumProfiles(){
        int num = Usernames.size();
        return num;
    }
    
    public String Print()
}
