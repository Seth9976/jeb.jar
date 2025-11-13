package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.code.android.DexParsingException;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

@Ser
public final class bia implements IDexValue {
   @SerId(1)
   private int q = -1;
   @SerId(2)
   private byte RF;
   @SerId(3)
   private short xK;
   @SerId(4)
   private char Dw;
   @SerId(5)
   private int Uv;
   @SerId(6)
   private long oW;
   @SerId(7)
   private float gO;
   @SerId(8)
   private double nf;
   @SerId(9)
   private int gP;
   @SerId(10)
   private int za;
   @SerId(11)
   private int lm;
   @SerId(12)
   private int zz;
   @SerId(13)
   private int JY;
   @SerId(14)
   private bia[] HF;
   @SerId(15)
   private bhs LK;
   @SerId(16)
   private boolean io;

   private bia() {
   }

   @Override
   public int getType() {
      return this.q;
   }

   @Override
   public boolean isNull() {
      return this.q == 30;
   }

   @Override
   public byte getByte() {
      if (this.q != 0) {
         throw new JebRuntimeException("Expected a byte value, got " + this.q);
      } else {
         return this.RF;
      }
   }

   @Override
   public short getShort() {
      if (this.q != 2) {
         throw new JebRuntimeException("Expected a short value, got " + this.q);
      } else {
         return this.xK;
      }
   }

   @Override
   public char getChar() {
      if (this.q != 3) {
         throw new JebRuntimeException("Expected a char value, got " + this.q);
      } else {
         return this.Dw;
      }
   }

   @Override
   public int getInt() {
      if (this.q != 4) {
         throw new JebRuntimeException("Expected an int value, got " + this.q);
      } else {
         return this.Uv;
      }
   }

   @Override
   public long getLong() {
      if (this.q != 6) {
         throw new JebRuntimeException("Expected a long value, got " + this.q);
      } else {
         return this.oW;
      }
   }

   @Override
   public float getFloat() {
      if (this.q != 16) {
         throw new JebRuntimeException("Expected a float value, got " + this.q);
      } else {
         return this.gO;
      }
   }

   @Override
   public double getDouble() {
      if (this.q != 17) {
         throw new JebRuntimeException("Expected a double value, got " + this.q);
      } else {
         return this.nf;
      }
   }

   @Override
   public int getMethodTypeIndex() {
      if (this.q != 21) {
         throw new JebRuntimeException("Expected a method_type value, got " + this.q);
      } else {
         return this.zz;
      }
   }

   @Override
   public int getMethodHandleIndex() {
      if (this.q != 22) {
         throw new JebRuntimeException("Expected a method handler, got " + this.q);
      } else {
         return this.zz;
      }
   }

   @Override
   public int getStringIndex() {
      if (this.q != 23) {
         throw new JebRuntimeException("Expected a string value, got " + this.q);
      } else {
         return this.gP;
      }
   }

   @Override
   public int getTypeIndex() {
      if (this.q != 24) {
         throw new JebRuntimeException("Expected a type index value, got " + this.q);
      } else {
         return this.za;
      }
   }

   @Override
   public int getFieldIndex() {
      if (this.q != 25) {
         throw new JebRuntimeException("Expected a field index value, got " + this.q);
      } else {
         return this.lm;
      }
   }

   @Override
   public int getMethodIndex() {
      if (this.q != 26) {
         throw new JebRuntimeException("Expected a method index value, got " + this.q);
      } else {
         return this.zz;
      }
   }

   @Override
   public int getEnumIndex() {
      if (this.q != 27) {
         throw new JebRuntimeException("Expected an enum index value, got " + this.q);
      } else {
         return this.JY;
      }
   }

   public bia[] q() {
      if (this.q != 28) {
         throw new JebRuntimeException("Expected an array value, got " + this.q);
      } else {
         return this.HF;
      }
   }

   @Override
   public List getArray() {
      if (this.q != 28) {
         throw new JebRuntimeException("Expected an array value, got " + this.q);
      } else {
         return ArrayUtil.asView(this.HF);
      }
   }

   @Override
   public IDexAnnotation getAnnotation() {
      if (this.q != 29) {
         throw new JebRuntimeException("Expected an annotation value, got " + this.q);
      } else {
         return this.LK;
      }
   }

   @Override
   public boolean getBoolean() {
      if (this.q != 31) {
         throw new JebRuntimeException("Expected a boolean value, got " + this.q);
      } else {
         return this.io;
      }
   }

