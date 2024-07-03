import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Project {
	static List<Ex> li = new ArrayList<>();
	static Scanner scan;

	public static void main(String[] args) throws FileNotFoundException {
		int option = 0;
		scan= new Scanner(new File("./txt/test.txt"));
		while(scan.hasNextLine()) {
			String[] strs = scan.nextLine().split(",");
			li.add(new Ex(strs[0],strs[1]));
		}
		do {
			System.out.println("1. 입력 | 2. 출력 | 3. 상세보기 | 4. 삭제 | 5. 수정 | 6. 종료");
			scan = new Scanner(System.in);
			System.out.print("번호 : ");
			option = scan.nextInt();
			switch (option) {
			case 1:
				insert();
				break;
			case 2:
				print();
				break;
			case 3:
				print_detaild();
				break;
			case 4:
				del();
				break;
			case 5:
				update();
				break;
			case 6:
				PrintWriter pw = new PrintWriter(new File("./txt/test.txt"));
				for(Ex e : li) {
					pw.write(e.toString()+"\n");
					pw.flush();
				}
				System.out.println("저장이 완료되었습니다.");
				break;
			}
		} while (option != 6);
		scan.close();
	}

	public static void print() {
		System.out.println("번호\t제목\t\t\t작성일");
		for (int i = 0; i < li.size(); i++) {
			System.out.printf("%-8d%-20s%-20s\n", i + 1, li.get(i).getTitle(), li.get(i).getRegDate());
		}
	}

	public static void insert() {
		scan.nextLine();
		System.out.print("제목 : ");
		String title = scan.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Ex ex = new Ex(title, sdf.format(new Date()));
		li.add(ex);
		System.out.println("저장이 완료되었습니다.");
	}

	public static void print_detaild() {
		System.out.print("상세 번호 : ");
		int idx = scan.nextInt() - 1;
		System.out.println("번호\t제목\t\t\t작성일");
		Ex ex = li.get(idx);
		System.out.printf("%-8d%-20s%-20s\n", idx + 1, ex.getTitle(), ex.getRegDate());
	}
	
	public static void del() {
		System.out.print("삭제할 번호 : ");
		int idx = scan.nextInt()-1;
		li.remove(idx);
		System.out.println("삭제가 완료되었습니다.");
	}
	
	public static void update() {
		System.out.print("수정할 번호 : ");
		int idx = scan.nextInt()-1;
		scan.nextLine();
		System.out.print("제목 : ");
		String title = scan.nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Ex e = new Ex(title, sdf.format(new Date()));
		li.set(idx, e);
		System.out.println("수정이 완료되었습니다.");
	}

}

class Ex{
	private String title;
	private String regDate;

	public String getTitle() {
		return title;
	}


	public String getRegDate() {
		return regDate;
	}


	public Ex(String title, String regDate) {
		this.title = title;
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return title + "," + regDate;
	}
}