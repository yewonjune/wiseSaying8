import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner sc = new Scanner(System.in);

    ArrayList<WiseSaying> wiseSayings = new ArrayList<>();

    int id = 0;

    void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine();

            if (command.equals("등록")) {
                actionWrite();
            } else if (command.equals("목록")) {
                actionList();
            } else if (command.startsWith("삭제?id=")) {
                int targetId = Integer.parseInt(command.substring(6));
                actionDelete(targetId);
            } else if (command.startsWith("수정?id=")) {
                int targetId = Integer.parseInt(command.substring(6));
                actionEdit(targetId);
            } else if (command.equals("종료")) {
                break;
            }
        }

        sc.close();
    }

    WiseSaying findById(int targetId) {
        for(WiseSaying wiseSaying : wiseSayings) {
            if(wiseSaying.getId() == targetId) {
                return wiseSaying;
            }
        }

        return null;
    }

    private void actionEdit(int targetId) {
        WiseSaying wiseSaying = findById(targetId);

        if(wiseSaying==null) {
            System.out.println(targetId + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존): "+ wiseSaying.getContent());
        System.out.print("명언: ");
        String content = sc.nextLine();

        System.out.println("작가(기존): "+ wiseSaying.getAuthor());
        System.out.print("작가: ");
        String author = sc.nextLine();

        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }

    private void actionDelete(int targetId) {
        WiseSaying wiseSaying = findById(targetId);

        if(wiseSaying==null) {
            System.out.println(targetId + "번 명언은 존재하지 않습니다.");
            return;
        }

        wiseSayings.remove(wiseSaying);
        System.out.println(targetId + "번 명언이 삭제되었습니다.");
    }

    int getSize() {
        return wiseSayings.size();
    }

    private void actionWrite() {
        System.out.print("명언: ");
        String content = sc.nextLine();

        System.out.print("작가: ");
        String author = sc.nextLine();

        WiseSaying wiseSaying = write(content, author);

        System.out.println(id + "번 명언이 등록되었습니다.");

    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------------");

        WiseSaying[] forListWiseSaying = findForList();
        for(WiseSaying wiseSaying : forListWiseSaying )
        {
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
        }
    }

    private WiseSaying[] findForList() {
        WiseSaying[] forListWiseSayings = new WiseSaying[getSize()];

        int forListWiseSayingsIndex = 0;

        for(int i=wiseSayings.size()-1; i>=0; i--) {
            forListWiseSayings[forListWiseSayingsIndex++] = wiseSayings.get(i);
        }

        return forListWiseSayings;
    }

    WiseSaying write(String content, String author) {
        id++;

        WiseSaying ws = new WiseSaying(id, content, author);
        wiseSayings.add(ws);

        return ws;
    }


}
