package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class K {
   private static final ILogger kS = GlobalLog.getLogger(K.class);
   @SerId(1)
   long pC;
   @SerId(2)
   long A;

   public static K pC(ByteBuffer var0, boolean var1) {
      int var2 = !var1 ? 8 : 16;
      if (var0.remaining() < var2) {
         kS.warn(S.L("Not enough bytes to read the last dynamic entry"));
         ByteBufferUtil.skipToEnd(var0);
         return null;
      } else {
         K var3 = new K();
         if (!var1) {
            var3.pC = var0.getInt();
            var3.A = var0.getInt() & 4294967295L;
         } else {
            var3.pC = var0.getLong();
            var3.A = var0.getLong();
         }

         return var3;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("tag=%s,val=%X", ELF.getDT((int)this.pC), this.A);
   }
}
