package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericBreakable;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericWhileLoop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class afc {
   private static final StructuredLogger q = aeg.q(afc.class);
   private static Map RF = new ConcurrentHashMap();

   public static ICClass q(afe var0, String var1) {
      return var1 != null && var0.getGlobalContext() != null ? var0.getGlobalContext().getClassFactory().create(var1) : null;
   }

   public static ICField RF(afe var0, String var1) {
      return var1 != null && var0.getGlobalContext() != null ? var0.getGlobalContext().getFieldFactory().create(var1) : null;
   }

   public static ICMethod xK(afe var0, String var1) {
      return var1 != null && var0.getGlobalContext() != null ? var0.getGlobalContext().getMethodFactory().create(var1) : null;
   }

   public static boolean q(ICMethod var0) {
      return Dw(var0);
   }

   private static boolean xK(ICElement var0) {
      return var0 instanceof ICGenericBreakable;
   }

   private static boolean Dw(ICElement var0) {
      if (var0 instanceof ICBreak) {
         return false;
      } else if (xK(var0)) {
         return true;
      } else {
         for (ICElement var2 : var0.getSubElements()) {
            if (CUtil.isClassMethodField(var2)) {
               return true;
            }

            if (!Dw(var2)) {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean RF(ICMethod var0) {
      return q(var0, new IdentityHashSet());
   }

   private static boolean Uv(ICElement var0) {
      return var0 instanceof ICIdentifier
         || var0 instanceof ICConstant
         || var0 instanceof ICLabel
         || var0 instanceof ICType
         || var0 instanceof ICClass
         || var0 instanceof ICField;
   }

   private static boolean q(ICElement var0, IdentityHashSet var1) {
      if (Uv(var0)) {
         return true;
      } else if (var1.contains(var0)) {
         Object[] var10000 = new Object[]{var0};
         return false;
      } else {
         var1.add(var0);

         for (ICElement var3 : var0.getSubElements()) {
            if (CUtil.isClassMethodField(var3)) {
               return true;
            }

            if (!q(var3, var1)) {
               return false;
            }
         }

         return true;
      }
   }

   public static String xK(ICMethod var0) {
      StringBuilder var1 = new StringBuilder();
      q(var0, var1);
      return var1.toString();
   }

   private static void q(ICElement var0, StringBuilder var1) {
      RF(var0, var1);

      for (ICElement var3 : var0.getSubElements()) {
         if (CUtil.isClassMethodField(var3)) {
            return;
         }

         q(var3, var1);
      }
   }

   private static void RF(ICElement var0, StringBuilder var1) {
      if (var0 instanceof ICGenericWhileLoop && ((ICGenericWhileLoop)var0).getPredicate().isLitteralFalse()) {
         var1.append("litteral false w/dow loop ");
      }

      if (var0 instanceof ICSwitchStm) {
         if (((ICSwitchStm)var0).getBlocks() != null && !((ICSwitchStm)var0).getBlocks().isEmpty()) {
            if (((ICSwitchStm)var0).getBlocks().size() == 1) {
               var1.append("switch-case with only one case ");
            }
         } else {
            var1.append("empty switch-case ");
         }
      }

      if (var0 instanceof ICForStm && ((ICForStm)var0).getPredicate().isLitteralFalse()) {
         var1.append("litteral false for loop ");
      }
   }

   public static ICIdentifier q(ICElement var0) {
      ICDecl var1 = null;
      if (var0 instanceof ICAssignment || var0 instanceof ICDecl) {
         var1 = CUtil.getDefinition(var0);
      } else if (var0 instanceof ahp) {
         var1 = CUtil.getDefinition(((ahp)var0).RF());
      } else if (var0 instanceof ICForStm) {
         var1 = CUtil.getDefinition(((ICForStm)var0).getPreStatement());
      }

      return var1 != null ? var1.getIdentifier() : null;
   }

   public static Set RF(ICElement var0) {
      HashSet var1 = new HashSet();
      if (!(var0 instanceof aid)) {
         q(var0, var1);
      } else if (var0 instanceof ahr) {
         q(((ahr)var0).q(), var1);
      } else if (var0 instanceof ahs) {
         q(((ahs)var0).q(), var1);
      } else if (var0 instanceof aib) {
         q(((aib)var0).q(), var1);
      } else if (var0 instanceof aho) {
         q(((aho)var0).q(), var1);
      } else if (var0 instanceof ahx) {
         q(((ahx)var0).RF(), var1);
      } else if (var0 instanceof ahp) {
         HashSet var2 = new HashSet();
         q(((ahp)var0).RF(), var2);
         var1 = var2;
         HashSet var3 = new HashSet();
         q(((ahp)var0).q(), var3);
         var2.addAll(var3);
         HashSet var4 = new HashSet();
         q(((ahp)var0).xK(), var4);
         var2.addAll(var4);
      }

      return var1;
   }

   private static void q(ICElement var0, Set var1) {
      for (ICElement var3 : var0.getSubElements()) {
         if (!CUtil.isClassMethodField(var3)) {
            if (var3 instanceof ICIdentifier) {
               var1.add((ICIdentifier)var3);
            }

            q(var3, var1);
         }
      }
   }

   public static int q(ICIfStm var0, ICLabel var1) {
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

   public static int RF(ICIfStm var0, ICLabel var1) {
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

   public static int q(long var0) {
      return q(var0, false);
   }

   public static int q(long var0, boolean var2) {
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

      Integer var3 = (Integer)RF.get(var0);
      if (var3 != null) {
         return var3;
      } else {
         double var4 = q(var0, 10);
         double var6 = q(var0, 16);
         var3 = var4 >= var6 ? 10 : 16;
         Object[] var10000 = new Object[]{var0, var0, var4, var6, var3};
         RF.put(var0, var3);
         return var3;
      }
   }

   private static double q(long var0, int var2) {
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
