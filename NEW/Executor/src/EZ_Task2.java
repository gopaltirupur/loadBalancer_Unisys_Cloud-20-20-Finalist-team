
public class EZ_Task2 extends Thread
{
	long[] l = new long[10];
	public void run()
	{
//		AZ_Task2[][][] e = new AZ_Task2[1000][200][200];	
		EZ_Task2[][] e = new EZ_Task2[1000][200];

		int a=-0;
/*		for(int i =0;i<2000;i++)
			for(int j =0;i<2000;i++)				
				for(int k =0;i<2000;i++)
				{
					//	e[i][j][k] = new even_task();
				}
*/
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
}