package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaInstanceField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public class bnf extends bmk implements IJavaInstanceField {
   @SerId(1)
   private IJavaExpression Dw;
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

   public bnf(String var1, IJavaExpression var2) {
      this.oW = var1;
      this.Dw = var2;
   }

   @Override
   public IJavaExpression getInstance() {
      return this.Dw;
   }

   @Override
   public String getFieldSignature() {
      return this.oW;
   }

   @Override
   public String getFieldName() {
      return this.oW == null ? "length" : JvmFieldSig.parse(this.oW).fname;
   }

   @Override
   public boolean isPseudoFieldArrayLength() {
      return this.oW == null;
   }

   public bnn xK() {
      return this.oW == null ? null : blr.RF(this, this.oW);
   }

   @Override
   public void setInstance(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
      }
   }

   @Override
   public List getSubElements() {
      return blo.q(this.Dw);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.Dw == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.Dw = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      boolean var2 = false;
      this.q(var1);
      JavaOutputSink.CaptureInfo var3 = var1.getCurrentAnonymousCaptureInfo();
      if (var3 != null) {
         IJavaExpression var4 = (IJavaExpression)var3.synth.get(this);
         if (var4 != null) {
            var4.generate(var1);
            var2 = true;
         }
      }

      if (!var2) {
         bnn var9 = this.xK();
         if (var9 != null) {
            boolean var5 = var9.hasFlags(64);
            boolean var6 = false;
            String var7 = var9.getName();
            if (var7.startsWith("this$")) {
               int var8 = Conversion.stringToInt(var7.substring(5), -1, 10);
               if (var8 >= 0) {
                  var6 = true;
               }
            }

            if (var5 || var6) {
               IJavaType var11 = var9.getType();
               if (var11 != null && var11.isClassOrInterface()) {
                  blk.q(var1, var11);
                  var1.append(".");
                  var1.appendKeyword(JavaKeyword.THIS);
                  var2 = true;
               }
            }
         }

         if (!var2) {
            if (var9 == null) {
               this.Dw.generate(var1);
               var1.append(".");
               var1.appendAndRecord(this.getFieldName(), ItemClassIdentifiers.FIELD_NAME);
            } else {
               String var10 = var9.xK(var1);
               if (var10 == null || !q(var1, this.Dw, var10)) {
                  this.Dw.generate(var1);
                  var1.append(".");
               }

               var9.generateName(var1, false, null, this.getOrigin() != null);
            }
         }
      }

      this.RF(var1);
   }

   static boolean q(JavaOutputSink var0, IJavaExpression var1, String var2) {
      if (var0.getDoNotGenerateThisIfPossible() && var1 instanceof bnc && "this".equals(((bnc)var1).getName()) && var0.getContainingMethods().size() == 1) {
         IJavaMethod var3 = var0.getCurrentContainingMethod();
         boolean var4 = false;

         for (IJavaIdentifier var6 : var3.getIdentifierManager().getIdentifiers()) {
            String var7 = var6.getGeneratedName(var0);
            if (var2.equals(var7)) {
               var4 = true;
               break;
            }
         }

         if (!var4) {
            return true;
         }
      }

      return false;
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.InstanceField;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.Dw.hashCode();
      return 31 * var1 + (this.oW == null ? 0 : this.oW.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bnf var2 = (bnf)var1;
         if (this.oW == null) {
            if (var2.oW != null) {
               return false;
            }
         } else if (!this.oW.equals(var2.oW)) {
            return false;
         }

         return this.Dw.equals(var2.Dw);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.Dw + "." + this.getFieldName();
   }

   public bnf Dw() {
      bnf var1 = new bnf(this.oW, this.Dw.duplicate());
      this.q(var1);
      return var1;
   }
}
