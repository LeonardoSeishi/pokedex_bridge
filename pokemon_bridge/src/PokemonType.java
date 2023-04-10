import java.util.*;

//enum auxiliar para ajudar nas definicoes das vantagens e desvantagens
//ajudar no cadastro de novos pokemons e facilitar a checagem durante as batalhas
public enum PokemonType {
    NORMAL, FOGO, AGUA, ELETRICO, GRAMA, GELO, LUTADOR; //tipos pokemons possiveis

    private List<PokemonType> advantages;
    private List<PokemonType> disadvantages;

    public void init(List<PokemonType> advantages, List<PokemonType> disadvantages) {
        this.advantages = advantages;
        this.disadvantages = disadvantages;
    }
    //definindo as vantagens e desvantagens de cada tipo pokemon
    static {
        NORMAL.init(    Arrays.asList(),            Arrays.asList());
        FOGO.init(      Arrays.asList(GRAMA, GELO), Arrays.asList(FOGO, AGUA));
        AGUA.init(      Arrays.asList(FOGO),        Arrays.asList(AGUA, GRAMA));
        ELETRICO.init(  Arrays.asList(AGUA),        Arrays.asList(ELETRICO, GRAMA));
        GRAMA.init(     Arrays.asList(AGUA),        Arrays.asList(GRAMA, FOGO));
        GELO.init(      Arrays.asList(GRAMA, GELO), Arrays.asList(GELO, FOGO, AGUA));
        LUTADOR.init(   Arrays.asList(NORMAL,GELO), Arrays.asList());

    }

    public List<PokemonType> getAdvantages() {
        return advantages;
    }

    public List<PokemonType> getDisadvantages() {
        return disadvantages;
    }

    public static PokemonType getPokemonTypeByNumber(int i) {
        switch (i) {
            case 1:
                return NORMAL;
            case 2:
                return FOGO;
            case 3:
                return AGUA;
            case 4:
                return ELETRICO;
            case 5:
                return GRAMA;
            case 6:
                return GELO;
            case 7:
                return LUTADOR;
            default:
                return NORMAL;
        }
    }
}