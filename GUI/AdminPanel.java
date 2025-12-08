package GUI;

import javax.swing.*;
import DataBase.Query;

public class AdminPanel extends JPanel {
    private Query db = new Query();
    private JTextArea area = new JTextArea(25, 80);

    public AdminPanel() {
        setLayout(new BorderLayout());
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);

        JButton btnShow = new JButton("Show All Data");
        btnShow.addActionListener(e -> {
            area.setText("");
            area.append("REQUESTS:\n");
            db.ShowAllRequests().forEach(s -> area.append(s + "\n"));
            area.append("\nTIME CARDS:\n");
            db.ShowAllTimeCards().forEach(s -> area.append(s + "\n"));
        });
        add(btnShow, BorderLayout.SOUTH);
        btnShow.doClick(); // load on start
    }
}