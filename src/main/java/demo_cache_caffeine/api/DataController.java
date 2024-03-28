package demo_cache_caffeine.api;

import ch.qos.logback.core.util.TimeUtil;
import demo_cache_caffeine.entity.Item;
import demo_cache_caffeine.entity.Shop;
import demo_cache_caffeine.repository.ItemRepository;
import demo_cache_caffeine.repository.ShopRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequiredArgsConstructor
public class DataController {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    @GetMapping("/item")
    @Cacheable(cacheNames = "item") // If no params are given on key, return SimpleKey.EMPTY.
//    This strategy will be ok for most use cases provided that the params implement valid equals and hascode methods.
    public List<Item> items() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        Iterable<Item> iterable = itemRepository.findAll();
        return Streamable.of(iterable).toList();
    }
    
    @GetMapping("/shop/{id}") //cacheNames = "shopItem" will create new cache name storage
    @Cacheable(cacheNames = "shop", key = "{#id}")//, condition = "#id < 2"
    // + key "{#bla}"
    public Shop shopById(@PathVariable Integer id) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return shopRepository.findById(id).orElse(null);
    }


}
