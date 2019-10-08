# SWExpertAcademy_D4_Java_8501

## SW Expert Academy D4 8501. 은비의 동전 뒤집기

### 1. 문제설명

출처: https://swexpertacademy.com/main/code/problem/problemList.do

`1`에서 `N`까지의 수가 적힌 동전 N 개가 모두 뒷면을 보이며 일렬로 나열되어 있을때, 일렬로 나열하여 가장 왼쪽 동전에서 시작하여 오른쪽으로 가면서 동전을 하나씩 본다고 한다. 현재 보고있는 동전의 수가 `x`라고 할 때, 현재 보고있는 동전보다 오른쪽에 있는 모든 동전에 대해 그 동전에 적힌 수가 `x`보다 큰 경우 그 큰 동전을 뒤집는다. `N`개의 모든 동전배치 `N!`가지에 대하여 모든 경우에 대해 최종적으로 앞면을 보이는 동전의 개수의 총 합을 출력하는 문제.

[입력]
> 첫 번째 줄에 테스트 케이스의 수 `T`가 주어진다.
> 각 테스트 케이스의 첫 번째 줄에는 하나의 정수 `N(1≤N≤10^3)`이 주어진다.

[출력]
> 각 테스트 케이스마다 `#x`(x는 테스트케이스 번호를 의미하며 `1`부터 시작한다)를 출력하고,
> 각 테스트 케이스마다 앞면을 보이는 동전의 총 개수를 출력한다.
> 이 수가 매우 클 수 있으므로, `1,000,000,007`로 나눈 나머지를 출력하도록 한다.

### 2. 풀이

`a(n)`을 `n`개의 동전을 이용하였을 때 총 앞면의 개수라 할때 `a(n) =  n * a(n-1) + [n/2] * (n-1)!`을 따른다.
이 식을 이용하여 구현하면된다. 식의 대한 설명은 아래에 있다.

```java
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
					numOfHead = Math.floorMod(
							(Math.floorMod(i*numOfHead, MOD) + Math.floorMod((i/2)*factorial, MOD))
							, MOD);

					factorial = Math.floorMod(factorial*i, MOD);
				}
			}
			System.out.println("#"+test_case+" "+numOfHead);
		}
	}
}
```

### 3. 식의 유도과정

**`a(n) =  n * a(n-1) + [n/2] * (n-1)!`**

#### 1) `n * a(n-1)`

`n-1`에서 `n`번째로 이어질 때를 동전 `n-1`개를 이용하여 만든 `(n-1)!`개의 조합에 `n`번째 동전이 들어갈 수 있는 자리 `n`개에 넣는다고 생각하였다. `n`번째 동전이 나열된 동전 중 가장 큰 값을 가지는 동전이기 때문에 위의 동전으로 인해 뒤집어지는 동전인 존재하지 않는다. 그렇기 때문에 `a(n-1)`을 그대로 사용가능하며 동전`n`이 `n`번 들어갈 수 있으므로 `1`부터 `n-1`까지의 동전으로 인하여 `n * a(n-1)`개의 앞면을 갖는 동전이 나오게 된다.

#### 2) `[n/2]*(n-1)!`

`n`번째 동전보다 큰 동전은 존재하지 않는다. `n`번째 동전 왼쪽에 홀수개의 동전이 존재한다면 `n`번째 동전은 최종적으로 앞면이 된다. `(n-1)!`개의 조합중 홀수개의 동전이 왼쪽에 존재할 경우는 `[n/2]`개이므로 `[n/2]*(n-1)!`개의 `n`번째 동전이 앞면을 갖게된다. 

이 두 경우를 더하여 식을 유도하였다.
