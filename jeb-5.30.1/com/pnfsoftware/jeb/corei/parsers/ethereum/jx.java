package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class jx {
   private static final ILogger xK = GlobalLog.getLogger(jx.class);
   eM q;
   ct RF;
   private jx.CU Dw;

   public jx(eM var1, ct var2) {
      this.q = var1;
      this.RF = var2;
   }

   boolean q(ct.eo var1) {
      Object[] var10000 = new Object[]{var1};
      this.Dw = new jx.CU(10);
      List var2 = this.RF.q(var1);
      if (var2 == null) {
         var10000 = new Object[0];
         return false;
      } else {
         Assert.a(var2.size() >= 1 && (Integer)var2.get(0) == var1.q);
         var10000 = new Object[]{this.RF(var2)};
         jx.eo var3 = this.q(var2);
         if (var3.RF()) {
            var10000 = new Object[0];
            return false;
         } else if (var3.xK == null) {
            var10000 = new Object[0];
            return false;
         } else {
            int var4 = var3.xK;
            int var5 = this.Dw.RF() + var4;
            var10000 = new Object[]{var4, var5};
            var1.xK = var4;
            var1.Dw = var5;
            return true;
         }
      }
   }

   jx.eo q(List var1) {
      com.pnfsoftware.jeb.corei.parsers.ethereum.eo var2 = this.q.Uv((Integer)var1.get(0));
      int var3 = 0;

      while (true) {
         this.q(var3, "Forward-slicing block: %s", var2);
         ArrayList var5 = new ArrayList(this.q.q(var2));
         int var6 = 0;
         int var7 = var5.size() - 1;

         while (true) {
            vX var8 = (vX)var5.get(var6);
            int var9 = var8.q();
            int var10 = 0;
            int var11 = 0;
            if (var8.RF() != null) {
               this.Dw.q(var8.RF());
            } else if (var8.xK() != null) {
               this.Dw.RF(var8.xK());
            } else if (var8.lm != null) {
               this.Dw.q(var8.lm);
            } else {
               var10 = var8.nf.Dw;
               var11 = var8.nf.Uv;
            }

            if (var6 == var7) {
               if (var2.za) {
                  ct.eo var12 = this.RF.q(var2.Dw);
                  if (var12 == null) {
                     this.q(var3, "ERROR. Passing over a call-to-sub, routine not found");
                     return null;
                  }

                  if (var12.xK == null || var12.Dw == null) {
                     this.q(var3, "ERROR. Needs routine in/out-info in order to calculate stack deltas");
                     return null;
                  }

                  int var13 = var12.xK + 1;
                  int var14 = var12.Dw;
                  if (var3 + 1 != var1.size()) {
                     var10 += var13;
                     var11 += var14;
                  } else {
                     this.Dw.q(var13, var14);
                  }
               }

               if (var3 + 1 == var1.size()) {
                  if (var9 == 86) {
                     return this.Dw.q();
                  }

                  return null;
               }

               int var4 = (Integer)var1.get(var3 + 1);
               this.Dw.q(var10, var11);
               var2 = this.q.Uv(var4);
               if (var2 == null) {
                  return null;
               }

               var3++;
               break;
            }

            this.Dw.q(var10, var11);
            var6++;
         }
      }
   }

   private void q(int var1, String var2, Object... var3) {
      Object[] var10000 = new Object[]{Strings.spaces(2 * var1), Strings.ff(var2, var3)};
   }

   private String RF(List var1) {
      StringBuilder var2 = new StringBuilder();
      int var3 = 0;

      for (int var5 : var1) {
         if (var3 > 0) {
            var2.append(" -> ");
         }

         Strings.ff(var2, "0x%X", var5);
         var3++;
      }

      return var2.toString();
   }

   private static class CU {
      int q;
      List RF = new ArrayList();

      CU(int var1) {
         if (var1 <= 0) {
            throw new IllegalArgumentException();
         } else {
            this.q = var1;

            for (int var2 = var1 - 1; var2 >= 0; var2--) {
               this.RF.add(new jx.eo(var2));
            }
         }
      }

      jx.eo q(BigInteger var1) {
         jx.eo var2 = new jx.eo(var1);
         this.RF.add(var2);
         return var2;
      }

      jx.eo q() {
         return (jx.eo)this.RF.get(this.RF.size() - 1);
      }

      void q(int var1) {
         jx.eo var2 = (jx.eo)this.RF.get(this.RF.size() - 1);
         jx.eo var3 = (jx.eo)this.RF.get(this.RF.size() - 1 - var1);
         this.RF.set(this.RF.size() - 1, var3);
         this.RF.set(this.RF.size() - 1 - var1, var2);
      }

      void RF(int var1) {
         jx.eo var2 = (jx.eo)this.RF.get(this.RF.size() - var1);
         this.RF.add(var2.q());
      }

      void q(int var1, int var2) {
         if (var1 > 0) {
            while (var1-- != 0) {
               this.RF.remove(this.RF.size() - 1);
            }
         }

         if (var2 > 0) {
            while (var2-- != 0) {
               this.RF.add(jx.eo.q);
            }
         }
      }

      int RF() {
         return this.RF.size() - this.q;
      }

      @Override
      public String toString() {
         return Strings.ff("%d: %s", this.RF(), this.RF);
      }
   }

   private static class eo {
      static jx.eo q = new jx.eo();
      private BigInteger RF;
      private Integer xK;

      private eo() {
      }

      eo(BigInteger var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.RF = var1;
         }
      }

      eo(int var1) {
         this.xK = var1;
      }

      public jx.eo q() {
         if (this == q) {
            return q;
         } else {
            jx.eo var1 = new jx.eo();
            var1.RF = this.RF;
            var1.xK = this.xK;
            return var1;
         }
      }

      boolean RF() {
         return this.RF == null && this.xK == null;
      }

      @Override
      public String toString() {
         if (this.RF != null) {
            return "0x" + this.RF.toString(16);
         } else {
            return this.xK != null ? "#" + this.xK : "?";
         }
      }
   }
}
