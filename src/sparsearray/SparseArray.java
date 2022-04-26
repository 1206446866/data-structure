package sparsearray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 	Warning : The encoding of document is GBK
 * 	Question: Why use English as document help?
 * 	Answer: Becaues change encoding of document will let chinese document become garbled code,
 * 	this's document is very impotant	
 * 
 *	实际应用:
 *	编写五子棋程序中，使用二维数组记录棋盘
 *	Practical application:
 *	Write Gobang program, use two-dimensional array to record the chessboard
 *	
 *	分析问题:
 *	因为二维数组int默认值为0，因此记录了很多没有没有意义的数据
 *	Analyze the problem:
 *	Because the default value of the two-dimensional array int is 0,
 *	a lot of data that are meaningless are recorded.
 *
 *	基本介绍：
 *	当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组
 *	Basic introduction:
 *	When most elements in an array are 0 or an array with the same value, 
 *	you can use a sparse array to save the array
 *
 *	处理方式：
 *	1. 记录数组共有几行几列，有多少个不同的值
 *	2. 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 *	Treatment method:
 *  1. How many rows and columns are there in the record array? How many different values are there
 * 	2. Record the rows, columns and values of elements with different values in a small-scale array, 
 * 	so as to reduce the size of the program
 *
 *	行		列		值
 *	row		column		value
 *
 * @author 黑岩射手
 *
 */
public class SparseArray {
	
	public static void main(String[] args) throws IOException {
//		创建一个原始的二维数组 11 * 11		Create an original two-dimensional array 11 * 11
//		0：表示没有棋子， 1 表示 黑子 2 表蓝子		0: indicates no chess, 1 indicates black chess, and 2 indicates blue chess
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		chessArr1[4][5] = 2;
//		输出原始的二维数组		Output the original two-dimensional array
		System.out.println("原始的二维数组(Output the original two-dimensional array)");
		for(int[] row : chessArr1) {
			for(int data: row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
		
//		将二维数组 转 稀疏数组		Convert 2D array to sparse array
//		1. 先遍历二维数组 得到非0数据个数		1. First traverse the two-dimensional array to get the number of non-0 data
		int sum = 0;
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11;j++) {
				if (chessArr1[i][j] != 0) {
					sum++;
				}
			}
		}
		System.out.println("sum=" + sum);
		
//		2. 创建对应的稀疏数组		2. Create the corresponding sparse array
		int sparseArr[][] = new int[sum + 1][3];
//		给稀疏数组赋值，将非0只存到sparseArr中		Assign a value to the sparse array, and save non-0 only in sparsearr
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;
		
//		遍历二维数组，将非0的值存放到 sparseArr中		Traverse the two-dimensional array and store non-0 values in sparsearr
		int count = 0;//count 是记录第几个非0数据		Is the number of non-zero data recorded
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11;j++) {
				if (chessArr1[i][j] != 0) {
					count++;
					sparseArr[count][0] = i;
					sparseArr[count][1] = j;
					sparseArr[count][2] = chessArr1[i][j];
				}
			}
		}
		
//		输出稀疏数组的形式		Output sparse array form
		System.out.println();
		System.out.println("得到的稀疏数组为(The resulting sparse array is)——————");
		for(int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}
		System.out.println();
		/*
		 * 	将稀疏数组  ——> 恢复成原始的二维数组
		 * 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
		 * 2. 在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
		 * Restore the sparse array - > to the original two-dimensional array
		 * 1. First read the first row of the sparse array and create the original two-dimensional array according to the data of the first row
		 * 2. Read the data of several rows after the sparse array and assign it to the original two-dimensional array
		 */
		
