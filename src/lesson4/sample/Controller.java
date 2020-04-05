package lesson4.sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    public Button sendingButton;
    public TextArea messageTextArea;
    public TextField messageTextField;
    private String message = "";

    public void sendMessage(ActionEvent actionEvent) {
        if (!messageTextField.getText().equals("")) {
            messageTextArea.setText(message += ">" + messageTextField.getText() + "\n");
            messageTextField.clear();
            messageTextArea.setScrollTop(Double.POSITIVE_INFINITY);
        }
    }
}
