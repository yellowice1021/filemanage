package test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class test {

	public static void main(String[] args)
	{
		Pattern pattern = Pattern.compile("^[+]?[\\d]*$");
		System.out.println(pattern.matcher("300").matches());
		
	}
	
}
