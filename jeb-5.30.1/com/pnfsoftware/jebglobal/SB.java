package com.pnfsoftware.jebglobal;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SB implements vv {
   private final List q = new ArrayList();

   public SB(byte[] var1) {
      ByteBuffer var2 = ByteBuffer.wrap(var1).order(ByteOrder.LITTLE_ENDIAN);

      while (var2.remaining() > 0) {
         this.q.add(oV.Dw(var2));
      }
   }

   public static SB q(InputStream var0) throws IOException {
      byte[] var1 = ByteStreams.toByteArray(var0);
      return new SB(var1);
   }

   public List q() {
      return Collections.unmodifiableList(this.q);
   }

   @Override
   public byte[] oW() throws IOException {
      return this.q(false);
   }

   @Override
   public byte[] q(boolean var1) throws IOException {
      ByteArrayDataOutput var2 = ByteStreams.newDataOutput();

      for (oV var4 : this.q) {
         var2.write(var4.q(var1));
      }

      return var2.toByteArray();
   }
}
