import java.util.HashMap;
import java.util.Map;

public class HashBooks {

    public static String stockSummary(String[] stocklist, String[] categories) {
        // Verificar si las entradas están vacías
        if (stocklist == null || stocklist.length == 0 || categories == null || categories.length == 0) {
            return "";
        }

        // Crear un mapa para almacenar la suma de libros por categoría
        Map<String, Integer> categoryTotals = new HashMap<>();

        // Inicializar las categorías en el mapa con valor 0
        for (int i = 0; i < categories.length; i++) {
            categoryTotals.put(categories[i], 0);
        }

        // Procesar el stocklist
        for (int i = 0; i < stocklist.length; i++) {
            // Dividir el código del libro en código y cantidad
            String[] parts = stocklist[i].split(" ");
            String code = parts[0];
            int quantity = Integer.parseInt(parts[1]);

            // Obtener la categoría del libro (primer carácter del código)
            String category = code.substring(0, 1);

            // Sumar la cantidad si la categoría está en la lista de categorías
            if (categoryTotals.containsKey(category)) {
                categoryTotals.put(category, categoryTotals.get(category) + quantity);
            }
        }

        // Construir el resultado en el formato esperado, excluyendo categorías con valor 0
        StringBuilder result = new StringBuilder();
        Object[] keys = categoryTotals.keySet().toArray(); // Convertimos las claves en un array para un for normal
        for (int i = 0; i < keys.length; i++) {
            String category = (String) keys[i];
            int total = categoryTotals.get(category);

            if (total > 0) { // Solo agregar categorías con valor mayor a 0
                if (result.length() > 0) {
                    result.append(" - ");
                }
                result.append("(").append(category).append(" : ").append(total).append(")");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Ejemplo de prueba
        String[] stocklist = {"ABART 20", "CDXEF 50", "BKWRK 25", "BTSQZ 89", "DRTYM 60"};
        String[] categories = {"A", "B", "C", "W"};

        String result = stockSummary(stocklist, categories);
        System.out.println(result); // "(A : 20) - (B : 114) - (C : 50)"
    }
}
