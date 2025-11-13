package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPackage;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.JavaUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class bws {
   private static final ILogger q = GlobalLog.getLogger(bws.class);
   private IDGlobalContext RF;
   private IDexUnit xK;
   private static final String Dw = cvm.q(new byte[]{39, 8, 47, 29, 23, 29}, 2, 68);
   private static final int Uv = 1;
   private static final int oW = 2;
   private static final int gO = 4;
   private static final int nf = 8;
   private static final int gP = 16;
   private static final String za = cvm.q(new byte[]{39, 8, 47, 15, 31, 54, 3, 13, 0, 127, 75, 13, 93}, 2, 12);
   private static List lm = Arrays.asList(-389569552, -1614206264, 187736038, -800171234);
   private static String zz = cvm.q(
      new byte[]{-96, 63, 28, 0, 26, 11, 14, 9, 69, 79, 13, 4, 19, 6, 16, 2, 21, 27, 29, 82, 72, 13, 9, 28, 21, 23, 82, 67, 15, 13, 18, 0}, 1, 240
   );

   public void q(IDGlobalContext var1) {
      Assert.a(var1 != null);
      this.RF = var1;
      this.xK = var1.getDex();
      System.currentTimeMillis();

      try {
         this.Dw();
         this.q();
         this.RF();
      } finally {
         System.currentTimeMillis();
      }
   }

   void q() {
      if (this.xK
            .getMethod(
               cvm.q(
                  new byte[]{
                     -17,
                     38,
                     11,
                     23,
                     23,
                     78,
                     67,
                     13,
                     15,
                     9,
                     72,
                     93,
                     23,
                     3,
                     10,
                     9,
                     6,
                     23,
                     91,
                     98,
                     40,
                     17,
                     28,
                     7,
                     11,
                     95,
                     22,
                     19,
                     87,
                     7,
                     24,
                     25,
                     4,
                     14,
                     77,
                     100,
                     38,
                     11,
                     23,
                     23,
                     78,
                     67,
                     13,
                     15,
                     9,
                     72,
                     96,
                     45,
                     8,
                     15,
                     6,
                     23,
                     79,
                     96,
                     23,
                     38,
                     11,
                     23,
                     23,
                     78,
                     67,
                     13,
                     15,
                     9,
                     72,
                     96,
                     45,
                     8,
                     15,
                     6,
                     23,
                     79,
                     18,
                     101,
                     38,
                     11,
                     23,
                     23,
                     78,
                     67,
                     13,
                     15,
                     9,
                     72,
                     96,
                     45,
                     8,
                     15,
                     6,
                     23,
                     79
                  },
                  1,
                  163
               )
            )
         != null) {
         IDexMethod var1 = this.xK
            .getMethod(
               cvm.q(
                  new byte[]{
                     -16,
                     38,
                     11,
                     23,
                     23,
                     78,
                     67,
                     13,
                     15,
                     9,
                     72,
                     124,
                     42,
                     10,
                     7,
                     17,
                     8,
                     86,
                     22,
                     19,
                     82,
                     3,
                     14,
                     5,
                     40,
                     37,
                     11,
                     16,
                     19,
                     19,
                     11,
                     81,
                     100,
                     38,
                     11,
                     23,
                     23,
                     78,
                     67,
                     13,
                     15,
                     9,
                     72,
                     124,
                     39,
                     6,
                     27,
                     7,
                     9,
                     92,
                     18,
                     127
                  },
                  1,
                  188
               )
            );
         if (var1 != null) {
            int var2 = var1.getIndex();

            for (IDexMethod var4 : this.xK.getMethods()) {
               if (var4.isInternal() && var4.getName(false).equals("<clinit>")) {
                  IDexMethodData var5 = var4.getData();
                  if (var5.getCodeItem() != null && var5.isStatic()) {
                     List var6 = var5.getCodeItem().getInstructions();
                     if (var6.size() >= 3) {
                        IDalvikInstruction var7 = (IDalvikInstruction)var6.get(0);
                        int var8 = var7.getOpcode();
                        if (var8 == 26 || var8 == 27) {
                           int var9 = (int)var7.getParameter(0).getValue();
                           int var10 = (int)var7.getParameter(1).getValue();
                           var7 = (IDalvikInstruction)var6.get(1);
                           var8 = var7.getOpcode();
                           if (var8 == 113) {
                              int var11 = (int)var7.getParameter(0).getValue();
                              if (var11 == var2) {
                                 int var12 = (int)var7.getParameter(1).getValue();
                                 if (var12 == var9) {
                                    String var13 = this.xK.getString(var10).getValue();
                                    if (this.q(var13)) {
                                       this.RF.setData(Dw, true);
                                       break;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private boolean q(String var1) {
      int var2 = var1.length();
      if (var2 >= 4 && var2 <= 8) {
         for (int var3 = 0; var3 < var2; var3++) {
            char var4 = var1.charAt(var3);
            if ((var4 < '0' || var4 > '9') && (var4 < 'a' || var4 > 'f')) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean RF(IDGlobalContext var0) {
      return Boolean.TRUE.equals(var0.getData(Dw));
   }

   void RF() {
      ArrayList var1 = new ArrayList();

      label96:
      for (IDexClass var3 : this.xK.getClasses()) {
         IDexMethod var4 = null;

         for (IDexMethod var6 : var3.getMethods()) {
            if (!var6.getData().isConstructor()) {
               int var7 = var6.getData().getAccessFlags();
               if ((var7 & 10) == 0 && var6.getPrototype().getShorty().equals("II")) {
                  if (var4 != null) {
                     continue label96;
                  }

                  var4 = var6;
               }
            }
         }

         if (var4 != null) {
            byte var18 = 0;

            for (IDexField var21 : var3.getFields()) {
               int var8 = var21.getData().getAccessFlags();
               if ((var8 & 8) == 0) {
                  String var9 = var21.getFieldTypeSignature(false);
                  byte var10;
                  switch (var9) {
                     case "[I":
                        var10 = 1;
                        break;
                     case "[J":
                        var10 = 2;
                        break;
                     case "[F":
                        var10 = 4;
                        break;
                     case "[D":
                        var10 = 8;
                        break;
                     case "[Ljava/lang/Object;":
                        var10 = 16;
                        break;
                     default:
                        continue;
                  }

                  if ((var18 & var10) != 0) {
                     continue label96;
                  }

                  var18 |= var10;
               }
            }

            if (var18 == 31) {
               BasicBlock var20 = var4.getData().getCodeItem().getControlFlowGraph().get(0);
               IDalvikInstruction var22 = (IDalvikInstruction)var20.getLast();
               if (var22.isSwitch()) {
                  var1.add(var3.getSignature(false));
               }
            }
         }
      }

      int var13 = var1.size();
      if (var13 > 0) {
         this.RF.setData(za, var13);
         String var14 = S.L("Virtualization: %d p-code VM were detected: %s");
         String var16 = Strings.ff(var14, var13, var1);
         q.info(var16);
         if (Licensing.isReleaseBuild()) {
            var14 = cvm.q(
               new byte[]{
                  7,
                  40,
                  80,
                  47,
                  63,
                  73,
                  3,
                  13,
                  0,
                  69,
                  75,
                  23,
                  76,
                  68,
                  11,
                  25,
                  28,
                  87,
                  12,
                  13,
                  18,
                  124,
                  125,
                  115,
                  101,
                  99,
                  111,
                  38,
                  46,
                  67,
                  46,
                  32,
                  0,
                  40,
                  35,
                  52,
                  99,
                  42,
                  63,
                  101,
                  39,
                  32,
                  54,
                  100
               },
               2,
               81
            );
            var16 = Strings.ff(var14, var13);
            throw new RuntimeException(var16);
         }
      }
   }

   public static boolean xK(IDGlobalContext var0) {
      return var0.getData(za) instanceof Integer var1 && var1 > 0;
   }

   void xK() {
      boolean var1 = false;

      for (IDexPackage var3 : this.xK.getPackages()) {
         String var4 = var3.getName(false);
         int var5 = RF(var4);
         if (lm.contains(var5)) {
            var1 = true;
            break;
         }
      }

      if (var1 && Licensing.isReleaseBuild()) {
         throw new RuntimeException(
            cvm.q(
               new byte[]{
                  17, 20, 112, 68, 1, 17, 17, 6, 23, 17, 1, 68, 13, 13, 108, 3, 8, 14, 10, 99, 116, 27, 111, 98, 7, 101, 105, 4, 29, 28, 9, 8, 8, 11, 26, 17, 1
               },
               1,
               85
            )
         );
      }
   }

   private static int RF(String var0) {
      int var1 = 0;
      if (var0 != null) {
         for (int var2 = 0; var2 < var0.length(); var2++) {
            var1 = 37 * var1 + var0.charAt(var2);
         }
      }

      return var1;
   }

   void Dw() {
      IDexMethod var1 = this.xK
         .getMethod(
            cvm.q(
               new byte[]{
                  14,
                  38,
                  11,
                  23,
                  23,
                  78,
                  67,
                  13,
                  15,
                  9,
                  72,
                  108,
                  47,
                  13,
                  18,
                  0,
                  72,
                  22,
                  19,
                  88,
                  9,
                  29,
                  60,
                  47,
                  12,
                  8,
                  77,
                  100,
                  38,
                  11,
                  23,
                  23,
                  78,
                  67,
                  13,
                  15,
                  9,
                  72,
                  124,
                  39,
                  6,
                  27,
                  7,
                  9,
                  92,
                  18,
                  101,
                  38,
                  11,
                  23,
                  23,
                  78,
                  67,
                  13,
                  15,
                  9,
                  72,
                  108,
                  47,
                  13,
                  18,
                  0,
                  72
               },
               1,
               66
            )
         );
      if (var1 != null) {
         int var2 = var1.getIndex();
         ArrayList var3 = new ArrayList();

         label96:
         for (IDexClass var5 : this.xK.getClasses()) {
            for (IDexMethod var7 : var5.getMethods()) {
               IDexMethodData var8 = var7.getData();
               if (var8.getCodeItem() != null && !var8.isConstructor() && var8.isStatic() && var8.isPublic() && var7.getParameterTypes().isEmpty()) {
                  List var9 = var8.getCodeItem().getInstructions();
                  if (var9.size() <= 50) {
                     for (int var10 = 1; var10 < var9.size(); var10++) {
                        IDalvikInstruction var11 = (IDalvikInstruction)var9.get(var10);
                        if (var11.getOpcode() == 113) {
                           int var12 = (int)var11.getParameter(0).getValue();
                           if (var12 == var2) {
                              int var13 = (int)var11.getParameter(1).getValue();
                              var11 = (IDalvikInstruction)var9.get(var10 - 1);
                              int var14 = var11.getOpcode();
                              if (var14 == 26 || var14 == 27) {
                                 int var15 = (int)var11.getParameter(0).getValue();
                                 if (var15 == var13) {
                                    int var16 = (int)var11.getParameter(1).getValue();
                                    String var17 = this.xK.getString(var16).getValue();
                                    if (var17.length() < 10 && !JavaUtil.isValidClassname(var17)) {
                                       q.trace("%s: %s", zz, var5.getSignature());
                                       var3.add(var5);
                                       continue label96;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (!var3.isEmpty()) {
            Set var18 = ccq.q(this.RF, true);

            for (IDexClass var20 : var3) {
               String var21 = var20.getSignature(false);
               this.xK.getCommentManager().addMetaComment(var21, new MetaComment(zz), false);
               var18.add(var21);
            }

            this.xK.notifyListeners(new JebEvent(J.UnitChange, new UnitChangeEventData(3, this.xK)));
         }
      }
   }
}
