package solutions;

import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookiesSweetness) {
        Queue<Integer> cookies = new PriorityQueue<>();
        for (int cookie : cookiesSweetness) {
            cookies.offer(cookie);
        }

        int iterations = 0;
        int currentMinSweetness = cookies.peek();

        while (cookies.peek() < requiredSweetness && cookies.size() >= 2) {
            int cookie1 = cookies.poll();
            int cookie2 = cookies.poll();
            int newCookie = cookie1 + (2 * cookie2);
            cookies.offer(newCookie);
            iterations++;
        }

        if (cookies.peek() < requiredSweetness) {
            return -1;
        } else {
            return iterations;
        }
    }
}
