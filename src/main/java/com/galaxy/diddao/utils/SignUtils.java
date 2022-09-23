package com.galaxy.diddao.utils;

import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @Author Ant
 * @Date 2022/9/23
 * @Description: 交易签名工具类
 */
public class SignUtils {

    public static final String PERSONAL_MESSAGE_PREFIX = "\u0019Ethereum Signed Message:\n";

    /**
     * 根据签名和签名信息获取地址
     *
     * @param signature 签名
     * @param message   签名信息
     * @return
     */
    public static String getRecoverAddressFromSignature(String signature, String message) {

//        String signature =
//                "0x2c6401216c9031b9a6fb8cbfccab4fcec6c951cdf40e2320108d1856eb532250576865fbcd452bcdc4c57321b619ed7a9cfd38bd973c3e1e0243ac2777fe9d5b1b";

        String address = "0x31b26e43651e9371c88af3d36c14cfd938baf4fd";
//        String message = "v0G9u7huK4mJb2K1";

        String prefix = PERSONAL_MESSAGE_PREFIX + message.length();
        byte[] msgHash = Hash.sha3((prefix + message).getBytes());

        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
        byte v = signatureBytes[64];
        if (v < 27) {
            v += 27;
        }

        Sign.SignatureData sd =
                new Sign.SignatureData(
                        v,
                        (byte[]) Arrays.copyOfRange(signatureBytes, 0, 32),
                        (byte[]) Arrays.copyOfRange(signatureBytes, 32, 64));

        String addressRecovered = null;
        boolean match = false;

        // Iterate for each possible key to recover
        for (int i = 0; i < 4; i++) {
            BigInteger publicKey =
                    Sign.recoverFromSignature(
                            (byte) i,
                            new ECDSASignature(
                                    new BigInteger(1, sd.getR()), new BigInteger(1, sd.getS())),
                            msgHash);

            if (publicKey != null) {
                addressRecovered = "0x" + Keys.getAddress(publicKey);

                return addressRecovered;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String signature =
                "0x2c6401216c9031b9a6fb8cbfccab4fcec6c951cdf40e2320108d1856eb532250576865fbcd452bcdc4c57321b619ed7a9cfd38bd973c3e1e0243ac2777fe9d5b1b";
        String message = "v0G9u7huK4mJb2K1";

        String address = "0x31b26e43651e9371c88af3d36c14cfd938baf4fd";

        System.out.println(getRecoverAddressFromSignature(signature, message));
        System.out.println(address.equals(getRecoverAddressFromSignature(signature, message)));

    }

}
