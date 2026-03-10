package lab2;

import java.util.Scanner;

public class CaesarCipher {
    private static final int CHAR_TABLE_SIZE = Character.MAX_VALUE + 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите текст для шифрования");
        String sourceText = scanner.nextLine();

        System.out.println("Введите ключ");
        int key = Integer.parseInt(scanner.nextLine());

        String encryptedText = transformText(sourceText, key);
        System.out.println("Текст после преобразования: " + encryptedText);

        while (true) {
            System.out.println("Выполнить обратное преобразование? (y/n)");
            String answer = scanner.nextLine();

            if ("y".equalsIgnoreCase(answer)) {
                String decryptedText = transformText(encryptedText, -key);
                System.out.println("Текст после преобразования: " + decryptedText);
                break;
            }

            if ("n".equalsIgnoreCase(answer)) {
                System.out.println("До свидания!");
                break;
            }

            System.out.println("Введите корректный ответ");
        }
    }

    private static String transformText(String text, int key) {
        StringBuilder result = new StringBuilder(text.length());

        for (int i = 0; i < text.length(); i++) {
            int shiftedCode = (text.charAt(i) + key) % CHAR_TABLE_SIZE;
            if (shiftedCode < 0) {
                shiftedCode += CHAR_TABLE_SIZE;
            }
            result.append((char) shiftedCode);
        }

        return result.toString();
    }
}
