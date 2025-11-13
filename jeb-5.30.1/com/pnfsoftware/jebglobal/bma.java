package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPrototype;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethodFactory;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Booleans;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class bma implements IJavaMethodFactory {
   private static final ILogger q = GlobalLog.getLogger(bma.class);
   @SerId(1)
   private IDexUnit RF;
   @SerId(2)
   private IJavaTypeFactory xK;
   @SerId(3)
   private bmv Dw;
   @SerId(4)
   private blw Uv;
   @SerId(6)
   private Map oW;
   @SerId(7)
   private Boolean gO;
   @SerId(8)
   private Integer nf;
   @SerId(9)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.ej gP;

   @SerCustomInitPostGraph
   private void nf() {
      if (this.oW instanceof HashMap) {
         this.oW = new ConcurrentHashMap(this.oW);
      }

      if (this.gP == null) {
         this.gP = (com.pnfsoftware.jeb.corei.parsers.dexdec.ej)DecompilerHelper.getDecompiler(this.RF, false);
         if (this.gP != null) {
            this.oW.values().forEach(var1 -> blw.q(var1, this.gP));
         }
      }
   }

   public bma(com.pnfsoftware.jeb.corei.parsers.dexdec.ej var1, IJavaTypeFactory var2, bmv var3, blw var4) {
      this.gP = var1;
      this.RF = var1.TX();
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4;
      this.oW = new ConcurrentHashMap();
   }

   public boolean q() {
      return this.gO != null ? this.gO : this.gP.Dw();
   }

   public int RF() {
      return this.nf != null ? this.nf : this.gP.xW();
   }

   public bno q(String var1) {
      IDexMethod var2 = this.RF.getMethod(var1);
      return var2 == null ? null : (bno)this.oW.get(var2.getIndex());
   }

   public bno RF(String var1) {
      IDexMethod var2 = this.RF.getMethod(var1);
      return var2 == null ? null : this.q(var2.getIndex());
   }

   private bno q(int var1) {
      if (var1 < 0) {
         throw new RuntimeException("Illegal method index");
      } else {
         bno var2 = (bno)this.oW.get(var1);
         if (var2 != null) {
            return var2;
         } else {
            synchronized (this) {
               var2 = (bno)this.oW.get(var1);
               if (var2 != null) {
                  return var2;
               } else {
                  IDexMethod var4 = this.RF.getMethod(var1);
                  IDexMethodData var5 = var4.getData();
                  String var6 = var4.getClassType().getSignature(false);
                  IJavaType var7 = this.xK.createType(var6);
                  String var8 = var4.getName(false);
                  String var9 = var4.getSignature(false);
                  int var11 = 0;
                  bnd var12 = null;
                  bnq var13 = null;
                  IJavaType var14 = null;
                  List var15 = null;
                  List var16 = null;
                  boolean var10;
                  if (var5 == null) {
                     var10 = true;
                     if (Booleans.isTrue(this.gP.RF(var9))) {
                        var11 |= 8;
                     }
                  } else {
                     var10 = false;
                     var11 = blw.q(DexUtil.convertDexFlagsToCodeFlags(var5.getAccessFlags()));
                     IDexClass var17 = this.RF.getClass(var6);
                     if (var17 != null && var17.isNonStaticMemberClass()) {
                        var11 |= 1048576;
                     }

                     var12 = new bnd(this.RF());
                     var13 = new bnq();
                     var14 = JavaTypeUtil.parseFullPrototype(var4.getPrototype(), this.xK)[0];
                     if (var5.getCodeItem() == null && !var5.isAbstract() && !var5.isNative()) {
                        throw new RuntimeException("Unexpected method: no bytecode, but method is neither abstract nor native");
                     }

                     if (var5.getCodeItem() == null) {
                        var15 = this.RF(var4, var12);
                     } else {
                        var15 = this.q(var4, var12);
                     }

                     var16 = this.q(var5);
                  }

                  var2 = new bno(var1, this.xK, this.Dw, var7, var8, var9, var10, var11, var14, var15, var12, var13, var16, new bmo());
                  var2.q(this.gP);
                  if (!var10) {
                     for (IDexClass var18 : DexUtil.getMemberClasses(this.RF, var4)) {
                        if (var18.isAnonymousClass()) {
                           var2.xK(var18.getSignature(false));
                        } else {
                           var2.RF(var18.getSignature(false));
                        }
                     }
                  }

                  this.oW.put(var1, var2);
                  return var2;
               }
            }
         }
      }
   }

   private List q(IDexMethod var1, bnd var2) {
      IDexMethodData var3 = var1.getData();
      int var4 = var1.getIndex();
      ArrayList var5 = new ArrayList();
      SortedMap var6 = JavaTypeUtil.parseMethodParameters(this.RF, var1, this.xK);
      var6.remove(-1);
      List var7 = null;
      if (this.q()) {
         var7 = DexUtil.getDebugParameterNames(this.RF, var3);
         if (var7 == null) {
            var7 = DexUtil.getAnnotatedParameterNames(this.RF, var3);
         }
      }

      int var8 = 0;
      int var9 = 0;

      for (int var11 : var6.keySet()) {
         IJavaType var12 = (IJavaType)var6.get(var11);
         String var13 = null;
         String var14 = null;
         if (var9 == 0 && !var3.isStatic()) {
            var13 = "this";
         } else if (var7 != null) {
            if (var8 < var7.size()) {
               var14 = (String)var7.get(var8);
            }

            var8++;
         }

         int var15 = DUtil.createRegisterVarId(var11, var12.isDoubleSlot());
         IJavaDefinition var16 = var2.q(-1, var12, var15, var11, var13, var14, var4, -2);
         var5.add(var16);
         var9++;
      }

      return var5;
   }

   private List RF(IDexMethod var1, bnd var2) {
      ArrayList var3 = new ArrayList();
      IDexMethodData var4 = var1.getData();
      int var5 = var4.getMethodIndex();
      IDexPrototype var6 = this.RF.getMethod(var5).getPrototype();
      IJavaType[] var7 = JavaTypeUtil.parseFullPrototype(var6, this.xK);
      int var8 = 0;
      if (!var4.isStatic()) {
         String var10 = this.RF.getMethod(var4.getMethodIndex()).getClassTypeSignature(false);
         IJavaType var11 = this.xK.parseType(var10);
         IJavaDefinition var9 = var2.q(-1, var11, var8, var8, "this", null, var5, -2);
         var3.add(var9);
         var8++;
      }

      for (int var14 = 1; var14 < var7.length; var14++) {
         IJavaType var15 = var7[var14];
         String var12 = "arg" + var8;
         IJavaDefinition var13 = var2.q(-1, var15, var8, var8, var12, null, var5, -2);
         var3.add(var13);
         var8++;
      }

      return var3;
   }

   private List q(IDexMethodData var1) {
      ArrayList var2 = new ArrayList();
      int[] var3 = var1.getExceptionTypeIndices();
      if (var3 != null) {
         for (int var7 : var3) {
            IJavaType var8 = this.xK.createType(this.RF.getType(var7).getSignature(false));
            var2.add(var8);
         }
      }

      return var2;
   }

   public void xK() {
      this.oW.clear();
   }

   public int Dw() {
      return this.oW.size();
   }

   public boolean xK(String var1) {
      bno var2 = this.q(var1);
      return var2 != null && this.q(var2);
   }

   public boolean q(bno var1) {
      int var2 = var1.Dw();
      bno var3 = (bno)this.oW.remove(var2);
      return var3 != null;
   }

   public int Uv() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.oW.entrySet()) {
         if ((((bno)var3.getValue()).getFlags() & 256) == 0) {
            var1.add((Integer)var3.getKey());
         }
      }

      var1.forEach(var1x -> this.oW.remove(var1x));
      return var1.size();
   }

   public Collection oW() {
      return Collections.unmodifiableCollection(this.oW.values());
   }

   public int gO() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.oW.entrySet()) {
         int var4 = (Integer)var3.getKey();
         bno var5 = (bno)var3.getValue();
         if (this.RF.getMethod(var4).isInternal() && var5.isExternal()) {
            var1.add(var4);
         }
      }

      var1.forEach(var1x -> this.oW.remove(var1x));
      return var1.size();
   }

   @Override
   public String toString() {
      return this.oW.toString();
   }
}
