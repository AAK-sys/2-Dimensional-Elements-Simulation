// TO DO: add your implementation and JavaDocs.

import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {
	private static final int INITCAP = 2; // default initial capacity
	private T[] storage; // underlying array, you MUST use this for credit (do not change the name or
							// type)
	private int size;
	private int capacity;

	@SuppressWarnings("unchecked")
	public DynamicArray() {
		// constructor
		// Initial capacity of the storage should be INITCAP
		size = 0;
		capacity = INITCAP;
		storage = (T[]) new Object[INITCAP];
	}

	@SuppressWarnings("unchecked")
	public DynamicArray(int initCapacity) {
		// constructor
		if (initCapacity < 1) {
			throw new IllegalArgumentException("Capacity cannot br zero or negative");
		}
		// The initial capacity of the storage should be initCapacity
		storage = (T[]) new Object[initCapacity];
		size = 0;
		capacity = initCapacity;
		// Throw IllegalArgumentException if initCapacity < 1
		// Use this _exact_ error message for the exception
		// (quotes are not part of the message):
		// "Capacity cannot be zero or negative."
	}

	public int size() {
		// Report the current number of elements.
		// O(1)

		return size;
	}

	public int capacity() {
		// Report the max number of elements before expansion.
		// O(1)

		return capacity;
	}

	public T set(int index, T value) {
		// Change the item at the given index to be the given value.
		// Return the old item at that index.
		// Note: You cannot add new items with this method.
		T temp;
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("Index" + index + "out of bounds!");
		}
		// O(1)
		temp = storage[index];
		storage[index] = value;
		// For an invalid index, throw an IndexOutOfBoundsException
		// Use this code to produce the correct error message for
		// the exception (do not use a different message):
		// "Index " + index + " out of bounds!"

		return temp;
	}

	public T get(int index) {
		// Return the item at the given index
		if (index > size() || index < 0) {
			throw new IndexOutOfBoundsException("Index" + index + "out of bounds!");
		}
		// O(1)

		// Use the exception (and error message) described in set()
		// for invalid indicies.

		return storage[index];
	}

	public boolean add(T value) {
		// Append an element to the end of the list and return true.
		size++;
		checkCapacity();
		// Double the capacity if no space available.
		storage[size() - 1] = value;
		// Amortized O(1)

		return true;
	}

	public void add(int index, T value) {
		if (index > size() || index < 0) {
			throw new IndexOutOfBoundsException("Index" + index + "out of bounds!");
		}
		size++;
		checkCapacity();

		for (int i = size() - 2; i > index; i--) {
			storage[i] = storage[i - 1];
		}

		storage[index] = value;
		// Insert the given value at the given index. Shift elements if needed,
		// double capacity if no space available, throw an exception if you cannot
		// insert at the given index. You _can_ append items with this method.

		// For the exception, use the same exception and message as set() and
		// get()... however remember that the condition of the exception is
		// different (different indexes are invalid).

		// O(N) where N is the number of elements currently in the list
	}

	public T remove(int index) {
		if (index >= size() || index < 0) {
			throw new IndexOutOfBoundsException("Index" + index + "out of bounds!");
		}
		// Remove and return the element the given index. Shift elements
		// to remove the gap. Throw an exception when there is an invalid
		// index (see set(), get(), etc. above).

		T temp = storage[index];

		for (int i = index; i < size() - 1; i++) {
			storage[i] = storage[i + 1];
		}
		storage[size() - 1] = null;

		size--;
		checkCapacity();
		// Halve capacity of the storage if the number of elements falls
		// below 1/3 of the capacity.

		// O(N) where N is the number of elements currently in the list

		return temp;
	}

	public Iterator<T> iterator() {
		// Uses an anonymous class style, complete the iterator code
		// below. Note that this uses the "diamond syntax" which is
		// only available for nested classes from Java 9 forward.
		// If you get an error on the next line you can add a <T>
		// betwen the <> or you can (and should) update your
		// version of the JDK.

		return new Iterator<>() {
			// instance variables here
			int current = 0;
			// only _required_ methods are outlined below
			// the interface also has some optional methods
			// you may implement if you find them helpful

			public T next() {
				// your code here
				return storage[current++];
			}

			public boolean hasNext() {
				// your code here
				return !(size() >= current);
			}
		};
	}

	/**
	 * a function that will check for the capacity and change it if need be, called
	 * at every addition or deletion.
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity() {

		T[] tempList = (T[]) new Object[capacity];

		if (size() - 1 == capacity()) {

			for (int i = 0; i < capacity(); i++) {

				tempList[i] = storage[i];

			}

			storage = (T[]) new Object[capacity * 2];

			for (int j = 0; j < size() - 1; j++) {

				storage[j] = tempList[j];

			}

			capacity = capacity() * 2;

		}
		if (size() == (capacity / 3)) {

			for (int i = 0; i < capacity(); i++) {

				tempList[i] = storage[i];

			}

			storage = (T[]) new Object[capacity / 2];

			for (int j = 0; j < capacity() / 2; j++) {

				storage[j] = tempList[j];

			}

			capacity = capacity() / 2;

		}
	}

	// ******************************************************
	// ******* BELOW THIS LINE IS TESTING CODE *******
	// ******* Edit it as much as you'd like! *******
	// ******************************************************

	public String toString() {
		// This method is provided for debugging purposes
		// (use/modify as much as you'd like), it just prints
		// out the list ifor easy viewing.
		StringBuilder s = new StringBuilder("Dynamic array with " + size()
				+ " items and a capacity of " + capacity() + ":");
		for (int i = 0; i < size(); i++) {
			s.append("\n  [" + i + "]: " + get(i));
		}
		return s.toString();

	}

	// JavaDoc note: How do you document a main? See Simulation.java for an example
	public static void main(String args[]) {
		// These are _sample_ tests. If you're seeing all the "yays" that's
		// an excellend first step! But it might not mean your code is 100%
		// working... You may edit this as much as you want, so you can add
		// own tests here, modify these tests, or whatever you need!

		DynamicArray<Integer> ida = new DynamicArray<>();
		if ((ida.size() == 0) && (ida.capacity() == 2)) {
			System.out.println("Yay 1");
		}

		boolean ok = true;
		for (int i = 0; i < 3; i++)
			ok = ok && ida.add(i * 5);

		if (ok && ida.size() == 3 && ida.get(2) == 10 && ida.capacity() == 4) {
			System.out.println("Yay 2");
		}

		ida.add(1, -10);
		ida.add(4, 100);
		if (ida.set(1, -20) == -10 && ida.get(2) == 5 && ida.size() == 5
				&& ida.capacity() == 8) {
			System.out.println("Yay 3");
		}
		// System.out.println(ida.remove(0));
		if (ida.remove(0) == 0 && ida.remove(0) == -20 && ida.remove(2) == 100
				&& ida.size() == 2 && ida.capacity() == 4) {
			System.out.println("Yay 4");
		}

		ida.add(34);
		ida.add(35);
		ida.remove(1);

		System.out.print("Printing values: ");
		for (Integer i : ida) {
			System.out.print(i);
			System.out.print(" ");

		}
		System.out.println(ida.toString());
	}
}