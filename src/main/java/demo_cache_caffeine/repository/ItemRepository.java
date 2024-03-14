package demo_cache_caffeine.repository;

import demo_cache_caffeine.entity.Item;
import org.hibernate.annotations.Cache;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
    @Override
    <S extends Item> Iterable<S> saveAll(Iterable<S> entities);
}