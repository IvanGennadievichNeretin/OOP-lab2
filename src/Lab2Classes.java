/**
 * Программа "Треугольники" принимает на вход череду вещественных чисел через пробел,
 * из которых формирует точки по трем координатам.
 *
 * Первые три числа распознаются, как координаты X, Y, Z первой точки, следующие
 * 3 числа - координаты второй точки и т.п.
 *
 * 3 точки (или 9 чисел) формируют треугольник. Программа вычисляет и выводит
 * площадь треугольника с некоторой погрешностью, или выводит сообщение, если
 * невозможно вычислить площадь.
 *
 * Процедура повторяется для каждого полного треугольника (формирующих его 9 чисел).
 * Лишние числа, не формирующие точки или треугольники, не учитываются.
 * **/
public class Lab2Classes {
    /** Входная точка программы **/
    public static void main(String[] args){
        Point3d[] pointsArray = formPointsArray(args);
        outputAllPoints(pointsArray);
        if (pointsArray.length < 3){
            System.out.println("Not enough points. You need 3 points (9 coordinates) at least");
            return;
        }

        double answer;
        int numberOfTriangle;
        for (int i = 0; i < pointsArray.length / 3; i+=1){
            answer = computeArea(pointsArray[i*3],pointsArray[(i*3)+1],pointsArray[(i*3)+2]);
            numberOfTriangle = i;
            System.out.print("Triangle no." + numberOfTriangle + ": ");
            if (answer != 0){
                System.out.println("area = " + answer);
            }
            else{
                System.out.println("Cannot calculate area");
            }
        }
    }

    /** Функция, вычисляющая площадь треугольника по трем точкам **/
    public static double computeArea(Point3d p1, Point3d p2, Point3d p3){
        //если какие-то из точек равны, то нет необходимости вычислять площадь
        if (p1.equals(p2) || p2.equals(p3) || p3.equals(p1)){
            System.out.println("Two or more points are identical. Calculation was cancelled");
            return 0;
        }
        //стороны треугольника
        double a = p1.distanceTo(p2);
        double b = p2.distanceTo(p3);
        double c = p3.distanceTo(p1);
        double p = (a + b + c) / 2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    /** Вывод информации о точке на экран **/
    public static void outputPoint(Point3d point, String name){
        System.out.println("Point " + name + ": X = "
                + point.getX() + "; Y = " + point.getY() + "; Z = " + point.getZ());
    }

    /** Функция вывода информации обо всех точках из массива точек **/
    public static void outputAllPoints(Point3d[] points){
        char[] alphabet = new char[] {'A','B','C'};
        String nameOfPoint;
        System.out.println("Created points: ");
        for (int i = 0; i < points.length; i++){
            if (i < alphabet.length){
                nameOfPoint = alphabet[i] + "";
            }
            else{
                int numberOfPoint = i / alphabet.length;
                nameOfPoint = alphabet[i % alphabet.length] + Integer.toString(numberOfPoint);
            }
            outputPoint(points[i],nameOfPoint);
        }
        System.out.println("End");
    }

    /** Функция создания массива точек из поступивших на вход значений **/
    public static Point3d[] formPointsArray(String[] args){
        //инициализация
        int i;
        int pointsCount = 0;
        int pointArrayLength = (args.length - (args.length % 3)) / 3; //сколько наборов из трех координат получится
        int length = args.length - (args.length % 3); //количество чисел, составляющих полный набор для точки
        //массив из трех чисел, в который будут переписываться координаты X, Y, Z
        double[] coords = new double[3];
        Point3d[] points = new Point3d[pointArrayLength]; //массив точек

        //создание точек из имеющихся наборов из трех чисел
        for (i = 0; i < length; i++){
            coords[i % 3] = Double.parseDouble(args[i]);
            if ((i+1) % 3 == 0){
                //создаем новую точку
                points[pointsCount] = new Point3d(coords[0],coords[1],coords[2]);
                pointsCount++;
            }
        }

        System.out.println(pointsCount + " points was created");
        return points;
    }
}
