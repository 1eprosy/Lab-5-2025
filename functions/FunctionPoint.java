package functions;

import java.io.Serializable;
import java.util.Objects;

public class FunctionPoint implements Serializable {
    private static final long serialVersionUID = 1L;
    private double x;
    private double y;
    private static final double EPSILON = 1e-10; // Точность для сравнения double


    public FunctionPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public FunctionPoint(FunctionPoint point) {
        this.x = point.x;
        this.y = point.y;
    }

    public FunctionPoint() {
        this(0.0, 0.0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Возвращает текстовое описание точки в формате (x; y)
     * @return строковое представление точки
     */
    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }

    /**
     * Сравнивает текущую точку с другим объектом
     * @param obj объект для сравнения
     * @return true, если объекты равны (оба являются FunctionPoint с одинаковыми координатами)
     */
    @Override
    public boolean equals(Object obj) {
        // Проверяем, является ли объект тем же самым
        if (this == obj) {
            return true;
        }

        // Проверяем, является ли объект экземпляром FunctionPoint
        if (!(obj instanceof FunctionPoint)) {
            return false;
        }

        // Приводим к типу FunctionPoint
        FunctionPoint other = (FunctionPoint) obj;

        // Сравниваем координаты с учетом погрешности для double
        return Math.abs(this.x - other.x) < EPSILON &&
                Math.abs(this.y - other.y) < EPSILON;
    }

    /**
     * Возвращает хэш-код точки
     * @return хэш-код, рассчитанный на основе координат
     */
    @Override
    public int hashCode() {
        // Преобразуем каждую координату в long битовое представление
        long xBits = Double.doubleToLongBits(x);
        long yBits = Double.doubleToLongBits(y);

        // Разбиваем каждый long на два int (старшие и младшие 4 байта)
        int x1 = (int)(xBits >> 32);        // Старшие 4 байта x
        int x2 = (int)(xBits & 0xFFFFFFFFL); // Младшие 4 байта x

        int y1 = (int)(yBits >> 32);        // Старшие 4 байта y
        int y2 = (int)(yBits & 0xFFFFFFFFL); // Младшие 4 байта y

        // Применяем XOR для всех компонентов
        return x1 ^ x2 ^ y1 ^ y2;

        // Альтернативная, более простая реализация (если допускается):
        // return Objects.hash(x, y);
    }

    /**
     * Создает и возвращает копию текущей точки
     * @return копия объекта FunctionPoint
     */
    @Override
    public Object clone() {
        try {
            // Используем конструктор копирования
            return new FunctionPoint(this.x, this.y);
        } catch (Exception e) {
            // В данном случае исключение маловероятно, но для соблюдения сигнатуры
            throw new InternalError("Ошибка при клонировании");
        }
    }
}