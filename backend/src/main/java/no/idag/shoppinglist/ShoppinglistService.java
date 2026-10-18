package no.idag.shoppinglist;

import org.springframework.stereotype.Service;

@Service
public class ShoppinglistService {
    private final ShoppinglistRepository shoppinglistRepo;

    public ShoppinglistService(ShoppinglistRepository shoppinglistRepo) {
        this.shoppinglistRepo = shoppinglistRepo;
    }

    public Shoppinglist createShoppinglist(String name) {
        return this.shoppinglistRepo.save(new Shoppinglist(name));
    }
}
