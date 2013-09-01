package org.crabapple.binding.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils {
	public static byte[] serializeObject(Object object) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(baos);
	    oos.writeObject(object);
	    oos.flush();
	    return baos.toByteArray();
	}
	
	public static Object deserializeObject(byte[] buf) throws IOException,
			ClassNotFoundException {
		Object object = null;
		ByteArrayInputStream sais = new ByteArrayInputStream(buf);
		ObjectInputStream ois = new ObjectInputStream(sais);
		object = ois.readObject();
		return object;
	}
}
