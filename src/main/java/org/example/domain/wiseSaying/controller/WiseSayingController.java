package org.example.domain.wiseSaying.controller;

import org.example.domain.wiseSaying.Rq;
import org.example.domain.wiseSaying.WiseSaying;
import org.example.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;
    // 생성자
    public WiseSayingController(Scanner sc) {
        this.sc = sc; // 생성자로 sc를 받는 이유: 계속 sc를 써야하므로, 매개변수가 아닌 생성자로 한 번만 주입함.
    }
    WiseSayingService wiseSayingService = new WiseSayingService();

    // [UI]: 사용자 상호 작용
    public void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        // 내림차순 명언 받기
        List<WiseSaying> wiseSayings = wiseSayingService.list();
        for (WiseSaying wiseSaying : wiseSayings) {
            System.out.println("%d / %s / %s / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent(), wiseSaying.getCreatedDate(), wiseSaying.getModifiedDate()));
        }
    }

    public void actionDelete(Rq rq) {
        String idStr = rq.getParam("id");
        int intIdStr = Integer.parseInt(idStr);

        System.out.println("사용자에게 받은 삭제할 id: " + intIdStr);

        if (wiseSayingService.delete(intIdStr)) {
            System.out.println("%d번이 삭제되었습니다.".formatted(intIdStr));
        }

        else {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(intIdStr));
        }
    }

    public void actionModify(Rq rq) {
        String idStr = rq.getParam("id");
        int id = Integer.parseInt(idStr);

        WiseSaying modifyTargetWiseSaying = wiseSayingService.findById(id);

        if (modifyTargetWiseSaying == null) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("명언(기존) : %s".formatted(modifyTargetWiseSaying.getContent()));
        System.out.print("명언 : ");
        String newContent = sc.nextLine();
        System.out.println("작가(기존) : %s".formatted(modifyTargetWiseSaying.getAuthor()));
        System.out.print("작가 : ");
        String newAuthor = sc.nextLine();

        wiseSayingService.modify(modifyTargetWiseSaying, newContent, newAuthor);
    }
}
