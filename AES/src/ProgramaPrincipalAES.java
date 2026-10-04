import java.util.Scanner;
public class ProgramaPrincipalAES {
        int opcio;
        Scanner sc = new Scanner(System.in);
        String missatgeInicial = "Aquest és un missatge secret.";
        String clauInicial = "1234567890123456";
    public static void main(String[] args) throws Exception {
        ProgramaPrincipalAES p = new ProgramaPrincipalAES();
        p.inicial();
        p.principal();
    }

    // Lo que demana l'enunciat: mostrar, encriptar, desencriptar i comprovar
    public void inicial(){
        String xifrat;
        String recuperat;

        System.out.println("=== AES ===");
        System.out.println("Missatge original:  " + missatgeInicial);
        System.out.println("Clau:               " + clauInicial);
        try {
            xifrat = ClasseAES.encripta(missatgeInicial, clauInicial);
            System.out.println("Missatge xifrat:    " + xifrat);
            recuperat = ClasseAES.desencripta(xifrat, clauInicial);
            System.out.println("Missatge recuperat: " + recuperat);
            if(recuperat.equals(missatgeInicial)){
                System.out.println("El missatge recuperat es igual al original");
            } else {
                System.out.println("El missatge recuperat NO es igual al original");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void principal(){
        do {
            System.out.println("\n=== Proves AES ===");
            System.out.println("1. Prova clau correcta");
            System.out.println("2. Prova clau diferent");
            System.out.println("3. Prova missatge diferent");
            System.out.println("4. Prova clau de longitud incorrecta");
            System.out.println("0. Sortir");
            opcio = sc.nextInt();
            sc.nextLine();
            switch (opcio) {
                case 1:
                    prova1();
                    break;

                case 2:
                    prova2();
                    break;

                case 3:
                    prova3();
                    break;

                case 4:
                    prova4();
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

    public void prova1(){
        String xifrat;
        String recuperat;

        System.out.println("=== Prova 1. Clau correcta ===");
        try {
            xifrat = ClasseAES.encripta(missatgeInicial, clauInicial);
            recuperat = ClasseAES.desencripta(xifrat, clauInicial);
            System.out.println("Missatge xifrat:    " + xifrat);
            System.out.println("Missatge recuperat: " + recuperat);
            resultat(recuperat.equals(missatgeInicial));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void prova2(){
        String xifrat;
        String recuperat;
        String clauDiferent = "6543210987654321";

        System.out.println("=== Prova 2. Clau diferent ===");
        System.out.println("Encriptem amb " + clauInicial + " i desencriptem amb " + clauDiferent);
        try {
            xifrat = ClasseAES.encripta(missatgeInicial, clauInicial);
            System.out.println("Missatge xifrat:    " + xifrat);
            recuperat = ClasseAES.desencripta(xifrat, clauDiferent);
            System.out.println("Missatge recuperat: " + recuperat);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Amb una clau diferent no es pot recuperar el missatge");
        }
    }

    public void prova3(){
        String missatge;
        String xifrat;
        String recuperat;

        System.out.println("=== Prova 3. Missatge diferent ===");
        System.out.print("Escriu un missatge: ");
        missatge = sc.nextLine();
        try {
            xifrat = ClasseAES.encripta(missatge, clauInicial);
            recuperat = ClasseAES.desencripta(xifrat, clauInicial);
            System.out.println("Missatge xifrat:    " + xifrat);
            System.out.println("Missatge recuperat: " + recuperat);
            resultat(recuperat.equals(missatge));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void prova4(){
        String xifrat;
        String clauCurta = "1234";

        System.out.println("=== Prova 4. Clau de longitud incorrecta ===");
        System.out.println("Clau: " + clauCurta + " (" + clauCurta.length() + " caracters, AES necessita 16, 24 o 32)");
        try {
            xifrat = ClasseAES.encripta(missatgeInicial, clauCurta);
            System.out.println("Missatge xifrat: " + xifrat);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.out.println("La clau no te una longitud valida per AES");
        }
    }

    public void resultat(boolean correcte){
        if(correcte){
            System.out.println("Resultat: Correcte");
        } else {
            System.out.println("Resultat: Incorrecte");
        }
    }
}
