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
public final class beg implements IDexValue {
   @SerId(1)
   private int pC = -1;
   @SerId(2)
   private byte A;
   @SerId(3)
   private short kS;
   @SerId(4)
   private char wS;
   @SerId(5)
   private int UT;
   @SerId(6)
   private long E;
   @SerId(7)
   private float sY;
   @SerId(8)
   private double ys;
   @SerId(9)
   private int ld;
   @SerId(10)
   private int gp;
   @SerId(11)
   private int oT;
   @SerId(12)
   private int fI;
   @SerId(13)
   private int WR;
   @SerId(14)
   private beg[] NS;
   @SerId(15)
   private bdy vP;
   @SerId(16)
   private boolean xC;

   private beg() {
   }

   @Override
   public int getType() {
      return this.pC;
   }

   @Override
   public boolean isNull() {
      return this.pC == 30;
   }

   @Override
   public byte getByte() {
      if (this.pC != 0) {
         throw new JebRuntimeException("Expected a byte value, got " + this.pC);
      } else {
         return this.A;
      }
   }

   @Override
   public short getShort() {
      if (this.pC != 2) {
         throw new JebRuntimeException("Expected a short value, got " + this.pC);
      } else {
         return this.kS;
      }
   }

   @Override
   public char getChar() {
      if (this.pC != 3) {
         throw new JebRuntimeException("Expected a char value, got " + this.pC);
      } else {
         return this.wS;
      }
   }

   @Override
   public int getInt() {
      if (this.pC != 4) {
         throw new JebRuntimeException("Expected an int value, got " + this.pC);
      } else {
         return this.UT;
      }
   }

   @Override
   public long getLong() {
      if (this.pC != 6) {
         throw new JebRuntimeException("Expected a long value, got " + this.pC);
      } else {
         return this.E;
      }
   }

   @Override
   public float getFloat() {
      if (this.pC != 16) {
         throw new JebRuntimeException("Expected a float value, got " + this.pC);
      } else {
         return this.sY;
      }
   }

   @Override
   public double getDouble() {
      if (this.pC != 17) {
         throw new JebRuntimeException("Expected a double value, got " + this.pC);
      } else {
         return this.ys;
      }
   }

   @Override
   public int getMethodTypeIndex() {
      if (this.pC != 21) {
         throw new JebRuntimeException("Expected a method_type value, got " + this.pC);
      } else {
         return this.fI;
      }
   }

   @Override
   public int getMethodHandleIndex() {
      if (this.pC != 22) {
         throw new JebRuntimeException("Expected a method handler, got " + this.pC);
      } else {
         return this.fI;
      }
   }

   @Override
   public int getStringIndex() {
      if (this.pC != 23) {
         throw new JebRuntimeException("Expected a string value, got " + this.pC);
      } else {
         return this.ld;
      }
   }

   @Override
   public int getTypeIndex() {
      if (this.pC != 24) {
         throw new JebRuntimeException("Expected a type index value, got " + this.pC);
      } else {
         return this.gp;
      }
   }

   @Override
   public int getFieldIndex() {
      if (this.pC != 25) {
         throw new JebRuntimeException("Expected a field index value, got " + this.pC);
      } else {
         return this.oT;
      }
   }

   @Override
   public int getMethodIndex() {
      if (this.pC != 26) {
         throw new JebRuntimeException("Expected a method index value, got " + this.pC);
      } else {
         return this.fI;
      }
   }

   @Override
   public int getEnumIndex() {
      if (this.pC != 27) {
         throw new JebRuntimeException("Expected an enum index value, got " + this.pC);
      } else {
         return this.WR;
      }
   }

   @Override
   public List getArray() {
      if (this.pC != 28) {
         throw new JebRuntimeException("Expected an array value, got " + this.pC);
      } else {
         return ArrayUtil.asView(this.NS);
      }
   }

   @Override
   public IDexAnnotation getAnnotation() {
      if (this.pC != 29) {
         throw new JebRuntimeException("Expected an annotation value, got " + this.pC);
      } else {
         return this.vP;
      }
   }

   @Override
   public boolean getBoolean() {
      if (this.pC != 31) {
         throw new JebRuntimeException("Expected a boolean value, got " + this.pC);
      } else {
         return this.xC;
      }
   }

