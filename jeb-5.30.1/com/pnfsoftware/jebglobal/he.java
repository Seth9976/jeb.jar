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

public class he implements IDebuggerThreadStackFrame {
   private static final ILogger q = GlobalLog.getLogger(he.class);
   private int RF;
   private Ht xK;
   private um Dw;

   public he(int var1, Ht var2, um var3) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
   }

   @Override
   public long getId() {
      return 1L;
   }

   @Override
   public String getAddress() {
      return Strings.ff("%Xh", this.xK.getProgramCounter());
   }

   @Override
   public List getVariables() {
      return this.getVariables(false);
   }

   @Override
   public List getVariables(boolean var1) {
      ArrayList var2 = new ArrayList();

      for (int var3 = 0; var3 < this.xK.size(); var3++) {
         String var4 = this.xK.getName(var3);
         byte[] var5 = this.xK.getValue(var3);
         if (var4 != null && var5 != null) {
            var2.add(new eV(this, var4, var5));
         }
      }

      return var2;
   }

   @Override
   public boolean setVariable(IDebuggerVariable var1) {
      RegisterDescriptionEntry var2 = RegisterUtil.getEntryByName(this.xK, var1.getName());
      if (var2 != null) {
         byte[] var3 = (byte[])var1.getTypedValue().getValue();
         this.xK.setValue(var2.getNumber(), var3);
         this.Dw.RF().q(this.RF, this.xK);
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
