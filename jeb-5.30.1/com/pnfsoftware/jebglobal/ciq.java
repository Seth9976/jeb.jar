package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ciq implements IJavaType {
   private static final ILogger RF = GlobalLog.getLogger(ciq.class);
   @SerId(1)
   private ciq.eo xK;
   @SerId(2)
   private String Dw;
   @SerId(3)
   private int Uv;
   @SerId(4)
   private ciq oW;
   @SerId(5)
   private ciq gO;
   @SerId(6)
   private cis nf;
   @SerId(7)
   private int gP;
   static final String q = "L;";

   ciq(ciq var1, int var2, ciq var3, cis var4) {
      this.xK = ciq.eo.JY;
      this.Dw = Strings.generate('[', var2) + (var1.Dw != null ? var1.Dw : RF(var1.xK));
      this.Uv = var2;
      this.oW = var1;
      this.gO = var3;
      this.nf = var4;
   }

   ciq(String var1, int var2, cis var3) {
      this.xK = ciq.eo.JY;
      this.Dw = var1;
      this.Uv = 0;
      this.oW = null;
      this.gO = null;
      this.gP = var2;
      this.nf = var3;
   }

   ciq(ciq.eo var1, cis var2) {
      if (var1 == ciq.eo.JY) {
         throw new IllegalArgumentException("Expected a non-object simple type, got: " + var1);
      } else {
         this.xK = var1;
         this.Dw = null;
         this.Uv = 0;
         this.oW = null;
         this.gO = null;
         this.nf = var2;
      }
   }

   public cis q() {
      return this.nf;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public String getName() {
      if (this.Dw == null) {
         switch (cir.q[this.xK.ordinal()]) {
            case 1:
               return "Z";
            case 2:
               return "B";
            case 3:
               return "C";
            case 4:
               return "S";
            case 5:
               return "I";
            case 6:
               return "J";
            case 7:
               return "F";
            case 8:
               return "D";
            case 9:
               return "V";
            default:
               return "?";
         }
      } else {
         return this.Dw;
      }
   }

   public String RF() {
      if (!this.isClassOrInterface()) {
         throw new RuntimeException("Not a class/interface: " + this);
      } else {
         return this.Dw;
      }
   }

   @Override
   public int getDimensions() {
      if (!this.isArray()) {
         throw new RuntimeException("Not an array");
      } else {
         return this.Uv;
      }
   }

   @Override
   public IJavaType getArrayElementType() {
      if (!this.isArray()) {
         throw new RuntimeException("Not an array");
      } else {
         return this.oW;
      }
   }

   @Override
   public IJavaType getArrayTypeDimensionBelow() {
      if (!this.isArray()) {
         throw new RuntimeException("Not an array");
      } else {
         return this.gO;
      }
   }

   @Override
   public boolean isVoid() {
      return this.xK == ciq.eo.q;
   }

   @Override
   public boolean isBoolean() {
      return this.xK == ciq.eo.RF;
   }

   @Override
   public boolean isByte() {
      return this.xK == ciq.eo.xK;
   }

   @Override
   public boolean isChar() {
      return this.xK == ciq.eo.Dw;
   }

   @Override
   public boolean isShort() {
      return this.xK == ciq.eo.Uv;
   }

   @Override
   public boolean isInt() {
      return this.xK == ciq.eo.oW;
   }

   @Override
   public boolean isLong() {
      return this.xK == ciq.eo.gO;
   }

   @Override
   public boolean isFloat() {
      return this.xK == ciq.eo.nf;
   }

   @Override
   public boolean isDouble() {
      return this.xK == ciq.eo.gP;
   }

   @Override
   public boolean isSingleSlotWildcard() {
      return this.xK == ciq.eo.za;
   }

   @Override
   public boolean isDoubleSlotWildcard() {
      return this.xK == ciq.eo.lm;
   }

   @Override
   public boolean isSmallIntegerWildcard() {
      return this.xK == ciq.eo.zz;
   }

   @Override
   public boolean isGenericObjectWildcard() {
      return this.xK == ciq.eo.JY && this.Uv == 0 && "L;".equals(this.Dw);
   }

   @Override
   public boolean isObjectWildcardExtends() {
      return this.gP == 1;
   }

   @Override
   public boolean isObjectWildcardSuper() {
      return this.gP == -1;
   }

   @Override
   public boolean isPrimitive() {
      return this.xK != ciq.eo.q && this.xK != ciq.eo.JY && this.xK != ciq.eo.za && this.xK != ciq.eo.lm;
   }

   @Override
   public boolean isObject() {
      return this.xK == ciq.eo.JY;
   }

   @Override
   public boolean isClassOrInterface() {
      return this.xK == ciq.eo.JY && !this.isArray();
   }

   @Override
   public boolean isArray() {
      return this.Uv >= 1;
   }

   @Override
   public boolean isJavaLangObject() {
      return this.isClassOrInterface() && this.gP == 0 && this.Dw.equals("Ljava/lang/Object;");
   }

   @Override
   public boolean isJavaLangString() {
      return this.isClassOrInterface() && this.gP == 0 && this.Dw.equals("Ljava/lang/String;");
   }

   @Override
   public boolean isJavaLangClass() {
      return this.isClassOrInterface() && this.gP == 0 && this.Dw.equals("Ljava/lang/Class;");
   }

   @Override
   public boolean isValidForRendering() {
      ciq.eo var1 = this.isArray() ? this.oW.xK : this.xK;
      switch (var1) {
         case RF:
         case xK:
         case Dw:
         case Uv:
         case oW:
         case gO:
         case nf:
         case gP:
         case q:
         case JY:
            return true;
         default:
            return false;
      }
   }

   public static void q(IJavaType var0) {
      if (!(var0 instanceof ciq) || !((ciq)var0).isValidForRendering()) {
         throw new RuntimeException("Illegal type for AST generation");
      }
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
   public boolean compareSimple(IJavaType var1) {
      return this.xK == ((ciq)var1).xK;
   }

   private void q(StringBuilder var1, boolean var2, boolean var3) {
      if (this.Dw != null) {
         if (!var3) {
            if (this.gP == 1) {
               var1.append("?_extends_");
            } else if (this.gP == -1) {
               var1.append("?_super_");
            }
         }

         if (var2) {
            var1.append(this.Dw);
         } else {
            var1.append(JavaTypeUtil.generateClassnameStandardRepresentation(this.Dw));
         }
      } else if (var2) {
         var1.append(RF(this.xK));
      } else {
         var1.append(q(this.xK));
      }
   }

   @Override
   public String getSignature() {
      return this.q(false);
   }

   public String q(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      if (!this.isArray()) {
         this.q(var2, true, var1);
      } else {
         for (int var3 = 0; var3 < this.Uv; var3++) {
            var2.append("[");
         }

         this.oW.q(var2, true, var1);
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      DFormattingContext var1 = new DFormattingContext();
      this.format(var1);
      return var1.toString();
   }

   private void q(DFormattingContext var1) {
      if (this.Dw != null) {
         if (this.gP == 1) {
            var1.append("?_extends_");
         } else if (this.gP == -1) {
            var1.append("?_super_");
         }

         var1.append(JavaTypeUtil.generateClassnameStandardRepresentation(this.Dw));
      } else {
         var1.append(q(this.xK));
      }
   }

   @Override
   public void format(DFormattingContext var1) {
      if (!this.isArray()) {
         this.q(var1);
      } else {
         this.oW.q(var1);

         for (int var2 = 0; var2 < this.Uv; var2++) {
            var1.append("[]");
         }
      }
   }

   @Override
   public void formatArray(DFormattingContext var1, IDExpression var2) {
      if (!this.isArray()) {
         throw new RuntimeException("Expected an array type");
      } else {
         this.oW.q(var1);
         var1.bracket();
         var2.format(var1);
         var1.bracketClose();

         for (int var3 = 1; var3 < this.Uv; var3++) {
            var1.append("[]");
         }
      }
   }

   private static String q(ciq.eo var0) {
      switch (var0) {
         case RF:
            return "boolean";
         case xK:
            return "byte";
         case Dw:
            return "char";
         case Uv:
            return "short";
         case oW:
            return "int";
         case gO:
            return "long";
         case nf:
            return "float";
         case gP:
            return "double";
         case q:
            return "void";
         case JY:
         default:
            throw new RuntimeException("Expected a simple type, got: " + var0);
         case za:
            return "SREG";
         case lm:
            return "DREG";
         case zz:
            return "_int";
      }
   }

   @Override
   public boolean isLegal() {
      switch (this.xK) {
         case q:
            return false;
         default:
            return true;
      }
   }

   @Override
   public boolean isUndefined() {
      switch (this.xK) {
         case q:
         case za:
         case lm:
            return true;
         case JY:
         default:
            return false;
      }
   }

   @Override
   public boolean isDefined() {
      switch (this.xK) {
         case q:
         case za:
         case lm:
            return false;
         case JY:
         default:
            return true;
      }
   }

   @Override
   public boolean isDetermined() {
      return this.xK != ciq.eo.q && !this.isWildcard();
   }

   @Override
   public boolean isWildcard() {
      switch (this.xK) {
         case za:
         case lm:
         case zz:
            return true;
         default:
            return this.gP != 0 ? true : this.isGenericObjectWildcard();
      }
   }

   @Override
   public IJavaType asWildcardExt() {
      return this.RF(true);
   }

   @Override
   public IJavaType asWildcardSup() {
      return this.RF(false);
   }

   public IJavaType RF(boolean var1) {
      switch (this.xK) {
         case RF:
         case gO:
         case nf:
         case gP:
            return this;
         case xK:
         case Dw:
         case Uv:
            if (var1) {
               return this;
            }

            return this.nf.zz;
         case oW:
            if (!var1) {
               return this.nf.oW;
            }

            return this.nf.zz;
         case q:
         case za:
         case lm:
         case zz:
            return this;
         case JY:
            if (this.isGenericObjectWildcard()) {
               return this.nf.JY;
            } else if (this.isArray()) {
               return this;
            } else {
               Assert.a(this.isClassOrInterface());
               if (this.isObjectWildcardExtends()) {
                  if (var1) {
                     return this;
                  }
               } else if (this.isObjectWildcardSuper() && !var1) {
                  return this;
               }

               return this.nf.createWildcardType(this.getName(), var1);
            }
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean isSmallInt() {
      switch (this.xK) {
         case xK:
         case Dw:
         case Uv:
         case oW:
         case zz:
            return true;
         case gO:
         case nf:
         case gP:
         case q:
         case JY:
         case za:
         case lm:
         default:
            return false;
      }
   }

   @Override
   public boolean isSpecificInteger() {
      switch (this.xK) {
         case xK:
         case Dw:
         case Uv:
         case oW:
         case gO:
            return true;
         default:
            return false;
      }
   }

   @Override
   public boolean isGenericInteger() {
      switch (this.xK) {
         case RF:
         case xK:
         case Dw:
         case Uv:
         case oW:
         case gO:
         case zz:
            return true;
         case nf:
         case gP:
         case q:
         case JY:
         case za:
         case lm:
         default:
            return false;
      }
   }

   @Override
   public boolean isFloatingPointType() {
      switch (this.xK) {
         case nf:
         case gP:
            return true;
         default:
            return false;
      }
   }

   @Override
   public boolean isSingleSlot() {
      switch (this.xK) {
         case RF:
         case xK:
         case Dw:
         case Uv:
         case oW:
         case nf:
         case JY:
         case za:
         case zz:
            return true;
         case gO:
         case gP:
         case q:
         case lm:
         default:
            return false;
      }
   }

   @Override
   public boolean isDoubleSlot() {
      switch (this.xK) {
         case gO:
         case gP:
         case lm:
            return true;
         default:
            return false;
      }
   }

   @Override
   public int getSlotCount() {
      if (this.isSingleSlot()) {
         return 1;
      } else if (this.isDoubleSlot()) {
         return 2;
      } else {
         throw new RuntimeException();
      }
   }

   @Override
   public boolean canSetType(IJavaType var1, boolean var2) {
      if (this.xK == ciq.eo.q || var1 == null || ((ciq)var1).xK == ciq.eo.q) {
         throw new RuntimeException(Strings.ff("Invalid types: %s, %s", this, var1));
      } else if (this.xK == ciq.eo.zz) {
         switch (((ciq)var1).xK) {
            case xK:
            case Dw:
            case Uv:
            case oW:
            case zz:
               return true;
            case gO:
            case nf:
            case gP:
            case q:
            case JY:
            case za:
            case lm:
            default:
               return false;
         }
      } else if (this.xK == ciq.eo.za) {
         switch (((ciq)var1).xK) {
            case RF:
            case xK:
            case Dw:
            case Uv:
            case oW:
            case nf:
            case JY:
            case za:
            case zz:
               return true;
            case gO:
            case gP:
            case q:
            case lm:
            default:
               return false;
         }
      } else if (this.xK == ciq.eo.lm) {
         switch (((ciq)var1).xK) {
            case gO:
            case gP:
            case lm:
               return true;
            default:
               return false;
         }
      } else if (this.isGenericObjectWildcard()) {
         return var1.isObject();
      } else if (this.equals(var1)) {
         return true;
      } else if (this.isObjectWildcardExtends() && this.q(true).equals("Ljava/lang/Object;") && var1.isGenericObjectWildcard()) {
         return false;
      } else {
         if (var2) {
            IDTypeInfoProvider var3 = this.nf.getTypeInfoProvider();
            ciq var4 = (ciq)var1;
            if (var3 != null && this.isObject() && var4.isObject()) {
               if (this.isObjectWildcardExtends() && !var4.isObjectWildcardSuper()) {
                  if (var3.isCompatible(var4.q(true), this.q(true))) {
                     return true;
                  }
               } else if (this.isObjectWildcardSuper() && !var4.isObjectWildcardExtends() && var3.isCompatible(this.q(true), var4.q(true))) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public IJavaType compareAndGetMostPrecise(IJavaType var1) {
      return RF(this, (ciq)var1);
   }

   static ciq q(ciq var0, ciq var1) {
      return q(var0, var1, true);
   }

   static ciq q(ciq var0, ciq var1, boolean var2) {
      if (var0.compareSimple(var1)) {
         if (var0.isGenericObjectWildcard()) {
            return var1;
         } else {
            return var1.isGenericObjectWildcard() ? var0 : var0;
         }
      } else if (var0.xK == ciq.eo.q) {
         return var1;
      } else if (var1.xK == ciq.eo.q) {
         return var0;
      } else {
         if (var0.xK == ciq.eo.zz) {
            switch (var1.xK) {
               case RF:
                  return var0;
               case xK:
               case Dw:
               case Uv:
               case oW:
                  return var1;
            }
         }

         if (var1.xK == ciq.eo.zz) {
            switch (var0.xK) {
               case RF:
                  return var1;
               case xK:
               case Dw:
               case Uv:
               case oW:
                  return var0;
            }
         }

         if (var0.xK == ciq.eo.za) {
            switch (var1.xK) {
               case RF:
               case xK:
               case Dw:
               case Uv:
               case oW:
               case nf:
               case JY:
               case zz:
                  return var1;
               case gO:
               case gP:
               case q:
               case za:
               case lm:
            }
         }

         if (var1.xK == ciq.eo.za) {
            switch (var0.xK) {
               case RF:
               case xK:
               case Dw:
               case Uv:
               case oW:
               case nf:
               case JY:
               case zz:
                  return var0;
               case gO:
               case gP:
               case q:
               case za:
               case lm:
            }
         }

         if (var0.xK == ciq.eo.lm) {
            switch (var1.xK) {
               case gO:
               case gP:
                  return var1;
            }
         }

         if (var1.xK == ciq.eo.lm) {
            switch (var0.xK) {
               case gO:
               case gP:
                  return var1;
            }
         }

         if (var2) {
            throw new RuntimeException(Strings.ff("Types cannot be compared, they're not compatible: %s, %s", var0, var1));
         } else {
            return null;
         }
      }
   }

   static ciq RF(ciq var0, ciq var1) {
      if (var0.compareSimple(var1)) {
         if (var0.isObject()) {
            if (var0.isGenericObjectWildcard()) {
               return var1;
            }

            if (var1.isGenericObjectWildcard()) {
               return var0;
            }

            if (var0.isJavaLangObject()) {
               return var1;
            }

            if (var1.isJavaLangObject()) {
               return var0;
            }
         }

         return var0;
      } else if (var0.xK == ciq.eo.q) {
         return var1;
      } else if (var1.xK == ciq.eo.q) {
         return var0;
      } else {
         if (var0.xK == ciq.eo.zz) {
            switch (var1.xK) {
               case RF:
                  return var0;
               case xK:
               case Dw:
               case Uv:
               case oW:
                  return var1;
            }
         }

         if (var1.xK == ciq.eo.zz) {
            switch (var0.xK) {
               case RF:
                  return var1;
               case xK:
               case Dw:
               case Uv:
               case oW:
                  return var0;
            }
         }

         if (var0.xK == ciq.eo.za) {
            switch (var1.xK) {
               case RF:
               case xK:
               case Dw:
               case Uv:
               case oW:
               case nf:
               case JY:
               case zz:
                  return var1;
               case gO:
               case gP:
               case q:
               case za:
               case lm:
            }
         }

         if (var1.xK == ciq.eo.za) {
            switch (var0.xK) {
               case RF:
               case xK:
               case Dw:
               case Uv:
               case oW:
               case nf:
               case JY:
               case zz:
                  return var0;
               case gO:
               case gP:
               case q:
               case za:
               case lm:
            }
         }

         if (var0.xK == ciq.eo.lm) {
            switch (var1.xK) {
               case gO:
               case gP:
                  return var1;
            }
         }

         if (var1.xK == ciq.eo.lm) {
            switch (var0.xK) {
               case gO:
               case gP:
                  return var0;
            }
         }

         return null;
      }
   }

   private static char RF(ciq.eo var0) {
      switch (var0) {
         case RF:
            return 'Z';
         case xK:
            return 'B';
         case Dw:
            return 'C';
         case Uv:
            return 'S';
         case oW:
            return 'I';
         case gO:
            return 'J';
         case nf:
            return 'F';
         case gP:
            return 'D';
         default:
            throw new RuntimeException("Unexpected simple type: " + var0);
      }
   }

   @Override
   public int getEncodingBitsize() {
      switch (this.xK) {
         case RF:
            return 1;
         case xK:
            return 8;
         case Dw:
         case Uv:
            return 16;
         case oW:
         case nf:
         case JY:
         case za:
         case zz:
            return 32;
         case gO:
         case gP:
         case lm:
            return 64;
         case q:
            return 0;
         default:
            throw new RuntimeException();
      }
   }

   @Ser
   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO,
      nf,
      gP,
      za,
      lm,
      zz,
      JY;
   }
}
