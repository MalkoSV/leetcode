package leetcode.mals.tasks.stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockSpanner {
    private Deque<Stock> stack;

    public StockSpanner() {
        this.stack = new ArrayDeque<>();
    }

    public int next(int price) {

        int span = 1;

        while (!stack.isEmpty() && price >= stack.peek().price) {
            span += stack.pop().span;
        }

        stack.push(new Stock(price, span));

        return span;
    }

    private static class Stock {
        final int price;
        final int span;

        public Stock(int price, int span) {
            this.price = price;
            this.span = span;
        }
    }
}
