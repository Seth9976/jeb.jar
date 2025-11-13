package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;

class IZ extends AR {
   private static final ILogger pC = GlobalLog.getLogger(IZ.class);
   private aI A;
   private int kS;
   private BytePipe wS = new BytePipe();

   public IZ(aI var1) {
      super(var1, IZ.class);
      this.A = var1;
   }

   @Override
   public void pC(byte[] var1) throws IOException {
      this.wS.append(var1);
      if (this.A.A) {
         pC.trace("< Received %d bytes > ( %s )", var1.length, Formatter.escapeBytes(var1));
      }

      while (this.wS.available() >= 1) {
         if (this.kS == 0) {
            byte var2 = this.wS.peek();
            if (var2 == 43) {
               this.kS = 1;
               this.wS.skip(1);
            } else if (var2 == 45) {
               this.kS = -1;
               this.wS.skip(1);
            }
         }

         if (this.kS == -1) {
            throw new IOException("Negative ack");
         }

         int var9 = this.wS.available();
         if (var9 < 4) {
            return;
         }

         byte[] var3 = new byte[var9];
         this.wS.peek(var3);
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

         this.wS.skip(var5 + 3);
         int var10 = Fi.pC(var3, 1, var5);
         if (var10 != var4) {
            throw new IOException(Strings.ff("Invalid packet checksum: expected=%Xh, actual=%Xh", var4, var10));
         }

         byte[] var7 = Fi.pC(var3, 1, var5, var4);
         Fi var8 = new Fi(var7);
         var8.pC(this.kS == 1);
         this.kS = 0;
         this.A.pC(var8);
      }
   }
}
