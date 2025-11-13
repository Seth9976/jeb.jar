package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UI {
   private static final ILogger q = GlobalLog.getLogger(UI.class);
   private oH RF;
   private Map xK = new HashMap();

   UI(oH var1) {
      this.RF = var1;
   }

   public synchronized List q() {
      return new ArrayList(this.xK.values());
   }

   public synchronized fQ q(long var1) {
      return (fQ)this.xK.get(var1);
   }

   public synchronized boolean RF(long var1) {
      return this.xK.containsKey(var1);
   }

   public synchronized boolean q(long var1, int var3) throws IOException {
      if (this.RF(var1)) {
         q.debug("%Xh: Breakpoint already set", var1);
         return false;
      } else {
         q.debug("%Xh: Attempting to set breakpoint (%d)", var1, var3);
         if (this.RF.za.xK(var1, var3)) {
            q.debug("%Xh: Set custom breakpoint", var1);
            byte[] var7 = this.RF.za.q(var1, var3);
            fQ var8 = new fQ(var1, var7);
            this.xK.put(var1, var8);
            return true;
         } else {
            String var4 = Strings.ff("Z0,%x,%d", var1, var3);
            String var5 = Strings.decodeASCII(this.RF.Dw(var4));
            if (MO.RF(var5)) {
               q.debug("%Xh: Set software (Z0) breakpoint", var1);
               fQ var6 = new fQ(var1, 0);
               this.xK.put(var1, var6);
               return true;
            } else {
               q.debug("%Xh: Cannot set breakpoint", var1);
               return false;
            }
         }
      }
   }

   public synchronized boolean xK(long var1) throws IOException {
      fQ var3 = (fQ)this.xK.get(var1);
      if (var3 == null) {
         return false;
      } else if (!this.q(var3, false)) {
         return false;
      } else if (var3.Uv != -1) {
         throw new IOException("Unsupported breakpoint type: " + var3.Uv);
      } else {
         this.xK.remove(var1);
         return true;
      }
   }

   public synchronized boolean RF() throws IOException {
      boolean var1 = true;

      for (long var3 : new ArrayList(this.xK.keySet())) {
         var1 = var1 && this.xK(var3);
      }

      return var1;
   }

   synchronized boolean q(fQ var1, boolean var2) throws IOException {
      if (var1.Dw == var2) {
         return true;
      } else if (var1.RF() != -1) {
         var1.Dw = var2;
         return true;
      } else {
         long var3 = var1.q();
         byte[] var5 = var1.xK();
         byte[] var6 = new byte[var5.length];
         if (this.RF.nf.q(var3, var6) != var6.length) {
            return false;
         } else if (Arrays.equals(var5, var6)) {
            var1.Dw = var2;
            return true;
         } else if (this.RF.nf.RF(var3, var5) != var5.length) {
            return false;
         } else {
            var1.q(var6);
            var1.Dw = var2;
            return true;
         }
      }
   }

   synchronized boolean q(boolean var1) throws IOException {
      boolean var2 = true;

      for (fQ var4 : this.xK.values()) {
         if (!this.q(var4, var1)) {
            q.error("Could not %sactivate breakpoint %s", var1 ? "" : "de", var4);
            var2 = false;
         }
      }

      return var2;
   }
}
