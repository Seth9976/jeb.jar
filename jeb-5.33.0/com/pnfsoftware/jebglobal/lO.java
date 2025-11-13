package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class lO {
   private static final ILogger pC = GlobalLog.getLogger(lO.class);
   private boolean A;
   private EX kS;
   private Exception wS;
   private List UT;

   public lO(InputStream var1) {
      this.kS = new MQ(var1);
   }

   public boolean pC() {
      return this.wS == null;
   }

   public List A() {
      if (this.UT == null) {
         throw new IllegalStateException();
      } else {
         return this.UT;
      }
   }

   public WD kS() throws IOException {
      if (this.A) {
         throw new IllegalStateException();
      } else {
         this.A = true;
         WD var1 = new WD(this.kS);
         int var2 = this.kS.A();
         if (var2 > 0) {
            this.kS.pC(S.L("Trailing (unread) bytes: %d"), var2);
         }

         this.UT = this.kS.kS();
         return var1;
      }
   }

   public hP wS() throws IOException {
      return this.pC(null, null, false, false);
   }

   public hP pC(com.pnfsoftware.jeb.corei.parsers.apk.decoder.uX var1, String var2, boolean var3, boolean var4) throws IOException {
      if (this.A) {
         throw new IllegalStateException();
      } else {
         this.A = true;
         hP var5 = new hP(this.kS, var1, var2, var3);

         try {
            var5.pC();
         } catch (RuntimeException var7) {
            if (!var4) {
               throw var7;
            }

            pC.catchingSilent(var7);
            this.wS = var7;
            this.kS.pC(S.L("Parsing did not complete successfully: %s"), var7.getMessage());
         }

         if (this.wS == null) {
            int var6 = this.kS.A();
            if (var6 > 0) {
               this.kS.pC(S.L("Trailing (unread) bytes: %d"), var6);
            }
         }

         this.UT = this.kS.kS();
         return var5;
      }
   }
}
