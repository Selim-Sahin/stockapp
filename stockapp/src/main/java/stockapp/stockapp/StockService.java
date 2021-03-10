package stockapp.stockapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
	@Autowired
	private StockRepository repo;
	
	public List<Stock> listAll() {		
		return repo.findAll();
	}
	
	public void save(Stock stock) {
		repo.save(stock);
	}
	
	public Stock get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}

