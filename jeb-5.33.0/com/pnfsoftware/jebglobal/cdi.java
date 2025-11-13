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
public class cdi implements IJavaType {
   private static final ILogger pC = GlobalLog.getLogger(cdi.class);
   @SerId(1)
   private cdi.Av A;
   @SerId(2)
   private String kS;
   @SerId(3)
   private int wS;
   @SerId(4)
   private cdi UT;
   @SerId(5)
   private cdi E;
   @SerId(6)
   private cdk sY;
   @SerId(7)
   private int ys;

   cdi(cdi var1, int var2, cdi var3, cdk var4) {
      this.A = cdi.Av.WR;
      this.kS = Strings.generate('[', var2) + (var1.kS != null ? var1.kS : A(var1.A));
      this.wS = var2;
      this.UT = var1;
      this.E = var3;
      this.sY = var4;
   }

   cdi(String var1, int var2, cdk var3) {
      this.A = cdi.Av.WR;
      this.kS = var1;
      this.wS = 0;
      this.UT = null;
      this.E = null;
      this.ys = var2;
      this.sY = var3;
   }

   cdi(cdi.Av var1, cdk var2) {
      if (var1 == cdi.Av.WR) {
         throw new IllegalArgumentException("Expected a non-object simple type, got: " + var1);
      } else {
         this.A = var1;
         this.kS = null;
         this.wS = 0;
         this.UT = null;
         this.E = null;
         this.sY = var2;
      }
   }

