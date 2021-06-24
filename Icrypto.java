import javax.crypto.*;

public interface Icrypto{
    public final String TRANSFORM="AES/CBC/PKCS5Padding";
    public final byte [] IV="AseizeCaracteres".getBytes();
    public final String ALGO="AES";
    public final int KEYSIZE=128;
    /**
     * cette methode permet de generer une cle
     * @param algo: L'algorithme de chiffrement(ex:'AES', 'DES')
     * @param keySize: La taille de la cle (ex: 128 ou 256)
     * @return La cle generee
     */
    public SecretKey genKey();
   /**
    * Cette methode permet de stocke la cle
    * @param key : La cle
    * @param filePath : Le chemin
    * @return
    */
    public String saveKey(SecretKey key, String filePath);
    /**
     * Cette methode permet de recuperer le chemin
     * @param filePath
     * @return
     */
    public SecretKey getKey(String filePath);
}
