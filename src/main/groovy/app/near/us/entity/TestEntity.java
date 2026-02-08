package app.near.us.entity;

import app.near.us.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TESTING")
public class TestEntity extends BaseEntity {
    private String test1;
    private String test2;
}
