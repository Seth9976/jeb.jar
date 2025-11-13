package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeObjectTracker;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventSubType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public class aek implements INativeObjectTracker, INativeItemListener {
   private static final ILogger q = aeg.q(aek.class);
   @SerId(1)
   private INativeDecompilerContext RF;
   @SerId(2)
   private IWildcardTypeManager xK;
   @SerId(3)
   private IdentityHashMap Dw = new IdentityHashMap();
   @SerId(4)
   private IdentityHashMap Uv = new IdentityHashMap();

   public aek(INativeDecompilerContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Need a decompiler");
      } else {
         this.RF = var1;
         this.xK = var1.getWildcardTypeManager();
         if (this.xK == null) {
            throw new IllegalArgumentException("The decompiler does not have an etypeman!");
         }
      }
   }

   @Override
   public int getCountOfObjects() {
      return this.Dw.size();
   }

   @Override
   public void acquire(INativeItem var1, INativeMethodItem var2) {
      aej var3 = (aej)this.Dw.get(var1);
      if (var3 == null) {
         var3 = new aej(var1);
         this.Dw.put(var1, var3);
         var1.addListener(this);
      }

      var3.q(var2);
   }

   @Override
   public void release(INativeItem var1, INativeMethodItem var2) {
      aej var3 = (aej)this.Dw.get(var1);
      if (var3 != null) {
         if (var3.q(var2, false) && !var3.xK()) {
            this.Dw.remove(var1);
            var1.removeListener(this);
         }
      }
   }

   @Override
   public void releaseAllFromUser(INativeMethodItem var1) {
      ArrayList var2 = new ArrayList();

      for (INativeItem var4 : this.Dw.keySet()) {
         aej var5 = (aej)this.Dw.get(var4);
         if (var5.q(var1, true) && !var5.xK()) {
            var2.add(var4);
         }
      }

      for (INativeItem var7 : var2) {
         this.Dw.remove(var7);
         var7.removeListener(this);
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      INativeItem var2 = var1.getItem();
      aej var3 = (aej)this.Dw.get(var2);
      if (var3 != null) {
         switch (var1.getType()) {
            case DISPOSED:
            case MODIFIED:
               if (var2 instanceof INativeMethodItem && var1.getSubtype() == NativeItemEventSubType.PROTOTYPE_UPDATED) {
                  this.Uv.remove(var2);
               }

               aea var4 = (aea)this.RF.getEngine();
               if (var4.Uv() == var2) {
                  ArrayList var5 = new ArrayList();

                  for (aej.eo var7 : var3.Dw()) {
                     INativeMethodItem var8 = var7.q();
                     if (var8 != null && var8 != var2) {
                        var5.add(var8);
                     }
                  }

                  for (INativeMethodItem var13 : var5) {
                     var4.RF((INativeItem)var13);
                  }
               } else {
                  this.Dw.remove(var2);
                  if (var3.RF() != null) {
                     alr var9 = (alr)this.RF.getGlobalContext();
                     var9.q(var3.RF());
                  }

                  for (aej.eo var12 : var3.Dw()) {
                     if (var12.q() != null) {
                        var4.RF((INativeItem)var12.q());
                     }
                  }
               }
         }
      }
   }

   @Override
   public INativeItem getNativeItemFromVar(IEVar var1) {
      if (var1 != null) {
         int var2 = var1.getId();

         for (Entry var4 : this.Dw.entrySet()) {
            Integer var5 = ((aej)var4.getValue()).RF();
            if (var5 != null && var5 == var2) {
               return (INativeItem)var4.getKey();
            }
         }
      }

      return null;
   }

   @Override
   public IEVar getSymbolForNativeItem(INativeItem var1, INativeMethodItem var2, IERoutineContext var3) {
      Object var4 = null;
      INativeType var5 = null;
      String var6;
      if (var1 instanceof INativeMethodItem) {
         var6 = var1.getName();
         var4 = ((INativeMethodItem)var1).getMemoryAddress();
      } else {
         if (!(var1 instanceof INativeDataItem)) {
            throw new IllegalArgumentException("Item must be a method item or data item");
         }

         var6 = var1.getName();
         if (var6 == null) {
            var6 = Strings.ff("%X", var4);
         }

         var6 = "ptr_" + var6;
         var4 = ((INativeDataItem)var1).getMemoryAddress();
         var5 = ((INativeDataItem)var1).getType();
      }

      aej var7 = (aej)this.Dw.get(var1);
      if (var7 != null && var7.RF() != null) {
         this.acquire(var1, var2);
      } else {
         IEVar var8 = var3.getGlobalContext().createGlobalReference(var6, (Long)var4);
         if (var5 != null) {
            IWildcardType var9 = this.xK.create(var5.getReference());
            var8.setType(var9);
         }

         this.acquire(var1, var2);
         var7 = (aej)this.Dw.get(var1);
         var7.q(var8.getId());
         this.acquire(var1, null);
      }

      return var3.getVariableById(var7.RF());
   }

   public Map q() {
      return this.Dw;
   }

   public String q(IERoutineContext var1) {
      StringBuilder var2 = new StringBuilder();
      Strings.ff(var2, "Native items used by routine context: %s\n", var1);

      for (INativeItem var4 : this.Dw.keySet()) {
         aej var5 = (aej)this.Dw.get(var4);

         for (aej.eo var7 : var5.Dw()) {
            if (var7.q() == var1) {
               Strings.ff(var2, "- %s", var4);
               if (var5.RF() != null) {
                  Strings.ff(var2, " (symbol: %s)", var5.RF());
               }

               var2.append("\n");
            }
         }
      }

      return var2.toString();
   }

   @Override
   public IWildcardPrototype getCandidatePrototype(INativeMethodItem var1) {
      IWildcardPrototype var2 = null;
      ani var3 = (ani)this.Uv.get(var1);
      if (var3 != null && var3.q != null) {
         var2 = var3.q;
      }

      if (var1.isAutoGeneratedPrototype() && var2 != null) {
         return var2;
      } else {
         IPrototypeItem var4 = var1.getPrototype();
         if (var4 != null) {
            var2 = this.xK.createPrototype(var4);
            this.setCandidatePrototype(var1, var2, 2);
         }

         return var2;
      }
   }

   @Override
   public boolean setCandidatePrototype(INativeMethodItem var1, IWildcardPrototype var2, int var3) {
      Object[] var10000 = new Object[]{var1, var2};
      ani var4 = new ani();
      var4.q = var2;
      var4.RF = var3;
      this.Uv.put(var1, var4);
      this.acquire(var1, null);
      return true;
   }

   @Override
   public String toString() {
      return this.RF();
   }

   public String RF() {
      StringBuilder var1 = new StringBuilder("TRACKED:\n");

      for (aej var3 : this.Dw.values()) {
         Strings.ff(var1, "- %s\n", var3);
      }

      return var1.toString();
   }

   public void xK() {
      for (Entry var2 : this.Dw.entrySet()) {
         ((INativeItem)var2.getKey()).removeListener(this);
      }

      this.Dw.clear();
      this.Uv.clear();
      this.RF = null;
      this.xK = null;
   }
}
