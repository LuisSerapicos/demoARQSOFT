package com.example.demo.student;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class SKUGeneratorService implements SKUGenerator{

    private static final int LETTERS_GROUP_1 = 3;
    private static final int DIGITS_GROUP_1 = 3;
    private static final int LETTERS_GROUP_2 = 2;
    private static final int DIGITS_GROUP_2 = 2;

    @Override
    public String generateSKUA() {
        StringBuilder sku = new StringBuilder();

        generateRandomLetters(sku, LETTERS_GROUP_1);
        generateRandomDigits(sku, DIGITS_GROUP_1);

        // Generate the second group (2 letters + 1 number)
        generateRandomLetters(sku, LETTERS_GROUP_2);
        generateRandomDigits(sku, DIGITS_GROUP_2);

        // Add a special character (you can customize this part)
        sku.append(generateRandomSpecialCharacter());

        return sku.toString();
    }

    private void generateRandomLetters(StringBuilder builder, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            char letter = (char) (random.nextInt(26) + 'a'); // Generating lowercase letters
            builder.append(letter);
        }
    }

    private void generateRandomDigits(StringBuilder builder, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }
    }

    private char generateRandomSpecialCharacter() {
        // You can customize this part to generate your desired special character
        // For example, generating a random character from a set of special characters
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        Random random = new Random();
        int index = random.nextInt(specialCharacters.length());
        return specialCharacters.charAt(index);
    }



    @Override
    public String generateSkuB(String designation) {

        try {
            // Create an MD5 hash of the product designation
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(designation.getBytes());

            // Convert the MD5 hash to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : bytes) {
                hexString.append(String.format("%02x", b));
            }

            // Select the 10 middle characters of the hexadecimal hash
            int startIndex = (hexString.length() - 10) / 2;
            int endIndex = startIndex + 10;

            return hexString.substring(startIndex, endIndex);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception
            return "";
        }
    }

    @Override
    public String generateSkuC(String designation) {
        String methodA = generateSKUA();
        String methodB = generateSkuB(designation);
        Random random = new Random();
        StringBuilder randomStringA = new StringBuilder();
        StringBuilder randomStringB = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(methodA.length());
            char randomChar = methodA.charAt(randomIndex);
            randomStringA.append(randomChar);
        }

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(methodB.length());
            char randomChar = methodB.charAt(randomIndex);
            randomStringB.append(randomChar);
        }

        return randomStringA + "" + randomStringB;
    }
}
