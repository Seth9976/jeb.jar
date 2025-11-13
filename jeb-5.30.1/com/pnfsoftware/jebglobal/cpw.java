package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

public class cpw {
   IInput q;
   int RF;
   int xK;
   List Dw = new ArrayList();

   public cpw(IInput var1, int var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
   }

   public boolean q() {
      return !this.Dw.isEmpty();
   }

   public List RF() {
      return this.Dw;
   }

   public void xK() throws Exception {
      try (SeekableByteChannel var1 = this.q.getChannel()) {
         var1.position(this.RF);
         ByteBuffer var2 = ByteBuffer.allocate(this.xK);
         int var3 = var1.read(var2);
         if (var3 != this.xK) {
            var2.limit(var3);
            this.xK = var3;
         }

         var2.order(ByteOrder.LITTLE_ENDIAN);
         int var4 = 0;

         while (var4 < this.xK) {
            var2.position(var4);
            int var5 = var2.getInt();
            if (var4 + (var5 + 7) / 8 * 8 > this.xK) {
               throw new IOException(Strings.f("Not enough bytes to extract certificate starting at %xh which size is %xh", this.RF + var4, var5));
            }

            int var6 = var2.getShort() & '\uffff';
            int var7 = var2.getShort() & '\uffff';
            byte[] var8 = new byte[var5 - 8];
            var2.get(var8);
            cpv var9 = new cpv(var6, var7, var8);
            this.Dw.add(var9);
            var4 += (var5 + 7) / 8 * 8;
         }
      }
   }

   @Override
   public String toString() {
      return super.toString();
   }
}
