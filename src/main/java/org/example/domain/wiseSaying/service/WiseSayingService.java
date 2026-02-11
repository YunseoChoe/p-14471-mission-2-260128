package org.example.domain.wiseSaying.service;

import org.example.domain.wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingService {
    private int id = 0;
    private List<WiseSaying> wiseSayings = new ArrayList<>();

    // [비즈니스]: 업무 처리
    public WiseSaying write(String content, String author) {
        // ++id;
        WiseSaying wiseSaying = new WiseSaying(++id, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying; // 저장한 것을 다시 돌려주는 것이 관례
    }

    public List<WiseSaying> list() {
        return wiseSayings.reversed();
    }

    public boolean delete(int intIdStr) {
        // 참고. for문으로 break로 찾아서 삭제하는 방법이 성능은 더 좋음.
        // removeIf는 가독성이 좋음.
        return wiseSayings.removeIf(w -> w.getId() == intIdStr);
    }

    public void modify(WiseSaying modifyTargetWiseSaying, String newContent, String newAuthor) {
        modifyTargetWiseSaying.setContent(newContent);
        modifyTargetWiseSaying.setAuthor(newAuthor);
    }

    // id에 해당하는 명언이 몇 번째에 저장되어 있는지
    public int findIndexById(int id) {
        return IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1); // 없으면 -1
    }

    // id로 명언 찾기
    public WiseSaying findById(int id) {
        int index = findIndexById(id);
        if (index == -1) return null;
        return wiseSayings.get(index);
    }
}
