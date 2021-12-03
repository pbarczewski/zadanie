package com.company.entities;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;


//W podanej przez Państwa klasie dodałem implementację interfejsu 'Serializable'
//Skorzystałem również z wzorca pośrednika serializacji opisanego w książę "Java efektywne programowanie"
public class Point implements Serializable {
    public final float x;
    public final float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream stream)
            throws InvalidObjectException {
        throw new InvalidObjectException("Invalid object");
    }

    private static class SerializationProxy implements Serializable  {
        public final float x;
        public final float y;

        SerializationProxy(Point point) {
            this.x = point.x;
            this.y = point.y;
        }

        private Object readResolve() {
            return new Point(x, y);
        }
        private static final long serialVersionUID = 234098243823485285L;
    }
}