//		1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组(Same as 1.)
		
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		
//		2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组即可(Same as 2.)
		
		for(int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		
//		输出恢复后的二维数组		Output recovered 2D array
		System.out.println();
		System.out.println("恢复后的二维数组");
		
		for(int[] row : chessArr2) {
			for(int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
		System.out.println();

		
//	TODO - mission:Write to sparsearray.date
		BufferedWriter fileWrite = new BufferedWriter(new FileWriter("sparsearray.date"));
//	行的遍历	Traversal of rows
		for(int i = 0; i < sparseArr.length; i++) {
//		列的遍历	Traversal of column
			for (int j = 0; j < sparseArr[i].length; j++) {
//			若达到该行末尾则换行输出，否则继续输出，由于数组下标从0开始，因此length - 1	(If the end of the line is reached, the output will be wrapped. Otherwise, the output will continue. Since the array subscript starts from 0, the length - 1)
				if(j == sparseArr[i].length - 1) {
					fileWrite.write(sparseArr[i][j] + "\n");
				}else {
					fileWrite.write(sparseArr[i][j] + "\t");
				}
			}
		}
		fileWrite.close();
		
		READ("sparsearray.date");
		
	}
	
	public static void READ(String text) throws IOException  {
//	TODO - mission:Read to sparsearray.date restore original array
		BufferedReader bufferedRead = new BufferedReader(new FileReader(text));
		String readResult = null;
		char[] bufferedArray = new char[1024];
		while(bufferedRead.read(bufferedArray, 0, bufferedArray.length) != -1) {
			readResult = new String(bufferedArray, 0, bufferedArray.length);
		}
		bufferedRead.close();
/*
 * 		sparsearray.datethe escape characters used in are “\t” and “\n”,use \t replace all \n
 *		read to result is String can use replace() method operation
 */
		String numberReplace = readResult.replace("\n", "\t");
		//replace()后使用split()方法分割字符串，使其成为字符串数组，完成所有的处理
		String[] numberSplit = numberReplace.split("\t");
		//此处循环为处理后数组结果检验(可删除)
		for(int k= 0 ; k < numberSplit.length - 1;k++) {
			System.out.println(numberSplit[k]);
		}
		
		
		/*
		 * 	分割之后最后一个元素是空白需要在创建数组时将其扔掉(numberSplit.length为13)
		 * 	除3的原因是列数总共有3列
		 * 	因为是从sparsearray中读取的信息
		 * 	所以数组处理后得到的数据总数必为3的倍数，若不是则数组处理出错
		 *	After splitting, the last element is blank and needs to be thrown away when creating the array (numbersplit. Length is 13)
		 *	The reason for dividing by 3 is that there are 3 columns in total
		 *	Because it is the information read from SparseArray
		 *	Therefore, the total number of data obtained after array processing must be a multiple of 3. If not, the array processing will make an error
		 */
		int[][] sparseArr = new int[(numberSplit.length - 1 ) / 3][3];
		//遍历经过处理后得到的数组
		for(int i = 0, j = 0; i < numberSplit.length ; i++) {
				//每3个数据作为一行的值赋给sparseArr
				if(i % 3 == 0 ) {
					/**
					 * 	在此必须全部转换为int型，否则报错
					 * 	parseInt()与valueOf()区别:
					 * 	parseInt()转换为int类型
					 * 	valueOf()转换为对应包装类型
					 * 	若是Integer使用该方法
					 * 	则返回Integer包装类
					 */
					sparseArr[j][0] = Integer.parseInt(numberSplit[i]);
					sparseArr[j][1] = Integer.parseInt(numberSplit[i+1]);
					sparseArr[j][2] = Integer.parseInt(numberSplit[i+2]);
					//j作为稀疏数组行数的记录
					j++;
				}
				else if(j >= sparseArr.length) {
					break;
				}
		}
		
		System.out.println("读写出的稀疏数组(sparse)输出结果————Read and write sparse array (sparse) output results");
		System.out.println("row\tcolumn\tvalue");
		for(int[] row : sparseArr) {
			for(int date : row) {
				System.out.printf(date+"\t");
			}
			System.out.println();
		}
		
		System.out.println("为原始数组赋值");
		int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
		for(int i = 1; i < sparseArr.length; i++) {
			chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		
		System.out.println("通过读写出的稀疏数组(sparse)得出的原始数组(chessArr)输出结果————The output result of the original array (chessarr) obtained by reading and writing the sparse array (sparse)");
		for(int[] row : chessArr) {
			for(int date : row) {
				System.out.printf("%d\t",date);
			}
			System.out.println();
		}
		
	}
}
