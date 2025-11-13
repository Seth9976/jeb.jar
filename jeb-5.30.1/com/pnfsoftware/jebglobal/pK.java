package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class pK {
   ByteBuf q;
   List RF = new ArrayList();

   public pK() {
      this.q = Unpooled.buffer(65536, 104857600);
      this.q.order(ByteOrder.LITTLE_ENDIAN);
   }

   void q(int var1, int var2) {
      this.RF.add(this.q.writerIndex());
      this.q.writeShortLE(var1);
      this.q.writeShortLE(var2);
      this.q.writeIntLE(-889275714);
   }

   void q() {
      int var1 = (Integer)this.RF.remove(this.RF.size() - 1);
      int var2 = this.q.writerIndex() - var1;
      this.q.markWriterIndex();
      this.q.writerIndex(var1 + 4);
      this.q.writeIntLE(var2);
      this.q.resetWriterIndex();
   }

   public byte[] RF() {
      if (!this.RF.isEmpty()) {
         throw new IllegalStateException(Strings.ff("%d offset(s) remaining in queue: %s", this.RF.size(), this.RF));
      } else {
         int var1 = this.q.writerIndex();
         byte[] var2 = new byte[var1];
         this.q.markReaderIndex();
         this.q.readerIndex(0);
         this.q.readBytes(var2);
         this.q.resetReaderIndex();
         return var2;
      }
   }
}
