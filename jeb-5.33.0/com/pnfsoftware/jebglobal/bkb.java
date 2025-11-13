package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconLambda;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bkb extends bin implements IJavaStaticField {
   @SerId(1)
   private IJavaType A;
   @SerId(value = 2, deprecated = true)
   @Deprecated
   private IJavaField kS;
   @SerId(3)
   private String wS;

   @SerCustomInitPostGraph
   private void wS() {
      if (this.kS != null) {
         this.wS = this.kS.getSignature();
         this.kS = null;
      }
   }

   public bkb(String var1, IJavaType var2) {
      this.wS = var1;
      this.A = var2;
   }

   @Override
   public IJavaType getClassType() {
      return this.A;
   }

   @Override
   public String getFieldSignature() {
      return this.wS;
   }

   @Override
   public String getFieldName() {
      return this.wS == null ? "class" : JvmFieldSig.parse(this.wS).fname;
   }

   @Override
   public boolean isPseudoFieldTypeClass() {
      return this.wS == null;
   }

   public bjq A() {
      return this.wS == null ? null : bhu.A(this, this.wS);
   }

   @Override
   public List getSubElements() {
      return bhr.pC();
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      if (this.getReconLambda() != null && var1.getGenerateLambdas()) {
         JavaReconLambda var2 = this.getReconLambda();
         bjr var3 = bhu.kS(this, var2.getImplementedMsig());
         if (var3 != null && !var1.isBeingGenerated(var3)) {
            var1.paren();
            int var4 = 0;

            for (int var6 : var2.getParameterPositions()) {
               if (var4 >= 1) {
                  var1.append(", ");
               }

               if (var6 >= 0) {
                  IJavaDefinition var7 = var3.getParameter(var6);
                  var7.generate(var1);
               } else {
                  var1.appendCommentAuto("/* " + S.L("MISSING LAMBDA PARAMETER") + " */");
               }

               var4++;
            }

            var1.parenClose();
            var1.space();
            var1.appendAndRecord("->", ItemClassIdentifiers.LAMBDA);
            var1.space();
            var1.pushReplacements(var2.getCapturedExpressions());
            var3.generate(var1, 2);
            var1.popReplacements();
            return;
         }
      }

      this.pC(var1);
      bhn.pC(var1, this.A);
      var1.append(".");
      bjq var8 = this.A();
      if (var8 == null) {
         var1.appendAndRecord(this.getFieldName(), ItemClassIdentifiers.FIELD_NAME);
      } else {
         var8.generateName(var1, false, null, this.getOrigin() != null);
      }

      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.StaticField;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      return 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bkb var2 = (bkb)var1;
         if (this.A == null) {
            if (var2.A != null) {
               return false;
            }
         } else if (!this.A.equals(var2.A)) {
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
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.getClassType() + ":" + this.getFieldName();
   }

   public bkb kS() {
      bkb var1 = new bkb(this.wS, this.A);
      this.pC(var1);
      return var1;
   }
}
