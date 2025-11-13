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
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class bjr extends bin implements IJavaMethod {
   private static final ILogger ld = GlobalLog.getLogger(bjr.class);
   @SerId(1)
   private boolean gp;
   @SerId(2)
   private int oT;
   @SerId(3)
   private int fI;
   @SerId(4)
   private String WR;
   @SerId(5)
   private IJavaType NS;
   @SerId(6)
   private IJavaType vP;
   @SerId(7)
   private List xC;
   @SerId(8)
   private IJavaBlock ED;
   @SerId(9)
   public bjg A;
   @SerId(10)
   private bjt Sc;
   @SerId(11)
   private List ah;
   @SerId(12)
   private int eP;
   @SerId(13)
   private String UO;
   @SerId(14)
   private List Ab;
   @SerId(15)
   private List rl;
   @SerId(value = 16, deprecated = true)
   @Deprecated
   private List z;
   @SerId(value = 17, deprecated = true)
   @Deprecated
   private List Ek;
   @SerId(18)
   private String hK;
   @SerId(19)
   public int kS;
   @SerId(20)
   @Deprecated
   public int wS;
   @SerId(21)
   private List Er;
   @SerId(22)
   private List FE;
   @SerId(23)
   private IJavaExpression Aj;
   @SerTransient
   public bpv UT = null;
   @SerTransient
   public int E = 0;
   @SerTransient
   public boolean sY;
   @SerTransient
   public boolean ys;

   @SerCustomInitPostGraph
   private void ys() {
      if (this.z != null) {
         this.Er = new CopyOnWriteArrayList();
         this.z.forEach(var1 -> this.Er.add(var1.getSignature()));
      }

      if (this.Ek != null) {
         this.FE = new CopyOnWriteArrayList();
         this.Ek.forEach(var1 -> this.FE.add(var1.getSignature()));
      }

      if (this.wS != 0) {
         this.addFlags(this.wS);
         this.wS = 0;
      }
   }

   public bjr(
      int var1,
      IJavaTypeFactory var2,
      biy var3,
      IJavaType var4,
      String var5,
      String var6,
      boolean var7,
      int var8,
      IJavaType var9,
      List var10,
      bjg var11,
      bjt var12,
      List var13,
      bir var14
   ) {
      this.gp = var7;
      this.oT = var8;
      this.fI = var1;
      this.WR = var5;
      this.hK = var6;
      this.NS = var4;
      this.vP = var9;
      this.xC = var10 == null ? null : new CopyOnWriteArrayList(var10);
      this.ED = var14;
      this.A = var11;
      this.Sc = var12;
      this.ah = var13;
      this.Er = new CopyOnWriteArrayList();
      this.FE = new CopyOnWriteArrayList();
   }

   public boolean A() {
      if (!this.isBuilt()) {
         return false;
      } else {
         ArrayList var1 = new ArrayList(this.Er.size() + this.FE.size());
         var1.addAll(this.Er);
         var1.addAll(this.FE);

         for (String var3 : var1) {
            bjl var4 = bhu.pC(this, var3);
            if (var4 == null || !var4.A()) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public IJavaGlobalContext getGlobalContext() {
      return this.d_() != null ? ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)this.d_()).A() : null;
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

   public int kS() {
      return this.fI;
   }

   @Override
   public List getInnerClassSignatures() {
      return this.Er;
   }

   @Override
   public List getInnerClasses() {
      ArrayList var1 = new ArrayList();
      this.Er.forEach(var2 -> Lists.addNonNulls(var1, bhu.pC(this, var2)));
      return var1;
   }

   public void A(String var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         if (!this.Er.contains(var1)) {
            this.Er.add(var1);
         }
      }
   }

   @Override
   public List getAnonymousClassSignatures() {
      return this.FE;
   }

   @Override
   public List getAnonymousClasses() {
      ArrayList var1 = new ArrayList();
      this.FE.forEach(var2 -> Lists.addNonNulls(var1, bhu.pC(this, var2)));
      return var1;
   }

   public void kS(String var1) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         if (!this.FE.contains(var1)) {
            this.FE.add(var1);
         }
      }
   }

   @Override
   public List getParameters() {
      if (this.gp) {
         throw new RuntimeException("External method");
      } else {
         return this.xC;
      }
   }

   @Override
   public IJavaDefinition getParameter(int var1) {
      if (this.gp) {
         throw new RuntimeException("External method");
      } else {
         return (IJavaDefinition)this.xC.get(var1);
      }
   }

   @Override
   public int getParameterCount() {
      if (this.gp) {
         throw new RuntimeException("External method");
      } else {
         return this.xC.size();
      }
   }

   @Override
   public void setIndexOfFirstVisibleParameter(int var1) {
      this.kS = var1;
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

      return Math.max(this.kS, var1);
   }

   @Override
   public int getVisibleParameterCount() {
      return this.xC.size() - this.getIndexOfFirstVisibleParameter();
   }

   @Override
   public List getVisibleParameters() {
      return this.xC.subList(this.getIndexOfFirstVisibleParameter(), this.xC.size());
   }

   @Override
   public IJavaType getReturnType() {
      return this.vP;
   }

   @Override
   public IJavaType getClassType() {
      return this.NS;
   }

   public String wS() {
      return this.NS.getName();
   }

   @Override
   public String getName() {
      return this.WR;
   }

   @Override
   public String getSignature() {
      return this.hK;
   }

   @Override
   public boolean isExternal() {
      return this.gp;
   }

   @Override
   public boolean isConstructor() {
      return (this.oT & 65536) != 0 || "<init>".equals(this.WR) || "<clinit>".equals(this.WR);
   }

   @Override
   public boolean isClassConstructor() {
      return "<init>".equals(this.WR);
   }

   @Override
   public boolean isClassInitializer() {
      return "<clinit>".equals(this.WR);
   }

   @Override
   public int getAccessFlags() {
      return this.oT;
   }

   @Override
   public boolean isStatic() {
      return (this.oT & 8) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.oT & 4096) != 0;
   }

   @Override
   public boolean isAbstract() {
      return (this.oT & 1024) != 0;
   }

   @Override
   public boolean isNative() {
      return (this.oT & 256) != 0;
   }

   @Override
   public boolean isPublic() {
      return (this.oT & 1) != 0;
   }

   @Override
   public boolean isProtected() {
      return (this.oT & 4) != 0;
   }

   @Override
   public boolean isPrivate() {
      return (this.oT & 2) != 0;
   }

   @Override
   public boolean isInner() {
      return (this.oT & 1048576) != 0;
   }

   public void wS(List var1) {
      this.Ab = var1;
   }

   @Override
   public List getMethodAnnotations() {
      return this.Ab;
   }

   public void UT(List var1) {
      this.rl = var1;
   }

   @Override
   public List getParameterAnnotations(int var1) {
      return this.rl == null ? null : (List)this.rl.get(var1);
   }

   @Override
   public void setDefaultValue(IJavaExpression var1) {
      this.Aj = var1;
   }

   @Override
   public IJavaExpression getDefaultValue() {
      return this.Aj;
   }

   public void pC(int var1, String var2) {
      this.eP = var1;
      this.UO = var2;
   }

   public void pC(int var1) {
      this.pC(var1, null);
   }

   public bjg UT() {
      if (this.gp) {
         throw new RuntimeException("External method");
      } else {
         return this.A;
      }
   }

   public bjt E() {
      if (this.gp) {
         throw new RuntimeException("External method");
      } else {
         return this.Sc;
      }
   }

   @Override
   public IJavaBlock getBody() {
      return this.ED;
   }

   @Override
   public boolean isEmpty() {
      return this.ED.size() == 0;
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
      if (this.gp) {
         throw new RuntimeException("Cannot generate an external method");
      } else {
         int var3 = var1.setCurrentMethodIndex(this.fI);
         this.pC(var1);
         var1.pushContainingMethod(this);
         if (var1.getDisplayMethodInternalsAsComment() == 1) {
            var1.appendMultiLineCommentAuto(this.hK, false, true);
         } else if (var1.getDisplayMethodInternalsAsComment() == 2) {
            var1.appendMultiLineCommentAuto(this.hK + "\n" + boa.A(this.toFlatList()), true, true);
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
         MethodCoordinates var18 = new MethodCoordinates(this.fI);
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
                  var9 = var17.findTypesWithSuperMethods(this.kS());
               } catch (Exception var16) {
                  com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var16, this.hK);
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

            if (this.Ab != null && var1.getGenerateAnnotations()) {
               var1.appendAnnotationsList(this.Ab, '\n', var19);
            }

            var1.recordGeneratedDecompilable(this.getSignature());
            int var21 = this.oT;
            if (var1.getCurrentContainingClass() != null && var1.getCurrentContainingClass().isInterface()) {
               var21 &= -2;
               if (this.isAbstract()) {
                  var21 &= -1025;
               } else if (!this.isStatic()) {
                  JavaKeyword.appendAccessKeyword(var1, JavaKeyword.DEFAULT);
                  var1.space();
               }
            }

            if ("<clinit>".equals(this.WR)) {
               var21 = 8;
            }

            JavaKeyword.generateMethodAccessFlags(var1, var21, -1);
            if (this.isConstructor()) {
               if (this.isStatic()) {
                  var7 = true;
               } else {
                  long var23 = 0L;
                  if (var17 != null && this.fI >= 0) {
                     var23 = var17.getMethodId(this.fI);
                  }

                  bhn.pC(var1, this.getClassType(), false, false, var23);
               }
            } else {
               bhn.pC(var1, this.vP);
               var1.space();
               this.pC(var1, true);
            }

            if (!var7) {
               int var24 = this.getIndexOfFirstVisibleParameter();
               var1.paren();

               for (int var27 = var24; var27 < this.xC.size(); var27++) {
                  if (var27 > var24) {
                     var1.append(", ");
                  }

                  if (this.rl != null && var1.getGenerateAnnotations() && var27 - var24 < this.rl.size()) {
                     List var30 = (List)this.rl.get(var27 - var24);
                     var1.appendAnnotationsList(var30, ' ');
                  }

                  ((IJavaDefinition)this.xC.get(var27)).generate(var1);
               }

               var1.parenClose();
            }

            if (this.ah != null && !this.ah.isEmpty()) {
               var1.space();
               var1.appendKeyword(JavaKeyword.THROWS);
               int var25 = 0;

               for (IJavaType var31 : this.ah) {
                  if (var25 >= 1) {
                     var1.append(",");
                  }

                  var1.space();
                  bhn.pC(var1, var31);
                  var25++;
               }
            }
         }

         var1.setEolCoordinates(var18);
         boolean var20 = var2 == 2;
         boolean var22 = var20 && this.ED.size() == 1 && this.Er.isEmpty() && this.FE.isEmpty();
         if (this.isAbstract()) {
            if (this.Aj != null) {
               var1.space();
               var1.appendKeyword(JavaKeyword.DEFAULT);
               var1.space();
               this.Aj.generate(var1);
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
            } else if (this.eP == 0) {
               if (!var22) {
                  this.ED.generateHeader(var1);
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

               this.ED.generateBody(var1, var22);
               List var35 = var1.getGeneratedAnon(this);

               for (IJavaClass var15 : this.getAnonymousClasses()) {
                  if (var15 != null && !var35.contains(var15)) {
                     var1.eol();
                     var15.generate(var1);
                  }
               }

               if (!var22) {
                  this.ED.generateFooter(var1);
               }
            } else {
               var1.brace();
               var1.eol();
               var1.incrementIndentationLevel();
               if (this.eP == 2) {
                  var1.appendCommentAuto(S.L("// DEMO VERSION - The method was not decompiled"));
               } else if (this.eP == 3) {
                  var1.appendCommentAuto(S.L("// The method body was not generated (decompiler flag)"));
               } else {
                  var1.appendCommentAuto(S.L("// ERROR - The method was not decompiled"));
               }

               var1.eol();
               if (this.eP != 2 && this.UO != null) {
                  for (String var39 : Strings.splitLines(this.UO, true)) {
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
         this.A(var1);
      }
   }

   public void pC(JavaOutputSink var1, boolean var2) {
      String var3 = this.WR;
      long var4 = 0L;
      IDynamicContentManager var6 = var1.getDynamicContentManager();
      if (var6 != null && this.fI >= 0) {
         var4 = var6.getMethodId(this.fI);
         var3 = var6.getMethodName(this.fI);
      }

      var1.appendAndRecord(var3, ItemClassIdentifiers.METHOD_NAME, var4, var2 ? 1 : 0);
   }

   public boolean pC(int var1, IJavaStatement var2) {
      return this.ED.insertAt(var1, var2);
   }

   @Override
   public List toFlatList() {
      return this.ED.generateFlatList();
   }

   @Override
   public void fromFlatList(List var1) {
      IJavaStatement var2 = (IJavaStatement)var1.get(0);
      int[] var3 = new int[1];
      IJavaStatement var4 = ((bno)var2).pC(var1, 0, var3);
      if (var3[0] != var1.size()) {
         throw new RuntimeException();
      } else {
         this.ED = (bir)var4;
      }
   }

   @Override
   public List getStatements() {
      ArrayList var1 = new ArrayList();
      this.pC(this.ED, var1);
      return var1;
   }

   private void pC(IJavaBlock var1, List var2) {
      for (IJavaStatement var4 : var1) {
         var2.add(var4);
         if (var4 instanceof bil) {
            for (IJavaBlock var6 : ((bil)var4).getBlocks()) {
               this.pC(var6, var2);
            }
         }
      }
   }

   @Override
   public boolean deleteStatement(IJavaStatement var1) {
      int var2 = this.pC(this.ED, var1);
      if (var2 >= 2) {
         throw new RuntimeException("Statement deleted more than once, the method is not valid: " + var1);
      } else {
         return var2 == 1;
      }
   }

   private int pC(IJavaBlock var1, IJavaStatement var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 == var2) {
            var1.remove(var4);
            var3++;
         } else {
            if (var5 instanceof bil) {
               for (IJavaBlock var7 : ((bil)var5).getBlocks()) {
                  var3 += this.pC(var7, var2);
               }
            }

            var4++;
         }
      }

      return var3;
   }

   @Override
   public List getSubElements() {
      List var1 = bhr.pC(this.xC);
      bhr.pC(var1, this.ED);
      return var1;
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.xC.size(); var3++) {
         if (this.xC.get(var3) == var1) {
            if (var2 instanceof bja var4) {
               this.xC.set(var3, var4);
               return true;
            }

            return false;
         }
      }

      if (this.ED != var1) {
         return false;
      } else if (var2 instanceof bir var5) {
         this.ED = var5;
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

   public bjr sY() {
      return this;
   }
}
