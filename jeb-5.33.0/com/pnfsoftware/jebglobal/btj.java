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

public class btj implements IDEmuClass {
   private btp A;
   String pC;
   private Map kS;
   private Class wS;
   private IDexClass UT;
   private btj.Av E;
   private Map sY = new HashMap();

   btj(btp var1, String var2, Class var3) {
      this.A = var1;
      this.pC = var2;
      this.wS = var3;
   }

   btj(btp var1, String var2, IDexClass var3) {
      this.A = var1;
      this.pC = var2;
      this.UT = var3;
   }

   public boolean pC() {
      return this.UT().pC();
   }

   @Override
   public boolean isInternal() {
      return !this.pC();
   }

   public String A() {
      return this.pC.substring(this.pC.lastIndexOf(47) + 1, this.pC.length() - 1);
   }

   @Override
   public String getFullName() {
      return this.pC;
   }

   @Override
   public boolean isInitialized() {
      return this.pC() || this.kS != null;
   }

   public void kS() throws DexDecEvaluationException {
      if (!this.isInitialized()) {
         this.kS = new HashMap();
         Assert.a(this.UT != null, "Expected a dex class for internal type: " + this.pC);

         try {
            for (IDexField var2 : this.UT.getFields()) {
               if (var2.getData().isStatic()) {
                  IDexFieldData var3 = var2.getData();
                  IDexValue var4 = DexUtil.getStaticFieldInitializer(this.UT, var3);
                  String var5 = var2.getFieldTypeSignature(false);
                  IJavaType var6 = this.A.E().createType(var5);
                  IDImm var7;
                  if (var4 != null) {
                     var7 = this.A.pC(var4, var6);
                  } else {
                     var7 = this.A.ys(var5);
                  }

                  this.pC(var2.getName(false), var6.getName(), var7, true);
               }
            }

            for (IDexMethod var10 : this.UT.getMethods()) {
               if (var10.getName(false).equals("<clinit>")) {
                  this.A.pC(var10, Collections.emptyList());
                  break;
               }
            }
         } catch (Exception var8) {
            if (this.A.isStrictClassInit()) {
               this.kS = null;
               this.A.E.remove(this.pC);
               this.A.sY.add(this.pC);
            }

            throw var8;
         }
      }
   }

   public Class wS() throws DexDecEvaluationException {
      if (this.wS == null) {
         bvo var1 = this.A.gp();
         btj.Av var2 = this.UT();
         this.wS = var1.pC(var2);
      }

      return this.wS;
   }

