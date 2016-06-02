/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigint;

import java.math.BigInteger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marr
 */
public class BigIntTest {

    public BigIntTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIntConstructor() {
        BigInt big = new BigInt((int) 206460);
        String result = big.toString();
        String expected = "206460";
        assertEquals(expected, result);
    }

    @Test
    public void testLongConstructor() {
        BigInt big = new BigInt(20644494346460L);
        String result = big.toString();
        String expected = "20644494346460";
        assertEquals(expected, result);
    }

    @Test
    public void testFLoatConstructor() {
        BigInt big = new BigInt((float) 20644494.164);
        String result = big.toString();
        String expected = "20644494";
        assertEquals(expected, result);
    }

    @Test
    public void testDoubleConstructor() {
        BigInt big = new BigInt((double) 20644494346460.164);
        String result = big.toString();
        String expected = "20644494346460";
        assertEquals(expected, result);
    }

    @Test
    public void testByteArrayConstructor() {
        String value = "534645263445623454234532426654234642";
        BigInt big1 = new BigInt(value);
        BigInt big2 = new BigInt(big1.toByteArray());
        assertEquals(big1.toString(), big2.toString());
    }

    @Test
    public void testByteArrayConstructor1() {
        String value = "4252594967295";
        BigInt big1 = new BigInt(value);
        BigInt big2 = new BigInt(big1.toByteArray());
        BigInteger big3 = new BigInteger(value);
        assertEquals(big2.toString(), big3.toString());
    }

//    @Test
//    public void testHexConstructor(){
//        String decValue = "79446667167";
//        String hexValue = "127F63EF9F";
//        BigInt decBig = new BigInt(decValue);
//        BigInt hexBig = new BigInt(hexValue, 16);
//        assertEquals(decBig.toString(), hexBig.toString());
//    }
//    
    @Test
    public void testToStringPositive() {
        String val = "4444444444";
        BigInt big = new BigInt(val);
        String result = big.toString();
        String expected = val;
        assertEquals(expected, result);
    }

    @Test
    public void testToStringNegative() {
        BigInt big = new BigInt("-1536464644423542340000");
        String result = big.toString();
        String expected = "-1536464644423542340000";
        assertEquals(expected, result);
    }

//    @Test
//    public void testToHexString() {
//        String value = "197767964796746917641300";
//        BigInt big1 = new BigInt(value);
//        BigInteger big2 = new BigInteger(value);
//        assertEquals(big2.toString(16).toUpperCase(), big1.toHexString());
//    }

