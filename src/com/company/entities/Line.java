package com.company.entities;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

// W podanej przez Państwa klasie dodałem implementację interfejsu 'Serializable'
// Skorzystałem również z wzorca pośrednika serializacji opisanego w książę "Java efektywne programowanie"
public class Line implements Serializable {
    public final Point[] points;
    public final boolean someFlag;

    public Line(Point[] points, boolean someFlag) {
        this.points = points;
        this.someFlag = someFlag;
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream stream)
            throws InvalidObjectException {
        throw new InvalidObjectException("Invalid object");
    }

    private static class SerializationProxy implements Serializable  {
        public final Point[] points;
        public final boolean someFlag;

        SerializationProxy(Line line) {
            this.points = line.points;
            this.someFlag = line.someFlag;
        }

        private Object readResolve() {
            return new Line(points, someFlag);
        }
        private static final long serialVersionUID = 234098243823485285L;
    }
}