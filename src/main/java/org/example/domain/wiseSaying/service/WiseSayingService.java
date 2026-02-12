package org.example.domain.wiseSaying.service;

import org.example.domain.wiseSaying.WiseSaying;
import org.example.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    // [비즈니스]: 업무 처리
    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(0, content, author); // 새롭게 저장되는 id를 0으로 세팅.
        wiseSaying = wiseSayingRepository.save(wiseSaying);
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

        // 저장 (실질적 저장)
        // wiseSayingRepository.save(modifyTargetWiseSaying);
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }
}
