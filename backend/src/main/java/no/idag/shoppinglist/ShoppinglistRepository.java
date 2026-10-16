package no.idag.shoppinglist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppinglistRepository extends JpaRepository<Shoppinglist,Long> {
}
