package org.example.domain.wiseSaying.controller;

import org.example.domain.wiseSaying.Rq;
import org.example.domain.wiseSaying.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class WiseSayingController {
    private Scanner sc;
    // 생성자
    public WiseSayingController(Scanner sc) {
        this.sc = sc; // 생성자로 sc를 받는 이유: 계속 sc를 써야하므로, 매개변수가 아닌 생성자로 한 번만 주입함.
    }

    private int id = 0;
    private List<WiseSaying> wiseSayings = new ArrayList<>();

    // [UI]: 사용자 상호 작용
    public void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = write(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        // 내림차순 명언 받기
        List<WiseSaying> wiseSayings = list();

        for (WiseSaying wiseSaying : wiseSayings) {
            System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
        }
    }

    public void actionDelete(Rq rq) {
        String idStr = rq.getParam("id");
        int intIdStr = Integer.parseInt(idStr);

        if (delete(intIdStr)) {
            System.out.println("%d번이 삭제되었습니다.".formatted(intIdStr));
        }

        else {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(intIdStr));
        }
    }

    public void actionModify(Rq rq) {
        String idStr = rq.getParam("id");
        int intIdStr = Integer.parseInt(idStr);

        int modifyTargetIndex = findIndexById(intIdStr);

        if (modifyTargetIndex == -1) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(intIdStr));
            return;
        }

        WiseSaying modifyTargetWiseSaying = wiseSayings.get(modifyTargetIndex);

        System.out.println("명언(기존) : %s".formatted(modifyTargetWiseSaying.getContent()));
        System.out.print("명언 : ");
        String newContent = sc.nextLine();
        System.out.println("작가(기존) : %s".formatted(modifyTargetWiseSaying.getAuthor()));
        System.out.print("작가 : ");
        String newAuthor = sc.nextLine();

        modify(modifyTargetWiseSaying, newContent, newAuthor);
    }

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

    // id에 해당하는 명언이 몇 번째에 저장되어 있는지
    public int findIndexById(int id) {
        return IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1); // 없으면 -1
    }

    private void modify(WiseSaying modifyTargetWiseSaying, String newContent, String newAuthor) {
        modifyTargetWiseSaying.setContent(newContent);
        modifyTargetWiseSaying.setAuthor(newAuthor);
    }
}
