package P1;

import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MagicSquare {
	boolean isLegalMagicSquare(String fileName) throws Exception {
		int i = 0, j = 0;
		int[][] test = new int[10000][10000];
		try {
			File filename = new File(fileName); // 读取fileName路径的文件
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));// 建立输入流对象reader
			BufferedReader br = new BufferedReader(reader);
			String myline = "";
			myline = br.readLine();
			String[] str = myline.split("\t");
			for (j = 0; j < str.length; ++j) {
				test[i][j] = Integer.valueOf(str[j]);
				if (test[i][j] <= 0) { // 不是正整数, 返回false并终止
					System.out.println("存在非正整数,False");
					return false;
				}
			}
			i = 1;
			int high = str.length; // 获取第一行列数
			while (myline != null) {
				myline = br.readLine();
				if (myline == null)
					break;
				++i;
				// System.out.println(myline);
				String[] str1 = myline.split("\t");
				if (high != str1.length) {
					System.out.println("行列数不等, 不是方阵,False");
					return false;
				}
				for (j = 0; j < str1.length; ++j) {
					test[i - 1][j] = Integer.valueOf(str1[j]);
					if (test[i - 1][j] <= 0) { // 不是正整数, 返回false并终止
						System.out.println("存在非正整数,False");
						return false;
					}
				}
			}

			if (i != j) // 不是方阵, 返回异常并终止
			{
				System.out.println("行列数不等,不是方阵,False");
				return false;
			}
			int line = i; // 记录方阵行数
			int sum = 0;
			for (j = 0; j < line; ++j)
				sum += test[0][j];
			final int num = sum;// 记录第一行的和, 并且设置为final常量
			// System.out.println(num);
			for (i = 1; i < line; i++) // 计算每一行的和,并比较
			{
				sum = 0;
				for (j = 0; j < line; j++) {
					sum += test[i][j];
				}
				if (sum != num) {
					System.out.println("行的和不相同,False");
					return false;
				}
			}
			for (j = 1; j < line; ++j) // 计算每一列的和
			{
				sum = 0;
				for (i = 0; i < line; ++i) {
					sum += test[i][j];
				}
				if (sum != num) {
					System.out.println(sum + "列的和不相同,False");
					return false;
				}
				sum = 0;
			}
			sum = 0;

			for (i = 0; i < line; ++i) {// 正对角线
										// System.out.println(test[i][i]+" ");
				sum += test[i][i];
			}
			if (sum != num) {
				System.out.println(sum + "正对角线和不相同,False");
				return false;
			}
			sum = 0;
			for (i = 0; i < line; ++i) // 验证副对角线
			{
				sum += test[i][line - i - 1];

			}
			if (sum != num) {
				System.out.println("负对角线和不相同,False");
				return false;
			}
			System.out.println("符合要求, Right");
			return true;
		} catch (NumberFormatException e) // 不是整数抛出异常
		{
			System.out.println("存在非整数,False");
			return false;
		}
	}

	public static boolean generateMagicSquare(int n) {
		if (n <= 0 || n % 2 == 0) {
			System.out.println("False");
			return false;
		}
		int magic[][] = new int[n][n];
		int row = 0, col = n / 2, i, j, square = n * n;
		for (i = 1; i <= square; i++) {
			magic[row][col] = i;
			if (i % n == 0)
				row++;
			else {
				if (row == 0)
					row = n - 1;
				else
					row--;
				if (col == (n - 1))
					col = 0;
				else
					col++;
			}
		}
		String file = "src/txt/6.txt";
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++)
					out.write(magic[i][j] + "\t");
				out.newLine();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		String file = "src/txt/5.txt";
		MagicSquare ms = new MagicSquare();
		ms.isLegalMagicSquare("src/txt/1.txt");
		ms.isLegalMagicSquare("src/txt/2.txt");
		ms.isLegalMagicSquare("src/txt/3.txt");
		ms.isLegalMagicSquare("src/txt/4.txt");
		ms.isLegalMagicSquare("src/txt/5.txt");
		ms.generateMagicSquare(-1);
		ms.generateMagicSquare(6);
		ms.generateMagicSquare(5);
		ms.isLegalMagicSquare("src/txt/6.txt");
	}
}
