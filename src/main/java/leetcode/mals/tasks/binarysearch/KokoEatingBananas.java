package leetcode.mals.tasks.binarysearch;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {

        int biggestPile = 0;
        for (int p : piles) {
            biggestPile = Math.max(p, biggestPile);
        }

        if (piles.length == h) {
            return biggestPile;
        }

        return searchBound(piles, 1, biggestPile, h);
    }

    public int searchBound(int[] piles, int start, int end, int duration) {

        if (start == end) {
            return start;
        }

        int i = start + (end - start) / 2;

        if (calculateEatingDuration(piles, i) <= duration) {
            return i == start
                    ? start
                    : searchBound(piles, start, i, duration);
        } else {
            return searchBound(piles, i + 1, end, duration);
        }
    }

    private int calculateEatingDuration(int[] piles, int eatInHour) {
        int result = 0;

        for (int p : piles) {
            result += (p + eatInHour - 1)/ eatInHour;
        }
        return result;
    }

}
