package CardLimitServiceImpl;

import java.util.List;

public class LoggingMessage {

    private String customerNo = "";
    private String className = "";
    private String corrID = "";
    private String humanReadable = "";
    private String part = "";
    private String delimiter = "/";

    @Override
    public String toString(){
        return "*" + part + delimiter + customerNo + delimiter +
                className + delimiter +
                corrID + delimiter + humanReadable + delimiter + "customlog1";
    }

    public String toStringHR(){
        return "*" + humanReadable + delimiter + corrID + delimiter + "customhrlog1";
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setCorrID(String corrID) {
        this.corrID = corrID;
    }

    public void setHumanReadable(String humanReadable) {
        this.humanReadable = humanReadable;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }


    public LoggingMessage() {
    }

    public LoggingMessage(String customerNo, String corrID, String humanReadable, String className, String part) {
        this.customerNo = customerNo;
        this.corrID = corrID;
        this.humanReadable = humanReadable;
        this.className = className;
        this.part = part;
    }


}
