import java.util.Random;

public class Pokemon {
    private String name;
    private PokemonType type;
    private int rarity;
    private boolean hp;


    public Pokemon(String name, PokemonType type) {
        this.name = name;
        this.type = type;
        this.rarity = randomRarity();
        this.hp = true;
    }

    public String getName() {
        return this.name;
    }

    public PokemonType getType() {
        return this.type;
    }

    public int getRarity() {
        return this.rarity;
    }

    //definir uma raridade aleatorio para cada pokemon registrado
    public int randomRarity() {
        Random random_rarity = new Random();
        int max_rarity = 100;
        int min_rarity = -100;
        //retornando uma raridade aleatoria, incluindo os valores de -100 ao 100
        int rarity = random_rarity.nextInt(max_rarity - min_rarity + 1) + min_rarity;
        return rarity;

    }

    public boolean getHp() {
        return this.hp;
    }

    public void die() {
        this.hp = false;
    }

    public void heal() {
        this.hp = true;
    }
}