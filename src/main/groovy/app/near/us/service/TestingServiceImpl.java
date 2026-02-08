package app.near.us.service;


import app.near.us.api_security.security.CurrentUserService;
import app.near.us.entity.TestEntity;
import app.near.us.repository.TestingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService{

    private final TestingRepository repository;
    private final CurrentUserService currentUserService;

    @Override
    public void createTest() {
        TestEntity test = new TestEntity();
        test.setTest1("Test1");
        test.setTest2("Test2");
        test.setCreateAt(LocalDateTime.now());
        test.setCreatedBy(currentUserService.getUsername());
        repository.save(test);
    }
}
