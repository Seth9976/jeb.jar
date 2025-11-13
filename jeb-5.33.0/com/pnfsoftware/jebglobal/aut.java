package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ILabelManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModelListener;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEvent;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventSubType;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class aut extends aun implements IMemoryModelListener, INativeMethodDataItem {
   @SerId(1)
   private auu pC;
   @SerId(2)
   private CFG kS;
   @SerId(3)
   private ILabelManager wS;
   @SerId(4)
   private iF UT;
   @SerId(5)
   private l ld;
   @SerId(6)
   private volatile List gp;

   public aut(CFG var1, INativeCodeModel var2, ayy var3, String var4) {
      super(Integer.MIN_VALUE, var4);
      this.kS = var1;
      this.wS = var2.getLabelManager();
      this.UT = new iF(var2.getBitsize(), var3.getPointerSize(), this, var2.getLock());
      this.UT.addListener(this);
      this.ld = new l(this.UT, var3);
      this.pC(var2.getLock());
   }

   @Override
   protected void A() {
      super.A();
      this.UT.removeListener(this);
      this.kS(null);
   }

   @Override
   protected void c_() {
      super.c_();
      if (this.A != null && this.A instanceof ZH) {
         ((ZH)this.A).pC(this.getMemoryAddress(), this.ys());
      }
   }

   @Override
   public void onModelUpdate(MemoryModelEvent var1) {
      if (var1.getModel() == this.UT) {
         if (var1.getType().isDeepChange()) {
            this.pC(NativeItemEventType.MODIFIED);
         } else {
            this.pC(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      auu var2 = this.E();
      if (var2 != null && var1.getItem() == var2) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            this.kS(null);
            return;
         }

         if (var1.getType() == NativeItemEventType.UPDATED) {
            if (var1.getSubtype() == NativeItemEventSubType.RENAMED) {
               String var3 = var2.E().getName();
               String var4 = var3;

               while (var4.startsWith("→")) {
                  var4 = var4.substring("→".length());
               }

               String var5 = this.getName();
               if (var5.startsWith("→") && !var5.endsWith(var4)) {
                  this.setName("→" + var3);
               }
            }

            if (var1.getSubtype() == NativeItemEventSubType.ROUTINE_SET) {
               this.kS((auu)var1.getDetails());
            } else if (var1.getSubtype() == null || !var1.getSubtype().isAttributeRelated()) {
               for (auu var7 : this.getMethodReferences()) {
                  if (!var7.equals(var2)) {
                     var7.setPrototype(var2.UT());
                     var7.setParameterNames(var2.getParameterNames());
                     if (!var7.getName().equals(this.getName())) {
                        var7.setName(this.getName());
                     }

                     if (!var2.getName().equals(var2.E().getName())) {
                        var2.setName(var2.E().getName());
                     }
                  }
               }

               this.pC(var1.getType());
            }
         }
      }
   }

   void pC(ays var1) {
      if (var1 != null) {
         this.ld.pC(var1, null);
      }
   }

   @Override
   public void setName(String var1) {
      try (ACLock var2 = this.gp().a()) {
         if (this.wS != null) {
            this.wS.setLabel(this.getMemoryAddress(), var1, true, false, false);
         } else {
            super.setName(var1);
         }
      }
   }

   @Override
   public long getMemoryAddress() {
      return this.kS.getEntryAddress();
   }

   public auu kS() {
      return this.pC;
   }

   @Override
   public List getMethodReferences() {
      ArrayList var1 = new ArrayList();
      if (this.pC != null) {
         var1.add(this.pC);
      }

      if (this.gp != null) {
         var1.addAll(this.gp);
      }

      return var1;
   }

   void pC(auu var1) {
      this.gp().verifyLocked();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var1 != this.pC && (this.gp == null || !this.gp.contains(var1))) {
         if (this.pC == null) {
            this.pC = var1;
         } else {
            if (this.gp == null) {
               this.gp = new CopyOnWriteArrayList();
            }

            this.gp.add(var1);
         }
      }
   }

   void A(auu var1) {
      this.gp().verifyLocked();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (this.pC == var1) {
            if (this.gp != null && !this.gp.isEmpty()) {
               this.pC = (auu)this.gp.get(0);
               this.gp.remove(0);
            } else {
               this.pC = null;
               this.xC();
            }
         } else if (this.gp != null && this.gp.contains(var1)) {
            this.gp.remove(var1);
         }
      }
   }

   @Override
   public CFG getCFG() {
      return this.kS;
   }

   public iF wS() {
      return this.UT;
   }

   public l UT() {
      return this.ld;
   }

   public boolean A(long var1) {
      return this.kS.getBlockContaining(var1) != null;
   }

   public void kS(auu var1) {
      try (ACLock var2 = this.gp().a()) {
         auu var3 = this.E();
         if (var3 != null) {
            var3.removeListener(this);
         }

         if (var1 == null) {
            this.removeAttribute("trampoline");
         } else {
            this.setAttribute("trampoline", var1);

            for (auu var5 : this.getMethodReferences()) {
               var5.setPrototype(var1.UT());
               var5.setParameterNames(var1.getParameterNames());
            }

            var1.addListener(this);
         }
      }
   }

   public auu E() {
      return (auu)this.getAttribute("trampoline", auu.class);
   }

   @Override
   public void setSPDeltaOnReturn(Integer var1) {
      this.setAttribute("stackPointerDeltaOnReturn", var1);
   }

   @Override
   public Integer getSPDeltaOnReturn() {
      return (Integer)this.getAttribute("stackPointerDeltaOnReturn", Integer.class);
   }

   public void pC(boolean var1) {
      this.setAttribute("deferReferencesDisposal", var1);
   }

   public boolean sY() {
      Boolean var1 = (Boolean)this.getAttribute("deferReferencesDisposal", Boolean.class);
      return var1 != null && var1;
   }

   public void E(boolean var1) {
      this.setAttribute("keepPreRoutines", var1);
   }

   public boolean ys() {
      Boolean var1 = (Boolean)this.getAttribute("keepPreRoutines", Boolean.class);
      return var1 != null && var1;
   }

   public void sY(boolean var1) {
      this.setAttribute("potentialSwitches", var1);
   }

   @Override
   public boolean isPotentialSwitches() {
      Boolean var1 = (Boolean)this.getAttribute("potentialSwitches", Boolean.class);
      return var1 != null && var1;
   }

   public void pC(Set var1) {
      this.setAttribute("badAddresses", var1);
   }

   @Override
   public Set getBadAddresses() {
      return (Set)this.getAttribute("badAddresses", Set.class);
   }

   @Override
   public String toString() {
      return Strings.ff("RoutineCode@%X(end:%X,attr:%s)", this.getMemoryAddress(), this.kS.getEndAddress(), this.ys == null ? '-' : this.ys.keySet());
   }
}
