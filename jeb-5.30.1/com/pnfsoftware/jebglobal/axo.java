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
public class axo extends axi implements IMemoryModelListener, INativeMethodDataItem {
   public static final String q = "trampoline";
   public static final String RF = "stackPointerDeltaOnReturn";
   public static final String Dw = "analysisStatus";
   public static final String Uv = "potentialSwitches";
   public static final String zz = "deferReferencesDisposal";
   public static final String JY = "keepPreRoutines";
   public static final String HF = "badAddresses";
   @SerId(1)
   private axp LK;
   @SerId(2)
   private CFG io;
   @SerId(3)
   private ILabelManager qa;
   @SerId(4)
   private abr Hk;
   @SerId(5)
   private abq Me;
   @SerId(6)
   private volatile List PV;

   public axo(CFG var1, INativeCodeModel var2, bby var3, String var4) {
      super(Integer.MIN_VALUE, var4);
      this.io = var1;
      this.qa = var2.getLabelManager();
      this.Hk = new abr(var2.getBitsize(), var3.getPointerSize(), this, var2.getLock());
      this.Hk.addListener(this);
      this.Me = new abq(this.Hk, var3);
      this.q(var2.getLock());
   }

   @Override
   protected void RF() {
      super.RF();
      this.Hk.removeListener(this);
      this.xK(null);
   }

   @Override
   protected void c_() {
      super.c_();
      if (this.xK != null && this.xK instanceof Bm) {
         ((Bm)this.xK).q(this.getMemoryAddress(), this.nf());
      }
   }

   @Override
   public void onModelUpdate(MemoryModelEvent var1) {
      if (var1.getModel() == this.Hk) {
         if (var1.getType().isDeepChange()) {
            this.q(NativeItemEventType.MODIFIED);
         } else {
            this.q(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      axp var2 = this.oW();
      if (var2 != null && var1.getItem() == var2) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            this.xK(null);
            return;
         }

         if (var1.getType() == NativeItemEventType.UPDATED) {
            if (var1.getSubtype() == NativeItemEventSubType.RENAMED) {
               String var3 = var2.oW().getName();
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
               this.xK((axp)var1.getDetails());
            } else if (var1.getSubtype() == null || !var1.getSubtype().isAttributeRelated()) {
               for (axp var7 : this.getMethodReferences()) {
                  if (!var7.equals(var2)) {
                     var7.setPrototype(var2.Uv());
                     var7.setParameterNames(var2.getParameterNames());
                     if (!var7.getName().equals(this.getName())) {
                        var7.setName(this.getName());
                     }

                     if (!var2.getName().equals(var2.oW().getName())) {
                        var2.setName(var2.oW().getName());
                     }
                  }
               }

               this.q(var1.getType());
            }
         }
      }
   }

   void q(bbs var1) {
      if (var1 != null) {
         this.Me.q(var1, null);
      }
   }

   @Override
   public void setName(String var1) {
      try (ACLock var2 = this.za().a()) {
         if (this.qa != null) {
            this.qa.setLabel(this.getMemoryAddress(), var1, true, false, false);
         } else {
            super.setName(var1);
         }
      }
   }

   @Override
   public long getMemoryAddress() {
      return this.io.getEntryAddress();
   }

   public axp xK() {
      return this.LK;
   }

   @Override
   public List getMethodReferences() {
      ArrayList var1 = new ArrayList();
      if (this.LK != null) {
         var1.add(this.LK);
      }

      if (this.PV != null) {
         var1.addAll(this.PV);
      }

      return var1;
   }

   void q(axp var1) {
      this.za().verifyLocked();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else if (var1 != this.LK && (this.PV == null || !this.PV.contains(var1))) {
         if (this.LK == null) {
            this.LK = var1;
         } else {
            if (this.PV == null) {
               this.PV = new CopyOnWriteArrayList();
            }

            this.PV.add(var1);
         }
      }
   }

   void RF(axp var1) {
      this.za().verifyLocked();
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (this.LK == var1) {
            if (this.PV != null && !this.PV.isEmpty()) {
               this.LK = (axp)this.PV.get(0);
               this.PV.remove(0);
            } else {
               this.LK = null;
               this.PV();
            }
         } else if (this.PV != null && this.PV.contains(var1)) {
            this.PV.remove(var1);
         }
      }
   }

   @Override
   public CFG getCFG() {
      return this.io;
   }

   public abr Dw() {
      return this.Hk;
   }

   public abq Uv() {
      return this.Me;
   }

   public boolean RF(long var1) {
      return this.io.getBlockContaining(var1) != null;
   }

   public void xK(axp var1) {
      try (ACLock var2 = this.za().a()) {
         axp var3 = this.oW();
         if (var3 != null) {
            var3.removeListener(this);
         }

         if (var1 == null) {
            this.removeAttribute("trampoline");
         } else {
            this.setAttribute("trampoline", var1);

            for (axp var5 : this.getMethodReferences()) {
               var5.setPrototype(var1.Uv());
               var5.setParameterNames(var1.getParameterNames());
            }

            var1.addListener(this);
         }
      }
   }

   public axp oW() {
      return (axp)this.getAttribute("trampoline", axp.class);
   }

   @Override
   public void setSPDeltaOnReturn(Integer var1) {
      this.setAttribute("stackPointerDeltaOnReturn", var1);
   }

   @Override
   public Integer getSPDeltaOnReturn() {
      return (Integer)this.getAttribute("stackPointerDeltaOnReturn", Integer.class);
   }

   public void q(boolean var1) {
      this.setAttribute("deferReferencesDisposal", var1);
   }

   public boolean gO() {
      Boolean var1 = (Boolean)this.getAttribute("deferReferencesDisposal", Boolean.class);
      return var1 != null && var1;
   }

   public void oW(boolean var1) {
      this.setAttribute("keepPreRoutines", var1);
   }

   public boolean nf() {
      Boolean var1 = (Boolean)this.getAttribute("keepPreRoutines", Boolean.class);
      return var1 != null && var1;
   }

   public void gO(boolean var1) {
      this.setAttribute("potentialSwitches", var1);
   }

   @Override
   public boolean isPotentialSwitches() {
      Boolean var1 = (Boolean)this.getAttribute("potentialSwitches", Boolean.class);
      return var1 != null && var1;
   }

   public void q(Set var1) {
      this.setAttribute("badAddresses", var1);
   }

   @Override
   public Set getBadAddresses() {
      return (Set)this.getAttribute("badAddresses", Set.class);
   }

   @Override
   public String toString() {
      return Strings.ff("RoutineCode@%X(end:%X,attr:%s)", this.getMemoryAddress(), this.io.getEndAddress(), this.lm == null ? '-' : this.lm.keySet());
   }
}
