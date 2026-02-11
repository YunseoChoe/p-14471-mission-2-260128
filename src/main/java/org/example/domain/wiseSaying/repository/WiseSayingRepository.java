package org.example.domain.wiseSaying.repository;

import org.example.domain.wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private List<WiseSaying> wiseSayings = new ArrayList<>();

    public List<WiseSaying> getWiseSayings() {
        return wiseSayings;
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

    public void save(WiseSaying wiseSaying) {
        wiseSayings.add(wiseSaying);
    }

    public List<WiseSaying> findListDesc() {
        return wiseSayings.reversed();
    }
}
