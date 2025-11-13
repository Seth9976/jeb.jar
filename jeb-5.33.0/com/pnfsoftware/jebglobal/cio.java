package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class cio {
   IInput pC;
   int A;
   int kS;
   List wS = new ArrayList();

   public cio(IInput var1, int var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   public boolean pC() {
      return !this.wS.isEmpty();
   }

   public List A() {
      return this.wS;
   }

   public void kS() throws Exception {
      try (SeekableByteChannel var1 = this.pC.getChannel()) {
         var1.position(this.A);
         ByteBuffer var2 = ByteBuffer.allocate(this.kS);
         int var3 = var1.read(var2);
         if (var3 != this.kS) {
            var2.limit(var3);
            this.kS = var3;
         }

         var2.order(ByteOrder.LITTLE_ENDIAN);
         int var4 = 0;

         while (var4 < this.kS) {
            var2.position(var4);
            int var5 = var2.getInt();
            if (var4 + (var5 + 7) / 8 * 8 > this.kS) {
               throw new IOException(Strings.f("Not enough bytes to extract certificate starting at %xh which size is %xh", this.A + var4, var5));
            }

            int var6 = var2.getShort() & '\uffff';
            int var7 = var2.getShort() & '\uffff';
            byte[] var8 = new byte[var5 - 8];
            var2.get(var8);
            cin var9 = new cin(var6, var7, var8);
            this.wS.add(var9);
            var4 += (var5 + 7) / 8 * 8;
         }
      }
   }

   @Override
   public String toString() {
      return super.toString();
   }
}
