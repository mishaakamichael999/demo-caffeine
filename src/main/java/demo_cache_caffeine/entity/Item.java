package demo_cache_caffeine.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    private Integer id;


    private Integer price;
    private String name;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "shop_id")
    @JsonBackReference
    private Shop shop;
}
