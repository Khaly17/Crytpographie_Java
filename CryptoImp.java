import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JFileChooser;

public class CryptoImp implements Icrypto {
    
        private Cipher c;
        public static String fileToEncrypt;
        public static String keyFile;

        @Override
        public SecretKey genKey() {
            
            try {
                KeyGenerator gen = KeyGenerator.getInstance(ALGO);
                gen.init(KEYSIZE);
                SecretKey key = gen.generateKey();
                return key;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return null;
        }

        
        @Override
        public String saveKey(SecretKey key, String keyfile) {
            try {
                FileOutputStream fos = new FileOutputStream(keyfile);
                ObjectOutputStream obj = new ObjectOutputStream(fos);
                obj.writeObject(key);
                obj.close();
                fos.close();
                return keyfile;
               } catch (Exception e) {
                   e.printStackTrace();
               }
            return null;
        }

        @Override
        public SecretKey getKey(String keyfile) {
            try {
                FileInputStream fis = new FileInputStream(keyfile);
                
                try {
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    SecretKey key = (SecretKey) ois.readObject();
                    return key;
                } catch (Exception e) {
                    e.printStackTrace();
                }   
            } catch (FileNotFoundException e) {
                
                e.printStackTrace();
            }
            return null;
        }

        //CHIFFREMENT

        //transform: "AES/CBC/PKCS5Padding"
         //ENCRYPT_MODE
         //les differentes etapes pour un cipher?
        public Cipher getCipher(String keyfile, int mode){
            try {

                c = Cipher.getInstance(TRANSFORM);
                IvParameterSpec iv = new IvParameterSpec(IV);
                c.init(mode,getKey(keyfile), iv);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return c;
        }   
        
        @Override
        public void Chiffrement(String fileToencrypt,String keyfile){
            try {

                FileInputStream fis = new FileInputStream(fileToencrypt);
                CipherInputStream  cis = new CipherInputStream(fis, getCipher(keyfile,Cipher.ENCRYPT_MODE));
                //methode process
                FileOutputStream fos = new FileOutputStream(fileToencrypt+".khd");
                byte[] buffer = new byte[1024];
                int nombreBytesLu=0;
                
                while ((nombreBytesLu = cis.read(buffer)) != -1){
                     fos.write(buffer, 0, nombreBytesLu);
               }
               cis.close();
               fis.close();
               fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(fileToEncrypt);
        }


    JFileChooser jfc = new JFileChooser();

    public void showSaveDg( SecretKey key){
        int option = jfc.showSaveDialog(jfc);


        if(JFileChooser.APPROVE_OPTION==option){
            String filePath = jfc.getSelectedFile().getAbsolutePath();
            this.saveKey(key, filePath);
        }
        String encoding = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(encoding);
    }

    public String showOpenKeyFile(SecretKey key){
        int option = jfc.showOpenDialog(jfc);
        if(JFileChooser.APPROVE_OPTION==option){
            String filePath = jfc.getSelectedFile().getAbsolutePath();
            key= this.getKey(filePath);
            keyFile = filePath;
        }
        String encoding2 = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(encoding2);
        return keyFile;
    }

    public String showOpenFileToEncrypt(){

        int option = jfc.showOpenDialog(jfc);
        if(JFileChooser.APPROVE_OPTION==option){
            String filePath = jfc.getSelectedFile().getAbsolutePath();
            fileToEncrypt = filePath;
        }

        return fileToEncrypt;
    }
    
    public void affichage(SecretKey key){
        JFileChooser jfc = new JFileChooser();
        //int option = jfc.showSaveDialog(jfc);
        int option = jfc.showOpenDialog(jfc);

        if(JFileChooser.APPROVE_OPTION==option){
            String filePath = jfc.getSelectedFile().getAbsolutePath();
            this.saveKey(key, filePath);
        }
        String encoding = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(encoding);
    }

    //Dechiffrement a revoir avant de faire quoi ce soit Merci Khaly
   
} 
      


