package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.CallGraphVertex;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICallGraph;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.collect.ConcurrentHashSet;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

@Ser
public class WP {
   @SerId(1)
   private aaf q;
   @SerId(2)
   private Map RF = new ConcurrentHashMap();
   @SerId(3)
   private Map xK = new ConcurrentHashMap();

   public ICallGraph q() {
      aau var1 = new aau(this.q);

      for (Entry var3 : this.RF.entrySet()) {
         long var4 = (Long)var3.getKey();

         for (CallGraphVertex var7 : (Set)var3.getValue()) {
            if (var7.isInternal()) {
               var1.recordInternalCall(var4, var7.getInternalAddress(), true);
            } else if (var7.isResolved()) {
               var1.recordExternalCall(var4, var7.getRoutine(), true);
            } else {
               var1.recordUnresolvedCall(var4, var7.getDereferencedAddress(), true);
            }
         }
      }

      return var1;
   }

   public WP(aaf var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.q = var1;
      }
   }

   public void q(long var1, CodePointer var3) {
      if (!var3.isUnknownAddress()) {
         this.q(var1, CallGraphVertex.buildFromInternalTarget(var3));
      } else {
         throw new IllegalArgumentException("Entry point is an unknown address");
      }
   }

   public void q(long var1, INativeMethodItem var3) {
      this.q(var1, CallGraphVertex.buildFromExternalTarget(var3));
   }

   public void q(long var1, long var3) {
      this.q(var1, CallGraphVertex.buildFromUnresolvedTarget(var3));
   }

   private void q(long var1, CallGraphVertex var3) {
      boolean var4 = false;

      try (ACLock var5 = this.q.q.a()) {
         Object var6 = (Set)this.RF.get(var1);
         if (var6 == null) {
            var6 = new ConcurrentHashSet();
            this.RF.put(var1, var6);
         }

         if (var6.add(var3)) {
            var4 = true;
         }

         Object var7 = (Set)this.xK.get(var3);
         if (var7 == null) {
            var7 = new ConcurrentHashSet();
            this.xK.put(var3, var7);
         }

         if (var7.add(var1)) {
            var4 = true;
         }

         if (var4) {
            this.q.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1);
            if (var3.isInternal()) {
               this.q.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var3.getInternalAddress());
            }
         }
      }
   }

   public void q(long var1) {
      try (ACLock var3 = this.q.q.a()) {
         boolean var4 = this.RF.remove(var1) != null;
         ConcurrentHashSet var6 = new ConcurrentHashSet();

         for (Entry var8 : this.xK.entrySet()) {
            boolean var5 = ((Set)var8.getValue()).remove(var1);
            if (((Set)var8.getValue()).size() == 0) {
               var6.add((CallGraphVertex)var8.getKey());
            }

            var4 = var4 || var5;
         }

         for (CallGraphVertex var12 : var6) {
            this.xK.remove(var12);
         }

         if (var4) {
            this.q.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1);
         }
      }
   }

   public Set RF(long var1) {
      return (Set)this.RF.get(var1);
   }

   public List q(INativeMethodItem var1) {
      if (var1.getData() == null) {
         throw new IllegalArgumentException("Callees can not be retrieved for external routine");
      } else {
         return this.q(var1.getData());
      }
   }

   public List q(INativeMethodDataItem var1) {
      ArrayList var2 = new ArrayList();

      for (AddressableInstruction var4 : var1.getCFG().addressableInstructions()) {
         Set var5 = (Set)this.RF.get(var4.getOffset());
         if (var5 != null) {
            var2.addAll(var5);
         }
      }

      return var2;
   }

   public Set xK(long var1) {
      HashSet var3 = new HashSet();
      Set var4 = this.RF(var1);
      if (var4 != null) {
         for (CallGraphVertex var6 : var4) {
            Object var7 = null;
            if (var6.isInternal()) {
               var7 = this.q.oW(var6.getInternalAddress().getAddress());
               if (var7 == null) {
                  INativeContinuousItem var8 = this.q.getItemAt(var6.getInternalAddress().getAddress());
                  if (var8 instanceof axu) {
                     var7 = ((axu)var8).cC();
                  }
               }
            } else if (var6.isResolved()) {
               var7 = var6.getRoutine();
            } else {
               INativeContinuousItem var9 = this.q.getItemAt(var6.getDereferencedAddress());
               if (var9 instanceof axu) {
                  var7 = ((axu)var9).cC();
               }
            }

            if (var7 != null) {
               var3.add(var7);
            }
         }
      }

      return var3;
   }

   public Set q(CallGraphVertex var1) {
      return (Set)this.xK.get(var1);
   }

   public Set RF(INativeMethodItem var1) {
      if (var1.getData() == null) {
         Set var2 = (Set)this.xK.get(CallGraphVertex.buildFromExternalTarget(var1));
         return var2 == null ? Collections.emptySet() : var2;
      } else {
         return this.RF(var1.getData());
      }
   }

   public Set RF(INativeMethodDataItem var1) {
      return this.RF(CodePointer.createFrom(var1));
   }

   private Set RF(CodePointer var1) {
      Set var2 = (Set)this.xK.get(CallGraphVertex.buildFromInternalTarget(var1));
      return var2 == null ? Collections.emptySet() : var2;
   }

   public Set q(CodePointer var1) {
      HashSet var2 = new HashSet();

      for (Long var4 : this.RF(var1)) {
         for (Long var6 : this.q.getContainedRoutineAddresses(var4)) {
            axp var7 = this.q.oW(var6);
            var2.add(var7);
         }
      }

      return var2;
   }
}
