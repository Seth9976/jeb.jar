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
public class bji extends bin implements IJavaInstanceField {
   @SerId(1)
   private IJavaExpression A;
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

   public bji(String var1, IJavaExpression var2) {
      this.wS = var1;
      this.A = var2;
   }

   @Override
   public IJavaExpression getInstance() {
      return this.A;
   }

   @Override
   public String getFieldSignature() {
      return this.wS;
   }

   @Override
   public String getFieldName() {
      return this.wS == null ? "length" : JvmFieldSig.parse(this.wS).fname;
   }

   @Override
   public boolean isPseudoFieldArrayLength() {
      return this.wS == null;
   }

   public bjq A() {
      return this.wS == null ? null : bhu.A(this, this.wS);
   }

   @Override
   public void setInstance(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
      }
   }

   @Override
   public List getSubElements() {
      return bhr.pC(this.A);
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.A == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.A = (IJavaExpression)var2;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void generate(JavaOutputSink var1) {
      boolean var2 = false;
      this.pC(var1);
      JavaOutputSink.CaptureInfo var3 = var1.getCurrentAnonymousCaptureInfo();
      if (var3 != null) {
         IJavaExpression var4 = (IJavaExpression)var3.synth.get(this);
         if (var4 != null) {
            var4.generate(var1);
            var2 = true;
         }
      }

      if (!var2) {
         bjq var9 = this.A();
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
                  bhn.pC(var1, var11);
                  var1.append(".");
                  var1.appendKeyword(JavaKeyword.THIS);
                  var2 = true;
               }
            }
         }

         if (!var2) {
            if (var9 == null) {
               this.A.generate(var1);
               var1.append(".");
               var1.appendAndRecord(this.getFieldName(), ItemClassIdentifiers.FIELD_NAME);
            } else {
               String var10 = var9.kS(var1);
               if (var10 == null || !pC(var1, this.A, var10)) {
                  this.A.generate(var1);
                  var1.append(".");
               }

               var9.generateName(var1, false, null, this.getOrigin() != null);
            }
         }
      }

      this.A(var1);
   }

   static boolean pC(JavaOutputSink var0, IJavaExpression var1, String var2) {
      if (var0.getDoNotGenerateThisIfPossible() && var1 instanceof bjf && "this".equals(((bjf)var1).getName()) && var0.getContainingMethods().size() == 1) {
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
      var1 = 31 * var1 + this.A.hashCode();
      return 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         bji var2 = (bji)var1;
         if (this.wS == null) {
            if (var2.wS != null) {
               return false;
            }
         } else if (!this.wS.equals(var2.wS)) {
            return false;
         }

         return this.A.equals(var2.A);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.A + "." + this.getFieldName();
   }

   public bji kS() {
      bji var1 = new bji(this.wS, this.A.duplicate());
      this.pC(var1);
      return var1;
   }
}
