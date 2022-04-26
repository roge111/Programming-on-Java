package test.lab.common.Exeptions;

public class Incorrectfilestructur extends  Exception{
    public Incorrectfilestructur(){
    }

    public Incorrectfilestructur(String message){
        super(message);
    }
    public Incorrectfilestructur(Throwable cause){
        super(cause);
    }

    public Incorrectfilestructur(String message, Throwable cause){
        super (message, cause);
    }

    public Incorrectfilestructur(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);

    }
}
