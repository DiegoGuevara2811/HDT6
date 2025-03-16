package Pokemon;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione la implementación de MAP (1: HashMap, 2: TreeMap, 3: LinkedHashMap):");
        int choice = scanner.nextInt();
        MapFactory factory = switch (choice) {
            case 1 -> new HashMapFactory();
            case 2 -> new TreeMapFactory();
            case 3 -> new LinkedHashMapFactory();
            default -> throw new IllegalArgumentException("Opción inválida.");
        };

        PokemonManager manager = new PokemonManager(factory);

        // Cargar los datos sin try-catch innecesario
        manager.loadPokemonData("pokemon_data_pokeapi.csv");

        while (true) {
            System.out.println("Seleccione una operación: \n1. Agregar Pokémon \n2. Ver Pokémon \n3. Ver colección ordenada\n4. Ver todos ordenados\n5. Buscar por habilidad\n6. Salir");
            int op = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (op) {
                case 1:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    System.out.println(manager.addPokemonToUserCollection(scanner.nextLine()));
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    System.out.println(manager.getPokemonInfo(scanner.nextLine()));
                    break;
                case 3:
                    System.out.println(manager.getUserCollectionSortedByType());
                    break;
                case 4:
                    System.out.println(manager.getAllPokemonSortedByType());
                    break;
                case 5:
                    System.out.print("Ingrese la habilidad: ");
                    System.out.println(manager.getPokemonByAbility(scanner.nextLine()));
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
