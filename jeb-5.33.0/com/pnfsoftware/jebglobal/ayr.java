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
public class ayr implements IPrimitiveTypeManager {
   @SerId(1)
   private ayy pC;
   @SerId(2)
   private PrimitiveSizes A;
   @SerId(3)
   private Map kS;
   @SerId(4)
   private MultiMap wS;
   @SerId(5)
   private int UT;
   @SerId(10)
   private ayq E;
   @SerId(11)
   private ayq sY;
   @SerId(12)
   private ayq ys;
   @SerId(13)
   private ayq ld;
   @SerId(14)
   private ayq gp;
   @SerId(15)
   private ayq oT;
   @SerId(16)
   private ayq fI;
   @SerId(17)
   private ayq WR;
   @SerId(18)
   private ayq NS;
   @SerId(19)
   private ayq vP;
   @SerId(20)
   private ayq xC;
   @SerId(21)
   private ayq ED;
   @SerId(22)
   private ayq Sc;
   @SerId(23)
   private ayq ah;
   @SerId(24)
   private ayq eP;
   @SerId(25)
   private ayq UO;
   @SerId(26)
   private ayq Ab;
   @SerId(27)
   private ayq rl;

   public ayr(ayy var1, IPrimitiveSizes var2, int var3) {
      this.pC = var1;
      this.A = new PrimitiveSizes(var2);
      this.UT = var3;
      this.kS = new HashMap();
      this.wS = new MultiMap();
      this.E = this.pC("void", 0, PrimitiveCategory.VOID);
      this.ys = this.pC("char", this.A.getCharSize(), PrimitiveCategory.INTEGER);
      this.pC("signed char", this.ys);
      this.ld = this.pC("unsigned char", this.A.getCharSize(), PrimitiveCategory.UNSIGNED);
      this.gp = this.pC("short", this.A.getShortSize(), PrimitiveCategory.INTEGER);
      this.pC("short int", this.gp);
      this.pC("signed short", this.gp);
      this.pC("signed short int", this.gp);
      this.oT = this.pC("unsigned short", this.A.getShortSize(), PrimitiveCategory.UNSIGNED);
      this.pC("unsigned short int", this.oT);
      this.pC("short unsigned int", this.oT);
      this.fI = this.pC("int", this.A.getIntSize(), PrimitiveCategory.INTEGER);
      this.pC("signed int", this.fI);
      this.pC("signed", this.fI);
      this.WR = this.pC("unsigned int", this.A.getIntSize(), PrimitiveCategory.UNSIGNED);
      this.pC("unsigned", this.WR);
      this.NS = this.pC("long", this.A.getLongSize(), PrimitiveCategory.INTEGER);
      this.pC("long int", this.NS);
      this.pC("signed long", this.NS);
      this.pC("signed long int", this.NS);
      this.pC("long signed int", this.NS);
      this.vP = this.pC("unsigned long", this.A.getLongSize(), PrimitiveCategory.UNSIGNED);
      this.pC("unsigned long int", this.vP);
      this.pC("long unsigned int", this.vP);
      this.xC = this.pC("long long", this.A.getLongLongSize(), PrimitiveCategory.INTEGER);
      this.pC("long long int", this.xC);
      this.pC("signed long long", this.xC);
      this.pC("signed long long int", this.xC);
      this.pC("long long signed int", this.xC);
      this.ED = this.pC("unsigned long long", this.A.getLongLongSize(), PrimitiveCategory.UNSIGNED);
      this.pC("unsigned long long int", this.ED);
      this.pC("long long unsigned int", this.ED);
      this.Sc = this.pC("float", this.A.getFloatSize(), PrimitiveCategory.FLOAT);
      this.ah = this.pC("double", this.A.getDoubleSize(), PrimitiveCategory.FLOAT);
      this.eP = this.pC("long double", this.A.getLongDoubleSize(), PrimitiveCategory.FLOAT);
      this.sY = this.pC("_Bool", this.A.getCharSize(), PrimitiveCategory.INTEGER);
      ayq var4 = this.pC(1, true);
      if (var4 != null) {
         this.pC("__int8", var4);
         this.pC("signed __int8", var4);
      }

      var4 = this.pC(1, false);
      if (var4 != null) {
         this.pC("unsigned __int8", var4);
      }

      var4 = this.pC(2, true);
      if (var4 != null) {
         this.pC("__int16", this.gp);
         this.pC("signed __int16", var4);
      }

      var4 = this.pC(2, false);
      if (var4 != null) {
         this.pC("unsigned __int16", var4);
      }

      var4 = this.pC(4, true);
      if (var4 != null) {
         this.pC("__int32", this.fI);
         this.pC("signed __int32", var4);
      }

      var4 = this.pC(4, false);
      if (var4 != null) {
         this.pC("unsigned __int32", var4);
      }

      var4 = this.pC(8, true);
      if (var4 != null) {
         this.pC("__int64", var4);
         this.pC("signed __int64", var4);
      }

      var4 = this.pC(8, false);
      if (var4 != null) {
         this.pC("unsigned __int64", var4);
      }

      this.UO = this.pC("_Complex float", 2 * this.A.getFloatSize(), PrimitiveCategory.COMPLEX);
      this.pC("float _Complex", this.UO);
      this.Ab = this.pC("_Complex double", 2 * this.A.getDoubleSize(), PrimitiveCategory.COMPLEX);
      this.pC("double _Complex", this.Ab);
      this.rl = this.pC("_Complex long double", 2 * this.A.getLongDoubleSize(), PrimitiveCategory.COMPLEX);
      this.pC("long double _Complex", this.rl);
      if (var1 != null && var1.getCompilerType() == CompilerType.GCC) {
         this.pC("__builtin_va_list", var3, PrimitiveCategory.UNSIGNED);
         ayq var5 = this.pC("__int128", 16, PrimitiveCategory.INTEGER);
         this.pC("signed __int128", var5);
         this.pC("__int128_t", var5);
         ayq var6 = this.pC("unsigned __int128", 16, PrimitiveCategory.UNSIGNED);
         this.pC("__uint128_t", var6);
         this.pC("_Float32x", this.ah);
         this.pC("_Complex _Float32x", this.Ab);
         this.pC("_Float32x _Complex", this.Ab);
         if (this.Sc.getSize() == 4 && this.ah.getSize() == 8) {
            if (this.eP.getSize() == 10) {
               this.pC("__float80", this.eP);
               this.pC("_Complex __float80", this.rl);
               this.pC("__float80 _Complex", this.rl);
            }

            this.pC("__float32", this.Sc);
            this.pC("_Complex __float32", this.UO);
            this.pC("__float32 _Complex", this.UO);
            this.pC("_Float32", this.Sc);
            this.pC("_Complex _Float32", this.UO);
            this.pC("_Float32 _Complex", this.UO);
            this.pC("__float64", this.ah);
            this.pC("_Complex __float64", this.Ab);
            this.pC("__float64 _Complex", this.Ab);
            this.pC("_Float64", this.ah);
            this.pC("_Complex _Float64", this.Ab);
            this.pC("_Float64 _Complex", this.Ab);
            ayq var7 = this.pC("__float128", 16, PrimitiveCategory.FLOAT);
            ayq var8 = this.pC("_Complex __float128", 32, PrimitiveCategory.COMPLEX);
            this.pC("__float128 _Complex", var8);
            this.pC("_Float128", var7);
            this.pC("_Complex _Float128", var8);
            this.pC("_Float128 _Complex", var8);
            this.pC("_Float64x", var7);
            this.pC("_Complex _Float64x", var8);
            this.pC("_Float64x _Complex", var8);
         }
      }

      if (this.pC(var3, false) == null) {
         this.wS(var3, false);
      }
   }

