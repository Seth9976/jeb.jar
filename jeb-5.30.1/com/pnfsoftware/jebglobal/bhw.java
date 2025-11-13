package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationForParameter;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotationsDirectory;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerStaticOk;
import java.util.Collections;
import java.util.List;

@Ser
public final class bhw implements IDexAnnotationsDirectory {
   private static final ILogger RF = GlobalLog.getLogger(bhw.class);
   @SerStaticOk
   public static bhw q = new bhw();
   @SerId(1)
   private bhv xK;
   @SerId(2)
   private bhx[] Dw;
   @SerId(3)
   private bhy[] Uv;
   @SerId(4)
   private bhz[] oW;

   private bhw() {
   }

   @Override
   public List getClassAnnotations() {
      return this.xK == null ? Collections.emptyList() : this.xK.RF();
   }

   @Override
   public List getFieldsAnnotations() {
      return this.Dw == null ? Collections.emptyList() : ArrayUtil.asView(this.Dw);
   }

   @Override
   public List getMethodsAnnotations() {
      return this.Uv == null ? Collections.emptyList() : ArrayUtil.asView(this.Uv);
   }

   @Override
   public List getParametersAnnotations() {
      return this.oW == null ? Collections.emptyList() : ArrayUtil.asView(this.oW);
   }

   @Override
   public List getFieldAnnotations(int var1) {
      for (IDexAnnotationForField var3 : this.getFieldsAnnotations()) {
         int var4 = var3.getFieldIndex();
         if (var4 == var1) {
            return var3.getAnnotationItems();
         }

         if (var4 > var1) {
            break;
         }
      }

      return Collections.emptyList();
   }

   @Override
   public List getMethodAnnotations(int var1) {
      for (IDexAnnotationForMethod var3 : this.getMethodsAnnotations()) {
         int var4 = var3.getMethodIndex();
         if (var4 == var1) {
            return var3.getAnnotationItems();
         }

         if (var4 > var1) {
            break;
         }
      }

      return Collections.emptyList();
   }

   @Override
   public List getParametersAnnotations(int var1) {
      for (IDexAnnotationForParameter var3 : this.getParametersAnnotations()) {
         int var4 = var3.getMethodIndex();
         if (var4 == var1) {
            return var3.getAnnotationItemSets();
         }

         if (var4 > var1) {
            break;
         }
      }

      return Collections.emptyList();
   }

   public static bhw q(com.pnfsoftware.jeb.corei.parsers.dex.bK var0, bjw var1, byte[] var2, int var3) {
      int var4 = DexUtil.bytearrayULEInt32ToInt(var2, var3);
      int var5 = DexUtil.bytearrayULEInt32ToInt(var2, var3 + 4);
      int var6 = DexUtil.bytearrayULEInt32ToInt(var2, var3 + 8);
      int var7 = DexUtil.bytearrayULEInt32ToInt(var2, var3 + 12);
      bhw var8 = new bhw();
      if (var4 > 0) {
         var8.xK = bhv.q(var0, var1, var2, var4);
      }

      int var9 = var3 + 16;
      var8.Dw = new bhx[var5];

      for (int var10 = 0; var10 < var5; var10++) {
         int var11 = DexUtil.bytearrayULEInt32ToInt(var2, var9);
         var11 = var1.Dw(var11);
         int var12 = DexUtil.bytearrayULEInt32ToInt(var2, var9 + 4);
         bhv var13 = bhv.q(var0, var1, var2, var12);
         var8.Dw[var10] = new bhx(var11, var13);
         var9 += 8;
      }

      var8.Uv = new bhy[var6];

      for (int var18 = 0; var18 < var6; var18++) {
         int var21 = DexUtil.bytearrayULEInt32ToInt(var2, var9);
         var21 = var1.Uv(var21);
         int var25 = DexUtil.bytearrayULEInt32ToInt(var2, var9 + 4);
         bhv var27 = bhv.q(var0, var1, var2, var25);
         var8.Uv[var18] = new bhy(var21, var27);
         var9 += 8;
      }

      var8.oW = new bhz[var7];

      for (int var19 = 0; var19 < var7; var19++) {
         int var23 = DexUtil.bytearrayULEInt32ToInt(var2, var9);
         var23 = var1.Uv(var23);
         int var26 = DexUtil.bytearrayULEInt32ToInt(var2, var9 + 4);
         int var28 = DexUtil.bytearrayULEInt32ToInt(var2, var26);
         bhv[] var14 = new bhv[var28];

         for (int var15 = 0; var15 < var28; var15++) {
            int var16 = DexUtil.bytearrayULEInt32ToInt(var2, var26 + 4 + 4 * var15);
            bhv var17 = bhv.q(var0, var1, var2, var16);
            var14[var15] = var17;
         }

         var8.oW[var19] = new bhz(var23, var14);
         var9 += 8;
      }

      return var8;
   }
}
