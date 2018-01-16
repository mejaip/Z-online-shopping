package net.mejaip.onlineshopping.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.mejaip.shoppingbackend.dao.CategoryDAO;
import net.mejaip.shoppingbackend.dto.Category;

@Controller
public class PageController {

	static Logger log = Logger.getLogger(PageController.class.getName());

	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("title", "Home");
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickHome", true);

		return mv;
	}

	@RequestMapping(value = { "/about" })
	public ModelAndView about() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		log.info("Returning the view.");
		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		log.info("Going through index method");
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		log.info("Returning the view.");
		return mv;
	}

	/* Method to load all the products based on category */
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		log.info("Going through index method");
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Product");
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickAllProducts", true);
		log.info("Returning the view.");
		return mv;
	}

	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		
		ModelAndView mv = new ModelAndView("page");
		
		Category category = categoryDAO.get(id);
		
		mv.addObject("title", category.getName());
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("category", categoryDAO.get(id));
		mv.addObject("userClickCategoryProducts", true);
		log.info("Returning the view.");
		return mv;
	}

	// Practice

	// @RequestMapping(value = { "/test/{greeting}" })
	// public ModelAndView test(@PathVariable("greeting") String greeting) {
	//
	// if(greeting ==null)
	// {
	// greeting="No Query String passed";
	// }
	// ModelAndView mv = new ModelAndView("page");
	// mv.addObject("greeting", greeting);
	// return mv;
	// }
	//
	// @RequestMapping(value = { "/test" })
	// public ModelAndView test(@RequestParam(value="greeting", required=false)
	// String greeting) {
	//
	// if(greeting ==null)
	// {
	// greeting="No Query String passed";
	// }
	// ModelAndView mv = new ModelAndView("page");
	// mv.addObject("greeting", greeting);
	// return mv;
	// }

	// @RequestMapping(method = RequestMethod.GET)
	// public String printHello(ModelMap model) {
	// model.addAttribute("greeting", "Hello Spring MVC Framework!");
	// return "page";
	// }
}
