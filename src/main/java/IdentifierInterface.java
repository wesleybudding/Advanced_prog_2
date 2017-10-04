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


    /**	@precondition -
     *  @postcondition - FALSE: item is not name.
     *  				TRUE:  item is name.
     **/
    boolean isName();

    /**	@precondition -
     *  @postcondition - FALSE: string is statement of value's.
     *  				TRUE:  string needs to return values's .
     **/
    boolean isQuestion();

    /**	@precondition -
     *  @postcondition - FALSE: item is not equal sign.
     *  				TRUE:  item is equal sign.
     **/
    boolean isEqualsSign();

    /**	@precondition -
     *  @postcondition - returns type operator.
     **/
    void isOperator();

    /**
     *  The identifier reads & splits string and identifies the element.
     *  @postcondition - items in string and gives them type
     **/
    void identifier(String input);


    /**
     *  Add creates new set.
     *  @postcondition -
     **/
    void add(String input);


    /**
     *  @precondition - The set is not empty
     *  @postcondition - Returns set
     **/
    void returns(String input);


}
