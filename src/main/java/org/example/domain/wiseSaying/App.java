package org.example.domain.wiseSaying;

import org.example.domain.wiseSaying.controller.SystemController;
import org.example.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

// App 클래스로 뺀 이유: main()의 static 제약에서 벗어나기 위함
public class App {
    private Scanner sc = new Scanner(System.in);

    private SystemController systemController = new SystemController();
    private WiseSayingController wiseSayingController = new WiseSayingController(sc);

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            Rq rq = new Rq(cmd);

            if (cmd.equals("종료")) {
                systemController.exit();
                break;
            }

            else if (cmd.equals("등록")) {
                wiseSayingController.actionWrite();
            }

            else if (cmd.equals("목록")) {
                wiseSayingController.actionList();
            }

            else if (cmd.startsWith("삭제")) {
                wiseSayingController.actionDelete(rq);
            }

            else if (cmd.startsWith("수정")) {
                wiseSayingController.actionModify(rq);
            }
        }
    }
}
