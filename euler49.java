package euler;
/* 
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms 
 * increases by 3330, is unusual in two ways: (i) each of the three terms 
 * are prime, and, (ii) each of the 4-digit numbers are 
 * permutations of one another.

There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, 
exhibiting this property, but there is one other 4-digit increasing sequence.

What 12-digit number do you form by concatenating the three terms in this sequence?
 */

public class euler49{
	// make 1D array with all the primes from 1000 to 9999
	static int [] primeList = getPrimes(1000, 9999); 
	
	public static void main(String[] args) {
		comparePrimes();
	}
	
	public static void comparePrimes(){
		// declare 4 part arrays for the three primes that will be compared
		int [] firstDigits = new int [4];
		int [] secondDigits = new int [4];
		int [] thirdDigits = new int [4];
		
		// try every prime in the primeList array
		for (int first = 0; first < primeList.length; first++){
			firstDigits = getDigits(primeList[first]); // get the digits of primeList[i], first prime
			
			for(int second = first + 1; second < primeList.length; second++){
				secondDigits = getDigits(primeList[second]); // get digits of second prime
				
				// do the first and second prime have the same digits?
				if (compareDigits(firstDigits, secondDigits) == true){
					// System.out.println(primeList[first] + " and " + primeList[second]);
					// then check every larger prime to see if it has the same as the first two
					for(int third = second + 1; third < primeList.length; third++){
						thirdDigits = getDigits(primeList[third]);
						if (compareDigits(firstDigits, thirdDigits) == true){ // if they all have the same digits
							shipEm(primeList[first], primeList[second], primeList[third]);
						}
					}
				}
			}
		}
	}
	
	public static void shipEm(int a, int b, int c){
		if (b - a == c - b){
			System.out.println("we found it! " + a + b + c);
		}
	}
	
	public static boolean compareDigits(int first[], int second[]){
		int sameCount = 0; // sameCount must be 4 if all digits are the same
		boolean isSame; 
		// for digit i in first, does second contain digit i?
		for(int i = 0; i < first.length; i++){
			isSame = false; // reset the boolean that tells whether first[i] found a match
			for (int k = 0; k < second.length && isSame == false; k++){
				// same digit?
				if (first[i] == second[k]){ 
					isSame = true;
					second[k] = 11; // second[k] cant be used any more, no duplicates
					sameCount++;
				}
			}
		}
		// were they all the same?
		if (sameCount == 4){
			return true;
		}
		return false; // not the same digits
	}
	
	public static int[] getDigits(int primeNumber){
		int [] digits = new int [4];
		for(int i = 3; i >= 0; i--){
			digits[i] = primeNumber % 10; // gather the last digit of primeNumber
			primeNumber = primeNumber / 10; // remove the last digit of primeNumber
		}
		return digits;		
	}
	
	public static int[] getPrimes(int a, int b){ 
		// return an array int with all the primes between a and b
		int primeCount = primeCount(a, b); // returns number of primes between a:b
		int [] primeList = new int [primeCount]; // array with an indicie for every a:b prime

		int primeIndexCount = 0; // for the incoming for loop

		for (int i = a; i <= b; i++){
			if (checkPrime(i) == true){
				primeList[primeIndexCount] = i;
				primeIndexCount++;
			}
		}
		return primeList;
		
	}
	
	public static int primeCount(int a, int b){
		// finds the number of primes in a given range a to b and returns the number
		int primeCount = 0;
		for (int i = a; i <= b; i++){
			if (checkPrime(i) == true){
				primeCount++;
			}
		}
		return primeCount;			
	}
	
	public static boolean checkPrime(long x){
		// returns true if x is a prime number
		if (x == 2 || x == 3 || x == 5) { return true; } 
		if (x > 2 && x % 2 == 0) { return false; } // divisible by 2?
		if (x > 3 && x % 3 == 0) { return false; } // divisible by 3?
		if (x > 5 && x % 5 == 0) { return false; } // divisible by 5?
		for(long i = 7; i <= Math.sqrt(x); i = i + 2){ // divisible by an odd above 5?
			if(x % i == 0){
				return false;
			}
		} // for loop only fully completes if x is a prime number
		return true; // only evaluates if for loop completes
	}	
}