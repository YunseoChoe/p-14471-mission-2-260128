package org.example.domain.wiseSaying.service;

import org.example.domain.wiseSaying.WiseSaying;
import org.example.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private int id = 0;
    private WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    // [비즈니스]: 업무 처리
    public WiseSaying write(String content, String author) {
        // ++id;
        WiseSaying wiseSaying = new WiseSaying(++id, content, author);
        wiseSayingRepository.save(wiseSaying);
        return wiseSaying; // 저장한 것을 다시 돌려주는 것이 관례
    }

    public List<WiseSaying> list() {
        return wiseSayingRepository.findListDesc();
    }

    public boolean delete(int intIdStr) {
        // 참고. for문으로 break로 찾아서 삭제하는 방법이 성능은 더 좋음.
        // removeIf는 가독성이 좋음.
        return wiseSayingRepository.getWiseSayings()
                .removeIf(w -> w.getId() == intIdStr);
    }

    public void modify(WiseSaying modifyTargetWiseSaying, String newContent, String newAuthor) {
        modifyTargetWiseSaying.setContent(newContent);
        modifyTargetWiseSaying.setAuthor(newAuthor);
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }
}