   public ayq pC(String var1, int var2, PrimitiveCategory var3) {
      ayq var4 = new ayq(this.pC, var1, var2, var3);
      return this.pC(var1, var2, var4);
   }

   private void wS(int var1, boolean var2) {
      if (!var2) {
         String var3 = "int" + 8 * var1;
         ayq var4 = new ayq(this.pC, var3, var1, PrimitiveCategory.INTEGER);
         this.pC(var3, var1, var4);
         var3 = "signed " + var3;
         this.pC(var3, var4);
         var3 = "uint" + 8 * var1;
         var4 = new ayq(this.pC, var3, var1, PrimitiveCategory.UNSIGNED);
         this.pC(var3, var1, var4);
         var3 = "unsigned int" + 8 * var1;
         this.pC(var3, var4);
      } else {
         String var8 = "float" + 8 * var1;
         ayq var10 = new ayq(this.pC, var8, var1, PrimitiveCategory.FLOAT);
         this.pC(var8, var1, var10);
      }
   }

   private ayq pC(String var1, int var2, ayq var3) {
      if (this.kS.put(var1, var3) != null) {
         throw new RuntimeException(Strings.ff("A primitive named %s was already registered!", var1));
      } else {
         this.wS.put(var2, var3);
         return var3;
      }
   }

