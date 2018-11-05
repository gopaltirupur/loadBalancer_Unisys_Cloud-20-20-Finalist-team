
import java.io.*;


public class EC_No_Of_Threads_Display extends Thread
{
	public void run()
	{
		while(true)
		{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("** - Active Threads - ** :"+Thread.activeCount());
		}
	}
		
}