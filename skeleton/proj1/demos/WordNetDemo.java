import ngordnet.WordNet;

/** Class that demonstrates basic WordNet functionality.
 *  @author Josh Hug
 */
public class WordNetDemo {
    public static void main(String[] args) {
        WordNet wn = new WordNet("./wordnet/synsets11.txt", "./wordnet/hyponyms11.txt");

        /* These should all print true. */
        System.out.println(wn.isNoun("jump"));
        System.out.println(wn.isNoun("leap"));
        System.out.println(wn.isNoun("nasal_decongestant"));

        /* The code below should print the following (maybe not in this order): 
            All nouns:
            augmentation
            nasal_decongestant
            change
            action
            actifed
            antihistamine
            increase
            descent
            parachuting
            leap
            demotion
            jump
        */

        System.out.println("All nouns:");
        for (String noun : wn.nouns()) {
            System.out.println(noun);
        }

        /* The code below should print the following (maybe not in this order): 
            Hypnoyms of increase:
            augmentation
            increase
            leap
            jump
        */

        System.out.println("Hypnoyms of increase:");
        for (String noun : wn.hyponyms("increase")) {
            System.out.println(noun);
        }

        /* The code below should print the following (maybe not in this order): 
            Hypnoyms of jump:
            parachuting
            leap
            jump
        */

        System.out.println("Hypnoyms of jump:");
        for (String noun : wn.hyponyms("jump")) {
            System.out.println(noun);
        }  

        /* The code below should print the following (maybe not in this order):
            Hypnoyms of change:
            alteration
            saltation
            modification
            change
            variation
            increase
            transition
            demotion
            leap
            jump        
        */

        /** From: http://goo.gl/EGLoys */
        System.out.println("Hypnoyms of change:");

        WordNet wn2 = new WordNet("./wordnet/synsets14.txt", "./wordnet/hyponyms14.txt");
        for (String noun : wn2.hyponyms("change")) {
            System.out.println(noun);
        }              
    }    
} 
