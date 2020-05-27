/**Класс трехмерной точки**/
public class Point3d extends Point2d {
    /** координата Z **/
    private double zCoord;
    /** Конструктор инициализации **/
    public Point3d (double x, double y, double z) {
        super(x, y);
        zCoord = z;
    }
    /** Конструктор преобразования **/
    public Point3d (Point2d point2d) {
        super(point2d.getX(), point2d.getY());
        zCoord = 0;
    }
    /** Конструктор по умолчанию. **/
    public Point3d () {
        this(0, 0, 0);
    }
    /** Возвращение координаты Z **/
    public double getZ () {
        return zCoord;
    }
    /** Установка значения координаты Z **/
    public void setZ (double val) {
        zCoord = val;
    }
    /** Сравнение двух точек в трехмерном пространстве**/
    public boolean equals(Point3d AnotherPoint){
        return  (super.getX() == AnotherPoint.getX()) &&
                (super.getY() == AnotherPoint.getY()) &&
                (this.getZ() == AnotherPoint.getZ());
    }
    /** Расстояние между двумя точками в трехмерном пространстве с точностью до
     * двух знаков после запятой **/
    public double distanceTo(Point3d point){
        return (double) Math.round(Math.sqrt(
                          Math.pow(point.getX() - this.getX(),2)
                        + Math.pow(point.getY() - this.getY(),2)
                        + Math.pow(point.getZ() - this.getZ(),2)
        ) * 100) / 100;
    }
}
