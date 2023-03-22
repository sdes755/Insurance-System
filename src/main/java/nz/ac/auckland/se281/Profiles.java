
package nz.ac.auckland.se281; 

import java.util.ArrayList;


public class Profiles{

    private ArrayList <String> Usernames = new ArrayList<String>();
    private ArrayList<Integer> Ages = new ArrayList<Integer>();




    public void AddUsernamesandAges(String userName, int age){

        int truth = 1;
        for(int i = 0; i<Usernames.size(); i++){
            
            if(userName.equals(Usernames.get(i)) == true ){
                truth = 0;
            }
        }
        if(truth == 1){
        Usernames.add(userName);
        Ages.add(age);
        }  else{
            MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
        } 

}
}
