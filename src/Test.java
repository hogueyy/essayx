import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class Test {
	
	
	public static void main(String[] args)
	{				
				Thesaurus th = new Thesaurus();
				th.initalize();
		
				String fullEssay;
				String extendedEssay;
								
				fullEssay = readEssay("essay.txt");
				extendedEssay = th.extendSentence(fullEssay);	
				try {
					writeEssay(extendedEssay);
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
    }
	
	public static String readEssay(String fileName)
	{
		StringBuilder essay = new StringBuilder("");
        String line = null;
                
        try {
       		
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while((line = bufferedReader.readLine()) != null) {essay.append(line);}   

            bufferedReader.close();   		            // Always close files.      
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");                  
        }
        
      return essay.toString();
		
	}
		
	public static void writeEssay(String extendedEssay) throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("extendedEssay.txt", "UTF-8");
		writer.print(extendedEssay);
		writer.close();
		
	}
	
	}


