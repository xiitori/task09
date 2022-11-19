import util.*;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class FrameMain extends JFrame{
    private JPanel panelMain;
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonLoadFromFile;
    private JButton buttonRandom;
    private JButton buttonSolution;
    private JButton buttonSaveInFile;
    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;

    public FrameMain() {
        this.setTitle("FrameMain");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableInput, 40, false, true, false, true);
        JTableUtils.initJTableForArray(tableOutput, 40, false, true, false, true);
        tableInput.setRowHeight(20);
        tableOutput.setRowHeight(20);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        JTableUtils.writeArrayToJTable(tableInput, ArrayUtils.createRandomIntMatrix(1, 5, 10));
        buttonLoadFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] array = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput, array);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    JTableUtils.writeArrayToJTable(tableInput, ArrayUtils.createRandomIntMatrix(tableInput.getRowCount(),tableInput.getColumnCount(), 10));
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int[] array = JTableUtils.readIntArrayFromJTable(tableInput);
                    List<Integer> list = new ArrayList<Integer>();
                    for(int el : array) {
                        list.add(el);
                    }
                    List<Integer> result = Calc.createNewList(list);
                    int[] resultArray = new int[result.size()];
                    for(int i = 0; i < resultArray.length; i++) {
                        resultArray[i] = result.get(i);
                    }
                    JTableUtils.writeArrayToJTable(tableOutput, resultArray);
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
        buttonSaveInFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        int[] array = JTableUtils.readIntArrayFromJTable(tableOutput);
                        ArrayUtils.writeArrayToFile(fileChooserSave.getSelectedFile().getPath(), array);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }
}
