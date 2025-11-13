package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

class Lq extends Ll {
   private static final ILogger q = GlobalLog.getLogger(Lq.class);
   private oH RF;
   private int xK;
   private BytePipe Dw = new BytePipe();

   public Lq(oH var1) {
      super(var1, Lq.class);
      this.RF = var1;
   }

   @Override
   public void q(byte[] var1) throws IOException {
      this.Dw.append(var1);
      if (this.RF.RF) {
         q.trace("< Received %d bytes > ( %s )", var1.length, Formatter.escapeBytes(var1));
      }

      while (this.Dw.available() >= 1) {
         if (this.xK == 0) {
            byte var2 = this.Dw.peek();
            if (var2 == 43) {
               this.xK = 1;
               this.Dw.skip(1);
            } else if (var2 == 45) {
               this.xK = -1;
               this.Dw.skip(1);
            }
         }

         if (this.xK == -1) {
            throw new IOException("Negative ack");
         }

         int var9 = this.Dw.available();
         if (var9 < 4) {
            return;
         }

         byte[] var3 = new byte[var9];
         this.Dw.peek(var3);
         if (var3[0] != 36) {
            throw new IOException(Strings.ff("Expecting \"$\", got \"%s\"", Formatter.escapeByte(var3[0])));
         }

         int var4 = -1;

         int var5;
         for (var5 = 1; var5 < var9 - 2; var5++) {
            if (var3[var5] == 35) {
               String var6 = Strings.ff("%c%c", (char)var3[var5 + 1], (char)var3[var5 + 2]);
               var4 = Integer.parseInt(var6, 16);
               break;
            }
         }

         if (var4 < 0) {
            return;
         }

         this.Dw.skip(var5 + 3);
         int var10 = cq.q(var3, 1, var5);
         if (var10 != var4) {
            throw new IOException(Strings.ff("Invalid packet checksum: expected=%Xh, actual=%Xh", var4, var10));
         }

         byte[] var7 = cq.q(var3, 1, var5, var4);
         cq var8 = new cq(var7);
         var8.q(this.xK == 1);
         this.xK = 0;
         this.RF.q(var8);
      }
   }
}
