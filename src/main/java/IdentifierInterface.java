/**	@elements : objects of characters
 *	@structure : linear
 *	@domain : 	The elements in the Identifier are char's and can only be numbers or letters. The first value is always a letter.
 *	It's on it's own and combines with the hashmap.
 *  d.papuc@
 **/

/** @precondition  -
 * 	@postcondition - The new identifier is the empty identifier.
 * 	                 !!int position starts with "a" and grows alphabetically incrementally when adding new characters.
 **/

interface IdentifierInterface{

    /**
     *  @precondition - first char to add to identifier, should be a letter
     *  @postcondition - char has to be added to identifier and position is initialized
     **/
    void addFirst(char charToStore);

    /**
     *  @precondition - position p !> 26 (the letter z)
     *  @postcondition - char has to be added to identifier and position has to go up 1.
     **/
    void add(char charToStore);


    /**
     *  @precondition - The identifier is not empty and p exists
     *  @postcondition - Returns char on position p
     **/
    char get(char p);

    /**
     *  @precondition -
     *  @postcondition - Returns size of identifier based on position
     **/
    int size();

}
