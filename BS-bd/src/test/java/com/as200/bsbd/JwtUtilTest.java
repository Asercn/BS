package com.as200.bsbd;

import com.as200.bsbd.common.util.JwtUtil;
import com.as200.bsbd.sys.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testCreateJwt(){
        User user = new User();
        user.setUsername("test1");
        user.setPassword("123465");
        user.setPhone("12345678901");

        String token = jwtUtil.createToken(user);
        System.out.println(token);

    }

    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0NjY1M2M5Ni1lZDdiLTRmMzktYTU5ZC1jYjZkNzFjYTc3MDkiLCJzdWIiOiJ7XCJwYXNzd29yZFwiOlwiMTIzNDY1XCIsXCJwaG9uZVwiOlwiMTIzNDU2Nzg5MDFcIixcInVzZXJuYW1lXCI6XCJ0ZXN0MVwifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTY5NzI2MDg0NCwiZXhwIjoxNjk3MzA0MDQ0fQ.TaDUAe9dTtA49lA6444Psi4oSJ8Lxu4Jt5eAe7_qdKo";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);
    }

    @Test
    public void testParseJwt1(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5NzZkYThiMy0yZjUwLTQ5ZmUtYjNlYy02YjQ4MTU0Mzg0MDUiLCJzdWIiOiJ7XCJwYXNzd29yZFwiOlwidGVzdDFcIixcInBob25lXCI6XCIxMjM0NTY3ODkwMVwiLFwidXNlcm5hbWVcIjpcInRlc3QxXCJ9IiwiaXNzIjoic3lzdGVtIiwiaWF0IjoxNjk1Njk2MTQ4LCJleHAiOjE2OTU3MzkzNDh9.qlIcmsKZ-P9s7ZvdUatzebbE3wqMRGasrHyAgdIsLlg";
        User user = jwtUtil.parseToken(token, User.class);
        System.out.println(user);
    }
}
