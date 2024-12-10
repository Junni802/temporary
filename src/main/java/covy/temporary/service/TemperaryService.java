package covy.temporary.service;

import covy.temporary.mapper.TemporaryMapper;
import covy.temporary.model.vo.TemporaryInVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TemperaryService {

    private static final Logger log = LoggerFactory.getLogger(TemperaryService.class);
    private final TemporaryMapper mapper;

    @Transactional
    public List<TemporaryInVo> findTemporary() {
        List<TemporaryInVo> inVoList = new ArrayList<>();

        final int BATCH_SIZE = 1000;
        List<TemporaryInVo> batchList = new ArrayList<>();

        mapper.createTemporaryTable();

        for (int i = 1; i < 9999; i++) {
            TemporaryInVo temp = new TemporaryInVo();
            temp.setShelfNo(String.format("%04d", i));
            temp.setShelfNm(i + "매대");
            temp.setRegiUserId("covy");
            temp.setFinalModUserId("covy");
            batchList.add(temp);

            // 1000개씩 배치 처리
            if (batchList.size() >= BATCH_SIZE) {
                mapper.insertShelfTemporary(batchList); // 배치로 insert 실행
                batchList.clear(); // 리스트 초기화
            }
        }

// 나머지 데이터 처리
        if (!batchList.isEmpty()) {
            mapper.insertShelfTemporary(batchList);
        }

        long startTime = System.currentTimeMillis();
        mapper.tempInShelf();

        // loop를 돌면서 커넥션풀로 하나씩 값 주입
//        for (TemporaryInVo temporaryInVo : inVoList) {
//            mapper.insertShelf(temporaryInVo);
//        }

        // mybatis 에서 loop를 돌면서 값 저장(커넥션 풀 1개)
//        mapper.insertShelfList(inVoList);
        List<TemporaryInVo> resultData = mapper.selectTemporaryData();
        long endTime = System.currentTimeMillis();
        log.info("총 시간 -> " + (endTime - startTime) + "ms");
        return resultData;
    }

    @Transactional
    public List<TemporaryInVo> findShelf() {
        List<TemporaryInVo> inVoList = new ArrayList<>();

        for (int i = 100; i < 999; i++) {
            TemporaryInVo temp = new TemporaryInVo();
            // i를 3자리로 포맷팅
            temp.setShelfNo(String.format("%03d", i));  // "001", "002" 형태로 만들어짐
            temp.setShelfNm(i + "매대");
            temp.setRegiUserId("covy");
            temp.setFinalModUserId("covy");
            inVoList.add(temp);
        }

        long startTime = System.currentTimeMillis();
        mapper.createTemporaryTable();
        mapper.insertShelfTemporary(inVoList);
        mapper.tempInShelf();
        List<TemporaryInVo> resultData = mapper.selectTemporaryData();
        long endTime = System.currentTimeMillis();
        log.info("총 시간 -> " + (endTime - startTime) + "ms");
        return resultData;
    }

}
