/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigint;

import java.math.BigInteger;

/**
 *
 * @author marr
 */
public class BigIntBenchmark {

    public static void main(String[] args) {
        BigIntBenchmark btest = new BigIntBenchmark();
    }

    public BigIntBenchmark() {
        addBenchmark();
        multiplyBenchmark();
        modBenchamrk();
        modPowBenchmark();

    }

    final public void addBenchmark() {
        System.out.println("----------------");
        System.out.println("Add benchmark");
        System.out.println("BEGIN");
        StringBuilder sb1 = new StringBuilder("");

        for (int i = 0; i < 1000; i++) {
            sb1.append("99999");

        }
        String big1 = sb1.toString();

        BigInt bigInt1 = new BigInt(big1);

        long start;
        long stop;

        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            bigInt1.add(bigInt1);
        }

        stop = System.currentTimeMillis();
        System.out.println("BigInt time[ms]: " + (stop - start));

        BigInteger baseBInteger = new BigInteger(big1);

        start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            baseBInteger.add(baseBInteger);
        }
        stop = System.currentTimeMillis();
        System.out.println("BigInteger time[ms]: " + (stop - start));
        System.out.println("same result: " + baseBInteger.toString().equals(bigInt1.toString()));
        System.out.println("END");
    }

    final public void multiplyBenchmark() {
        System.out.println("----------------");
        System.out.println("Multiply benchmark");
        System.out.println("BEGIN");
        int iteration = 50000;

        StringBuilder sb1 = new StringBuilder("");
        StringBuilder sb2 = new StringBuilder("");
//        for (int i = 0; i < 8000; i++) {
        sb1.append("77");
        sb2.append("88");

//        }
        String big1 = sb1.toString();
        String big2 = sb2.toString();

        BigInt bigInt1 = new BigInt(big1);
        BigInt bigInt2 = new BigInt(big2);

        long start;
        long stop;

        start = System.currentTimeMillis();
        for (int i = 0; i < iteration; i++) {
            bigInt1 = bigInt1.multiply(bigInt2);
        }
        stop = System.currentTimeMillis();
        System.out.println("BigInt time[ms]: " + (stop - start));

        BigInteger baseBInteger1 = new BigInteger(big1);
        BigInteger baseBInteger2 = new BigInteger(big2);

        start = System.currentTimeMillis();
        for (int i = 0; i < iteration; i++) {
            baseBInteger1 = baseBInteger1.multiply(baseBInteger2);
        }
        stop = System.currentTimeMillis();
        System.out.println("BigInteger time[ms]: " + (stop - start));
        System.out.println("same result: " + baseBInteger1.toString().equals(bigInt1.toString()));
        System.out.println("END");
    }

    final public void modPowBenchmark() {
        System.out.println("----------------");
        System.out.println("ModPow benchmark");
        System.out.println("BEGIN");
        String base = "976348348634325454354354354354354312341266666666452634623424562345234525624789878673542346362346232353";
        String exp = "10205534528697894532222222224535435423543245235453454354354235342235423453651234123466666666668452531746666666666246345634563456456346245634562340000000";
        String mod = "16493324342341234152133122132312133452346235665545454653654766666623452345643734562363456342345345324523455324";
        BigInt baseBInt = new BigInt(base);
        BigInt expBInt = new BigInt(exp);
        BigInt modBInt = new BigInt(mod);

        long start;
        long stop;

        start = System.currentTimeMillis();
        BigInt result = baseBInt.modPow(expBInt, modBInt);
        stop = System.currentTimeMillis();
        System.out.println("BigInt time[ms]: " + (stop - start));

        BigInteger baseBInteger = new BigInteger(base);
        BigInteger expBInteger = new BigInteger(exp);
        BigInteger modBInteger = new BigInteger(mod);
        start = System.currentTimeMillis();
        BigInteger res = baseBInteger.modPow(expBInteger, modBInteger);
        stop = System.currentTimeMillis();
        System.out.println("BigInteger time[ms]: " + (stop - start));
        System.out.println("same result: " + res.toString().equals(result.toString()));

        System.out.println("END");
    }

    final public void modBenchamrk() {
        System.out.println("----------------");
        System.out.println("Mod benchmark");
        System.out.println("BEGIN");
        StringBuilder baseS = new StringBuilder("");
        StringBuilder modS = new StringBuilder("");
        for (int i = 0; i < 1000; i++) {
            baseS.append("13");

        }
        modS.append("216");
        String base = baseS.toString();
        String mod = modS.toString();

        BigInt baseBInt = new BigInt(base);
        BigInt modBInt = new BigInt(mod);

        long start;
        long stop;

        start = System.currentTimeMillis();
        BigInt result = baseBInt.mod(modBInt);
        stop = System.currentTimeMillis();
        System.out.println("BigInt time[ms]: " + (stop - start));

        BigInteger baseBInteger = new BigInteger(base);
        BigInteger modBInteger = new BigInteger(mod);

        start = System.currentTimeMillis();
        BigInteger res = baseBInteger.mod(modBInteger);
        stop = System.currentTimeMillis();
        System.out.println("BigInteger time[ms]: " + (stop - start));
        System.out.println("same result: " + res.toString().equals(result.toString()));
        System.out.println("END");
    }
}
