import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class ScratchPad {

    static long winnerIndex(long groupSize)
        {
        
       
        if (groupSize == 2)
            {
            return 1;
        }
        
        if (groupSize %2 == 1)
            {
            return winnerIndex(groupSize - 1) + 1;
        }
        
        long winnerHalf = winnerIndex(groupSize/2);
        long subProblem = groupSize/2;
       
        
        if (winnerHalf <= subProblem/2)
            {
            // second half of circle
            
            return (groupSize/2 + winnerHalf*2);
            
            
        }
        else
            {
            //first half of circle
            
            return (winnerHalf - subProblem/2)*2 - 1;
            
        }
        
    }
    
    public static void main(String[] args) {
      
        
       BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
       try
       {   
         int T= Integer.parseInt(inp.readLine());
            
         for(int i=0;i<T;i++)
         {
            long n= Long.parseLong(inp.readLine());
            //Your code goes here
             
             System.out.println (winnerIndex(n));
         }
       }
         catch(Exception e){}

      
    }
}
