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
 *	ʵ��Ӧ��:
 *	��д����������У�ʹ�ö�ά�����¼����
 *	Practical application:
 *	Write Gobang program, use two-dimensional array to record the chessboard
 *	
 *	��������:
 *	��Ϊ��ά����intĬ��ֵΪ0����˼�¼�˺ܶ�û��û�����������
 *	Analyze the problem:
 *	Because the default value of the two-dimensional array int is 0,
 *	a lot of data that are meaningless are recorded.
 *
 *	�������ܣ�
 *	��һ�������д󲿷�Ԫ��Ϊ0������Ϊͬһ��ֵ������ʱ������ʹ��ϡ�����������������
 *	Basic introduction:
 *	When most elements in an array are 0 or an array with the same value, 
 *	you can use a sparse array to save the array
 *
 *	����ʽ��
 *	1. ��¼���鹲�м��м��У��ж��ٸ���ͬ��ֵ
 *	2. �Ѿ��в�ֵͬ��Ԫ�ص����м�ֵ��¼��һ��С��ģ�������У��Ӷ���С����Ĺ�ģ
 *	Treatment method:
 *  1. How many rows and columns are there in the record array? How many different values are there
 * 	2. Record the rows, columns and values of elements with different values in a small-scale array, 
 * 	so as to reduce the size of the program
 *
 *	��		��		ֵ
 *	row		column		value
 *
 * @author ��������
 *
 */
public class SparseArray {
	
