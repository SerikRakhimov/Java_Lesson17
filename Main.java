// 1. Создание новой директории и файл средствами класса File.
// Пользователь указывает название директории и имя файла. В случае
// неудачи записи пользователь видит текст, указанный в exeption.
// 2. После создания файла пользователь попадает в раздел меню для
// записи в файл произвольного текста — пользователь вводит текст и
// при нажатии клавиши Enter производится запись текста в файл.
// Приложение оповещает о результатх выполненной операции.
// 3. После успешной записи текста, пользователю предлагается выбрать
// одну из операций:
// 1. Считать строки из файла и вывести результат на консоль.
// 2. Поменять местами первое и последнее слова в каждой строке
// и вывести результат на консоль.

import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the directory name = ");
        String dirname = in.nextLine();
        System.out.print("Enter the file name = ");
        String filename = in.nextLine();
        String fullname = dirname + '\\' + filename;
        String textsave = "";
        try {
            // создание каталога
            File dir = new File(dirname);
            boolean created = dir.mkdir();
            if (created)
                System.out.println("Folder has been created");
            // создание файла
            File myFile = new File(fullname);

            created = myFile.createNewFile();
            if (created)
                System.out.println("File has been created");

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        while (true) {
            System.out.println("Enter a line to write to the file (Empty - finish entering) = ");
            textsave = in.nextLine();
            if (textsave.isEmpty()) {
                break;
            }

            try (FileWriter writer = new FileWriter(fullname, true)) {
                writer.write(textsave + '\n');
                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        System.out.print("1 - Read lines from file and output result to console\n");
        System.out.print("2 - Swap the first and last words in each line and output the result to the console\n");
        System.out.print("Your choice (1,2) = ");

        int num = in.nextInt();
        String calcword;
        in.close();
        if (num == 1 || num == 2) {
            System.out.print("\nResults:\n");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fullname)))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // считать строки из файла и вывести результат в консоль
                    if (num == 1) {
                        System.out.println(line);
                    }
                    // поменять местами первое и последнее слова в каждой строке и вывести результат в консоль
                    if (num == 2) {
                        String[] words = line.split(" ");
                        // поменять местами первый и последний элемент массива words[] при выводе результата на консоль
                        for (int i = 0; i < words.length; i++) {
                            calcword = words[i];
                            if (i == 0) {
                                calcword = calcword = words[words.length - 1];
                            }
                            if (i == words.length - 1) {
                                calcword = calcword = words[0];
                            }
                            System.out.print(calcword + ' ');
                        }
                        System.out.println();
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            System.out.print("\n");
        }

    }
}
