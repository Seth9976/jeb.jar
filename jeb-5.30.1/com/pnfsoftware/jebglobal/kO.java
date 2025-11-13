package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class kO {
   private static final ILogger q = GlobalLog.getLogger(kO.class);
   private boolean RF;
   private uL xK;
   private Exception Dw;
   private List Uv;

   public kO(InputStream var1) {
      this.xK = new oe(var1);
   }

   public boolean q() {
      return this.Dw == null;
   }

   public List RF() {
      if (this.Uv == null) {
         throw new IllegalStateException();
      } else {
         return this.Uv;
      }
   }

   public boolean xK() {
      if (this.Uv == null) {
         throw new IllegalStateException();
      } else {
         return !this.Uv.isEmpty();
      }
   }

   public Kd Dw() throws IOException {
      if (this.RF) {
         throw new IllegalStateException();
      } else {
         this.RF = true;
         Kd var1 = new Kd(this.xK);
         int var2 = this.xK.RF();
         if (var2 > 0) {
            this.xK.q(S.L("Trailing (unread) bytes: %d"), var2);
         }

         this.Uv = this.xK.xK();
         return var1;
      }
   }

   public IO Uv() throws IOException {
      return this.q(null, null, false, false);
   }

   public IO q(com.pnfsoftware.jeb.corei.parsers.apk.decoder.kY var1, String var2, boolean var3, boolean var4) throws IOException {
      if (this.RF) {
         throw new IllegalStateException();
      } else {
         this.RF = true;
         IO var5 = new IO(this.xK, var1, var2, var3);

         try {
            var5.q();
         } catch (RuntimeException var7) {
            if (!var4) {
               throw var7;
            }

            q.catchingSilent(var7);
            this.Dw = var7;
            this.xK.q(S.L("Parsing did not complete successfully: %s"), var7.getMessage());
         }

         if (this.Dw == null) {
            int var6 = this.xK.RF();
            if (var6 > 0) {
               this.xK.q(S.L("Trailing (unread) bytes: %d"), var6);
            }
         }

         this.Uv = this.xK.xK();
         return var5;
      }
   }
}
