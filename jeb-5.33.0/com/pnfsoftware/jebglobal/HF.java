package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThreadStackFrame;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class HF implements IDebuggerThreadStackFrame {
   private static final ILogger pC = GlobalLog.getLogger(HF.class);
   private int A;
   private LD kS;
   private ia wS;

   public HF(int var1, LD var2, ia var3) {
      this.A = var1;
      this.kS = var2;
      this.wS = var3;
   }

   @Override
   public long getId() {
      return 1L;
   }

   @Override
   public String getAddress() {
      return Strings.ff("%Xh", this.kS.getProgramCounter());
   }

   @Override
   public List getVariables() {
      return this.getVariables(false);
   }

   @Override
   public List getVariables(boolean var1) {
      ArrayList var2 = new ArrayList();

      for (int var3 = 0; var3 < this.kS.size(); var3++) {
         String var4 = this.kS.getName(var3);
         byte[] var5 = this.kS.getValue(var3);
         if (var4 != null && var5 != null) {
            var2.add(new Ft(this, var4, var5));
         }
      }

      return var2;
   }

   @Override
   public boolean setVariable(IDebuggerVariable var1) {
      RegisterDescriptionEntry var2 = RegisterUtil.getEntryByName(this.kS, var1.getName());
      if (var2 != null) {
         byte[] var3 = (byte[])var1.getTypedValue().getValue();
         this.kS.setValue(var2.getNumber(), var3);
         this.wS.A().pC(this.A, this.kS);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public IDebuggerVariable getInternalParameter(int var1, String var2) {
      throw new UnsupportedOperationException();
   }
}
