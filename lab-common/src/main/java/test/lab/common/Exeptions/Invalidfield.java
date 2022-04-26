package test.lab.common.Exeptions;

public class Invalidfield extends Exception{
    public  Invalidfield(){
    }

    public Invalidfield(String message){
        super(message);
    }

    public Invalidfield(Throwable cause){
        super(cause);

    }

    public Invalidfield(String message, Throwable cause){
        super(message, cause);
    }

    public Invalidfield(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
