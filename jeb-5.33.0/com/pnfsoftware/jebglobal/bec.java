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
public final class bec implements IDexAnnotationsDirectory {
   private static final ILogger A = GlobalLog.getLogger(bec.class);
   @SerStaticOk
   public static bec pC = new bec();
   @SerId(1)
   private beb kS;
   @SerId(2)
   private bed[] wS;
   @SerId(3)
   private bee[] UT;
   @SerId(4)
   private bef[] E;

   private bec() {
   }

   @Override
   public List getClassAnnotations() {
      return this.kS == null ? Collections.emptyList() : this.kS.pC();
   }

   @Override
   public List getFieldsAnnotations() {
      return this.wS == null ? Collections.emptyList() : ArrayUtil.asView(this.wS);
   }

   @Override
   public List getMethodsAnnotations() {
      return this.UT == null ? Collections.emptyList() : ArrayUtil.asView(this.UT);
   }

   @Override
   public List getParametersAnnotations() {
      return this.E == null ? Collections.emptyList() : ArrayUtil.asView(this.E);
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

   public static bec pC(com.pnfsoftware.jeb.corei.parsers.dex.vi var0, bgb var1, byte[] var2, int var3) {
      int var4 = DexUtil.bytearrayULEInt32ToInt(var2, var3);
      int var5 = DexUtil.bytearrayULEInt32ToInt(var2, var3 + 4);
      int var6 = DexUtil.bytearrayULEInt32ToInt(var2, var3 + 8);
      int var7 = DexUtil.bytearrayULEInt32ToInt(var2, var3 + 12);
      bec var8 = new bec();
      if (var4 > 0) {
         var8.kS = beb.pC(var0, var1, var2, var4);
      }

      int var9 = var3 + 16;
      var8.wS = new bed[var5];

      for (int var10 = 0; var10 < var5; var10++) {
         int var11 = DexUtil.bytearrayULEInt32ToInt(var2, var9);
         var11 = var1.wS(var11);
         int var12 = DexUtil.bytearrayULEInt32ToInt(var2, var9 + 4);
         beb var13 = beb.pC(var0, var1, var2, var12);
         var8.wS[var10] = new bed(var11, var13);
         var9 += 8;
      }

      var8.UT = new bee[var6];

      for (int var18 = 0; var18 < var6; var18++) {
         int var21 = DexUtil.bytearrayULEInt32ToInt(var2, var9);
         var21 = var1.UT(var21);
         int var25 = DexUtil.bytearrayULEInt32ToInt(var2, var9 + 4);
         beb var27 = beb.pC(var0, var1, var2, var25);
         var8.UT[var18] = new bee(var21, var27);
         var9 += 8;
      }

      var8.E = new bef[var7];

      for (int var19 = 0; var19 < var7; var19++) {
         int var23 = DexUtil.bytearrayULEInt32ToInt(var2, var9);
         var23 = var1.UT(var23);
         int var26 = DexUtil.bytearrayULEInt32ToInt(var2, var9 + 4);
         int var28 = DexUtil.bytearrayULEInt32ToInt(var2, var26);
         beb[] var14 = new beb[var28];

         for (int var15 = 0; var15 < var28; var15++) {
            int var16 = DexUtil.bytearrayULEInt32ToInt(var2, var26 + 4 + 4 * var15);
            beb var17 = beb.pC(var0, var1, var2, var16);
            var14[var15] = var17;
         }

         var8.E[var19] = new bef(var23, var14);
         var9 += 8;
      }

      return var8;
   }
}
