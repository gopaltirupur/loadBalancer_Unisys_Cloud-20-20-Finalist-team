import java.io.*;
import java.net.*;

public class client_auto
{
	public static String scheduler_ip;
	public static void main(String args[])throws IOException
	{
		scheduler_ip = args[0];
//		char a =args[0].charAt(0);
//		int n=1;
		String reply;
		try 
		{
			while(true) 
			{
//for client A
//				case 'A': 
//					while(n==1)
					{
						Socket sa=new Socket(client_auto.scheduler_ip,6666);
						OutputStream outa=sa.getOutputStream();
						DataOutputStream douta=new DataOutputStream(outa);
						InputStream ina=sa.getInputStream();
						DataInputStream dina=new DataInputStream(ina);
						douta.writeUTF('A'+",1,3,6,7,9,1,3,5,7,9");
						reply=dina.readUTF();
						System.out.println(reply);
						System.out.println("-------------------------------------------------");
						douta.close();
						outa.close();
						sa.close();
						Thread.sleep(3000);
//						n=0;
//						System.out.println("press 1 to reply : ");
//						BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//						n=Integer.parseInt(br.readLine());
					}																																											//end of case
//					break;	
//for client B
//				case 'B':
//					while(n==1)
					{
						Socket sb=new Socket(client_auto.scheduler_ip,6666);
						OutputStream outb=sb.getOutputStream(); 
						DataOutputStream doutb=new DataOutputStream(outb);
						InputStream inb=sb.getInputStream();
						DataInputStream dinb=new DataInputStream(inb);
						doutb.writeUTF('B'+",2,4,5,8,10,2,4,6,8,10");
						reply=dinb.readUTF();
						System.out.println(reply);
						System.out.println("-------------------------------------------------");
						doutb.close();
						outb.close();
						sb.close();
						Thread.sleep(3000);
/*						n=0;
						System.out.println("press 1 to reply : ");
						BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
						n=Integer.parseInt(br.readLine());
*/
					}
//					break;																																							//end of case  
//for client C 
//				case 'C':
//					while(n==1)
					{
						  Socket sc=new Socket(client_auto.scheduler_ip,6666);
						  OutputStream outc=sc.getOutputStream();
						  DataOutputStream doutc=new DataOutputStream(outc);
						  InputStream inc=sc.getInputStream();
						  DataInputStream dinc=new DataInputStream(inc);
						  doutc.writeUTF('C'+",1,3,5,7,9,1,3,6,7,9");
						  reply=dinc.readUTF();
						  System.out.println(reply);
						  System.out.println("-------------------------------------------------");
						  doutc.close();
						  outc.close();
						  sc.close();
						  Thread.sleep(3000);
/*						  n=0;
						  System.out.println("press 1 to reply : ");
						  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
						  n=Integer.parseInt(br.readLine());
*/
						}
//						break;																																							//end of case
//for client D	
//				case 'D':
//					while(n==1)
					{
					  Socket sd=new Socket(client_auto.scheduler_ip,6666);
					  OutputStream outd=sd.getOutputStream();
					  DataOutputStream doutd=new DataOutputStream(outd);
					  InputStream ind=sd.getInputStream();
					  DataInputStream dind=new DataInputStream(ind);
					  doutd.writeUTF('D'+",2,4,6,8,10,2,4,5,8,10");
					  reply=dind.readUTF();
					  System.out.println(reply);
					  System.out.println("-------------------------------------------------");
					  doutd.close();
					  outd.close();
					  sd.close();
					  Thread.sleep(3000);
/*					   n=0;
					  System.out.println("press 1 to reply : ");
					  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
					  n=Integer.parseInt(br.readLine());
*/
					}
//					break;																																								//end of case
//for client E	
//				case 'E':
//					while(n==1)
					{
						  Socket se=new Socket(client_auto.scheduler_ip,6666);
						  OutputStream oute=se.getOutputStream();
						  DataOutputStream doute=new DataOutputStream(oute);
						  InputStream ine=se.getInputStream();
						  DataInputStream dine=new DataInputStream(ine);
						  doute.writeUTF('E'+",1,3,6,7,9,1,3,5,7,9");
						  reply=dine.readUTF();
						  System.out.println(reply);
						  System.out.println("-------------------------------------------------");
						  doute.close();
						  oute.close();
						  se.close();
						  Thread.sleep(3000);
 /*						  *n=0;
						  System.out.println("press 1 to reply : ");
						  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
						  n=Integer.parseInt(br.readLine());
*/	
						}
//							break;																																									//end of case
							
//if the clients are not A,B,C,D,E		
//				default:
//						System.out.println("Invalid client");
//						System.exit(0);
//						break;																									//end of default case																
			}																													//end of switch case block
		}			
		catch(Exception ee)             													//if the server socket is not open for new connection
		{
			System.out.println("Server not connected");
		}
	}
}