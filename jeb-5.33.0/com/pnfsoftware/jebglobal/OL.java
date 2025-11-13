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

public class OL {
   private static final ILogger pC = GlobalLog.getLogger(OL.class);
   private aI A;
   private Map kS = new HashMap();

   OL(aI var1) {
      this.A = var1;
   }

   public synchronized List pC() {
      return new ArrayList(this.kS.values());
   }

   public synchronized boolean pC(long var1) {
      return this.kS.containsKey(var1);
   }

   public synchronized boolean pC(long var1, int var3) throws IOException {
      if (this.pC(var1)) {
         pC.debug("%Xh: Breakpoint already set", var1);
         return false;
      } else {
         pC.debug("%Xh: Attempting to set breakpoint (%d)", var1, var3);
         if (this.A.gp.kS(var1, var3)) {
            pC.debug("%Xh: Set custom breakpoint", var1);
            byte[] var7 = this.A.gp.pC(var1, var3);
            xK var8 = new xK(var1, var7);
            this.kS.put(var1, var8);
            return true;
         } else {
            String var4 = Strings.ff("Z0,%x,%d", var1, var3);
            String var5 = Strings.decodeASCII(this.A.wS(var4));
            if (zI.A(var5)) {
               pC.debug("%Xh: Set software (Z0) breakpoint", var1);
               xK var6 = new xK(var1, 0);
               this.kS.put(var1, var6);
               return true;
            } else {
               pC.debug("%Xh: Cannot set breakpoint", var1);
               return false;
            }
         }
      }
   }

   public synchronized boolean A(long var1) throws IOException {
      xK var3 = (xK)this.kS.get(var1);
      if (var3 == null) {
         return false;
      } else if (!this.pC(var3, false)) {
         return false;
      } else if (var3.A != -1) {
         throw new IOException("Unsupported breakpoint type: " + var3.A);
      } else {
         this.kS.remove(var1);
         return true;
      }
   }

   public synchronized boolean A() throws IOException {
      boolean var1 = true;

      for (long var3 : new ArrayList(this.kS.keySet())) {
         var1 = var1 && this.A(var3);
      }

      return var1;
   }

   synchronized boolean pC(xK var1, boolean var2) throws IOException {
      if (var1.pC == var2) {
         return true;
      } else if (var1.A() != -1) {
         var1.pC = var2;
         return true;
      } else {
         long var3 = var1.pC();
         byte[] var5 = var1.kS();
         byte[] var6 = new byte[var5.length];
         if (this.A.ys.pC(var3, var6) != var6.length) {
            return false;
         } else if (Arrays.equals(var5, var6)) {
            var1.pC = var2;
            return true;
         } else if (this.A.ys.A(var3, var5) != var5.length) {
            return false;
         } else {
            var1.pC(var6);
            var1.pC = var2;
            return true;
         }
      }
   }

   synchronized boolean pC(boolean var1) throws IOException {
      boolean var2 = true;

      for (xK var4 : this.kS.values()) {
         if (!this.pC(var4, var1)) {
            pC.error("Could not %sactivate breakpoint %s", var1 ? "" : "de", var4);
            var2 = false;
         }
      }

      return var2;
   }
}
