package org.example;

import java.util.Scanner;

// App 클래스로 뺀 이유: static 제약에서 벗어나기 위함
public class App {
    // 인스턴스 변수: 모든 함수에서 사용할 수 있음
    Scanner sc = new Scanner(System.in);
    int id = 0;
    WiseSaying[] wiseSayings = new WiseSaying[10];
    int lastWiseSayingIndex = 0;

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                break;
            }

            else if (cmd.equals("등록")) {
                actionWrite();
            }

            else if (cmd.equals("목록")) {
                actionList();
            }

            else if (cmd.startsWith("삭제")) {
                actionDelete(cmd);
            }
        }
    }

    // [UI]: 사용자 상호 작용
    public void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = write(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        // 내림차순 명언 받기
        WiseSaying[] wiseSayings = list();

        for (WiseSaying wiseSaying : wiseSayings) {
            System.out.println("%d / %s / %s".formatted(wiseSaying.id, wiseSaying.author, wiseSaying.content));
        }
    }

    public void actionDelete(String cmd) {
        String[] commandBits = cmd.split("=");

        if (commandBits.length < 2) {
            System.out.println("번호를 제대로 입력해주세요.");
            return;
        }

        String idStr = commandBits[1];
        int intIdStr = Integer.parseInt(idStr);

        if (delete(intIdStr)) {
            System.out.println("%d번이 삭제되었습니다.".formatted(intIdStr));
        }

        else {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(intIdStr));
        }
    }

    // [비즈니스]: 업무 처리
    public WiseSaying write(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying();

        wiseSaying.id = ++id;
        wiseSaying.content = content;
        wiseSaying.author = author;

        wiseSayings[lastWiseSayingIndex++] = wiseSaying;

        return wiseSaying; // 저장한 것을 다시 돌려주는 것이 관례
    }

    public WiseSaying[] list() {
        WiseSaying[] resultList = new WiseSaying[lastWiseSayingIndex];
        int resultListIndex = 0;

        // 역순 배열 저장
        for (int i = lastWiseSayingIndex - 1; i >= 0; i--) {
            resultList[resultListIndex] = wiseSayings[i];
            resultListIndex++;
        }

        return resultList;
    }

    public boolean delete(int intIdStr) {
        int deleteTargetIndex = -1; // 삭제하고 싶은 명언이 저장된 위치

        for (int i = 0; i < lastWiseSayingIndex; i++) {
            if (wiseSayings[i].id == intIdStr) {
                deleteTargetIndex = i;
                break;
            }
        }

        if (deleteTargetIndex == -1) {
            return false;
        }

        // 삭제
        for (int i = deleteTargetIndex; i < lastWiseSayingIndex; i++) {
            wiseSayings[i] = wiseSayings[i + 1];
        }
        lastWiseSayingIndex--;

        return true;
    }
}
