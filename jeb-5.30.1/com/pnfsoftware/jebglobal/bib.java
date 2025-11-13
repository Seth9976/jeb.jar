package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstructionArrayData;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Ser
public class bib implements IDalvikInstructionArrayData {
   @SerId(1)
   int q;
   @SerId(2)
   byte[][] RF;

   public bib(int var1, int var2, int var3) {
      this.q = var1;
      this.RF = new byte[var2][var3];
   }

   @Override
   public int getOffset() {
      return this.q;
   }

   @Override
   public byte[][] getElements() {
      return this.RF;
   }

   @Override
   public long[] asArrayOfLongs() {
      long[] var1 = new long[this.RF.length];

      for (int var2 = 0; var2 < this.RF.length; var2++) {
         byte[] var5 = this.RF[var2];

         var1[var2] = switch (var5.length) {
            case 1 -> var5[0];
            case 2 -> ByteBuffer.wrap(var5).order(ByteOrder.LITTLE_ENDIAN).getShort();
            default -> 0L;
            case 4 -> ByteBuffer.wrap(var5).order(ByteOrder.LITTLE_ENDIAN).getInt();
            case 8 -> ByteBuffer.wrap(var5).order(ByteOrder.LITTLE_ENDIAN).getLong();
         };
      }

      return var1;
   }
}
