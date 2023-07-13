package Experiments;

import java.util.Date;

public class Demo_Date
{

	public static void main(String[] args) 
	{
				Date date = new Date();
				{
						System.out.println(date);																						//  Fri Jun 30 11:35:11 EDT 2023
						
						System.out.println(date.toString());         																// Fri Jun 30 11:41:07 EDT 2023         
						
						System.out.println(date.toString().replace(" ", "_"));      										// Fri_Jun_30_11:42:16_EDT_2023
						
						System.out.println(date.toString().replace(" ","_" ).replace(":","_"));    				// Fri_Jun_30_11_43_34_EDT_2023
						
						
				String  dateText = date.toString();
				
				String dateTextWithOutSpace = dateText.replace(" ", "_");
				
				String dateTextWithOutSpaceandColon = dateTextWithOutSpace.replace(":","_");
				
				System.out.println(dateTextWithOutSpaceandColon);                                                       // Fri_Jun_30_11_49_33_EDT_2023
				
				
				
					
				}
		
		

	}

}
