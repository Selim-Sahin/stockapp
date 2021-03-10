package stockapp.stockapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private StockService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Stock> listStocks = service.listAll();
		model.addAttribute("listStocks", listStocks);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewStockForm(Model model) {
		Stock stock = new Stock();
		model.addAttribute("stock", stock);
		
		return "new_stock";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveStock(@ModelAttribute("stock") Stock stock) {
		service.save(stock);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditStockForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_stock");
		
		Stock stock = service.get(id);
		mav.addObject("stock", stock);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteStock(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
}