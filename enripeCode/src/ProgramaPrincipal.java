import java.util.Scanner;
public class ProgramaPrincipal {
        int opcio;
        Scanner sc = new Scanner(System.in);
        ClasseCriptografica c = new ClasseCriptografica();
    public static void main(String[] args) throws Exception {
        ProgramaPrincipal p = new ProgramaPrincipal();
        p.principal();
    }

    public void principal(){
        String missatge;
        int clau;
        do {
            System.out.println("\n=== enripeCode ===");
            System.out.println("1. Encriptar");
            System.out.println("2. Desencriptar");
            System.out.println("3. Fer els tests");
            System.out.println("0. Sortir");
            opcio = sc.nextInt();
            sc.nextLine();
            switch (opcio) {
                case 1:
                    System.out.println("=== Encriptar ===");
                    System.out.print("Missatge: ");
                    missatge = sc.nextLine();
                    System.out.print("Clau: ");
                    clau = sc.nextInt();
                    if(c.clauValida(clau)){
                        System.out.println("Missatge encriptat: " + c.encriptar(missatge, clau));
                    } else {
                        System.out.println("Clau no valida, ha de ser de l'1 al 35 i no pot ser 27");
                    }
                    break;

                case 2:
                    System.out.println("=== Desencriptar ===");
                    System.out.print("Missatge encriptat: ");
                    missatge = sc.nextLine();
                    System.out.print("Clau: ");
                    clau = sc.nextInt();
                    if(c.clauValida(clau)){
                        System.out.println("Missatge original: " + c.desencriptar(missatge, clau));
                    } else {
                        System.out.println("Clau no valida, ha de ser de l'1 al 35 i no pot ser 27");
                    }
                    break;

                case 3:
                    System.out.println("=== Fer els tests ===");
                    tests();
                    break;

                case 0:
                    System.out.println("Sortint...");
                    break;

                default:
                    System.out.println("Opcio no valida...");
                    break;
            }
        } while (!(opcio == 0));

    }

    public void tests(){
        String missatge;
        String encriptat;
        String desencriptat;

        // Test 1
        missatge = "HOLA";
        encriptat = c.encriptar(missatge, 7);
        desencriptat = c.desencriptar(encriptat, 7);
        System.out.println("\nTest 1. Missatge curt: " + missatge + " (clau 7)");
        System.out.println("Encriptat:    " + encriptat);
        System.out.println("Desencriptat: " + desencriptat);
        resultat(desencriptat.equals(missatge));

        // Test 2
        missatge = "HOLA MÓN";
        encriptat = c.encriptar(missatge, 7);
        desencriptat = c.desencriptar(encriptat, 7);
        System.out.println("\nTest 2. Missatge amb espais: " + missatge + " (clau 7)");
        System.out.println("Encriptat:    " + encriptat);
        System.out.println("Desencriptat: " + desencriptat);
        resultat(desencriptat.equals(missatge));

        // Test 3
        missatge = "AQUEST MISSATGE TE MES DE TRENTA CARACTERS";
        encriptat = c.encriptar(missatge, 7);
        desencriptat = c.desencriptar(encriptat, 7);
        System.out.println("\nTest 3. Missatge llarg (" + missatge.length() + " caracters, clau 7)");
        System.out.println("Original:     " + missatge);
        System.out.println("Encriptat:    " + encriptat);
        System.out.println("Desencriptat: " + desencriptat);
        resultat(desencriptat.equals(missatge));

        // Test 4
        String encriptat3 = c.encriptar("HOLA", 3);
        String encriptat7 = c.encriptar("HOLA", 7);
        System.out.println("\nTest 4. Clau diferent: HOLA amb clau 3 i amb clau 7");
        System.out.println("Clau 3: " + encriptat3);
        System.out.println("Clau 7: " + encriptat7);
        resultat(!encriptat3.equals(encriptat7));

        // Test 5
        missatge = "Hola Món, això és una prova!";
        encriptat = c.encriptar(missatge, 15);
        desencriptat = c.desencriptar(encriptat, 15);
        System.out.println("\nTest 5. Encriptar i desencriptar (clau 15)");
        System.out.println("Original:     " + missatge);
        System.out.println("Encriptat:    " + encriptat);
        System.out.println("Desencriptat: " + desencriptat);
        resultat(desencriptat.equals(missatge));

        // Test 6
        missatge = "HOLA";
        encriptat = c.encriptar(missatge, 7);
        desencriptat = c.desencriptar(encriptat, 3);
        System.out.println("\nTest 6. Clau incorrecta: encriptem amb la 7 i desencriptem amb la 3");
        System.out.println("Encriptat:    " + encriptat);
        System.out.println("Desencriptat: " + desencriptat);
        System.out.println("Les lletres es tiren 3 posicions enrere en lloc de 7, per aixo no surt HOLA");
        resultat(!desencriptat.equals(missatge));
    }

    public void resultat(boolean correcte){
        if(correcte){
            System.out.println("Resultat: Correcte");
        } else {
            System.out.println("Resultat: Incorrecte");
        }
    }
}