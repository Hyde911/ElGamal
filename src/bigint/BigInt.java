/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bigint;

import java.util.Arrays;

/**
 *
 * @author marr
 */
public class BigInt extends Number implements Comparable<BigInt> {

    private static final long MASK = (1L << 32) - 1;
    private int[] digits;
    private int size;
    private int signum;
    public static final BigInt ZERO = new BigInt(0);

    public BigInt(String value) {
        setUpDigits(value.toCharArray());
    }

    private BigInt(int[] digits, int signum) {
        this.digits = trimmLeadingZeros(digits);
        this.size = this.digits.length;
        this.signum = signum;
    }

    public BigInt(int value) {
        this(String.valueOf(value));
    }

    public BigInt(long value) {
        this(String.valueOf(value));
    }

    public BigInt(double value) {
        this(String.valueOf((long) value));
    }

    public BigInt(float value) {
        this(String.valueOf((long) value));
    }

    public BigInt(byte[] data) {
        signum = 1;
        int bitLength = data.length;
        digits = new int[bitLength / 4 + 1];
        for (int i = 0, j = 0; i < bitLength;) {
            digits[j] = (data[i++] & 0xFF);
            if (i >= bitLength) {
                break;
            }
            for (int k = 1; k < 4; k++) {
                digits[j] |= (data[i++] << (8 * k) & (0xff << 8 * k));
                if (i >= bitLength) {
                    break;
                }
            }
            j++;
        }
        digits = trimmLeadingZeros(digits);
        size = digits.length;
    }

    @Override
    public String toString() {
        if (isZero()) {
            return ("0");
        }
        if (digits.length == 0) {
            return "0";
        }
        int max = size * 10;
        final char[] buffer = new char[max];
        Arrays.fill(buffer, '0');
        final int[] copy = Arrays.copyOf(digits, size);
        while (true) {
            final int j = max;
            for (int tmp = udivDigits(1000000000); tmp > 0; tmp /= 10) {
                buffer[--max] += tmp % 10;
            }
            if (size == 1 && digits[0] == 0) {
                break;
            } else {
                max = j - 9;
            }
        }
        if (signum < 0) {
            buffer[--max] = '-';
        }
        System.arraycopy(copy, 0, digits, 0, size = copy.length);
        return new String(buffer, max, buffer.length - max);
    }

