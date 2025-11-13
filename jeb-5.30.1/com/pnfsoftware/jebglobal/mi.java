package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class mi {
   private static final ILogger q = GlobalLog.getLogger(vf.class);
   private rq RF;
   private CW[] xK = null;

   public mi(CW... var1) {
      this(rq.q, var1);
   }

   public mi(rq var1, CW... var2) {
      this.RF = var1;
      this.xK = var2;
   }

   public Long q(long var1, int var3, IMachineContext var4) {
      long[] var5 = new long[this.xK.length];

      for (int var6 = 0; var6 < this.xK.length; var6++) {
         Long var7 = this.xK[var6].q(var1, var3, var4);
         if (var7 == null) {
            return null;
         }

         var5[var6] = var3 == 64 ? var7 : var7.intValue();
      }

      if (var5.length == 1) {
         if (this.RF != null && this.RF != rq.io) {
            switch (this.RF) {
               case qa:
                  return ~var5[0];
               case Hk:
                  return ZD.q(var5[0], q(this.RF), 0L, var4, var3 != 64);
               default:
                  return var5[0];
            }
         } else {
            return var5[0];
         }
      } else if (var5.length <= 2) {
         switch (this.RF) {
            case Hk:
            case zz:
            case JY:
            case HF:
            case LK:
               return ZD.q(var5[0], q(this.RF), var5[1], var4, var3 != 64);
            case q:
            case RF:
            case xK:
            case Dw:
            case Uv:
            case oW:
            case za:
            case lm:
            default:
               return q(this.RF, var3, var5[0], var5[1]);
            case gO:
               if (!rT.q(var4)) {
                  return null;
               }

               boolean var16 = rT.RF(var4);
               return var5[0] - var5[1] - (var16 ? 0 : 1);
            case nf:
               if (!rT.q(var4)) {
                  return null;
               }

               boolean var15 = rT.RF(var4);
               return var5[1] - var5[0] - (var15 ? 0 : 1);
            case gP:
               if (!rT.q(var4)) {
                  return null;
               } else {
                  boolean var14 = rT.RF(var4);
                  return var5[0] + var5[1] + (var14 ? 1 : 0);
               }
         }
      } else if (this.RF != rq.q) {
         q.catchingSilent(
            new RuntimeException(Strings.ff("Unable to calculate result with more than 2 operands for operation %s at %xh", Strings.safe(this.RF), var1))
         );
         return null;
      } else {
         long var13 = 0L;

         for (long var11 : var5) {
            var13 += var11;
         }

         return var13;
      }
   }

   public static long q(rq var0, int var1, long var2, long var4) {
      switch (var0) {
         case q:
            return var2 + var4;
         case RF:
            return var2 - var4;
         case xK:
            return var2 & var4;
         case Dw:
            return var2 & ~var4;
         case Uv:
            return var2 ^ var4;
         case oW:
            return var4 - var2;
         case za:
            return var2 | var4;
         case lm:
            return var2 | ~var4;
         case gO:
         case nf:
         case gP:
         default:
            return -1L;
         case zz:
         case JY:
         case HF:
         case LK:
            return ZD.q(var2, q(var0), var4, null, var1 != 64);
      }
   }

   private static DH.eo q(rq var0) {
      return DH.eo.valueOf(var0.toString());
   }
}
