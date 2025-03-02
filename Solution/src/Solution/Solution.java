package Solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import StructureDonnee.ListNode;
import StructureDonnee.TreeNode;

public class Solution {

    /**
     * Convertit un chiffre romain en chiffre décimal.
     * 
     * ! La chaîne `s` est supposée être valide et contenir uniquement des chiffres romains.
     * 
     * Complexité :
     * * O(n) -> time (parcours linéaire de la chaîne)
     * * O(1) -> space (seule la HashMap fixe est utilisée)
     * 
     * @param s La chaîne représentant un nombre en chiffres romains.
     * @return La valeur entière correspondant au chiffre romain.
     */
    public int romanToInt(String s) {

        HashMap<Character, Integer> romanNumbers = new HashMap<Character, Integer>();
        romanNumbers.put('I', 1);
        romanNumbers.put('V', 5);
        romanNumbers.put('X', 10);
        romanNumbers.put('L', 50);
        romanNumbers.put('C', 100);
        romanNumbers.put('D', 500);
        romanNumbers.put('M', 1000);
        
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 && romanNumbers.get(s.charAt(i)) < romanNumbers.get(s.charAt(i + 1))) {
                result -= romanNumbers.get(s.charAt(i)); // Soustraction si un plus grand nombre suit
            } else {
                result += romanNumbers.get(s.charAt(i)); // Addition sinon
            }
        }
        return result;
    }

    /**
     * Permet de trouver le mots ou la chaine de charactère
     * le plus long dans la chaine de charactère sans caractères répétés 
     * 
     * ! Aucun espace dans la String envoyé
     * 
     * Complexité :
     * * O(n) -> time
     * * O(1), best - O(n) worst -> space
     * 
     * @param s
     * @return la taille de cette chaine de charactère
     */
    public int lengthOfLongestSubstring(String s) {
        
        Set<Character> lengthString = new HashSet<Character>();
        int length = s.length();
        int result = 0;
        int left = 0;

        for (int right = 0; right < length; right++) {
            while (lengthString.contains(s.charAt(right))) {
                lengthString.remove(s.charAt(left));
                left++;
            }
            lengthString.add(s.charAt(right));
            result = Math.max(result, right - left + 1);
        }
        return result;
    }

    /**
     * Génère toutes les combinaisons possibles de lettres correspondant aux chiffres d'un clavier de téléphone.
     * 
     * ! Seuls les chiffres de 2 à 9 sont acceptés.
     * 
     * Complexité :
     * * O(4ⁿ) -> time (pire cas, chaque chiffre a jusqu'à 4 lettres, ex: 7 et 9)
     * * O(4ⁿ) -> space (stockage des combinaisons générées dans la liste)
     * 
     * @param digits La chaîne de chiffres représentant l'entrée utilisateur.
     * @return Une liste contenant toutes les combinaisons possibles des lettres correspondantes.
     */
    public List<String> letterCombinations(String digits) {
        // Associe chaque chiffre aux lettres correspondantes du clavier téléphonique
        HashMap<Character, String> combinations = new HashMap<>();
        combinations.put('2', "abc");
        combinations.put('3', "def");
        combinations.put('4', "ghi");
        combinations.put('5', "jkl");
        combinations.put('6', "mno");
        combinations.put('7', "pqrs");
        combinations.put('8', "tuv");
        combinations.put('9', "wxyz");

        // Liste pour stocker les combinaisons générées
        List<String> result = new ArrayList<>();

        // Vérification de l'entrée : si vide, retourner une liste vide
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // Démarre la récursion pour générer les combinaisons
        findCombinations(result, new StringBuilder(), digits, 0, combinations);
        return result;
    }

     /**
     * Méthode récursive pour générer toutes les combinaisons possibles de lettres.
     * 
     * Complexité :
     * * O(4ⁿ) -> time (chaque chiffre peut ajouter jusqu'à 4 lettres)
     * * O(n) -> space (profondeur maximale de la récursion = longueur du chiffre)
     * 
     * @param result Liste contenant toutes les combinaisons générées.
     * @param current Chaîne en cours de formation.
     * @param digits Chaîne de chiffres d'entrée.
     * @param index Position actuelle dans la chaîne de chiffres.
     * @param combinations Map associant chaque chiffre à ses lettres correspondantes.
     */
    public void findCombinations(List<String> result, StringBuilder current, String digits, int index, HashMap<Character, String> combinations) {
        // Cas de base : si l'index atteint la longueur de la chaîne de chiffres
        if (index == digits.length()) {
            // Ajouter la combinaison actuelle à la liste des résultats
            result.add(current.toString());
            return;
        }

        // Récupérer le chiffre actuel
        char digit = digits.charAt(index);
        // Obtenir les lettres correspondantes à ce chiffre
        String letters = combinations.get(digit);

        // Parcourir toutes les lettres possibles pour le chiffre actuel
        for (char letter : letters.toCharArray()) {
            // Ajouter la lettre courante à la combinaison
            current.append(letter);
            // Récursion : traiter le chiffre suivant
            findCombinations(result, current, digits, index + 1, combinations);
            // Backtracking : retirer la dernière lettre ajoutée pour tester d'autres possibilités
            current.deleteCharAt(current.length() - 1);
        }
    }

    /**
     * Vérifie si un tableau d'entiers contient des doublons.
     * 
     * Complexité :
     * * O(n) -> time (on parcourt une seule fois le tableau)
     * * O(n) -> space (dans le pire des cas, tous les éléments sont uniques et stockés dans le Set)
     * 
     * @param nums Tableau d'entiers à analyser.
     * @return true si un doublon est trouvé, sinon false.
     */
    public boolean containsDuplicate(int[] nums) {

        // Set pour stocker les nombres déjà rencontrés
        Set<Integer> allNumbIntegers = new HashSet<Integer>();

        // Parcourt chaque élément du tableau
        for (int i = 0; i < nums.length; i++) {
            // Si l'élément est déjà dans le Set, alors il y a un doublon
            if (allNumbIntegers.contains(nums[i])) { 
                return true; 
            }
            // Ajouter l'élément au Set
            allNumbIntegers.add(nums[i]);
        }

        // Aucun doublon trouvé
        return false;
    }

    /**
     * Trouve la lettre supplémentaire dans la chaîne `t` par rapport à `s`.
     * Chaque lettre apparaît une seule fois dans `s`, sauf une en plus dans `t`.
     * 
     * Complexité :
     * * O(n) -> time (on parcourt chaque caractère une fois)
     * * O(1) -> space (on utilise seulement une variable `char`)
     * 
     * @param s Chaîne d'origine.
     * @param t Chaîne contenant une lettre supplémentaire.
     * @return Le caractère ajouté dans `t`.
     */
    public char findTheDifference(String s, String t) {
        char result = 0;

        // XOR de tous les caractères dans `s`
        for (char c : s.toCharArray()) {
            result ^= c;
        }
        // XOR de tous les caractères dans `t`
        for (char c : t.toCharArray()) {
            result ^= c;
        }

        return result;
    }

    /**
     * Trie les personnes par taille décroissante en maintenant leur nom associé.
     * 
     * Complexité :
     * * O(n log n) -> time (tri des hauteurs)
     * * O(n) -> space (utilisation d'une HashMap et d'un tableau)
     * 
     * @param names Tableau contenant les noms des personnes.
     * @param heights Tableau contenant les tailles associées aux noms.
     * @return Un tableau trié des noms en fonction des tailles décroissantes.
     */
    public String[] sortPeople(String[] names, int[] heights) {
        HashMap<Integer, String> peopleMHashMap = new HashMap<>();
        String[] result = new String[names.length];

        // Associer chaque taille à un nom
        for (int i = 0; i < heights.length; i++) {
            peopleMHashMap.put(heights[i], names[i]);
        }

        // Trier les tailles en ordre croissant
        Arrays.sort(heights);

        // Placer les noms triés en ordre décroissant dans le tableau résultat
        for (int i = 0; i < heights.length; i++) {
            result[i] = peopleMHashMap.get(heights[(heights.length - 1) - i]);
        }

        return result; 
    }

    /**
     * Cette méthode prend une chaîne binaire en entrée et retourne la plus grande chaîne binaire
     * impair possible en réorganisant les chiffres de la chaîne d'origine. Le dernier chiffre
     * de la chaîne résultante sera toujours '1' pour garantir qu'il s'agit d'un nombre impair.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la longueur de la chaîne d'entrée.
     * * O(n) -> espace, pour stocker les caractères de la chaîne résultante.
     * 
     * @param s La chaîne binaire d'entrée.
     * @return La plus grande chaîne binaire impair possible.
     */
    public String maximumOddBinaryNumber(String s) {
        char[] input = s.toCharArray();
        Queue<String> output = new LinkedList<>();
        int length = input.length;
        StringBuilder result = new StringBuilder(length);     
        int ones = 0;

        for (int i = 0; i < input.length; i++){
            if (input[i] == '1'){ones++;}
        }

        for (; ones > 1; ones--){
            output.add("1");
            length--;
        }

        for (; length > 1; length--){ output.add("0"); }

        for (int j = 1; input.length > j; j++){ result.append(output.remove()); }

        result.append("1");
        return result.toString();
    }


    /**
     * Cette méthode prend un tableau d'entiers et retourne un tableau où chaque élément est le carré de 
     * l'élément correspondant dans le tableau d'origine, puis trie ce tableau dans l'ordre croissant.
     * 
     * Complexité :
     * * O(n log n) -> temps, où n est la taille du tableau. La complexité provient de l'appel à Arrays.sort().
     * * O(1) -> espace additionnel, car l'algorithme trie le tableau en place.
     * 
     * @param nums Le tableau d'entiers d'entrée.
     * @return Un tableau d'entiers où chaque élément est le carré des éléments de nums, trié dans l'ordre croissant.
     */
    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++){
            nums[i] = nums[i] * nums[i];
        }

        Arrays.sort(nums);
        return nums;
    }


    /**
     * Cette méthode calcule la longueur minimale d'une chaîne de caractères après avoir supprimé
     * les caractères à partir des deux extrémités s'ils sont égaux.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la longueur de la chaîne, car chaque caractère de la chaîne est comparé.
     * * O(1) -> espace additionnel, car seul un nombre fixe de variables sont utilisées.
     * 
     * @param s La chaîne de caractères d'entrée.
     * @return La longueur minimale de la chaîne après suppression des caractères égaux aux extrémités.
     */
    public int minimumLength(String s) {
        char[] input = s.toCharArray();
        int middle = Math.floorDiv(input.length, 2);
        
        int stops = 0, result = 0;
        int twoRight = 0, twoLeft = 0;

        for (int i = 0; i < middle; i++){
            if (input[i + twoLeft] != input[input.length - 1 - i - twoRight]){ break; }
            if (input[i + 1] == input[i]) { twoLeft++; }
            if (input[input.length - 1 - i - twoRight] == input[input.length - 2 - i - twoRight]) { twoRight++; }
            stops++;
        }

        if (stops >= middle){ result = 0; } 
        else { result = input.length - (twoLeft + twoRight) - 2 * stops; }
        
        return result;
    }


    /**
     * Cette méthode calcule le nombre total d'occurrences des éléments ayant la fréquence maximale dans le tableau.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la taille du tableau, car chaque élément est parcouru une fois.
     * * O(n) -> espace additionnel, car une HashMap est utilisée pour stocker les fréquences des éléments.
     * 
     * @param nums Le tableau d'entiers.
     * @return Le total des occurrences des éléments ayant la fréquence maximale.
     */
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxFreq = 0;

        // Iterate through the array and populate the map
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(num));
        }

        int res = 0;
        // Iterate through the map to find elements with frequency equal to maxFreq
        for (int key : map.keySet()) {
            if (map.get(key) == maxFreq) { res += map.get(key); }
        }

        return res;
    }

    /**
     * Cette méthode trouve le premier élément commun entre deux tableaux d'entiers.
     * Si un élément commun est trouvé, il est retourné. Sinon, la méthode retourne -1.
     * 
     * Complexité :
     * * O(n + m) -> temps, où n est la taille du premier tableau (nums1) et m est la taille du second tableau (nums2),
     * *    car nous parcourons une fois chaque tableau pour remplir le Set et ensuite vérifier la présence des éléments du second tableau.
     * * O(n) -> espace additionnel, car un Set est utilisé pour stocker les éléments du premier tableau.
     * 
     * @param nums1 Le premier tableau d'entiers.
     * @param nums2 Le second tableau d'entiers.
     * @return Le premier élément commun entre les deux tableaux, ou -1 si aucun élément commun n'est trouvé.
     */
    public int getCommon(int[] nums1, int[] nums2) {
        Set<Integer> setNumbersOne = new HashSet<>();
        for(int num : nums1){ setNumbersOne.add(num); }

        int result = -1;
        for (int num : nums2){
            if (setNumbersOne.contains(num)){ return num;}
        }

        return result;
    }

    /**
     * Cette méthode utilise la technique des deux pointeurs pour trouver le premier élément commun entre 
     * deux tableaux triés d'entiers. Si un élément commun est trouvé, il est retourné. Sinon, la méthode retourne -1.
     * 
     * Complexité :
     * * O(n + m) -> temps, où n est la taille du premier tableau (nums1) et m est la taille du second tableau (nums2).
     * *  Nous utilisons deux pointeurs pour parcourir les deux tableaux simultanément, ce qui permet une recherche plus rapide 
     * *  comparée à la méthode utilisant un Set.
     * * O(1) -> espace additionnel, car nous utilisons simplement deux variables pour les pointeurs sans structure de données supplémentaire.
     * 
     * @param nums1 Le premier tableau d'entiers trié.
     * @param nums2 Le second tableau d'entiers trié.
     * @return Le premier élément commun entre les deux tableaux, ou -1 si aucun élément commun n'est trouvé.
     */
    public int getCommonTwoPointers(int[] nums1, int[] nums2) {
        int pointerFirst = 0;
        int pointerSecond = 0;

        while(pointerFirst < nums1.length && pointerSecond < nums2.length){
            if (nums1[pointerFirst] == nums2[pointerSecond]){return nums1[pointerFirst];}
            else if (nums1[pointerFirst] > nums2[pointerSecond]){pointerSecond++;}
            else if (nums1[pointerFirst] < nums2[pointerSecond]){pointerFirst++;}
        }

        return -1;
    }

    /**
     * Cette méthode trouve l'intersection de deux tableaux d'entiers, c'est-à-dire les éléments communs présents
     * dans les deux tableaux. L'intersection est retournée sous forme de tableau, avec chaque élément apparaissant une seule fois.
     * 
     * Complexité :
     * * O(n log n + m log m) -> temps, où n est la taille du premier tableau (nums1) et m est la taille du second tableau (nums2).
     * *  La complexité vient du tri des deux tableaux (chaque tri ayant une complexité de O(n log n) et O(m log m)).
     * *  Ensuite, l'itération des deux tableaux avec deux pointeurs prend un temps linéaire O(n + m).
     * * O(n + m) -> espace, en raison de l'utilisation d'une ArrayList pour stocker les résultats et de la conversion en tableau.
     * 
     * @param nums1 Le premier tableau d'entiers.
     * @param nums2 Le second tableau d'entiers.
     * @return Un tableau contenant les éléments communs entre les deux tableaux, sans doublon.
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int pointerFirst = 0;
        int pointerSecond = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        while(pointerFirst < nums1.length && pointerSecond < nums2.length){
            if (nums1[pointerFirst] == nums2[pointerSecond]){
                if (result.isEmpty() || !result.contains(nums1[pointerFirst])) {
                    result.add(nums1[pointerFirst]);
                }
                pointerFirst++;
                pointerSecond++;
            }
            else if (nums1[pointerFirst] > nums2[pointerSecond]){ pointerSecond++; }
            else if (nums1[pointerFirst] < nums2[pointerSecond]){ pointerFirst++; }
        }

        int[] intersectionArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            intersectionArray[i] = result.get(i);
        }

        return intersectionArray;
    }

    /**
     * Cette méthode trouve l'intersection de deux tableaux d'entiers, c'est-à-dire les éléments communs présents
     * dans les deux tableaux. L'intersection est retournée sous forme de tableau, avec chaque élément apparaissant une seule fois.
     * 
     * Complexité :
     * * O(n + m) -> temps, où n est la taille du premier tableau (nums1) et m est la taille du second tableau (nums2).
     * *  La complexité vient de l'itération sur les deux tableaux, chaque itération prenant un temps constant O(1) grâce à l'utilisation de HashSet.
     * * O(n + m) -> espace, en raison de l'utilisation d'un HashSet pour stocker les éléments du premier tableau et d'une ArrayList pour stocker les résultats.
     * 
     * @param nums1 Le premier tableau d'entiers.
     * @param nums2 Le second tableau d'entiers.
     * @return Un tableau contenant les éléments communs entre les deux tableaux, sans doublon.
     */
    public int[] intersectionOther(int[] nums1, int[] nums2) {
        Set<Integer> setNumbersOne = new HashSet<>();
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int num : nums1){
            if (!setNumbersOne.contains(num)) { setNumbersOne.add(num); }
        }

        for (int num : nums2){
            if (setNumbersOne.contains(num) && !result.contains(num)){ result.add(num); }  
        }

        int[] intersectionArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) { intersectionArray[i] = result.get(i); }

        return intersectionArray;
    }

    /**
     * Cette méthode trie les caractères de la chaîne `s` en fonction de l'ordre défini par la chaîne `order`.
     * Les caractères présents dans `order` apparaîtront dans le résultat dans le même ordre,
     * suivis des caractères qui ne sont pas présents dans `order` (dans l'ordre où ils apparaissent dans `s`).
     * 
     * Complexité :
     * * O(n + m) -> temps, où n est la longueur de la chaîne `s` et m est la longueur de la chaîne `order`.
     * *  La complexité vient de l'itération sur la chaîne `s` pour compter les occurrences des caractères (O(n)),
     * *  et l'itération sur `order` pour ajouter les caractères dans le bon ordre (O(m)).
     * * O(n) -> espace, en raison de l'utilisation d'une `HashMap` pour stocker les fréquences des caractères dans `s`.
     * 
     * @param order La chaîne définissant l'ordre de tri des caractères.
     * @param s La chaîne de caractères à trier.
     * @return La chaîne `s` triée selon l'ordre défini par `order`, suivie des autres caractères.
     */
    public String customSortString(String order, String s){
        HashMap<Character,Integer> map = new HashMap<>();

        // Comptage des occurrences de chaque caractère dans la chaîne s
        for(char letter : s.toCharArray()){ map.put(letter, map.getOrDefault(letter, 0) + 1); }

        StringBuilder result = new StringBuilder();

        // Ajout des caractères dans l'ordre donné par "order"
        for(char letter : order.toCharArray()){
            if(map.containsKey(letter)){
                for(int i = 0; i < map.get(letter); i++){ result.append(letter); }
            }
            map.remove(letter);
        }

        // Ajout des caractères restants (ceux qui ne sont pas dans "order")
        if(!map.isEmpty()){
            for(char letter : map.keySet()){
                for(int i = 0; i < map.get(letter); i++){ result.append(letter); }
            }
        }

        return result.toString();   
    }

    /**
     * Cette méthode supprime toutes les sous-listes consécutives d'une liste chaînée où la somme des éléments est égale à zéro.
     * Elle utilise une `HashMap` pour suivre la somme cumulative des éléments jusqu'à chaque nœud.
     * Si une somme cumulative identique est trouvée à un autre nœud, cela signifie que la sous-liste entre ces deux nœuds a une somme de zéro et doit être supprimée.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre de nœuds dans la liste. Chaque nœud est traité une seule fois et la recherche et l'insertion dans la `HashMap` prennent un temps constant en moyenne.
     * * O(n) -> espace, en raison de l'utilisation d'une `HashMap` pour suivre les sommes cumulées et les nœuds associés.
     * 
     * @param head La tête de la liste chaînée.
     * @return La nouvelle tête de la liste chaînée après avoir supprimé les sous-listes de somme nulle.
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        HashMap<Integer, ListNode> map = new HashMap<>();

        // Dummy node to simplify the head deletion case
        ListNode front = new ListNode(0, head);
        ListNode currentNode = front;

        int sumList = 0;

        while (currentNode != null) {
            sumList += currentNode.val;

            if (map.containsKey(sumList)) {
                ListNode previousNode = map.get(sumList);
                currentNode = previousNode.next;

                int deleteMap = sumList + currentNode.val;

                while (deleteMap != sumList) {
                    map.remove(deleteMap);
                    currentNode = currentNode.next;
                    deleteMap += currentNode.val;
                }

                previousNode.next = currentNode.next;
            } else {
                map.put(sumList, currentNode);
            }

            // Move to the next node
            currentNode = currentNode.next;
        }

        return front.next;
    }

    /**
     * Cette méthode trouve l'entier pivot dans la séquence des entiers de 1 à n, 
     * tel que la somme des entiers à gauche du pivot est égale à la somme des entiers à droite du pivot.
     * Elle calcule d'abord la somme totale des entiers de 1 à n, puis itère à travers la séquence en maintenant les sommes 
     * à gauche et à droite du pivot pour vérifier si elles sont égales.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la valeur de l'entier. Nous effectuons une seule passe à travers les entiers de 1 à n.
     * * O(1) -> espace additionnel, car nous n'utilisons qu'un nombre constant de variables.
     * 
     * @param n L'entier maximum de la séquence (1 à n).
     * @return L'entier pivot si trouvé, sinon -1 si aucun pivot n'existe.
     */
    public int pivotInteger(int n) {
        int nullResult = -1;
        int totalSum = n * (n + 1) / 2;

        int leftSum = 0;
        int rightSum = totalSum;

        for (int pivot = 1; pivot <= n; pivot++) {
            leftSum += pivot;
            if (leftSum == rightSum) { return pivot; }
            rightSum -= pivot;
        }

        return nullResult;
    }

    /**
     * Cette méthode calcule, pour chaque élément d'un tableau, le produit de tous les autres éléments 
     * sans inclure l'élément actuel. Elle utilise deux passes : une pour calculer les produits à gauche 
     * de chaque élément et une autre pour calculer les produits à droite.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la longueur du tableau, car nous effectuons deux passes sur le tableau.
     * * O(n) -> espace additionnel, car nous utilisons un tableau supplémentaire pour stocker les produits à droite.
     * 
     * @param nums Le tableau d'entrée dont les produits doivent être calculés.
     * @return Un tableau contenant les produits de tous les autres éléments pour chaque index.
     */
    public int[] productExceptSelf(int[] nums) {
        int[] right = new int[nums.length];

        int size = nums.length - 1;
        right[size] = 1;

        for (int i = 1; i < nums.length; i++) { right[size - i] = nums[size - i + 1] * right[size - i + 1]; }

        int leftNext = 1;
        for (int i = 0; i < nums.length; i++) {
            int result = leftNext * right[i];
            leftNext *= nums[i];
            nums[i] = result;
        }

        return nums;
    }

    /**
     * Cette méthode insère un nouvel intervalle dans une liste d'intervalles triée, en fusionnant si nécessaire.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre d'intervalles dans la liste, car chaque intervalle est comparé au nouvel intervalle et inséré.
     * * O(n) -> espace, car une nouvelle liste est créée pour stocker les résultats.
     * 
     * @param intervals Les intervalles existants, triés par leur début.
     * @param newInterval L'intervalle à insérer.
     * @return La liste des intervalles après insertion, triée et fusionnée.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<int[]>();
        int iterator = 0;

        if (intervals.length == 0) { return new int[][] {newInterval}; }

        // Ajouter tous les éléments avant le nouvel intervalle
        while (iterator < intervals.length && intervals[iterator][1] < newInterval[0]) {
            res.add(intervals[iterator]);
            iterator++;
        }

        // Enregistrer le nouvel intervalle
        while (iterator < intervals.length && intervals[iterator][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[iterator][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[iterator][1]);
            iterator++;
        }
        res.add(newInterval);

        // Ajouter tous les intervalles après le nouvel intervalle
        while (iterator < intervals.length) {
            res.add(intervals[iterator]);
            iterator++; 
        }

        return res.toArray(new int[res.size()][]);
    }

    /**
     * Cette méthode détermine le nombre minimum de flèches nécessaires pour percer tous les ballons.
     * Les ballons sont représentés par des intervalles (points), et une flèche peut percer tous les ballons
     * qui ont un intervalle de chevauchement avec la flèche.
     * 
     * Complexité :
     * * O(n log n) -> temps, où n est le nombre de ballons, car il faut trier les intervalles.
     * * O(1) -> espace, car l'algorithme utilise un nombre constant d'espace supplémentaire.
     * 
     * @param points Un tableau d'intervalles représentant les ballons (début, fin).
     * @return Le nombre minimum de flèches nécessaires pour percer tous les ballons.
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) { return 0; }

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int currentEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > currentEnd) {
                arrows++;
                currentEnd = points[i][1];
            }
        }

        return arrows;
    }

    /**
     * Cette méthode renvoie le k-ième élément distinct dans un tableau de chaînes de caractères.
     * Si cet élément distinct n'existe pas, elle renvoie une chaîne vide.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre d'éléments dans le tableau, car chaque élément est inséré et vérifié dans la carte.
     * * O(n) -> espace, car la carte utilise un espace proportionnel au nombre d'éléments distincts.
     * 
     * @param arr Le tableau de chaînes de caractères.
     * @param k L'indice de l'élément distinct à trouver (1-indexé).
     * @return Le k-ième élément distinct, ou une chaîne vide si ce n'est pas possible.
     */
    public String kthDistinct(String[] arr, int k) {
        LinkedHashMap<String, Boolean> map = new LinkedHashMap<>();
        int iterator = 0;

        for (String sample : arr){
            if (map.containsKey(sample)) { map.put(sample, false); } 
            else { map.put(sample, true); }
        }

        for (String sample : map.keySet()) {
            if (map.get(sample)) {
                iterator++;
                if (iterator == k) { return sample; }
            }
        }

        return "";
    }

    /**
     * Cette méthode vérifie si un nombre entier est un palindrome.
     * Un palindrome est un nombre qui se lit de la même manière de gauche à droite et de droite à gauche.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre de chiffres dans l'entier, car chaque chiffre est comparé.
     * * O(n) -> espace, car une chaîne de caractères est utilisée pour représenter le nombre.
     * 
     * @param x Le nombre entier à vérifier.
     * @return true si x est un palindrome, false sinon.
     */
    public boolean isPalindrome(int x) {
        if(x < 0){ return false; }
        if(0 < x && x < 10){ return true; }
        if(x % 10 == 0){ return false; }

        String numberStr = Integer.toString(x);
        char[] matrix = numberStr.toCharArray();

        int left = 0;
        int right = matrix.length - 1;
        
        while (left < right) {
            if (matrix[left] != matrix[right]) { return false; }
            left++;
            right--;
        }

        return true;
    }

    /**
     * Cette méthode vérifie si deux chaînes de caractères peuvent être considérées comme un "swap".
     * Elle compare les caractères des deux chaînes, un à un, depuis les extrémités vers le centre.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la longueur des chaînes de caractères, car chaque caractère des chaînes est comparé.
     * * O(1) -> espace, car seule une variable pour les indices gauche et droit est utilisée.
     * 
     * @param s1 La première chaîne de caractères à comparer.
     * @param s2 La deuxième chaîne de caractères à comparer.
     * @return true si les chaînes sont des "swap", sinon false.
     */
    public boolean isSwap(String s1, String s2){   
        int left = 0, right = s2.length() - 1;

        if (s1.length() != s2.length()){ return false; }

        for(int i = 0; i < s1.length(); i++){
            if (s1.charAt(left) != s2.charAt(right)){ return false; }
            left++;
            right--;
        }

        return true;
    }

    /**
     * Cette méthode trouve le préfixe commun le plus long parmi un tableau de chaînes de caractères.
     * Elle compare chaque chaîne avec le préfixe actuel, et le réduit si nécessaire.
     * 
     * Complexité :
     * * O(n*m) -> temps, où n est le nombre de chaînes et m est la longueur de la chaîne la plus longue. 
     * *    Chaque comparaison de préfixe peut prendre jusqu'à m comparaisons.
     * * O(1) -> espace, car seules quelques variables sont utilisées.
     * 
     * @param strs Le tableau de chaînes de caractères.
     * @return Le préfixe commun le plus long.
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) { return ""; }
        if (strs.length == 1) { return strs[0]; }

        String firstPrefixe = strs[0];

        for (String words : strs) {
            if(firstPrefixe.equals("")){ break; }
            if(!words.startsWith(firstPrefixe)){
                firstPrefixe = findPrefixe(words, firstPrefixe);
            }
        }

        return firstPrefixe;
    }

    /**
     * Cette méthode trouve le plus long préfixe commun entre deux chaînes de caractères.
     * Elle parcourt les deux chaînes et ajoute les caractères correspondants au préfixe.
     * 
     * Complexité :
     * * O(m) -> temps, où m est la longueur de la chaîne la plus courte entre s et t. 
     * *    Chaque caractère des deux chaînes est comparé jusqu'à ce que le préfixe cesse.
     * * O(m) -> espace, car le préfixe est stocké dans une nouvelle chaîne de longueur m.
     * 
     * @param s La première chaîne de caractères.
     * @param t La deuxième chaîne de caractères.
     * @return Le préfixe commun le plus long.
     */
    public String findPrefixe(String s, String t){
        StringBuilder prefix = new StringBuilder();
        
        int minLength = Math.min(s.length(), t.length());

        for (int i = 0; i < minLength; i++) {
            if (s.charAt(i) == t.charAt(i)) {prefix.append(s.charAt(i));} 
            else {break;}
        }
        return prefix.toString();
    }

    /**
     * Cette méthode vérifie si une chaîne de caractères contenant des parenthèses, crochets et accolades est valide.
     * Une chaîne est valide si chaque ouverture de parenthèse a une fermeture correspondante dans le bon ordre.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la longueur de la chaîne, car chaque caractère est analysé une seule fois.
     * * O(n) -> espace, où n est la longueur maximale de la pile qui contient les parenthèses ouvertes.
     * 
     * @param s La chaîne de caractères contenant des parenthèses, crochets et accolades.
     * @return true si la chaîne est valide, false sinon.
     */
    public boolean isValid(String s) {
        Stack<Character> allbrackets = new Stack<>();

        for (char bracket : s.toCharArray()){
            if (bracket == '(') {allbrackets.push(')');} 
            else if (bracket == '[') {allbrackets.push(']');}  
            else if (bracket == '{') {allbrackets.push('}');}
            else {
                if (allbrackets.isEmpty()) {return false;}
                if (allbrackets.peek() != bracket){return false;}
                else {allbrackets.pop();}
            }
        }

        return allbrackets.isEmpty();
    }

    /**
     * Cette méthode fusionne deux listes triées en une seule liste triée.
     * Elle compare les éléments des deux listes et les ajoute dans l'ordre.
     * 
     * Complexité :
     * * O(n + m) -> temps, où n est la longueur de la première liste et m la longueur de la deuxième liste,
     * *    car chaque élément des deux listes est visité une seule fois.
     * * O(1) -> espace, car seule une quantité constante de mémoire est utilisée pour les pointeurs.
     * 
     * @param list1 La première liste triée.
     * @param list2 La deuxième liste triée.
     * @return La nouvelle liste triée résultant de la fusion des deux listes.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        current.next = list1 != null ? list1 : list2;
        return dummy.next;
    }

    /**
     * Cette méthode supprime les doublons d'un tableau trié en place.
     * Elle déplace les éléments uniques dans les premières positions du tableau.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la longueur du tableau, car chaque élément est vérifié une seule fois.
     * * O(1) -> espace, car aucune mémoire supplémentaire n'est utilisée (modification en place).
     * 
     * @param nums Le tableau d'entiers trié.
     * @return Le nombre d'éléments uniques dans le tableau.
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) { return 0; }
        int iteretor = 0;

        for (int i = 0; i < nums.length; i++) {
            if (iteretor == 0 || nums[i] != nums[iteretor - 1]) {
                nums[iteretor] = nums[i];
                iteretor++;
            }
        }
        return iteretor;
    }

    /**
     * Cette méthode supprime toutes les occurrences d'un élément spécifique dans un tableau en place.
     * Elle déplace les éléments différents de `val` dans les premières positions du tableau.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la longueur du tableau, car chaque élément est vérifié une seule fois.
     * * O(1) -> espace, car aucune mémoire supplémentaire n'est utilisée (modification en place).
     * 
     * @param nums Le tableau d'entiers.
     * @param val L'entier à supprimer du tableau.
     * @return Le nombre d'éléments restants dans le tableau après suppression des occurrences de `val`.
     */
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) { return 0; }
        int iterator = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[iterator] = nums[i];
                iterator++;
            }
        }
        return iterator;
    }

    /**
     * Cette méthode recherche la première occurrence de la sous-chaîne `needle` dans la chaîne `haystack`.
     * Si `needle` est une chaîne vide, elle renvoie 0.
     * Si `needle` n'est pas trouvé, elle renvoie -1.
     * 
     * Complexité :
     * * O(n * m) -> temps, où n est la longueur de `haystack` et m est la longueur de `needle`, 
     * *    car pour chaque position dans `haystack`, une comparaison de `m` caractères est effectuée.
     * * O(1) -> espace, car aucune mémoire supplémentaire n'est utilisée à part des variables temporaires.
     * 
     * @param haystack La chaîne dans laquelle on recherche la sous-chaîne.
     * @param needle La sous-chaîne à rechercher.
     * @return L'index de la première occurrence de `needle` dans `haystack`, ou -1 si elle n'est pas trouvée.
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) { return 0; }

        int needleLength = needle.length();
        int haystackLength = haystack.length();

        for (int i = 0; i <= haystackLength - needleLength; i++) {
            if (haystack.substring(i, i + needleLength).equals(needle)) { return i; }
        }

        return -1;
    }

    /**
     * Cette méthode recherche la position d'insertion de `target` dans le tableau trié `nums`.
     * Si `target` existe déjà, elle renvoie son index.
     * Sinon, elle renvoie l'index où `target` devrait être inséré pour maintenir l'ordre trié.
     * 
     * Complexité :
     * * O(log n) -> temps, où n est la longueur de `nums`, car on effectue une recherche binaire, divisant l'espace de recherche par 2 à chaque itération.
     * * O(1) -> espace, car seule une quantité constante de mémoire est utilisée pour les indices.
     * 
     * @param nums Le tableau trié dans lequel on recherche la position d'insertion.
     * @param target L'élément à insérer dans le tableau.
     * @return L'index de la position d'insertion de `target`.
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) { return mid; }
            else if (nums[mid] > target) { high = mid - 1; }
            else { low = mid + 1; }
        }
        
        return low;
    }

    /**
     * Cette méthode renvoie la longueur du dernier mot dans une chaîne de caractères `s`.
     * Un mot est défini comme une séquence de caractères non espacés.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la longueur de la chaîne `s`, car chaque caractère de la chaîne est parcouru une fois.
     * * O(1) -> espace, car seule une variable supplémentaire est utilisée pour compter la longueur du dernier mot.
     * 
     * @param s La chaîne de caractères dont on souhaite trouver la longueur du dernier mot.
     * @return La longueur du dernier mot dans la chaîne.
     */
    public int lengthOfLastWord(String s) {
        int dummyIterator = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') { dummyIterator++; }
            else if (dummyIterator > 0) { break; }
        }

        return dummyIterator;
    }

    /**
     * Cette méthode ajoute un à un nombre représenté par un tableau d'entiers `digits`.
     * Le tableau représente un nombre entier où chaque élément du tableau est un chiffre du nombre.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la longueur du tableau `digits`, car chaque élément du tableau est parcouru au plus une fois.
     * * O(n) -> espace, où n est la longueur du tableau `digits`, car un nouveau tableau de taille n+1 est créé si nécessaire.
     * 
     * @param digits Le tableau d'entiers représentant le nombre.
     * @return Le tableau d'entiers représentant le nombre après l'ajout de un.
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] result = new int[n + 1];
        result[0] = 1;

        return result;
    }

    /**
     * Cette méthode additionne deux chaînes de caractères binaires et renvoie le résultat sous forme de chaîne binaire.
     * 
     * Complexité :
     * * O(max(m, n)) -> temps, où m et n sont les longueurs des chaînes `a` et `b` respectivement. La boucle itère jusqu'à ce que les deux chaînes soient parcourues.
     * * O(max(m, n)) -> espace, car nous utilisons un StringBuilder pour stocker le résultat, et ce dernier peut avoir une taille maximale de max(m, n) + 1 (en raison du report final).
     * 
     * @param a La première chaîne binaire.
     * @param b La deuxième chaîne binaire.
     * @return La chaîne binaire représentant la somme de `a` et `b`.
     */
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            if (i >= 0) { sum += a.charAt(i--) - '0'; }
            if (j >= 0) { sum += b.charAt(j--) - '0'; }

            carry = sum / 2;
            result.append(sum % 2);
        }

        return result.reverse().toString();
    }

    /**
     * Cette méthode calcule la racine carrée entière d'un nombre non négatif `x` en utilisant une approche de recherche binaire.
     * 
     * Complexité :
     * * O(log(x)) -> temps, car nous effectuons une recherche binaire entre 0 et `x`, réduisant l'espace de recherche de moitié à chaque itération.
     * * O(1) -> espace, car nous utilisons un nombre constant d'espaces supplémentaires pour les variables locales.
     * 
     * @param x Le nombre dont on veut calculer la racine carrée entière.
     * @return La racine carrée entière de `x`.
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {return x;}

        int low = 0;
        int high = x;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long midSquared = (long) mid * mid;

            if (midSquared == x) {return mid;} 
            else if (midSquared < x) {low = mid + 1;} 
            else {high = mid - 1;}
        }

        return high;
    }

    /**
     * Cette méthode calcule le nombre de façons de monter `n` marches en prenant soit 1 soit 2 marches à chaque fois.
     * C'est un problème classique de programmation dynamique.
     * 
     * Complexité :
     * * O(n) -> temps, car nous devons remplir un tableau de taille `n + 1` en parcourant toutes les étapes jusqu'à `n`.
     * * O(n) -> espace, car nous utilisons un tableau de taille `n + 1` pour mémoriser les résultats intermédiaires.
     * 
     * @param n Le nombre de marches à gravir.
     * @return Le nombre de façons de monter `n` marches.
     */
    public int climbStairs(int n) {
        if (n <= 1) {return n;}

        int[] steps = new int[n + 1];
        steps[0] = 1;
        steps[1] = 1;

        for (int i = 2; i <= n; i++) { steps[i] = steps[i - 1] + steps[i - 2]; }

        return steps[n];
    }

    /**
     * Cette méthode supprime les doublons dans une liste triée.
     * Elle parcourt la liste et modifie les liens pour supprimer les éléments en doublon.
     * 
     * Complexité :
     * * O(n) -> temps, où n est la taille de la liste, car chaque élément de la liste est parcouru une seule fois.
     * * O(1) -> espace, car la solution modifie la liste en place sans nécessiter d'espace supplémentaire.
     * 
     * @param head La tête de la liste chaînée.
     * @return La tête de la liste chaînée après la suppression des doublons.
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) { return null; }

        ListNode currentNode = head;
        ListNode secondNode = head.next;
        int lastValue = head.val;

        while (secondNode != null) {
            if (secondNode.val == lastValue) {
                if (secondNode.next == null) {
                    currentNode.next = null;
                    break;
                }
                secondNode = secondNode.next;
                currentNode.next = secondNode;
            } else {
                currentNode = secondNode;
                lastValue = currentNode.val;
                secondNode = secondNode.next;
            }
        }
        return head;
    }

    /**
     * Cette méthode fusionne deux tableaux triés dans un seul tableau trié.
     * Les éléments de `nums2` sont insérés dans `nums1` en conservant l'ordre.
     * 
     * Complexité :
     * * O(m + n) -> temps, où m est la taille de `nums1` et n est la taille de `nums2`, car chaque élément des deux tableaux est traité une seule fois.
     * * O(1) -> espace, car la fusion se fait directement dans `nums1` sans utiliser d'espace supplémentaire.
     * 
     * @param nums1 Le premier tableau, avec suffisamment d'espace pour contenir les éléments de `nums2`.
     * @param m Le nombre d'éléments valides dans `nums1`.
     * @param nums2 Le second tableau à fusionner avec `nums1`.
     * @param n Le nombre d'éléments dans `nums2`.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pointerFirst = m - 1;
        int pointerSecond = n - 1;
        int iterator = m + n - 1;

        while (pointerFirst >= 0 && pointerSecond >= 0) {
            if (nums1[pointerFirst] > nums2[pointerSecond]) {
                nums1[iterator] = nums1[pointerFirst];
                pointerFirst--;
            } else {
                nums1[iterator] = nums2[pointerSecond];
                pointerSecond--;
            }
            iterator--;
        }

        while (pointerSecond >= 0) {
            nums1[iterator] = nums2[pointerSecond];
            pointerSecond--;
            iterator--;
        }
    }

    /**
     * Cette méthode effectue un parcours en ordre d'un arbre binaire et retourne une liste des valeurs des nœuds.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre de nœuds dans l'arbre, car chaque nœud est visité une seule fois.
     * * O(h) -> espace, où h est la hauteur de l'arbre, pour la pile d'appels lors du parcours récursif (dans le cas du parcours récursif en profondeur).
     * 
     * @param root La racine de l'arbre binaire.
     * @return Une liste contenant les valeurs des nœuds dans l'ordre du parcours en ordre.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> orderList = new ArrayList<>();
        inOrder(root, orderList);
        return orderList;
    }

    /**
     * Fonction récursive pour effectuer le parcours en ordre.
     * 
     * @param node Le nœud actuel de l'arbre.
     * @param orderList La liste dans laquelle les résultats sont stockés.
     */
    private void inOrder(TreeNode node, List<Integer> orderList) {
        if (node == null) {
            return;
        }
        inOrder(node.left, orderList);   // Parcours du sous-arbre gauche
        orderList.add(node.val);         // Ajout du nœud courant
        inOrder(node.right, orderList);  // Parcours du sous-arbre droit
    }

    /**
     * Cette méthode vérifie si deux arbres binaires sont identiques.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre de nœuds dans les arbres, car chaque nœud est comparé une seule fois.
     * * O(h) -> espace, où h est la hauteur de l'arbre, pour la pile d'appels lors du parcours récursif (dans le cas du parcours récursif en profondeur).
     * 
     * @param p La racine du premier arbre binaire.
     * @param q La racine du deuxième arbre binaire.
     * @return true si les deux arbres sont identiques, sinon false.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return inOrderCheck(p, q);
    }

    /**
     * Fonction récursive pour vérifier si deux arbres sont identiques en parcourant les nœuds.
     * 
     * @param node1 Le nœud actuel du premier arbre.
     * @param node2 Le nœud actuel du deuxième arbre.
     * @return true si les deux arbres sont identiques à ce niveau, sinon false.
     */
    private boolean inOrderCheck(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) { return true; }
        if (node1 == null || node2 == null) { return false; }
        if (node1.val != node2.val) { return false; }

        return inOrderCheck(node1.left, node2.left) && inOrderCheck(node1.right, node2.right);
    }

    /**
     * Cette méthode vérifie si un arbre binaire est symétrique autour de son axe central.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre de nœuds dans l'arbre, car chaque nœud est visité une seule fois pour vérifier la symétrie.
     * * O(h) -> espace, où h est la hauteur de l'arbre, pour la pile d'appels lors du parcours récursif (dans le cas du parcours récursif en profondeur).
     * 
     * @param root La racine de l'arbre binaire.
     * @return true si l'arbre est symétrique, sinon false.
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) { return true; }
        return isMirror(root.left, root.right);
    }

    /**
     * Fonction récursive pour vérifier si deux sous-arbres sont des miroirs l'un de l'autre.
     * 
     * @param left Le sous-arbre gauche.
     * @param right Le sous-arbre droit.
     * @return true si les sous-arbres sont des miroirs l'un de l'autre, sinon false.
     */
    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) { return true; }
        if (left == null || right == null) { return false; }
        if (left.val != right.val) { return false; }

        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    /**
     * Cette méthode calcule la profondeur maximale d'un arbre binaire en utilisant un parcours de niveau (BFS).
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre de nœuds dans l'arbre, car chaque nœud est visité une seule fois.
     * * O(n) -> espace, car dans le pire des cas, la file contiendra tous les nœuds d'un niveau de l'arbre, ce qui peut être n dans un arbre dégénéré.
     * 
     * @param root La racine de l'arbre binaire.
     * @return La profondeur maximale de l'arbre binaire.
     */
    public int maxDepth(TreeNode root) {
        if (root == null) { return 0; }

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            if (currentNode == null) {
                depth++;
                if (!queue.isEmpty()) { queue.add(null); }
            } else {
                if (currentNode.left != null) { queue.add(currentNode.left); }
                if (currentNode.right != null) { queue.add(currentNode.right); }
            }
        }

        return depth;
    }

    /**
     * Cette méthode transforme un tableau trié en un arbre binaire de recherche (BST) équilibré.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre d'éléments dans le tableau. Chaque élément du tableau est visité une fois pour être inséré dans l'arbre.
     * * O(log n) -> espace, pour la profondeur de la pile d'appels récursifs. Dans le pire des cas, l'arbre est équilibré, ce qui donne une hauteur de log(n).
     * 
     * @param nums Le tableau d'entiers trié.
     * @return La racine de l'arbre binaire de recherche équilibré.
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) { return null; }
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int start, int end) {
        if (start > end) { return null; }

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        node.left = buildBST(nums, start, mid - 1);
        node.right = buildBST(nums, mid + 1, end);

        return node;
    }

    /**
     * Cette méthode vérifie si un arbre binaire est équilibré.
     * Un arbre est équilibré si, pour chaque nœud, la différence de hauteur entre ses sous-arbres gauche et droit est au maximum 1.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre de nœuds dans l'arbre. Chaque nœud est visité une seule fois.
     * * O(h) -> espace, où h est la hauteur de l'arbre. Cela correspond à la profondeur maximale de la pile d'appels récursifs.
     * *    Dans le pire des cas, l'arbre est totalement déséquilibré, ce qui donne une hauteur de n, mais dans le cas d'un arbre équilibré, la hauteur est log(n).
     * 
     * @param root La racine de l'arbre binaire.
     * @return true si l'arbre est équilibré, sinon false.
     */
    public boolean isBalanced(TreeNode root) {
        return notHardAtAll(root) != -1;
    }

    public int notHardAtAll(TreeNode node) {
        if (node == null) { return 0; }
        int left = notHardAtAll(node.left);
        int right = notHardAtAll(node.right);

        if (left == -1 || right == -1) { return -1; }
        if (Math.abs((left - right)) > 1) { return -1; }
        else { return (1 + Math.max(left, right)); }
    }

    /**
     * Cette méthode calcule la profondeur minimale d'un arbre binaire.
     * La profondeur minimale est définie comme le nombre de nœuds du chemin le plus court entre la racine et une feuille.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre de nœuds dans l'arbre. Chaque nœud peut être visité une fois.
     * * O(h) -> espace, où h est la hauteur de l'arbre. Cela correspond à la profondeur maximale de la pile d'appels récursifs.
     * *    Dans le pire des cas, l'arbre est totalement déséquilibré, ce qui donne une hauteur de n, mais dans un arbre équilibré, la hauteur est de l'ordre de log(n).
     * 
     * @param root La racine de l'arbre binaire.
     * @return La profondeur minimale de l'arbre.
     */
    public int minDepth(TreeNode root) {
        if (root == null) { return 0; }
        if (root.left == null && root.right == null) { return 1; }
        if (root.left == null) { return minDepth(root.right) + 1; }
        if (root.right == null) { return minDepth(root.left) + 1; }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * Cette méthode vérifie si un chemin dans l'arbre binaire mène à une somme cible spécifiée.
     * Un chemin est défini comme un chemin de nœuds de la racine à une feuille, en additionnant les valeurs des nœuds.
     * 
     * Complexité :
     * * O(n) -> temps, où n est le nombre de nœuds dans l'arbre. Chaque nœud est visité une seule fois dans la recherche du chemin.
     * * O(h) -> espace, où h est la hauteur de l'arbre. Cela correspond à la profondeur maximale de la pile d'appels récursifs.
     * *    Dans le pire des cas, l'arbre est totalement déséquilibré, ce qui donne une hauteur de n, mais dans un arbre équilibré, la hauteur est de l'ordre de log(n).
     * 
     * @param root La racine de l'arbre binaire.
     * @param targetSum La somme cible à vérifier.
     * @return true si un chemin de la racine à une feuille a une somme égale à targetSum, sinon false.
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) { return false; }
        return checkSum(root, targetSum, 0);
    }

    public boolean checkSum(TreeNode root, int targetSum, int sum) {
        sum += root.val;

        if (root.left == null && root.right == null) { return sum == targetSum; }

        if (root.left != null && checkSum(root.left, targetSum, sum)) { return true; }
        if (root.right != null && checkSum(root.right, targetSum, sum)) { return true; }

        return false;
    }

    /**
     * Cette méthode génère le triangle de Pascal jusqu'à un nombre donné de lignes.
     * Chaque ligne représente les coefficients binomiaux correspondants à une expansion de binôme.
     * 
     * Complexité :
     * * O(n^2) -> temps, où n est le nombre de lignes à générer. Le triangle de Pascal a en tout n(n+1)/2 éléments à ajouter, ce qui donne une complexité quadratique.
     * * O(n^2) -> espace, car nous stockons toutes les lignes du triangle dans une liste.
     * 
     * @param numRows Le nombre de lignes du triangle de Pascal à générer.
     * @return Une liste de listes d'entiers représentant le triangle de Pascal jusqu'à numRows lignes.
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pyramide = new ArrayList<List<Integer>>(numRows);
        List<Integer> previousValue = new ArrayList<Integer>();
        previousValue.add(1);
        pyramide.add(previousValue);

        for(int i = 1; i < numRows; i++){
            ArrayList<Integer> addedValue = new ArrayList<Integer>();

            for (int j = 0; j < i; j++){
                if (j == 0){ addedValue.add(previousValue.get(j)); } 
                else { addedValue.add((addedValue.get(j - 1) + addedValue.get(j))); }
            }

            previousValue = addedValue;
            pyramide.add(i, addedValue);
        }

        return pyramide;
    }
    
}