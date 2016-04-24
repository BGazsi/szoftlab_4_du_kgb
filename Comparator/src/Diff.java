import java.util.Scanner;

public class Diff {

	public void compareTextFiles(Scanner s1, Scanner s2) {

		int count1 = 0;
		int count2 = 0;
		Boolean differs = false;
		// while we have lines left in both files, compare and
		// print the lines that don't match
		while (s1.hasNextLine() && s2.hasNextLine()) {
			String line1 = s1.nextLine().trim();
			count1++;
			String line2 = s2.nextLine().trim();
			count2++;
			if (!line1.equalsIgnoreCase(line2)) {
				System.out.println("Line " + count1 + " differs:");
				System.out.println("\t < " + line1);
				System.out.println("\t > " + line2);
				differs = true;
			}
		}

		// any leftover lines in file1 count as differences
		while (s1.hasNextLine()) {
			String line1 = s1.nextLine();
			count1++;
			System.out.println("Line " + count1 + " differs:");
			System.out.println("\t < " + line1);
			System.out.println("\t > ");
			differs = true;
		}

		// any leftover lines in file2 count as differences
		while (s2.hasNextLine()) {
			String line2 = s2.nextLine();
			count2++;
			System.out.println("Line " + count2 + " differs:");
			System.out.println("\t < ");
			System.out.println("\t > " + line2);
			differs = true;
		}

		if (!differs) {
			System.out.println("The two files are identical");
		}
	}
}
