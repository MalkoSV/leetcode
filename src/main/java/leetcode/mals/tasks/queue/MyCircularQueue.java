package leetcode.mals.tasks.queue;

public class MyCircularQueue {

    int[] arr;
    int front;
    int rear;
    int size;

    public MyCircularQueue(int k) {
            this.arr = new int[k];
            this.front = 0;
            this.rear = 0;
            this.size = 0;
    }

    public boolean enQueue(int value) {

        if (isFull()) {
            return false;
        }

        arr[rear] = value;
        rear = (rear + 1) % arr.length;
        size++;

        return true;
    }

    public boolean deQueue() {

        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % arr.length;
        size--;

        return true;
    }

    public int Front() {

        if (isEmpty()) {
            return -1;
        }

        return arr[front];
    }

    public int Rear() {

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
