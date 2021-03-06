package br.com.lellis.pricecheck.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;

public class SerializableImage implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int NO_IMAGE = -1;

    private Bitmap image;

    public SerializableImage() {
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        if (image != null) {
            final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, stream);
            final byte[] imageByteArray = stream.toByteArray();
            out.writeInt(imageByteArray.length);
            out.write(imageByteArray);
        } else {
            out.writeInt(NO_IMAGE);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{

        final int length = in.readInt();

        if (length != NO_IMAGE) {
            final byte[] imageByteArray = new byte[length];
            image = BitmapFactory.decodeByteArray(imageByteArray, 0, length);
        }
    }
}