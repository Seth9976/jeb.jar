package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class adj {
   private static final StructuredLogger pC = aco.pC(adj.class);
   private static Map A = new ConcurrentHashMap();

   public static ICField pC(adl var0, String var1) {
      return var1 != null && var0.getGlobalContext() != null ? var0.getGlobalContext().getFieldFactory().create(var1) : null;
   }

   public static ICMethod A(adl var0, String var1) {
      return var1 != null && var0.getGlobalContext() != null ? var0.getGlobalContext().getMethodFactory().create(var1) : null;
   }

   public static ICIdentifier pC(ICElement var0) {
      ICDecl var1 = null;
      if (var0 instanceof ICAssignment || var0 instanceof ICDecl) {
         var1 = CUtil.getDefinition(var0);
      } else if (var0 instanceof afw) {
         var1 = CUtil.getDefinition(((afw)var0).A());
      } else if (var0 instanceof ICForStm) {
         var1 = CUtil.getDefinition(((ICForStm)var0).getPreStatement());
      }

      return var1 != null ? var1.getIdentifier() : null;
   }

   public static Set A(ICElement var0) {
      HashSet var1 = new HashSet();
      if (!(var0 instanceof agk)) {
         pC(var0, var1);
      } else if (var0 instanceof afy) {
         pC(((afy)var0).pC(), var1);
      } else if (var0 instanceof afz) {
         pC(((afz)var0).pC(), var1);
      } else if (var0 instanceof agi) {
         pC(((agi)var0).pC(), var1);
      } else if (var0 instanceof afv) {
         pC(((afv)var0).pC(), var1);
      } else if (var0 instanceof age) {
         pC(((age)var0).A(), var1);
      } else if (var0 instanceof afw) {
         HashSet var2 = new HashSet();
         pC(((afw)var0).A(), var2);
         var1 = var2;
         HashSet var3 = new HashSet();
         pC(((afw)var0).pC(), var3);
         var2.addAll(var3);
         HashSet var4 = new HashSet();
         pC(((afw)var0).kS(), var4);
         var2.addAll(var4);
      }

      return var1;
   }

   private static void pC(ICElement var0, Set var1) {
      for (ICElement var3 : var0.getSubElements()) {
         if (!CUtil.isClassMethodField(var3)) {
            if (var3 instanceof ICIdentifier) {
               var1.add((ICIdentifier)var3);
            }

            pC(var3, var1);
         }
      }
   }

   public static int pC(ICIfStm var0, ICLabel var1) {
      int var2 = 0;
      boolean var3 = false;

      for (ICBlock var5 : var0.getBlocks()) {
         if (var5.size() == 1 && (CUtil.isGotoTo(var5.get(0), var1) || CUtil.isPlainBreak(var5.get(0)) || CUtil.isBreakTo(var5.get(0), var1))) {
            var3 = true;
            break;
         }

         var2++;
      }

      return var3 ? var2 : -1;
   }

   public static int A(ICIfStm var0, ICLabel var1) {
      int var2 = 0;
      int var3 = -1;

      for (ICBlock var5 : var0.getBlocks()) {
         if (var5.size() == 1 && (CUtil.isGotoTo(var5.get(0), var1) || CUtil.isPlainBreak(var5.get(0)) || CUtil.isBreakTo(var5.get(0), var1))) {
            var3 = var2;
         }

         var2++;
      }

      return var3;
   }

   public static int pC(long var0, boolean var2) {
      if (var2) {
         if (var0 >= 0L && var0 <= 127L) {
            return 10;
         }
      } else if (-127L <= var0 && var0 <= 127L) {
         return 10;
      }

      if (var0 < 0L) {
         var0 = -var0;
         if (var0 < 0L) {
            return 16;
         }
      }

      Integer var3 = (Integer)A.get(var0);
      if (var3 != null) {
         return var3;
      } else {
         double var4 = pC(var0, 10);
         double var6 = pC(var0, 16);
         var3 = var4 >= var6 ? 10 : 16;
         Object[] var10000 = new Object[]{var0, var0, var4, var6, var3};
         A.put(var0, var3);
         return var3;
      }
   }

   private static double pC(long var0, int var2) {
      long var3 = var0;
      int[] var5 = new int[var2];
      int var6 = -10;
      int var7 = 0;

      double var8;
      for (var8 = 0.0; var3 != 0L; var3 /= var2) {
         int var10 = (int)(var3 % var2);
         var5[var10]++;
         if (var10 != var6 && var10 != var6 - 1 && var10 != var6 + 1) {
            if (var7 >= 3) {
               var8 += Math.pow(2.0, var7 - 1);
            }

            var6 = var10;
            var7 = 1;
         } else {
            var6 = var10;
            var7++;
         }
      }

      if (var7 >= 3) {
         var8 += Math.pow(2.0, var7 - 1);
      }

      double var16 = 0.0;

      for (int var15 : var5) {
         if (var15 > 0) {
            var16 += var15 - 1;
         }
      }

      var16 += var8;
      return var16 * Math.log(var2);
   }
}
