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
public class axm extends axj implements INativeFieldItem, bbn {
   @SerId(1)
   private bby q;
   @SerId(2)
   private INativeType RF;
   @SerId(3)
   private INativeDataItem xK;
   @SerId(4)
   private bbv Dw;
   @SerId(5)
   private bbu Uv;

   public axm(bby var1, String var2, bbd var3) {
      super(0, var2);
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         this.q(var1.oW());
         this.RF = var3;
         var3.addListener(this);
      }
   }

   public axm(bby var1, INativeDataItem var2) {
      super(Integer.MIN_VALUE, var2.getName(true));
      this.q = var1;
      this.xK = var2;
      var2.addListener(this);
   }

   public axm(bby var1, bbv var2, bbu var3) {
      super(Integer.MIN_VALUE, var3.getName());
      this.q = var1;
      if (var2.getIndexOfField(var3) == -1) {
         throw new RuntimeException();
      } else {
         this.Dw = var2;
         this.Uv = var3;
         var2.addListener(this);
      }
   }

   @Override
   public bby xK() {
      return this.q;
   }

   @Override
   public IClassType getClassType() {
      bbo var1 = this.q.q((axj)this).q();
      return var1 != null && var1.RF() instanceof axl ? ((axl)var1.RF()).gO() : null;
   }

   public axl Dw() {
      bbo var1 = this.q.q((axj)this).q();
      return var1 != null && var1.RF() instanceof axl ? (axl)var1.RF() : null;
   }

   @Override
   public INativeType getFieldType() {
      if (this.xK != null) {
         return this.xK.getType();
      } else {
         return (INativeType)(this.Uv != null ? this.Uv.q() : this.RF);
      }
   }

   public Integer Uv() {
      return this.Uv != null ? this.Uv.getOffset() : null;
   }

   @Override
   public INativeDataItem getData() {
      return this.xK;
   }

   bbu oW() {
      return this.Uv;
   }

   @Override
   public Couple getStructureFieldDetails() {
      return this.Dw == null ? null : new Couple(this.Dw, this.Uv);
   }

   @Override
   public String getName(boolean var1) {
      if (this.xK != null) {
         return this.xK.getName(var1);
      } else {
         return this.Uv != null ? this.Uv.getName() : super.getName(var1);
      }
   }

   @Override
   public void setName(String var1) {
      if (this.xK != null) {
         this.xK.setName(var1);
      }

      if (this.Dw != null) {
         this.Dw.q(this.Uv, var1);
      }

      super.setName(var1);
   }

   @Override
   public String getAddress(boolean var1) {
      axj var2 = this.q.xK(this);
      return var2 != null ? var2.getAddress(var1) + "::" + this.getName(var1) : this.getName(var1);
   }

   public boolean gO() {
      axl var1 = this.Dw();
      return var1 == null ? false : var1.getInstanceFields().contains(this);
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() != null) {
         if (this.RF != null && var1.getItem() == this.RF) {
            this.za().verifyLocked();
            if (var1.getType() == NativeItemEventType.DISPOSED) {
               this.PV();
            } else {
               this.q(var1.getType());
            }
         } else if (this.xK != null && var1.getItem() == this.xK) {
            if (var1.getType() == NativeItemEventType.DISPOSED) {
               this.xK(Integer.MIN_VALUE, false);
               this.RF = this.xK.getType();
               this.RF.addListener(this);
               this.xK = null;
               this.Ef();
            } else {
               this.q(var1.getType());
            }
         }

         if (this.Dw != null && var1.getItem() == this.Dw) {
            if (var1.getType() == NativeItemEventType.DISPOSED) {
               this.xK(Integer.MIN_VALUE, false);
               this.RF = this.Uv.q();
               this.RF.addListener(this);
               this.Dw = null;
               this.Uv = null;
               this.Ef();
            } else if (var1.getType() == NativeItemEventType.MODIFIED) {
               if (var1.getDetails() == this.Uv) {
                  int var2 = this.Dw.getIndexOfField(this.Uv);
                  if (var2 < 0) {
                     this.xK(Integer.MIN_VALUE, false);
                     this.RF = this.Uv.q();
                     this.RF.addListener(this);
                     this.Dw = null;
                     this.Uv = null;
                     this.Ef();
                  }
               }
            } else if (var1.getDetails() == this.Uv) {
               this.q(var1.getType());
            }
         }
      }
   }

   @Override
   protected void RF() {
      super.RF();
      if (this.RF != null) {
         this.RF.removeListener(this);
      }

      if (this.xK != null) {
         this.xK.removeListener(this);
         this.xK.dispose(true);
      }

      if (this.Dw != null) {
         this.Dw.removeListener(this);
         this.Dw.q(this.Uv);
      }
   }

   @Override
   protected final void c_() {
      super.c_();
      this.q.q(this);
   }

   @Override
   public String toString() {
      return Strings.ff("Field{%s}", this.getName(true));
   }
}
