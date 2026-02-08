package app.near.us.repository;

import app.near.us.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestingRepository extends JpaRepository<TestEntity, Long> {
}
