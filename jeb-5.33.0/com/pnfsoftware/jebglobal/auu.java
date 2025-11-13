package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.UnitLockedException;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventSubType;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class auu extends auo implements INativeMethodItem, ayn {
   private static final ILogger A = GlobalLog.getLogger(auu.class);
   @SerId(1)
   private ayy kS;
   @SerId(2)
   private ays wS;
   @SerId(3)
   private aut UT;
   @SerId(4)
   private List ld = new CopyOnWriteArrayList();
   @SerId(5)
   private Long gp;
   @SerId(6)
   private ayt oT;
   @SerTransient
   boolean pC;

   public auu(ayy var1, String var2, ays var3, aut var4) {
      super(0, var2);
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.kS = var1;
         this.pC(var1.wS());
         this.NS();
         this.setPrototype(var3);
         this.pC(var4, false);
         this.WR();
      }
   }

   public void pC(aut var1, boolean var2) {
      this.gp().verifyLocked();
      if (this.UT != null && var1 != null && this.UT != var1) {
         throw new IllegalStateException("A routine definition was already associated; it must be reset before attempting to set another one");
      } else if (this.UT != null || var1 != null) {
         if (this.UT == null) {
            if (this.gp != null && var1.getMemoryAddress() != this.gp) {
               throw new IllegalArgumentException("The routine definition can be updated only if it has the same original base address");
            }

            if (this.gp == null) {
               this.gp = var1.getMemoryAddress();
            }

            this.gp = var1.getMemoryAddress();
            this.UT = var1;
            this.A(Integer.MIN_VALUE, false);
            this.UT.addListener(this);
            this.UT.pC(this);
         } else {
            this.kS(Integer.MIN_VALUE, false);
            this.UT.removeListener(this);
            this.UT.A(this);
            this.UT = null;
         }

         this.UT(var2);
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() != null) {
         if (var1.getItem() == this.wS) {
            if (var1.getType() == NativeItemEventType.DISPOSED) {
               this.wS.removeListener(this);
               this.setPrototype(null);
            } else {
               this.pC(var1.getType());
            }
         } else if (var1.getItem() == this.UT && var1.getType() == NativeItemEventType.DISPOSED) {
            if (this.UT.sY()) {
               this.pC(null, true);
            } else {
               this.xC();
            }
         }
      }
   }

   @Override
   protected void A() {
      super.A();
      if (this.UT != null) {
         this.UT.removeListener(this);
         this.UT.A(this);
      }

      if (this.wS != null) {
         this.wS.removeListener(this);
      }
   }

   @Override
   protected final void c_() {
      super.c_();
      this.kS.pC(this);
   }

   @Override
   public ayy kS() {
      return this.kS;
   }

   public ayt wS() {
      if (this.oT != null) {
         return this.oT;
      } else {
         if (this.gp().isLockedByCurrentThread()) {
            try {
               ayt var2;
               try (ACLock var1 = this.gp().a()) {
                  var2 = this.hZ();
               }

               return var2;
            } catch (UnitLockedException var6) {
            }
         }

         return this.kS.E();
      }
   }

   private ayt hZ() {
      this.oT = this.wS == null ? this.kS.E() : this.kS.pC(this.wS, 1);
      return this.oT;
   }

   public ays UT() {
      return this.wS;
   }

   @Override
   public void setPrototype(IPrototypeItem var1) {
      this.pC(var1, false);
   }

   public void pC(IPrototypeItem var1, boolean var2) {
      this.pC((ays)var1, var2, null);
   }

   private void pC(ays var1, boolean var2, akf var3) {
      this.vP();

      try (ACLock var4 = this.gp().a()) {
         boolean var5 = false;
         if (this.wS != var1) {
            if (this.wS != null) {
               this.wS.removeListener(this);
            }

            this.wS = var1;
            this.hZ();
            if (this.wS != null) {
               this.wS.addListener(this);
            }

            if (this.UT != null && this.sY()) {
               this.UT.pC(this.wS);
            }

            var5 = true;
         }

         this.pC = var2;
         if (var5) {
            this.pC(new NativeItemEvent(NativeItemEventType.MODIFIED, this, NativeItemEventSubType.PROTOTYPE_UPDATED, var1));
         }
      }
   }

   @Override
   public boolean isAutoGeneratedPrototype() {
      return this.pC;
   }

   public aut E() {
      return this.UT;
   }

   public boolean sY() {
      return this.UT != null && this.UT.kS() == this;
   }

   @Override
   public void setName(String var1) {
      try (ACLock var2 = this.gp().a()) {
         boolean var3 = this.Ek();
         ayo var4 = this.kS.pC((auo)this).pC();
         if (var4 != null && var4.A() instanceof auq) {
            auq var5 = (auq)var4.A();
            Couple var6 = var5.getCoordinatesOfVirtualMethod(this);
            if (var6 != null) {
               ayi var7 = var5.sY();
               if (var7 != null && !var7.pC) {
                  int var8 = (Integer)var6.getFirst();
                  int var9 = (Integer)var6.getSecond();
                  var7.renameVirtualMethod(var8, var9, var1);
                  return;
               }
            }
         }

         if (this.UT != null && this.sY()) {
            this.UT.setName(var1);
         }

         super.setName(var1);
         if (var3) {
            this.A(false);
         }
      }
   }

   @Override
   public String getName(boolean var1) {
      return this.UT != null && this.sY() ? this.UT.getName(var1) : super.getName(var1);
   }

   @Override
   public String getFinalName() {
      return this.UT != null && this.UT.E() != null ? this.UT.E().getFinalName() : this.getName();
   }

   @Override
   public String getAddress(boolean var1) {
      if (this.isDisposed()) {
         return null;
      } else {
         auq var2 = this.ld();
         return var2 != null ? var2.getAddress() + "::" + this.getName(true) : this.getName(var1);
      }
   }

   @Override
   public String getSignature(boolean var1) {
      return this.wS != null ? this.wS.pC(var1, this.getName(var1), this.getParameterNames()) : super.getSignature(var1);
   }

   public ayi ys() {
      auq var1 = this.ld();
      return var1 == null ? null : var1.sY();
   }

   public auq ld() {
      ayo var1 = this.kS.pC((auo)this).pC();
      return var1 != null && var1.A() instanceof auq ? (auq)var1.A() : null;
   }

   @Override
   public boolean isVirtualMethod() {
      auq var1 = this.ld();
      return var1 == null ? false : var1.getVirtualMethods(true).contains(this);
   }

   public aye UO() {
      return this.wS == null ? null : this.wS.sY();
   }

   @Override
   public List getParameterTypes() {
      return this.wS == null ? null : this.wS.getParameterTypes();
   }

   public ICallingConvention Ab() {
      return this.wS == null ? null : this.wS.getCallingConvention();
   }

   @Override
   public List getParameterNames() {
      return this.ld;
   }

   @Override
   public String getParameterName(int var1) {
      return var1 >= this.ld.size() ? null : (String)this.ld.get(var1);
   }

   @Override
   public void setParameterNames(Collection var1) {
      try (ACLock var2 = this.gp().a()) {
         this.vP();
         this.ld.clear();
         this.ld.addAll(var1);
         this.pC(NativeItemEventType.UPDATED);
      }
   }

   @Override
   public void setParameterName(int var1, String var2) {
      if (var2 != null && var2.length() != 0) {
         if (var1 < 100) {
            try (ACLock var3 = this.gp().a()) {
               this.vP();

               while (this.ld.size() <= var1) {
                  this.ld.add(null);
               }

               this.ld.set(var1, var2);
               this.pC(NativeItemEventType.UPDATED);
            }
         }
      }
   }

   public boolean rl() {
      Boolean var1 = (Boolean)this.getAttribute("natSigCheck", Boolean.class);
      return var1 == null || var1;
   }

   public void pC(Boolean var1) {
      this.setAttribute("natSigCheck", var1);
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.asm.nativesig.HE var1) {
      this.setAttribute("natSigMatchResultAttribute", var1);
   }

   public com.pnfsoftware.jeb.corei.parsers.asm.nativesig.HE z() {
      return (com.pnfsoftware.jeb.corei.parsers.asm.nativesig.HE)this.getAttribute(
         "natSigMatchResultAttribute", com.pnfsoftware.jeb.corei.parsers.asm.nativesig.HE.class
      );
   }

   public void A(Boolean var1) {
      this.setAttribute("natSigNamedFromCaller", var1);
   }

   public boolean Ek() {
      Boolean var1 = (Boolean)this.getAttribute("natSigNamedFromCaller", Boolean.class);
      return var1 != null && var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.Sv var1) {
      this.setAttribute("natCodelessSigMatch", var1);
   }

   public com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.Sv hK() {
      return (com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.Sv)this.getAttribute(
         "natCodelessSigMatch", com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.Sv.class
      );
   }

   public void pC(String var1) {
      this.setAttribute("mangledNameAttribute", var1);
   }

   public String Er() {
      return (String)this.getAttribute("mangledNameAttribute", String.class);
   }

   public void A(String var1) {
      this.setAttribute("demangledFullNameAttribute", var1);
   }

   public String FE() {
      return (String)this.getAttribute("demangledFullNameAttribute", String.class);
   }

   public void kS(String var1) {
      this.setAttribute("demangledNameAttribute", var1);
   }

   public String Aj() {
      return (String)this.getAttribute("demangledNameAttribute", String.class);
   }

   @Override
   public Boolean getNonReturning() {
      return (Boolean)this.getAttribute("isNonReturning", Boolean.class);
   }

   @Override
   public void setNonReturning(Boolean var1) {
      this.setAttribute("isNonReturning", var1);
   }

   @Override
   public boolean isPlaceholderMethod() {
      Boolean var1 = (Boolean)this.getAttribute("placeholderMethod", Boolean.class);
      return var1 != null && var1;
   }

   @Override
   public boolean isDetectedAsLibraryCode() {
      return this.z() != null || this.hK() != null;
   }

   public void kS(Boolean var1) {
      this.setAttribute("placeholderMethod", var1);
   }

   public abv EX() {
      return (abv)this.getAttribute("exceptionInfo", abv.class);
   }

   public void pC(abv var1) {
      this.setAttribute("exceptionInfo", var1);
   }

   public Integer LM() {
      return (Integer)this.getAttribute("exceptionProcessingStatus", Integer.class);
   }

   public void pC(Integer var1) {
      this.setAttribute("exceptionProcessingStatus", var1);
   }

   public Integer mv() {
      return (Integer)this.getAttribute("tailCallProcessingStatus", Integer.class);
   }

   public void A(Integer var1) {
      this.setAttribute("tailCallProcessingStatus", var1);
   }

   public void wS(String var1) {
      this.setAttribute("dwarfSignature", var1);
   }

   public String sO() {
      return (String)this.getAttribute("dwarfSignature", String.class);
   }

   public void wS(Boolean var1) {
      boolean var2 = this.kS(true);

      try {
         this.setNonReturning(var1);
      } finally {
         this.kS(var2);
      }
   }

   public boolean os() {
      Boolean var1 = (Boolean)this.getAttribute("needCallersAnalysis", Boolean.class);
      return var1 != null && var1;
   }

   public void pC(boolean var1) {
      this.setAttribute("needCallersAnalysis", var1);
   }

   public Long Cu() {
      return this.gp;
   }

   public void pC(Long var1) {
      this.gp = var1;
   }

   @Deprecated
   @Override
   public List getInstructions() {
      return this.UT != null ? this.UT.getCFG().getInstructions() : null;
   }

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("Method{%s}", this.getName(true));
      Long var2 = this.getMemoryAddress();
      if (var2 != null) {
         var1 = var1 + Strings.ff("@%Xh", var2);
      }

      return var1;
   }
}
