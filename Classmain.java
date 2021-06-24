import javax.crypto.SecretKey;
import javax.swing.JPanel;

public class Classmain extends JPanel{

    public static void main(String[] args) {
        CryptoImp crypto = new CryptoImp();
        SecretKey key = crypto.genKey();
        

        crypto.showSaveDg(key);

        String keyFile = crypto.showOpenKeyFile(key);
        String fileToEncrypt = crypto.showOpenFileToEncrypt();
        crypto.getCipher(keyFile );
        crypto.Chiffrement(fileToEncrypt, keyFile);

        
       
     

        // JFileChooser jfc = new JFileChooser();
        // //int option = jfc.showSaveDialog(jfc);
        // int option = jfc.showOpenDialog(jfc);

        // if(JFileChooser.APPROVE_OPTION==option){
        //     String filePath = jfc.getSelectedFile().getAbsolutePath();
        //     crypto.saveKey(key, filePath);
        // }
        // String encoding = Base64.getEncoder().encodeToString(key.getEncoded());
        // System.out.println(encoding);







        
        //SecretKey key2 = crypto.genKey();
        // option = jfc.showSaveDialog(jfc);
        // if(JFileChooser.APPROVE_OPTION==option){
        //     String filePath = jfc.getSelectedFile().getAbsolutePath();
        //     key2= crypto.getKey(filePath);
        // }
        
        //crypto.saveKey(key, "mykey.khd");
        // String encoding2 = Base64.getEncoder().encodeToString(key2.getEncoded());
        // System.out.println(encoding2);
        
    }
    
}
