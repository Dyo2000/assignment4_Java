package utitilies;

import java.io.Serializable;
import java.util.UUID;

/**
 * THis is the pokemon class that implements serializable.
 * THis creates a pokemon constructor and has a few methods
 */
public class Pokemon implements Serializable {
    private UUID pokemonID;
    private String pokemonType;
    private String name;
    private Boolean checkedIn;

    /**
     * This is the pokemon consturctor that sends the type, name, UUID and checkin to the main
     * @param pokemonType
     * @param name
     */
    public Pokemon(String pokemonType, String name) {
        this.pokemonType = pokemonType;
        this.name = name;
        this.pokemonID = UUID.randomUUID();
        this.checkedIn = false;
    }

    /**
     * This is the getPokemondID method that returns the pokemonID
     * @return
     */
    public UUID getPokemonID(){
        return pokemonID;
    }

    /**
     * This is the getPokemonType method that returns the pokemonType
     * @return
     */
    public String getPokemonType(){
        return pokemonType;
    }

    /**
     * This is the getName method that gets the name of the pokemon
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * This is the ischecked in method that returns the checked in status
     * @return
     */
    public boolean isCheckedIn(){
        return checkedIn;
    }

    /**
     * This is the checkin method that sets the checkin variable to true
     */
    public void checkIn(){
        checkedIn = true;
    }

    /**
     * This is the checkout method that sets the checkedin variable to false
     */
    public void checkOut(){
        checkedIn = false;
    }
}
