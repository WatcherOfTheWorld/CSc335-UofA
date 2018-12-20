package model;

public class PunchCard {
    private int numVisits = 0;

    public void addVisit(){
        numVisits ++;
    }

    public boolean isNthVisit(int n){
        if(numVisits == 5){
            numVisits = 0;
            return true;
        }
        return false;
    }
}
