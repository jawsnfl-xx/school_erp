package schoolerp;


public class programStart {
   
    //This method ensures that MySQL is running when the program starts
    public void startUtilities(){
       try{
       Process process = Runtime.getRuntime().exec("net START MySQL");
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
    }    
    //Method is called to stop MySQL when exiting the program
    public void stopUtilities(){
       
        try{
            Process process = Runtime.getRuntime().exec("net STOP MySQL");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
}
