/*
* HeapPriorityQueue.java
*
* An implementation of a minimum PriorityQueue using a heap.
* based on the implementation in "Data Structures and Algorithms
* in Java", by Goodrich and Tamassia
*
* This implementation will throw a Runtime, HeapEmptyException
*	if the heap is empty and removeLow is called.
*
* This implementation will throw a Runtime, HeapFullException
*	if the heap is full and insert is called.
*
*/
@SuppressWarnings({"rawtypes", "unchecked"})
public class HeapPriorityQueue implements PriorityQueue {

	protected final static int DEFAULT_SIZE = 10000;
	
	protected Comparable[] storage;
	protected int currentSize;

	/*
	 * Constructor that initializes the array to hold DEFAULT_SIZE elements
	 */
	public HeapPriorityQueue() {
		// TODO: implement this
		storage = new Comparable[DEFAULT_SIZE+1];
		currentSize = 0;
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
	}
	
	/*
	 * Constructor that initializes the array to hold size elements
	 */
	public HeapPriorityQueue(int size) {
		// TODO: implement this
		storage = new Comparable[size+1];
		currentSize = 0;
		// if using a 1-based implementation, remember to allocate an 
		// extra space in the array since index 0 is not used. 
	}

	public void insert (Comparable element) throws HeapFullException {
		// TODO: implement this
		if(isFull()){
			throw new HeapFullException();
		}
		// adds one to the index
		currentSize++;
		// adds the element to the the array
		storage[currentSize] = element;

		// starts bubbleUp in order to ascertain whether it is in the correct placement;
		bubbleUp(currentSize);



		// When inserting the first element, choose whether to use 
		// a 0-based on 1-based implementation. Whatever you choose,
		// make sure your implementation for the rest of the program
		// is consistent with this choice.		
    }
	
	public void bubbleUp(int index) {
		// TODO: implement this
		// checks to see if the item at the index has a parent
		//if not returns
		if(index <=1) {
			return;
		}
		// gets the parent of the element
		Comparable parent = storage[index/2];
		// compares the child to the parent
		// if the child is less than the parent returns
		if(storage[index].compareTo(parent) > 0){
			return;
		}// if the child is greater than the parent then switches the parents and childs places in teh line
		else if (storage[index].compareTo(parent) < 0){
			swap(index/2, index);
			bubbleUp(index/2);
		}// otehrwise if the parent and child are equal to each other, keeps thelist how it was.
		else{
			return;
		}
	
		
	}
	private void swap(int indexHigh, int indexLow){
		// index high  is the one that is ranked higher in the list
		// indexLow is the one that is ranked lower in the list
		
		Comparable tmp = storage[indexLow]; // stores the value from the lower index
		storage[indexLow] = storage[indexHigh]; // switches the value from the higher index to the lower. ( parent to the childs position)
		storage[indexHigh] = tmp; // switches the lower value from the lower index into the higher index.
	}
			
	public Comparable removeMin() throws HeapEmptyException {
		// TODO: implement this
		if (isEmpty()){
			throw new HeapEmptyException();
		}
		Comparable oldRoot = storage[1];
		if(size() == 1){

			currentSize--;
			return oldRoot;
		}


		swap(1, currentSize);
		
		currentSize--;
		
		bubbleDown(1);
		return oldRoot; // so it compiles
	}
	
	private void bubbleDown(int index) {
		
		if(index*2 <=size()){
			int minChildIndex = minChild(index);
			Comparable higher = storage[index];
			Comparable lower = storage[minChildIndex];

			if(higher.compareTo(lower) > 0){
				swap(index, minChildIndex);
				bubbleDown(minChildIndex);
			}
			else{
				return;
			}

		}else{
			return;
		}
	}
	private int minChild(int parentIndex){
		int leftChild = parentIndex*2;
		int rightChild = parentIndex*2+1;
		// checks if there is at least the left child
		if(leftChild <= size() && rightChild > size()){

			return leftChild;
		}// now compares the rest of them to see which one is lower
		else if(leftChild <= size() && rightChild <= size()){
			Comparable left = storage[leftChild];
			Comparable right = storage[rightChild];

			if(left.compareTo(right) < 0){
				return leftChild;
			}
			else{
				return rightChild;
			}

		}
		return 2;

	}

	public boolean isEmpty(){
		// TODO: implement this
		// if there are no elements in the heap
		return currentSize == 0; // so it compiles
	}
	
	public boolean isFull() {
		
		// if all of the positions in the aray are filled
		return size() == storage.length -1; // so it compiles
	}
	
	public int size () {
		// TODO: implement this
		// returns the number of elements in the array
		return currentSize; // so it compiles
	}

	public String toString() {
		String s = "";
		String sep = "";
		// This implementation of toString assumes you 
		// are using a 1-based approach. Update the initial
		// and final value for i if using a 0-based
		for(int i=1; i<=currentSize; i++) {
			s += sep + storage[i];
			sep = " ";
		}
		return s;
	}
}
