package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteOrder;
import java.util.Arrays;

@Ser
public class BytesBlock {
   @SerId(1)
   private byte[] code;
   @SerId(2)
   private boolean isBigEndian;
   @SerId(3)
   private int groupSize;

   public BytesBlock(byte[] var1) {
      this(var1, Endianness.LITTLE_ENDIAN, 1);
   }

   public BytesBlock(byte[] var1, Endianness var2, int var3) {
      if (var3 == 1 || var3 > 0 && var3 % 2 == 0) {
         if (var1.length % var3 != 0) {
            throw new IllegalArgumentException("Ilegal block size: " + var3);
         } else {
            this.code = var1;
            this.isBigEndian = var2.isBig();
            this.groupSize = var3;
         }
      } else {
         throw new IllegalArgumentException("Ilegal group size: " + var3);
      }
   }

   public byte[] getCode() {
      return this.code;
   }

   public byte[] getCode(ByteOrder var1) {
      if (this.groupSize == 1) {
         return this.code;
      } else {
         boolean var2 = var1 == ByteOrder.BIG_ENDIAN;
         return var2 == this.isBigEndian ? this.code : this.swap();
      }
   }

   public byte[] getBECode() {
      if (this.groupSize == 1) {
         return this.code;
      } else {
         return this.isBigEndian ? this.code : this.swap();
      }
   }

   public byte[] getLECode() {
      if (this.groupSize == 1) {
         return this.code;
      } else {
         return !this.isBigEndian ? this.code : this.swap();
      }
   }

   private byte[] swap() {
      byte[] var1 = new byte[this.code.length];
      ArrayUtil.copyBytes(var1, 0, this.code, 0, this.code.length);
      EndianUtil.swapByGroup(var1, this.groupSize);
      return var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + Arrays.hashCode(this.code);
      var1 = 31 * var1 + this.groupSize;
      return 31 * var1 + (this.isBigEndian ? 1231 : 1237);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         BytesBlock var2 = (BytesBlock)var1;
         if (!Arrays.equals(this.code, var2.code)) {
            return false;
         } else {
            return this.groupSize != var2.groupSize ? false : this.isBigEndian == var2.isBigEndian;
         }
      }
   }
}
