package gp2;
//comment
//comment

public class Individual {
    private char status;
    private double infectProb;
    private double recoveryProb;

    //Default Constructor
    public Individual() {
        status = 'S';
        infectProb = 0.0;
        recoveryProb = 0.0;
    }

    //Another Constructor
    public Individual(char status, double infectProb, double recoveryProb) {
      this.status = status;
      this.infectProb = infectProb;
      this.recoveryProb = recoveryProb;
    }
  

    //Getters and Setters for Indivdual Attributes 
    public void setStatus(char personStatus) {
        status = personStatus;
     }

    public void setInfect(double rateInfect) {
        infectProb = rateInfect;
     }
  
     public void setRecovery(int rateRecover) {
        recoveryProb = rateRecover;
     }

     public char getStatus() {
        return status;
     }

     public double getInfect() {
        return infectProb;
     }
  
     public double getRecovery() {
        return recoveryProb;
     }



}