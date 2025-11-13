package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrototypeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class ays extends aye implements IPrototypeItem {
   @SerId(1)
   ICallingConvention pC;
   @SerId(2)
   List A = new CopyOnWriteArrayList();
   @SerId(3)
   List kS = new CopyOnWriteArrayList();
   @SerId(4)
   volatile Set wS;
   @SerTransient
   List UT = new CopyOnWriteArrayList();
   @SerTransient
   String ld;

   ays(ayy var1, ICallingConvention var2, List var3, List var4, Collection var5) {
      super(var1, null);
      if (var3 == null) {
         var3 = new ArrayList();
      }

      if (var4 == null) {
         var4 = new ArrayList();
      }

      if (var2 == null) {
         var2 = var1.A().getDefaultConvention();
      }

      for (aye var7 : var3) {
         this.A(var7, false);
      }

      for (aye var9 : var4) {
         this.kS(var9, false);
      }

      this.pC = var2;
      if (var5 != null) {
         this.wS = new HashSet(var5);
         if (this.wS.contains(PrototypeAttribute.VarArg)) {
            this.A(128, false);
         }
      }
   }

   @Override
   public int getSize() {
      return this.kS().getPointerSize();
   }

   @Override
   public String getName(boolean var1) {
      StringBuilder var2 = new StringBuilder();
      if (!this.E()) {
         var2.append(this.sY().getName(var1));
      } else {
         var2.append("(");
         int var3 = 0;

         for (aye var5 : this.A) {
            if (var3 >= 1) {
               var2.append(", ");
            }

            var2.append(var5.getName(var1));
            var3++;
         }

         var2.append("):");
      }

      var2.append("(");
      if (this.kS == null) {
         var2.append("?");
      } else {
         int var7 = 0;

         for (aye var9 : this.kS) {
            if (var7 >= 1) {
               var2.append(",");
            }

            String var6 = var9 == null ? "?" : var9.getName(var1);
            var2.append(var6);
            var7++;
         }
      }

      if (this.isVariableArgument()) {
         if (this.kS != null && !this.kS.isEmpty()) {
            var2.append(",");
         }

         var2.append("...");
      }

      var2.append(")");
      return var2.toString();
   }

   @Override
   public void setName(String var1) {
      throw new RuntimeException("Cannot set name of a prototype");
   }

   @Override
   public String pC(boolean var1, String var2) {
      return this.pC(var1, var2, null);
   }

   public String pC(boolean var1, String var2, List var3) {
      if (var2 == null) {
         var2 = this.getRoutineName();
         if (var2 == null) {
            var2 = "f";
         }
      }

      if (var3 == null) {
         var3 = this.UT;
      }

      StringBuilder var4 = new StringBuilder();
      boolean var5 = this.E();
      if (!var5) {
         var4.append(this.sY().getSignature(var1));
      }

      var4.append(' ');
      var4.append(this.pC.getName());

      for (PrototypeAttribute var7 : this.getPrototypeAttributes()) {
         if (var7 != PrototypeAttribute.VarArg) {
            var4.append(' ').append(var7.format());
         }
      }

      if (var2 != null) {
         var4.append(' ');
         var4.append(var2);
      }

      var4.append("(");
      int var10 = 0;

      for (aye var8 : this.kS) {
         if (var10 >= 1) {
            var4.append(", ");
         }

         String var9 = null;
         if (var3 != null && var10 < var3.size()) {
            var9 = (String)var3.get(var10);
         }

         var4.append(var8.pC(var1, var9));
         var10++;
      }

      if (this.isVariableArgument()) {
         if (!this.kS.isEmpty()) {
            var4.append(", ");
         }

         var4.append("...");
      }

      var4.append(")");
      if (this.E()) {
         var4.append(": (");
         var10 = 0;

         for (aye var14 : this.A) {
            if (var10 >= 1) {
               var4.append(", ");
            }

            var4.append(var14.getSignature(var1));
            var10++;
         }

         var4.append(")");
      }

      return var4.toString();
   }

   @Override
   public boolean isVariableArgument() {
      return this.wS != null && this.wS.contains(PrototypeAttribute.VarArg);
   }

   @Override
   public boolean isNoReturn() {
      return this.wS != null && this.wS.contains(PrototypeAttribute.NoReturn);
   }

   @Override
   public Collection getPrototypeAttributes() {
      return this.wS == null ? Collections.emptySet() : this.wS;
   }

   public void pC(PrototypeAttribute var1, boolean var2) {
      try (ACLock var3 = this.gp().a()) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         }

         if (var1 == PrototypeAttribute.VarArg) {
            this.A(128, false);
         }

         if (this.wS == null) {
            this.wS = new ConcurrentHashSet();
         }

         boolean var4 = this.wS.add(var1);
         if (var2) {
            this.wS(var4);
         }
      }
   }

   private aye pC(aye var1, aye var2, boolean var3) {
      if (var2 != null && var1 == null) {
         throw new IllegalArgumentException();
      } else if (var2 == var1) {
         return var2;
      } else {
         if (var2 != null) {
            var2.removeListener(this);
         }

         var1.addListener(this);
         if (var3) {
            this.pC(NativeItemEventType.MODIFIED);
         }

         return var1;
      }
   }

   @Override
   public int getCountOfReturns() {
      return this.A.size();
   }

   public boolean E() {
      return this.A.size() >= 2;
   }

   public aye sY() {
      return this.A.isEmpty() ? this.kS().UT() : (aye)this.A.get(0);
   }

   public void pC(aye var1, boolean var2) {
      this.vP();
      Assert.a(this.A.isEmpty());
      if (var1 != null && TypeUtil.isVoid(var1)) {
         var1 = null;
      }

      this.pC(var1, null, var2);
      if (var1 != null) {
         this.A(var1, var2);
      }
   }

   @Override
   public List getReturnTypes() {
      return this.A;
   }

   public void A(aye var1, boolean var2) {
      this.vP();
      if (!this.A.isEmpty() || var1 == null || !TypeUtil.isVoid(var1)) {
         try (ACLock var3 = this.gp().a()) {
            if (var1 == null || TypeUtil.isVoid(var1)) {
               throw new NullPointerException("Illegal return type: " + var1);
            }

            this.A.add(this.pC(var1, null, var2));
         }
      }
   }

   @Override
   public int getCountOfParameters() {
      return this.kS.size();
   }

   @Override
   public List getParameterTypes() {
      return this.kS;
   }

   @Override
   public List getParameterNames() {
      return this.UT;
   }

   public void kS(aye var1, boolean var2) {
      this.pC(var1, null, var2);
   }

   public void pC(aye var1, String var2, boolean var3) {
      this.vP();

      try (ACLock var4 = this.gp().a()) {
         if (var1 == null || TypeUtil.isVoid(var1)) {
            throw new NullPointerException("Illegal parameter type: " + var1);
         }

         this.kS.add(this.pC(var1, null, var3));
         this.UT.add(var2);
      }
   }

   @Override
   public String getRoutineName() {
      return this.ld;
   }

   public void pC(String var1) {
      this.ld = var1;
   }

   @Override
   public ICallingConvention getCallingConvention() {
      return this.pC;
   }

   public void pC(ICallingConvention var1) {
      this.vP();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      INativeItem var2 = var1.getItem();
      if (this.A.contains(var2) || this.kS.contains(var2)) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            this.xC();
         } else {
            this.pC(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   protected void A() {
      super.A();

      for (aye var2 : this.A) {
         var2.removeListener(this);
      }

      for (aye var4 : this.kS) {
         var4.removeListener(this);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("Prototype(%s)", this.getSignature(false));
   }
}
