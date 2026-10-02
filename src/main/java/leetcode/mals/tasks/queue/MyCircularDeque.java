package leetcode.mals.tasks.queue;

public class MyCircularDeque {

    int[] arr;
    int front;
    int rear;
    int size;

    public MyCircularDeque(int k) {
        this.arr = new int[k];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean insertFront(int value) {

        if (isFull()) {
            return false;
        }

        front = (front - 1 + arr.length) % arr.length;
        arr[front] = value;
        size++;

        return true;
    }

    public boolean insertLast(int value) {

        if (isFull()) {
            return false;
        }

        arr[rear] = value;
        rear = (rear + 1) % arr.length;
        size++;

        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % arr.length;
        size--;

        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        rear = (rear - 1 + arr.length) % arr.length;
        size--;

        return true;

    }

    public int getFront() {
        if (isEmpty()) {
            return -1;
        }

        return arr[front];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        int index = (rear - 1 + arr.length) % arr.length;

        return arr[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }
}
