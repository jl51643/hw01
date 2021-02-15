package hr.fer.oprpp1.custom.collections;

/**
 * Model which represents some general collection of objects.
 */
public class Collection {

    /**
     * Checks if collection is empty.
     *
     * @return true if collection contains no objects and false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of currently stored objects in this collection.
     *
     * @return number of currently stored objects in this collection.
     */
    public int size() {
        return 0;
    }

    /**
     * Adds the given object into this collection.
     *
     * @param value object to add in collection.
     */
    public void add(Object value) {

    }

    /**
     * Returns true only if this collection contains given object.
     *
     * @param value object to check if it is in this collection.
     * @return returns true if this collection contains given object and false otherwise.
     */
    public boolean contains(Object value) {
        return false;
    }

    /**
     * Returns true only if the collection contains given value and removes
     * one occurrence of it.
     *
     * @param value Object to remove.
     * @return returns true if the collection contains given value and false otherwise.
     */
    public boolean remove(Object value) {
        return false;
    }

    /**
     * Allocates new array with size equals to the size of this collections,
     * fills it with collection content and returns the array.
     *
     * @return new array of objects contained in this collection.
     */
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    /**
     * Calls <code>processor.process(.)</code> for each element of this collection.
     *
     * @param processor proces to be executed for each element of this collection.
     */
    public void forEach(Processor processor) {

    }

    /**
     * Method adds into the current collection all elements from the given collection.
     *
     * @param other collection which elements will be added in current collection.
     */
    public void addAll(Collection other) {

        class AddAllProcessor extends Processor {

            @Override
            public void process(Object value) {
                Collection.this.add(value);
            }
        }

        AddAllProcessor processor = new AddAllProcessor();
        other.forEach(processor);
    }

    /**
     * Removes all elements from this collection.
     */
    public void clear() {

    }
}
