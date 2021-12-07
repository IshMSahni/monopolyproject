package monopoly;


import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Serialization {

    public static ByteArrayOutputStream write(Serializable s){
        try {
            ByteArrayOutputStream byte_array_output_stream = new ByteArrayOutputStream();
            ObjectOutputStream object_output_stream = new ObjectOutputStream(byte_array_output_stream);
            object_output_stream.writeObject(s);
            object_output_stream.close();
            return byte_array_output_stream;
        } catch (Throwable t) {
            t.printStackTrace(System.err);
            return null;
        }
    }

    public static String write_base64(Serializable s){
        ByteArrayOutputStream os = write(s);
        if(os != null){
            return Base64.getEncoder().encodeToString(os.toByteArray());
        }
        else{
            return "";
        }
    }

    public static Object read(ByteArrayInputStream byte_array_input_stream){
        Object object = null;
        try {
            ObjectInput input = new ObjectInputStream(byte_array_input_stream);
            object = input.readObject();
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public static Object read_base64(String base64_str){
        ByteArrayInputStream byte_array_input_stream = new ByteArrayInputStream(Base64.getDecoder().decode(base64_str));

        return read(byte_array_input_stream);
    }

}