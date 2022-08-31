package com.bdm;

import com.bdm.utils.KeystoreUtils;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.MakeSignature;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

@SpringBootTest
class KeystoreDemoApplicationTests {

    public static final String KEYSTORE = "C:\\Users\\14740\\IdeaProjects\\mybatisplus\\seal\\src\\main\\resources\\android.keystore";
    // 之前生成的keystory密码
    public static final char[] PASSWORD = "123456".toCharArray();
    // 需要签名的PDF路径
    public static final String SRC = "C:\\Users\\14740\\IdeaProjects\\mybatisplus\\seal\\src\\main\\resources\\abc.pdf";
    // 完成签名的PDF路径
    public static final String OUTPUT_SRC = "C:\\Users\\14740\\IdeaProjects\\mybatisplus\\seal\\src\\main\\resources\\1abc-sign.pdf";
    public static final String IMG = "C:\\Users\\14740\\IdeaProjects\\mybatisplus\\seal\\src\\main\\resources\\1661911867452.png";


    public static void main(String[] args) {
        try {
            //读取keystore ，获得私钥和证书链
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(KEYSTORE), PASSWORD);
            String alias = (String)keyStore.aliases().nextElement();
            PrivateKey PrivateKey = (PrivateKey) keyStore.getKey(alias, PASSWORD);
            Certificate[] chain = keyStore.getCertificateChain(alias);

            KeystoreUtils keystoreUtils = new KeystoreUtils();
            keystoreUtils.sign(SRC, String.format(OUTPUT_SRC, 3),IMG, chain, PrivateKey, DigestAlgorithms.SHA1, null, MakeSignature.CryptoStandard.CMS, "Test", "Ghent");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            e.printStackTrace();
        }
    }
}
