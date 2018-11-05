import java.io.*;
import java.net.*;



public class EB_Receive_Task_And_Execute extends Thread
{	
	public void run()
	{
	
		System.out.println("Test for executing the tasks");
		EB_Receive_Task_And_Execute obj_EB = new EB_Receive_Task_And_Execute();
		while(true)
		{		
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				try
				{					
				ServerSocket s10001 = new ServerSocket(10001);
				Socket so10001 = s10001.accept();
//				OutputStream out10001= sa10001.getOutputStream();
//				DataOutputStream dout10001 = new DataOutputStream(out10001);
				InputStream in10001 = so10001.getInputStream();
				DataInputStream din10001 = new DataInputStream(in10001);
				
				String task_to_be_executed = din10001.readUTF();
				int task = Integer.parseInt(task_to_be_executed);
				if(task%2==0)
				{
					obj_EB.run_a_task();
					System.out.print("Task1 - Started :");
					
				}
				else
				{
					obj_EB.run_a_task();
					System.out.print("Task2 - Started :");															
				}
	/*
				System.out.print("Total Tasks = "+(++Executor.no_of_tasks_completed));
				System.out.print("Active Threads = "+Thread.activeCount());
				Executor.actual_no_of_tasks_completed=((Executor.no_of_tasks_completed)-4-Thread.activeCount());
				System.out.println(":: Completed Tasks = "+Executor.actual_no_of_tasks_completed);				
		*/		
				din10001.close();	in10001.close();
//				dout10001.close();	out10001.close();
				so10001.close();	s10001.close();
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		  }		
	
	public void run_a_task()
	{
		EZ_Task1 obj_EZ1 = new EZ_Task1();		
		obj_EZ1.start();
	}
	}	
