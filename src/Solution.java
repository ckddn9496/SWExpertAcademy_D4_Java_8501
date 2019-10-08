import java.util.Scanner;

class Solution {
	public static final int MOD = 1000000007;
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(sc.nextLine());
			
			long numOfHead = 0;
			long factorial = 1;
			if (N > 1) {
				for (int i = 2; i <= N; i++) {
					numOfHead = Math.floorMod((Math.floorMod(i*numOfHead, MOD) + Math.floorMod((i/2)*factorial, MOD)), MOD);

					factorial = Math.floorMod(factorial*i, MOD);
				}
			}
			System.out.println("#"+test_case+" "+numOfHead);
		}
	}
}