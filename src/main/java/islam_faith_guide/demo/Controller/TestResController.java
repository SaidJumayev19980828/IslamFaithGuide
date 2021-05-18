package islam_faith_guide.demo.Controller;


import com.squareup.okhttp.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import islam_faith_guide.demo.DTO.Test_bit;
import islam_faith_guide.demo.DTO.Tested;
import islam_faith_guide.demo.Entity.UserData;
import lombok.SneakyThrows;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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


@RestController
//@RequestMapping("testRes")
public class TestResController {

    //
//    @Autowired
//    TestR
//    esultRepo testResultRepo;
//
//    @PostMapping("post")
//    public ResponseEntity<TestResult>postResult( @RequestBody TestResult testResult)
//    {
//        return ResponseEntity.status(HttpStatus.OK).body(testResultRepo.save(testResult));
//    }
//
//    @GetMapping("get")
//    public ResponseEntity<List<TestResult>> getResult(TestResult testResult, String name)
//    {
//        name=testResult.getUserName();
//     return ResponseEntity.status(HttpStatus.OK).body(testResultRepo.findByUserName(name)) ;
//    }
//    @SneakyThrows
@SneakyThrows
@PostMapping("query_two_bits")
private islam_faith_guide.demo.DTO.Response updateIOSDeviceCheckSample(@org.springframework.web.bind.annotation.RequestBody Tested tested) {
    //With this value you can get two bool values and the last modified date.

   Response response=postRequest(" https://api.development.devicecheck.apple.com/v1/query_two_bits", tested.getDevice_token(),null,null);
    islam_faith_guide.demo.DTO.Response response1=new islam_faith_guide.demo.DTO.Response();
    response1.setMessage(respone.body().string());


   return response1;

}



    @SneakyThrows
    @PostMapping("update_two_bits")
    private ResponseEntity<String> updateIOSDeviceCheckSample(@org.springframework.web.bind.annotation.RequestBody Test_bit test_bit) {
       Response response = postRequest("https://api.development.devicecheck.apple.com/v1/update_two_bits", test_bit.getDevice_token(), test_bit.isBit0(), test_bit.isBit1());
        return ResponseEntity.ok().body(response.body().string());
    }

    @SneakyThrows
    private static Response postRequest(String url, String deviceToken, Boolean bit0, Boolean bit1) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

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
        String json = jsonObject.toString();

        RequestBody body = RequestBody.create(JSON, json);
        String jwt = getJWTStr();
        System.out.println("TOKEN GENERATED-------"+getJWTStr());
        if (jwt == null) {
            return null;
        }
        Request request = new Request.Builder()
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
                    .signWith(SignatureAlgorithm.ES256 ,privateKey)
                    .compact();

        } catch (Exception e) {
            System.out.println(e+"tested");
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
