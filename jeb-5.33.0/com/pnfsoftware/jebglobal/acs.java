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
import java.util.Map.Entry;

@Ser
public class acs implements INativeObjectTracker, INativeItemListener {
   private static final ILogger pC = aco.pC(acs.class);
   @SerId(1)
   private INativeDecompilerContext A;
   @SerId(2)
   private IWildcardTypeManager kS;
   @SerId(3)
   private IdentityHashMap wS = new IdentityHashMap();
   @SerId(4)
   private IdentityHashMap UT = new IdentityHashMap();

   public acs(INativeDecompilerContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Need a decompiler");
      } else {
         this.A = var1;
         this.kS = var1.getWildcardTypeManager();
         if (this.kS == null) {
            throw new IllegalArgumentException("The decompiler does not have an etypeman!");
         }
      }
   }

   @Override
   public int getCountOfObjects() {
      return this.wS.size();
   }

   @Override
   public void acquire(INativeItem var1, INativeMethodItem var2) {
      acr var3 = (acr)this.wS.get(var1);
      if (var3 == null) {
         var3 = new acr(var1);
         this.wS.put(var1, var3);
         var1.addListener(this);
      }

      var3.pC(var2);
   }

   @Override
   public void release(INativeItem var1, INativeMethodItem var2) {
      acr var3 = (acr)this.wS.get(var1);
      if (var3 != null) {
         if (var3.pC(var2, false) && !var3.A()) {
            this.wS.remove(var1);
            var1.removeListener(this);
         }
      }
   }

   @Override
   public void releaseAllFromUser(INativeMethodItem var1) {
      ArrayList var2 = new ArrayList();

      for (INativeItem var4 : this.wS.keySet()) {
         acr var5 = (acr)this.wS.get(var4);
         if (var5.pC(var1, true) && !var5.A()) {
            var2.add(var4);
         }
      }

      for (INativeItem var7 : var2) {
         this.wS.remove(var7);
         var7.removeListener(this);
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      INativeItem var2 = var1.getItem();
      acr var3 = (acr)this.wS.get(var2);
      if (var3 != null) {
         switch (var1.getType()) {
            case DISPOSED:
            case MODIFIED:
               if (var2 instanceof INativeMethodItem && var1.getSubtype() == NativeItemEventSubType.PROTOTYPE_UPDATED) {
                  this.UT.remove(var2);
               }

               aci var4 = (aci)this.A.getEngine();
               if (var4.wS() == var2) {
                  ArrayList var5 = new ArrayList();

                  for (acr.Av var7 : var3.kS()) {
                     INativeMethodItem var8 = var7.pC();
                     if (var8 != null && var8 != var2) {
                        var5.add(var8);
                     }
                  }

                  for (INativeMethodItem var13 : var5) {
                     var4.A((INativeItem)var13);
                  }
               } else {
                  this.wS.remove(var2);
                  if (var3.pC() != null) {
                     ajo var9 = (ajo)this.A.getGlobalContext();
                     var9.pC(var3.pC());
                  }

                  for (acr.Av var12 : var3.kS()) {
                     if (var12.pC() != null) {
                        var4.A((INativeItem)var12.pC());
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

         for (Entry var4 : this.wS.entrySet()) {
            Integer var5 = ((acr)var4.getValue()).pC();
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

      acr var7 = (acr)this.wS.get(var1);
      if (var7 != null && var7.pC() != null) {
         this.acquire(var1, var2);
      } else {
         IEVar var8 = var3.getGlobalContext().createGlobalReference(var6, (Long)var4);
         if (var5 != null) {
            IWildcardType var9 = this.kS.create(var5.getReference());
            var8.setType(var9);
         }

         this.acquire(var1, var2);
         var7 = (acr)this.wS.get(var1);
         var7.pC(var8.getId());
         this.acquire(var1, null);
      }

      return var3.getVariableById(var7.pC());
   }

   @Override
   public IWildcardPrototype getCandidatePrototype(INativeMethodItem var1) {
      IWildcardPrototype var2 = null;
      ald var3 = (ald)this.UT.get(var1);
      if (var3 != null && var3.pC != null) {
         var2 = var3.pC;
      }

      if (var1.isAutoGeneratedPrototype() && var2 != null) {
         return var2;
      } else {
         IPrototypeItem var4 = var1.getPrototype();
         if (var4 != null) {
            var2 = this.kS.createPrototype(var4);
            this.setCandidatePrototype(var1, var2, 2);
         }

         return var2;
      }
   }

   @Override
   public boolean setCandidatePrototype(INativeMethodItem var1, IWildcardPrototype var2, int var3) {
      Object[] var10000 = new Object[]{var1, var2};
      ald var4 = new ald();
      var4.pC = var2;
      var4.A = var3;
      this.UT.put(var1, var4);
      this.acquire(var1, null);
      return true;
   }

   @Override
   public String toString() {
      return this.pC();
   }

   public String pC() {
      StringBuilder var1 = new StringBuilder("TRACKED:\n");

      for (acr var3 : this.wS.values()) {
         Strings.ff(var1, "- %s\n", var3);
      }

      return var1.toString();
   }

   public void A() {
      for (Entry var2 : this.wS.entrySet()) {
         ((INativeItem)var2.getKey()).removeListener(this);
      }

      this.wS.clear();
      this.UT.clear();
      this.A = null;
      this.kS = null;
   }
}
