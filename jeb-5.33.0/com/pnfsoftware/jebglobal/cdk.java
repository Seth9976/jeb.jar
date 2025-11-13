package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDTypeInfoProvider;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.code.java.JavaTypeUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.HashMap;
import java.util.Map;

@Ser
public class cdk implements IJavaTypeFactory {
   @SerId(1)
   private Map NS = new HashMap();
   @SerId(2)
   private Map vP = new HashMap();
   @SerId(3)
   public final cdi pC = new cdi(cdi.Av.pC, this);
   @SerId(4)
   public final cdi A = new cdi(cdi.Av.A, this);
   @SerId(5)
   public final cdi kS = new cdi(cdi.Av.kS, this);
   @SerId(6)
   public final cdi wS = new cdi(cdi.Av.wS, this);
   @SerId(7)
   public final cdi UT = new cdi(cdi.Av.UT, this);
   @SerId(8)
   public final cdi E = new cdi(cdi.Av.E, this);
   @SerId(9)
   public final cdi sY = new cdi(cdi.Av.sY, this);
   @SerId(10)
   public final cdi ys = new cdi(cdi.Av.ys, this);
   @SerId(11)
   public final cdi ld = new cdi(cdi.Av.ld, this);
   @SerId(12)
   public final cdi gp = new cdi(cdi.Av.gp, this);
   @SerId(13)
   public final cdi oT = new cdi(cdi.Av.oT, this);
   @SerId(14)
   public final cdi fI = new cdi(cdi.Av.fI, this);
   @SerId(15)
   public final cdi WR = new cdi("L;", 0, this);
   @SerId(16)
   private Map xC = new HashMap();
   @SerId(17)
   private Map ED = new HashMap();
   @SerTransient
   private com.pnfsoftware.jeb.corei.parsers.dexdec.Ws Sc;
   @SerTransient
   private volatile IDTypeInfoProvider ah;

   @SerCustomInitPostGraph
   private void NS() {
      if (this.xC == null) {
         this.xC = new HashMap();
      }

      if (this.ED == null) {
         this.ED = new HashMap();
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.dexdec.Ws pC() {
      return this.Sc;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var1) {
      this.Sc = var1;
   }

   @Override
   public IDTypeInfoProvider getTypeInfoProvider() {
      if (this.ah == null && this.Sc != null) {
         synchronized (this) {
            if (this.ah == null) {
               bgl var2 = this.Sc.fI();
               this.ah = var2;
            }
         }
      }

      return this.ah;
   }

   public cdi A() {
      return this.pC;
   }

   public cdi kS() {
      return this.A;
   }

   @Override
   public IJavaType getByte() {
      return this.kS;
   }

   public cdi wS() {
      return this.wS;
   }

   public cdi UT() {
      return this.UT;
   }

   public cdi E() {
      return this.E;
   }

   public cdi sY() {
      return this.sY;
   }

   public cdi ys() {
      return this.ys;
   }

   public cdi ld() {
      return this.ld;
   }

   public cdi gp() {
      return this.gp;
   }

   public cdi oT() {
      return this.oT;
   }

   public cdi fI() {
      return this.fI;
   }

   public cdi WR() {
      return this.WR;
   }

   @Override
   public synchronized IJavaType createArrayType(IJavaType var1, int var2) {
      if (var1 == this.WR) {
         throw new RuntimeException("The generic-object wildcard type cannot be made an array (it already represents arrays)");
      } else if (var1.isArray()) {
         throw new RuntimeException("Need a non-array base type");
      } else if (var2 <= 0) {
         throw new RuntimeException("Need a stricly positive dimension count");
      } else {
         IJavaType var3;
         if (var2 == 1) {
            var3 = var1;
         } else {
            var3 = this.createArrayType(var1, var2 - 1);
         }

         Object var4 = (Map)this.vP.get(var1);
         if (var4 == null) {
            var4 = new HashMap();
            this.vP.put(var1, var4);
         }

         Object var5 = (IJavaType)var4.get(var2);
         if (var5 == null) {
            var5 = new cdi((cdi)var1, var2, (cdi)var3, this);
            var4.put(var2, var5);
         }

         return (IJavaType)var5;
      }
   }

   public synchronized IJavaType pC(String var1) {
      if (var1 == null || var1.isEmpty()) {
         throw new IllegalArgumentException("No type string provided");
      } else if (!JavaTypeUtil.isLegalInternalClassname(var1)) {
         return this.parseType(var1);
      } else {
         Object var2 = (IJavaType)this.NS.get(var1);
         if (var2 == null) {
            var2 = new cdi(var1, 0, this);
            this.NS.put(var1, var2);
         }

         return (IJavaType)var2;
      }
   }

   @Override
   public IJavaType letterToType(char var1) {
      switch (var1) {
         case 'B':
            return this.kS;
         case 'C':
            return this.wS;
         case 'D':
            return this.ld;
         case 'E':
         case 'G':
         case 'H':
         case 'K':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'U':
         case 'W':
         case 'X':
         case 'Y':
         default:
            throw new RuntimeException("Invalid letter for type: " + var1);
         case 'F':
            return this.ys;
         case 'I':
            return this.E;
         case 'J':
            return this.sY;
         case 'L':
            return this.WR;
         case 'S':
            return this.UT;
         case 'V':
            return this.pC;
         case 'Z':
            return this.A;
      }
   }

   @Override
   public IJavaType primitiveNameToType(String var1) {
      switch (var1) {
         case "boolean":
            return this.A;
         case "byte":
            return this.kS;
         case "char":
            return this.wS;
         case "short":
            return this.UT;
         case "int":
            return this.E;
         case "long":
            return this.sY;
         case "float":
            return this.ys;
         case "double":
            return this.ld;
         default:
            throw new RuntimeException("Invalid primitive name: " + var1);
      }
   }

   @Override
   public IJavaType parseType(String var1) {
      int var2 = 0;
      char var3 = var1.charAt(var2++);

      int var4;
      for (var4 = 0; var3 == '['; var3 = var1.charAt(var2++)) {
         var4++;
      }

      IJavaType var5 = this.letterToType(var3);
      if (var5 == this.WR) {
         var5 = this.pC(var1.substring(var2 - 1));
      }

      if (var4 >= 1) {
         var5 = this.createArrayType(var5, var4);
      }

      return var5;
   }

   @Override
   public IJavaType createType(String var1) {
      return (IJavaType)(var1.equals("V") ? this.pC : this.parseType(var1));
   }

   @Override
   public synchronized IJavaType createWildcardType(String var1, boolean var2) {
      IJavaType var3 = this.parseType(var1);
      if (!var3.isPrimitive() && !var3.isArray()) {
         Map var4 = var2 ? this.xC : this.ED;
         var1 = var3.getSignature();
         var3 = (IJavaType)var4.get(var1);
         if (var3 == null) {
            var3 = new cdi(var1, var2 ? 1 : -1, this);
            var4.put(var1, var3);
         }

         return var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaType getJavaLangObject() {
      return this.pC("Ljava/lang/Object;");
   }

   @Override
   public IJavaType getJavaLangClass() {
      return this.pC("Ljava/lang/Class;");
   }

   @Override
   public IJavaType getJavaLangString() {
      return this.pC("Ljava/lang/String;");
   }
}
