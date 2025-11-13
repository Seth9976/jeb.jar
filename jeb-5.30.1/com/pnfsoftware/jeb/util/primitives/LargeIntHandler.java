package com.pnfsoftware.jeb.util.primitives;

import com.pnfsoftware.jeb.util.format.Strings;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class LargeIntHandler {
   private static Map map = new HashMap();
   private int bitsize;
   private BigInteger max;
   private BigInteger mask;

   public static LargeIntHandler create(int var0) {
      LargeIntHandler var1 = (LargeIntHandler)map.get(var0);
      if (var1 == null) {
         var1 = new LargeIntHandler(var0);
         map.put(var0, var1);
      }

      return var1;
   }

   private LargeIntHandler(int var1) {
      this.bitsize = var1;
      this.max = BigInteger.ONE.shiftLeft(var1);
      this.mask = this.max.subtract(BigInteger.ONE);
   }

   public int getBitsize() {
      return this.bitsize;
   }

   private void verifyBitlength(BigInteger var1) {
      int var2 = var1.bitLength() + 1;
      if (var2 > this.bitsize) {
         throw new RuntimeException(Strings.ff("Value %s does not fit properly on %d bits", var1.toString(16), this.bitsize));
      }
   }

   private void verifyBitlength(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1);
      this.verifyBitlength(var2);
   }

   public BigInteger create(String var1) {
      BigInteger var2 = this.convert(var1);
      this.verifyBitlength(var2);
      return var2;
   }

   public BigInteger truncate(String var1) {
      return this.truncate(this.convert(var1));
   }

   public BigInteger truncate(BigInteger var1) {
      int var2 = var1.bitLength() + 1;
      if (var2 > this.bitsize) {
         var1 = var1.and(this.mask);
      }

      var2 = var1.bitLength() + 1;
      if (var2 > this.bitsize && var1.signum() == 1) {
         var1 = var1.subtract(this.max);
      }

      this.verifyBitlength(var1);
      return var1;
   }

   private BigInteger convert(String var1) {
      byte var2 = 10;
      var1 = var1.trim();
      if (var1.startsWith("0x")) {
         var2 = 16;
         var1 = var1.substring(2);
      } else if (var1.endsWith("h")) {
         var2 = 16;
         var1 = var1.substring(0, var1.length() - 1);
      } else if (var1.length() >= 2 && var1.startsWith("0")) {
         var2 = 8;
         var1 = var1.substring(1);
      }

      return new BigInteger(var1, var2);
   }

   public BigInteger toUnsigned(BigInteger var1) {
      this.verifyBitlength(var1);
      if (var1.signum() == -1) {
         var1 = var1.add(this.max);
      }

      return var1;
   }

   public BigInteger neg(BigInteger var1) {
      this.verifyBitlength(var1);
      BigInteger var2 = var1.negate();
      return this.truncate(var2);
   }

   public BigInteger add(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      BigInteger var3 = var1.add(var2);
      return this.truncate(var3);
   }

   public BigInteger sub(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      BigInteger var3 = var1.subtract(var2);
      return this.truncate(var3);
   }

   public BigInteger mulS(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      BigInteger var3 = var1.multiply(var2);
      return this.truncate(var3);
   }

   public BigInteger mulU(BigInteger var1, BigInteger var2) {
      var1 = this.toUnsigned(var1);
      var2 = this.toUnsigned(var2);
      BigInteger var3 = var1.multiply(var2);
      return this.truncate(var3);
   }

   public BigInteger mul2S(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      BigInteger var3 = var1.multiply(var2);
      return create(2 * this.bitsize).truncate(var3);
   }

   public BigInteger mul2U(BigInteger var1, BigInteger var2) {
      var1 = this.toUnsigned(var1);
      var2 = this.toUnsigned(var2);
      BigInteger var3 = var1.multiply(var2);
      return create(2 * this.bitsize).truncate(var3);
   }

   public BigInteger divS(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      BigInteger var3 = var1.divide(var2);
      return this.truncate(var3);
   }

   public BigInteger divU(BigInteger var1, BigInteger var2) {
      var1 = this.toUnsigned(var1);
      var2 = this.toUnsigned(var2);
      BigInteger var3 = var1.divide(var2);
      return this.truncate(var3);
   }

   public BigInteger remS(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      BigInteger var3 = var1.remainder(var2);
      return this.truncate(var3);
   }

   public BigInteger remU(BigInteger var1, BigInteger var2) {
      var1 = this.toUnsigned(var1);
      var2 = this.toUnsigned(var2);
      BigInteger var3 = var1.remainder(var2);
      return this.truncate(var3);
   }

   public BigInteger[] div2S(BigInteger var1, BigInteger var2) {
      create(2 * this.bitsize).verifyBitlength(var1);
      this.verifyBitlength(var2);
      BigInteger[] var3 = var1.divideAndRemainder(var2);
      var3[0] = this.truncate(var3[0]);
      var3[1] = this.truncate(var3[1]);
      return var3;
   }

   public BigInteger[] div2U(BigInteger var1, BigInteger var2) {
      var1 = create(2 * this.bitsize).toUnsigned(var1);
      var2 = this.toUnsigned(var2);
      BigInteger[] var3 = var1.divideAndRemainder(var2);
      var3[0] = this.truncate(var3[0]);
      var3[1] = this.truncate(var3[1]);
      return var3;
   }

   public BigInteger and(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      BigInteger var3 = var1.and(var2);
      return this.truncate(var3);
   }

   public BigInteger or(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      BigInteger var3 = var1.or(var2);
      return this.truncate(var3);
   }

   public BigInteger not(BigInteger var1) {
      this.verifyBitlength(var1);
      BigInteger var2 = var1.not();
      return this.truncate(var2);
   }

   public BigInteger xor(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      BigInteger var3 = var1.xor(var2);
      return this.truncate(var3);
   }

   public boolean testbit(BigInteger var1, int var2) {
      this.verifyBitlength(var1);
      return var1.testBit(var2);
   }

   public BigInteger setbit(BigInteger var1, int var2) {
      this.verifyBitlength(var1);
      BigInteger var3 = var1.setBit(var2);
      return this.truncate(var3);
   }

   public BigInteger clearbit(BigInteger var1, int var2) {
      this.verifyBitlength(var1);
      BigInteger var3 = var1.clearBit(var2);
      return this.truncate(var3);
   }

   private int modBitsize(int var1) {
      var1 %= this.bitsize;
      if (var1 < 0) {
         var1 += this.bitsize;
      }

      return var1;
   }

   public BigInteger shl(BigInteger var1, int var2) {
      this.verifyBitlength(var1);
      var2 = this.modBitsize(var2);
      BigInteger var3 = var1.shiftLeft(var2);
      return this.truncate(var3);
   }

   public BigInteger shr(BigInteger var1, int var2) {
      var1 = this.toUnsigned(var1);
      var2 = this.modBitsize(var2);
      BigInteger var3 = var1.shiftRight(var2);
      return this.truncate(var3);
   }

   public BigInteger sar(BigInteger var1, int var2) {
      this.verifyBitlength(var1);
      var2 = this.modBitsize(var2);
      BigInteger var3 = var1.shiftRight(var2);
      return this.truncate(var3);
   }

   public BigInteger ror(BigInteger var1, int var2) {
      var1 = this.toUnsigned(var1);
      var2 = this.modBitsize(var2);
      BigInteger var3 = var1.shiftRight(var2).or(var1.shiftLeft(this.bitsize - var2));
      return this.truncate(var3);
   }

   public BigInteger rol(BigInteger var1, int var2) {
      var1 = this.toUnsigned(var1);
      var2 = this.modBitsize(var2);
      BigInteger var3 = var1.shiftLeft(var2).or(var1.shiftRight(this.bitsize - var2));
      return this.truncate(var3);
   }

   public int compare(BigInteger var1, BigInteger var2) {
      this.verifyBitlength(var1, var2);
      return var1.compareTo(var2);
   }

   public int compareU(BigInteger var1, BigInteger var2) {
      var1 = this.toUnsigned(var1);
      var2 = this.toUnsigned(var2);
      return var1.compareTo(var2);
   }

   public String toUnsignedHexString(BigInteger var1) {
      return this.toUnsigned(var1).toString(16).toUpperCase();
   }
}