    public boolean isZero() {
        for (int i : digits) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public BigInt add(final BigInt other) {
        int[] newDigits;
        int newSignum = 1;
        if (this.signum + other.signum > 0) {
            newDigits = addition(other.digits, this.digits);
            newSignum = 1;
        } else {
            return subtract(other);
        }
        return new BigInt(newDigits, newSignum);
    }

    public BigInt subtract(final BigInt other) {
        int[] newDigits;
        int newSignum;
        int compare = this.compareTo(other);
        if (compare == 0) {
            return new BigInt("0");
        } else if (compare > 0) {
            newDigits = substraction(this.digits, other.digits);
            newSignum = 1;
        } else {
            newDigits = substraction(other.digits, this.digits);
            newSignum = -1;
        }
        return new BigInt(trimmLeadingZeros(newDigits), newSignum);
    }

    public BigInt multiply(final BigInt other) {
        if (this.isZero() || other.isZero()) {
            return new BigInt(0);
        }
        int[] newDigits = multiplication(digits, size, 0, other.digits, other.size, 0);
        return new BigInt(newDigits, signum * other.signum);
    }

    public BigInt shift(int shift) {
        return new BigInt(shiftBaseTimes(digits, shift), signum);
    }

    public BigInt pow(int exponent) {
        if (exponent == 0) {
            return new BigInt("1");
        } else if (exponent < 0) {
            throw new ArithmeticException("negative exponent");
        } else {
            BigInt big = new BigInt(this.toString());
            for (int i = 1; i < exponent; i++) {
                big = big.multiply(this);

            }
            return big;
        }
    }

    public BigInt divide(BigInt divider) {
        if (divider.isZero()) {
            throw new ArithmeticException("dividing by zero");
        }
        if (divider.size == 1) {
            int[] newDigits = Arrays.copyOf(digits, size);
            udiv(divider.digits[0], newDigits);
            return new BigInt(trimmLeadingZeros(newDigits), this.signum * divider.signum);
        }
        if (this.compareTo(divider) == 0) {
            int[] one = {1};
            return new BigInt(one, this.signum * divider.signum);
        }
        if (this.compareTo(divider) < 0) {
            return new BigInt("0");
        }

        final int[] newDigits = new int[this.size - divider.size + 1];
        if (size == digits.length) {
            final int[] res = new int[size + 1];
            System.arraycopy(digits, 0, res, 0, size);
            digits = res;
        }
        int[] divident = Arrays.copyOf(digits, size + 1);
        int[] div = Arrays.copyOf(divider.digits, divider.size + 1);
        div(divident, div, size, divider.size, newDigits);
        return new BigInt(trimmLeadingZeros(newDigits), 1);

    }

    public BigInt shiftLeft(int n) {
        if (n == 0) {
            return this;
        } else {
            return new BigInt(shiftLeft(digits, n), this.signum);
        }

    }

    public byte[] toByteArray() {
        int byteLength = bitLength() / 8 + 1;
        byte[] byteArray = new byte[byteLength];

        for (int i = 0, j = 0; i < digits.length; i++) {
            int currentInt = digits[i];
            for (int k = 0; k < 4; k++) {
                if (j >= byteLength) {
                    break;
                }
                byteArray[j++] = (byte) currentInt;
                currentInt >>>= 8;
            }
        }
        if (byteArray[byteLength - 1] == 0x00) {
            byteArray = Arrays.copyOfRange(byteArray, 0, byteLength - 1);
        }
        for (byte b : byteArray) {
//            System.out.println(b);
        }
        return byteArray;
    }

    public BigInt shiftOneRight() {
        return shiftR();
    }

    public BigInt mod(BigInt m) {
        if (m.isZero()) {
            throw new ArithmeticException();
        }
//        System.out.println(toString() + "mod(" + m.toString() + ")");
        BigInt quotient = this.divide(m);
//        System.out.println("quotient: " + quotient.toString());
        BigInt mul = m.multiply(quotient);
//        System.out.println("mul:      " + mul.toString());
        BigInt reminder = this.subtract(mul);
//        System.out.println("rem:      " + reminder.toString());
        return reminder;
    }

    public int bitLength() {
        int length = digits.length;
        if (length == 0) {
            return 0;
        }
        int tmp1 = (length - 1) << 5;
        int tmp3 = digits[length - 1];
        int tmp2 = 32 - Integer.numberOfLeadingZeros(digits[length - 1]);
        return tmp1 + tmp2;
    }

    public BigInt modPow(BigInt exp, BigInt m) {
        exp = new BigInt(exp.digits, 1);
        if (exp.isZero()) {
            return new BigInt(1);
        } else if (exp.signum > 0) {
            return modPositivePow(exp, m);
        } else {
            return null;
        }
    }

    private int[] realloc(int[] dig, int newLen) {
        final int[] res = new int[newLen];
        Arrays.fill(res, 0);
        System.arraycopy(dig, 0, res, 0, dig.length);
        return res;
    }

    private void setUpDigits(char[] chars) {
        signum = 1;
        if (chars[0] == '-') {
            signum = -1;
        }
        size = chars.length + (signum - 1 >> 1);
        int allocation;
        if (size < 10) {
            allocation = 1;
        } else {
            allocation = (int) (size * 3402L >>> 10) + 32 >>> 5;
        }
        if (digits == null || digits.length < allocation) {
            digits = new int[allocation];
        }

        int i = size % 9;
        if (i == 0) {
            i = 9;
        }
        i -= (signum - 1 >> 1);

        digits[0] = parseChars(chars, 0 - (signum - 1 >> 1), i);
        for (size = 1; i < chars.length;) {
            int parse = parseChars(chars, i, i += 9);
            multiAdd(1000000000, parse);
        }
    }

    private int[] addition(final int[] n1, final int[] n2) {
        int size1 = n1.length;
        int size2 = n2.length;
        int[] add = new int[size1 + 1];
        long reminder = 0;
        for (int i = 0; i < size1; i++) {
            int digit1 = n1[i];
            int digit2;
            if (i >= size2) {
                digit2 = 0;
            } else {
                digit2 = n2[i];
            }
            reminder = (digit1 & MASK) + (digit2 & MASK) + reminder;
            add[i] = (int) (reminder & MASK);
            reminder >>>= 32;
        }

        add[size1] = (int) (reminder & MASK);

        if (add[size1] == 0) {
            return Arrays.copyOfRange(add, 0, size1);
        }
        return add;
    }

    private int[] substraction(final int[] n1, final int[] n2) {
        int size1 = n1.length;
        int size2 = n2.length;

        int[] sub = new int[size1];
        long reminder = 0;
        int i = 0;
        for (; i < size1; i++) {
            int digit1 = n1[i];
            int digit2;
            if (i >= size2) {
                digit2 = 0;
            } else {
                digit2 = n2[i];
            }
            reminder = (digit1 & MASK) - (digit2 & MASK) + reminder;
            sub[i] = (int) (reminder);
            reminder >>= 32;
        }
        if (reminder != 0) {

        }

        return sub;
    }

    private int[] multiplication(int[] n1, int size1, int startIndex1, int[] n2, int size2, int startIndex2) {
        if (size1 <= 128 || size2 <= 128) {
            return smallMultiplication(n1, size1, startIndex1, n2, size2, startIndex2);
        } else {
            int split = size1 / 2;
            if (size2 < size1) {
                split = size2 / 2;
            }
            int[] part1 = multiplication(n2, size2 - split, split, n1, size1 - split, split);//a c
            int[] part2 = multiplication(n2, split, 0, n1, split, 0);//b d
            int[] a_b = addArrays(n2, size2 - split, split, n2, split, 0);
            int[] c_d = addArrays(n1, size1 - split, split, n1, split, 0);
            int a_bSize = a_b.length;
            int c_dSize = c_d.length;
            int[] part3 = multiplication(a_b, a_bSize, 0, c_d, c_dSize, 0);

            return addArrays(addArrays(shiftBaseTimes(part1, 2 * split), shiftBaseTimes(substraction(part3, addArrays(part1, part2)), split)), part2);
        }
    }

    private static int[] shiftLeft(int[] mag, int n) {
        int ints = n >>> 5;
        int bits1 = n & 0x1f;
        int dLength = mag.length;
        int result[] = null;

        if (bits1 == 0) {
            result = new int[dLength + ints];
            System.arraycopy(mag, 0, result, 0, dLength);
        } else {
            int i = 0;
            int bits2 = 32 - bits1;
            int highBits = mag[0] >>> bits2;
            if (highBits != 0) {
                result = new int[dLength + ints + 1];
                result[i++] = highBits;
            } else {
                result = new int[dLength + ints];
            }
            int j = 0;
            while (j < dLength - 1) {
                result[i++] = mag[j++] << bits1 | mag[j] >>> bits2;
            }
            result[i] = mag[j] << bits1;
        }
        return result;
    }

    private int udiv(final int div, int[] n) {
        final long d = div & MASK;
        long rem = 0;
        int len = n.length;
        for (int i = len - 1; i >= 0; i--) {
            rem <<= 32;
            rem = rem + (n[i] & MASK);
            n[i] = (int) (rem / d);
            rem = rem % d;
        }
        if (n[len - 1] == 0 && len > 1) {
            n = Arrays.copyOfRange(n, 0, len - 1);
        }

        return (int) rem;
    }

    private BigInt shiftR() {
        int newMag[] = new int[size];
        newMag[0] = digits[0] >>> 1;
        for (int i = 1; i < size; i++) {
            int tmp = digits[i] << 31;
            int tmp2 = newMag[i - 1] | tmp;
            newMag[i - 1] = tmp2;
            newMag[i] = digits[i] >>> 1;
        }
        return new BigInt((newMag), signum);
    }

    public boolean leastSignificantBit() {
        if ((this.digits[0] & 0x01) == 1) {
            return true;
        }
        return false;
    }

    private int udivDigits(final int div) {
        final long d = div & MASK;
        long rem = 0;
        for (int i = size - 1; i >= 0; i--) {
            rem <<= 32;
            rem = rem + (digits[i] & MASK);
            digits[i] = (int) (rem / d);
            rem = rem % d;
        }
        if (digits[size - 1] == 0 && size > 1) {
            --size;
        }

        return (int) rem;
    }

    private BigInt modPositivePow(BigInt exp, BigInt mod) {
        BigInt x = this;
        BigInt s = new BigInt(1);
        BigInt z = x.mod(mod);
        int mask;
        for (int i = 0; i < exp.size; i++) {
            mask = 0x01;
            for (int j = 0; j < 32; j++) {
                if ((exp.digits[i] & mask) == mask) {
                    s = s.multiply(z).mod(mod);
                }
                z = z.multiply(z).mod(mod);
                mask <<= 1;
            }
        }
        s = new BigInt(s.digits, 1);
        return s;
    }

    private int[] addArrays(final int[] n1, int size1, int startIndex1, final int[] n2, int size2, int startIndex2) {
        if (n1.length >= n2.length) {
            return subArrayAddition(n1, size1, startIndex1, n2, size2);
        } else {
            return subArrayAddition(n2, size2, startIndex2, n1, size1);
        }
    }

    private int[] addArrays(int[] n1, int[] n2) {
        if (n1.length >= n2.length) {
            return addition(n1, n2);
        } else {
            return addition(n2, n1);
        }
    }

    private int[] subArrayAddition(final int[] n1, final int size1, int startIndex1, int[] n2, int size2) {
        int[] add = new int[size1 + 1];
        long reminder = 0;
        for (int i = startIndex1; i < size1; i++) {
            int digit1 = n1[i];
            int digit2;
            if (i >= size2) {
                digit2 = 0;
            } else {
                digit2 = n2[i];
            }
            reminder = (digit1 & MASK) + (digit2 & MASK) + reminder;
            add[i] = (int) (reminder & MASK);
            reminder >>>= 32;
        }

        add[size1] = (int) (reminder & MASK);
        if (add[size1] == 0) {
            return Arrays.copyOfRange(add, 0, size1);
        }
        return add;
    }

    private int[] smallMultiplication(int[] n1, int size1, int startIndex1, int[] n2, int size2, int startIndex2) {
        final int[] result = new int[size1 + size2];
        long carry = 0;
        long tmp;
        long fArg = n1[0] & MASK;
        for (int i = startIndex1; i < size2; i++) {
            tmp = fArg * (n2[i] & MASK) + carry;
            result[i] = (int) tmp;
            carry = tmp >>> 32;
        }
        result[size2] = (int) carry;

        for (int i = 1; i < size1; i++) {
            fArg = n1[i] & MASK;
            carry = 0;
            for (int j = 0; j < size2; j++) {
                tmp = fArg * (n2[j] & MASK) + (result[i + j] & MASK) + carry;
                result[i + j] = (int) tmp;
                carry = tmp >>> 32;
            }
            result[size2 + i] = (int) carry;
        }
        return result;
    }

    private int[] trimmLeadingZeros(final int[] n) {
        int zeros = 0;
        for (int i = n.length - 1; i >= 0; i--) {
            if (n[i] != 0) {
                break;
            }
            zeros++;
        }
        if (zeros == 0) {
            return n;
        } else if (zeros == n.length) {
            int ret[] = {0};
            return ret;
        } else {
            return Arrays.copyOfRange(n, 0, n.length - zeros);
        }
    }

    private int[] shiftBaseTimes(final int[] n, int shift) {
        if (shift == 0) {
            return n;
        }
        int len = n.length;
        if (len + shift <= 0) {
            return new int[1];
        }
        int[] result = new int[len + shift];
        if (shift > 0) {
            for (int i = 0; i < len; i++) {
                result[i + shift] = n[i];
            }
        } else {
            for (int i = 0; i < len + shift; i++) {
                result[i] = n[i - shift];
            }
        }
        return result;
    }

    private int parseChars(final char[] chars, int from, final int to) {
        int result = chars[from] - '0';
        while (++from < to) {
            result = result * 10 + chars[from] - '0';
        }
        return result;
    }

    private void multiAdd(final int mul, final int add) {
        long reminder = 0;
        for (int i = 0; i < size; i++) {
            reminder = mul * (digits[i] & MASK) + reminder;
            digits[i] = (int) reminder;
            reminder >>>= 32;
        }
        if (reminder != 0) {
            digits[size++] = (int) reminder;
        }
        reminder = (digits[0] & MASK) + add;
        digits[0] = (int) reminder;

        if ((reminder >>> 32) != 0) {
            int i = 1;
            while (i < size && ++digits[i] == 0) {
                ++i;
            }
            if (i == size) {
                digits[size++] = 1;
            }
        }

    }

    private int[] uintMul(int[] dig, int mul) {
        if (mul == 0) {
            int[] d = {0};
            return d;
        }
        long carry = 0;
        long n = mul & MASK;
        int[] newDigits = Arrays.copyOf(dig, dig.length);
        for (int i = 0; i < dig.length; i++) {
            carry = (newDigits[i] & MASK) * n + carry;
            newDigits[i] = (int) carry;
            carry >>>= 32;
        }
        if (carry == 0) {
            return newDigits;
        } else {
            int[] tmp = new int[dig.length + 1];
            System.arraycopy(newDigits, 0, tmp, 0, dig.length);
            tmp[dig.length] = (int) carry;
            return tmp;
        }
    }

    private void div(final int[] u, final int[] v, final int m, final int n, final int[] q) {
        final long b = 1L << 32;
        long qhat;
        long rhat;
        long p;

        int s, i, j;
        long t, k;
        s = Integer.numberOfLeadingZeros(v[n - 1]);
        if (s > 0) {
            for (i = n - 1; i > 0; i--) {
                v[i] = (v[i] << s) | (v[i - 1] >>> 32 - s);
            }
            v[0] = v[0] << s;

            u[m] = u[m - 1] >>> 32 - s;
            for (i = m - 1; i > 0; i--) {
                u[i] = (u[i] << s) | (u[i - 1] >>> 32 - s);
            }
            u[0] = u[0] << s;
        }

        final long dh = v[n - 1] & MASK, dl = v[n - 2] & MASK, hbit = Long.MIN_VALUE;

        for (j = m - n; j >= 0; j--) {
            k = u[j + n] * b + (u[j + n - 1] & MASK);
            qhat = (k >>> 1) / dh << 1;
            t = k - qhat * dh;
            if (t + hbit > dh + hbit) {
                qhat++;
            }
            rhat = k - qhat * dh;

            while (qhat + hbit >= b + hbit || qhat * dl + hbit > b * rhat + (u[j + n - 2] & MASK) + hbit) {
                qhat = qhat - 1;
                rhat = rhat + dh;
                if (rhat + hbit >= b + hbit) {
                    break;
                }
            }

            k = 0;
            for (i = 0; i < n; i++) {
                p = qhat * (v[i] & MASK);
                t = (u[i + j] & MASK) - k - (p & MASK);
                u[i + j] = (int) t;
                k = (p >>> 32) - (t >> 32);
            }
            t = (u[j + n] & MASK) - k;
            u[j + n] = (int) t;

            q[j] = (int) qhat;
            if (t < 0) {
                q[j] = q[j] - 1;
                k = 0;
                for (i = 0; i < n; i++) {
                    t = (u[i + j] & MASK) + (v[i] & MASK) + k;
                    u[i + j] = (int) t;
                    k = t >>> 32;
                }
                u[j + n] += (int) k;
            }
        }

        if (s > 0) {
            for (i = 0; i < n - 1; i++) {
                v[i] = v[i] >>> s | v[i + 1] << 32 - s;
            }
            v[n - 1] >>>= s;

            for (i = 0; i < m; i++) {
                u[i] = u[i] >>> s | u[i + 1] << 32 - s;
            }
            u[m] >>>= s;
        }
    }

    @Override
    public int compareTo(BigInt other) {
        if (this.signum * other.signum < 0) {
            if (this.signum < 0) {
                return -1;
            } else {
                return 1;
            }
        }
        if (size > other.size) {
            return 1;
        }
        if (size < other.size) {
            return -1;
        }
        for (int i = size - 1; i >= 0; i--) {
            if ((digits[i] & MASK) != (other.digits[i] & MASK)) {
                if ((digits[i] & MASK) > (other.digits[i] & MASK)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
        return 0;
    }

    @Override
    public int intValue() {
        return signum * (digits[0] & 0xFFFFFFFF);
    }

    @Override
    public long longValue() {
        return signum * ((digits[1] & 0x7FFFFFFFL) << 32 | (digits[0] & MASK));
    }

    @Override
    public float floatValue() {
        return (float) intValue();
    }

    @Override
    public double doubleValue() {
        return (double) longValue();
    }

    public int isNegative() {
        return signum;
    }

    public int getSize() {
        return size;
    }

}
