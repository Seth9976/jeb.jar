package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrototypeAttribute;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class akf extends ajf implements IWildcardPrototype {
   @SerId(1)
   private ICallingConvention A;
   @SerId(2)
   private List kS;
   @SerId(3)
   private List wS;
   @SerId(4)
   private Set UT;

   akf(akr var1, ICallingConvention var2, List var3, List var4, Collection var5) {
      super(var1);
      if (var2 == null) {
         var2 = this.pC().A().A().getDefaultConvention();
      }

      if (var3 == null) {
         var3 = new ArrayList();
      } else {
         for (akp var7 : var3) {
            if (var7 == null || var7.isVoid()) {
               throw new IllegalArgumentException("Illegal return type: " + var7);
            }
         }
      }

      if (var4 == null) {
         var4 = new ArrayList();
      } else {
         for (akp var11 : var4) {
            if (var11 == null || var11.isVoid()) {
               throw new IllegalArgumentException("Illegal parameter type: " + var11);
            }
         }
      }

      this.A = var2;
      this.kS = (List)var3;
      this.wS = (List)var4;
      if (var5 != null) {
         this.UT = new HashSet(var5);
         if (this.UT.contains(PrototypeAttribute.VarArg)) {
            this.addFlags(128);
         }
      }

      for (akp var12 : var3) {
         var12.addListener(this);
      }

      for (akp var13 : var4) {
         var13.addListener(this);
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      return 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         akf var2 = (akf)var1;
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

         if (this.kS == null) {
            if (var2.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var2.kS)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      INativeItem var2 = var1.getItem();
      if (this.kS.contains(var2) || this.wS.contains(var2)) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            for (akp var4 : this.kS) {
               var4.removeListener(this);
            }

            for (akp var6 : this.wS) {
               var6.removeListener(this);
            }

            this.xC();
         } else {
            this.pC(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   protected void A() {
      super.A();

      for (akp var2 : this.kS) {
         var2.removeListener(this);
      }

      for (akp var4 : this.wS) {
         var4.removeListener(this);
      }
   }

   public akr kS() {
      return this.pC;
   }

   @Override
   public ICallingConvention getCallingConvention() {
      return this.A;
   }

   public akp wS() {
      return this.kS.isEmpty() ? this.pC.pC("void") : (akp)this.kS.get(0);
   }

   @Override
   public List getReturnTypes() {
      return this.kS;
   }

   public akp pC(int var1) {
      return var1 >= this.kS.size() ? null : (akp)this.kS.get(var1);
   }

   @Override
   public List getParameterTypes() {
      return this.wS;
   }

   public akp A(int var1) {
      return var1 >= this.wS.size() ? null : (akp)this.wS.get(var1);
   }

   @Override
   public boolean isVariableArgument() {
      return this.UT != null && this.UT.contains(PrototypeAttribute.VarArg);
   }

   @Override
   public Collection getPrototypeAttributes() {
      return this.UT == null ? Collections.emptySet() : this.UT;
   }

   @Override
   public boolean hasPrototypeAttributes() {
      return this.UT != null && !this.UT.isEmpty();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("EProto{");
      if (this.A != null) {
         var1.append("cc:").append(this.A.getName()).append(";");
      }

      var1.append(this.getSignature());
      var1.append("}");
      return var1.toString();
   }

   @Override
   public String getSignature(boolean var1) {
      StringBuilder var2 = new StringBuilder("");
      int var3 = 0;
      var2.append("(");

      for (akp var5 : this.wS) {
         if (var3 >= 1) {
            var2.append(",");
         }

         var2.append(var5.toString());
         var3++;
      }

      if (this.isVariableArgument()) {
         if (var3 >= 1) {
            var2.append(",");
         }

         var2.append("...");
      }

      var2.append("):(");
      var3 = 0;

      for (akp var8 : this.kS) {
         if (var3 >= 1) {
            var2.append(",");
         }

         var2.append(var8.toString());
         var3++;
      }

      var2.append(")");
      return var2.toString();
   }

   public ays UT() {
      ayy var1 = this.pC.A();
      ArrayList var2 = new ArrayList(this.kS.size());

      for (akp var4 : this.kS) {
         var2.add(var4.kS().getNativeType());
      }

      ArrayList var6 = new ArrayList(this.wS.size());

      for (akp var5 : this.wS) {
         var6.add(var5.kS().getNativeType());
      }

      return var1.pC(this.A, var2, var6, this.UT);
   }
}
