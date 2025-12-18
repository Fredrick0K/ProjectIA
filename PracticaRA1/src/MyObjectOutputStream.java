
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyObjectOutputStream extends ObjectOutputStream {
	
    protected MyObjectOutputStream() 
    throws IOException, SecurityException {
        super();
    }
    
    public MyObjectOutputStream(OutputStream out) 
    throws IOException {
        super(out);
    }

    protected void writeStreamHeader() 
    throws IOException {}

}
