package net.mejaip.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.mejaip.onlineshopping.util.FileUploadUtility;
import net.mejaip.onlineshopping.validator.ProductValidator;
import net.mejaip.shoppingbackend.dao.CategoryDAO;
import net.mejaip.shoppingbackend.dao.ProductDAO;
import net.mejaip.shoppingbackend.dto.Category;
import net.mejaip.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Product");

		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Submitted Successfully");
			} else if (operation.equals("category")) {
				mv.addObject("message", "Category Submitted Successfully");
			}
		}

		return mv;
	}
	// Handling Product Submission

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult result,
			Model model, HttpServletRequest request) {
		if (mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, result);
		} else {
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, result);
			}
		}

		if (result.hasErrors()) {

			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "Manage Product");
			model.addAttribute("message", "Validation failed for Product Submission");

			return "page";
		}
		logger.info(mProduct.toString());
		if (mProduct.getId() == 0) {
			productDAO.add(mProduct);
		} else {
			productDAO.update(mProduct);
		}

		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();

		product.setActive(!product.isActive());

		productDAO.update(product);

		return (isActive) ? "You have successfully De activated the product with id - " + product.getId()
				: "You have successfully Activated the product with id - " + product.getId();
	}

	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProducts(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Product");
		// Fetch product from the Database

		Product nProduct = productDAO.get(id);
		mv.addObject("product", nProduct);

		return mv;
	}
	
	// to handle category submission
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		
		categoryDAO.add(category);
		
		return "redirect:/manage/products/?operation=category";
		
	}

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
}
