package br.com.skipthedishes.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedEntityGraph(name = "CousineEntity.detail",
        attributeNodes = @NamedAttributeNode("stores"))
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CousineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    private List<StoreEntity> stores;

    protected CousineEntity() {
    }

    public CousineEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StoreEntity> getStores() {
        return stores;
    }

    @Override
    public String toString() {
        return "CousineEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
