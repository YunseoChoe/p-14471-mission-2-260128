package org.example;

// 최대한 라이브러리를 사용하지 않고 구현하기.

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int idx = 0;
        List<Info> list = new ArrayList<>();
        System.out.println("== 명언 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine();

            if (cmd.equals("종료")) {
                break;
            }

            // 등록
            else if (cmd.equals("등록")) {
                Info info = new Info(++idx, sc);
                list.add(info);
                System.out.println(idx + "번 명령이 등록되었습니다.");
            }

            // 목록
            else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = list.size() - 1; i >= 0; i--) {
                    Info info = list.get(i);
                    System.out.println(info.number + " / " + info.author + " / " + info.wiseSaying);
                }
            }

            // 삭제
            else if (cmd.equals("삭제")) {
                System.out.print("삭제?id=");
                int deleteIdx = Integer.parseInt(sc.nextLine());
                boolean isFind = false;
                Iterator<Info> it = list.iterator();

                while (it.hasNext()) {
                    Info info = it.next();
                    if (info.number == deleteIdx) {
                        it.remove();
                        isFind = true;
                        System.out.println(deleteIdx + "번 명언이 삭제되었습니다.");
                        break;
                    }
                }
                if (isFind == false) {
                    System.out.println(deleteIdx + "번 명언은 존재하지 않습니다.");
                }
            }

            // 수정
            else if (cmd.equals("수정")) {
                System.out.print("수정?id=");
                int updateIdx = Integer.parseInt(sc.nextLine());
                boolean isFind = false;
                Iterator<Info> it = list.iterator();

                while (it.hasNext()) {
                    Info info = it.next();
                    if (info.number == updateIdx) {
                        isFind = true;
                        System.out.println("명언(기존) : " + info.wiseSaying);
                        System.out.print("명언 : ");
                        info.wiseSaying = sc.nextLine();

                        System.out.println("작가(기존) : " + info.author);
                        System.out.print("작가 : ");
                        info.author = sc.nextLine();
                    }
                }
                if (isFind == false) {
                    System.out.println(updateIdx + "번 명언은 존재하지 않습니다.");
                }
            }

            else {
                System.out.println("명령을 다시 입력해 주세요.");
            }
        }
    }
}

class Info {
    int number;
    String author;
    String wiseSaying;

    Info(int number, Scanner sc) {
        this.number = number;

        System.out.print("명언 : ");
        this.wiseSaying = sc.nextLine();

        System.out.print("작가 : ");
        this.author = sc.nextLine();
    }
}
