package signin.company.com.recyclerviewdemo;

import org.junit.Test;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;

//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secret");
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes, sigAlg.getJcaName());
        Key key = MacProvider.generateKey();
        System.out.println(new String(key.getAlgorithm()));
        System.out.println(new String(key.getEncoded()).length());
        System.out.println(new String(key.getFormat()));
//        System.out.println(request.getUserPrincipal().getName());
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("username",new Model("lisi"));
        hashMap.put("age",new Model("25"));
        JwtBuilder builder = Jwts.builder()
                .setSubject("zhangsan")
                .setClaims(hashMap)
                .signWith(sigAlg, key);
        String jwtStr = builder.compact();
        Map user = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(jwtStr).getBody().get("age", Map.class);
        Map user1 = Jwts.parser().setSigningKey(key)
                .parseClaimsJws(jwtStr).getBody().get("username", Map.class);

        System.out.println(Jwts.parser().setSigningKey(key)
                .parseClaimsJws(jwtStr).getBody().getSubject());
        System.out.println(user.toString());
        System.out.println(user1.toString());
    }

}