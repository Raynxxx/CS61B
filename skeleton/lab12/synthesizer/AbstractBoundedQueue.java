// Make sure to make this class a part of the synthesizer package
//package <package name>;
package synthesizer;

//Make sure to make this class and all of its methods public
//Make sure to make this class and abstract class which implements BoundedQueue
public abstract class AbstractBoundedQueue implements BoundedQueue {

  //declare size variable
  public int fillCount;
  public int capacity;

  //Fill in the BoundedQueue methods we've decided to implement

  // return size variable
  public int fillCount() {
    return fillCount;
  }

  // check if size variable is empty
  public boolean isEmpty() {
    return (fillCount == 0);
  }

  public int capacity() {
    return capacity;
  }

  // check if size variable is equal to our length
  public boolean isFull() {
    return (fillCount == capacity);
  }

}
