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
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 总的来说，工具类中有三个方法 获取JwtToken，获取JwtToken中封装的信息，判断JwtToken是否存在
 * <p>
 * 1. encode()，参数是=签发人，存在时间，一些其他的信息=。返回值是JwtToken对应的字符串 </p>
 * <p>
 * 2. decode()，参数是=JwtToken=。返回值是荷载部分的键值对
 * </p>
 * 3. isVerify()，参数是=JwtToken=。返回值是这个JwtToken是否存在
 *
 * @author xiesu created on 2023/7/12 16:38
 */
public class ES256kJwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(ES256kJwtUtil.class);

    /**
     * 使用 {@link  ES256kGenerator}生成自己公钥、私钥信息
     */
    private static final String ecJwkBase64 = "rO0ABXNyABtjb20ubmltYnVzZHMuam9zZS5qd2suRUNLZXkAAAAA"
            + "AAAAAQIABUwAA2NydnQAHUxjb20vbmltYnVzZHMvam9zZS9qd2svQ3VydmU7TAABZHQAIkxjb20vbmltYnVz"
            + "ZHMvam9zZS91dGlsL0Jhc2U2NFVSTDtMAApwcml2YXRlS2V5dAAaTGphdmEvc2VjdXJpdHkvUHJpdmF0ZUtle"
            + "TtMAAF4cQB+AAJMAAF5cQB+AAJ4cgAZY29tLm5pbWJ1c2RzLmpvc2UuandrLkpXSwAAAAAAAAABAgAOTAADYW"
            + "xndAAdTGNvbS9uaW1idXNkcy9qb3NlL0FsZ29yaXRobTtMAANleHB0ABBMamF2YS91dGlsL0RhdGU7TAADaWF"
            + "0cQB+AAZMAAhrZXlTdG9yZXQAGExqYXZhL3NlY3VyaXR5L0tleVN0b3JlO0wAA2tpZHQAEkxqYXZhL2xhbmcv"
            + "U3RyaW5nO0wAA2t0eXQAH0xjb20vbmltYnVzZHMvam9zZS9qd2svS2V5VHlwZTtMAANuYmZxAH4ABkwAA29wc"
            + "3QAD0xqYXZhL3V0aWwvU2V0O0wACXBhcnNlZFg1Y3QAEExqYXZhL3V0aWwvTGlzdDtMAAN1c2V0AB5MY29tL2"
            + "5pbWJ1c2RzL2pvc2UvandrL0tleVVzZTtMAAN4NWNxAH4AC0wAA3g1dHEAfgACTAAGeDV0MjU2cQB+AAJMAAN"
            + "4NXV0AA5MamF2YS9uZXQvVVJJO3hwcHBwcHQAAzEyM3NyAB1jb20ubmltYnVzZHMuam9zZS5qd2suS2V5VHlw"
            + "ZQAAAAAAAAABAgACTAALcmVxdWlyZW1lbnR0AB9MY29tL25pbWJ1c2RzL2pvc2UvUmVxdWlyZW1lbnQ7TAAFd"
            + "mFsdWVxAH4ACHhwfnIAHWNvbS5uaW1idXNkcy5qb3NlLlJlcXVpcmVtZW50AAAAAAAAAAASAAB4cgAOamF2YS"
            + "5sYW5nLkVudW0AAAAAAAAAABIAAHhwdAALUkVDT01NRU5ERUR0AAJFQ3BwcHNyABxjb20ubmltYnVzZHMuam9"
            + "zZS5qd2suS2V5VXNlAAAAAAAAAAECAAFMAAppZGVudGlmaWVycQB+AAh4cHQAA3NpZ3BwcHBzcgAbY29tLm5p"
            + "bWJ1c2RzLmpvc2UuandrLkN1cnZlAAAAAAAAAAECAANMAARuYW1lcQB+AAhMAANvaWRxAH4ACEwAB3N0ZE5hb"
            + "WVxAH4ACHhwdAAJc2VjcDI1NmsxdAAMMS4zLjEzMi4wLjEwcQB+AB1zcgAgY29tLm5pbWJ1c2RzLmpvc2UudX"
            + "RpbC5CYXNlNjRVUkwVPfdPaoj5+QIAAHhyAB1jb20ubmltYnVzZHMuam9zZS51dGlsLkJhc2U2NAAAAAAAAAA"
            + "BAgABTAAFdmFsdWVxAH4ACHhwdAArQmhGbDgyYlhHZjVRR2VDeVpJSkdTc3pKUkdMQm9ZcnZlelF1VU10dHly"
            + "Y3BzcQB+AB90ACt3NVFDNW1ramUxNE1PLUc3RVJhNUhxUHMtT0ZPSGNnVVM5Wm9jbGN3TnM4c3EAfgAfdAArU"
            + "2hMY1J4ZXI2VGh0RFl5X2Y3SDh1bEZ5UVJwSE9YQ1NpRTk1bzZLajRlWQ==";

    private static final ECKey ecJWK;

    static {
        ecJWK = SerializationUtils.deserialize(Base64.getDecoder().decode(ecJwkBase64));
    }


    /**
     * 签名办法JWT
     * Token jwt字符串包括三个部分
     * 1. header -当前字符串的类型，一般都是“JWT” -哪种算法加密，“HS256”或者其他的加密算法
     * 所以一般都是固定的，没有什么变化
     * 2. payload 一般有四个最常见的标准字段（下面有）
     * iat：签发时间，也就是这个jwt什么时候生成的
     * jti：JWT的唯一标识
     * iss：签发人，一般都是username或者userId
     * exp：过期时间
     * <p>
     * <p>
     * <p>
     * iss（全称为 issuer），指明 JWT 是由谁签发的
     * sub（全称为 subject），指明 JWT 的主题（也可理解为面向用户的类型）
     * aud（全称为 audience），指明 JWT 希望谁签收
     * exp（全称为 expiration time），指明 JWT 的过期时间，过期时间需大于签发时间
     * nbf（全称为 not before time），指明 JWT 在哪个时间点生效
     * iat（全称为 issued at time），指明 JWT 的签发时间
     * jti（全称为 JWT ID），指明 JWT 唯一 ID，用于避免重放攻击
     *
     * <a href="https://datatracker.ietf.org/doc/html/rfc7519#section-4.1.2">
     * 在使用JWT时可以额外定义的载荷。为了避免冲突，应该使用 IANA
     * JSON Web Token Registry 中 定义好的，或者给额外载荷加上类似命名空间的唯一标识。
     * </a>
     *
     * <p>
     * 3.signature
     */
    public static String encode(JWTClaimsSet claims) throws JOSEException {
        Objects.requireNonNull(claims);

        Map<String, Object> claimsMap = new LinkedHashMap<>(
                Objects.requireNonNull(claims.getClaims(), "claims required non null"));

        if (claims.getExpirationTime() == null) {
            //默认10分钟有效期
            claimsMap.put(JWTClaimNames.EXPIRATION_TIME,
                    new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        }

        if (claims.getIssueTime() == null) {
            //默认当前签发
            claimsMap.put(JWTClaimNames.ISSUED_AT, new Date());
        }

        if (claims.getNotBeforeTime() == null) {
            //默认立即有效
            claimsMap.put(JWTClaimNames.NOT_BEFORE, new Date());
        }

        var claimsBuilder = JwtClaimBuilder.builder();
        for (Entry<String, Object> claim : claimsMap.entrySet()) {
            claimsBuilder.claim(claim.getKey(), claim.getValue());
        }

        // Create JWT for ES256K alg
        var jwt = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.ES256K).keyID(ecJWK.getKeyID()).build(),
                claimsBuilder.build());

        // Sign with private EC key
        var signer = new ECDSASigner(ecJWK);
        signer.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());
        jwt.sign(signer);

        return jwt.serialize();
    }


    /**
     * 校验token
     * 目标:
     * signature 未被篡改
     * exp > now date
     * nbf < now date
     * iat < now date
     *
     * @param jwtToken token
     * @return true or false
     */
    public static boolean tokenVerify(String jwtToken) throws ParseException, JOSEException {
        SignedJWT jwt = SignedJWT.parse(jwtToken);

        // Verify the ES256K signature with the public EC key
        var verifier = new ECDSAVerifier(ecJWK.toECPublicKey());
        verifier.getJCAContext().setProvider(BouncyCastleProviderSingleton.getInstance());
        var flag = jwt.verify(verifier);

        if (flag) {
            var claimSet = decode(jwtToken);
            var nowDate = new Date();

            //logger.info("token最后有效期={}", claimSet.getExpirationTime());
            return claimSet.getExpirationTime().after(nowDate) && claimSet.getNotBeforeTime()
                    .before(nowDate) && claimSet.getIssueTime().before(nowDate);

        }

        return false;
    }

    /**
     * 解密token，拿到存储的payload信息
     *
     * @param jwtToken token
     */
    public static JWTClaimsSet decode(String jwtToken) throws ParseException {
        return SignedJWT.parse(jwtToken).getJWTClaimsSet();
    }

    private ES256kJwtUtil() {
    }
}