   private ayq pC(String var1, ayq var2) {
      return this.pC(var1, var2.getSize(), var2);
   }

   public PrimitiveSizes pC() {
      return this.A;
   }

   @Override
   public Collection getTypes() {
      return Collections.unmodifiableSet(new LinkedHashSet(this.kS.values()));
   }

   @Override
   public Collection getNames() {
      return this.kS.keySet();
   }

   public ayq pC(String var1) {
      return (ayq)this.kS.get(var1);
   }

   public ayq pC(int var1, boolean var2) {
      return this.pC(var1, var2, false);
   }

   public ayq pC(int var1, boolean var2, boolean var3) {
      String var4 = null;
      if (var1 == this.A.getCharSize()) {
         var4 = "char";
      } else if (var1 == this.A.getShortSize()) {
         var4 = "short";
      } else if (var1 == this.A.getIntSize()) {
         var4 = "int";
      } else if (var1 == this.A.getLongSize()) {
         var4 = "long";
      } else if (var1 == this.A.getLongLongSize()) {
         var4 = "long long";
      } else if (this.UT != 0 && var1 == this.UT) {
         var4 = "int" + 8 * this.UT;
      }

      if (var4 != null) {
         if (!var2) {
            var4 = "unsigned " + var4;
         }

         return (ayq)this.kS.get(var4);
      } else {
         List var5 = this.wS.getSafe(var1);
         PrimitiveCategory var6 = var2 ? PrimitiveCategory.INTEGER : PrimitiveCategory.UNSIGNED;

         for (ayq var8 : var5) {
            if (var8.getCategory() == var6) {
               return var8;
            }
         }

         if (var3) {
            this.wS(var1, false);
            return this.pC(var1, var2, false);
         } else {
            return null;
         }
      }
   }

   public ayq A(int var1, boolean var2) {
      return this.A(var1, var2, false);
   }

   public ayq A(int var1, boolean var2, boolean var3) {
      String var4 = null;
      if (var1 <= this.A.getCharSize()) {
         var4 = "char";
      } else if (var1 <= this.A.getShortSize()) {
         var4 = "short";
      } else if (var1 <= this.A.getIntSize()) {
         var4 = "int";
      } else if (var1 <= this.A.getLongSize()) {
         var4 = "long";
      } else if (var1 <= this.A.getLongLongSize()) {
         var4 = "long long";
      } else if (this.UT != 0 && var1 <= this.UT) {
         var4 = "int" + 8 * this.UT;
      }

      if (var4 != null) {
         if (!var2) {
            var4 = "unsigned " + var4;
         }

         return (ayq)this.kS.get(var4);
      } else {
         PrimitiveCategory var5 = var2 ? PrimitiveCategory.INTEGER : PrimitiveCategory.UNSIGNED;
         ayq var6 = null;

         for (ayq var8 : this.kS.values()) {
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
                     this.wS(var9, false);
                     return this.pC(var9, var2, false);
                  }
               }
            }

            return var6;
         }
      }
   }

   public ayq pC(int var1) {
      return this.kS(var1, false);
   }

   public ayq kS(int var1, boolean var2) {
      String var3 = null;
      if (var1 == this.A.getFloatSize()) {
         var3 = "float";
      } else if (var1 == this.A.getDoubleSize()) {
         var3 = "double";
      } else if (var1 == this.A.getLongDoubleSize()) {
         var3 = "long double";
      }

      if (var3 != null) {
         return (ayq)this.kS.get(var3);
      } else {
         for (ayq var6 : this.wS.getSafe(var1)) {
            if (var6.getCategory() == PrimitiveCategory.FLOAT) {
               return var6;
            }
         }

         if (var2) {
            this.wS(var1, true);
            return this.kS(var1, false);
         } else {
            return null;
         }
      }
   }

   @Override
   public boolean isVoid(IPrimitiveType var1) {
      return var1 == this.E;
   }

   @Override
   public boolean isCharacter(IPrimitiveType var1) {
      return var1 == this.ys;
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

   private boolean pC(IPrimitiveType var1) {
      return this.kS.values().contains(var1);
   }

   @Override
   public List getAlternateNames(IPrimitiveType var1) {
      if (!this.pC(var1)) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();
         String var3 = var1.getName();

         for (Entry var5 : this.kS.entrySet()) {
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
      if (!this.pC(var1)) {
         return false;
      } else {
         try {
            this.pC(var2, (ayq)var1);
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
      if (!this.pC(var1)) {
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
