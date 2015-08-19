// Make sure to make this class a part of the synthesizer package
//package <package name>;
package synthesizer;

//Make sure to make this class an interface
//interface <interface name> { // methods}
public interface BoundedQueue {

  //FILL IN method definitions but DO NOT implement them
  //ie. int size();
  int capacity();
  int fillCount();
  boolean isEmpty();
  boolean isFull();
  void enqueue(double x);
  double dequeue();
  double peek();
}
