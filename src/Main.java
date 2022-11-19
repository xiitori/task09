import util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static InputArgs parseArgs(String[] args) {
        InputArgs inputArgs = new InputArgs();
        if(args.length > 0 && args.length <= 5) {
            try {
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equals("--window")) {
                        inputArgs.window = true;
                        return inputArgs;
                    }
                    if (args[i].equals("--inputfile")) {
                        inputArgs.inputFile = args[i + 1];
                    }
                    if (args[i].equals("--outputfile")) {
                        inputArgs.outputFile = args[i + 1];
                    }
                }
            } catch (Exception e) {
            }
        } else {
            inputArgs.error = true;
        }
        return inputArgs;
    }

    public static void winMain() {
        Locale.setDefault(Locale.ROOT);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        InputArgs inputArgs = parseArgs(args);
        if (inputArgs.error) {
            System.err.println("Некорректные аргументы!");
            System.exit(1);
        }
        if(inputArgs.window) {
            winMain();
        } else {
            InputArgs Args = parseArgs(args);
            int [] array = {};
            try {
                array = ArrayUtils.readIntArrayFromFile(Args.inputFile);
            } catch (Exception e) {
                System.err.println("Не удается прочесть файл!");
                System.exit(2);
            }

            List<Integer> list = new ArrayList<Integer>();
            try {
                for (int el : array) {
                    list.add(el);
                }
            } catch (Exception ex) {
                System.err.println("Некорректный входной файл!");
                System.exit(3);
            }
            List<Integer> result = new ArrayList<Integer>(Calc.createNewList(list));
            int[] resultArray = new int[result.size()];
            for(int i = 0; i < resultArray.length; i++) {
                resultArray[i] = result.get(i);
            }
            try {
                ArrayUtils.writeArrayToFile(Args.outputFile, resultArray);
            } catch (Exception e) {
                System.err.println("Не указан выходной файл!");
                System.exit(4);
            }
        }
    }
}