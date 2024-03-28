package demo_cache_caffeine.service;

import demo_cache_caffeine.entity.Item;
import demo_cache_caffeine.entity.Shop;
import demo_cache_caffeine.repository.ItemRepository;
import demo_cache_caffeine.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDataServiceImpl {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    @EventListener
    public void handleContextRefresh(ApplicationStartedEvent event) {
        List<Shop> shops = createShops();
        addClothes(shopRepository, shops.getFirst());
        addStationeries(shopRepository, shops.getLast());
    }

    private static List<Shop> createShops() {
        return List.of(
                Shop.builder().id(1).city("Odesa").area(1500).build(),
                Shop.builder().id(2).city("Donetsk").area(5000).build());
    }

    private void addClothes(ShopRepository shopRepository, Shop shop) {
        List<Item> clothes = List.of(
                Item.builder().id(1).price(100).name("Boot").shop(shop).build(),
                Item.builder().id(2).price(200).name("Cap").shop(shop).build());
        shop.setItems(clothes);
        shopRepository.save(shop);
    }

    private void addStationeries(ShopRepository shopRepository, Shop shop) {
        List<Item> stationaries = List.of(Item.builder().id(3).price(300).name("Pen").shop(shop).build(),
                Item.builder().id(4).price(400).name("Pencil").shop(shop).build());
        shop.setItems(stationaries);
        shopRepository.save(shop);
    }

}