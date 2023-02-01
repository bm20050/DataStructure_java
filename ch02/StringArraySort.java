package ch02;

import java.io.FileOutputStream;
//12장 입출력 작업하기 Test06_2를 수정하여 스트링 정렬하기, Test11/596페이지
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StringArraySort {

	public static void main(String[] args) {

		try {

			// 파일 읽어스 string 객체를 정렬하는 프로그램 구성
			Path input = Paths.get("a.txt");

			byte[] bytes = Files.readAllBytes(input);
			System.out.println("== readAllBytes ==");
			System.out.println(new String(bytes));
			// ------------------- 추가한 코드
			// file 입출력 + String 처리 + 정렬 코딩
			String s = new String(bytes);
			System.out.println("s = " + s);
			String[] sa = s.split(" |\n");

			String temp = "";
			for (int i = 0; i < sa.length - 1; i++) {
				for (int j = i + 1; j < sa.length; j++) {
					if (sa[i].compareTo(sa[j]) > 0) {
						temp = sa[i];
						sa[i] = sa[j];
						sa[j] = temp;
					}
				}
			}
			// ---------------------------

			int bufferSize = 102400;
			String b = " ";
			ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
			for (String sx : sa) {
				System.out.println(" " + sx);
				buffer.put(sx.getBytes());
				buffer.put(b.getBytes());
			}
			buffer.flip();
			FileOutputStream file = new FileOutputStream("c.txt");
			FileChannel channel = file.getChannel();
			channel.write(buffer);
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
