import java.util.*;
import java.net.Inet4Address;

public class subnet{
   
               public static void main(String[] args)
                      {
                            Scanner sc = new Scanner(System.in);
                            System.out.println("Enter IP address: ");
                            String IPAdd = sc.next();
                            System.out.println("\nEnter no of subnets: ");
                            int nsub= sc.nextInt();
                            int raiseto=0;
   
                            raiseto = (int)Math.ceil(Math.log(nsub)/Math.log(2));

                            double r = Math.pow(2,(8-raiseto));
                            int rangesize = (int)r;
                            int j=0;
                            int k=rangesize-1;
                            for(int i=0;i<nsub;i++)
                               {
                                  System.out.println("\nSubnet "+(i+1)+" : "+j+" to "+k);
                                  j=j+rangesize;
                                  k=k+rangesize;
                               }
                            int total=0, set=128;
                            for(int i=0;i<=raiseto;i++)
                                {
                                   total+=set;
                                   set=set/2;
                                }
                          
                           String[] SplitArray = IPAdd.split("\\.");
                            
                           if(Integer.parseInt(SplitArray[0])<=127)
                                  {
                                      System.out.println("\nClass A");
                                      System.out.println("\nDefault subnet: 255.0.0.0");
                                      System.out.println("\nBits borrowed: "+raiseto);
                                      System.out.println("\nSubnet mask: 255."+total+".0.0");
                                  }

                           else if(Integer.parseInt(SplitArray[0])<=191)
                                  {
                                      System.out.println("\nClass B");
                                      System.out.println("\nDefault subnet: 255.255.0.0");
                                      System.out.println("\nBits borrowed: "+raiseto);
                                      System.out.println("\nSubnet mask: 255.255."+total+".0");
                                  }

                           else if(Integer.parseInt(SplitArray[0])<=223)
                                  {
                                      System.out.println("\nClass C");
                                      System.out.println("\nDefault subnet: 255.255.255.0");
                                      System.out.println("\nBits borrowed: "+raiseto);
                                      System.out.println("\nSubnet mask: 255.255.255."+total);
                                  }
 
                          else
                                  {
                                      System.out.println("\nClass D");
                                      System.out.println("\nBits borrowed: "+raiseto);
                                      
                                  }
                       }
}
