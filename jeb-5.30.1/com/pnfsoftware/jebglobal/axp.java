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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class axp extends axj implements INativeMethodItem, bbn {
   private static final ILogger KT = GlobalLog.getLogger(axp.class);
   public static final String q = "natSigCheck";
   public static final String RF = "natSigMatchResultAttribute";
   public static final String xK = "natSigNamedFromCaller";
   public static final String Dw = "natCodelessSigMatch";
   public static final String Uv = "mangledNameAttribute";
   public static final String zz = "demangledNameAttribute";
   public static final String JY = "demangledFullNameAttribute";
   public static final String HF = "callingConventionHint";
   public static final String LK = "isNonReturning";
   public static final String io = "needCallersAnalysis";
   public static final String qa = "placeholderMethod";
   public static final String Hk = "exceptionProcessingStatus";
   public static final String Me = "exceptionInfo";
   public static final String PV = "tailCallProcessingStatus";
   public static final String oQ = "dwarfSignature";
   @SerId(1)
   private bby Gf;
   @SerId(2)
   private bbs Ef;
   @SerId(3)
   private axo cC;
   @SerId(4)
   private List sH = new CopyOnWriteArrayList();
   @SerId(5)
   private Long CE;
   @SerId(6)
   private bbt wF;
   @SerTransient
   boolean xW;

   public axp(bby var1, String var2, bbs var3, axo var4) {
      super(0, var2);
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Gf = var1;
         this.q(var1.oW());
         this.qa();
         this.setPrototype(var3);
         this.q(var4, false);
         this.io();
      }
   }

   public void q(axo var1, boolean var2) {
      this.za().verifyLocked();
      if (this.cC != null && var1 != null && this.cC != var1) {
         throw new IllegalStateException("A routine definition was already associated; it must be reset before attempting to set another one");
      } else if (this.cC != null || var1 != null) {
         if (this.cC == null) {
            if (this.CE != null && var1.getMemoryAddress() != this.CE) {
               throw new IllegalArgumentException("The routine definition can be updated only if it has the same original base address");
            }

            if (this.CE == null) {
               this.CE = var1.getMemoryAddress();
            }

            this.CE = var1.getMemoryAddress();
            this.cC = var1;
            this.RF(Integer.MIN_VALUE, false);
            this.cC.addListener(this);
            this.cC.q(this);
         } else {
            this.xK(Integer.MIN_VALUE, false);
            this.cC.removeListener(this);
            this.cC.RF(this);
            this.cC = null;
         }

         this.Uv(var2);
      }
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() != null) {
         if (var1.getItem() == this.Ef) {
            if (var1.getType() == NativeItemEventType.DISPOSED) {
               this.Ef.removeListener(this);
               this.setPrototype(null);
            } else {
               this.q(var1.getType());
            }
         } else if (var1.getItem() == this.cC && var1.getType() == NativeItemEventType.DISPOSED) {
            if (this.cC.gO()) {
               this.q(null, true);
            } else {
               this.PV();
            }
         }
      }
   }

   @Override
   protected void RF() {
      super.RF();
      if (this.cC != null) {
         this.cC.removeListener(this);
         this.cC.RF(this);
      }

      if (this.Ef != null) {
         this.Ef.removeListener(this);
      }
   }

   @Override
   protected final void c_() {
      super.c_();
      this.Gf.q(this);
   }

   @Override
   public bby xK() {
      return this.Gf;
   }

   public bbt Dw() {
      if (this.wF != null) {
         return this.wF;
      } else {
         if (this.za().isLockedByCurrentThread()) {
            try {
               bbt var2;
               try (ACLock var1 = this.za().a()) {
                  var2 = this.rL();
               }

               return var2;
            } catch (UnitLockedException var6) {
            }
         }

         return this.Gf.gP();
      }
   }

   private bbt rL() {
      this.wF = this.Ef == null ? this.Gf.gP() : this.Gf.q(this.Ef, 1);
      return this.wF;
   }

   public bbs Uv() {
      return this.Ef;
   }

   @Override
   public void setPrototype(IPrototypeItem var1) {
      this.q(var1, false);
   }

   public void q(IPrototypeItem var1, boolean var2) {
      this.q((bbs)var1, var2, null);
   }

   private void q(bbs var1, boolean var2, ami var3) {
      this.Me();

      try (ACLock var4 = this.za().a()) {
         boolean var5 = false;
         if (this.Ef != var1) {
            if (this.Ef != null) {
               this.Ef.removeListener(this);
            }

            this.Ef = var1;
            this.rL();
            if (this.Ef != null) {
               this.Ef.addListener(this);
            }

            if (this.cC != null && this.gO()) {
               this.cC.q(this.Ef);
            }

            var5 = true;
         }

         this.xW = var2;
         if (var5) {
            this.q(new NativeItemEvent(NativeItemEventType.MODIFIED, this, NativeItemEventSubType.PROTOTYPE_UPDATED, var1));
         }
      }
   }

   @Override
   public boolean isAutoGeneratedPrototype() {
      return this.xW;
   }

   public axo oW() {
      return this.cC;
   }

   public boolean gO() {
      return this.cC != null && this.cC.xK() == this;
   }

   @Override
   public void setName(String var1) {
      try (ACLock var2 = this.za().a()) {
         boolean var3 = this.If();
         bbo var4 = this.Gf.q((axj)this).q();
         if (var4 != null && var4.RF() instanceof axl) {
            axl var5 = (axl)var4.RF();
            Couple var6 = var5.getCoordinatesOfVirtualMethod(this);
            if (var6 != null) {
               bbi var7 = var5.gO();
               if (var7 != null && !var7.q) {
                  int var8 = (Integer)var6.getFirst();
                  int var9 = (Integer)var6.getSecond();
                  var7.renameVirtualMethod(var8, var9, var1);
                  return;
               }
            }
         }

         if (this.cC != null && this.gO()) {
            this.cC.setName(var1);
         }

         super.setName(var1);
         if (var3) {
            this.RF(false);
         }
      }
   }

   @Override
   public String getName(boolean var1) {
      return this.cC != null && this.gO() ? this.cC.getName(var1) : super.getName(var1);
   }

   @Override
   public String getFinalName() {
      return this.cC != null && this.cC.oW() != null ? this.cC.oW().getFinalName() : this.getName();
   }

   @Override
   public String getAddress(boolean var1) {
      if (this.isDisposed()) {
         return null;
      } else {
         axl var2 = this.gP();
         return var2 != null ? var2.getAddress() + "::" + this.getName(true) : this.getName(var1);
      }
   }

   @Override
   public String getSignature(boolean var1) {
      return this.Ef != null ? this.Ef.q(var1, this.getName(var1), this.getParameterNames()) : super.getSignature(var1);
   }

   public bbi nf() {
      axl var1 = this.gP();
      return var1 == null ? null : var1.gO();
   }

   public axl gP() {
      bbo var1 = this.Gf.q((axj)this).q();
      return var1 != null && var1.RF() instanceof axl ? (axl)var1.RF() : null;
   }

   @Override
   public boolean isVirtualMethod() {
      axl var1 = this.gP();
      return var1 == null ? false : var1.getVirtualMethods(true).contains(this);
   }

   public bbd cC() {
      return this.Ef == null ? null : this.Ef.nf();
   }

   @Override
   public List getParameterTypes() {
      return this.Ef == null ? null : this.Ef.getParameterTypes();
   }

   public ICallingConvention sH() {
      return this.Ef == null ? null : this.Ef.getCallingConvention();
   }

   @Override
   public List getParameterNames() {
      return this.sH;
   }

   @Override
   public String getParameterName(int var1) {
      return var1 >= this.sH.size() ? null : (String)this.sH.get(var1);
   }

   @Override
   public void setParameterNames(Collection var1) {
      try (ACLock var2 = this.za().a()) {
         this.Me();
         this.sH.clear();
         this.sH.addAll(var1);
         this.q(NativeItemEventType.UPDATED);
      }
   }

   @Override
   public void setParameterName(int var1, String var2) {
      if (var2 != null && var2.length() != 0) {
         if (var1 < 100) {
            try (ACLock var3 = this.za().a()) {
               this.Me();

               while (this.sH.size() <= var1) {
                  this.sH.add(null);
               }

               this.sH.set(var1, var2);
               this.q(NativeItemEventType.UPDATED);
            }
         }
      }
   }

   public boolean CE() {
      Boolean var1 = (Boolean)this.getAttribute("natSigCheck", Boolean.class);
      return var1 == null || var1;
   }

   public void q(Boolean var1) {
      this.setAttribute("natSigCheck", var1);
   }

   public void q(com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oL var1) {
      this.setAttribute("natSigMatchResultAttribute", var1);
   }

   public com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oL wF() {
      return (com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oL)this.getAttribute(
         "natSigMatchResultAttribute", com.pnfsoftware.jeb.corei.parsers.asm.nativesig.oL.class
      );
   }

   public void RF(Boolean var1) {
      this.setAttribute("natSigNamedFromCaller", var1);
   }

   public boolean If() {
      Boolean var1 = (Boolean)this.getAttribute("natSigNamedFromCaller", Boolean.class);
      return var1 != null && var1;
   }

   public void q(com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.CU var1) {
      this.setAttribute("natCodelessSigMatch", var1);
   }

   public com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.CU Dz() {
      return (com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.CU)this.getAttribute(
         "natCodelessSigMatch", com.pnfsoftware.jeb.corei.parsers.asm.nativesig.codeless.CU.class
      );
   }

   public void q(String var1) {
      this.setAttribute("mangledNameAttribute", var1);
   }

   public String mI() {
      return (String)this.getAttribute("mangledNameAttribute", String.class);
   }

   public void RF(String var1) {
      this.setAttribute("demangledFullNameAttribute", var1);
   }

   public String jq() {
      return (String)this.getAttribute("demangledFullNameAttribute", String.class);
   }

   public void xK(String var1) {
      this.setAttribute("demangledNameAttribute", var1);
   }

   public String ui() {
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
      return this.wF() != null || this.Dz() != null;
   }

   public void xK(Boolean var1) {
      this.setAttribute("placeholderMethod", var1);
   }

   public adn TX() {
      return (adn)this.getAttribute("exceptionInfo", adn.class);
   }

   public void q(adn var1) {
      this.setAttribute("exceptionInfo", var1);
   }

   public Integer Rr() {
      return (Integer)this.getAttribute("exceptionProcessingStatus", Integer.class);
   }

   public void q(Integer var1) {
      this.setAttribute("exceptionProcessingStatus", var1);
   }

   public Integer EB() {
      return (Integer)this.getAttribute("tailCallProcessingStatus", Integer.class);
   }

   public void RF(Integer var1) {
      this.setAttribute("tailCallProcessingStatus", var1);
   }

   public void Dw(String var1) {
      this.setAttribute("dwarfSignature", var1);
   }

   public String Xo() {
      return (String)this.getAttribute("dwarfSignature", String.class);
   }

   public void Dw(Boolean var1) {
      boolean var2 = this.xK(true);

      try {
         this.setNonReturning(var1);
      } finally {
         this.xK(var2);
      }
   }

   public boolean Bu() {
      Boolean var1 = (Boolean)this.getAttribute("needCallersAnalysis", Boolean.class);
      return var1 != null && var1;
   }

   public void q(boolean var1) {
      this.setAttribute("needCallersAnalysis", var1);
   }

   public Long IN() {
      return this.CE;
   }

   public void q(Long var1) {
      this.CE = var1;
   }

   @Deprecated
   @Override
   public List getInstructions() {
      return this.cC != null ? this.cC.getCFG().getInstructions() : null;
   }

   public static Collection q(Collection var0) {
      ArrayList var1 = new ArrayList();

      for (axp var3 : var0) {
         if (var3.oW() != null) {
            var1.add(var3.oW());
         }
      }

      return var1;
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
