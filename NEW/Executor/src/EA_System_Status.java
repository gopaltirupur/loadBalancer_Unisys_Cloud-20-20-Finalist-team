import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.Socket;


public class EA_System_Status extends Thread
{
	public static long totalPhysicalMemory;
	public static long idealPhysicalMemoryFree;
	
	public static long currentPhysicalMemoryFree;
	public static double systemCPULoad;	
	
	public void run()
	{
		AH_Current_CPU_Status obj_AH = new AH_Current_CPU_Status();
		
		
		EA_System_Status.currentPhysicalMemoryFree = obj_AH.getCurrentPhysicalMemoryFree();
		EA_System_Status.systemCPULoad = obj_AH.getSystemCPULoad();
				
		
	

while(true)
{
	try {
		Thread.sleep(200);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	BD_ListOfExecutors obj_BD = new BD_ListOfExecutors();
	
	obj_BD.executor_id=Executor.executor_id;
	obj_BD.ip_no=Executor.ip.toString();
	obj_BD.totalPhysicalMemory=EA_System_Status.totalPhysicalMemory;
	obj_BD.idealPhysicalMemoryFree=EA_System_Status.idealPhysicalMemoryFree;
	obj_BD.currentPhysicalMemoryFree=obj_AH.getCurrentPhysicalMemoryFree();
	obj_BD.systemCPULoad=obj_AH.getSystemCPULoad();
	obj_BD.no_of_tasks_completed=Executor.no_of_tasks_completed;
	
		try
		{
		Socket sa10000 = new Socket(Executor.server_ip,10000);
		OutputStream out10000= sa10000.getOutputStream();
		DataOutputStream dout10000 = new DataOutputStream(out10000);
//		InputStream in10000 = sa10000.getInputStream();
//		DataInputStream din10000 = new DataInputStream(in10000);
		String executors_string=obj_BD.executor_id+"&"+obj_BD.ip_no+"&"+obj_BD.totalPhysicalMemory+"&";
		executors_string+=obj_BD.idealPhysicalMemoryFree+"&"+obj_BD.currentPhysicalMemoryFree+"&"+obj_BD.systemCPULoad+"&";
		executors_string+=Executor.actual_no_of_tasks_completed;
		
		
		dout10000.writeUTF(executors_string);
		
//		din10000.close();	in10000.close();
		dout10000.close();	out10000.close();
		sa10000.close();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Total Tasks = "+(++Executor.no_of_tasks_completed));
		System.out.print("Active Threads = "+Thread.activeCount());
		Executor.actual_no_of_tasks_completed=((Executor.no_of_tasks_completed)-4-Thread.activeCount());
		System.out.println(":: Completed Tasks = "+Executor.actual_no_of_tasks_completed);
	}
  }	
}