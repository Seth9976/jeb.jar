package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeItem;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class acv implements IEventListener {
   acv(acu var1) {
      this.pC = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      if (var1.getSource() == this.pC.UT) {
         JebEvent var2 = (JebEvent)var1;
         if (var2.getType() == J.UnitChange) {
            ICElement var3 = this.pC.getASTItem();
            Object var4 = null;
            if (var3 instanceof adu) {
               int var5 = ((adu)var3).getIndex();
               var4 = this.pC.UT.A(var5);
            } else if (var3 instanceof aek) {
               int var6 = ((aek)var3).getIndex();
               var4 = this.pC.UT.pC(var6);
            } else if (var3 instanceof aew) {
               int var7 = ((aew)var3).getIndex();
               var4 = this.pC.UT.UT(var7);
            }

            if (var4 != null) {
               acu.pC(this.pC, ((INativeItem)var4).getName(true));
            }

            this.pC.notifyListeners(var2);
         }
      }
   }
}
