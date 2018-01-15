package net.mejaip.onlineshopping.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	
	static Logger log= Logger.getLogger(PageController.class.getName());

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		log.info("Going through index method");
		System.out.println("Jai ram ji");
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", "Welcome to Spring Web MVC");
		log.info("Returning the view.");
		return mv;
	}
	
	

	
	//Practice
	
//	@RequestMapping(value = { "/test/{greeting}" })
//	public ModelAndView test(@PathVariable("greeting") String greeting) {
//		
//		if(greeting ==null)
//		{
//			greeting="No Query String passed";
//		}
//		ModelAndView mv = new ModelAndView("page");
//		mv.addObject("greeting", greeting);
//		return mv;
//	}
//	
//	@RequestMapping(value = { "/test" })
//	public ModelAndView test(@RequestParam(value="greeting", required=false) String greeting) {
//		
//		if(greeting ==null)
//		{
//			greeting="No Query String passed";
//		}
//		ModelAndView mv = new ModelAndView("page");
//		mv.addObject("greeting", greeting);
//		return mv;
//	}

//	@RequestMapping(method = RequestMethod.GET)
//	public String printHello(ModelMap model) {
//		model.addAttribute("greeting", "Hello Spring MVC Framework!");
//		return "page";
//	}
}
