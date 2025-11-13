package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.IJLSAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.IJLSField;
import com.pnfsoftware.jeb.core.units.code.android.IJLSMethod;
import com.pnfsoftware.jeb.core.units.code.android.IJLSType;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.JvmUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexAnnotation;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexFieldData;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexValue;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuClass;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInvokeInfo;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.base.JavaUtil;
import com.pnfsoftware.jeb.util.base.Wrapper;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class bxy implements IDEmuClass {
   private bye RF;
   String q;
   private Map xK;
   private Class Dw;
   private IDexClass Uv;
   private bxy.eo oW;
   private Map gO = new HashMap();

   bxy(bye var1, String var2, Class var3) {
      this.RF = var1;
      this.q = var2;
      this.Dw = var3;
   }

   bxy(bye var1, String var2, IDexClass var3) {
      this.RF = var1;
      this.q = var2;
      this.Uv = var3;
   }

   public boolean q() {
      return this.Uv().q();
   }

   @Override
   public boolean isInternal() {
      return !this.q();
   }

   public String RF() {
      return this.q.substring(this.q.lastIndexOf(47) + 1, this.q.length() - 1);
   }

   @Override
   public String getFullName() {
      return this.q;
   }

   @Override
   public boolean isInitialized() {
      return this.q() || this.xK != null;
   }

   public void xK() throws DexDecEvaluationException {
      if (!this.isInitialized()) {
         this.xK = new HashMap();
         Assert.a(this.Uv != null, "Expected a dex class for internal type: " + this.q);

         try {
            for (IDexField var2 : this.Uv.getFields()) {
               if (var2.getData().isStatic()) {
                  IDexFieldData var3 = var2.getData();
                  IDexValue var4 = DexUtil.getStaticFieldInitializer(this.Uv, var3);
                  String var5 = var2.getFieldTypeSignature(false);
                  IJavaType var6 = this.RF.oW().createType(var5);
                  IDImm var7;
                  if (var4 != null) {
                     var7 = this.RF.q(var4, var6);
                  } else {
                     var7 = this.RF.nf(var5);
                  }

                  this.q(var2.getName(false), var6.getName(), var7, true);
               }
            }

            for (IDexMethod var10 : this.Uv.getMethods()) {
               if (var10.getName(false).equals("<clinit>")) {
                  this.RF.q(var10, Collections.emptyList());
                  break;
               }
            }
         } catch (Exception var8) {
            if (this.RF.isStrictClassInit()) {
               this.xK = null;
               this.RF.oW.remove(this.q);
               this.RF.gO.add(this.q);
            }

            throw var8;
         }
      }
   }

   public Class Dw() throws DexDecEvaluationException {
      if (this.Dw == null) {
         cad var1 = this.RF.za();
         bxy.eo var2 = this.Uv();
         this.Dw = var1.q(var2);
      }

      return this.Dw;
   }

   public bxy.eo Uv() {
      if (this.oW == null) {
         bxy.eo var1 = new bxy.eo();
         StringBuilder var2 = new StringBuilder();
         String var3 = this.q;
         ArrayDeque var4 = new ArrayDeque();
         int var5 = 0;
         IDexClass var6 = null;

         while (true) {
            IDexClass var7 = this.RF.getDex().getClass(var3);
            if (var7 == null || var7 == var6) {
               var1.RF = var3;
               var1.Dw = var2.toString();
               if (var1.Dw.isEmpty()) {
                  var1.xK = Collections.emptyList();
               } else {
                  var1.xK = new ArrayList();
                  HashSet var8 = new HashSet();

                  while (!var4.isEmpty()) {
                     String var9 = (String)var4.remove();
                     if (var8.add(var9)) {
                        var7 = this.RF.getDex().getClass(var9);
                        if (var7 == null) {
                           var1.xK.add(var9);
                        } else {
                           var4.addAll(Arrays.asList(var7.getInterfaceSignatures(false)));
                        }
                     }
                  }
               }

               this.oW = var1;
               break;
            }

            var2.append(var3).append("#");
            var6 = var7;
            var3 = var7.getSupertypeSignature(false);
            var4.addAll(Arrays.asList(var7.getInterfaceSignatures(false)));
            if (++var5 >= 1000) {
               throw new RuntimeException();
            }
         }
      }

      return this.oW;
   }

   public IDImm q(String var1, String var2) throws DexDecEvaluationException {
      String var3 = this.q + "->" + var1 + ":" + var2;
      if (this.q()) {
         this.RF.EB();
         cad var8 = this.RF.za();
         return var8.q(var3, null);
      } else {
         this.RF.jq();
         if (!this.isInitialized()) {
            this.xK();
         }

         String var4 = var1 + ":" + var2;
         this.RF.Bu();
         if (this.RF.qa().q()) {
            long var5 = bye.io();
            Wrapper var7 = this.RF.qa().getField(var5, var3, null);
            if (var7 != null) {
               return (IDImm)var7.get();
            }
         }

         return (IDImm)this.xK.get(var4);
      }
   }

   public boolean q(String var1, String var2, IDImm var3) throws DexDecEvaluationException {
      return this.q(var1, var2, var3, false);
   }

   private boolean q(String var1, String var2, IDImm var3, boolean var4) throws DexDecEvaluationException {
      String var5 = this.q + "->" + var1 + ":" + var2;
      if (this.q()) {
         this.RF.Xo();
         cad var14 = this.RF.za();
         var14.q(var5, null, var3);
         return true;
      } else {
         this.RF.ui();
         if (!var4 && !this.isInitialized()) {
            this.xK();
         }

         String var6 = var1 + ":" + var2;
         if (!var4 && !this.xK.containsKey(var6)) {
            return false;
         } else {
            int var7 = 0;
            if (!var4) {
               boolean var8 = false;

               for (IDEmuFrame var11 : this.RF.getCurrentContext().getFrames()) {
                  String var12 = var11.getMethodSignature();
                  JvmMethodSig var13 = JvmMethodSig.parse(var12);
                  if (var13.mname.equals("<clinit>")) {
                     var8 = true;
                     break;
                  }
               }

               var7 = var8 ? 1 : 2;
            }

            this.RF.Dw(var7);
            boolean var15 = false;
            if (this.RF.qa().q()) {
               long var16 = bye.io();
               IDImm[] var17 = new IDImm[]{var3};
               Boolean var18 = this.RF.qa().setField(var16, var5, null, var17);
               if (var18 != null) {
                  if (var18) {
                     var15 = true;
                  } else {
                     var3 = var17[0];
                  }
               }
            }

            if (var15) {
               return true;
            } else {
               this.xK.put(var6, var3);
               return true;
            }
         }
      }
   }

   public boolean q(IDExpression var1, IDImm var2) throws DexDecEvaluationException {
      if (!(var1 instanceof IDInvokeInfo)) {
         throw new RuntimeException();
      } else if (var2 == null) {
         return false;
      } else {
         Object var3;
         try {
            if (!var2.isNonNullRef()) {
               return false;
            }

            var3 = this.RF.getObject(var2);
         } catch (DexDecEvaluationException var4) {
            return false;
         }

         if (!(var3 instanceof String)) {
            if (!(var3 instanceof byte[])) {
               return false;
            }

            var3 = Arrays.copyOf((byte[])var3, ((byte[])var3).length);
         }

         this.gO.put(var1.getPhysicalOffset(), new Couple(var1, var3));
         return true;
      }
   }

   public Couple q(int var1) {
      return (Couple)this.gO.get(var1);
   }

   @Override
   public String toString() {
      return Strings.ff("%s (%s)", this.q, this.q() ? "EXTERNAL" : "INTERNAL");
   }

   public Object q(String var1, List var2) throws Exception {
      switch (cvm.xK(var1)) {
         case -1986661425:
            String var24 = (String)var2.get(0);
            String var36 = this.RF.q(this.q, var24, null, 1, -1);
            if (var36 == null) {
               throw new NoSuchFieldException();
            }

            IDexField var48 = this.RF.getDex().getField(var36);
            int var61 = var48 != null && var48.isInternal() ? var48.getData().getAccessFlags() : 0;
            return new bxw(var36, var61);
         case -1978215926:
            List var23 = this.RF.getTypeAdapter().getTypeAnnotations(this.q);
            if (var23 != null) {
               ArrayList var35 = new ArrayList();

               for (IJLSAnnotation var60 : var23) {
                  var35.add(new bxx(var60.getType()));
               }

               return var35.toArray(new bxx[0]);
            }
            break;
         case -1841955043:
            String var22 = (String)var2.get(0);
            String var34 = this.RF.q(this.q, var22, null, 0, 0);
            if (var34 == null) {
               throw new NoSuchFieldException();
            }

            IDexField var46 = this.RF.getDex().getField(var34);
            int var59 = var46 != null && var46.isInternal() ? var46.getData().getAccessFlags() : 0;
            return new bxw(var34, var59);
         case -1576325035:
            String var21 = (String)var2.get(0);
            Class[] var33 = (Class[])var2.get(1);
            ArrayList var45 = new ArrayList();
            if (var33 != null) {
               for (Class var80 : var33) {
                  var45.add(this.q(var80));
               }
            }

            String var58 = this.RF.q(this.q, var21, var45, 0, 0);
            if (var58 == null) {
               throw new NoSuchMethodException();
            }

            IDexMethod var69 = this.RF.getDex().getMethod(var58);
            int var77 = var69 != null && var69.isInternal() ? var69.getData().getAccessFlags() : 0;
            return new bxw(var58, var77);
         case -1350810624:
            List var20 = q(this.RF.getTypeAdapter(), this.q);
            if (var20 != null) {
               var20.sort((var0, var1x) -> ((String)var0.getFirst()).compareTo((String)var1x.getFirst()));
               ArrayList var32 = new ArrayList();

               for (Couple var56 : var20) {
                  var32.add(new bxw((String)var56.getFirst(), (Integer)var56.getSecond()));
               }

               return var32.toArray(new bxw[0]);
            }
            break;
         case -1171249475:
            return this.Dw.getSuperclass();
         case -1040008539:
            return this.RF.nf();
         case -914716952:
            IDexUnit var19 = this.RF.getDex();
            IDexClass var31 = this.RF.getDex().getClass(this.q);
            if (var31.isAnonymousClass()
               || DexUtil.findAnnotation(var19, var31.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/InnerClass;", 2) == null) {
               return null;
            }

            IDexAnnotation var43 = DexUtil.findAnnotation(var19, var31.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/EnclosingClass;", 2);
            if (var43 == null) {
               return null;
            }

            int var55 = DexUtil.findAnnotationElement(var19, var43, "value").getTypeIndex();
            String var67 = var19.getType(var55).getSignature(false);
            if (var67 != null) {
               bxy var75 = this.RF.Uv(var67);
               if (var75 != null) {
                  return var75.Dw;
               }
            }
            break;
         case -885104571:
            IDexClass var18 = this.RF.getDex().getClass(this.q);
            if (DexUtil.findAnnotation(this.RF.getDex(), var18.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/InnerClass;", 2) != null
               && DexUtil.findAnnotation(this.RF.getDex(), var18.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/EnclosingMethod;", 2)
                  != null) {
               return !var18.isAnonymousClass();
            }

            return false;
         case -796489990:
            String var17 = "<init>";
            Class[] var30 = (Class[])var2.get(0);
            ArrayList var42 = new ArrayList();

            for (Class var79 : var30) {
               var42.add(this.q(var79));
            }

            String var54 = this.RF.q(this.q, var17, var42, 0, 0);
            if (var54 == null) {
               throw new NoSuchMethodException();
            }

            IDexMethod var66 = this.RF.getDex().getMethod(var54);
            int var74 = var66 != null && var66.isInternal() ? var66.getData().getAccessFlags() : 0;
            return new bxw(var54, var74);
         case -535673496:
            return JavaUtil.extractSimpleName(this.q, false);
         case -308480361:
            ArrayList var16 = new ArrayList();

            for (IJLSField var41 : this.RF.getTypeAdapter().getFields(this.q)) {
               var16.add(new bxw(this.q + "->" + var41.getSignature(), var41.getAccessFlags()));
            }

            var16.sort((var0, var1x) -> var0.Dw().compareTo(var1x.Dw()));
            return var16.toArray(new bxw[0]);
         case -52464707:
            String var15 = "<init>";
            Class[] var28 = (Class[])var2.get(0);
            ArrayList var40 = new ArrayList();

            for (Class var78 : var28) {
               var40.add(this.q(var78));
            }

            String var52 = this.RF.q(this.q, var15, var40, 1, -1);
            if (var52 == null) {
               throw new NoSuchMethodException();
            }

            IDexMethod var64 = this.RF.getDex().getMethod(var52);
            int var72 = var64 != null && var64.isInternal() ? var64.getData().getAccessFlags() : 0;
            return new bxw(var52, var72);
         case 530254156:
            ArrayList var14 = new ArrayList();

            for (IJLSMethod var39 : this.RF.getTypeAdapter().getMethods(this.q)) {
               if (!Strings.isContainedIn(var39.getName(), "<init>", "<clinit>")) {
                  var14.add(new bxw(this.q + "->" + var39.getSignature(), var39.getAccessFlags()));
               }
            }

            var14.sort((var0, var1x) -> var0.Uv().compareTo(var1x.Uv()));
            return var14.toArray(new bxw[var14.size()]);
         case 923317504:
            List var13 = bkm.RF(this.RF.getTypeAdapter(), this.q);
            if (var13 != null) {
               var13.sort(null);
               ArrayList var26 = new ArrayList();

               for (String var50 : var13) {
                  var26.add(new bxw(var50));
               }

               return var26.toArray(new bxw[0]);
            }
            break;
         case 1175387769:
            IDexClass var12 = this.RF.getDex().getClass(this.q);
            if (DexUtil.findAnnotation(this.RF.getDex(), var12.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/InnerClass;", 2) != null
               && DexUtil.findAnnotation(this.RF.getDex(), var12.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/EnclosingMethod;", 2)
                  != null) {
               return var12.isAnonymousClass();
            }

            return false;
         case 1567407707:
            if (this.q.startsWith("L")) {
               return this.q.substring(1, this.q.length() - 1).replace('/', '.');
            }
            break;
         case 1741280055:
            String var11 = (String)var2.get(0);
            Class[] var25 = (Class[])var2.get(1);
            ArrayList var37 = new ArrayList();
            if (var25 != null) {
               for (Class var9 : var25) {
                  var37.add(this.q(var9));
               }
            }

            String var49 = this.RF.q(this.q, var11, var37, 1, -1);
            if (var49 == null) {
               throw new NoSuchMethodException();
            }

            IDexMethod var62 = this.RF.getDex().getMethod(var49);
            int var70 = var62 != null && var62.isInternal() ? var62.getData().getAccessFlags() : 0;
            return new bxw(var49, var70);
         case 1857178773:
            IDexClass var10 = this.RF.getDex().getClass(this.q);
            if (DexUtil.findAnnotation(this.RF.getDex(), var10.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/InnerClass;", 2) != null
               && DexUtil.findAnnotation(this.RF.getDex(), var10.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/EnclosingClass;", 2)
                  != null) {
               return !var10.isAnonymousClass();
            }

            return false;
         case 2057618771:
            ArrayList var3 = new ArrayList();

            for (IJLSMethod var5 : this.RF.getTypeAdapter().getMethods(this.q)) {
               if (var5.getName().equals("<init>")) {
                  var3.add(new bxw(this.q + "->" + var5.getSignature(), var5.getAccessFlags()));
               }
            }

            var3.sort((var0, var1x) -> var0.Dw().compareTo(var1x.Dw()));
            return var3.toArray(new bxw[0]);
      }

      throw new DexDecEvaluationException(
         cvm.q(new byte[]{-100, 59, 29, 6, 5, 0, 31, 29, 6, 17, 1, 68, 82, 23, 3, 73, 70, 7, 26, 84, 67, 2, 18, 22, 95, 26}, 1, 201) + var1
      );
   }

   private String q(Class var1) {
      if (var1.isArray()) {
         int var2 = 0;

         Class var3;
         for (var3 = var1; var3.isArray(); var2++) {
            var3 = var3.getComponentType();
         }

         if (cad.xK(var3)) {
            return Strings.generate('[', var2) + this.RF.RF(var3).q;
         }
      }

      return cad.xK(var1) ? this.RF.RF(var1).q : JvmUtil.generateTypeSig(var1);
   }

   public static List q(IJLSTypeAdapter var0, String var1) {
      ArrayList var2 = new ArrayList();
      switch (var1) {
         case "Z":
         case "B":
         case "C":
         case "S":
         case "I":
         case "L":
         case "F":
         case "D":
         case "V":
            return var2;
         default:
            boolean var3 = (var0.getType(var1).getAccessFlags() & 512) != 0;
            ArrayDeque var15 = new ArrayDeque();
            var15.add(var1);
            HashSet var5 = new HashSet();
            HashSet var6 = new HashSet();
            int var7 = 0;

            while (!var15.isEmpty()) {
               String var8 = (String)var15.remove();
               if (var5.add(var8)) {
                  IJLSType var9 = var0.getType(var8);
                  if (var9 == null) {
                     return null;
                  }

                  for (IJLSMethod var11 : var0.getMethods(var8)) {
                     if (!Strings.isContainedIn(var11.getName(), "<init>", "<clinit>")) {
                        int var12 = var11.getAccessFlags();
                        if ((var12 & 1) != 0
                           && (!var3 || !var8.equals("Ljava/lang/Object;"))
                           && (var7 < 1 || (var12 & 8) == 0 || (var9.getAccessFlags() & 512) == 0)) {
                           String var13 = var11.getSignature();
                           String var14 = var13.substring(0, var13.indexOf(41) + 1);
                           if (var6.add(var14)) {
                              var2.add(new Couple(var8 + "->" + var13, var12));
                           }
                        }
                     }
                  }

                  var15.addAll(var0.getParentTypes(var8));
                  var7++;
               }
            }

            return var2;
      }
   }

   public static class eo {
      private String RF;
      private List xK;
      private String Dw;
      public boolean q;

      public boolean q() {
         return this.Dw.isEmpty();
      }

      public String RF() {
         return this.RF;
      }

      public List xK() {
         return this.xK;
      }

      public String Dw() {
         return this.Dw;
      }

      @Override
      public String toString() {
         return this.q() ? this.RF : this.RF + "__" + this.Dw;
      }
   }
}
