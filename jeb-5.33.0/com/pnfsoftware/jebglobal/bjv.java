package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNew;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconLambda;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

@Ser
public class bjv extends bio implements IJavaNew {
   @SerId(1)
   private IJavaType wS;
   @SerId(value = 2, deprecated = true)
   @Deprecated
   private bjr UT;
   @SerId(3)
   private List E;
   @SerId(4)
   private String sY;

   @SerCustomInitPostGraph
   private void UT() {
      if (this.UT != null) {
         this.sY = this.UT.getSignature();
         this.UT = null;
      }
   }

   public bjv(String var1, IJavaType var2) {
      this(var1, var2, null);
   }

   bjv(String var1, IJavaType var2, List var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Constructor cannot be null");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Type cannot be null");
      } else {
         this.sY = var1;
         this.wS = var2;
         this.E = new ArrayList();
         if (var3 != null) {
            this.E.addAll(var3);
         }
      }
   }

   public String A() {
      return JvmMethodSig.parse(this.sY).mname;
   }

   @Override
   public IJavaType getType() {
      return this.wS;
   }

   @Override
   public String getConstructorSignature() {
      return this.sY;
   }

   @Override
   public String getConstructorName() {
      return JvmMethodSig.parse(this.sY).mname;
   }

   public bjr kS() {
      return bhu.kS(this, this.sY);
   }

   @Override
   public List getArguments() {
      return this.E;
   }

   public void pC(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.E.add(var1);
      }
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.E);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.E.size(); var3++) {
         if (this.E.get(var3) == var1) {
            if (!(var2 instanceof IJavaExpression)) {
               return false;
            }

            this.E.set(var3, (IJavaExpression)var2);
            return true;
         }
      }

      return false;
   }

   @Override
   public IJavaClass getGeneratedAnonymousClass(JavaOutputSink var1, int[] var2) {
      IJavaClass var3 = null;
      int var4 = 0;
      IJavaMethod var5 = var1.getCurrentContainingMethod();
      if (var5 != null) {
         var4 = var5.isStatic() ? 0 : 1;
         if (this.E.size() >= var4) {
            for (IJavaClass var7 : var5.getAnonymousClasses()) {
               if (var7.getType().equals(this.wS)) {
                  var3 = var7;
                  var1.addGeneratedAnon(var5, var7);
                  break;
               }
            }
         }
      }

      if (var3 == null) {
         IJavaClass var9 = var1.getCurrentContainingClass();
         if (var9 != null) {
            for (IJavaClass var8 : var9.getAnonymousClasses()) {
               if (var8.getType().equals(this.wS)) {
                  var3 = var8;
                  var1.addGeneratedAnon(var9, var8);
                  break;
               }
            }
         }
      }

      if (var2 != null) {
         var2[0] = var4;
      }

      return var3;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      bjr var2 = this.kS();
      if (var2 == null) {
         this.pC(var1);
         ItemClassIdentifiers var11 = this.getOrigin() != null ? ItemClassIdentifiers.METHOD_NAME_GENERATED : ItemClassIdentifiers.METHOD_NAME;
         var1.appendAndRecord(this.A(), var11);
         var1.paren();

         for (int var13 = 0; var13 < this.E.size(); var13++) {
            if (var13 > 0) {
               var1.append(", ");
            }

            ((IJavaExpression)this.E.get(var13)).generate(var1);
         }

         var1.parenClose();
         this.A(var1);
      } else {
         if (this.getReconLambda() != null && var1.getGenerateLambdas()) {
            JavaReconLambda var3 = this.getReconLambda();
            bjr var4 = bhu.kS(this, var3.getImplementedMsig());
            if (!var1.isBeingGenerated(var4)) {
               var1.paren();
               int var14 = 0;

               for (int var7 : var3.getParameterPositions()) {
                  if (var14 >= 1) {
                     var1.append(", ");
                  }

                  if (var7 >= 0) {
                     IJavaDefinition var8 = var4.getParameter(var7);
                     var8.generate(var1);
                  } else {
                     var1.appendCommentAuto(S.L("/* MISSING LAMBDA PARAMETER */"));
                  }

                  var14++;
               }

               var1.parenClose();
               var1.space();
               var1.appendAndRecord("->", ItemClassIdentifiers.LAMBDA);
               var1.space();
               var1.pushReplacements(var3.getCapturedExpressions());
               var4.generate(var1, 2);
               var1.popReplacements();
               return;
            }
         }

         this.pC(var1);
         int[] var10 = new int[1];
         IJavaClass var12 = this.getGeneratedAnonymousClass(var1, var10);
         boolean var5 = this.wS != var2.getClassType();
         if (var5) {
            var1.onEolAddComment(S.L("initializer") + ": " + this.sY);
         }

         var1.appendKeyword(JavaKeyword.NEW);
         var1.append(" ");
         if (var12 != null && var12.isBuilt()) {
            int var15 = var10[0];

            try {
               var12.generate(var1, this.E, var15, false);
            } catch (ConcurrentModificationException var9) {
               throw var9;
            }
         } else {
            long var6 = 0L;
            if (var1.getDynamicContentManager() != null) {
               var6 = var1.getDynamicContentManager().getMethodId(var2.kS());
            }

            bhn.pC(var1, this.wS, false, false, var6);
            bit.pC(var1, true, this.kS(), this.sY, this.E, 0);
         }

         this.A(var1);
      }
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.New;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.sY == null ? 0 : this.sY.hashCode());
      var1 = 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
      return 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
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
         bjv var2 = (bjv)var1;
         if (this.sY == null) {
            if (var2.sY != null) {
               return false;
            }
         } else if (!this.sY.equals(var2.sY)) {
            return false;
         }

         if (this.E == null) {
            if (var2.E != null) {
               return false;
            }
         } else if (!this.E.equals(var2.E)) {
            return false;
         }

         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("new ");
      var1.append(this.wS);
      var1.append("(");
      int var2 = 0;

      for (IJavaExpression var4 : this.E) {
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
      return "new " + this.wS;
   }

   public bjv wS() {
      bjv var1 = new bjv(this.sY, this.wS);
      var1.E = pC(this.E);
      this.pC(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
