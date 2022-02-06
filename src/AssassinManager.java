import java.util.List;

/**
 * * Created by Andrew Cantrell on February 5
 * For Dr. Han
 * EGR 227
 * Due February 7
 * HW#3: Hangman Manager
 * This class is utilized by Assassin Main and runs a lot of different
 * functionality. It constructs the Assassin Manager, then can return the
 * set set of all remaining players in the game and who they are stalking. It
 * can return the set of all dead players and who they were killed by.
 * It can return a boolean value of if the killRing or graveyard contains
 * a specific name. It can tell the main if the game is over and who the winner of
 * the game was. Most of the functionality comes from the ability of this class
 * to be able to record who was killed, who they were killed by, and update the
 * kill ring.
 * Discussed with: Chris Styerwalt, Chase Crossley
 **/
public class AssassinManager {


    private AssassinNode graveFront;
    private AssassinNode killRingFront;

    /**
     * This constructor instantiates the kill ring and makes each piece
     * point to the next piece in the chain. It also throws an illegal
     * argument exeption for if the list passed in was emtpy.
     * @param names list of names to be made into the game
     */
    public AssassinManager(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException();
        }
        AssassinNode temp = new AssassinNode(names.get(0));
        killRingFront = temp;
        for (int i = 1; i < names.size(); i++) {
            temp.next = new AssassinNode(names.get(i));
            temp = temp.next;
        }
    }

    /**
     * This method simply prints all of the names remaining in the
     * kill ring and currently alive in the game
     * It pulls its data from killRingFront
     */
    public void printKillRing() {
        AssassinNode temp = killRingFront;
        while (temp.next != null) {
            System.out.println("    " + temp.name + " is stalking " + temp.next.name);
            temp = temp.next;
        }
        System.out.println("    " + temp.name + " is stalking " + killRingFront.name);
    }

    /**
     * This method simply prints out the list of every player
     * who has died so far in the game as well as who killed them.
     * It pulls its data from gravefront
     */
    public void printGraveyard() {
        if (graveFront != null) {
            AssassinNode temp = graveFront;
            while (temp != null) {
                System.out.println("    " + temp.name + " was killed by " + temp.killer);
                temp = temp.next;
            }
        }
    }

    /**
     * This method returns if the kill ring contains the passed in name
     * @param name name to be checked in the ring
     * @return true if the name exists
     */
    public boolean killRingContains(String name){
            return ifContains(killRingFront, name);
    }

    /**
     * This method returns if the graveyard contains the passed in name
     * @param name name to be checked in the graveyard
     * @return true if the name exists
     */
    public boolean graveyardContains(String name){
        if(graveFront != null){
            return ifContains(graveFront, name);
        }
        return false;
    }

    /**
     * A simple helper method to check if the list contains the name
     * to be checked.
     * @param pointer the pointer to the list to be checked
     * @param name the name to be checked
     * @return true if the name exists
     */
    private boolean ifContains(AssassinNode pointer, String name){
        AssassinNode temp = pointer;
        while(temp != null){
            if(temp.name.equalsIgnoreCase(name)){
                return true;
            }else{
                temp = temp.next;
            }
        }
        return false;
    }

    /**
     * Returns true if there is only one player remaining in the game.
     * @return boolean if game is over
     */
    public boolean isGameOver(){
        if(killRingFront.next == null){
            return true;
        }
        return false;
    }

    /**
     * Returns the name of the last player standing if the
     * game is actually over.
     * @return the name of the victor
     */
    public String winner(){
        if(isGameOver()){
            return killRingFront.name;
        }
        return null;
    }

    /**
     * this method records the player killed by the game. It also records who
     * the player was killed by and then adds their name into the graveyard.
     * It then updates the pointers in the killRing to properly advance the game
     * @param name
     */
    public void kill(String name){
        //If the game is over, do not run the code
        if(isGameOver()){throw new IllegalStateException();}
        //Temporary pointer
        AssassinNode temp = killRingFront;
        //If the front node is the one to be killed
        if(temp.name.equalsIgnoreCase(name)){
            //Instantiate killer
            AssassinNode findLast = temp;
            //Finds last node
            while(findLast.next != null){
                findLast = findLast.next;
            }
            //Updates the killer of the first node
            temp.killer = findLast.name;
            //Update the killRing
            killRingFront = temp.next;
            //Add to the graveyard
            AssassinNode killed = temp;
            killed.next = graveFront;
            graveFront = killed;
        }
        while(temp.next != null){//find the node that needs to be killed
            if(temp.next.name.equalsIgnoreCase(name)){
                //Instantiate killer
                temp.next.killer = temp.name;
                //Temp data piece
                AssassinNode killed = temp.next;
                //Skip killed
                temp.next = temp.next.next;
                //Add that name to the grave
                killed.next = graveFront;
                graveFront = killed;
                //Quite once you found the person
                return;
            }else{
                //Move forward
                temp = temp.next;
            }
        }
    }

    //////// DO NOT MODIFY AssassinNode.  You will lose points if you do. ////////

    /**
     * Each AssassinNode object represents a single node in a linked list
     * for a game of Assassin.
     */
    private static class AssassinNode {
        public final String name;  // this person's name
        public String killer;      // name of who killed this person (null if alive)
        public AssassinNode next;  // next node in the list (null if none)

        /**
         * Constructs a new node to store the given name and no next node.
         */
        public AssassinNode(String name) {
            this(name, null);
        }

        /**
         * Constructs a new node to store the given name and a reference
         * to the given next node.
         */
        public AssassinNode(String name, AssassinNode next) {
            this.name = name;
            this.killer = null;
            this.next = next;
        }
    }
}
