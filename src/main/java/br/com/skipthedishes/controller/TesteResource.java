package br.com.skipthedishes.controller;

import br.com.skipthedishes.entity.CousineEntity;
import br.com.skipthedishes.entity.ProductEntity;
import br.com.skipthedishes.entity.StoreEntity;
import br.com.skipthedishes.repository.CousineRepository;
import br.com.skipthedishes.repository.ProductRepository;
import br.com.skipthedishes.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteResource {

    @Autowired
    private CousineRepository cousineRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public void insertData() {
        CousineEntity cousine1 = cousineRepository.save(new CousineEntity("Chinese"));
        CousineEntity cousine2 = cousineRepository.save(new CousineEntity("Pizza"));
        CousineEntity cousine3 = cousineRepository.save(new CousineEntity("Vietnamese"));
        CousineEntity cousine4 = cousineRepository.save(new CousineEntity("Sushi"));

        StoreEntity store1 = storeRepository.save(new StoreEntity("Hai Shang", "2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada", cousine1));
        StoreEntity store2 = storeRepository.save(new StoreEntity("Ye's", "616 St James St, Winnipeg, Manitoba R3G 3J5, Canada", cousine1));
        StoreEntity store3 = storeRepository.save(new StoreEntity("Good Earth Chop Suey House", "1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada", cousine1));
        StoreEntity store4 = storeRepository.save(new StoreEntity("Za Pizza Bistro", "E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada", cousine2));

        ProductEntity product1 = productRepository.save(new ProductEntity("Shrimp Tempura", "Fresh shrimp battered and deep fried until golden brown", 10.95, store1));
        ProductEntity product2 = productRepository.save(new ProductEntity("Shrimp with Snow Peas and Cashew", "A delicious combination of fresh shrimp, snow peas, and cashew", 12.5, store1));
        ProductEntity product3 = productRepository.save(new ProductEntity("Special Deep-Fried Fish", "Tilapia fish deep fried until flaky and tender", 12.95, store1));
        ProductEntity product4 = productRepository.save(new ProductEntity("Guarana", "Guarana KUAT", 1.6, store4));
        ProductEntity product5 = productRepository.save(new ProductEntity("Tapioca", "Tapioca de mandioca", 1.5, store1));
        ProductEntity product6 = productRepository.save(new ProductEntity("Bolo", "Bolo de chocolate", 1.4, store2));
        ProductEntity product7 = productRepository.save(new ProductEntity("Bala", "Bala doce", 1.3, store3));

    }

    @RequestMapping(method = RequestMethod.GET, value = "/cousine/{name}")
    public Long insertCousine(@PathVariable String name) {
        CousineEntity teste = new CousineEntity(name);
        cousineRepository.save(teste);

        return teste.getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/store/{name}/{address}/{cousineId}")
    public Long insertStore(@PathVariable String name, @PathVariable String address, @PathVariable Long cousineId) {
        StoreEntity storeEntity = new StoreEntity(name, address, cousineRepository.findOne(cousineId));
        storeRepository.save(storeEntity);

        return storeEntity.getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product/{name}/{description}/{price}/{storeId}")
    public Long insertProduct(@PathVariable String name, @PathVariable String description, @PathVariable Double price, @PathVariable Long storeId) {
        ProductEntity productEntity = new ProductEntity(name, description, price, storeRepository.findOne(storeId));
        productRepository.save(productEntity);

        return productEntity.getId();
    }
}
