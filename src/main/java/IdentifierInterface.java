/**	@elements : objects of characters
 *	@structure : linear
 *	@domain : 	state boundary it states only numbers & letters It's on it's own and combines with the hashmap and the set
 *	get & size add
 *  d.papuc@
 **/

/**
 *  functions Identifier
 *  functions Add
 *  operations
 *
 *  Questions
 **/

/** @precondition  - The list is not empty.
 * 	@postcondition - The element is removed from the set.
 **/

interface IdentifierInterface{

    /**
     *  Add creates new set.
     *  @precondition -
     *  @postcondition -
     **/
    void add();


    /**
     *  @precondition - The object is not empty
     *  @postcondition - Returns set
     **/
    void returns();

    /**
     *  @precondition -
     *  @postcondition - Returns object on certain position
     **/
    void get();

    /**
     *  @precondition -
     *  @postcondition - Returns set
     **/
    void size();

}
