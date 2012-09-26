package w2_greedy;
import java.util.*;
import java.io.*;
/*
 * @Author: Nguyen Van Hung
 * @Class: 2c09
 * @Version: 1.0
 * @Des: This class is used to find the minimum numbers of stalls to block(ensure stall have a cow is blocked) using gready algorithm
 * @Precondition: Read M (max boards), S( nums of stalls), C(num of cows), and cows numbers
 * Initial boolean list: index = stall numbers, value = false ( initial all stalls are empty)
 * Read cow'stall numbers => set stall that index =number to true( indicate stall have a cow)
 * @selection function & fesible function: 
 * best choice = Select longest continuous false value ( stall have no cow) and remove. The number of false' set to remove = max board -1
 * @objective function: remove longest continous false val from solution
 * @Fesible
 * @Solution function: 
 * @Email:hung2c09hanu@gmail.com
 * @Created Date: 20120922
 * @Modified Date:
 * @Time Elapsed: 25 minutes
 */
public class barn {
	private static int M;// Maximum number of boards
	private static ArrayList<Boolean> list;

	public static void main(String[] args) throws IOException {
		// Remove all empty stall
		list = removeEndBlanks(setUpList());

		M--;// use 1 board
		// The number of largest blank stall to remove equal = M-1
		for (int i = 0; i < M; i++) {
			// Remove three largest blank
			list = removeLargestBlank1(list);
		}

		int ans = list.size();

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"C:/Users/hung2c09hanu/workspace/OLP_W1/src/w2_greedy/barn.out")));
		out.println(ans);
		out.close();
		System.out.println(ans);

		System.exit(0);
	}

	/*
	 * This function will find the maximum number of continuous false value in
	 * array and remove these After remove these we will have better array ,
	 * this is feasible stalls 0:true 1 :true 2 :false 3 :true 4 :false 5 :true
	 * 6 :false 7 :false 8 :false 9 :false 10 :false 11 :true 12 :true 13 :true
	 * 14 :true 15 :false 16 :false 17 :false 18 :true 19 :false 20 :false 21
	 * :false 22 :true 23 :true 24 :true 25 :false 26 :false 27 :true 28 :true
	 * 29 :false 30 :false 31 :false 32 :false 33 :false 34 :false 35 :false 36
	 * :false 37 :true 38 :true 39 :true 40 :true
	 * 
	 * @Param s, Array list contain the feasible stalls (stall that may be
	 * board)
	 */
	public static ArrayList<Boolean> removeLargestBlank1(ArrayList<Boolean> s) {
		// The maximum number of false value in array
		int maxFalseVal = 0;
		// maxBank[0]: position of false value in longest continuos false val
		// maxBlank[1]: pos of the first true value after longest contious false
		// val
		// ex: t (f f f f t)
		int maxBlank[] = new int[2];
		// bank[0]: pos false value find in s array
		// blank[1]: post the first true value
		// ex: t (f f f f t)
		int blank[] = new int[2];
		// value of current element
		boolean current = true;
		// maximum number of false val

		for (int i = 0; i < s.size(); i++) {
			// find false value which has previous value is true
			if (current && !s.get(i)) {
				blank[0] = i;
			} else if (!current && s.get(i)) {// find true value after
												// continuous false val.
				blank[1] = i;
				int tmp = blank[1] - blank[0];

				if (tmp > maxFalseVal) {

					maxFalseVal = tmp;

					maxBlank[0] = blank[0];
					maxBlank[1] = blank[1];
				}

			}
			current = s.get(i);// current value
		}

		int n = maxBlank[1] - maxBlank[0];

		for (int i = 0; i < n; i++) {

			s.remove(maxBlank[0]); // remove all false position ( after each
									// remove, the size of s--)
		}
		System.out.println("removeLargestBlank" + maxBlank[0] + " - "
				+ maxBlank[1]);
		return s;

	}

	/*
	 * Function that return a list, index is the stall number, value is status
	 * of stall Empty stall => false Stall have a cow => true
	 */
	public static ArrayList<Boolean> setUpList() throws IOException {
		BufferedReader f = new BufferedReader(new FileReader(
				"C:/Users/hung2c09hanu/workspace/OLP_W1/src/w2_greedy/barn.in"));

		// Constructs a string tokenizer for the specified string.
		// The tokenizer uses the default delimiter set, which is " \t\n\r\f":
		StringTokenizer str = new StringTokenizer(f.readLine());

		M = Integer.parseInt(str.nextToken());//maximum number of board
		int S = Integer.parseInt(str.nextToken());//number of stalls
		// System.out.println("S = " + S);
		int C = Integer.parseInt(str.nextToken());//number of cows

		// initial list with capacity = number of stalls
		list = new ArrayList<Boolean>(S);

		for (int i = 0; i < S; i++) {

			// mark all stalls as empty = no cow inside
			list.add(false);

		}
		// System.out.println(list.size());

		for (int i = 0; i < C; i++)// loop through each cow number in the file
		{
			// Mark stall number that has a count as true (f.readLine()) - 1
			// because index start from 0)
			list.set(Integer.parseInt(f.readLine()) - 1, true);

		}
		f.close();

		return list;
	}

	public static ArrayList<Boolean> removeEndBlanks(ArrayList<Boolean> s) {
		while (!s.isEmpty())// remove blanks in the front of the first
							// containing stall
		{
			if (!s.get(0))
				s.remove(0);
			else
				break;
		}

		while (!s.isEmpty())// remove blanks in the back last containing stall
		{
			if (!s.get(s.size() - 1))
				s.remove(s.size() - 1);
			else
				break;
		}
		// System.out.println("removeEndBlanks");
		// printArray(s);
		return s;
	}

	public static void printArray(ArrayList<Boolean> l) {
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i))
				System.out.print("1 ");
			else
				System.out.print("0 ");
		}
	}

}
