import java.sql.*;

import javax.sql.*;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import com.sun.management.*;
import com.mysql.jdbc.exceptions.*;

// Run with   0   as parameter if dont want to use the Optimization Algorithm
//	Run with   1   as parameter if want to use the Optimization Algorithm


public class AAA_Main extends Thread
{	
	public static int turn=0;
	public static boolean[] flag=new boolean[2];
	public int record_count=0;
	public static int option=0;
	
	public static long totalPhysicalMemory = 0;
	public static long idealPhysicalMemoryFree = 0;
	public static long currentPhysicalMemoryFree = 0;
	public static double systemCPULoad =  0;
	
	//For Displaying the Current CPU Status
	public void display_CPU_Status()
	{
		AH_Current_CPU_Status obj_AH = new AH_Current_CPU_Status();
		AAA_Main.totalPhysicalMemory = obj_AH.getTotalPhysicalMemory();
		AAA_Main.currentPhysicalMemoryFree = obj_AH.getCurrentPhysicalMemoryFree();
		AAA_Main.systemCPULoad = obj_AH.getSystemCPULoad();

		System.out.println();
		System.out.print("Total Physical Memory  =  "+totalPhysicalMemory+"           ");
		System.out.println("Free Memory(While Starting Server ) = "+idealPhysicalMemoryFree);
		System.out.print("Current Free Memory    =  "+currentPhysicalMemoryFree+"          ");
		System.out.println(" Current CPU Load                    = "+(systemCPULoad)*100+" % ");		
	}
	
	public static void main(String args[])throws Exception
	{
		option = Integer.parseInt(args[0]);
		if(option==10)
		{
			nexec10.BAA_Main obj_BAA = new nexec10.BAA_Main();
			obj_BAA.BAA_Main_main();
		}
		else if(option==11)
		{
			nexec11.CAA_Main obj_CAA = new nexec11.CAA_Main();
			obj_CAA.CAA_Main_main();
		}
		else
		{
//		Array Object Creation for getting the contents of Task Table
		AC_Task_Details[] 	obj_AC = null; 
		long count = 0;	//Initialization for Task_Instance_No while starting the server

		AAA_Main obj_AAA = new AAA_Main();
		AH_Current_CPU_Status obj_AH = new AH_Current_CPU_Status();
		AAA_Main.idealPhysicalMemoryFree = obj_AH.getIdealPhysicalMemoryFree();		
		obj_AAA.display_CPU_Status();		
		
		//For Taking the Task Details from the Task Table and Keeping it in the RAM as a Object Array		
		try{
			AC_Task_Details 	obj_temp_AC 	= 	new 		AC_Task_Details();
			int 	no_of_tasks 	= 	obj_temp_AC.no_Of_Tasks();
			System.out.println();			System.out.println();
			System.out.println("  ***********************************************************************");
			System.out.println("  ***   WELCOME TO CLOUD OPTIMIZATION - SERVER  "+args[0]+"   ***   ");
			System.out.println("  ***********************************************************************");
			System.out.println();			
			System.out.println("Server Started - No. of Predefined Tasks Available in the Server = "+no_of_tasks);
			System.out.println();
			obj_AC = 	obj_temp_AC.get_Object_Array();		
			
			// For Displaying Current System Status - Every 30 Seconds After - Seperate Thread No. 4
			//Thread No. 1 - Main,  2 - Task Sort  3 - Task Consume  (If Server is 0 - then 2 will not applicable )
			{
				Thread t_AG = new AG_System_Status();
				t_AG.start();				
			}
			
			//To Display the list of contents in the Task Table
		/*	for(int x=0;x<no_of_tasks;x++)
			{
				System.out.println(obj_AC[x].task_no);
				System.out.println(obj_AC[x].type);
				System.out.println(obj_AC[x].time_to_live_sec);
				System.out.println(obj_AC[x].no_of_cycles);
				System.out.println(obj_AC[x].priority);
				System.out.println(obj_AC[x].memory_req_kB);	
			}
		*/		
		}

		catch(Exception e)
		{
			System.out.println(e);
		}
				
	
		{
			//This is a separate Thread runs, This will check the list of accumulated tasks from the 
			// entry table and enter the same in to the sorted table and update the weitage of the already
			//listed tasks
			
			if(option ==1)
			{
				Thread t_AE = new AE_Task_Sort();
				t_AE.start();				
				Thread t_AF = new AF_TaskConsume_from_Sorted();
				t_AF.start();			
			}

			//This is a separate Thread runs, This will Check the available resources and consume the processes by allotting the resources for execution. 
			else if(option ==0)
			{
				Thread t_AF = new AF_TaskConsume_from_Entry();
				t_AF.start();
			}
					
			else
			{
				System.out.println("Wrong Parameter - Only 0 and 1 allowed as parameter ");
				System.out.println("  0 for running Server with Optimization Algorithm ");      
				System.out.println("  1 for running Server with out Optimization Algorithm");
				return;
			}

			
		}
	
		
		
		//Waits for the Client, Receives the Sequence of tasks from the client
		//Inserts it to the Entry Table using the AC class 
	
		while(true)
		{
			try
			{
				TCP_Server obj_TCP_Server = new TCP_Server();
				String task_sequence = obj_TCP_Server.connectionWithClient();	//RECEIVING TASK SEQUENCE AT PORT 6666
				
				AB_StringToInt obj_AB = new AB_StringToInt(task_sequence);
				int arrayLength = obj_AB.getArrayLength();
				int[] intArray = new int[arrayLength];
				intArray = obj_AB.getIntArray();
				String clientID = obj_AB.getClientId();
				
				AD_TaskEntry obj_AD = new AD_TaskEntry();

				String dbURL = "jdbc:mysql://localhost/cloud";
				String dbClass = "com.mysql.jdbc.Driver";
				Class.forName("com.mysql.jdbc.Driver");	
				Connection con = DriverManager.getConnection (dbURL,"root",nexec10.BAA_Main.database_password);
				Statement stmt = con.createStatement();
				
				//for Making Atomic Transaction on the Entry Table
				flag[0]=true;
				turn = 1;
				while(flag[1]&&turn==1);				
				//Critical Section - Entry Table - Start
				for(int i=0;i<arrayLength;i++)
				{
					count=count+1;					
					obj_AD.insert(obj_AC[intArray[i]-1],clientID,count,stmt);					
				}
				if(Integer.parseInt(args[0])==0)
					System.out.println(arrayLength+" No. of Tasks Considered for Allocation");
				flag[0]=false;	
				//Critical Section - Entry Table - End
				
			}
			catch(Exception e)
			{
				System.out.println("Exception Received"+e);			
			}
//			System.out.println("Client Connection Finished");
		} //End of While		
	}
	}
}