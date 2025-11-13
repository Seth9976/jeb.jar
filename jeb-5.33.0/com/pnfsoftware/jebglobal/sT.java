package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;

public class sT {
   private static final ILogger pC = GlobalLog.getLogger(Rb.class);
   private VZ A;
   private Yg[] kS = null;

   public sT(Yg... var1) {
      this(VZ.pC, var1);
   }

   public sT(VZ var1, Yg... var2) {
      this.A = var1;
      this.kS = var2;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public Long pC(long var1, int var3, IMachineContext var4) {
      long[] var5 = new long[this.kS.length];

      for (int var6 = 0; var6 < this.kS.length; var6++) {
         Long var7 = this.kS[var6].pC(var1, var3, var4);
         if (var7 == null) {
            return null;
         }

         var5[var6] = var3 == 64 ? var7 : var7.intValue();
      }

      if (var5.length == 1) {
         if (this.A != null && this.A != VZ.xC) {
            switch (this.A) {
               case ED:
                  return ~var5[0];
               case Sc:
                  return ZV.pC(var5[0], pC(this.A), 0L, var4, var3 != 64);
               default:
                  return var5[0];
            }
         } else {
            return var5[0];
         }
      } else if (var5.length <= 2) {
         switch (this.A) {
            case Sc:
            case fI:
            case WR:
            case NS:
            case vP:
               return ZV.pC(var5[0], pC(this.A), var5[1], var4, var3 != 64);
            case pC:
            case A:
            case kS:
            case wS:
            case UT:
            case E:
            case gp:
            case oT:
            default:
               return pC(this.A, var3, var5[0], var5[1]);
            case sY:
               if (!PM.pC(var4)) {
                  return null;
               }

               boolean var16 = PM.A(var4);
               return var5[0] - var5[1] - (var16 ? 0 : 1);
            case ys:
               if (!PM.pC(var4)) {
                  return null;
               }

               boolean var15 = PM.A(var4);
               return var5[1] - var5[0] - (var15 ? 0 : 1);
            case ld:
               if (!PM.pC(var4)) {
                  return null;
               } else {
                  boolean var14 = PM.A(var4);
                  return var5[0] + var5[1] + (var14 ? 1 : 0);
               }
         }
      } else if (this.A != VZ.pC) {
         pC.catchingSilent(
            new RuntimeException(Strings.ff("Unable to calculate result with more than 2 operands for operation %s at %xh", Strings.safe(this.A), var1))
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

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static long pC(VZ var0, int var1, long var2, long var4) {
      switch (var0) {
         case pC:
            return var2 + var4;
         case A:
            return var2 - var4;
         case kS:
            return var2 & var4;
         case wS:
            return var2 & ~var4;
         case UT:
            return var2 ^ var4;
         case E:
            return var4 - var2;
         case gp:
            return var2 | var4;
         case oT:
            return var2 | ~var4;
         case sY:
         case ys:
         case ld:
         default:
            return -1L;
         case fI:
         case WR:
         case NS:
         case vP:
            return ZV.pC(var2, pC(var0), var4, null, var1 != 64);
      }
   }

   private static Z.Av pC(VZ var0) {
      return Z.Av.valueOf(var0.toString());
   }
}
