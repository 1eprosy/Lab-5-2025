import functions.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ МЕТОДОВ TABULATED FUNCTION ===\n");

        // Создаем тестовые данные
        System.out.println("1. СОЗДАНИЕ ТЕСТОВЫХ ОБЪЕКТОВ:");

        // Создаем массив точек для тестирования
        FunctionPoint[] testPoints = {
                new FunctionPoint(1.0, 2.0),
                new FunctionPoint(2.0, 4.0),
                new FunctionPoint(3.0, 6.0),
                new FunctionPoint(4.0, 8.0),
                new FunctionPoint(5.0, 10.0)
        };

        // Создаем объекты ArrayTabulatedFunction
        ArrayTabulatedFunction arrayFunc1 = new ArrayTabulatedFunction(testPoints);
        ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(0.0, 10.0, 5);
        ArrayTabulatedFunction arrayFunc3 = new ArrayTabulatedFunction(0.0, 10.0, new double[]{1.0, 2.0, 3.0, 4.0, 5.0});

        // Создаем объекты LinkedListTabulatedFunction
        LinkedListTabulatedFunction linkedListFunc1 = new LinkedListTabulatedFunction(testPoints);
        LinkedListTabulatedFunction linkedListFunc2 = new LinkedListTabulatedFunction(0.0, 10.0, 5);
        LinkedListTabulatedFunction linkedListFunc3 = new LinkedListTabulatedFunction(0.0, 10.0, new double[]{1.0, 2.0, 3.0, 4.0, 5.0});

        System.out.println("Создано:");
        System.out.println("  - 3 объекта ArrayTabulatedFunction");
        System.out.println("  - 3 объекта LinkedListTabulatedFunction");

        // ============================================
        // ТЕСТ МЕТОДА toString()
        // ============================================
        System.out.println("\n2. ТЕСТ МЕТОДА toString():");

        System.out.println("\nArrayTabulatedFunction arrayFunc1:");
        System.out.println("  " + arrayFunc1.toString());

        System.out.println("\nArrayTabulatedFunction arrayFunc2:");
        System.out.println("  " + arrayFunc2.toString());

        System.out.println("\nLinkedListTabulatedFunction linkedListFunc1:");
        System.out.println("  " + linkedListFunc1.toString());

        System.out.println("\nLinkedListTabulatedFunction linkedListFunc2:");
        System.out.println("  " + linkedListFunc2.toString());

        // ============================================
        // ТЕСТ МЕТОДА equals()
        // ============================================
        System.out.println("\n3. ТЕСТ МЕТОДА equals():");

        System.out.println("\nСравнение одинаковых объектов одного класса:");
        System.out.println("  arrayFunc1.equals(arrayFunc1): " + arrayFunc1.equals(arrayFunc1));

        // Создаем точно такую же функцию как arrayFunc1
        ArrayTabulatedFunction arrayFunc1Copy = new ArrayTabulatedFunction(testPoints);
        System.out.println("  arrayFunc1.equals(arrayFunc1Copy): " + arrayFunc1.equals(arrayFunc1Copy));

        System.out.println("\nСравнение разных объектов одного класса:");
        System.out.println("  arrayFunc1.equals(arrayFunc2): " + arrayFunc1.equals(arrayFunc2));

        System.out.println("\nСравнение объектов разных классов с одинаковыми точками:");
        // Создаем LinkedListTabulatedFunction с теми же точками
        FunctionPoint[] testPoints2 = {
                new FunctionPoint(1.0, 2.0),
                new FunctionPoint(2.0, 4.0),
                new FunctionPoint(3.0, 6.0),
                new FunctionPoint(4.0, 8.0),
                new FunctionPoint(5.0, 10.0)
        };
        LinkedListTabulatedFunction linkedListFunc1Copy = new LinkedListTabulatedFunction(testPoints2);
        System.out.println("  arrayFunc1.equals(linkedListFunc1Copy): " + arrayFunc1.equals(linkedListFunc1Copy));

        System.out.println("\nСравнение объектов разных классов с разными точками:");
        System.out.println("  arrayFunc2.equals(linkedListFunc2): " + arrayFunc2.equals(linkedListFunc2));

        System.out.println("\nСравнение с null:");
        System.out.println("  arrayFunc1.equals(null): " + arrayFunc1.equals(null));

        System.out.println("\nСравнение с объектом другого типа:");
        System.out.println("  arrayFunc1.equals(\"строка\"): " + arrayFunc1.equals("строка"));

        // ============================================
        // ТЕСТ МЕТОДА hashCode()
        // ============================================
        System.out.println("\n4. ТЕСТ МЕТОДА hashCode():");

        System.out.println("\nХэш-коды объектов:");
        System.out.println("  arrayFunc1.hashCode(): " + arrayFunc1.hashCode());
        System.out.println("  arrayFunc1Copy.hashCode(): " + arrayFunc1Copy.hashCode());
        System.out.println("  arrayFunc2.hashCode(): " + arrayFunc2.hashCode());
        System.out.println("  linkedListFunc1.hashCode(): " + linkedListFunc1.hashCode());
        System.out.println("  linkedListFunc1Copy.hashCode(): " + linkedListFunc1Copy.hashCode());
        System.out.println("  linkedListFunc2.hashCode(): " + linkedListFunc2.hashCode());

        // Проверка согласованности equals() и hashCode()
        System.out.println("\nПроверка согласованности equals() и hashCode():");
        System.out.println("  arrayFunc1.equals(arrayFunc1Copy): " + arrayFunc1.equals(arrayFunc1Copy));
        System.out.println("  arrayFunc1.hashCode() == arrayFunc1Copy.hashCode(): " +
                (arrayFunc1.hashCode() == arrayFunc1Copy.hashCode()));

        System.out.println("  arrayFunc1.equals(arrayFunc2): " + arrayFunc1.equals(arrayFunc2));
        System.out.println("  arrayFunc1.hashCode() == arrayFunc2.hashCode(): " +
                (arrayFunc1.hashCode() == arrayFunc2.hashCode()));

        // Тест изменения объекта и хэш-кода
        System.out.println("\nТест изменения объекта и хэш-кода:");
        System.out.println("  Исходный хэш-код arrayFunc3: " + arrayFunc3.hashCode());
        System.out.println("  Меняем координату Y у точки с индексом 2 с 3.0 на 3.001...");

        // Сохраняем исходный хэш-код
        int originalHashCode = arrayFunc3.hashCode();

        // Меняем одну координату
        try {
            arrayFunc3.setPointY(2, 3.001);
        } catch (Exception e) {
            System.out.println("  Ошибка при изменении точки: " + e.getMessage());
        }

        System.out.println("  Новый хэш-код arrayFunc3: " + arrayFunc3.hashCode());
        System.out.println("  Хэш-коды " + (originalHashCode == arrayFunc3.hashCode() ? "совпали" : "различаются"));

        // ============================================
        // ТЕСТ МЕТОДА clone()
        // ============================================
        System.out.println("\n5. ТЕСТ МЕТОДА clone():");

        // Тест для ArrayTabulatedFunction
        System.out.println("\nТест клонирования ArrayTabulatedFunction:");
        System.out.println("  Исходный объект arrayFunc1: " + arrayFunc1.toString());

        ArrayTabulatedFunction arrayFunc1Clone = (ArrayTabulatedFunction) arrayFunc1.clone();
        System.out.println("  Клон arrayFunc1Clone: " + arrayFunc1Clone.toString());

        System.out.println("  arrayFunc1 == arrayFunc1Clone: " + (arrayFunc1 == arrayFunc1Clone));
        System.out.println("  arrayFunc1.equals(arrayFunc1Clone): " + arrayFunc1.equals(arrayFunc1Clone));

        // Проверка глубокого клонирования для ArrayTabulatedFunction
        System.out.println("\nПроверка глубокого клонирования (ArrayTabulatedFunction):");
        System.out.println("  Изменяем исходный объект arrayFunc1...");
        try {
            arrayFunc1.setPointY(2, 100.0); // Меняем Y у точки с индексом 2
        } catch (Exception e) {
            System.out.println("  Ошибка при изменении точки: " + e.getMessage());
        }

        System.out.println("  Исходный объект arrayFunc1 после изменения: " + arrayFunc1.toString());
        System.out.println("  Клон arrayFunc1Clone после изменения оригинала: " + arrayFunc1Clone.toString());
        System.out.println("  Клон изменился? " + (!arrayFunc1.toString().equals(arrayFunc1Clone.toString())));

        // Тест для LinkedListTabulatedFunction
        System.out.println("\nТест клонирования LinkedListTabulatedFunction:");
        System.out.println("  Исходный объект linkedListFunc1: " + linkedListFunc1.toString());

        LinkedListTabulatedFunction linkedListFunc1Clone = (LinkedListTabulatedFunction) linkedListFunc1.clone();
        System.out.println("  Клон linkedListFunc1Clone: " + linkedListFunc1Clone.toString());

        System.out.println("  linkedListFunc1 == linkedListFunc1Clone: " + (linkedListFunc1 == linkedListFunc1Clone));
        System.out.println("  linkedListFunc1.equals(linkedListFunc1Clone): " + linkedListFunc1.equals(linkedListFunc1Clone));

        // Проверка глубокого клонирования для LinkedListTabulatedFunction
        System.out.println("\nПроверка глубокого клонирования (LinkedListTabulatedFunction):");
        System.out.println("  Изменяем исходный объект linkedListFunc1...");
        try {
            linkedListFunc1.setPointY(2, 200.0); // Меняем Y у точки с индексом 2
        } catch (Exception e) {
            System.out.println("  Ошибка при изменении точки: " + e.getMessage());
        }

        System.out.println("  Исходный объект linkedListFunc1 после изменения: " + linkedListFunc1.toString());
        System.out.println("  Клон linkedListFunc1Clone после изменения оригинала: " + linkedListFunc1Clone.toString());
        System.out.println("  Клон изменился? " + (!linkedListFunc1.toString().equals(linkedListFunc1Clone.toString())));

        // Дополнительные тесты
        System.out.println("\n6. ДОПОЛНИТЕЛЬНЫЕ ТЕСТЫ:");

        // Тест с пустой функцией (специальный случай)
        System.out.println("\nТест с функцией из 2 точек:");
        ArrayTabulatedFunction smallFunc = new ArrayTabulatedFunction(0.0, 1.0, 2);
        System.out.println("  smallFunc.toString(): " + smallFunc.toString());
        System.out.println("  smallFunc.hashCode(): " + smallFunc.hashCode());

        // Тест сравнения разных типов
        System.out.println("\nТест сравнения ArrayTabulatedFunction и LinkedListTabulatedFunction:");

        // Создаем одинаковые функции разными способами
        FunctionPoint[] identicalPoints = {
                new FunctionPoint(0.0, 1.0),
                new FunctionPoint(1.0, 2.0),
                new FunctionPoint(2.0, 3.0)
        };

        ArrayTabulatedFunction identicalArrayFunc = new ArrayTabulatedFunction(identicalPoints);
        LinkedListTabulatedFunction identicalLinkedListFunc = new LinkedListTabulatedFunction(identicalPoints);

        System.out.println("  identicalArrayFunc.toString(): " + identicalArrayFunc.toString());
        System.out.println("  identicalLinkedListFunc.toString(): " + identicalLinkedListFunc.toString());
        System.out.println("  identicalArrayFunc.equals(identicalLinkedListFunc): " + identicalArrayFunc.equals(identicalLinkedListFunc));
        System.out.println("  identicalLinkedListFunc.equals(identicalArrayFunc): " + identicalLinkedListFunc.equals(identicalArrayFunc));

        System.out.println("\n=== ТЕСТИРОВАНИЕ ЗАВЕРШЕНО ===");
    }
}