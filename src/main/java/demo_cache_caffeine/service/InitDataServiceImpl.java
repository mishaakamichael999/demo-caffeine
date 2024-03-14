package demo_cache_caffeine.service;

import demo_cache_caffeine.entity.Item;
import demo_cache_caffeine.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDataServiceImpl {

    private final ItemRepository repository;

    @EventListener
    public void handleContextRefresh(ApplicationStartedEvent event) {
        List<Item> items = new ArrayList<>();
        items.add(Item.builder().id(1).price(100).name("Boot").build());
        items.add(Item.builder().id(2).price(200).name("Cap").build());
        items.add(Item.builder().id(3).price(300).name("Pants").build());
        items.add(Item.builder().id(4).price(400).name("Gloves").build());

        repository.saveAll(items);
    }

}