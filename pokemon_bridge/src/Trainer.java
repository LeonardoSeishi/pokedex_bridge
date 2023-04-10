import java.util.ArrayList;

public class Trainer {
    private String name;
    private ArrayList<Pokemon> pokemon_team;

    public Trainer(String name) {
        this.name = name;
        this.pokemon_team = new ArrayList<Pokemon>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Pokemon> getTeam() {
        return this.pokemon_team;
    }


    public int getSize() {
        return this.pokemon_team.size();
    }

    //escolher algum pokemon que possa lutar
    public Pokemon choosePokemon() {
        for (Pokemon pokemon : this.pokemon_team) {
            if (pokemon.getHp()) {
               return pokemon;
           }
       }
        return null;
    }

    //mostrar todos pokemons na equipe
    public void displayPokemonTeam() {
        System.out.println("\n*Equipe "+this.getName()+":");
        for (Pokemon pokemon : getTeam()) {
            System.out.println("-"+pokemon.getName()+"["+pokemon.getType()+"|"+pokemon.getRarity()+"]");
        }
    }
    //adcionar pokemon para a equipe
    public boolean addPokemonTeam(Pokemon pokemon){
        for (Pokemon teammate: this.pokemon_team) {
            if (pokemon == teammate) {
                this.pokemon_team.remove(teammate);
                return false;
            }
        }
        this.pokemon_team.add(pokemon);
        return true;
    }

    //abolver time pokemon
    public void revokePokemonTeam() {
        this.pokemon_team = new ArrayList<Pokemon>();
    }
}
