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
public class bny extends bmk implements IJavaStaticField {
   @SerId(1)
   private IJavaType Dw;
   @SerId(value = 2, deprecated = true)
   @Deprecated
   private IJavaField Uv;
   @SerId(3)
   private String oW;

   @SerCustomInitPostGraph
   private void Uv() {
      if (this.Uv != null) {
         this.oW = this.Uv.getSignature();
         this.Uv = null;
      }
   }

   public bny(String var1, IJavaType var2) {
      this.oW = var1;
      this.Dw = var2;
   }

   @Override
   public IJavaType getClassType() {
      return this.Dw;
   }

   @Override
   public String getFieldSignature() {
      return this.oW;
   }

   @Override
   public String getFieldName() {
      return this.oW == null ? "class" : JvmFieldSig.parse(this.oW).fname;
   }

   @Override
   public boolean isPseudoFieldTypeClass() {
      return this.oW == null;
   }

   public bnn xK() {
      return this.oW == null ? null : blr.RF(this, this.oW);
   }

   @Override
   public List getSubElements() {
      return blo.q();
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      return false;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      if (this.getReconLambda() != null && var1.getGenerateLambdas()) {
         JavaReconLambda var2 = this.getReconLambda();
         bno var3 = blr.xK(this, var2.getImplementedMsig());
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

      this.q(var1);
      blk.q(var1, this.Dw);
      var1.append(".");
      bnn var8 = this.xK();
      if (var8 == null) {
         var1.appendAndRecord(this.getFieldName(), ItemClassIdentifiers.FIELD_NAME);
      } else {
         var8.generateName(var1, false, null, this.getOrigin() != null);
      }

      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.StaticField;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      return 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bny var2 = (bny)var1;
         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
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
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.getClassType() + ":" + this.getFieldName();
   }

   public bny Dw() {
      bny var1 = new bny(this.oW, this.Dw);
      this.q(var1);
      return var1;
   }
}
