package demo_cache_caffeine.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shop {
    @Id
    private Integer id;
    private String address;
    private Integer area;
}
