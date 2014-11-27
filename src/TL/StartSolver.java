package TL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class StartSolver {
    public SudokuSolver solver;
    JPanel mainPanel;
    ArrayList<JTextField> values = new ArrayList<JTextField>(81);
    ArrayList<String> output = new ArrayList<String>();

    public static void main(String[] args) {
        StartSolver startSolver = new StartSolver();
        startSolver.createGUI();
    }

    public class MyStartListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            solver = new SudokuSolver();
            String input = "";
            for (int i = 0; i < 81; i++) {
                String value = values.get(i).getText();
                if (value.equals("")){
                    input = input + "0";
                } else {
                    input = input + value;
                }
            }

            output = solver.sudokuSolver(input);
            for (int i = 0; i < 81; i++) {
                values.get(i).setText(output.get(i));
            }
        }
    }

    public void createGUI() {
        JFrame frame = new JFrame("Sudoku solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] valuesList = new String[81];
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JLabel label = new JLabel("Type known digits and press start");



        background.add(BorderLayout.SOUTH, buttonBox);
        background.add(BorderLayout.NORTH, label);

        frame.getContentPane().add(background);

        GridLayout grid = new GridLayout(9,9);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 81; i++) {
            JTextField c = new JTextField("");
            c.setColumns(3);
            values.add(c);
            mainPanel.add(c);
        } // end loop

        frame.setBounds(50,50,500,500);
        frame.pack();
        frame.setVisible(true);
    }
}
