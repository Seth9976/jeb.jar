package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGlobalContext;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class bno extends bmk implements IJavaMethod {
   private static final ILogger HF = GlobalLog.getLogger(bno.class);
   public static final String Dw = "IR_CFG_FINAL";
   public static final String Uv = "KEY_IR_DEOB_NSCORE";
   public static final String oW = "KEY_IR_DEOB_RATING";
   @SerId(1)
   private boolean LK;
   @SerId(2)
   private int io;
   @SerId(3)
   private int qa;
   @SerId(4)
   private String Hk;
   @SerId(5)
   private IJavaType Me;
   @SerId(6)
   private IJavaType PV;
   @SerId(7)
   private List oQ;
   @SerId(8)
   private IJavaBlock xW;
   @SerId(9)
   public bnd gO;
   @SerId(10)
   private bnq KT;
   @SerId(11)
   private List Gf;
   @SerId(12)
   private int Ef;
   @SerId(13)
   private String cC;
   @SerId(14)
   private List sH;
   @SerId(15)
   private List CE;
   @SerId(value = 16, deprecated = true)
   @Deprecated
   private List wF;
   @SerId(value = 17, deprecated = true)
   @Deprecated
   private List If;
   @SerId(18)
   private String Dz;
   @SerId(19)
   public int nf;
   @SerId(20)
   @Deprecated
   public int gP;
   @SerId(21)
   private List mI;
   @SerId(22)
   private List jq;
   @SerId(23)
   private IJavaExpression ui;
   @SerTransient
   public bub za = null;
   @SerTransient
   public int lm = 0;
   @SerTransient
   public boolean zz;
   @SerTransient
   public boolean JY;

   @SerCustomInitPostGraph
   private void JY() {
      if (this.wF != null) {
         this.mI = new CopyOnWriteArrayList();
         this.wF.forEach(var1 -> this.mI.add(var1.getSignature()));
      }

      if (this.If != null) {
         this.jq = new CopyOnWriteArrayList();
         this.If.forEach(var1 -> this.jq.add(var1.getSignature()));
      }

      if (this.gP != 0) {
         this.addFlags(this.gP);
         this.gP = 0;
      }
   }

   public bno(
      int var1,
      IJavaTypeFactory var2,
      bmv var3,
      IJavaType var4,
      String var5,
      String var6,
      boolean var7,
      int var8,
      IJavaType var9,
      List var10,
      bnd var11,
      bnq var12,
      List var13,
      bmo var14
   ) {
      this.LK = var7;
      this.io = var8;
      this.qa = var1;
      this.Hk = var5;
      this.Dz = var6;
      this.Me = var4;
      this.PV = var9;
      this.oQ = var10 == null ? null : new CopyOnWriteArrayList(var10);
      this.xW = var14;
      this.gO = var11;
      this.KT = var12;
      this.Gf = var13;
      this.mI = new CopyOnWriteArrayList();
      this.jq = new CopyOnWriteArrayList();
   }

   public boolean xK() {
      if (!this.isBuilt()) {
         return false;
      } else {
         ArrayList var1 = new ArrayList(this.mI.size() + this.jq.size());
         var1.addAll(this.mI);
         var1.addAll(this.jq);

         for (String var3 : var1) {
            bni var4 = blr.q(this, var3);
            if (var4 == null || !var4.xK()) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public IJavaGlobalContext getGlobalContext() {
      return this.d_() != null ? ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)this.d_()).RF() : null;
   }

   @Override
   public void markSecondParameterOuterClassReference() {
      if (this.isStatic()) {
         throw new IllegalStateException();
      } else {
         this.addFlags(2);
      }
   }

   @Override
   public boolean isSecondParameterOuterClassReference() {
      return this.hasFlags(2);
   }

   public int Dw() {
      return this.qa;
   }

   @Override
   public List getInnerClassSignatures() {
      return this.mI;
   }

   @Override
   public List getInnerClasses() {
      ArrayList var1 = new ArrayList();
      this.mI.forEach(var2 -> Lists.addNonNulls(var1, blr.q(this, var2)));
      return var1;
   }

   public void RF(String var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         if (!this.mI.contains(var1)) {
            this.mI.add(var1);
         }
      }
   }

   @Override
   public List getAnonymousClassSignatures() {
      return this.jq;
   }

   @Override
   public List getAnonymousClasses() {
      ArrayList var1 = new ArrayList();
      this.jq.forEach(var2 -> Lists.addNonNulls(var1, blr.q(this, var2)));
      return var1;
   }

   public void xK(String var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         if (!this.jq.contains(var1)) {
            this.jq.add(var1);
         }
      }
   }

   @Override
   public List getParameters() {
      if (this.LK) {
         throw new RuntimeException("External method");
      } else {
         return this.oQ;
      }
   }

   @Override
   public IJavaDefinition getParameter(int var1) {
      if (this.LK) {
         throw new RuntimeException("External method");
      } else {
         return (IJavaDefinition)this.oQ.get(var1);
      }
   }

   @Override
   public int getParameterCount() {
      if (this.LK) {
         throw new RuntimeException("External method");
      } else {
         return this.oQ.size();
      }
   }

   @Override
   public void setIndexOfFirstVisibleParameter(int var1) {
      this.nf = var1;
   }

   @Override
   public int getIndexOfFirstVisibleParameter() {
      int var1 = 0;
      if (!this.isStatic()) {
         var1++;
         if (this.isSecondParameterOuterClassReference()) {
            var1++;
         }
      }

      return Math.max(this.nf, var1);
   }

   @Override
   public int getVisibleParameterCount() {
      return this.oQ.size() - this.getIndexOfFirstVisibleParameter();
   }

   @Override
   public List getVisibleParameters() {
      return this.oQ.subList(this.getIndexOfFirstVisibleParameter(), this.oQ.size());
   }

   @Override
   public IJavaType getReturnType() {
      return this.PV;
   }

   @Override
   public IJavaType getClassType() {
      return this.Me;
   }

   public String Uv() {
      return this.Me.getName();
   }

   @Override
   public String getName() {
      return this.Hk;
   }

   @Override
   public String getSignature() {
      return this.Dz;
   }

   @Override
   public boolean isExternal() {
      return this.LK;
   }

   @Override
   public boolean isConstructor() {
      return (this.io & 65536) != 0 || "<init>".equals(this.Hk) || "<clinit>".equals(this.Hk);
   }

   @Override
   public boolean isClassConstructor() {
      return "<init>".equals(this.Hk);
   }

   @Override
   public boolean isClassInitializer() {
      return "<clinit>".equals(this.Hk);
   }

   @Override
   public int getAccessFlags() {
      return this.io;
   }

   @Override
   public boolean isStatic() {
      return (this.io & 8) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.io & 4096) != 0;
   }

   @Override
   public boolean isAbstract() {
      return (this.io & 1024) != 0;
   }

   @Override
   public boolean isNative() {
      return (this.io & 256) != 0;
   }

   @Override
   public boolean isPublic() {
      return (this.io & 1) != 0;
   }

   @Override
   public boolean isProtected() {
      return (this.io & 4) != 0;
   }

   @Override
   public boolean isPrivate() {
      return (this.io & 2) != 0;
   }

   @Override
   public boolean isInner() {
      return (this.io & 1048576) != 0;
   }

   public void Dw(List var1) {
      this.sH = var1;
   }

   @Override
   public List getMethodAnnotations() {
      return this.sH;
   }

   public void Uv(List var1) {
      this.CE = var1;
   }

   public List oW() {
      return this.CE;
   }

   @Override
   public List getParameterAnnotations(int var1) {
      return this.CE == null ? null : (List)this.CE.get(var1);
   }

   @Override
   public void setDefaultValue(IJavaExpression var1) {
      this.ui = var1;
   }

   @Override
   public IJavaExpression getDefaultValue() {
      return this.ui;
   }

   public void q(int var1, String var2) {
      this.Ef = var1;
      this.cC = var2;
   }

   public void q(int var1) {
      this.q(var1, null);
   }

   public bnd gO() {
      if (this.LK) {
         throw new RuntimeException("External method");
      } else {
         return this.gO;
      }
   }

   public bnq nf() {
      if (this.LK) {
         throw new RuntimeException("External method");
      } else {
         return this.KT;
      }
   }

   @Override
   public IJavaBlock getBody() {
      return this.xW;
   }

   @Override
   public boolean isEmpty() {
      return this.xW.size() == 0;
   }

   public void q(IJavaStatement var1) {
      this.RF(var1);
   }

   public void RF(IJavaStatement var1) {
      this.xW.add(var1);
   }

   public IJavaStatement gP() {
      return this.xW.getLast();
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.generate(var1, false);
   }

   @Override
   public void generate(JavaOutputSink var1, boolean var2) {
      this.generate(var1, var2 ? 1 : 0);
   }

   @Override
   public void generate(JavaOutputSink var1, int var2) {
      if (this.LK) {
         throw new RuntimeException("Cannot generate an external method");
      } else {
         int var3 = var1.setCurrentMethodIndex(this.qa);
         this.q(var1);
         var1.pushContainingMethod(this);
         if (var1.getDisplayMethodInternalsAsComment() == 1) {
            var1.appendMultiLineCommentAuto(this.Dz, false, true);
         } else if (var1.getDisplayMethodInternalsAsComment() == 2) {
            var1.appendMultiLineCommentAuto(this.Dz + "\n" + bsb.xK(this.toFlatList()), true, true);
         }

         Integer var4 = (Integer)this.getData("KEY_IR_DEOB_NSCORE");
         if (var4 != null && var4 > 0) {
            String var5 = (String)this.getData("KEY_IR_DEOB_RATING");
            if (var5 != null && JebCoreService.getExistingInstance().getOptions().isUIClient()) {
               String var6 = S.L("Deobfuscation rating:") + " ";
               String var8 = Strings.ff("%s%s(%d)%s", var6, var5, var4, "");
               var1.appendMultiLineCommentAuto(var8, false, true);
            }
         }

         IDynamicContentManager var17 = var1.getDynamicContentManager();
         MethodCoordinates var18 = new MethodCoordinates(this.qa);
         var1.renderPreComment(var18);
         var1.renderInlineComment(var18, true);
         boolean var7 = false;
         if (var2 == 0) {
            if (this.hasFlags(16)) {
               var1.appendMultiLineCommentAuto(S.L("Detected as a lambda implementation"), false, true);
            }

            HashSet var19 = null;
            if (var17 != null && var1.getGenerateOverrideAnnotations() && !this.isPrivate() && !this.isConstructor()) {
               List var9 = null;

               try {
                  var9 = var17.findTypesWithSuperMethods(this.Dw());
               } catch (Exception var16) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var16, this.Dz);
               }

               if (var9 != null && !var9.isEmpty()) {
                  var1.appendAndRecord("@Override", ItemClassIdentifiers.ANNOTATION);
                  ArrayList var10 = new ArrayList();

                  for (String var12 : var9) {
                     if (!var12.startsWith("java.")) {
                        var10.add(var12);
                     }
                  }

                  if (!var10.isEmpty()) {
                     var1.space(2);
                     var1.appendCommentAuto("// " + Strings.join(", ", var10));
                  }

                  var1.eol();
                  var19 = Sets.createNonNulls("java.lang.Override");
               }
            }

            if (this.sH != null && var1.getGenerateAnnotations()) {
               var1.appendAnnotationsList(this.sH, '\n', var19);
            }

            var1.recordGeneratedDecompilable(this.getSignature());
            int var21 = this.io;
            if (var1.getCurrentContainingClass() != null && var1.getCurrentContainingClass().isInterface()) {
               var21 &= -2;
               if (this.isAbstract()) {
                  var21 &= -1025;
               } else if (!this.isStatic()) {
                  JavaKeyword.appendAccessKeyword(var1, JavaKeyword.DEFAULT);
                  var1.space();
               }
            }

            if ("<clinit>".equals(this.Hk)) {
               var21 = 8;
            }

            JavaKeyword.generateMethodAccessFlags(var1, var21, -1);
            if (this.isConstructor()) {
               if (this.isStatic()) {
                  var7 = true;
               } else {
                  long var23 = 0L;
                  if (var17 != null && this.qa >= 0) {
                     var23 = var17.getMethodId(this.qa);
                  }

                  blk.q(var1, this.getClassType(), false, false, var23);
               }
            } else {
               blk.q(var1, this.PV);
               var1.space();
               this.q(var1, true);
            }

            if (!var7) {
               int var24 = this.getIndexOfFirstVisibleParameter();
               var1.paren();

               for (int var27 = var24; var27 < this.oQ.size(); var27++) {
                  if (var27 > var24) {
                     var1.append(", ");
                  }

                  if (this.CE != null && var1.getGenerateAnnotations() && var27 - var24 < this.CE.size()) {
                     List var30 = (List)this.CE.get(var27 - var24);
                     var1.appendAnnotationsList(var30, ' ');
                  }

                  ((IJavaDefinition)this.oQ.get(var27)).generate(var1);
               }

               var1.parenClose();
            }

            if (this.Gf != null && !this.Gf.isEmpty()) {
               var1.space();
               var1.appendKeyword(JavaKeyword.THROWS);
               int var25 = 0;

               for (IJavaType var31 : this.Gf) {
                  if (var25 >= 1) {
                     var1.append(",");
                  }

                  var1.space();
                  blk.q(var1, var31);
                  var25++;
               }
            }
         }

         var1.setEolCoordinates(var18);
         boolean var20 = var2 == 2;
         boolean var22 = var20 && this.xW.size() == 1 && this.mI.isEmpty() && this.jq.isEmpty();
         if (this.isAbstract()) {
            if (this.ui != null) {
               var1.space();
               var1.appendKeyword(JavaKeyword.DEFAULT);
               var1.space();
               this.ui.generate(var1);
            }

            var1.append(";");
         } else {
            if (!var7 && var2 == 0) {
               var1.space();
            }

            String[] var26 = new String[1];
            boolean var29 = false;
            if (!var1.getDisregardCollapse()) {
               var29 = var17 != null && var17.isCollapsed(this.getSignature(), var26);
            }

            if (var29 && var2 == 0) {
               var1.getCurrentLine().addFlags(1);
               var1.append("[...]");
               String var34 = var26[0];
               if (!Strings.isBlank(var34)) {
                  var1.appendCommentAuto(" // " + var34);
               }
            } else if (this.Ef == 0) {
               if (!var22) {
                  this.xW.generateHeader(var1);
               }

               int var32 = 0;

               for (IJavaClass var14 : this.getInnerClasses()) {
                  if (var14 != null) {
                     if (var32 >= 1) {
                        var1.eol();
                     }

                     var14.generate(var1);
                     var32++;
                  }
               }

               this.xW.generateBody(var1, var22);
               List var35 = var1.getGeneratedAnon(this);

               for (IJavaClass var15 : this.getAnonymousClasses()) {
                  if (var15 != null && !var35.contains(var15)) {
                     var1.eol();
                     var15.generate(var1);
                  }
               }

               if (!var22) {
                  this.xW.generateFooter(var1);
               }
            } else {
               var1.brace();
               var1.eol();
               var1.incrementIndentationLevel();
               if (this.Ef == 2) {
                  var1.appendCommentAuto(S.L("// DEMO VERSION - The method was not decompiled"));
               } else if (this.Ef == 3) {
                  var1.appendCommentAuto(S.L("// The method body was not generated (decompiler flag)"));
               } else {
                  var1.appendCommentAuto(S.L("// ERROR - The method was not decompiled"));
               }

               var1.eol();
               if (this.Ef != 2 && this.cC != null) {
                  for (String var39 : Strings.splitLines(this.cC, true)) {
                     var1.appendCommentAuto("// " + var39);
                     var1.eol();
                  }
               }

               var1.decrementIndentationLevel();
               var1.braceClose();
            }
         }

         if (!var20) {
            var1.eol();
         }

         var1.setCurrentMethodIndex(var3);
         var1.popContainingMethod();
         this.RF(var1);
      }
   }

   public void q(JavaOutputSink var1, boolean var2) {
      String var3 = this.Hk;
      long var4 = 0L;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null && this.qa >= 0) {
         var4 = var6.getMethodId(this.qa);
         var3 = var6.getMethodName(this.qa);
      }

      var1.appendAndRecord(var3, ItemClassIdentifiers.METHOD_NAME, var4, var2 ? 1 : 0);
   }

   public boolean q(int var1, IJavaStatement var2) {
      return this.xW.insertAt(var1, var2);
   }

   @Override
   public List toFlatList() {
      return this.xW.generateFlatList();
   }

   @Override
   public void fromFlatList(List var1) {
      IJavaStatement var2 = (IJavaStatement)var1.get(0);
      int[] var3 = new int[1];
      IJavaStatement var4 = ((brp)var2).q(var1, 0, var3);
      if (var3[0] != var1.size()) {
         throw new RuntimeException();
      } else {
         this.xW = (bmo)var4;
      }
   }

   public void za() {
      IdentityHashSet var1 = new IdentityHashSet();
      if (!this.q(this.xW, var1)) {
         throw new RuntimeException("AST validation failed!");
      }
   }

   private boolean q(IJavaBlock var1, Set var2) {
      for (IJavaStatement var4 : var1) {
         if (var2.contains(var4)) {
            return false;
         }

         var2.add(var4);
         if (var4 instanceof bmi) {
            for (IJavaBlock var6 : ((bmi)var4).getBlocks()) {
               if (!this.q(var6, var2)) {
                  return false;
               }
            }
         }

         if (var4 instanceof bml) {
            ;
         }
      }

      return true;
   }

   public int lm() {
      return this.xW.size();
   }

   public IJavaStatement RF(int var1) {
      return this.xW.get(var1);
   }

   @Override
   public List getStatements() {
      ArrayList var1 = new ArrayList();
      this.q(this.xW, var1);
      return var1;
   }

   private void q(IJavaBlock var1, List var2) {
      for (IJavaStatement var4 : var1) {
         var2.add(var4);
         if (var4 instanceof bmi) {
            for (IJavaBlock var6 : ((bmi)var4).getBlocks()) {
               this.q(var6, var2);
            }
         }
      }
   }

   public IJavaStatement xK(int var1) {
      return this.xW.remove(var1);
   }

   @Override
   public boolean deleteStatement(IJavaStatement var1) {
      int var2 = this.q(this.xW, var1);
      if (var2 >= 2) {
         throw new RuntimeException("Statement deleted more than once, the method is not valid: " + var1);
      } else {
         return var2 == 1;
      }
   }

   private int q(IJavaBlock var1, IJavaStatement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 == var2) {
            var1.remove(var4);
            var3++;
         } else {
            if (var5 instanceof bmi) {
               for (IJavaBlock var7 : ((bmi)var5).getBlocks()) {
                  var3 += this.q(var7, var2);
               }
            }

            var4++;
         }
      }

      return var3;
   }

   @Override
   public List getSubElements() {
      List var1 = blo.q(this.oQ);
      blo.q(var1, this.xW);
      return var1;
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.oQ.size(); var3++) {
         if (this.oQ.get(var3) == var1) {
            if (var2 instanceof bmx var4) {
               this.oQ.set(var3, var4);
               return true;
            }

            return false;
         }
      }

      if (this.xW != var1) {
         return false;
      } else if (var2 instanceof bmo var5) {
         this.xW = var5;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Method;
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public String toString() {
      return "method:" + this.getClassType() + "." + this.getName();
   }

   public bno zz() {
      return this;
   }
}
