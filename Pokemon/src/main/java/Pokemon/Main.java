package Pokemon;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PokemonManager manager = new PokemonManager(new HashMapFactory());

        // Cargar los datos del CSV
        System.out.println(manager.loadPokemonData("pokemon_data_pokeapi.csv"));

        boolean running = true;
        while (running) {
            System.out.println("Seleccione una operación:");
            System.out.println("1. Agregar Pokémon");
            System.out.println("2. Consultar Pokémon por nombre");
            System.out.println("3. Ver colección de Pokémon ordenada por tipo");
            System.out.println("4. Ver todos los Pokémon ordenados por tipo");
            System.out.println("5. Buscar Pokémon por habilidad");
            System.out.println("6. Salir");

            System.out.print("Ingrese una opción: ");
            int option;
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea pendiente
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar el buffer
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String nameToAdd = scanner.nextLine();
                    System.out.println(manager.addPokemonToUserCollection(nameToAdd));
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String name = scanner.nextLine();
                    System.out.println(manager.getPokemonInfo(name));
                    break;
                case 3:
                    System.out.println(manager.getUserCollectionSortedByType());
                    break;
                case 4:
                    System.out.println(manager.getAllPokemonSortedByType());
                    break;
                case 5:
                    System.out.print("Ingrese la habilidad: ");
                    String ability = scanner.nextLine();
                    System.out.println(manager.getPokemonByAbility(ability));
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}
