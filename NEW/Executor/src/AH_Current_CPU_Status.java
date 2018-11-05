import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import com.sun.management.*;
import com.mysql.jdbc.exceptions.*;

public class AH_Current_CPU_Status
{
	
	OperatingSystemMXBean osMBean;
	RuntimeMXBean runtimeMBean;
	
	public AH_Current_CPU_Status()
	{
		try
		{
		osMBean =(OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		runtimeMBean = ManagementFactory.getRuntimeMXBean();
		}
		catch(ClassCastException e)
		{
			e.printStackTrace();
		}
	}
	public long getTotalPhysicalMemory() 
	{	
		return osMBean.getTotalPhysicalMemorySize();
	}

	public long getIdealPhysicalMemoryFree() 
	{
		return osMBean.getFreePhysicalMemorySize();
	}

	public long getCurrentPhysicalMemoryFree() 
	{
		return osMBean.getFreePhysicalMemorySize();
	}

	public double getSystemCPULoad() 
	{
		return osMBean.getSystemCpuLoad();
	}	
}
