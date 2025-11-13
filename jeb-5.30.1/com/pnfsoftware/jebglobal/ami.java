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
public class ami extends ali implements IWildcardPrototype {
   @SerId(1)
   private ICallingConvention RF;
   @SerId(2)
   private List xK;
   @SerId(3)
   private List Dw;
   @SerId(4)
   private Set Uv;

   ami(amu var1, ICallingConvention var2, List var3, List var4, Collection var5) {
      super(var1);
      if (var2 == null) {
         var2 = this.q().RF().Dw().getDefaultConvention();
      }

      if (var3 == null) {
         var3 = new ArrayList();
      } else {
         for (ams var7 : var3) {
            if (var7 == null || var7.isVoid()) {
               throw new IllegalArgumentException("Illegal return type: " + var7);
            }
         }
      }

      if (var4 == null) {
         var4 = new ArrayList();
      } else {
         for (ams var11 : var4) {
            if (var11 == null || var11.isVoid()) {
               throw new IllegalArgumentException("Illegal parameter type: " + var11);
            }
         }
      }

      this.RF = var2;
      this.xK = (List)var3;
      this.Dw = (List)var4;
      if (var5 != null) {
         this.Uv = new HashSet(var5);
         if (this.Uv.contains(PrototypeAttribute.VarArg)) {
            this.addFlags(128);
         }
      }

      for (ams var12 : var3) {
         var12.addListener(this);
      }

      for (ams var13 : var4) {
         var13.addListener(this);
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      return 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
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
         ami var2 = (ami)var1;
         if (this.RF == null) {
            if (var2.RF != null) {
               return false;
            }
         } else if (!this.RF.equals(var2.RF)) {
            return false;
         }

         if (this.Dw == null) {
            if (var2.Dw != null) {
               return false;
            }
         } else if (!this.Dw.equals(var2.Dw)) {
            return false;
         }

         if (this.xK == null) {
            if (var2.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var2.xK)) {
            return false;
         }

         if (this.Uv == null) {
            if (var2.Uv != null) {
               return false;
            }
         } else if (!this.Uv.equals(var2.Uv)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      INativeItem var2 = var1.getItem();
      if (this.xK.contains(var2) || this.Dw.contains(var2)) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            for (ams var4 : this.xK) {
               var4.removeListener(this);
            }

            for (ams var6 : this.Dw) {
               var6.removeListener(this);
            }

            this.PV();
         } else {
            this.q(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   protected void RF() {
      super.RF();

      for (ams var2 : this.xK) {
         var2.removeListener(this);
      }

      for (ams var4 : this.Dw) {
         var4.removeListener(this);
      }
   }

   public amu xK() {
      return this.q;
   }

   @Override
   public ICallingConvention getCallingConvention() {
      return this.RF;
   }

   public ams Dw() {
      return this.xK.isEmpty() ? this.q.q("void") : (ams)this.xK.get(0);
   }

   @Override
   public List getReturnTypes() {
      return this.xK;
   }

   public ams q(int var1) {
      return var1 >= this.xK.size() ? null : (ams)this.xK.get(var1);
   }

   @Override
   public List getParameterTypes() {
      return this.Dw;
   }

   public ams RF(int var1) {
      return var1 >= this.Dw.size() ? null : (ams)this.Dw.get(var1);
   }

   @Override
   public boolean isVariableArgument() {
      return this.Uv != null && this.Uv.contains(PrototypeAttribute.VarArg);
   }

   @Override
   public Collection getPrototypeAttributes() {
      return this.Uv == null ? Collections.emptySet() : this.Uv;
   }

   @Override
   public boolean hasPrototypeAttributes() {
      return this.Uv != null && !this.Uv.isEmpty();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder("EProto{");
      var1.append(this.getSignature());
      var1.append("}");
      return var1.toString();
   }

   @Override
   public String getSignature(boolean var1) {
      StringBuilder var2 = new StringBuilder("");
      int var3 = 0;
      var2.append("(");

      for (ams var5 : this.Dw) {
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

      for (ams var8 : this.xK) {
         if (var3 >= 1) {
            var2.append(",");
         }

         var2.append(var8.toString());
         var3++;
      }

      var2.append(")");
      return var2.toString();
   }

   public bbs Uv() {
      bby var1 = this.q.RF();
      ArrayList var2 = new ArrayList(this.xK.size());

      for (ams var4 : this.xK) {
         var2.add(var4.xK().getNativeType());
      }

      ArrayList var6 = new ArrayList(this.Dw.size());

      for (ams var5 : this.Dw) {
         var6.add(var5.xK().getNativeType());
      }

      return var1.q(this.RF, var2, var6, this.Uv);
   }
}