   public btj.Av UT() {
      if (this.E == null) {
         btj.Av var1 = new btj.Av();
         StringBuilder var2 = new StringBuilder();
         String var3 = this.pC;
         ArrayDeque var4 = new ArrayDeque();
         int var5 = 0;
         IDexClass var6 = null;

         while (true) {
            IDexClass var7 = this.A.getDex().getClass(var3);
            if (var7 == null || var7 == var6) {
               var1.A = var3;
               var1.wS = var2.toString();
               if (var1.wS.isEmpty()) {
                  var1.kS = Collections.emptyList();
               } else {
                  var1.kS = new ArrayList();
                  HashSet var8 = new HashSet();

                  while (!var4.isEmpty()) {
                     String var9 = (String)var4.remove();
                     if (var8.add(var9)) {
                        var7 = this.A.getDex().getClass(var9);
                        if (var7 == null) {
                           var1.kS.add(var9);
                        } else {
                           var4.addAll(Arrays.asList(var7.getInterfaceSignatures(false)));
                        }
                     }
                  }
               }

               this.E = var1;
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

      return this.E;
   }

   public IDImm pC(String var1, String var2) throws DexDecEvaluationException {
      String var3 = this.pC + "->" + var1 + ":" + var2;
      if (this.pC()) {
         this.A.UW();
         bvo var8 = this.A.gp();
         return var8.pC(var3, null);
      } else {
         this.A.sO();
         if (!this.isInitialized()) {
            this.kS();
         }

         String var4 = var1 + ":" + var2;
         this.A.cX();
         if (this.A.ED().pC()) {
            long var5 = btp.xC();
            Wrapper var7 = this.A.ED().getField(var5, var3, null);
            if (var7 != null) {
               return (IDImm)var7.get();
            }
         }

         return (IDImm)this.kS.get(var4);
      }
   }

   public boolean pC(String var1, String var2, IDImm var3) throws DexDecEvaluationException {
      return this.pC(var1, var2, var3, false);
   }

   private boolean pC(String var1, String var2, IDImm var3, boolean var4) throws DexDecEvaluationException {
      String var5 = this.pC + "->" + var1 + ":" + var2;
      if (this.pC()) {
         this.A.PR();
         bvo var14 = this.A.gp();
         var14.pC(var5, null, var3);
         return true;
      } else {
         this.A.os();
         if (!var4 && !this.isInitialized()) {
            this.kS();
         }

         String var6 = var1 + ":" + var2;
         if (!var4 && !this.kS.containsKey(var6)) {
            return false;
         } else {
            int var7 = 0;
            if (!var4) {
               boolean var8 = false;

               for (IDEmuFrame var11 : this.A.getCurrentContext().getFrames()) {
                  String var12 = var11.getMethodSignature();
                  JvmMethodSig var13 = JvmMethodSig.parse(var12);
                  if (var13.mname.equals("<clinit>")) {
                     var8 = true;
                     break;
                  }
               }

               var7 = var8 ? 1 : 2;
            }

            this.A.wS(var7);
            boolean var15 = false;
            if (this.A.ED().pC()) {
               long var16 = btp.xC();
               IDImm[] var17 = new IDImm[]{var3};
               Boolean var18 = this.A.ED().setField(var16, var5, null, var17);
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
               this.kS.put(var6, var3);
               return true;
            }
         }
      }
   }

   public boolean pC(IDExpression var1, IDImm var2) throws DexDecEvaluationException {
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

            var3 = this.A.getObject(var2);
         } catch (DexDecEvaluationException var4) {
            return false;
         }

         if (!(var3 instanceof String)) {
            if (!(var3 instanceof byte[])) {
               return false;
            }

            var3 = Arrays.copyOf((byte[])var3, ((byte[])var3).length);
         }

         this.sY.put(var1.getPhysicalOffset(), new Couple(var1, var3));
         return true;
      }
   }

   public Couple pC(int var1) {
      return (Couple)this.sY.get(var1);
   }

   @Override
   public String toString() {
      return Strings.ff("%s (%s)", this.pC, this.pC() ? "EXTERNAL" : "INTERNAL");
   }

   public Object pC(String var1, List var2) throws Exception {
      switch (ckx.kS(var1)) {
         case -1986661425:
            String var24 = (String)var2.get(0);
            String var36 = this.A.pC(this.pC, var24, null, 1, -1);
            if (var36 == null) {
               throw new NoSuchFieldException();
            }

            IDexField var48 = this.A.getDex().getField(var36);
            int var61 = var48 != null && var48.isInternal() ? var48.getData().getAccessFlags() : 0;
            return new bth(var36, var61);
         case -1978215926:
            List var23 = this.A.getTypeAdapter().getTypeAnnotations(this.pC);
            if (var23 != null) {
               ArrayList var35 = new ArrayList();

               for (IJLSAnnotation var60 : var23) {
                  var35.add(new bti(var60.getType()));
               }

               return var35.toArray(new bti[0]);
            }
            break;
         case -1841955043:
            String var22 = (String)var2.get(0);
            String var34 = this.A.pC(this.pC, var22, null, 0, 0);
            if (var34 == null) {
               throw new NoSuchFieldException();
            }

            IDexField var46 = this.A.getDex().getField(var34);
            int var59 = var46 != null && var46.isInternal() ? var46.getData().getAccessFlags() : 0;
            return new bth(var34, var59);
         case -1576325035:
            String var21 = (String)var2.get(0);
            Class[] var33 = (Class[])var2.get(1);
            ArrayList var45 = new ArrayList();
            if (var33 != null) {
               for (Class var80 : var33) {
                  var45.add(this.pC(var80));
               }
            }

            String var58 = this.A.pC(this.pC, var21, var45, 0, 0);
            if (var58 == null) {
               throw new NoSuchMethodException();
            }

            IDexMethod var69 = this.A.getDex().getMethod(var58);
            int var77 = var69 != null && var69.isInternal() ? var69.getData().getAccessFlags() : 0;
            return new bth(var58, var77);
         case -1350810624:
            List var20 = pC(this.A.getTypeAdapter(), this.pC);
            if (var20 != null) {
               var20.sort((var0, var1x) -> ((String)var0.getFirst()).compareTo((String)var1x.getFirst()));
               ArrayList var32 = new ArrayList();

               for (Couple var56 : var20) {
                  var32.add(new bth((String)var56.getFirst(), (Integer)var56.getSecond()));
               }

               return var32.toArray(new bth[0]);
            }
            break;
         case -1171249475:
            return this.wS.getSuperclass();
         case -1040008539:
            return this.A.ys();
         case -914716952:
            IDexUnit var19 = this.A.getDex();
            IDexClass var31 = this.A.getDex().getClass(this.pC);
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
               btj var75 = this.A.UT(var67);
               if (var75 != null) {
                  return var75.wS;
               }
            }
            break;
         case -885104571:
            IDexClass var18 = this.A.getDex().getClass(this.pC);
            if (DexUtil.findAnnotation(this.A.getDex(), var18.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/InnerClass;", 2) != null
               && DexUtil.findAnnotation(this.A.getDex(), var18.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/EnclosingMethod;", 2)
                  != null) {
               return !var18.isAnonymousClass();
            }

            return false;
         case -796489990:
            String var17 = "<init>";
            Class[] var30 = (Class[])var2.get(0);
            ArrayList var42 = new ArrayList();

            for (Class var79 : var30) {
               var42.add(this.pC(var79));
            }

            String var54 = this.A.pC(this.pC, var17, var42, 0, 0);
            if (var54 == null) {
               throw new NoSuchMethodException();
            }

            IDexMethod var66 = this.A.getDex().getMethod(var54);
            int var74 = var66 != null && var66.isInternal() ? var66.getData().getAccessFlags() : 0;
            return new bth(var54, var74);
         case -535673496:
            return JavaUtil.extractSimpleName(this.pC, false);
         case -308480361:
            ArrayList var16 = new ArrayList();

            for (IJLSField var41 : this.A.getTypeAdapter().getFields(this.pC)) {
               var16.add(new bth(this.pC + "->" + var41.getSignature(), var41.getAccessFlags()));
            }

            var16.sort((var0, var1x) -> var0.wS().compareTo(var1x.wS()));
            return var16.toArray(new bth[0]);
         case -52464707:
            String var15 = "<init>";
            Class[] var28 = (Class[])var2.get(0);
            ArrayList var40 = new ArrayList();

            for (Class var78 : var28) {
               var40.add(this.pC(var78));
            }

            String var52 = this.A.pC(this.pC, var15, var40, 1, -1);
            if (var52 == null) {
               throw new NoSuchMethodException();
            }

            IDexMethod var64 = this.A.getDex().getMethod(var52);
            int var72 = var64 != null && var64.isInternal() ? var64.getData().getAccessFlags() : 0;
            return new bth(var52, var72);
         case 530254156:
            ArrayList var14 = new ArrayList();

            for (IJLSMethod var39 : this.A.getTypeAdapter().getMethods(this.pC)) {
               if (!Strings.isContainedIn(var39.getName(), "<init>", "<clinit>")) {
                  var14.add(new bth(this.pC + "->" + var39.getSignature(), var39.getAccessFlags()));
               }
            }

            var14.sort((var0, var1x) -> var0.UT().compareTo(var1x.UT()));
            return var14.toArray(new bth[var14.size()]);
         case 923317504:
            List var13 = bgq.pC(this.A.getTypeAdapter(), this.pC);
            if (var13 != null) {
               var13.sort(null);
               ArrayList var26 = new ArrayList();

               for (String var50 : var13) {
                  var26.add(new bth(var50));
               }

               return var26.toArray(new bth[0]);
            }
            break;
         case 1175387769:
            IDexClass var12 = this.A.getDex().getClass(this.pC);
            if (DexUtil.findAnnotation(this.A.getDex(), var12.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/InnerClass;", 2) != null
               && DexUtil.findAnnotation(this.A.getDex(), var12.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/EnclosingMethod;", 2)
                  != null) {
               return var12.isAnonymousClass();
            }

            return false;
         case 1567407707:
            if (this.pC.startsWith("L")) {
               return this.pC.substring(1, this.pC.length() - 1).replace('/', '.');
            }
            break;
         case 1741280055:
            String var11 = (String)var2.get(0);
            Class[] var25 = (Class[])var2.get(1);
            ArrayList var37 = new ArrayList();
            if (var25 != null) {
               for (Class var9 : var25) {
                  var37.add(this.pC(var9));
               }
            }

            String var49 = this.A.pC(this.pC, var11, var37, 1, -1);
            if (var49 == null) {
               throw new NoSuchMethodException();
            }

            IDexMethod var62 = this.A.getDex().getMethod(var49);
            int var70 = var62 != null && var62.isInternal() ? var62.getData().getAccessFlags() : 0;
            return new bth(var49, var70);
         case 1857178773:
            IDexClass var10 = this.A.getDex().getClass(this.pC);
            if (DexUtil.findAnnotation(this.A.getDex(), var10.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/InnerClass;", 2) != null
               && DexUtil.findAnnotation(this.A.getDex(), var10.getAnnotationsDirectory().getClassAnnotations(), "Ldalvik/annotation/EnclosingClass;", 2)
                  != null) {
               return !var10.isAnonymousClass();
            }

            return false;
         case 2057618771:
            ArrayList var3 = new ArrayList();

            for (IJLSMethod var5 : this.A.getTypeAdapter().getMethods(this.pC)) {
               if (var5.getName().equals("<init>")) {
                  var3.add(new bth(this.pC + "->" + var5.getSignature(), var5.getAccessFlags()));
               }
            }

            var3.sort((var0, var1x) -> var0.wS().compareTo(var1x.wS()));
            return var3.toArray(new bth[0]);
      }

      throw new DexDecEvaluationException(
         ckx.pC(new byte[]{22, 1, 3, 12, 2, 25, 8, 26, 0, 69, 76, 67, 91, 69, 87, 22, 80, 93, 88, 0, 81, 81, 65, 80, 22, 0}, 2, 167) + var1
      );
   }

   private String pC(Class var1) {
      if (var1.isArray()) {
         int var2 = 0;

         Class var3;
         for (var3 = var1; var3.isArray(); var2++) {
            var3 = var3.getComponentType();
         }

         if (bvo.kS(var3)) {
            return Strings.generate('[', var2) + this.A.A(var3).pC;
         }
      }

      return bvo.kS(var1) ? this.A.A(var1).pC : JvmUtil.generateTypeSig(var1);
   }

   public static List pC(IJLSTypeAdapter var0, String var1) {
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

   public static class Av {
      private String A;
      private List kS;
      private String wS;
      public boolean pC;

      public boolean pC() {
         return this.wS.isEmpty();
      }

      public String A() {
         return this.A;
      }

      public List kS() {
         return this.kS;
      }

      public String wS() {
         return this.wS;
      }

      @Override
      public String toString() {
         return this.pC() ? this.A : this.A + "__" + this.wS;
      }
   }
}
