package w2_greedy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
 * @Author: Nguyen Van Hung
 * @Class: 2c09
 * @Version: 1.0
 * @Des: This class is used to find the maximum number of activites using gready algorithm
 * @selection function & fesible function: 
 * select first activites => select activites that have start-time > finished time of 
 * previous activities. => add added actities as last activites add to solution
 * @object tive function: add activities to solution
 * @Email:hung2c09hanu@gmail.com
 * @Created Date: 20120922
 * @Modified Date:
 * @Time Elapsed: 25 minutes
 */
public class ActivitySelector {
	static Scanner sc = new Scanner(System.in);

	int[][] s;// set of activites
	int n;// number of activites
	List<Integer> a = new ArrayList<Integer>();// solution list to store  activites number
												

	public static void main(String[] args) {

		List<Integer> result = new ArrayList<Integer>();
		ActivitySelector as = new ActivitySelector();
		result = as.greedyActivitySelector();

		Iterator<Integer> it = result.iterator();

		while (it.hasNext()) {

			System.out.println(it.next());

		}

	}

	public List<Integer> greedyActivitySelector() {

		int last = 0;

		a.add(1);// add 1st act = candidates to solution set

		for (int i = 1; i < n; i++) {

			/*
			 * select best candidate by finished date && also feasibility
			 * function to check candidate
			 */

			if (s[i][0] >= s[last][1]) {

				a.add((i + 1));// object tive function add candidates to
								// solution set
				last = (i);
			}
		}

		return a;

	}

	/*
	 * Function to initial set of task s
	 */
	public void initialActivities() {

		s = new int[n][2];
		int start = 0;
		int finish = 0;

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < 2; j++) {

				if (j == 0) {

					System.out.println("Starting time for activity " + (i + 1));
					start = sc.nextInt();

					while (!isValidate(start)) {

						System.out
								.println("Invalidate Starting time for activity "
										+ (i + 1));
						start = sc.nextInt();

					}

					s[i][j] = start;

				} else {

					System.out
							.println("Finishing time for activity " + (i + 1));
					finish = sc.nextInt();

					while (!isValidate(finish) || finish < start) {

						System.out
								.println("Invalidate Starting time for activity "
										+ (i + 1));

						finish = sc.nextInt();

					}

					s[i][j] = finish;
				}

			}
		}
	}

	public ActivitySelector() {

		getNumberOfActivites();
		initialActivities();

	}

	// validate date
	public boolean isValidate(int date) {

		if (date <= 0 || date >= 32)

			return false;

		else

			return true;
	}

	public int getNumberOfActivites() {

		System.out.println("0 < input number of activies <=100");

		n = sc.nextInt();

		while (n <= 0 || n >= 100) {

			System.out.println("0 < input number of activies <=100");

		}

		return n;

	}

	public void printActivities() {

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < 2; j++) {

				if (j == 0) {

					System.out.println("Starting time for activity " + (i + 1)
							+ " : " + s[i][j]);

				} else {

					System.out.println("Finishing time for activity " + (i + 1)
							+ " : " + s[i][j]);

				}

			}
		}
	}
}