    @Test
    public void testAdd() {
        BigInt o1 = new BigInt("2339979491100319853346466664138");
        BigInt o2 = new BigInt("46464164894989979113000316494941");
        BigInt summ = o1.add(o2);
        String expResult = "48804144386090298966346783159079";
        String result = summ.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddZeroos() {
        BigInt big1 = new BigInt("6400000000000");
        BigInt big2 = new BigInt("1200000000000");
        String expResult = "7600000000000";
        BigInt res = big1.add(big2);
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsZeroTrue() {
        BigInt o1 = new BigInt("0");
        assertTrue(o1.isZero());
    }

    @Test
    public void testIsZeroFalse() {
        BigInt o1 = new BigInt("5464613648979");
        assertFalse(o1.isZero());
    }

    @Test
    public void testCompareToLessThan() {
        BigInt o1 = new BigInt("994792374012502193471203951057");
        BigInt o2 = new BigInt("464616346463464644494164699711");
        int expResult = 1;
        int result = o1.compareTo(o2);
        assertEquals(expResult, result);
    }

    @Test
    public void testCompareToGreaterThan() {
        BigInt o1 = new BigInt("290131649494694110684698498");
        BigInt o2 = new BigInt("933549819817697979767917979");
        int expResult = -1;
        int result = o1.compareTo(o2);
        assertEquals(expResult, result);
    }

    @Test
    public void testCompareToEqual() {
        BigInt o1 = new BigInt("464616346463446");
        BigInt o2 = new BigInt("464616346463446");
        int expResult = 0;
        int result = o1.compareTo(o2);
        assertEquals(expResult, result);
    }

    @Test
    public void testCompareToTrimmingLeadingZeros() {
        BigInt o1 = new BigInt("00664640464646984641648690");
        BigInt o2 = new BigInt("00000010064648779946464646");
        int expResult = 1;
        int result = o1.compareTo(o2);
        assertEquals(expResult, result);
    }

    @Test
    public void testIntValue() {
        BigInt instance = new BigInt("346461634");
        int expResult = 346461634;
        int result = instance.intValue();
        assertEquals(expResult, result);
    }

    @Test
    public void testIntValueNegative() {
        BigInt instance = new BigInt("-346461634");
        int expResult = -346461634;
        int result = instance.intValue();
        assertEquals(expResult, result);
    }

    @Test
    public void testLongValue() {
        BigInt instance = new BigInt("34646163414231224");
        long expResult = 34646163414231224L;
        long result = instance.longValue();
        assertEquals(expResult, result);
    }

    @Test
    public void testLongValueNegative() {
        BigInt instance = new BigInt("-34646163414231224");
        long expResult = -34646163414231224L;
        long result = instance.longValue();
        assertEquals(expResult, result);
    }

    @Test
    public void testFloatValue() {
        BigInt instance = new BigInt(694644953);
        float expResult = (float) 694644953.0;
        float result = instance.floatValue();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testFloatValueNegative() {
        BigInt instance = new BigInt(-646853);
        float expResult = (float) -646853.0;
        float result = instance.floatValue();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testDoubleValue() {
        BigInt instance = new BigInt("256464646461641");
        double expResult = 256464646461641.0;
        double result = instance.doubleValue();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testDoubleValueNegative() {
        BigInt instance = new BigInt("-79979179746387925");
        double expResult = -79979179746387925.0;
        double result = instance.doubleValue();
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testRandomBigInt(){
        int bits = 512;
        BigInt big = BigInt.getRandom(bits);
        assertEquals(bits, big.bitLength());
    }
    
    @Test
    public void testSubstractLess() {
        BigInt big1 = new BigInt("9999999999999999999999999999999999999999999999999999");
        BigInt big2 = new BigInt("8888888888888888888888888888888888888888888888888888");
        String expResult = "1111111111111111111111111111111111111111111111111111";
        BigInt res = big1.subtract(big2);
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testSubstractGreater() {
        BigInt big1 = new BigInt("7161616166131313161");
        BigInt big2 = new BigInt("8496196416494949984");
        String expResult = "-1334580250363636823";
        BigInt res = big1.subtract(big2);
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testSubstractLessZeros() {
        BigInt big1 = new BigInt("6400000000000");
        BigInt big2 = new BigInt("1200000000000");
        String expResult = "5200000000000";
        BigInt res = big1.subtract(big2);
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testSubstractGreaterZeros() {
        BigInt big1 = new BigInt("1200000000000");
        BigInt big2 = new BigInt("6400000000000");
        String expResult = "-5200000000000";
        BigInt res = big1.subtract(big2);
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testSubtractEquals() {
        BigInt big1 = new BigInt(46464000);
        BigInt big2 = new BigInt(46464000);
        String expResult = "0";
        BigInt res = big1.subtract(big2);
        String result = res.toString();
        assertEquals(expResult, result);
        assertTrue(res.isZero());
    }

    @Test
    public void testBigMultiply() {
        String value1 = "6123412413412416456234653546567678766425346224563457846765862346423524562676786783242364646464";
        String value2 = "614235324523451243434325252464547564522547568634256776533431465685870758906464466767864564634";
        BigInt big2 = new BigInt(value1);
        BigInt big1 = new BigInt(value2);
        BigInteger bigInt1 = new BigInteger(value1);
        BigInteger bigInt2 = new BigInteger(value2);
        BigInteger bigInt3 = bigInt1.multiply(bigInt2);
        BigInt res = big2.multiply(big1);
        String expResult = bigInt3.toString();
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testSmallMultiply() {
        String value1 = "612";
        String value2 = "147";
        BigInt big2 = new BigInt(value1);
        BigInt big1 = new BigInt(value2);
        BigInteger bigInt1 = new BigInteger(value1);
        BigInteger bigInt2 = new BigInteger(value2);
        BigInteger bigInt3 = bigInt1.multiply(bigInt2);
        BigInt res = big2.multiply(big1);
        String expResult = bigInt3.toString();
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testSmallAndBigMultiply() {
        String value1 = "6123412413412416456234653245623246464897942364646464";
        String value2 = "6142";
        BigInt big2 = new BigInt(value1);
        BigInt big1 = new BigInt(value2);
        BigInteger bigInt1 = new BigInteger(value1);
        BigInteger bigInt2 = new BigInteger(value2);
        BigInteger bigInt3 = bigInt1.multiply(bigInt2);
        BigInt res = big2.multiply(big1);
        String expResult = bigInt3.toString();
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testShiftBaseLeft() {
        BigInt big1 = new BigInt("1346");
        BigInt big2 = big1.shift(2);
        String result = big2.toString();
        String expResult = "24829317523213056475136";
        assertEquals(expResult, result);
    }

    @Test
    public void testShiftBaseRight() {
        BigInt big1 = new BigInt("1420399294023527825408");
        BigInt big2 = big1.shift(-1);
        String result = big2.toString();
        String expResult = "330712481873";
        assertEquals(expResult, result);
    }

    @Test
    public void testShiftNone() {
        BigInt big1 = new BigInt("1631163");
        BigInt big2 = big1.shift(0);
        String result = big2.toString();
        String expResult = "1631163";
        assertEquals(expResult, result);
    }

    @Test
    public void testPowSmall() {
        String base = "9464353452435999";
        int exp = 644;
        BigInt baseBInt = new BigInt(base);

        BigInt resultBInt = baseBInt.pow(exp);
        String result = resultBInt.toString();

        BigInteger baseBInteger = new BigInteger(base);

        BigInteger resultBInteger = baseBInteger.pow(exp);
        String expResult = resultBInteger.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testModBig() {
        String base = "999464546163498641674649999999";
        String mod = "444444445425";
        BigInt baseBInt = new BigInt(base);
        BigInt modBInt = new BigInt(mod);
        BigInt resultBInt = baseBInt.mod(modBInt);
        String result = resultBInt.toString();

        BigInteger baseBInteger = new BigInteger(base);
        BigInteger modBInteger = new BigInteger(mod);
        BigInteger resultBInteger = baseBInteger.mod(modBInteger);
        String expResult = resultBInteger.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testModPowSmallPositive() {
        String base = "999469999";
        String mod = "464";
        String exp = "143";
        BigInt baseBInt = new BigInt(base);
        BigInt modBInt = new BigInt(mod);
        BigInt expBInt = new BigInt(exp);
        BigInt resultBInt = baseBInt.modPow(expBInt, modBInt);
        String result = resultBInt.toString();

        BigInteger baseBInteger = new BigInteger(base);
        BigInteger modBInteger = new BigInteger(mod);
        BigInteger expBInteger = new BigInteger(exp);
        BigInteger resultBInteger = baseBInteger.modPow(expBInteger, modBInteger);
        String expResult = resultBInteger.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testModPowBigPositive() {
        String base = "13123470993736547077347344312944322981233270032157105994387694686850345255075050749062718719862078205483739037635612439488160983984569230344563781596596601";
        String exp = "12236283845869197965382696703148170247819614994046365323444547330526880755298467415148481885391599510165379733390350893394664858718320148998984874439846501";
        String mod = "12236283845869197965382696703148170587822073510489647746724712616576743454826330691047107894374062193418086215512358782980418885191876985582330413455777973";
        BigInt baseBInt = new BigInt(base);
        BigInt modBInt = new BigInt(mod);
        BigInt expBInt = new BigInt(exp);
        BigInt resultBInt = baseBInt.modPow(expBInt, modBInt);
        String result = resultBInt.toString();
        BigInteger baseBInteger = new BigInteger(base);
        BigInteger modBInteger = new BigInteger(mod);
        BigInteger expBInteger = new BigInteger(exp);
        BigInteger resultBInteger = baseBInteger.modPow(expBInteger, modBInteger);
        String expResult = resultBInteger.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testLeastSignificantBitTrue() {
        BigInt b = new BigInt("67968463");
        assertTrue(b.leastSignificantBit());
    }

    @Test
    public void testLeastSignificantBitFalse() {
        BigInt b = new BigInt("6796846");
        assertFalse(b.leastSignificantBit());
    }

    @Test
    public void testPrime(){
        int prob = 10;
        BigInt b = BigInt.getProbalePrime(512);
        BigInteger bb = new BigInteger(b.toString());
        assertTrue(bb.isProbablePrime(prob));
    }
    
    @Test
    public void testDivideByBig() {
        String value1 = "6123412413412416456971672343452345235345431672344696469749474967694796479653245623242364646464";
        String value2 = "614235324523451243252547345324534523452396796749724646464464564634";
        BigInt big2 = new BigInt(value1);
        BigInt big1 = new BigInt(value2);
        BigInteger bigInt1 = new BigInteger(value1);
        BigInteger bigInt2 = new BigInteger(value2);
        BigInteger bigInt3 = bigInt1.divide(bigInt2);
        BigInt res = big2.divide(big1);
        String expResult = bigInt3.toString();
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testDivideBigBySmall() {
        String value1 = "61234124134124164562346997564653245623242364646464";
        String value2 = "3";
        BigInt big2 = new BigInt(value1);
        BigInt big1 = new BigInt(value2);
        BigInteger bigInt1 = new BigInteger(value1);
        BigInteger bigInt2 = new BigInteger(value2);
        BigInteger bigInt3 = bigInt1.divide(bigInt2);
        BigInt res = big2.divide(big1);
        String expResult = bigInt3.toString();
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testDivideByGreater() {
        String value1 = "616456234653245623242364646464";
        String value2 = "714235324523451243252524646464464564634";
        BigInt big2 = new BigInt(value1);
        BigInt big1 = new BigInt(value2);
        BigInteger bigInt1 = new BigInteger(value1);
        BigInteger bigInt2 = new BigInteger(value2);
        BigInteger bigInt3 = bigInt1.divide(bigInt2);
        BigInt res = big2.divide(big1);
        String expResult = bigInt3.toString();
        String result = res.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testDivideByEqualSameSign() {
        BigInt big1 = new BigInt("654654");
        BigInt big2 = new BigInt("654654");
        BigInt big3 = big1.divide(big2);
        String result = "1";
        String expResult = big3.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testDivideByEqualDifferentSign() {
        BigInt big1 = new BigInt("654654");
        BigInt big2 = new BigInt("-654654");
        BigInt big3 = big1.divide(big2);
        String result = "-1";
        String expResult = big3.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testGcd(){
        String value1 = "623446632444764";
        String value2 = "169654456123478";
        BigInt big1 = new BigInt(value1);
        BigInt big2 = new BigInt(value2);
        BigInt big3 = BigInt.gcd(big1, big2);
        BigInteger bbig1 = new BigInteger(value1);
        BigInteger bbig2 = new BigInteger(value2);
        BigInteger bbig3 = bbig1.gcd(bbig2);
        assertEquals(bbig3.toString(), big3.toString());
    }
    
    @Test
    public void testShiftRight() {
        String value = "15196729642949642949672429496729696729429496729661672942967294292312001";
        BigInt b1 = new BigInt(value);
        b1 = b1.shiftOneRight();
        BigInteger b2 = new BigInteger(value);
        b2 = b2.shiftRight(1);
        String expResult = b2.toString();
        String result = b1.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void testShiftRightOneToZero() {
        BigInt b1 = new BigInt("1");
        b1 = b1.shiftOneRight();
        assertTrue(b1.isZero());
    }

    @Test
    public void testShiftRightZeroToZero() {
        BigInt b1 = new BigInt("0");
        b1 = b1.shiftOneRight();
        assertTrue(b1.isZero());
    }

    @Test
    public void testBitCount() {
        String value = "56446311";
        BigInt big1 = new BigInt(value);
        BigInteger big2 = new BigInteger(value);
        assertEquals(big2.bitLength(), big1.bitLength());
    }

    @Test
    public void testToByteArray() {
        String value = "564463046411";
        BigInt big1 = new BigInt(value);
        BigInteger big2 = new BigInteger(value);
        byte[] bytes2 = big2.toByteArray();
        byte[] bytes1 = big1.toByteArray();
        assertEquals(bytes2[1], bytes1[4]);
    }

}
