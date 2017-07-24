package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args)
	{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String a = "2016-07-08";
		String b = "2016-07-07";
		
		Date date = new Date();
		String nowDate = sf.format(date);
		System.out.println(nowDate);
		
		try {
			Long c = sf.parse(a).getTime();
			Long d = sf.parse(b).getTime();
			
			if(c > d)
			{
				System.out.println("hi");
			}
			else
			{
				System.out.println("hihi");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
