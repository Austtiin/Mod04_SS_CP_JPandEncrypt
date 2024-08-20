package App;

import javax.swing.*;

public class mainGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                homeFrame home = new homeFrame();
                home.setVisible(true);
            }
        });
    }
}
