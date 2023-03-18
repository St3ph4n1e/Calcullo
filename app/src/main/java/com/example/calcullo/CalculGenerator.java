package com.example.calcullo;

import com.example.calcullo.Calcul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Classe pour générer des calculs aléatoires en fonction de la difficulté
 */
public class CalculGenerator {

    // Constantes pour les bornes supérieures des nombres aléatoires à générer
    private static final int MAX_NUM1 = 20;
    private static final int MAX_NUM2_EASY = 10;
    private static final int MAX_NUM2_MEDIUM = 20;
    private static final int MAX_NUM2_DIFFICULT = 30;

    private static List<Integer> generateRandomAnswers(int correctAnswer) {
        Random random = new Random();
        List<Integer> answers = new ArrayList<>();
        // Générer 3 réponses aléatoires distinctes de la réponse correcte
        while (answers.size() < 3) {
            int answer = random.nextInt(correctAnswer + 10) + 1; // ajouter 1 pour éviter une réponse de 0
            if (!answers.contains(answer) && answer != correctAnswer) {
                answers.add(answer);
            }
        }
        // Ajouter la réponse correcte
        answers.add(correctAnswer);
        // Trier les réponses
        Collections.shuffle(answers);
        return answers;
    }


    /**
     * Génère un calcul facile aléatoire (addition)
     *
     * @return un objet Calcul
     */
    public static Calcul easyCalcul() {
        Random random = new Random();
        int num1 = random.nextInt(MAX_NUM1 + 1); // +1 pour inclure la borne supérieure
        int num2 = random.nextInt(MAX_NUM2_EASY + 1);
        int correctAnswer = num1 + num2;
        List<Integer> answers = generateRandomAnswers(correctAnswer);

        return new Calcul (num1, num2, "+", correctAnswer, answers);
    }

    /**
     * Génère un calcul moyen aléatoire (soustraction)
     *
     * @return un objet Calcul
     */
    public static Calcul mediumCalcul() {
        Random random = new Random();
        int num1 = random.nextInt(MAX_NUM1 + 1);
        int num2 = random.nextInt(MAX_NUM2_MEDIUM + 1);
        int correctAnswer = num1 - num2;
        List<Integer> answers = generateRandomAnswers(correctAnswer);
        return new Calcul(num1, num2, "-", correctAnswer, answers);
    }

    /**
     * Génère un calcul difficile aléatoire (multiplication)
     *
     * @return un objet Calcul
     */
    public static Calcul difficultCalcul() {
        Random random = new Random();
        int num1 = random.nextInt(MAX_NUM1 + 1);
        int num2 = random.nextInt(MAX_NUM2_DIFFICULT + 1);
        int correctAnswer = num1 * num2;
        List<Integer> answers = generateRandomAnswers(correctAnswer);
        return new Calcul(num1, num2, "x", correctAnswer, answers);
    }

}
