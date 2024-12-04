package covy.temporary.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Spring Boot 테스트 컨텍스트 로드
class TemperaryServiceTest {

    private final TemperaryService temporaryService;

    @Autowired
    public TemperaryServiceTest(TemperaryService temporaryService) {
        this.temporaryService = temporaryService;
    }

    @Test
    void findTemporary() {
        var result = temporaryService.findTemporary();
        Assertions.assertNotNull(result); // 결과 값이 null이 아닌지 확인
    }
}