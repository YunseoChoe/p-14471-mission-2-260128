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
        }
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = new WiseSaying();

        wiseSaying.id = ++id;
        wiseSaying.content = content;
        wiseSaying.author = author;

        wiseSayings[lastWiseSayingIndex++] = wiseSaying;
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    public void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (int i = lastWiseSayingIndex - 1; i >= 0; i--) {
            WiseSaying foundedWiseSaying = wiseSayings[i];
            System.out.println(foundedWiseSaying.id + " / " + foundedWiseSaying.author + " / " + foundedWiseSaying.content);
        }
    }
}
