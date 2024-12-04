package covy.temporary.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TemporaryServiceTest {

    private final TemperaryService temporaryService;

    @Autowired
    public TemporaryServiceTest(TemperaryService temporaryService) {
        this.temporaryService = temporaryService;
    }

    @Test
    void findTemporary() {
        var result = temporaryService.findTemporary();
        assertNotNull(result); // 예제: 테스트 로직
    }
}