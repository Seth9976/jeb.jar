package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Ser
public class bit extends bio implements IJavaCall {
   @SerId(value = 1, deprecated = true)
   @Deprecated
   private bjr wS;
   @SerId(2)
   private List UT;
   @SerId(value = 3, deprecated = true)
   @Deprecated
   private boolean E;
   @SerId(4)
   private int sY;
   @SerId(5)
   private String ys;

   @SerCustomInitPostGraph
   private void wS() {
      if (this.E) {
         this.sY = 1;
      }

      if (this.wS != null) {
         this.ys = this.wS.getSignature();
         if (this.wS.isStatic()) {
            this.sY = 3;
         }

         this.wS = null;
      }
   }

   public bit(String var1, int var2) {
      this(var1, var2, null);
   }

   bit(String var1, int var2, List var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.ys = var1;
         this.sY = var2;
         this.UT = new ArrayList();
         if (var3 != null) {
            this.UT.addAll(var3);
         }
      }
   }

   @Override
   public String getMethodSignature() {
      return this.ys;
   }

   @Override
   public String getMethodName() {
      return JvmMethodSig.parse(this.ys).mname;
   }

   @Override
   public String getMethodClass() {
      return JvmMethodSig.parse(this.ys).csig;
   }

   public bjr A() {
      return bhu.kS(this, this.ys);
   }

   @Override
   public void setMethod(IJavaMethod var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("Provide a method object");
      } else {
         this.ys = var1.getSignature();
         this.sY = var2;
      }
   }

   @Override
   public int getCallType() {
      return this.sY;
   }

   @Override
   public boolean isSuperCall() {
      return this.sY == 1;
   }

   @Override
   public boolean isLambdaCall() {
      return this.sY == 2;
   }

   @Override
   public boolean isStaticCall() {
      return this.sY == 3;
   }

   @Override
   public int getArgumentCount() {
      return this.UT.size();
   }

   @Override
   public List getArguments() {
      return this.UT;
   }

   @Override
   public IJavaExpression getArgument(int var1) {
      return (IJavaExpression)this.UT.get(var1);
   }

   @Override
   public void addArgument(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null object argument");
      } else {
         this.UT.add(var1);
      }
   }

   @Override
   public void insertArgument(int var1, IJavaExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Null object argument");
      } else {
         this.UT.add(var1, var2);
      }
   }

   @Override
   public void setArgument(int var1, IJavaExpression var2) {
      if (var2 == null) {
         throw new IllegalArgumentException("Null object argument");
      } else {
         this.UT.set(var1, var2);
      }
   }

   @Override
   public IJavaExpression removeArgument(int var1) {
      return (IJavaExpression)this.UT.remove(var1);
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.UT);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.UT.size(); var3++) {
         if (this.UT.get(var3) == var1) {
            if (!(var2 instanceof IJavaExpression)) {
               return false;
            }

            this.UT.set(var3, (IJavaExpression)var2);
            return true;
         }
      }

      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      bjr var2 = this.A();
      if (var2 == null) {
         this.pC(var1);
         ItemClassIdentifiers var19 = this.getOrigin() != null ? ItemClassIdentifiers.METHOD_NAME_GENERATED : ItemClassIdentifiers.METHOD_NAME;
         var1.appendAndRecord(this.getMethodName(), var19);
         var1.paren();

         for (int var21 = 0; var21 < this.UT.size(); var21++) {
            if (var21 > 0) {
               var1.append(", ");
            }

            ((IJavaExpression)this.UT.get(var21)).generate(var1);
         }

         var1.parenClose();
         this.A(var1);
      } else if (this.sY == 2) {
         if (var2.isExternal()) {
            bhn.pC(var1, var2.getClassType());
            var1.append("::");
            var1.append(var2.getName());
         } else {
            int var18 = this.UT.size();
            HashMap var20 = new HashMap();

            for (int var22 = 0; var22 < var18; var22++) {
               IJavaIdentifier var24 = var2.getParameter(var22).getIdentifier();
               var20.put(var24, (IJavaExpression)this.UT.get(var22));
            }

            var1.paren();
            int var23 = 0;

            for (int var25 = var18; var25 < var2.getParameterCount(); var25++) {
               IJavaDefinition var7 = var2.getParameter(var25);
               if (var23 >= 1) {
                  var1.append(", ");
               }

               var7.generate(var1);
               var23++;
            }

            var1.parenClose();
            var1.space();
            var1.appendAndRecord("->", ItemClassIdentifiers.LAMBDA);
            var1.space();
            var1.pushReplacements(var20);
            var2.generate(var1, 2);
            var1.popReplacements();
         }
      } else {
         this.pC(var1);
         boolean var3 = true;
         String var4 = var2.getName();
         int var5 = var2.kS();
         long var6 = 0L;
         IDynamicContentManager var8 = var1.getDynamicContentManager();
         byte var9 = 0;
         boolean var10 = this.isSuperCall();
         boolean var11 = var4.equals("<init>");
         if (var11) {
            var4 = "super";
            if (var1.getCurrentContainingClass() != null) {
               String var12 = var2.getClassType().getName();
               String var13 = var1.getCurrentContainingClass().getName();
               if (var12 != null && var13 != null && var12.equals(var13)) {
                  var4 = "this";
               }
            } else if (var1.getCurrentContainingMethod() != null) {
               String var26 = var2.getClassType().getName();
               String var29 = var1.getCurrentContainingMethod().getClassType().getName();
               if (var26 != null && var29 != null && var26.equals(var29)) {
                  var4 = "this";
               }
            }

            var3 = false;
            if (var8 != null && var5 >= 0) {
               var6 = var8.getMethodId(var5);
            }
         } else if (var8 != null && var5 >= 0) {
            if (var1.getResolveMethodCallTargets()) {
               if (!var10 && !this.A().isConstructor() && !this.isStaticCall() && !this.A().isPrivate()) {
                  ArrayList var27 = new ArrayList();
                  var6 = var8.getBestVirtualMethodId(var5, null, var27);
                  if (var27.size() >= 2) {
                     var9 |= 2;
                  }
               }

               if (!var10 && !this.A().isConstructor() && this.isStaticCall() && !this.A().isPrivate()) {
                  var6 = var8.getImplStaticMethodId(var5);
               }
            }

            if (var6 == 0L) {
               var6 = var8.getMethodId(var5);
            }

            var4 = var8.getMethodName(var5);
         }

         int var28;
         if (this.isStaticCall()) {
            IJavaType var30 = var2.getClassType();
            bhn.pC(var1, var30);
            var1.append(".");
            var28 = 0;
         } else {
            if (var3) {
               boolean var31 = false;
               IJavaExpression var14 = (IJavaExpression)this.UT.get(0);
               if (var10) {
                  IJavaClass var15 = var1.getCurrentContainingClass();
                  if (var15 != null) {
                     for (IJavaType var17 : var15.getImplementedInterfaces()) {
                        if (var17.equals(var2.getClassType())) {
                           bhn.pC(var1, var17);
                           var1.append(".");
                           break;
                        }
                     }
                  }

                  boolean var33 = false;
                  if (!(var14 instanceof bjf)) {
                     var33 = true;
                  } else if (!"this".equals(((bjf)var14).getName())) {
                     var33 = true;
                  }

                  if (var33) {
                     var14.generate(var1);
                     var1.append(".");
                  }

                  var1.appendKeyword(JavaKeyword.SUPER);
               } else if (bji.pC(var1, var14, var4)) {
                  var31 = true;
               } else {
                  var14.generate(var1);
               }

               if (!var31) {
                  var1.append(".");
               }
            }

            var28 = var2.getIndexOfFirstVisibleParameter();
         }

         ItemClassIdentifiers var32 = this.getOrigin() != null ? ItemClassIdentifiers.METHOD_NAME_GENERATED : ItemClassIdentifiers.METHOD_NAME;
         var1.appendAndRecord(var4, var32, var6, var9);
         pC(var1, false, this.A(), this.ys, this.UT, var28);
         this.A(var1);
      }
   }

   static void pC(JavaOutputSink var0, boolean var1, bjr var2, String var3, List var4, int var5) {
      int var6 = var0.getSplitCallArgThreshold();
      boolean var7 = var6 >= 1 && var4.size() >= var6;
      ArrayList var8 = null;

      try {
         if (var7) {
            IDynamicContentManager var9 = var0.getDynamicContentManager();
            var8 = new ArrayList();
            if (!var2.isExternal()) {
               for (IJavaDefinition var11 : var2.getParameters()) {
                  IJavaIdentifier var12 = var11.getIdentifier();
                  String var13 = var12.getGeneratedName(var0);
                  String var14 = var12.getGeneratedType(var0);
                  var8.add(var13 + ":" + var14);
               }

               if (var1) {
                  Assert.a(var2.isConstructor());
                  var8.remove(0);
               }
            } else if (var3 != null) {
               for (int var22 = 0; var22 < var5; var22++) {
                  var8.add(null);
               }

               JvmMethodSig var23 = JvmMethodSig.parse(var3);
               String[] var25 = bgv.pC().pC(var3);
               if (var25 != null && var25.length <= var23.partypes.size()) {
                  int var27 = 0;

                  for (String var16 : var25) {
                     String var17 = (String)var23.partypes.get(var27);
                     if (var9 != null) {
                        IDexType var18 = var9.getDexType(var17);
                        if (var18 != null) {
                           var17 = var18.getSignature(true, false, true);
                        }
                     }

                     var8.add(var16 + ":" + var17);
                     var27++;
                  }
               }
            }
         }
      } catch (Exception var19) {
         var8 = null;
      }

      var0.paren();
      if (var8 != null) {
         int var20 = -1;
         int var24 = var0.getCurrentLineLength() - var0.getCurrentMarginLength();

         for (int var26 = var5; var26 < var4.size(); var26++) {
            ((IJavaExpression)var4.get(var26)).generate(var0);
            if (var26 < var4.size() - 1) {
               var0.append(",");
            }

            String var28 = (String)Lists.get(var8, var26);
            if (var28 != null) {
               var0.space();
               if (var20 < 0) {
                  var0.space(8);
                  var20 = var0.getCurrentLineLength();
               } else {
                  int var30 = Math.max(0, var20 - var0.getCurrentLineLength());
                  var0.space(var30);
               }

               var0.appendCommentAuto("// " + var28);
            }

            var0.eol();
            var0.space(var24);
         }
      } else {
         for (int var21 = var5; var21 < var4.size(); var21++) {
            if (var21 > var5) {
               var0.append(", ");
            }

            ((IJavaExpression)var4.get(var21)).generate(var0);
         }
      }

      var0.parenClose();
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Call;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.ys == null ? 0 : this.ys.hashCode());
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      return 31 * var1 + this.sY;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bit var2 = (bit)var1;
         if (this.ys == null) {
            if (var2.ys != null) {
               return false;
            }
         } else if (!this.ys.equals(var2.ys)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         return this.sY == var2.sY;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.isSuperCall()) {
         var1.append("super.");
      } else if (this.isLambdaCall()) {
         var1.append("lambda:");
      }

      var1.append(this.getMethodName());
      var1.append("(");
      int var2 = 0;

      for (IJavaExpression var4 : this.UT) {
         if (var2 >= 1) {
            var1.append(", ");
         }

         var1.append(var4);
         var2++;
      }

      var1.append(")");
      return var1.toString();
   }

   @Override
   public String toShortString() {
      return this.getMethodName();
   }

   public bit kS() {
      bit var1 = new bit(this.ys, this.sY, pC(this.UT));
      this.pC(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
