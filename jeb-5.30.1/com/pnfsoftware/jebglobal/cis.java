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
public class cis implements IJavaTypeFactory {
   @SerId(1)
   private Map HF = new HashMap();
   @SerId(2)
   private Map LK = new HashMap();
   @SerId(3)
   public final ciq q = new ciq(ciq.eo.q, this);
   @SerId(4)
   public final ciq RF = new ciq(ciq.eo.RF, this);
   @SerId(5)
   public final ciq xK = new ciq(ciq.eo.xK, this);
   @SerId(6)
   public final ciq Dw = new ciq(ciq.eo.Dw, this);
   @SerId(7)
   public final ciq Uv = new ciq(ciq.eo.Uv, this);
   @SerId(8)
   public final ciq oW = new ciq(ciq.eo.oW, this);
   @SerId(9)
   public final ciq gO = new ciq(ciq.eo.gO, this);
   @SerId(10)
   public final ciq nf = new ciq(ciq.eo.nf, this);
   @SerId(11)
   public final ciq gP = new ciq(ciq.eo.gP, this);
   @SerId(12)
   public final ciq za = new ciq(ciq.eo.za, this);
   @SerId(13)
   public final ciq lm = new ciq(ciq.eo.lm, this);
   @SerId(14)
   public final ciq zz = new ciq(ciq.eo.zz, this);
   @SerId(15)
   public final ciq JY = new ciq("L;", 0, this);
   @SerId(16)
   private Map io = new HashMap();
   @SerId(17)
   private Map qa = new HashMap();
   @SerTransient
   private com.pnfsoftware.jeb.corei.parsers.dexdec.ej Hk;
   @SerTransient
   private volatile IDTypeInfoProvider Me;

   @SerCustomInitPostGraph
   private void HF() {
      if (this.io == null) {
         this.io = new HashMap();
      }

      if (this.qa == null) {
         this.qa = new HashMap();
      }
   }

   public com.pnfsoftware.jeb.corei.parsers.dexdec.ej q() {
      return this.Hk;
   }

   public void q(com.pnfsoftware.jeb.corei.parsers.dexdec.ej var1) {
      this.Hk = var1;
   }

   @Override
   public IDTypeInfoProvider getTypeInfoProvider() {
      if (this.Me == null && this.Hk != null) {
         synchronized (this) {
            if (this.Me == null) {
               bkg var2 = this.Hk.Xo();
               this.Me = var2;
            }
         }
      }

      return this.Me;
   }

   public ciq RF() {
      return this.q;
   }

   public ciq xK() {
      return this.RF;
   }

   @Override
   public IJavaType getByte() {
      return this.xK;
   }

   public ciq Dw() {
      return this.Dw;
   }

   public ciq Uv() {
      return this.Uv;
   }

   public ciq oW() {
      return this.oW;
   }

   public ciq gO() {
      return this.gO;
   }

   public ciq nf() {
      return this.nf;
   }

   public ciq gP() {
      return this.gP;
   }

   public ciq za() {
      return this.za;
   }

   public ciq lm() {
      return this.lm;
   }

   public ciq zz() {
      return this.zz;
   }

   public ciq JY() {
      return this.JY;
   }

   @Override
   public synchronized IJavaType createArrayType(IJavaType var1, int var2) {
      if (var1 == this.JY) {
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

         Object var4 = (Map)this.LK.get(var1);
         if (var4 == null) {
            var4 = new HashMap();
            this.LK.put(var1, var4);
         }

         Object var5 = (IJavaType)var4.get(var2);
         if (var5 == null) {
            var5 = new ciq((ciq)var1, var2, (ciq)var3, this);
            var4.put(var2, var5);
         }

         return (IJavaType)var5;
      }
   }

   public synchronized IJavaType q(String var1) {
      if (var1 == null || var1.isEmpty()) {
         throw new IllegalArgumentException("No type string provided");
      } else if (!JavaTypeUtil.isLegalInternalClassname(var1)) {
         return this.parseType(var1);
      } else {
         Object var2 = (IJavaType)this.HF.get(var1);
         if (var2 == null) {
            var2 = new ciq(var1, 0, this);
            this.HF.put(var1, var2);
         }

         return (IJavaType)var2;
      }
   }

   @Override
   public IJavaType letterToType(char var1) {
      switch (var1) {
         case 'B':
            return this.xK;
         case 'C':
            return this.Dw;
         case 'D':
            return this.gP;
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
            return this.nf;
         case 'I':
            return this.oW;
         case 'J':
            return this.gO;
         case 'L':
            return this.JY;
         case 'S':
            return this.Uv;
         case 'V':
            return this.q;
         case 'Z':
            return this.RF;
      }
   }

   @Override
   public IJavaType primitiveNameToType(String var1) {
      switch (var1) {
         case "boolean":
            return this.RF;
         case "byte":
            return this.xK;
         case "char":
            return this.Dw;
         case "short":
            return this.Uv;
         case "int":
            return this.oW;
         case "long":
            return this.gO;
         case "float":
            return this.nf;
         case "double":
            return this.gP;
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
      if (var5 == this.JY) {
         var5 = this.q(var1.substring(var2 - 1));
      }

      if (var4 >= 1) {
         var5 = this.createArrayType(var5, var4);
      }

      return var5;
   }

   @Override
   public IJavaType createType(String var1) {
      return (IJavaType)(var1.equals("V") ? this.q : this.parseType(var1));
   }

   @Override
   public synchronized IJavaType createWildcardType(String var1, boolean var2) {
      IJavaType var3 = this.parseType(var1);
      if (!var3.isPrimitive() && !var3.isArray()) {
         Map var4 = var2 ? this.io : this.qa;
         var1 = var3.getSignature();
         var3 = (IJavaType)var4.get(var1);
         if (var3 == null) {
            var3 = new ciq(var1, var2 ? 1 : -1, this);
            var4.put(var1, var3);
         }

         return var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public IJavaType getJavaLangObject() {
      return this.q("Ljava/lang/Object;");
   }

   @Override
   public IJavaType getJavaLangClass() {
      return this.q("Ljava/lang/Class;");
   }

   @Override
   public IJavaType getJavaLangString() {
      return this.q("Ljava/lang/String;");
   }

   public IJavaType RF(String var1) {
      if (var1.startsWith("[")) {
         String var2 = var1.replace('.', '/');
         return this.parseType(var2);
      } else {
         switch (var1) {
            case "boolean":
            case "byte":
            case "char":
            case "short":
            case "int":
            case "long":
            case "float":
            case "double":
               return this.primitiveNameToType(var1);
            default:
               String var4 = "L" + var1.replace('.', '/') + ";";
               return this.parseType(var4);
         }
      }
   }
}
