package es.uniovi.hci.util;

import org.apache.log4j.Logger;


public class NumberConverter {
	
	final static Logger logger = Logger.getLogger(NumberConverter.class);

	public static Integer parseInt(String data)
	{
		Integer result =  -1;
		try
		{
			result = Integer.parseInt(data);
		}
		catch (NumberFormatException e)
		{
			logger.error("Exception caught due a bad number format exception. Orinal data: "+data+". "+e.getStackTrace());
			
		}
		return result;
	}
	
}
