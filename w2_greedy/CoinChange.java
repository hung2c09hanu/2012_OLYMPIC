package w2_greedy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/*
 * @Author: Nguyen Van Hung
 * @Class: 2c09
 * @Version: 1.0
 * @Des: This class is used to solve coin exchange problems.
 * @Precondition: List of candidates are already sorted
 * @selection function: remove all element that large than remain amount to exchanges
 * && select last candidates in list that <= remain amount to exchange
 * @ A feasibility funtion that determin whether or not add a candidate will exceed the total amount of exchange
 * @Solution: check whether completed solution is found
 * @Email:hung2c09hanu@gmail.com
 * @Created Date: 20120922
 * @Modified Date:
 * @Time Elapsed: 25 minutes
 */

public class CoinChange {
	
//List candidate , assum list is sorted increasing order
	
	List<Integer> candidates = new ArrayList<Integer>();//List of candidates , assum sorted
	List<Integer> change = new ArrayList<Integer>();
	int amount;

	public static void main(String [] args){
		
		CoinChange c = new CoinChange();
	System.out.println("Enter amount of money to change");
	
	c.setAmount();
	
	while(c.getAmount() <= 0){
		
		c.setAmount();
		
	}
	
	boolean hasSolution =false;
	
	while(!c.isEmpty() && c.getTotalChangeAmount() < c.getAmount()){
		
		int choose = c.selectCandidate();
		
		while(c.isFeasible(choose)){
		
			c.addCoinToChange(choose);
			
		}
		
		if(c.solution()){
			
			hasSolution = true;
			break;
		}
		else
			c.removeCandiate(choose);
	}
	
	if(hasSolution){
		
		System.out.print(c.getAmount() + " = ");
		
		for(Integer i : c.getChange() ){
			
			System.out.print(i +" ");
			
		}
		
		System.out.println();
		
	}
	
	else
		System.out.print("Fail to exchange "+c.getAmount());
	
	}
	
	public List<Integer> getCandidate(){
		
		return this.candidates;
		
		
	}
	public List<Integer> getChange(){
		
		return this.change;
		
		
	}
	public int getAmount(){
		
		return this.amount;
		
	}
	public void setAmount(){
		
		Scanner sc = new Scanner(System.in);
		amount = sc.nextInt();
		
	}
	public  CoinChange(){
		
		initialCandidates();
		
	}
	public void initialCandidates() {
		
		candidates.add(1);
		candidates.add(2);
		candidates.add(5);
		candidates.add(10);
		candidates.add(25);
		candidates.add(50);
		candidates.add(100);
		candidates.add(1000);
		
	}
/*
 * A select funciton that select best candidate
 */
	public int selectCandidate() {
		
		int remainAmount = amount - getTotalChangeAmount();
		int relVal = 0;
		
		Iterator<Integer> iterator = candidates.iterator();
		
		while(iterator.hasNext()){
			
			int coinVal = iterator.next();
			
			if(coinVal > remainAmount){
				
				iterator.remove();//remove all element that large than remain amount to exchanges
				
			}
			else
			{
				relVal = coinVal;//select last Numbers in list that <= remain amount to exchange
			}
		}
		
		return relVal;
		

	}

	public void removeCandiate(int val){
		
		candidates.remove(new Integer(val));
		
		
	}
	/*
	 * A feasibility funtion that determin whether or not a candidate can be add to solution
	 */
	public boolean isFeasible(int val) {
		
		int tmp = getTotalChangeAmount() + val;
		
		if (tmp <= amount)
			
			return true;
		
		else
			
			return false;
	}
/*
 * Object tive funciton , that assign a candidate to solution
 */
	public void addCoinToChange(int coinVal){
		
		change.add(coinVal);
		
	}
	
	public boolean isEmpty(){
		
		return candidates.isEmpty();
		
	}
	
	public int getTotalChangeAmount() {
		
		int sum = 0;
		/* The for-each construct allows you to concisely traverse a collection or array using a for loop*/
		
		for (Integer i : change) {
			
			sum += i;
			
		}
		return sum;

	}
	
	/*
	 * Solution function that check whether completed solution is found
	 */
	public boolean solution() {
		
		if(getTotalChangeAmount() == amount){
			
			return true;
			
		}
		
		else
			return false;

	}

}
