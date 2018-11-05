import java.io.*;
import java.net.*;


public class Executor
{
	
	static int executor_id;
	static long no_of_tasks_completed=0;
	public static long actual_no_of_tasks_completed=0;
	static InetAddress ip;
	static String server_ip;
	public static void main(String args[]) throws UnknownHostException, IOException
	{	
		server_ip = args[0];
		AH_Current_CPU_Status obj_AH = new AH_Current_CPU_Status();
		
		EA_System_Status.totalPhysicalMemory = obj_AH.getTotalPhysicalMemory();
		EA_System_Status.idealPhysicalMemoryFree = obj_AH.getCurrentPhysicalMemoryFree();
		
		Socket sa = new Socket(server_ip,9999);
		
		OutputStream outa= sa.getOutputStream();
		DataOutputStream douta = new DataOutputStream(outa);
		InputStream ina = sa.getInputStream();
		DataInputStream dina = new DataInputStream(ina);
		
		
		ip = InetAddress.getLocalHost();
		douta.writeUTF(ip.toString());
		executor_id=Integer.parseInt(dina.readUTF());
		
		System.out.println(" IP Address is ="+ip);
		System.out.println("Executor Id is "+executor_id);	
		
		dina.close();	ina.close();
		douta.close();	outa.close();
		sa.close();
		
		//This below Thread is to update the Task Scheduler about the current Executor's System Status
		EA_System_Status obj_EA = new EA_System_Status();
		obj_EA.start();		
		
		//This below Thread is to wait for Task and Start Executing - Port No. 10001
		EB_Receive_Task_And_Execute obj_EB = new EB_Receive_Task_And_Execute();
		obj_EB.start();
		
		//To Display the Active Threads
//		EC_No_Of_Threads_Display obj_EC = new EC_No_Of_Threads_Display();
//		obj_EC.start();
	}
}

