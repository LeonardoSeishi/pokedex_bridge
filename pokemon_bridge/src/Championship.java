import java.util.List;
import java.util.Random;

public class Championship {

    private Trainer trainer1;
    private Trainer trainer2;

    public Championship(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
    }

    public String fight(Pokemon pokemon1, Pokemon pokemon2) {
        //pegando os tipos de ambos os pokemons
        PokemonType type1 = pokemon1.getType();
        PokemonType type2 = pokemon2.getType();

        Random random = new Random();

        if (type1 != type2) { //verificar se ambos pokemons são do mesmo tipo

            //pegando as listas de vantagens e desvantagens
            List<PokemonType> p1_advantages = type1.getAdvantages();
            List<PokemonType> p1_disadvantages = type1.getDisadvantages();
            List<PokemonType> p2_advantages = type2.getAdvantages();
            List<PokemonType> p2_disadvantages = type2.getDisadvantages();

            if (p1_advantages.contains(type2)) { //verificar se o tipo do pokemon2 é uma vantagem do pokemon1
                pokemon2.die();
                return pokemon1.getName();
            } else if (p2_advantages.contains(type1)){ //verificar se o tipo do pokemon1 é uma vantagem do pokemon2
                pokemon1.die();
                return pokemon2.getName();
            } else if (p1_disadvantages.contains(type2)) { //verificar se o tipo do pokemon2 é uma desvantagem do pokemon1
                pokemon1.die();
                return pokemon2.getName();
            } else if (p2_disadvantages.contains(type1)) { //verificar se o tipo do pokemon1 é uma desvantagem do pokemon2
                pokemon2.die();
                return pokemon1.getName();
            } else { //nao possuem vantagem sobre o outro
                if (pokemon1.getRarity() > pokemon2.getRarity()) { //decidir a batalha pela raridade
                    pokemon2.die();
                    return pokemon1.getName();
                } else if (pokemon1.getRarity() < pokemon2.getRarity()){
                    pokemon1.die();
                    return pokemon2.getName();
                } else {                                        //caso empate decidir aleatoriamente
                    boolean t = random.nextBoolean();
                    if (t) {
                        pokemon2.die();
                        return pokemon1.getName();
                    } else {
                        pokemon1.die();
                        return pokemon2.getName();
                    }
                }
            }
        } else { //se forem do mesmo tipo, apesar das vantagens e desvantagens, no final a batalha entre eles sera neutra
            if (pokemon1.getRarity() > pokemon2.getRarity()) { //decidir pela raridade
                pokemon2.die();
                return pokemon1.getName();
            } else if (pokemon1.getRarity() < pokemon2.getRarity()){
                pokemon1.die();
                return pokemon2.getName();
            } else {                                    //caso empate decidir aleatoriamente
                boolean t = random.nextBoolean();
                if (t) {
                    pokemon2.die();
                    return pokemon1.getName();
                } else {
                    pokemon1.die();
                    return pokemon2.getName();
                }
            }
        }
    }

    public Trainer battle() {
        pokemonCenter(); //reviver todos pokemons antes da batalha começar

        int num_fight = this.trainer1.getSize()*2; //o maximo de lutas possiveis sera o (tamanho do time * 2) -1

        String name1 = this.trainer1.getName();
        String name2 = this.trainer2.getName();

        System.out.println("\n-Inicio da Batalha!!\n-Equipe "+ this.trainer1.getName()+" vs Equipe " + this.trainer2.getName());
        System.out.println("._________________________________________.");
        int i = 1;
        //começo das lutas entre pokemons
        while (i < (num_fight)+1) {
            Pokemon pokemon1 = this.trainer1.choosePokemon(); //escolher um pokemon capacitado de lutar
            Pokemon pokemon2 = this.trainer2.choosePokemon(); //escolher um pokemon capacitado de lutar
            if (pokemon1 == null) {    //caso nao tenha mais pokemons vivos, retornar o vencedor
                System.out.printf("._________________________________________.");
                return this.trainer2;}
            else if (pokemon2 == null) {
                System.out.printf("._________________________________________.");
                return this.trainer1;}

            //prints para entender as rodadas
            System.out.printf("RODADA "+i+ "- "+pokemon1.getName()+"("+this.trainer1.getName()+") x ");
            System.out.println(pokemon2.getName()+"("+this.trainer2.getName()+")");

            //retornar o pokemon vencedor
            String winner = fight(pokemon1,pokemon2); //funcao resposavel por decidir os resultados das rodadas

            //prints para entender o desenrolar das rodadas
            if (winner == pokemon1.getName()) {
                System.out.println("RODADA " + i + "- " + winner + "(" + this.trainer1.getName() + ")\n");
            } else {
                System.out.println("RODADA " + i + "- " + winner + "(" + this.trainer2.getName() + ")\n");
            }
            i++;
        }
        return null;
    }

    //centro pokemon e resposavel por curar todos os pokemons
    public void pokemonCenter() {
        for (Pokemon pokemon :this.trainer1.getTeam()) {
            pokemon.heal();
        }
        for (Pokemon pokemon :this.trainer2.getTeam()) {
            pokemon.heal();
        }
    }

}
