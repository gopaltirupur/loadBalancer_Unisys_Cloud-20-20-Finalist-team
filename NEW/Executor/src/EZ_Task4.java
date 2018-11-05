
public class EZ_Task4 extends Thread
{
	long[] l = new long[10];
	public void run()
	{
		EZ_Task4[][][] e = new EZ_Task4[1000][200][200];	

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