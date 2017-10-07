package f1.com.example.rohit.homeword4;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rohit on 2/7/2017.
 */

public class Question  implements Serializable{

    int id,canswer;
    int useranswer;
    String text,imageurl;
    ArrayList<String> choice;

    public int getUseranswer() {
        return useranswer;
    }

    public void setUseranswer(int useranswer) {
        this.useranswer = useranswer;
    }

    public int getCanswer() {
        return canswer;
    }

    public void setCanswer(int canswer) {
        this.canswer = canswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", canswer=" + canswer +
                ", text='" + text + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", choice=" + choice +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public ArrayList<String> getChoice() {
        return choice;
    }

    public void setChoice(ArrayList<String> choice) {
        this.choice = choice;
    }
}
