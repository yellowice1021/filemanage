package com.scut.client.user;

public class WorkingJob 
{
	public static int workingjob = 0;
	public static void addjob()
	{
		workingjob += 1;
	}
	
	public static void deljob()
	{
		workingjob -= 1;
	}
	public static boolean hasjob()
	{
		return workingjob == 0;
	}
}
