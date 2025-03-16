package Pokemon;

public class Pokemon {
    String name;
    String type1;
    String type2;
    String ability;

    public Pokemon(String name, String type1, String type2, String ability) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.ability = ability;
    }

    @Override
    public String toString() {
        return name + " (Type1: " + type1 + ", Type2: " + type2 + ", Ability: " + ability + ")";
    }
}