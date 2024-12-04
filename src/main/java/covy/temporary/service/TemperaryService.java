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
        List<TemporaryInVo> resultData = mapper.selectTemporaryData();
        long endTime = System.currentTimeMillis();
        log.info("총 시간 -> " + (endTime - startTime) + "ms");
        return resultData;
    }

}
