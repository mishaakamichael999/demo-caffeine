package demo_cache_caffeine.repository;

import demo_cache_caffeine.entity.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopRepository extends CrudRepository<Shop, Integer> {
}
