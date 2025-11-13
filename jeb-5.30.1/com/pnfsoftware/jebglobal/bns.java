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
public class bns extends bml implements IJavaNew {
   @SerId(1)
   private IJavaType oW;
   @SerId(value = 2, deprecated = true)
   @Deprecated
   private bno gO;
   @SerId(3)
   private List nf;
   @SerId(4)
   private String gP;

   @SerCustomInitPostGraph
   private void nf() {
      if (this.gO != null) {
         this.gP = this.gO.getSignature();
         this.gO = null;
      }
   }

   public bns(String var1, IJavaType var2) {
      this(var1, var2, null);
   }

   bns(String var1, IJavaType var2, List var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Constructor cannot be null");
      } else if (var2 == null) {
         throw new IllegalArgumentException("Type cannot be null");
      } else {
         this.gP = var1;
         this.oW = var2;
         this.nf = new ArrayList();
         if (var3 != null) {
            this.nf.addAll(var3);
         }
      }
   }

   public String Dw() {
      return this.gP;
   }

   public String Uv() {
      return JvmMethodSig.parse(this.gP).mname;
   }

   @Override
   public IJavaType getType() {
      return this.oW;
   }

   @Override
   public String getConstructorSignature() {
      return this.gP;
   }

   @Override
   public String getConstructorName() {
      return JvmMethodSig.parse(this.gP).mname;
   }

   public bno oW() {
      return blr.xK(this, this.gP);
   }

   @Override
   public List getArguments() {
      return this.nf;
   }

   public void q(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.nf.add(var1);
      }
   }

   @Override
   public List getSubElements() {
      return blo.q(this.nf);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      for (int var3 = 0; var3 < this.nf.size(); var3++) {
         if (this.nf.get(var3) == var1) {
            if (!(var2 instanceof IJavaExpression)) {
               return false;
            }

            this.nf.set(var3, (IJavaExpression)var2);
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
         if (this.nf.size() >= var4) {
            for (IJavaClass var7 : var5.getAnonymousClasses()) {
               if (var7.getType().equals(this.oW)) {
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
               if (var8.getType().equals(this.oW)) {
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
      bno var2 = this.oW();
      if (var2 == null) {
         this.q(var1);
         ItemClassIdentifiers var11 = this.getOrigin() != null ? ItemClassIdentifiers.METHOD_NAME_GENERATED : ItemClassIdentifiers.METHOD_NAME;
         var1.appendAndRecord(this.Uv(), var11);
         var1.paren();

         for (int var13 = 0; var13 < this.nf.size(); var13++) {
            if (var13 > 0) {
               var1.append(", ");
            }

            ((IJavaExpression)this.nf.get(var13)).generate(var1);
         }

         var1.parenClose();
         this.RF(var1);
      } else {
         if (this.getReconLambda() != null && var1.getGenerateLambdas()) {
            JavaReconLambda var3 = this.getReconLambda();
            bno var4 = blr.xK(this, var3.getImplementedMsig());
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

         this.q(var1);
         int[] var10 = new int[1];
         IJavaClass var12 = this.getGeneratedAnonymousClass(var1, var10);
         boolean var5 = this.oW != var2.getClassType();
         if (var5) {
            var1.onEolAddComment(S.L("initializer") + ": " + this.gP);
         }

         var1.appendKeyword(JavaKeyword.NEW);
         var1.append(" ");
         if (var12 != null && var12.isBuilt()) {
            int var15 = var10[0];

            try {
               var12.generate(var1, this.nf, var15, false);
            } catch (ConcurrentModificationException var9) {
               throw var9;
            }
         } else {
            long var6 = 0L;
            if (var1.getDynamicContentManager() != null) {
               var6 = var1.getDynamicContentManager().getMethodId(var2.Dw());
            }

            blk.q(var1, this.oW, false, false, var6);
            bmq.q(var1, true, this.oW(), this.gP, this.nf, 0);
         }

         this.RF(var1);
      }
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.New;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      return 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
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
         bns var2 = (bns)var1;
         if (this.gP == null) {
            if (var2.gP != null) {
               return false;
            }
         } else if (!this.gP.equals(var2.gP)) {
            return false;
         }

         if (this.nf == null) {
            if (var2.nf != null) {
               return false;
            }
         } else if (!this.nf.equals(var2.nf)) {
            return false;
         }

         if (this.oW == null) {
            if (var2.oW != null) {
               return false;
            }
         } else if (!this.oW.equals(var2.oW)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("new ");
      var1.append(this.oW);
      var1.append("(");
      int var2 = 0;

      for (IJavaExpression var4 : this.nf) {
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
      return "new " + this.oW;
   }

   public bns gO() {
      bns var1 = new bns(this.gP, this.oW);
      var1.nf = q(this.nf);
      this.q(var1);
      return var1;
   }

   @Override
   public boolean canCauseException() {
      return true;
   }
}