   public cdk pC() {
      return this.sY;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public String getName() {
      if (this.kS == null) {
         switch (this.A) {
            case A:
               return "Z";
            case kS:
               return "B";
            case wS:
               return "C";
            case UT:
               return "S";
            case E:
               return "I";
            case sY:
               return "J";
            case ys:
               return "F";
            case ld:
               return "D";
            case pC:
               return "V";
            default:
               return "?";
         }
      } else {
         return this.kS;
      }
   }

   @Override
   public int getDimensions() {
      if (!this.isArray()) {
         throw new RuntimeException("Not an array");
      } else {
         return this.wS;
      }
   }

   @Override
   public IJavaType getArrayElementType() {
      if (!this.isArray()) {
         throw new RuntimeException("Not an array");
      } else {
         return this.UT;
      }
   }

   @Override
   public IJavaType getArrayTypeDimensionBelow() {
      if (!this.isArray()) {
         throw new RuntimeException("Not an array");
      } else {
         return this.E;
      }
   }

   @Override
   public boolean isVoid() {
      return this.A == cdi.Av.pC;
   }

   @Override
   public boolean isBoolean() {
      return this.A == cdi.Av.A;
   }

   @Override
   public boolean isByte() {
      return this.A == cdi.Av.kS;
   }

   @Override
   public boolean isChar() {
      return this.A == cdi.Av.wS;
   }

   @Override
   public boolean isShort() {
      return this.A == cdi.Av.UT;
   }

   @Override
   public boolean isInt() {
      return this.A == cdi.Av.E;
   }

   @Override
   public boolean isLong() {
      return this.A == cdi.Av.sY;
   }

   @Override
   public boolean isFloat() {
      return this.A == cdi.Av.ys;
   }

   @Override
   public boolean isDouble() {
      return this.A == cdi.Av.ld;
   }

   @Override
   public boolean isSingleSlotWildcard() {
      return this.A == cdi.Av.gp;
   }

   @Override
   public boolean isDoubleSlotWildcard() {
      return this.A == cdi.Av.oT;
   }

   @Override
   public boolean isSmallIntegerWildcard() {
      return this.A == cdi.Av.fI;
   }

   @Override
   public boolean isGenericObjectWildcard() {
      return this.A == cdi.Av.WR && this.wS == 0 && "L;".equals(this.kS);
   }

   @Override
   public boolean isObjectWildcardExtends() {
      return this.ys == 1;
   }

   @Override
   public boolean isObjectWildcardSuper() {
      return this.ys == -1;
   }

   @Override
   public boolean isPrimitive() {
      return this.A != cdi.Av.pC && this.A != cdi.Av.WR && this.A != cdi.Av.gp && this.A != cdi.Av.oT;
   }

   @Override
   public boolean isObject() {
      return this.A == cdi.Av.WR;
   }

   @Override
   public boolean isClassOrInterface() {
      return this.A == cdi.Av.WR && !this.isArray();
   }

   @Override
   public boolean isArray() {
      return this.wS >= 1;
   }

   @Override
   public boolean isJavaLangObject() {
      return this.isClassOrInterface() && this.ys == 0 && this.kS.equals("Ljava/lang/Object;");
   }

   @Override
   public boolean isJavaLangString() {
      return this.isClassOrInterface() && this.ys == 0 && this.kS.equals("Ljava/lang/String;");
   }

   @Override
   public boolean isJavaLangClass() {
      return this.isClassOrInterface() && this.ys == 0 && this.kS.equals("Ljava/lang/Class;");
   }

   @Override
   public boolean isValidForRendering() {
      cdi.Av var1 = this.isArray() ? this.UT.A : this.A;
      switch (var1) {
         case A:
         case kS:
         case wS:
         case UT:
         case E:
         case sY:
         case ys:
         case ld:
         case pC:
         case WR:
            return true;
         default:
            return false;
      }
   }

   public static void pC(IJavaType var0) {
      if (!(var0 instanceof cdi) || !((cdi)var0).isValidForRendering()) {
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
      return this.A == ((cdi)var1).A;
   }

   private void pC(StringBuilder var1, boolean var2, boolean var3) {
      if (this.kS != null) {
         if (!var3) {
            if (this.ys == 1) {
               var1.append("?_extends_");
            } else if (this.ys == -1) {
               var1.append("?_super_");
            }
         }

         if (var2) {
            var1.append(this.kS);
         } else {
            var1.append(JavaTypeUtil.generateClassnameStandardRepresentation(this.kS));
         }
      } else if (var2) {
         var1.append(A(this.A));
      } else {
         var1.append(pC(this.A));
      }
   }

   @Override
   public String getSignature() {
      return this.pC(false);
   }

   public String pC(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      if (!this.isArray()) {
         this.pC(var2, true, var1);
      } else {
         for (int var3 = 0; var3 < this.wS; var3++) {
            var2.append("[");
         }

         this.UT.pC(var2, true, var1);
      }

      return var2.toString();
   }

   @Override
   public String toString() {
      DFormattingContext var1 = new DFormattingContext();
      this.format(var1);
      return var1.toString();
   }

   private void pC(DFormattingContext var1) {
      if (this.kS != null) {
         if (this.ys == 1) {
            var1.append("?_extends_");
         } else if (this.ys == -1) {
            var1.append("?_super_");
         }

         var1.append(JavaTypeUtil.generateClassnameStandardRepresentation(this.kS));
      } else {
         var1.append(pC(this.A));
      }
   }

   @Override
   public void format(DFormattingContext var1) {
      if (!this.isArray()) {
         this.pC(var1);
      } else {
         this.UT.pC(var1);

         for (int var2 = 0; var2 < this.wS; var2++) {
            var1.append("[]");
         }
      }
   }

   @Override
   public void formatArray(DFormattingContext var1, IDExpression var2) {
      if (!this.isArray()) {
         throw new RuntimeException("Expected an array type");
      } else {
         this.UT.pC(var1);
         var1.bracket();
         var2.format(var1);
         var1.bracketClose();

         for (int var3 = 1; var3 < this.wS; var3++) {
            var1.append("[]");
         }
      }
   }

   private static String pC(cdi.Av var0) {
      switch (var0) {
         case A:
            return "boolean";
         case kS:
            return "byte";
         case wS:
            return "char";
         case UT:
            return "short";
         case E:
            return "int";
         case sY:
            return "long";
         case ys:
            return "float";
         case ld:
            return "double";
         case pC:
            return "void";
         case WR:
         default:
            throw new RuntimeException("Expected a simple type, got: " + var0);
         case gp:
            return "SREG";
         case oT:
            return "DREG";
         case fI:
            return "_int";
      }
   }

   @Override
   public boolean isLegal() {
      switch (this.A) {
         case pC:
            return false;
         default:
            return true;
      }
   }

   @Override
   public boolean isUndefined() {
      switch (this.A) {
         case pC:
         case gp:
         case oT:
            return true;
         case WR:
         default:
            return false;
      }
   }

   @Override
   public boolean isDefined() {
      switch (this.A) {
         case pC:
         case gp:
         case oT:
            return false;
         case WR:
         default:
            return true;
      }
   }

   @Override
   public boolean isDetermined() {
      return this.A != cdi.Av.pC && !this.isWildcard();
   }

   @Override
   public boolean isWildcard() {
      switch (this.A) {
         case gp:
         case oT:
         case fI:
            return true;
         default:
            return this.ys != 0 ? true : this.isGenericObjectWildcard();
      }
   }

   @Override
   public IJavaType asWildcardExt() {
      return this.A(true);
   }

   @Override
   public IJavaType asWildcardSup() {
      return this.A(false);
   }

   public IJavaType A(boolean var1) {
      switch (this.A) {
         case A:
         case sY:
         case ys:
         case ld:
            return this;
         case kS:
         case wS:
         case UT:
            if (var1) {
               return this;
            }

            return this.sY.fI;
         case E:
            if (!var1) {
               return this.sY.E;
            }

            return this.sY.fI;
         case pC:
         case gp:
         case oT:
         case fI:
            return this;
         case WR:
            if (this.isGenericObjectWildcard()) {
               return this.sY.WR;
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

               return this.sY.createWildcardType(this.getName(), var1);
            }
         default:
            throw new RuntimeException();
      }
   }

   @Override
   public boolean isSmallInt() {
      switch (this.A) {
         case kS:
         case wS:
         case UT:
         case E:
         case fI:
            return true;
         case sY:
         case ys:
         case ld:
         case pC:
         case WR:
         case gp:
         case oT:
         default:
            return false;
      }
   }

   @Override
   public boolean isSpecificInteger() {
      switch (this.A) {
         case kS:
         case wS:
         case UT:
         case E:
         case sY:
            return true;
         default:
            return false;
      }
   }

   @Override
   public boolean isGenericInteger() {
      switch (this.A) {
         case A:
         case kS:
         case wS:
         case UT:
         case E:
         case sY:
         case fI:
            return true;
         case ys:
         case ld:
         case pC:
         case WR:
         case gp:
         case oT:
         default:
            return false;
      }
   }

   @Override
   public boolean isFloatingPointType() {
      switch (this.A) {
         case ys:
         case ld:
            return true;
         default:
            return false;
      }
   }

   @Override
   public boolean isSingleSlot() {
      switch (this.A) {
         case A:
         case kS:
         case wS:
         case UT:
         case E:
         case ys:
         case WR:
         case gp:
         case fI:
            return true;
         case sY:
         case ld:
         case pC:
         case oT:
         default:
            return false;
      }
   }

   @Override
   public boolean isDoubleSlot() {
      switch (this.A) {
         case sY:
         case ld:
         case oT:
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
      if (this.A == cdi.Av.pC || var1 == null || ((cdi)var1).A == cdi.Av.pC) {
         throw new RuntimeException(Strings.ff("Invalid types: %s, %s", this, var1));
      } else if (this.A == cdi.Av.fI) {
         switch (((cdi)var1).A) {
            case kS:
            case wS:
            case UT:
            case E:
            case fI:
               return true;
            case sY:
            case ys:
            case ld:
            case pC:
            case WR:
            case gp:
            case oT:
            default:
               return false;
         }
      } else if (this.A == cdi.Av.gp) {
         switch (((cdi)var1).A) {
            case A:
            case kS:
            case wS:
            case UT:
            case E:
            case ys:
            case WR:
            case gp:
            case fI:
               return true;
            case sY:
            case ld:
            case pC:
            case oT:
            default:
               return false;
         }
      } else if (this.A == cdi.Av.oT) {
         switch (((cdi)var1).A) {
            case sY:
            case ld:
            case oT:
               return true;
            default:
               return false;
         }
      } else if (this.isGenericObjectWildcard()) {
         return var1.isObject();
      } else if (this.equals(var1)) {
         return true;
      } else if (this.isObjectWildcardExtends() && this.pC(true).equals("Ljava/lang/Object;") && var1.isGenericObjectWildcard()) {
         return false;
      } else {
         if (var2) {
            IDTypeInfoProvider var3 = this.sY.getTypeInfoProvider();
            cdi var4 = (cdi)var1;
            if (var3 != null && this.isObject() && var4.isObject()) {
               if (this.isObjectWildcardExtends() && !var4.isObjectWildcardSuper()) {
                  if (var3.isCompatible(var4.pC(true), this.pC(true))) {
                     return true;
                  }
               } else if (this.isObjectWildcardSuper() && !var4.isObjectWildcardExtends() && var3.isCompatible(this.pC(true), var4.pC(true))) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   @Override
   public IJavaType compareAndGetMostPrecise(IJavaType var1) {
      return pC(this, (cdi)var1);
   }

   static cdi pC(cdi var0, cdi var1) {
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
      } else if (var0.A == cdi.Av.pC) {
         return var1;
      } else if (var1.A == cdi.Av.pC) {
         return var0;
      } else {
         if (var0.A == cdi.Av.fI) {
            switch (var1.A) {
               case A:
                  return var0;
               case kS:
               case wS:
               case UT:
               case E:
                  return var1;
            }
         }

         if (var1.A == cdi.Av.fI) {
            switch (var0.A) {
               case A:
                  return var1;
               case kS:
               case wS:
               case UT:
               case E:
                  return var0;
            }
         }

         if (var0.A == cdi.Av.gp) {
            switch (var1.A) {
               case A:
               case kS:
               case wS:
               case UT:
               case E:
               case ys:
               case WR:
               case fI:
                  return var1;
               case sY:
               case ld:
               case pC:
               case gp:
               case oT:
            }
         }

         if (var1.A == cdi.Av.gp) {
            switch (var0.A) {
               case A:
               case kS:
               case wS:
               case UT:
               case E:
               case ys:
               case WR:
               case fI:
                  return var0;
               case sY:
               case ld:
               case pC:
               case gp:
               case oT:
            }
         }

         if (var0.A == cdi.Av.oT) {
            switch (var1.A) {
               case sY:
               case ld:
                  return var1;
            }
         }

         if (var1.A == cdi.Av.oT) {
            switch (var0.A) {
               case sY:
               case ld:
                  return var0;
            }
         }

         return null;
      }
   }

   private static char A(cdi.Av var0) {
      switch (var0) {
         case A:
            return 'Z';
         case kS:
            return 'B';
         case wS:
            return 'C';
         case UT:
            return 'S';
         case E:
            return 'I';
         case sY:
            return 'J';
         case ys:
            return 'F';
         case ld:
            return 'D';
         default:
            throw new RuntimeException("Unexpected simple type: " + var0);
      }
   }

   @Override
   public int getEncodingBitsize() {
      switch (this.A) {
         case A:
            return 1;
         case kS:
            return 8;
         case wS:
         case UT:
            return 16;
         case E:
         case ys:
         case WR:
         case gp:
         case fI:
            return 32;
         case sY:
         case ld:
         case oT:
            return 64;
         case pC:
            return 0;
         default:
            throw new RuntimeException();
      }
   }

   @Ser
   public static enum Av {
      pC,
      A,
      kS,
      wS,
      UT,
      E,
      sY,
      ys,
      ld,
      gp,
      oT,
      fI,
      WR;
   }
}
