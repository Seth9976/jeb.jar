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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class bid implements IJavaMethodFactory {
   private static final ILogger pC = GlobalLog.getLogger(bid.class);
   @SerId(1)
   private IDexUnit A;
   @SerId(2)
   private IJavaTypeFactory kS;
   @SerId(3)
   private biy wS;
   @SerId(4)
   private bhz UT;
   @SerId(6)
   private Map E;
   @SerId(7)
   private Boolean sY;
   @SerId(8)
   private Integer ys;
   @SerId(9)
   private com.pnfsoftware.jeb.corei.parsers.dexdec.Ws ld;

   @SerCustomInitPostGraph
   private void UT() {
      if (this.E instanceof HashMap) {
         this.E = new ConcurrentHashMap(this.E);
      }

      if (this.ld == null) {
         this.ld = (com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)DecompilerHelper.getDecompiler(this.A, false);
         if (this.ld != null) {
            this.E.values().forEach(var1 -> bhz.pC(var1, this.ld));
         }
      }
   }

   public bid(com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var1, IJavaTypeFactory var2, biy var3, bhz var4) {
      this.ld = var1;
      this.A = var1.ld();
      this.kS = var2;
      this.wS = var3;
      this.UT = var4;
      this.E = new ConcurrentHashMap();
   }

   public boolean pC() {
      return this.sY != null ? this.sY : this.ld.wS();
   }

   public int A() {
      return this.ys != null ? this.ys : this.ld.ys();
   }

   public bjr pC(String var1) {
      IDexMethod var2 = this.A.getMethod(var1);
      return var2 == null ? null : (bjr)this.E.get(var2.getIndex());
   }

   public bjr A(String var1) {
      IDexMethod var2 = this.A.getMethod(var1);
      return var2 == null ? null : this.pC(var2.getIndex());
   }

   private bjr pC(int var1) {
      if (var1 < 0) {
         throw new RuntimeException("Illegal method index");
      } else {
         bjr var2 = (bjr)this.E.get(var1);
         if (var2 != null) {
            return var2;
         } else {
            synchronized (this) {
               var2 = (bjr)this.E.get(var1);
               if (var2 != null) {
                  return var2;
               } else {
                  IDexMethod var4 = this.A.getMethod(var1);
                  IDexMethodData var5 = var4.getData();
                  String var6 = var4.getClassType().getSignature(false);
                  IJavaType var7 = this.kS.createType(var6);
                  String var8 = var4.getName(false);
                  String var9 = var4.getSignature(false);
                  int var11 = 0;
                  bjg var12 = null;
                  bjt var13 = null;
                  IJavaType var14 = null;
                  List var15 = null;
                  List var16 = null;
                  boolean var10;
                  if (var5 == null) {
                     var10 = true;
                     if (Booleans.isTrue(this.ld.A(var9))) {
                        var11 |= 8;
                     }
                  } else {
                     var10 = false;
                     var11 = bhz.pC(DexUtil.convertDexFlagsToCodeFlags(var5.getAccessFlags()));
                     IDexClass var17 = this.A.getClass(var6);
                     if (var17 != null && var17.isNonStaticMemberClass()) {
                        var11 |= 1048576;
                     }

                     var12 = new bjg(this.A());
                     var13 = new bjt();
                     var14 = JavaTypeUtil.parseFullPrototype(var4.getPrototype(), this.kS)[0];
                     if (var5.getCodeItem() == null && !var5.isAbstract() && !var5.isNative()) {
                        throw new RuntimeException("Unexpected method: no bytecode, but method is neither abstract nor native");
                     }

                     if (var5.getCodeItem() == null) {
                        var15 = this.A(var4, var12);
                     } else {
                        var15 = this.pC(var4, var12);
                     }

                     var16 = this.pC(var5);
                  }

                  var2 = new bjr(var1, this.kS, this.wS, var7, var8, var9, var10, var11, var14, var15, var12, var13, var16, new bir());
                  var2.pC(this.ld);
                  if (!var10) {
                     for (IDexClass var18 : DexUtil.getMemberClasses(this.A, var4)) {
                        if (var18.isAnonymousClass()) {
                           var2.kS(var18.getSignature(false));
                        } else {
                           var2.A(var18.getSignature(false));
                        }
                     }
                  }

                  this.E.put(var1, var2);
                  return var2;
               }
            }
         }
      }
   }

   private List pC(IDexMethod var1, bjg var2) {
      IDexMethodData var3 = var1.getData();
      int var4 = var1.getIndex();
      ArrayList var5 = new ArrayList();
      SortedMap var6 = JavaTypeUtil.parseMethodParameters(this.A, var1, this.kS);
      var6.remove(-1);
      List var7 = null;
      if (this.pC()) {
         var7 = DexUtil.getDebugParameterNames(this.A, var3);
         if (var7 == null) {
            var7 = DexUtil.getAnnotatedParameterNames(this.A, var3);
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
         IJavaDefinition var16 = var2.pC(-1, var12, var15, var11, var13, var14, var4, -2);
         var5.add(var16);
         var9++;
      }

      return var5;
   }

   private List A(IDexMethod var1, bjg var2) {
      ArrayList var3 = new ArrayList();
      IDexMethodData var4 = var1.getData();
      int var5 = var4.getMethodIndex();
      IDexPrototype var6 = this.A.getMethod(var5).getPrototype();
      IJavaType[] var7 = JavaTypeUtil.parseFullPrototype(var6, this.kS);
      int var8 = 0;
      if (!var4.isStatic()) {
         String var10 = this.A.getMethod(var4.getMethodIndex()).getClassTypeSignature(false);
         IJavaType var11 = this.kS.parseType(var10);
         IJavaDefinition var9 = var2.pC(-1, var11, var8, var8, "this", null, var5, -2);
         var3.add(var9);
         var8++;
      }

      for (int var14 = 1; var14 < var7.length; var14++) {
         IJavaType var15 = var7[var14];
         String var12 = "arg" + var8;
         IJavaDefinition var13 = var2.pC(-1, var15, var8, var8, var12, null, var5, -2);
         var3.add(var13);
         var8++;
      }

      return var3;
   }

   private List pC(IDexMethodData var1) {
      ArrayList var2 = new ArrayList();
      int[] var3 = var1.getExceptionTypeIndices();
      if (var3 != null) {
         for (int var7 : var3) {
            IJavaType var8 = this.kS.createType(this.A.getType(var7).getSignature(false));
            var2.add(var8);
         }
      }

      return var2;
   }

   public boolean kS(String var1) {
      bjr var2 = this.pC(var1);
      return var2 != null && this.pC(var2);
   }

   public boolean pC(bjr var1) {
      int var2 = var1.kS();
      bjr var3 = (bjr)this.E.remove(var2);
      return var3 != null;
   }

   public int kS() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.E.entrySet()) {
         if ((((bjr)var3.getValue()).getFlags() & 256) == 0) {
            var1.add((Integer)var3.getKey());
         }
      }

      var1.forEach(var1x -> this.E.remove(var1x));
      return var1.size();
   }

   public int wS() {
      ArrayList var1 = new ArrayList();

      for (Entry var3 : this.E.entrySet()) {
         int var4 = (Integer)var3.getKey();
         bjr var5 = (bjr)var3.getValue();
         if (this.A.getMethod(var4).isInternal() && var5.isExternal()) {
            var1.add(var4);
         }
      }

      var1.forEach(var1x -> this.E.remove(var1x));
      return var1.size();
   }

   @Override
   public String toString() {
      return this.E.toString();
   }
}
