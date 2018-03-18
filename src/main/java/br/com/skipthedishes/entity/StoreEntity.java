package br.com.skipthedishes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cousineId")
    private CousineEntity cousine;

    @Column(name = "cousineId", insertable = false, updatable = false)
    private Long cousineId;

    protected StoreEntity() {
    }

    public StoreEntity(String name, String address, CousineEntity cousineEntity) {
        this.name = name;
        this.address = address;
        this.cousine = cousineEntity;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CousineEntity getCousine() {
        return cousine;
    }

    public void setCousine(CousineEntity cousineEntity) {
        this.cousine = cousineEntity;
    }

    public Long getCousineId() {
        return cousineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntity that = (StoreEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "StoreEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cousine=" + cousine +
                '}';
    }
}