   public static beg pC(bgb var0, byte[] var1, int var2, int[] var3) {
      int var4 = var1[var2] & 255;
      int var5 = var4 & 31;
      int var6 = var4 >> 5;
      int[] var7 = new int[1];
      beg var8 = new beg();
      var8.pC = var5;
      switch (var5) {
         case 0:
            pC(var6, 0, 0);
            var8.A = var1[var2 + 1];
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
            pC(var6, 0, 1);
            ByteBuffer var17 = pC(var1, var2 + 1, var6 + 1, 2, true);
            var8.kS = var17.getShort(0);
            var6++;
            break;
         case 3:
            pC(var6, 0, 1);
            ByteBuffer var16 = pC(var1, var2 + 1, var6 + 1, 2, false);
            var8.wS = var16.getChar(0);
            var6++;
            break;
         case 4:
            pC(var6, 0, 3);
            ByteBuffer var15 = pC(var1, var2 + 1, var6 + 1, 4, true);
            var8.UT = var15.getInt(0);
            var6++;
            break;
         case 6:
            pC(var6, 0, 7);
            ByteBuffer var14 = pC(var1, var2 + 1, var6 + 1, 8, true);
            var8.E = var14.getLong(0);
            var6++;
            break;
         case 16:
            pC(var6, 0, 3);
            ByteBuffer var13 = pC(var1, var2 + 1, var6 + 1, 4);
            var8.sY = var13.getFloat(0);
            var6++;
            break;
         case 17:
            pC(var6, 0, 7);
            ByteBuffer var12 = pC(var1, var2 + 1, var6 + 1, 8);
            var8.ys = var12.getDouble(0);
            var6++;
            break;
         case 21:
         case 22:
         case 23:
         case 24:
         case 25:
         case 26:
         case 27:
            pC(var6, 0, 3);
            ByteBuffer var9 = pC(var1, var2 + 1, var6 + 1, 4, false);
            int var10 = var9.getInt(0);
            if (var10 < 0) {
               throw new DexParsingException("Negative index: " + var10);
            }

            if (var5 == 23) {
               var8.ld = var0.pC(var10);
            } else if (var5 == 24) {
               var8.gp = var0.A(var10);
            } else if (var5 == 25) {
               var8.oT = var0.wS(var10);
            } else if (var5 == 26) {
               var8.fI = var0.UT(var10);
            } else if (var5 == 21) {
               var8.fI = var0.kS(var10);
            } else if (var5 == 22) {
               var8.fI = var0.sY(var10);
            } else {
               var8.WR = var0.wS(var10);
            }

            var6++;
            break;
         case 28:
            pC(var6, 0, 0);
            var8.NS = A(var0, var1, var2 + 1, var7);
            var6 = var7[0];
            break;
         case 29:
            pC(var6, 0, 0);
            var8.vP = bdy.pC(var0, var1, var2 + 1, var7);
            var6 = var7[0];
            break;
         case 30:
            pC(var6, 0, 0);
            var6 = 0;
            break;
         case 31:
            pC(var6, 0, 1);
            var8.xC = var6 == 1;
            var6 = 0;
      }

      var3[0] = 1 + var6;
      return var8;
   }

   public static beg[] A(bgb var0, byte[] var1, int var2, int[] var3) throws DexParsingException {
      int[] var4 = new int[1];
      int var5 = DexUtil.bytearrayULEB128ToInt(var1, var2, var4);
      int var6 = var2 + var4[0];
      beg[] var7 = new beg[var5];

      for (int var8 = 0; var8 < var5; var8++) {
         var7[var8] = pC(var0, var1, var6, var4);
         var6 += var4[0];
      }

      var3[0] = var6 - var2;
      return var7;
   }

   private static void pC(int var0, int var1, int var2) throws DexParsingException {
      if (var0 < var1 || var0 > var2) {
         throw new DexParsingException("Invalid length for encoded field");
      }
   }

   private static ByteBuffer pC(byte[] var0, int var1, int var2, int var3, boolean var4) {
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

   private static ByteBuffer pC(byte[] var0, int var1, int var2, int var3) {
      ByteBuffer var4 = ByteBuffer.allocate(var3);
      var4.order(ByteOrder.LITTLE_ENDIAN);
      byte[] var5 = var4.array();

      for (int var6 = 0; var6 < var2; var6++) {
         var5[var3 - var2 + var6] = var0[var1 + var6];
      }

      return var4;
   }
}
