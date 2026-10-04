public class ClasseCriptografica {
    char[] abecedario = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    char[] majuscules = new char[]{'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

    public boolean clauValida(int clau){
        if(clau < 1 || clau > 35 || clau == 27){
            return false;
        }
        return true;
    }

    public String encriptar(String missatge, int clau){
        String missatgeEncriptat = "";

        clau = clau % 36;

        int numMissatge = missatge.length();

        if(numMissatge == 0){
            return "";
        }

        int[] soroll = new int[numMissatge * 3];
        char[] sorollTraduit = new char[numMissatge * 3];

        int a = clau;
        int b = numMissatge % 36;
        int comptador = 0;

        if(b != clau){
            soroll[comptador] = b;
            comptador++;
        }

        while(comptador < soroll.length){
            int nou = (a + b) % 36;
            a = b;
            b = nou;
            if(nou != clau){
                soroll[comptador] = nou;
                comptador++;
            }
        }

        for(int i = 0; i < sorollTraduit.length; i++){
            sorollTraduit[i] = abecedario[soroll[i]];
        }

        char marcador = abecedario[clau];
        String blocs = "";
        int posSoroll = 0;

        for(int i = 0; i < numMissatge; i++){
            char lletra = desplacar(missatge.charAt(i), clau % 27);

            if(i % 2 == 0){
                blocs = blocs + sorollTraduit[posSoroll] + sorollTraduit[posSoroll + 1] + marcador + lletra + sorollTraduit[posSoroll + 2];
            } else {
                blocs = blocs + sorollTraduit[posSoroll] + marcador + lletra + sorollTraduit[posSoroll + 1] + sorollTraduit[posSoroll + 2];
            }
            posSoroll = posSoroll + 3;

            if(i < numMissatge - 1){
                blocs = blocs + "-";
            }
        }

        missatgeEncriptat = girar(blocs);

        return missatgeEncriptat;
    }

    public String desencriptar(String missatgeEncriptat, int clau){
        String missatgeDesencriptat = "";

        clau = clau % 36;

        String blocs = girar(missatgeEncriptat);

        int numBlocs = (blocs.length() + 1) / 6;

        for(int i = 0; i < numBlocs; i++){
            char lletra;
            if(i % 2 == 0){
                lletra = blocs.charAt(i * 6 + 3);
            } else {
                lletra = blocs.charAt(i * 6 + 2);
            }
            missatgeDesencriptat = missatgeDesencriptat + desplacar(lletra, 27 - (clau % 27));
        }

        return missatgeDesencriptat;
    }

    private String girar(String text){
        String girat = "";
        for(int i = text.length() - 1; i >= 0; i--){
            girat = girat + text.charAt(i);
        }
        return girat;
    }

    private char desplacar(char lletra, int desplacament){
        for(int i = 0; i < majuscules.length; i++){
            if(majuscules[i] == lletra){
                return majuscules[(i + desplacament) % 27];
            }
            if(Character.toLowerCase(majuscules[i]) == lletra){
                return Character.toLowerCase(majuscules[(i + desplacament) % 27]);
            }
        }
        return lletra;
    }
}