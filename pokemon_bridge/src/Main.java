import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in); //scanner que sera usado para ler as resposta no menu

        String name; //nome do seu treinador

        System.out.printf("Digite o nome de seu treinador pokemon:");
        name = read.next();
        System.out.println("Seja bem vindo "+name+"!");

        Pokedex pokedex = new Pokedex(); //criacao da pokedex, onde ficara todos os pokemons cadastrados

        Trainer default_trainer = new Trainer("Ashe"); //treinador adversario, sempre tera um time aleatorio de pokemons
        Trainer you = new Trainer(name);                     //treinador o qual voce podera escolher o time

        int pre_setting;
        System.out.printf("\nDeseja adicionar pokemons pré-setados a sua pokedex? [0/1] 0-Nao e 1-Sim\nresposta:");
        pre_setting = read.nextInt();

        //Pokemon psy = new Pokemon("psy", PokemonType.AGUA);
        //Pokemon m = new Pokemon("Mankey", PokemonType.LUTADOR);

        if (pre_setting != 0) { //pokemons pre-setados para ajudar durante o teste do codigo
            pokedex.addPokemon(new Pokemon("Pikachu", PokemonType.ELETRICO));
            pokedex.addPokemon(new Pokemon("Charmander", PokemonType.FOGO));
            pokedex.addPokemon(new Pokemon("Squirtle", PokemonType.AGUA));
            pokedex.addPokemon(new Pokemon("Bulbasaur", PokemonType.GRAMA));
            pokedex.addPokemon(new Pokemon("Gyarados", PokemonType.AGUA));
            pokedex.addPokemon(new Pokemon("Snorlax", PokemonType.NORMAL));
            pokedex.addPokemon(new Pokemon("Machamp", PokemonType.LUTADOR));
            pokedex.addPokemon(new Pokemon("Vaporeon", PokemonType.AGUA));
            pokedex.addPokemon(new Pokemon("Flareon", PokemonType.FOGO));
            pokedex.addPokemon(new Pokemon("Jolteon", PokemonType.ELETRICO));
            pokedex.addPokemon(new Pokemon("Articuno", PokemonType.GELO));
            pokedex.addPokemon(new Pokemon("Zapdos", PokemonType.ELETRICO));
            pokedex.addPokemon(new Pokemon("Moltres", PokemonType.FOGO));
            pokedex.addPokemon(new Pokemon("Raichu", PokemonType.ELETRICO));
            pokedex.addPokemon(new Pokemon("Raichu", PokemonType.ELETRICO));
            pokedex.addPokemon(new Pokemon("Chikorita", PokemonType.GRAMA));
            pokedex.addPokemon(new Pokemon("Totodile", PokemonType.AGUA));
            pokedex.addPokemon(new Pokemon("Pidgey", PokemonType.NORMAL));
            pokedex.addPokemon(new Pokemon("Ratata", PokemonType.NORMAL));
            pokedex.addPokemon(new Pokemon("Psyduck", PokemonType.AGUA));
            pokedex.addPokemon(new Pokemon("Mankey", PokemonType.LUTADOR));
        }

        boolean aux = true;
        while (aux) {
            int option;
            System.out.println("\nMENU DE OPÇOES\n#--------------------#");
            System.out.println("1-Adcionar pokemon\n2-Criar time pokemon\n3-Gerar time pokemon\n4-Absolver time");
            System.out.println("5-Ver todos pokemons\n6-Ver times\n7-Pokemon mediano\n8-Verificar vantajosos\n9-Batalhar\n0-Sair");
            System.out.println("#--------------------#");
            System.out.printf("Resposta:");
            option = read.nextInt();
            switch (option) {
                case 1: //Adcionar pokemon
                    int type_number;
                    String pokemon_name;

                    //primeiramente ele irá pedir para escolher algum tipo pokemon dentre as opcoes possiveis
                    //e depois o nome para criar o objeto da classe Pokemon
                    System.out.println("\nEscolha algum tipo pokemon:");
                    System.out.println("1-NORMAL\n2-FOGO\n3-AGUA\n4-ELETRICO\n5-GRAMA\n6-GELO\n7-LUTADOR");
                    System.out.printf("tipo:");
                    type_number = read.nextInt();
                    System.out.printf("Nome do Pokemon:");

                    pokemon_name = read.next();
                    PokemonType type = PokemonType.getPokemonTypeByNumber(type_number);

                    pokedex.addPokemon(new Pokemon(pokemon_name, type)); //adcionar um novo pokemon na pokedex
                    break;

                case 2: //criar time pokemon
                    //aqui voce podera escolher exatamente os pokemons que deseja em seu time

                    if (!you.getTeam().isEmpty()) { //verificando se voce ja possui um time
                        System.out.println("\nVoce ja possui um time!!");
                        break;
                    }

                    //setar o tamanho das equipes
                    int team_size;
                    System.out.printf("Defina o tamanho da sua equipe:");
                    team_size = read.nextInt();

                    //verificar se há pokemons suficientes
                    if ((team_size * 2) > pokedex.getSize()) {
                        System.out.printf("Nao há pokemons suficientes encontrados!!!\n");
                        break;
                    }

                    //o index e o i_pokemons serao uteis para verificar se algum pokemon ja foi escolhido por qualquer equipe
                    //evitando que haja pokemons repetidos em ambos times ou no mesmo
                    int index = pokedex.getSize();
                    ArrayList<Integer> i_pokemons = new ArrayList<Integer>();

                    System.out.println("\nEscolha pokemons ja conhecidos pelo seus respectivos index");
                    pokedex.displayAllPokemon(); //mostrar os pokemons ja conhecidos para facilitar a escolha
                    for (int i = 0; i < team_size; i++) {
                        int pokemon_index;
                        System.out.printf((i + 1) + "° pokemon:");
                        pokemon_index = read.nextInt();   //ira ler o index do pokemon escolhido

                        //se foi adcionado, setar na lista para nao ser escolhido pelo adversario
                        boolean added = you.addPokemonTeam(pokedex.getPokemonList().get(pokemon_index - 1));
                        if (added) {
                            index = pokemon_index - 1;
                            i_pokemons.add(index);
                        } else {
                            System.out.println("Pokemon já se encontra no time!"); //se o pokemon ja esta na sua equipe
                            i--;                                                   //ira pedir que escolha novamente
                        }
                    }
                    System.out.println("\nSeu time está pronto!!");

                    //criando um time aleatorio para o adversario com os pokemons restantes
                    for (int i = 0; i < team_size; i++) {
                        while (true) {
                            index = (int) (Math.random() * pokedex.getSize()); //pegando um index aleatorio da pokedex
                            if (!i_pokemons.contains(index)) { //verificar se o pokemon ja foi escolhido
                                default_trainer.addPokemonTeam(pokedex.getPokemonList().get(index)); //se nao, adcionar ao time
                                i_pokemons.add(index); //adcionando o index a lista para nao se repetir
                                break;
                            }
                        }
                    }
                    break;

                case 3: //gerar time aleatorio
                    if (!you.getTeam().isEmpty()) { //verificar se ja existe um time
                        System.out.println("\nVoce ja possui um time!!");
                        break;
                    }
                    int team_size_; //setar o tamanho das equipes
                    System.out.printf("Defina o tamanho da sua equipe:");
                    team_size_ = read.nextInt();

                    if ((team_size_ * 2) > pokedex.getSize()) { //verificar se há pokemons suficientes
                        System.out.printf("Nao há pokemons suficientes encontrados!!!\n");
                        break;
                    }

                    //lista auxiliar para nao repetir o mesmo pokemon
                    ArrayList<Integer> i_pokemons_ = new ArrayList<Integer>();
                    int index_ = pokedex.getSize();
                    for (int i = 0; i < team_size_; i++) {
                        while (true) {
                            index_ = (int) (Math.random() * pokedex.getSize()); //pegando um index aleatorio da pokedex
                            if (!i_pokemons_.contains(index_)) { //verificar se o pokemon ja foi escolhido
                                you.addPokemonTeam(pokedex.getPokemonList().get(index_)); //se nao, adcionar ao time
                                i_pokemons_.add(index_); //adcionando o index a lista para nao se repetir
                                break;
                            }
                        }
                    }
                    System.out.println("\nSeu time está pronto!!");

                    //fazer o mesmo acima para o treinador adversario
                    for (int i = 0; i < team_size_; i++) {
                        while (true) {
                            index_ = (int) (Math.random() * pokedex.getSize()); //pegando um index aleatorio da pokedex
                            if (!i_pokemons_.contains(index_)) { //verificar se o pokemon ja foi escolhido
                                default_trainer.addPokemonTeam(pokedex.getPokemonList().get(index_)); //se nao, adcionar ao time
                                i_pokemons_.add(index_); //adcionando o index a lista para nao se repetir
                                break;
                            }
                        }
                    }

                    break;

                case 4: //absolver ambos os times
                    you.revokePokemonTeam();
                    default_trainer.revokePokemonTeam();
                    System.out.println("O seu time foi absolvido!");
                    break;

                case 5: //mostrar todos os pokemons na pokedex
                    pokedex.displayAllPokemon();
                    break;

                case 6: //ver ambos os times
                    if (you.getTeam().isEmpty()) {
                        System.out.println("Voce ainda nao possui um time no momento!");
                        break;
                    }
                    you.displayPokemonTeam();
                    default_trainer.displayPokemonTeam();
                    break;

                case 7: //mostrar o pokemon com a pontuacao mais neutra
                    pokedex.findMostAveregePokemon();
                    break;

                case 8: //mostrar a lista de todos pokemons que possuem vantagem sobre um pokemon escolhido
                    int pokemon_index;
                    System.out.println("\nEscolha o pokemon a ser analisado, pelo seu respectivo index");
                    pokedex.displayAllPokemon();
                    System.out.printf("index:");
                    pokemon_index = read.nextInt();

                    pokedex.listBetterPokemons(pokedex.getPokemonList().get(pokemon_index - 1));
                    break;

                case 9: //batalhar
                    if (you.getSize() > 0) { //verificar se voce possui um time

                        Championship championship = new Championship(you, default_trainer);

                        Trainer winner = championship.battle(); //retorna o vencedor entre os treinadores

                        System.out.println("\nVENCEDOR: " + winner.getName() + "!!!!!!");  //mostrar o vencedor
                        System.out.printf("Pokemons restantes: ");
                        for (Pokemon pokemon : winner.getTeam()) {                         //mostrar os pokemons restantes
                            if (pokemon.getHp()) {
                                System.out.printf(pokemon.getName() + "|");
                            }
                        }
                        System.out.println();
                    } else {
                        System.out.println("Voce ainda não possui um time pokemon!");
                    }
                    break;
                default: //fechar programa
                    aux = false;

            }
        }
    }
}