/**	@elements : objects of type E
 *	@structure : Set
 *	@domain : 	The elements in the set are not sorted monotonically increasing.
 *				All elements of type E are valid values for a set.
 *
 *	@constructor - Set();
 *	<dl>
 *		<dt><b>PRE-conditie</b><dd>		-
 *		<dt><b>POST-conditie</b><dd> 	The new Set-object is the empty set.
 * </dl>
 **/

public interface SetInterface<E extends Comparable> {

    /** @precondition  -
     *	@postcondition - Union of set s and set t is returned. What is the point of the parameter s and t, only one is needed. you can also just create a new set/ be more explainative
     **/
    SetInterface<E> union(SetInterface t);

    /** @precondition  -
     *	@postcondition - Intersection of set s and set t is returned.
     **/
    SetInterface<E> intersection(SetInterface t);

    /** @precondition  -
     *	@postcondition - The complement of set s and set t is returned. (s \ t)
     **/
    SetInterface<E> complement(SetInterface t);

    /** @precondition  -
     *	@postcondition - The symmetric indifference of set s and set t is returned.
     **/
    SetInterface<E> indifference(SetInterface t);

    /** @precondition  - Elemend d has not to be in set
     *	@postcondition - Element d has been added to the Set
     **/
    SetInterface<E> add(E d);

    /** @precondition  - The set is not empty.
     *	@postcondition -The element d has been returned.
     */
    E retrieve();

    /** @precondition  - The set is not empty.
     * 	@postcondition - The element is removed from the set.
     **/
    SetInterface<E> remove(E d);

    /**
     * @precondition - The set is not empty.
     * @postcondition - TRUE: Element is present in set.
     *                  FALSE: Element is not present in set.
     */
    boolean isElement(E d);

    /**	@precondition -
     *  @postcondition - FALSE: Set is not empty.
     *  				TRUE:  Set is empty.
     **/
    boolean isEmpty();

    /**	@precondition  -
     *	@postcondition - The number of elements has been returned.
     **/
    int cardinality();

    void firstElement();

    void nextElement();
}
