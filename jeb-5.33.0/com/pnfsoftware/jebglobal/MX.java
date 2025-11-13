package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.format.Strings;

public class MX {
   public static mN pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0) {
      NC var1 = var0.wS();
      String var2 = var0.wS().pC();
      mN var3 = null;
      if (var0.getProcessorMode() != 64) {
         if (Strings.startsWith(var2, "LDA", "LDR", "VLDR")) {
            var3 = A(var0, var2);
            if (var3 != null && var2.equals("LDR")) {
               var3.pC(true);
            }
         } else if (Strings.startsWith(var2, "STREX", "STLEX")) {
            var3 = wS(var0, var2, mN.A(var1));
         } else if (Strings.startsWith(var2, "STR", "STL", "VSTR")) {
            var3 = pC(var0, var2);
         } else if (Strings.startsWith(var2, "POP", "LDM", "VPOP", "VLDM")) {
            var3 = A(var0, var2, mN.pC(var1));
            if (var3 != null && (var2.startsWith("LDM") || var2.equals("POP"))) {
               var3.pC(true);
            }
         } else if (Strings.startsWith(var2, "VLD")) {
            var3 = pC(var0, var2, mN.pC(var1));
         } else if (Strings.startsWith(var2, "PUSH", "STM", "VPUSH", "VSTM")) {
            var3 = A(var0, var2, mN.A(var1));
         } else if (Strings.startsWith(var2, "VST")) {
            var3 = pC(var0, var2, mN.A(var1));
         }
      } else if (Strings.isContainedIn(var2, "LDRAA", "LDRAB")) {
         var3 = A(var0, var2);
         var3.A(var2);
      } else if (Strings.startsWith(var2, "LDG", "ST2G", "STG", "STZ2G", "STZG")) {
         var3 = var2.startsWith("L") ? A(var0, var2) : pC(var0, var2);
         var3.kS(var2);
      } else if (Strings.startsWith(
         var2,
         "LDADD",
         "LDCLR",
         "LDEOR",
         "LDSET",
         "LDSMAX",
         "LDSMIN",
         "LDUMAX",
         "LDUMIN",
         "STADD",
         "STCLR",
         "STEOR",
         "STSET",
         "STSMAX",
         "STSMIN",
         "STUMAX",
         "STUMIN"
      )) {
         if (var2.startsWith("L")) {
            var3 = mN.pC(var1);
            var3.pC(var0.pC(1));
         } else {
            var3 = mN.A(var1);
            var3.pC(var0.pC(0));
         }

         var3.pC(var2, var0.pC(0));
         var3.A(var0.A()[var0.A().length - 1]);
         var3.kS(var0.wS());
      } else if (Strings.startsWith(var2, "LDR", "LDAPR", "LDAPUR", "LDAR", "LDAXP", "LDAXR", "LDLAR", "LDNP", "LDP", "LDTR", "LDUR", "LDXP", "LDXR")) {
         var3 = A(var0, var2);
      } else if (Strings.startsWith(var2, "STLX", "STX")) {
         var3 = wS(var0, var2, mN.A(var1));
      } else if (Strings.startsWith(var2, "STR", "STLLR", "STLR", "STLUR", "STTR", "STUR") || Strings.isContainedIn(var2, "STP", "STNP")) {
         var3 = pC(var0, var2);
      } else if (Strings.startsWith(var2, "LD64B", "ST64B")) {
         var3 = var2.startsWith("L") ? mN.pC(var1) : mN.A(var1);
         Yg var4 = var0.pC(0);
         if ((var2.endsWith("V") || var2.endsWith("V0")) && !var4.wS(var0.getProcessorMode())) {
            var3.kS(var4);
            var4 = var0.pC(1);
         }

         var3.pC(var4);
         int var5 = RegisterUtil.getRegisterIndex(var4.getOperandValue());

         for (int var6 = 1; var6 < 8; var6++) {
            var3.pC(LC.pC(var5 + var6, var0.getProcessorMode()));
         }

         var3.A(var0.A()[var0.A().length - 1]);
         var3.kS(var0.wS());
         if (var2.endsWith("V0")) {
            var3.wS(var2);
         }
      } else if (Strings.startsWith(var2, "LD")) {
         var3 = pC(var0, var2, mN.pC(var1));
      } else if (Strings.startsWith(var2, "ST")) {
         var3 = pC(var0, var2, mN.A(var1));
      }

      if (var3 != null && Strings.contains(var2, "SB", "SH", "SW")) {
         var3.A(true);
      }

      return var3 != null && var3.ah() ? var3 : null;
   }

   private static mN pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, String var1, mN var2) {
      if (var0.A().length != 2) {
         return null;
      } else {
         boolean var3 = true;
         Yg var4 = var0.A()[0];
         if (Strings.startsWith(var1, "LD")) {
            if (var1.endsWith("R")) {
               var2.pC(mN.Av.wS);
            } else if (!var1.equals("LD1") && !(var4 instanceof rw)) {
               var2.pC(mN.Av.A);
            }
         } else if (Strings.startsWith(var1, "ST") && !var1.equals("ST1")) {
            var2.pC(mN.Av.A);
         } else if (Strings.startsWith(var1, "VLD")) {
            var2.pC(var1);
            if (var4.E() != null && var4.E()[0] instanceof rw && ((rw)var4.E()[0]).kS() == null) {
               if (var1.equals("VLD1")) {
                  var2.pC(mN.Av.UT);
               } else {
                  var2.pC(mN.Av.wS);
               }

               var2.pC(Gq.pC(var0.wS()));
               var3 = false;
            } else if (var1.equals("VLD2") && var4.E() != null && var4.E().length == 4) {
               var2.pC(mN.Av.kS);
            } else if (!var1.equals("VLD1") && !(var4 instanceof rw)) {
               var2.pC(mN.Av.A);
            }
         } else if (Strings.startsWith(var1, "VST")) {
            var2.pC(var1);
            if (var1.equals("VST2") && var4.E() != null && var4.E().length == 4) {
               var2.pC(mN.Av.kS);
            } else if (!var1.equals("VST1")) {
               var2.pC(mN.Av.A);
            }
         }

         if (var4 instanceof Uw var5) {
            for (int var6 = 0; var6 < var5.E().length; var6++) {
               Yg var7 = var5.E()[var6];
               if (var0.getProcessorMode() != 64 && var7 instanceof rw && ((rw)var7).kS() == null) {
                  var2.pC(((rw)var7).A());
               } else {
                  var2.pC(var7);
               }
            }
         } else {
            if (!(var4 instanceof rw var10)) {
               return null;
            }

            if (!(var10.E()[0] instanceof Uw)) {
               return null;
            }

            Uw var9 = (Uw)var4.E()[0];
            int var11 = var10.kS();

            for (int var8 = 0; var8 < var9.E().length; var8++) {
               var2.pC(new rw(var9.E()[var8], var11));
            }
         }

         var2.A(var0.A()[var0.A().length - 1]);
         if (var3) {
            var2.kS(var0.wS());
         }

         return var2;
      }
   }

   private static mN A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, String var1, mN var2) {
      if (var0.A().length > 2) {
         return null;
      } else {
         if (var0.A().length == 1) {
            Yg var3 = LC.sY(var0.getProcessorMode());
            var2.A(new KH(var3, null, true, true, var0.getProcessorMode()));
         } else if (var0.A()[0] instanceof KH) {
            var2.A(var0.A()[0]);
         } else {
            boolean var5 = var0.A()[0].UT();
            boolean var4 = true;
            var2.A(new KH(var0.A()[0], null, var5, var4, var0.getProcessorMode()));
         }

         for (int var6 = 0; var6 < var0.A()[var0.A().length - 1].E().length; var6++) {
            var2.pC(var0.A()[var0.A().length - 1].E()[var6]);
         }

         var2.kS(var0.wS());
         var2.pC(var1);
         return var2;
      }
   }

   private static mN pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, String var1) {
      mN var2 = mN.A(var0.wS());
      return kS(var0, var1, var2);
   }

   private static mN A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, String var1) {
      mN var2 = mN.pC(var0.wS());
      return kS(var0, var1, var2);
   }

   private static mN kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, String var1, mN var2) {
      for (int var3 = 0; var3 < var0.A().length - 1; var3++) {
         var2.pC(var0.A()[var3]);
      }

      var2.A(var0.A()[var0.A().length - 1]);
      var2.kS(var0.wS());
      return var2;
   }

   private static mN wS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var0, String var1, mN var2) {
      var2.kS(var0.A()[0]);

      for (int var3 = 1; var3 < var0.A().length - 1; var3++) {
         var2.pC(var0.A()[var3]);
      }

      var2.A(var0.A()[var0.A().length - 1]);
      var2.kS(var0.wS());
      return var2;
   }
}
