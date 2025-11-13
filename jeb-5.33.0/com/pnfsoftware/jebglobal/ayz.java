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
public class ayz implements INativeItemListener, IVirtualTableDefinition {
   private static final ILogger E = GlobalLog.getLogger(ayz.class);
   private static int sY = 0;
   @SerId(1)
   public ayy pC;
   @SerId(2)
   public long A;
   @SerId(3)
   public ayv kS;
   @SerId(4)
   public ayi wS;
   @SerId(5)
   public int UT;

   public ayz(ITypeManager var1, String var2) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         if (var2 == null) {
            var2 = Strings.ff("VT%03d", sY);
         }

         this.pC = (ayy)var1;
         this.kS = this.pC.A(var2);
         sY++;
      }
   }

   @Override
   public int size() {
      return this.kS.getFieldsCount();
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (this.wS != null) {
         if (!this.wS.pC) {
            if (var1.getType() == NativeItemEventType.UPDATED && var1.getItem() == this.kS && var1.getDetails() instanceof ayu) {
               ayu var2 = (ayu)var1.getDetails();
               String var3 = var2.getName();
               int var4 = this.kS.getFields().indexOf(var1.getDetails());
               if (var4 >= 0 && var4 < this.size()) {
                  this.wS.pC(this, var4, var3);
               }
            }
         }
      }
   }

   public void pC(String var1) {
      if (this.UT != 0) {
         throw new IllegalStateException();
      } else {
         int var2 = this.kS.getFieldsCount();
         if (var1 == null) {
            var1 = "vfunc_" + var2;
         }

         this.kS.pC(var1, this.pC.createReference(this.pC.UT("void")), -1);
      }
   }

   public void pC() {
      this.UT = 1;
      this.kS.UT(3);
      this.kS.addListener(this);
   }

   public void A() {
      this.UT = 0;
      this.kS.UT(0);
      this.kS.removeListener(this);
   }

   public ayv kS() {
      return this.kS;
   }

   public ayi wS() {
      return this.wS;
   }
}
