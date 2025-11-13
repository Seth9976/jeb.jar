package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.format.Strings;

public class PG {
   public static fV q(fA var0) {
      Op var1 = var0.Dw();
      String var2 = var0.Dw().q();
      fV var3 = null;
      if (var0.getProcessorMode() != 64) {
         if (Strings.startsWith(var2, "LDA", "LDR", "VLDR")) {
            var3 = RF(var0, var2);
            if (var3 != null && var2.equals("LDR")) {
               var3.q(true);
            }
         } else if (Strings.startsWith(var2, "STREX", "STLEX")) {
            var3 = Dw(var0, var2, fV.RF(var1));
         } else if (Strings.startsWith(var2, "STR", "STL", "VSTR")) {
            var3 = q(var0, var2);
         } else if (Strings.startsWith(var2, "POP", "LDM", "VPOP", "VLDM")) {
            var3 = RF(var0, var2, fV.q(var1));
            if (var3 != null && (var2.startsWith("LDM") || var2.equals("POP"))) {
               var3.q(true);
            }
         } else if (Strings.startsWith(var2, "VLD")) {
            var3 = q(var0, var2, fV.q(var1));
         } else if (Strings.startsWith(var2, "PUSH", "STM", "VPUSH", "VSTM")) {
            var3 = RF(var0, var2, fV.RF(var1));
         } else if (Strings.startsWith(var2, "VST")) {
            var3 = q(var0, var2, fV.RF(var1));
         }
      } else if (Strings.isContainedIn(var2, "LDRAA", "LDRAB")) {
         var3 = RF(var0, var2);
         var3.RF(var2);
      } else if (Strings.startsWith(var2, "LDG", "ST2G", "STG", "STZ2G", "STZG")) {
         var3 = var2.startsWith("L") ? RF(var0, var2) : q(var0, var2);
         var3.xK(var2);
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
            var3 = fV.q(var1);
            var3.q(var0.q(1));
         } else {
            var3 = fV.RF(var1);
            var3.q(var0.q(0));
         }

         var3.q(var2, var0.q(0));
         var3.RF(var0.RF()[var0.RF().length - 1]);
         var3.xK(var0.Dw());
      } else if (Strings.startsWith(var2, "LDR", "LDAPR", "LDAPUR", "LDAR", "LDAXP", "LDAXR", "LDLAR", "LDNP", "LDP", "LDTR", "LDUR", "LDXP", "LDXR")) {
         var3 = RF(var0, var2);
      } else if (Strings.startsWith(var2, "STLX", "STX")) {
         var3 = Dw(var0, var2, fV.RF(var1));
      } else if (Strings.startsWith(var2, "STR", "STLLR", "STLR", "STLUR", "STTR", "STUR") || Strings.isContainedIn(var2, "STP", "STNP")) {
         var3 = q(var0, var2);
      } else if (Strings.startsWith(var2, "LD64B", "ST64B")) {
         var3 = var2.startsWith("L") ? fV.q(var1) : fV.RF(var1);
         CW var4 = var0.q(0);
         if ((var2.endsWith("V") || var2.endsWith("V0")) && !var4.Dw(var0.getProcessorMode())) {
            var3.xK(var4);
            var4 = var0.q(1);
         }

         var3.q(var4);
         int var5 = RegisterUtil.getRegisterIndex(var4.getOperandValue());

         for (int var6 = 1; var6 < 8; var6++) {
            var3.q(GC.q(var5 + var6, var0.getProcessorMode()));
         }

         var3.RF(var0.RF()[var0.RF().length - 1]);
         var3.xK(var0.Dw());
         if (var2.endsWith("V0")) {
            var3.Dw(var2);
         }
      } else if (Strings.startsWith(var2, "LD")) {
         var3 = q(var0, var2, fV.q(var1));
      } else if (Strings.startsWith(var2, "ST")) {
         var3 = q(var0, var2, fV.RF(var1));
      }

      if (var3 != null && Strings.contains(var2, "SB", "SH", "SW")) {
         var3.RF(true);
      }

