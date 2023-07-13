/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xiesu.security;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;
import java.util.Base64;
import org.apache.commons.lang3.SerializationUtils;

/**
 * ES256K (secp256k1 椭圆曲线) 密钥对生成器 used in Bitcoin and Ethereum,性能比普通曲线效率高30%
 *
 * @author xiesu created on 2023/7/12 23:12
 */
public class ES256kGenerator {


    public static void main(String[] args) throws JOSEException {
        // Generate EC key pair on the secp256k1 curve
        ECKey ecJWK = new ECKeyGenerator(Curve.SECP256K1)
                .keyUse(KeyUse.SIGNATURE)
                .keyID("123")
                .provider(BouncyCastleProviderSingleton.getInstance())
                .generate();

        byte[] bytes = SerializationUtils.serialize(ecJWK);
        String base64 = Base64.getEncoder().encodeToString(bytes);
        System.out.println(base64);

//        ECPublicKey publicKey = ecJWK.toECPublicKey();
//        ECPrivateKey privateKey = ecJWK.toECPrivateKey();
//
//        System.out.println(Base64.getEncoder().encodeToString(ecJWK.));
//
//
//
//        // 将公钥编码为Base64字符串
//        String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKey.getEncoded());
//        // 将私钥编码为Base64字符串
//        String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());
//        System.out.println("Private Key: " + privateKey);
//        System.out.println("Public Key: " + publicKey);
//
//        System.out.println(publicKeyBase64);
//        System.out.println("----");
//        System.out.println(privateKeyBase64);
//
//
//// 生成ECDSA密钥对
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
//        keyPairGenerator.initialize(384); // 使用EC384曲线
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//// 获取私钥和公钥
//        ECPrivateKey privateKey2 = (ECPrivateKey) keyPair.getPrivate();
//        ECPublicKey publicKey2 = (ECPublicKey) keyPair.getPublic();
//
//// 打印私钥和公钥
//        System.out.println("Private Key2: " + privateKey2);
//        System.out.println("Public Key2: " + publicKey2);
//// 将公钥编码为Base64字符串
//        String publicKeyBase642 = Base64.getEncoder().encodeToString(publicKey2.getEncoded());
//// 将私钥编码为Base64字符串
//        String privateKeyBase642 = Base64.getEncoder().encodeToString(privateKey2.getEncoded());

    }

}
