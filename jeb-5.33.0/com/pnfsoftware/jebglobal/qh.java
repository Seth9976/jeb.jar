package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

class qh {
   private final Charset pC;
   private ByteBuffer A;
   private byte[] kS;
   private final LittleEndianDataInputStream wS;

   qh(LittleEndianDataInputStream var1, Charset var2) {
      this.wS = var1;
      this.pC = var2;
   }

   void pC(int var1) throws IOException {
      for (int var2 = 0; var2 < var1; var2++) {
         this.kS();
         this.A.position(this.A.getShort() + this.A.position());
      }
   }

   void pC() throws IOException {
      Preconditions.checkArgument(!this.A.hasRemaining());
      Preconditions.checkArgument(this.wS.readInt() == 0);
   }

   String A() throws IOException {
      this.kS();
      short var1 = this.A.getShort();
      String var2 = new String(this.A.array(), this.A.position(), var1, this.pC);
      this.A.position(var1 + this.A.position());
      return var2;
   }

   final void kS() throws IOException {
      if (this.A == null || !this.A.hasRemaining()) {
         int var1 = ((LittleEndianDataInputStream)Preconditions.checkNotNull(this.wS)).readInt();
         boolean var2 = var1 > 0;
         Preconditions.checkArgument(var2);
         if (this.kS == null || this.kS.length < var1) {
            this.kS = new byte[var1 + 1024];
         }

         this.wS.readFully(this.kS, 0, var1);
         this.A = ByteBuffer.wrap(this.kS, 0, var1).order(ByteOrder.LITTLE_ENDIAN);
      }
   }
}
