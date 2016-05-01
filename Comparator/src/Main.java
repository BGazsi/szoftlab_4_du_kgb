import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {

		Diff diff = new Diff();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Path of the output file:");
		String filename1 = br.readLine();

		System.out.print("Path of the reference output file:");
		String filename2 = br.readLine();

		File file1 = new File(filename1);
		File file2 = new File(filename2);
		Scanner s1 = new Scanner(file1);
		Scanner s2 = new Scanner(file2);

		diff.compareTextFiles(s1, s2);

	}
}