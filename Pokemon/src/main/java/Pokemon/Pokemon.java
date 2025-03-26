package Pokemon;

public class Pokemon {
    String name;
    String type1;
    String type2;
    String ability;

    // Constructor
    public Pokemon(String name, String type1, String type2, String ability) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.ability = ability;
    }

    // Método getter para obtener la habilidad del Pokémon
    public String getAbility() {
        return ability;
    }

    // Método para obtener el nombre del Pokémon
    public String getName() {
        return name;
    }

    // Método para obtener el primer tipo del Pokémon
    public String getType1() {
        return type1;
    }

    // Método para obtener el segundo tipo del Pokémon
    public String getType2() {
        return type2;
    }

    @Override
    public String toString() {
        return name + " (Type1: " + type1 + ", Type2: " + type2 + ", Ability: " + ability + ")";
    }
}