      return var3 != null && var3.Me() ? var3 : null;
   }

   private static fV q(fA var0, String var1, fV var2) {
      if (var0.RF().length != 2) {
         return null;
      } else {
         boolean var3 = true;
         CW var4 = var0.RF()[0];
         if (Strings.startsWith(var1, "LD")) {
            if (var1.endsWith("R")) {
               var2.q(fV.eo.Dw);
            } else if (!var1.equals("LD1") && !(var4 instanceof BY)) {
               var2.q(fV.eo.RF);
            }
         } else if (Strings.startsWith(var1, "ST") && !var1.equals("ST1")) {
            var2.q(fV.eo.RF);
         } else if (Strings.startsWith(var1, "VLD")) {
            var2.q(var1);
            if (var4.oW() != null && var4.oW()[0] instanceof BY && ((BY)var4.oW()[0]).xK() == null) {
               if (var1.equals("VLD1")) {
                  var2.q(fV.eo.Uv);
               } else {
                  var2.q(fV.eo.Dw);
               }

               var2.q(k.q(var0.Dw()));
               var3 = false;
            } else if (var1.equals("VLD2") && var4.oW() != null && var4.oW().length == 4) {
               var2.q(fV.eo.xK);
            } else if (!var1.equals("VLD1") && !(var4 instanceof BY)) {
               var2.q(fV.eo.RF);
            }
         } else if (Strings.startsWith(var1, "VST")) {
            var2.q(var1);
            if (var1.equals("VST2") && var4.oW() != null && var4.oW().length == 4) {
               var2.q(fV.eo.xK);
            } else if (!var1.equals("VST1")) {
               var2.q(fV.eo.RF);
            }
         }

         if (var4 instanceof eL var5) {
            for (int var6 = 0; var6 < var5.oW().length; var6++) {
               CW var7 = var5.oW()[var6];
               if (var0.getProcessorMode() != 64 && var7 instanceof BY && ((BY)var7).xK() == null) {
                  var2.q(((BY)var7).RF());
               } else {
                  var2.q(var7);
               }
            }
         } else {
            if (!(var4 instanceof BY var10)) {
               return null;
            }

            if (!(var10.oW()[0] instanceof eL)) {
               return null;
            }

            eL var9 = (eL)var4.oW()[0];
            int var11 = var10.xK();

            for (int var8 = 0; var8 < var9.oW().length; var8++) {
               var2.q(new BY(var9.oW()[var8], var11));
            }
         }

         var2.RF(var0.RF()[var0.RF().length - 1]);
         if (var3) {
            var2.xK(var0.Dw());
         }

         return var2;
      }
   }

   private static fV RF(fA var0, String var1, fV var2) {
      if (var0.RF().length > 2) {
         return null;
      } else {
         if (var0.RF().length == 1) {
            CW var3 = GC.oW(var0.getProcessorMode());
            var2.RF(new wh(var3, null, true, true, var0.getProcessorMode()));
         } else if (var0.RF()[0] instanceof wh) {
            var2.RF(var0.RF()[0]);
         } else {
            boolean var5 = var0.RF()[0].Uv();
            boolean var4 = true;
            var2.RF(new wh(var0.RF()[0], null, var5, var4, var0.getProcessorMode()));
         }

         for (int var6 = 0; var6 < var0.RF()[var0.RF().length - 1].oW().length; var6++) {
            var2.q(var0.RF()[var0.RF().length - 1].oW()[var6]);
         }

         var2.xK(var0.Dw());
         var2.q(var1);
         return var2;
      }
   }

   private static fV q(fA var0, String var1) {
      fV var2 = fV.RF(var0.Dw());
      return xK(var0, var1, var2);
   }

   private static fV RF(fA var0, String var1) {
      fV var2 = fV.q(var0.Dw());
      return xK(var0, var1, var2);
   }

   private static fV xK(fA var0, String var1, fV var2) {
      for (int var3 = 0; var3 < var0.RF().length - 1; var3++) {
         var2.q(var0.RF()[var3]);
      }

      var2.RF(var0.RF()[var0.RF().length - 1]);
      var2.xK(var0.Dw());
      return var2;
   }

   private static fV Dw(fA var0, String var1, fV var2) {
      var2.xK(var0.RF()[0]);

      for (int var3 = 1; var3 < var0.RF().length - 1; var3++) {
         var2.q(var0.RF()[var3]);
      }

      var2.RF(var0.RF()[var0.RF().length - 1]);
      var2.xK(var0.Dw());
      return var2;
   }
}
