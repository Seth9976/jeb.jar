package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import com.google.common.io.LittleEndianDataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

class OK {
   private final Charset q;
   private ByteBuffer RF;
   private byte[] xK;
   private final LittleEndianDataInputStream Dw;

   OK(LittleEndianDataInputStream var1, Charset var2) {
      this.Dw = var1;
      this.q = var2;
   }

   void q(int var1) throws IOException {
      for (int var2 = 0; var2 < var1; var2++) {
         this.xK();
         this.RF.position(this.RF.getShort() + this.RF.position());
      }
   }

   void q() throws IOException {
      Preconditions.checkArgument(!this.RF.hasRemaining());
      Preconditions.checkArgument(this.Dw.readInt() == 0);
   }

   String RF() throws IOException {
      this.xK();
      short var1 = this.RF.getShort();
      String var2 = new String(this.RF.array(), this.RF.position(), var1, this.q);
      this.RF.position(var1 + this.RF.position());
      return var2;
   }

   final void xK() throws IOException {
      if (this.RF == null || !this.RF.hasRemaining()) {
         int var1 = ((LittleEndianDataInputStream)Preconditions.checkNotNull(this.Dw)).readInt();
         boolean var2 = var1 > 0;
         Preconditions.checkArgument(var2);
         if (this.xK == null || this.xK.length < var1) {
            this.xK = new byte[var1 + 1024];
         }

         this.Dw.readFully(this.xK, 0, var1);
         this.RF = ByteBuffer.wrap(this.xK, 0, var1).order(ByteOrder.LITTLE_ENDIAN);
      }
   }
}
