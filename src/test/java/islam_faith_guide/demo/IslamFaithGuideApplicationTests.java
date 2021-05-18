package islam_faith_guide.demo;

import com.squareup.okhttp.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@SpringBootTest

class IslamFaithGuideApplicationTests {


    @Test
    void contextLoads() {


    }



    private static com.squareup.okhttp.Response postRequest(String url, String deviceToken, Boolean bit0, Boolean bit1) throws IOException {
      com.squareup.okhttp.MediaType JSON = MediaType.parse("application/json; charset=utf-8");


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("device_token", deviceToken);
        jsonObject.put("transaction_id", UUID.randomUUID().toString());
        jsonObject.put("timestamp", new Date().getTime());
        if (bit0 != null) {
            jsonObject.put("bit0", bit0);
        }
        if (bit1 != null) {
            jsonObject.put("bit1", bit1);
        }
        String json = jsonObject.toJSONString();

       com.squareup.okhttp.RequestBody body = RequestBody.create(JSON, json);

        String jwt = getJWTStr();
        if (jwt == null) {
            return null;
        }
        com.squareup.okhttp.Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Content-Length", String.valueOf(json.length()))
                .header("Authorization", "Bearer " + jwt)
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute();
    }

    private static String getJWTStr() {
        try {
            ECPrivateKey privateKey = getECPrivateKey("AuthKey_97647PM467.p8");


            return Jwts.builder()
                    .setHeaderParam("kid", "97647PM467")
                    .setIssuer("P3X9NZB9JD")
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.ES256, privateKey)
                    .compact();
        } catch (Exception e) {
            return null;
        }
    }
    private static ECPrivateKey getECPrivateKey(String p8FilePath) throws Exception {

        final FileInputStream fileInputStream = new FileInputStream(new File(p8FilePath));
        final ECPrivateKey signingKey;
        {
            final String base64EncodedPrivateKey;
            {
                final StringBuilder privateKeyBuilder = new StringBuilder();

                final BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
                boolean haveReadHeader = false;
                boolean haveReadFooter = false;

                for (String line; (line = reader.readLine()) != null; ) {
                    if (!haveReadHeader) {
                        if (line.contains("BEGIN PRIVATE KEY")) {
                            haveReadHeader = true;
                        }
                    } else {
                        if (line.contains("END PRIVATE KEY")) {
                            haveReadFooter = true;
                            break;
                        } else {
                            privateKeyBuilder.append(line);
                        }
                    }
                }

                if (!(haveReadHeader && haveReadFooter)) {
                    throw new IOException("Could not find private key header/footer");
                }

                base64EncodedPrivateKey = privateKeyBuilder.toString();
            }

            final byte[] keyBytes = Base64.getDecoder().decode(base64EncodedPrivateKey.getBytes(StandardCharsets.US_ASCII));

            final PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            final KeyFactory keyFactory = KeyFactory.getInstance("EC");

            try {
                signingKey = (ECPrivateKey) keyFactory.generatePrivate(keySpec);
            } catch (InvalidKeySpecException e) {
                throw new InvalidKeyException(e);
            }
        }
        return signingKey;
    }
}