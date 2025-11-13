package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SerDisabled
public class tB {
   private static final ILogger q = GlobalLog.getLogger(tB.class);

   public static Integer q(fA var0) {
      Integer var1 = null;
      if (var0.zz()) {
         var1 = (int)var0.RF()[0].getOperandValue();
      } else if (var0.Uv().gO() && var0.q().isPCUpdated()) {
         var1 = var0.Uv().RF();
      }

      return var1;
   }

   public static boolean RF(fA var0) {
      Integer var1 = q(var0);
      return var1 != null && var1 >= 2 && var1 <= 13 && (var1 & 14) != 6;
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   static boolean q(fA var0, CW var1) {
      CW[] var2;
      try {
         var2 = p.q(var0);
         if (var2 == null) {
            return false;
         }
      } catch (Exception var9) {
         q.catchingSilent(var9);
         return true;
      }

      CW[] var3;
      int var4;
      int var5;
      try {
         var3 = var2;
         var4 = var2.length;
         var5 = 0;
      } catch (Exception var7) {
         q.catchingSilent(var7);
         return true;
      }

      while (true) {
         try {
            if (var5 >= var4) {
               return false;
            }

            CW var6 = var3[var5];
            if (var6.equals(var1)) {
               return true;
            }
         } catch (Exception var8) {
            q.catchingSilent(var8);
            return true;
         }

         var5++;
      }
   }

   static boolean q(bR var0, fA var1, Ia.nI var2, int... var3) {
      return q(var0, var1, var2.q(), var3);
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   static boolean q(bR var0, fA var1, IEGeneric var2, int... var3) {
      CW[] var4;
      List var5;
      try {
         var4 = p.q(var1);
         var5 = q(var0.getGlobalContext(), var2, var3);
         if (var4 == null) {
            return false;
         }
      } catch (Exception var12) {
         q.catchingSilent(var12);
         return true;
      }

      CW[] var6;
      int var7;
      int var8;
      try {
         var6 = var4;
         var7 = var4.length;
         var8 = 0;
      } catch (Exception var10) {
         q.catchingSilent(var10);
         return true;
      }

      while (true) {
         try {
            if (var8 >= var7) {
               return false;
            }

            CW var9 = var6[var8];
            if (var5.contains(var0.xK(null, var9, -1L))) {
               return true;
            }
         } catch (Exception var11) {
            q.catchingSilent(var11);
            return true;
         }

         var8++;
      }
   }

   static boolean q(IEGlobalContext var0, bR var1, CW var2, Ia.nI var3) {
      List var4 = q(var0, var3);
      IEGeneric var5 = var1.xK(null, var2, -1L);
      return var4.contains(var5);
   }

   static List q(IEGlobalContext var0, Ia.nI var1, int... var2) {
      return q(var0, var1.q(), var2);
   }

   static List q(IEGlobalContext var0, IEGeneric var1, int... var2) {
      IdRanges var3 = var1.getUsed();
      if (var3.size() == 0) {
         return Collections.emptyList();
      } else {
         ArrayList var4 = new ArrayList();

         for (int var6 : var3.getVarIds()) {
            boolean var7 = false;

            for (int var11 : var2) {
               if (var6 == var11) {
                  var7 = true;
                  break;
               }
            }

            if (!var7) {
               IEVar var12 = var0.getVar(var6);
               var4.add(var12);
            }
         }

         return var4;
      }
   }

   static boolean q(bR var0, CW var1, IEVar var2) {
      return RegisterUtil.getPureId(var1.getOperandValue()) == var0.getNativeRegisterIdFromRegisterVariable(var2, true);
   }

   static boolean q(CW var0, CW var1) {
      return RegisterUtil.getPureId(var0.getOperandValue()) == RegisterUtil.getPureId(var1.getOperandValue());
   }
}
