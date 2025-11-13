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
public class Td {
   private static final ILogger pC = GlobalLog.getLogger(Td.class);

   public static Integer pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      Integer var1 = null;
      if (var0.fI()) {
         var1 = (int)var0.A()[0].getOperandValue();
      } else if (var0.UT().sY() && var0.pC().isPCUpdated()) {
         var1 = var0.UT().A();
      }

      return var1;
   }

   public static boolean A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      Integer var1 = pC(var0);
      return var1 != null && var1 >= 2 && var1 <= 13 && (var1 & 14) != 6;
   }

   static boolean pC(Sp var0, com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Ro.K var2, int... var3) {
      return pC(var0, var1, var2.pC(), var3);
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   static boolean pC(Sp var0, com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, IEGeneric var2, int... var3) {
      Yg[] var4;
      List var5;
      try {
         var4 = jR.pC(var1);
         var5 = pC(var0.getGlobalContext(), var2, var3);
         if (var4 == null) {
            return false;
         }
      } catch (Exception var12) {
         pC.catchingSilent(var12);
         return true;
      }

      Yg[] var6;
      int var7;
      int var8;
      try {
         var6 = var4;
         var7 = var4.length;
         var8 = 0;
      } catch (Exception var10) {
         pC.catchingSilent(var10);
         return true;
      }

      while (true) {
         try {
            if (var8 >= var7) {
               return false;
            }

            Yg var9 = var6[var8];
            if (var5.contains(var0.kS(null, var9, -1L))) {
               return true;
            }
         } catch (Exception var11) {
            pC.catchingSilent(var11);
            return true;
         }

         var8++;
      }
   }

   static boolean pC(IEGlobalContext var0, Sp var1, Yg var2, Ro.K var3) {
      List var4 = pC(var0, var3);
      IEGeneric var5 = var1.kS(null, var2, -1L);
      return var4.contains(var5);
   }

   static List pC(IEGlobalContext var0, Ro.K var1, int... var2) {
      return pC(var0, var1.pC(), var2);
   }

   static List pC(IEGlobalContext var0, IEGeneric var1, int... var2) {
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

   static boolean pC(Sp var0, Yg var1, IEVar var2) {
      return RegisterUtil.getPureId(var1.getOperandValue()) == var0.getNativeRegisterIdFromRegisterVariable(var2, true);
   }

   static boolean pC(Yg var0, Yg var1) {
      return RegisterUtil.getPureId(var0.getOperandValue()) == RegisterUtil.getPureId(var1.getOperandValue());
   }
}
