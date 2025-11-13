package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveSizes;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveCategory;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveSizes;
import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.util.collect.MultiMap;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public class bbr implements IPrimitiveTypeManager {
   @SerId(1)
   private bby q;
   @SerId(2)
   private PrimitiveSizes RF;
   @SerId(3)
   private Map xK;
   @SerId(4)
   private MultiMap Dw;
   @SerId(5)
   private int Uv;
   @SerId(10)
   private bbq oW;
   @SerId(11)
   private bbq gO;
   @SerId(12)
   private bbq nf;
   @SerId(13)
   private bbq gP;
   @SerId(14)
   private bbq za;
   @SerId(15)
   private bbq lm;
   @SerId(16)
   private bbq zz;
   @SerId(17)
   private bbq JY;
   @SerId(18)
   private bbq HF;
   @SerId(19)
   private bbq LK;
   @SerId(20)
   private bbq io;
   @SerId(21)
   private bbq qa;
   @SerId(22)
   private bbq Hk;
   @SerId(23)
   private bbq Me;
   @SerId(24)
   private bbq PV;
   @SerId(25)
   private bbq oQ;
   @SerId(26)
   private bbq xW;
   @SerId(27)
   private bbq KT;

   public bbr(bby var1, IPrimitiveSizes var2, int var3) {
      this.q = var1;
      this.RF = new PrimitiveSizes(var2);
      this.Uv = var3;
      this.xK = new HashMap();
      this.Dw = new MultiMap();
      this.oW = this.q("void", 0, PrimitiveCategory.VOID);
      this.nf = this.q("char", this.RF.getCharSize(), PrimitiveCategory.INTEGER);
      this.q("signed char", this.nf);
      this.gP = this.q("unsigned char", this.RF.getCharSize(), PrimitiveCategory.UNSIGNED);
      this.za = this.q("short", this.RF.getShortSize(), PrimitiveCategory.INTEGER);
      this.q("short int", this.za);
      this.q("signed short", this.za);
      this.q("signed short int", this.za);
      this.lm = this.q("unsigned short", this.RF.getShortSize(), PrimitiveCategory.UNSIGNED);
      this.q("unsigned short int", this.lm);
      this.q("short unsigned int", this.lm);
      this.zz = this.q("int", this.RF.getIntSize(), PrimitiveCategory.INTEGER);
      this.q("signed int", this.zz);
      this.q("signed", this.zz);
      this.JY = this.q("unsigned int", this.RF.getIntSize(), PrimitiveCategory.UNSIGNED);
      this.q("unsigned", this.JY);
      this.HF = this.q("long", this.RF.getLongSize(), PrimitiveCategory.INTEGER);
      this.q("long int", this.HF);
      this.q("signed long", this.HF);
      this.q("signed long int", this.HF);
      this.q("long signed int", this.HF);
      this.LK = this.q("unsigned long", this.RF.getLongSize(), PrimitiveCategory.UNSIGNED);
      this.q("unsigned long int", this.LK);
      this.q("long unsigned int", this.LK);
      this.io = this.q("long long", this.RF.getLongLongSize(), PrimitiveCategory.INTEGER);
      this.q("long long int", this.io);
      this.q("signed long long", this.io);
      this.q("signed long long int", this.io);
      this.q("long long signed int", this.io);
      this.qa = this.q("unsigned long long", this.RF.getLongLongSize(), PrimitiveCategory.UNSIGNED);
      this.q("unsigned long long int", this.qa);
      this.q("long long unsigned int", this.qa);
      this.Hk = this.q("float", this.RF.getFloatSize(), PrimitiveCategory.FLOAT);
      this.Me = this.q("double", this.RF.getDoubleSize(), PrimitiveCategory.FLOAT);
      this.PV = this.q("long double", this.RF.getLongDoubleSize(), PrimitiveCategory.FLOAT);
      this.gO = this.q("_Bool", this.RF.getCharSize(), PrimitiveCategory.INTEGER);
      bbq var4 = this.q(1, true);
      if (var4 != null) {
         this.q("__int8", var4);
         this.q("signed __int8", var4);
      }

      var4 = this.q(1, false);
      if (var4 != null) {
         this.q("unsigned __int8", var4);
      }

      var4 = this.q(2, true);
      if (var4 != null) {
         this.q("__int16", this.za);
         this.q("signed __int16", var4);
      }

      var4 = this.q(2, false);
      if (var4 != null) {
         this.q("unsigned __int16", var4);
      }

      var4 = this.q(4, true);
      if (var4 != null) {
         this.q("__int32", this.zz);
         this.q("signed __int32", var4);
      }

      var4 = this.q(4, false);
      if (var4 != null) {
         this.q("unsigned __int32", var4);
      }

      var4 = this.q(8, true);
      if (var4 != null) {
         this.q("__int64", var4);
         this.q("signed __int64", var4);
      }

      var4 = this.q(8, false);
      if (var4 != null) {
         this.q("unsigned __int64", var4);
      }

      this.oQ = this.q("_Complex float", 2 * this.RF.getFloatSize(), PrimitiveCategory.COMPLEX);
      this.q("float _Complex", this.oQ);
      this.xW = this.q("_Complex double", 2 * this.RF.getDoubleSize(), PrimitiveCategory.COMPLEX);
      this.q("double _Complex", this.xW);
      this.KT = this.q("_Complex long double", 2 * this.RF.getLongDoubleSize(), PrimitiveCategory.COMPLEX);
      this.q("long double _Complex", this.KT);
      if (var1 != null && var1.getCompilerType() == CompilerType.GCC) {
         this.q("__builtin_va_list", var3, PrimitiveCategory.UNSIGNED);
         bbq var5 = this.q("__int128", 16, PrimitiveCategory.INTEGER);
         this.q("signed __int128", var5);
         this.q("__int128_t", var5);
         bbq var6 = this.q("unsigned __int128", 16, PrimitiveCategory.UNSIGNED);
         this.q("__uint128_t", var6);
         this.q("_Float32x", this.Me);
         this.q("_Complex _Float32x", this.xW);
         this.q("_Float32x _Complex", this.xW);
         if (this.Hk.getSize() == 4 && this.Me.getSize() == 8) {
            if (this.PV.getSize() == 10) {
               this.q("__float80", this.PV);
               this.q("_Complex __float80", this.KT);
               this.q("__float80 _Complex", this.KT);
            }

            this.q("__float32", this.Hk);
            this.q("_Complex __float32", this.oQ);
            this.q("__float32 _Complex", this.oQ);
            this.q("_Float32", this.Hk);
            this.q("_Complex _Float32", this.oQ);
            this.q("_Float32 _Complex", this.oQ);
            this.q("__float64", this.Me);
            this.q("_Complex __float64", this.xW);
            this.q("__float64 _Complex", this.xW);
            this.q("_Float64", this.Me);
            this.q("_Complex _Float64", this.xW);
            this.q("_Float64 _Complex", this.xW);
            bbq var7 = this.q("__float128", 16, PrimitiveCategory.FLOAT);
            bbq var8 = this.q("_Complex __float128", 32, PrimitiveCategory.COMPLEX);
            this.q("__float128 _Complex", var8);
            this.q("_Float128", var7);
            this.q("_Complex _Float128", var8);
            this.q("_Float128 _Complex", var8);
            this.q("_Float64x", var7);
            this.q("_Complex _Float64x", var8);
            this.q("_Float64x _Complex", var8);
         }
      }

      if (this.q(var3, false) == null) {
         this.Dw(var3, false);
      }
   }

   public bbq q(String var1, int var2, PrimitiveCategory var3) {
      bbq var4 = new bbq(this.q, var1, var2, var3);
      return this.q(var1, var2, var4);
   }

   private void Dw(int var1, boolean var2) {
      if (!var2) {
         String var3 = "int" + 8 * var1;
         bbq var4 = new bbq(this.q, var3, var1, PrimitiveCategory.INTEGER);
         this.q(var3, var1, var4);
         var3 = "signed " + var3;
         this.q(var3, var4);
         var3 = "uint" + 8 * var1;
         var4 = new bbq(this.q, var3, var1, PrimitiveCategory.UNSIGNED);
         this.q(var3, var1, var4);
         var3 = "unsigned int" + 8 * var1;
         this.q(var3, var4);
      } else {
         String var8 = "float" + 8 * var1;
         bbq var10 = new bbq(this.q, var8, var1, PrimitiveCategory.FLOAT);
         this.q(var8, var1, var10);
      }
   }

   private bbq q(String var1, int var2, bbq var3) {
      if (this.xK.put(var1, var3) != null) {
         throw new RuntimeException(Strings.ff("A primitive named %s was already registered!", var1));
      } else {
         this.Dw.put(var2, var3);
         return var3;
      }
   }

   private bbq q(String var1, bbq var2) {
      return this.q(var1, var2.getSize(), var2);
   }

   public PrimitiveSizes q() {
      return this.RF;
   }

   @Override
   public Collection getTypes() {
      return Collections.unmodifiableSet(new LinkedHashSet(this.xK.values()));
   }

   @Override
   public Collection getNames() {
      return this.xK.keySet();
   }

   public bbq q(String var1) {
      return (bbq)this.xK.get(var1);
   }

   public bbq q(int var1, boolean var2) {
      return this.q(var1, var2, false);
   }

   public bbq q(int var1, boolean var2, boolean var3) {
      String var4 = null;
      if (var1 == this.RF.getCharSize()) {
         var4 = "char";
      } else if (var1 == this.RF.getShortSize()) {
         var4 = "short";
      } else if (var1 == this.RF.getIntSize()) {
         var4 = "int";
      } else if (var1 == this.RF.getLongSize()) {
         var4 = "long";
      } else if (var1 == this.RF.getLongLongSize()) {
         var4 = "long long";
      } else if (this.Uv != 0 && var1 == this.Uv) {
         var4 = "int" + 8 * this.Uv;
      }

      if (var4 != null) {
         if (!var2) {
            var4 = "unsigned " + var4;
         }

         return (bbq)this.xK.get(var4);
      } else {
         List var5 = this.Dw.getSafe(var1);
         PrimitiveCategory var6 = var2 ? PrimitiveCategory.INTEGER : PrimitiveCategory.UNSIGNED;

         for (bbq var8 : var5) {
            if (var8.getCategory() == var6) {
               return var8;
            }
         }

         if (var3) {
            this.Dw(var1, false);
            return this.q(var1, var2, false);
         } else {
            return null;
         }
      }
   }

   public bbq RF(int var1, boolean var2) {
      return this.RF(var1, var2, false);
   }

   public bbq RF(int var1, boolean var2, boolean var3) {
      String var4 = null;
      if (var1 <= this.RF.getCharSize()) {
         var4 = "char";
      } else if (var1 <= this.RF.getShortSize()) {
         var4 = "short";
      } else if (var1 <= this.RF.getIntSize()) {
         var4 = "int";
      } else if (var1 <= this.RF.getLongSize()) {
         var4 = "long";
      } else if (var1 <= this.RF.getLongLongSize()) {
         var4 = "long long";
      } else if (this.Uv != 0 && var1 <= this.Uv) {
         var4 = "int" + 8 * this.Uv;
      }

      if (var4 != null) {
         if (!var2) {
            var4 = "unsigned " + var4;
         }

         return (bbq)this.xK.get(var4);
      } else {
         PrimitiveCategory var5 = var2 ? PrimitiveCategory.INTEGER : PrimitiveCategory.UNSIGNED;
         bbq var6 = null;

         for (bbq var8 : this.xK.values()) {
            if (var8.getSize() >= var1 && var8.getCategory() == var5 && (var6 == null || var6.getSize() > var8.getSize())) {
               var6 = var8;
            }
         }

         if (var6 != null) {
            return var6;
         } else {
            if (var3) {
               for (byte var9 = 1; var9 > 0; var9 <<= 1) {
                  if (var9 >= var1) {
                     this.Dw(var9, false);
                     return this.q(var9, var2, false);
                  }
               }
            }

            return var6;
         }
      }
   }

   public bbq q(int var1) {
      return this.xK(var1, false);
   }

   public bbq xK(int var1, boolean var2) {
      String var3 = null;
      if (var1 == this.RF.getFloatSize()) {
         var3 = "float";
      } else if (var1 == this.RF.getDoubleSize()) {
         var3 = "double";
      } else if (var1 == this.RF.getLongDoubleSize()) {
         var3 = "long double";
      }

      if (var3 != null) {
         return (bbq)this.xK.get(var3);
      } else {
         for (bbq var6 : this.Dw.getSafe(var1)) {
            if (var6.getCategory() == PrimitiveCategory.FLOAT) {
               return var6;
            }
         }

         if (var2) {
            this.Dw(var1, true);
            return this.xK(var1, false);
         } else {
            return null;
         }
      }
   }

   @Override
   public boolean isVoid(IPrimitiveType var1) {
      return var1 == this.oW;
   }

   @Override
   public boolean isCharacter(IPrimitiveType var1) {
      return var1 == this.nf;
   }

   @Override
   public boolean isInteger(IPrimitiveType var1) {
      return var1 != null && (var1.getCategory() == PrimitiveCategory.INTEGER || var1.getCategory() == PrimitiveCategory.UNSIGNED);
   }

   @Override
   public boolean isSignedInteger(IPrimitiveType var1) {
      return var1 != null && var1.getCategory() == PrimitiveCategory.INTEGER;
   }

   @Override
   public boolean isUnsignedInteger(IPrimitiveType var1) {
      return var1 != null && var1.getCategory() == PrimitiveCategory.UNSIGNED;
   }

   @Override
   public boolean isFloat(IPrimitiveType var1) {
      return var1 != null && var1.getCategory() == PrimitiveCategory.FLOAT;
   }

   private boolean q(IPrimitiveType var1) {
      return this.xK.values().contains(var1);
   }

   @Override
   public List getAlternateNames(IPrimitiveType var1) {
      if (!this.q(var1)) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         String var3 = var1.getName();

         for (Entry var5 : this.xK.entrySet()) {
            if (var5.getValue() == var1) {
               String var6 = (String)var5.getKey();
               if (!var6.equals(var3)) {
                  var2.add((String)var5.getKey());
               }
            }
         }

         return var2;
      }
   }

   @Override
   public boolean addAlternateName(IPrimitiveType var1, String var2, boolean var3) {
      if (!this.q(var1)) {
         return false;
      } else {
         try {
            this.q(var2, (bbq)var1);
         } catch (RuntimeException var4) {
            return false;
         }

         if (var3) {
            var1.setName(var2);
         }

         return true;
      }
   }

   @Override
   public boolean setEffectiveName(IPrimitiveType var1, String var2) {
      if (!this.q(var1)) {
         return false;
      } else if (var2 == null) {
         var1.setName(null);
         return true;
      } else if (var2.equals(var1.getName())) {
         return true;
      } else if (!this.getAlternateNames(var1).contains(var2)) {
         return false;
      } else {
         var1.setName(var2);
         return true;
      }
   }
}
