package net.mejaip.onlineshopping.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public  ModelAndView handleNoHandlerFoundException()
	{
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "The page is not  constructed");
		mv.addObject("errorDescription", "The page you are looking is not available now");
		mv.addObject("title", "404 Error Page");
		
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public  ModelAndView handleProductNotFoundException()
	{
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Product Not Available");
		mv.addObject("errorDescription", "The Product you are looking for is not available");
		mv.addObject("title", "Product Unavailable");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public  ModelAndView handleException(Exception ex)
	{
		ModelAndView mv = new ModelAndView("error");
		
		/* Only for Debugging   */
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		ex.printStackTrace(pw);
		
		
		mv.addObject("errorTitle", "Error");
		mv.addObject("errorDescription", "Contact Tour Adminstrator "+sw.toString());
		mv.addObject("title", "Error");
		
		return mv;
	}

}