	public static void main(String[] args) throws IOException {
//		����һ��ԭʼ�Ķ�ά���� 11 * 11		Create an original two-dimensional array 11 * 11
//		0����ʾû�����ӣ� 1 ��ʾ ���� 2 ������		0: indicates no chess, 1 indicates black chess, and 2 indicates blue chess
		int chessArr1[][] = new int[11][11];
		chessArr1[1][2] = 1;
		chessArr1[2][3] = 2;
		chessArr1[4][5] = 2;
//		���ԭʼ�Ķ�ά����		Output the original two-dimensional array
		System.out.println("ԭʼ�Ķ�ά����(Output the original two-dimensional array)");
		for(int[] row : chessArr1) {
			for(int data: row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
		
//		����ά���� ת ϡ������		Convert 2D array to sparse array
//		1. �ȱ�����ά���� �õ���0���ݸ���		1. First traverse the two-dimensional array to get the number of non-0 data
		int sum = 0;
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11;j++) {
				if (chessArr1[i][j] != 0) {
					sum++;
				}
			}
		}
		System.out.println("sum=" + sum);
		
//		2. ������Ӧ��ϡ������		2. Create the corresponding sparse array
		int sparseArr[][] = new int[sum + 1][3];
//		��ϡ�����鸳ֵ������0ֻ�浽sparseArr��		Assign a value to the sparse array, and save non-0 only in sparsearr
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = sum;
		
//		������ά���飬����0��ֵ��ŵ� sparseArr��		Traverse the two-dimensional array and store non-0 values in sparsearr
		int count = 0;//count �Ǽ�¼�ڼ�����0����		Is the number of non-zero data recorded
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
		
//		���ϡ���������ʽ		Output sparse array form
		System.out.println();
		System.out.println("�õ���ϡ������Ϊ(The resulting sparse array is)������������");
		for(int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
		}
		System.out.println();
		/*
		 * 	��ϡ������  ����> �ָ���ԭʼ�Ķ�ά����
		 * 1. �ȶ�ȡϡ������ĵ�һ�У����ݵ�һ�е����ݣ�����ԭʼ�Ķ�ά����
		 * 2. �ڶ�ȡϡ��������е����ݣ�������ԭʼ�Ķ�ά���鼴��
		 * Restore the sparse array - > to the original two-dimensional array
		 * 1. First read the first row of the sparse array and create the original two-dimensional array according to the data of the first row
		 * 2. Read the data of several rows after the sparse array and assign it to the original two-dimensional array
		 */
		
//		1. �ȶ�ȡϡ������ĵ�һ�У����ݵ�һ�е����ݣ�����ԭʼ�Ķ�ά����(Same as 1.)
		
		int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
		
//		2. �ڶ�ȡϡ��������е�����(�ӵڶ��п�ʼ)�������� ԭʼ�Ķ�ά���鼴��(Same as 2.)
		
		for(int i = 1; i < sparseArr.length; i++) {
			chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		
//		����ָ���Ķ�ά����		Output recovered 2D array
		System.out.println();
		System.out.println("�ָ���Ķ�ά����");
		
		for(int[] row : chessArr2) {
			for(int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println();
		}
		System.out.println();

		
//	TODO - mission:Write to sparsearray.date
		BufferedWriter fileWrite = new BufferedWriter(new FileWriter("sparsearray.date"));
//	�еı���	Traversal of rows
		for(int i = 0; i < sparseArr.length; i++) {
//		�еı���	Traversal of column
			for (int j = 0; j < sparseArr[i].length; j++) {
//			���ﵽ����ĩβ������������������������������±��0��ʼ�����length - 1	(If the end of the line is reached, the output will be wrapped. Otherwise, the output will continue. Since the array subscript starts from 0, the length - 1)
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
 * 		sparsearray.datethe escape characters used in are ��\t�� and ��\n��,use \t replace all \n
 *		read to result is String can use replace() method operation
 */
		String numberReplace = readResult.replace("\n", "\t");
		//replace()��ʹ��split()�����ָ��ַ�����ʹ���Ϊ�ַ������飬������еĴ���
		String[] numberSplit = numberReplace.split("\t");
		//�˴�ѭ��Ϊ���������������(��ɾ��)
		for(int k= 0 ; k < numberSplit.length - 1;k++) {
			System.out.println(numberSplit[k]);
		}
		
		
		/*
		 * 	�ָ�֮�����һ��Ԫ���ǿհ���Ҫ�ڴ�������ʱ�����ӵ�(numberSplit.lengthΪ13)
		 * 	��3��ԭ���������ܹ���3��
		 * 	��Ϊ�Ǵ�sparsearray�ж�ȡ����Ϣ
		 * 	�������鴦���õ�������������Ϊ3�ı����������������鴦�����
		 *	After splitting, the last element is blank and needs to be thrown away when creating the array (numbersplit. Length is 13)
		 *	The reason for dividing by 3 is that there are 3 columns in total
		 *	Because it is the information read from SparseArray
		 *	Therefore, the total number of data obtained after array processing must be a multiple of 3. If not, the array processing will make an error
		 */
		int[][] sparseArr = new int[(numberSplit.length - 1 ) / 3][3];
		//�������������õ�������
		for(int i = 0, j = 0; i < numberSplit.length ; i++) {
				//ÿ3��������Ϊһ�е�ֵ����sparseArr
				if(i % 3 == 0 ) {
					/**
					 * 	�ڴ˱���ȫ��ת��Ϊint�ͣ����򱨴�
					 * 	parseInt()��valueOf()����:
					 * 	parseInt()ת��Ϊint����
					 * 	valueOf()ת��Ϊ��Ӧ��װ����
					 * 	����Integerʹ�ø÷���
					 * 	�򷵻�Integer��װ��
					 */
					sparseArr[j][0] = Integer.parseInt(numberSplit[i]);
					sparseArr[j][1] = Integer.parseInt(numberSplit[i+1]);
					sparseArr[j][2] = Integer.parseInt(numberSplit[i+2]);
					//j��Ϊϡ�����������ļ�¼
					j++;
				}
				else if(j >= sparseArr.length) {
					break;
				}
		}
		
		System.out.println("��д����ϡ������(sparse)��������������Read and write sparse array (sparse) output results");
		System.out.println("row\tcolumn\tvalue");
		for(int[] row : sparseArr) {
			for(int date : row) {
				System.out.printf(date+"\t");
			}
			System.out.println();
		}
		
		System.out.println("Ϊԭʼ���鸳ֵ");
		int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
		for(int i = 1; i < sparseArr.length; i++) {
			chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
		}
		
		System.out.println("ͨ����д����ϡ������(sparse)�ó���ԭʼ����(chessArr)��������������The output result of the original array (chessarr) obtained by reading and writing the sparse array (sparse)");
		for(int[] row : chessArr) {
			for(int date : row) {
				System.out.printf("%d\t",date);
			}
			System.out.println();
		}
		
	}
}
