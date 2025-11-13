package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItemListener;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.IVirtualTableDefinition;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bbz implements INativeItemListener, IVirtualTableDefinition {
   private static final ILogger oW = GlobalLog.getLogger(bbz.class);
   private static int gO = 0;
   @SerId(1)
   public bby q;
   @SerId(2)
   public long RF;
   @SerId(3)
   public bbv xK;
   @SerId(4)
   public bbi Dw;
   @SerId(5)
   public int Uv;

   public bbz(ITypeManager var1, int var2) {
      this(var1, null);
      this.q(var2);
   }

   public bbz(ITypeManager var1, String var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (var2 == null) {
            var2 = Strings.ff("VT%03d", gO);
         }

         this.q = (bby)var1;
         this.xK = this.q.RF(var2);
         gO++;
      }
   }

   @Override
   public int size() {
      return this.xK.getFieldsCount();
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.Dw != null) {
         if (!this.Dw.q) {
            if (var1.getType() == NativeItemEventType.UPDATED && var1.getItem() == this.xK && var1.getDetails() instanceof bbu) {
               bbu var2 = (bbu)var1.getDetails();
               String var3 = var2.getName();
               int var4 = this.xK.getFields().indexOf(var1.getDetails());
               if (var4 >= 0 && var4 < this.size()) {
                  this.Dw.q(this, var4, var3);
               }
            }
         }
      }
   }

   public void q() {
      this.q(null);
   }

   public void q(int var1) {
      for (int var2 = 0; var2 < var1; var2++) {
         this.q(null);
      }
   }

   public void q(String var1) {
      if (this.Uv != 0) {
         throw new IllegalStateException();
      } else {
         int var2 = this.xK.getFieldsCount();
         if (var1 == null) {
            var1 = "vfunc_" + var2;
         }

         this.xK.q(var1, this.q.createReference(this.q.Uv("void")), -1);
      }
   }

   public void RF() {
      this.Uv = 1;
      this.xK.Uv(3);
      this.xK.addListener(this);
   }

   public void xK() {
      this.Uv = 0;
      this.xK.Uv(0);
      this.xK.removeListener(this);
   }

   public bbv Dw() {
      return this.xK;
   }

   public bbi Uv() {
      return this.Dw;
   }
}