   public static bia q(bjw var0, byte[] var1, int var2, int[] var3) {
      int var4 = var1[var2] & 255;
      int var5 = var4 & 31;
      int var6 = var4 >> 5;
      int[] var7 = new int[1];
      bia var8 = new bia();
      var8.q = var5;
      switch (var5) {
         case 0:
            q(var6, 0, 0);
            var8.RF = var1[var2 + 1];
            var6++;
            break;
         case 1:
         case 5:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 18:
         case 19:
         case 20:
         default:
            throw new DexParsingException("Unknown encoded value type: " + var5);
         case 2:
            q(var6, 0, 1);
            ByteBuffer var17 = q(var1, var2 + 1, var6 + 1, 2, true);
            var8.xK = var17.getShort(0);
            var6++;
            break;
         case 3:
            q(var6, 0, 1);
            ByteBuffer var16 = q(var1, var2 + 1, var6 + 1, 2, false);
            var8.Dw = var16.getChar(0);
            var6++;
            break;
         case 4:
            q(var6, 0, 3);
            ByteBuffer var15 = q(var1, var2 + 1, var6 + 1, 4, true);
            var8.Uv = var15.getInt(0);
            var6++;
            break;
         case 6:
            q(var6, 0, 7);
            ByteBuffer var14 = q(var1, var2 + 1, var6 + 1, 8, true);
            var8.oW = var14.getLong(0);
            var6++;
            break;
         case 16:
            q(var6, 0, 3);
            ByteBuffer var13 = q(var1, var2 + 1, var6 + 1, 4);
            var8.gO = var13.getFloat(0);
            var6++;
            break;
         case 17:
            q(var6, 0, 7);
            ByteBuffer var12 = q(var1, var2 + 1, var6 + 1, 8);
            var8.nf = var12.getDouble(0);
            var6++;
            break;
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
            q(var6, 0, 3);
            ByteBuffer var9 = q(var1, var2 + 1, var6 + 1, 4, false);
            int var10 = var9.getInt(0);
            if (var10 < 0) {
               throw new DexParsingException("Negative index: " + var10);
            }

            if (var5 == 23) {
               var8.gP = var0.q(var10);
            } else if (var5 == 24) {
               var8.za = var0.RF(var10);
            } else if (var5 == 25) {
               var8.lm = var0.Dw(var10);
            } else if (var5 == 26) {
               var8.zz = var0.Uv(var10);
            } else if (var5 == 21) {
               var8.zz = var0.xK(var10);
            } else if (var5 == 22) {
               var8.zz = var0.gO(var10);
            } else {
               var8.JY = var0.Dw(var10);
            }

            var6++;
            break;
         case 28:
            q(var6, 0, 0);
            var8.HF = RF(var0, var1, var2 + 1, var7);
            var6 = var7[0];
            break;
         case 29:
            q(var6, 0, 0);
            var8.LK = bhs.q(var0, var1, var2 + 1, var7);
            var6 = var7[0];
            break;
         case 30:
            q(var6, 0, 0);
            var6 = 0;
            break;
         case 31:
            q(var6, 0, 1);
            var8.io = var6 == 1;
            var6 = 0;
      }

      var3[0] = 1 + var6;
      return var8;
   }

   public static bia[] RF(bjw var0, byte[] var1, int var2, int[] var3) throws DexParsingException {
      int[] var4 = new int[1];
      int var5 = DexUtil.bytearrayULEB128ToInt(var1, var2, var4);
      int var6 = var2 + var4[0];
      bia[] var7 = new bia[var5];

      for (int var8 = 0; var8 < var5; var8++) {
         var7[var8] = q(var0, var1, var6, var4);
         var6 += var4[0];
      }

      var3[0] = var6 - var2;
      return var7;
   }

   private static void q(int var0, int var1, int var2) throws DexParsingException {
      if (var0 < var1 || var0 > var2) {
         throw new DexParsingException("Invalid length for encoded field");
      }
   }

   private static ByteBuffer q(byte[] var0, int var1, int var2, int var3, boolean var4) {
      ByteBuffer var5 = ByteBuffer.allocate(var3);
      var5.order(ByteOrder.LITTLE_ENDIAN);
      byte[] var6 = var5.array();

      for (int var7 = 0; var7 < var2; var7++) {
         var6[var7] = var0[var1 + var7];
      }

      if (var4 && (var6[var2 - 1] & 128) != 0) {
         for (int var8 = var2; var8 < var3; var8++) {
            var6[var8] = -1;
         }
      }

      return var5;
   }

   private static ByteBuffer q(byte[] var0, int var1, int var2, int var3) {
      ByteBuffer var4 = ByteBuffer.allocate(var3);
      var4.order(ByteOrder.LITTLE_ENDIAN);
      byte[] var5 = var4.array();

      for (int var6 = 0; var6 < var2; var6++) {
         var5[var3 - var2 + var6] = var0[var1 + var6];
      }

      return var4;
   }
}
