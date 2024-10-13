package com.mgmetehan.userjwtsecurityexample2.auth.constants;

import com.mgmetehan.userjwtsecurityexample2.auth.utils.KeyConverter;

import java.security.PrivateKey;
import java.security.PublicKey;

public final class TokenConfigurationConstants {
    public static final String ISSUER = "ISSUER";
    public static final int ACCESS_TOKEN_EXPIRE_MINUTE = 30;
    public static final int REFRESH_TOKEN_EXPIRE_DAY = 1;
    public static final PublicKey PUBLIC_KEY = KeyConverter.convertPublicKey("""
            -----BEGIN PUBLIC KEY-----
            MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1K/N0Eqnp+qmalSBGDwG
            wICYuedh/fU9DLrWEZrlzcf07hZswkA1ymQdHSTh3Q8UEdvQynbA9SbHQjFwnk8I
            PnNiB7zad+BGED/Da8Osu1B8oAA9666nj8KXvOYN0yAJ/QJ3VcE3z0AiETECUYYF
            46EMbEkf5peSyMx1ENm5D+X6fn7THXQoE925aPf9zRpH/c0Y5QXmH+JHTtGIgBFc
            Kj7ML/bxWK722kovcv+SQOhDkd5Gs1F5afI0pcf9KhzwfeD1hffAolRW1PqKlzvg
            yYRAUjCXpex5r/xP39tJ09y+la5adWdPA9dFWAxs+5Gl+MWfzx0JkP98n+KJbTPm
            pwIDAQAB
            -----END PUBLIC KEY-----
                        """);//online rsa key generator
    public static final PrivateKey PRIVATE_KEY = KeyConverter.convertPrivateKey("""
            -----BEGIN PRIVATE KEY-----
            MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDUr83QSqen6qZq
            VIEYPAbAgJi552H99T0MutYRmuXNx/TuFmzCQDXKZB0dJOHdDxQR29DKdsD1JsdC
            MXCeTwg+c2IHvNp34EYQP8Nrw6y7UHygAD3rrqePwpe85g3TIAn9AndVwTfPQCIR
            MQJRhgXjoQxsSR/ml5LIzHUQ2bkP5fp+ftMddCgT3blo9/3NGkf9zRjlBeYf4kdO
            0YiAEVwqPswv9vFYrvbaSi9y/5JA6EOR3kazUXlp8jSlx/0qHPB94PWF98CiVFbU
            +oqXO+DJhEBSMJel7Hmv/E/f20nT3L6Vrlp1Z08D10VYDGz7kaX4xZ/PHQmQ/3yf
            4oltM+anAgMBAAECggEAR0XqyKRb7q95uSQ8Zqym7peplpLtibdHEB8EYb3wyLb7
            Y1XseGQgIoTOJNgZQIUvCpUJMO/+1ah7GbmLreF6N8Ne1Iz7ATUNBaD4kgJuvg7I
            1K2AEpzi1hA13T2S4G9aKYlMCFbJpxAs2mn8BunMy21Kxnby08lzjQrHKDBuDd89
            BOQWcGUWDqHi6ZSrCzk6cE5Yb+B3fAwConvrSehgUL55YyLGXOk3fpmkmrqh1lnv
            W1AHCsvrKnK17i1kdqhTQMSEQZW/xzSUI7p3fG3sgHE3a3GG6g7GlVJPkL+Iy06m
            myNX4HKNyglaDX0oUQR/ZzR/9ZRV9ZN2Ef0Veygr8QKBgQDaGPQoMeQ56+qZkpJ1
            yL9oiWK2TcselZRBjM1dHyNvsclJzXPMVMNu2ga/6pxHsO8afuWeNCV0U6RwX4rc
            P4FMWyCP6i5us2OTYjRwTpIY+tmgojvF5HLQ+Q6JfJkvJOxzs3bcRuZSis3Vkmmz
            oAv2YWHdlYYw1JpqMNVfoQnCQwKBgQD5piELVktuc2QmrKXeLt/sj+R3dAot5tEO
            kH43M0lU1Gtpy01kArxpNOoKI+T5j8QgdQJ4BPozUAE/wMGnzgpVEZKTLyvNM4vl
            UYu2eHA7sSHZGXu0RQi5MhqwUkAO2pZINIMhuYmb+TLK9nBeyNPNh1n6GvPY+Fba
            7pVXNVtdzQKBgHGt8BXDa6KFRZMmwxCBwsNKWRmNEwKuFMjWC3UuddoSYUkGvgUT
            aYquOjZuo2b64qZ83lLH+33W2y93nXmP4GVcWmypM+qftpdr6MlhKuW6WxVPerh7
            ix1BtEIVoALizBzCvoGysvrzPrbDyjTGh3W07L4C4h8xsgsHyTA/UEbhAoGAOhGW
            ZhiwewSEKyrFeiTVGSVvMwnexEaIIich9J0NVZoDUhxj5/tq793LAojO3e2+5P3S
            kTA3MnBNmhKv+ORIKvlnaqzyN0ziIXkSUmM5V+kfwmvXBlt4Z/trkc/P7qSRiEIO
            rm2FwK36TpN8F9AG1CvDlhVm2vUI3uvX+O0ET5ECgYAL0vLPHuBJ9TdEj1atYkQi
            U6P+9PVO3V6SyGbnYBwa78lLINIHddp8kDiPmA/ZYCzj8TD1q9P4bK6ukPtou+Gf
            gVpXNrQUnn9kAKV/+c3SOCXmqRzUOJvwVZ2k5RVebRLW9Tfai/4uxwSFK5oToEhI
            cU6fRQMHx752QDuiUzMc+A==
            -----END PRIVATE KEY-----
            """);//online rsa key generator
}
