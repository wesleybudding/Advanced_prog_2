/**	@elements : objects of characters
 *	@structure : linear
 *	@domain : 	The elements in the Identifier are char's and can only be numbers or letters. It's on it's own and combines with the hashmap.
 *  d.papuc@
 **/

/** @precondition  -
 * 	@postcondition - The new identifier is the empty identifier.
 * 	                 char position starts with "a" and grows alphabetically incrementally when adding new characters.
 **/

interface IdentifierInterface{

    /**
     *  @precondition - element has to be char
     *  @postcondition - char has to be added to identifier and position has to go to next letter in alphabet
     **/
    void add(char charToStore);


    /**
     *  @precondition - The identifier is not empty
     *  @postcondition - Returns char on current position
     **/
    void returns();

    /**
     *  @precondition - The identifier is not empty
     *  @postcondition - Returns char on position p
     **/
    void get(char p);

    /**
     *  @precondition -
     *  @postcondition - Returns size of identifier based on position
     **/
    void size();

}
