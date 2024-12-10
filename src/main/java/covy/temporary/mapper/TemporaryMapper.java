package covy.temporary.mapper;

import covy.temporary.model.vo.TemporaryInVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemporaryMapper {

    void createTemporaryTable();

    void insertShelfTemporary(List<TemporaryInVo> inVos);

    void tempInShelf();

    List<TemporaryInVo> selectTemporaryData();

    void insertShelf(TemporaryInVo inVo);

    void insertShelfList(List<TemporaryInVo> inVos);

}
