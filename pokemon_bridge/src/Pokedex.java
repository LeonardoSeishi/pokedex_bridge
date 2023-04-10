import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;

public class Pokedex {
    private ArrayList<Pokemon> pokemonList;

    public Pokedex() {
        this.pokemonList = new ArrayList<Pokemon>();
    }

    public ArrayList<Pokemon> getPokemonList() {
        return this.pokemonList;
    }

    public int getSize() {
        return this.pokemonList.size();
    }
    //adcionar novos pokemons a pokedex
    public void addPokemon(Pokemon pokemon) {
        this.pokemonList.add(pokemon);
        System.out.println(pokemon.getName()+"("+pokemon.getRarity()+") Foi adcionado a pokedex!");
    }
    //mostrar todos os pokemons conhecidos/ja cadastrados
    public void displayAllPokemon() {
        System.out.println("\nPokedex:");
        System.out.println("*----------------*");
        int i =1;
        for (Pokemon pokemon : this.pokemonList) {
            System.out.println(i+"-"+pokemon.getName() + ": " + pokemon.getRarity());
            i++;
        }
        System.out.println("*----------------*");
    }
    //funcao auxiliar
    public Pokemon findPokemonByName(String name) {
        for (Pokemon pokemon : this.pokemonList) {
            if (pokemon.getName().equals(name)) {
                return pokemon;
            }
        }
        return null;
    }
    //retonar o pokemon com a raridade mais neutra
    public void findMostAveregePokemon() {
        //variaveis para guardar os valores encontrados
        int averege_rarity = 100;
        Pokemon averege_pokemon = null;

        for (Pokemon pokemon : this.pokemonList) {
            int pokemon_rarity = abs(pokemon.getRarity());
            //quanto menor, mais perto de zero
            if (pokemon_rarity < averege_rarity) {
                averege_rarity = pokemon_rarity;
                averege_pokemon = pokemon;

            //caso sejam iguais, pegar o valor positivo
            //se ambos forem positivos, manter resposta anterior
            } else if (pokemon_rarity == averege_rarity) {
                if (pokemon.getRarity() > averege_rarity) {
                    averege_rarity = pokemon_rarity;
                    averege_pokemon = pokemon;
                }
            }
        }
        System.out.println("\n*O pokemon com a raridade mais neutra é "+ averege_pokemon.getName() + ": "+ averege_pokemon.getRarity());
    }
    //retornar lista de pokemons que possuem vantagem sobre o pokemon passado na entrada
    public void listBetterPokemons(Pokemon pokemon) {
        PokemonType type = pokemon.getType();
        //pegar uma lista de todos os tipos que o pokemon possui desvantagem
        List<PokemonType> disadvantages = pokemon.getType().getDisadvantages();

        //criar uma lista que tera todos os pokemons conhecidos que terao vantagem sobre o pokemon passado na entrada
        ArrayList<Pokemon> better = new ArrayList<Pokemon>();

        //verificar todos pokemons na pokedex
        for (Pokemon p: this.pokemonList) {
            PokemonType type_p = p.getType();
            //se forem do mesmo tipo, apesar das vantagens e desvantagens
            //no final a vantagem se tornara neutra em todas as ocasioes
            if (type_p != type) {
                //boolean para auxiliar e evitar que o mesmo pokemon seja adcionado duas 2 vezes
                boolean already_added = false;
                for (PokemonType t: disadvantages) {
                    if (t == type_p) {       //se o pokemon escolhido tiver desvantagem sobre o pokemon atual
                        better.add(p);       //adcionar na lista
                        already_added = true;
                    }
                }
                if (!already_added) {     //verificar se o pokemon ja foi adcionado
                    List<PokemonType> advantages = type_p.getAdvantages();
                    for (PokemonType t: advantages) {
                        if (t == type) {    //se o pokemon atual possui vantagem sobre o pokemon escolhido
                            better.add(p);  //adcionar na lista
                        }
                    }
                }
            }

        }
        if (better.size() > 0) {
            System.out.println("\n*Pokemons com vantagem sobre " + pokemon.getName());
            for (Pokemon p : better) {
                System.out.println("-" + p.getName());
            }
        }
    }
}
