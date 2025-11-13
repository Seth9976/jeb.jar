package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class aur extends auo implements INativeFieldItem, ayn {
   @SerId(1)
   private ayy pC;
   @SerId(2)
   private INativeType A;
   @SerId(3)
   private INativeDataItem kS;
   @SerId(4)
   private ayv wS;
   @SerId(5)
   private ayu UT;

   public aur(ayy var1, String var2, aye var3) {
      super(0, var2);
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.pC(var1.wS());
         this.A = var3;
         var3.addListener(this);
      }
   }

   public aur(ayy var1, INativeDataItem var2) {
      super(Integer.MIN_VALUE, var2.getName(true));
      this.pC = var1;
      this.kS = var2;
      var2.addListener(this);
   }

   public aur(ayy var1, ayv var2, ayu var3) {
      super(Integer.MIN_VALUE, var3.getName());
      this.pC = var1;
      if (var2.getIndexOfField(var3) == -1) {
         throw new RuntimeException();
      } else {
         this.wS = var2;
         this.UT = var3;
         var2.addListener(this);
      }
   }

   @Override
   public ayy kS() {
      return this.pC;
   }

   @Override
   public IClassType getClassType() {
      ayo var1 = this.pC.pC((auo)this).pC();
      return var1 != null && var1.A() instanceof auq ? ((auq)var1.A()).sY() : null;
   }

   public auq wS() {
      ayo var1 = this.pC.pC((auo)this).pC();
      return var1 != null && var1.A() instanceof auq ? (auq)var1.A() : null;
   }

   @Override
   public INativeType getFieldType() {
      if (this.kS != null) {
         return this.kS.getType();
      } else {
         return (INativeType)(this.UT != null ? this.UT.pC() : this.A);
      }
   }

   @Override
   public INativeDataItem getData() {
      return this.kS;
   }

   ayu UT() {
      return this.UT;
   }

   @Override
   public Couple getStructureFieldDetails() {
      return this.wS == null ? null : new Couple(this.wS, this.UT);
   }

   @Override
   public String getName(boolean var1) {
      if (this.kS != null) {
         return this.kS.getName(var1);
      } else {
         return this.UT != null ? this.UT.getName() : super.getName(var1);
      }
   }

   @Override
   public void setName(String var1) {
      if (this.kS != null) {
         this.kS.setName(var1);
      }

      if (this.wS != null) {
         this.wS.pC(this.UT, var1);
      }

      super.setName(var1);
   }

   @Override
   public String getAddress(boolean var1) {
      auo var2 = this.pC.A(this);
      return var2 != null ? var2.getAddress(var1) + "::" + this.getName(var1) : this.getName(var1);
   }

   public boolean E() {
      auq var1 = this.wS();
      return var1 == null ? false : var1.getInstanceFields().contains(this);
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() != null) {
         if (this.A != null && var1.getItem() == this.A) {
            this.gp().verifyLocked();
            if (var1.getType() == NativeItemEventType.DISPOSED) {
               this.xC();
            } else {
               this.pC(var1.getType());
            }
         } else if (this.kS != null && var1.getItem() == this.kS) {
            if (var1.getType() == NativeItemEventType.DISPOSED) {
               this.kS(Integer.MIN_VALUE, false);
               this.A = this.kS.getType();
               this.A.addListener(this);
               this.kS = null;
               this.eP();
            } else {
               this.pC(var1.getType());
            }
         }

         if (this.wS != null && var1.getItem() == this.wS) {
            if (var1.getType() == NativeItemEventType.DISPOSED) {
               this.kS(Integer.MIN_VALUE, false);
               this.A = this.UT.pC();
               this.A.addListener(this);
               this.wS = null;
               this.UT = null;
               this.eP();
            } else if (var1.getType() == NativeItemEventType.MODIFIED) {
               if (var1.getDetails() == this.UT) {
                  int var2 = this.wS.getIndexOfField(this.UT);
                  if (var2 < 0) {
                     this.kS(Integer.MIN_VALUE, false);
                     this.A = this.UT.pC();
                     this.A.addListener(this);
                     this.wS = null;
                     this.UT = null;
                     this.eP();
                  }
               }
            } else if (var1.getDetails() == this.UT) {
               this.pC(var1.getType());
            }
         }
      }
   }

   @Override
   protected void A() {
      super.A();
      if (this.A != null) {
         this.A.removeListener(this);
      }

      if (this.kS != null) {
         this.kS.removeListener(this);
         this.kS.dispose(true);
      }

      if (this.wS != null) {
         this.wS.removeListener(this);
         this.wS.pC(this.UT);
      }
   }

   @Override
   protected final void c_() {
      super.c_();
      this.pC.pC(this);
   }

   @Override
   public String toString() {
      return Strings.ff("Field{%s}", this.getName(true));
   }
}
