package golf.course.object;

import golf.physics.*;
import com.badlogic.gdx.math.Vector3;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;

public class GameObject implements Serializable {
    public double friction = 0;        // coefficient of friction
    public double bounciness = 0;      // coefficient of restitution
    public Vector3 velocity = new Vector3(0, 0, 0);
    public Vector3 position = new Vector3(0, 0, 0);
    public Vector3 acceleration = new Vector3(0, 0, 0);
    public double mass = 0.1;
    public boolean moving = false;
    int xy = 2;
    public Vector3 dimensions = new Vector3(xy, xy, 2);

    @Override
    public GameObject clone() {
        GameObject p = new GameObject();
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            serializeToOutputStream(this, bos);
            byte[] bytes = bos.toByteArray();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            p = (GameObject)ois.readObject();
            return p;
        } catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return p;
    }

    private void serializeToOutputStream(Serializable ser, OutputStream os) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(ser);
            oos.flush();
        } finally {
            oos.close();
        }
    }
}