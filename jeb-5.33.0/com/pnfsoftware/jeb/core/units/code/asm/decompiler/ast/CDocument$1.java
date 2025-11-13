package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.util.events.IEvent;
import com.pnfsoftware.jeb.util.events.IEventListener;

class CDocument$1 implements IEventListener {
   CDocument$1(CDocument var1) {
      this.this$0 = var1;
   }

   @Override
   public void onEvent(IEvent var1) {
      JebEvent var2 = (JebEvent)var1;
      if (var2.getType() == J.UnitChange) {
         this.this$0.notifyListeners(var2);
      }
   }
}
