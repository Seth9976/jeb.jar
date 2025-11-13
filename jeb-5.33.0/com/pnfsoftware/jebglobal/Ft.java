package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThreadStackFrame;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueRaw;
import com.pnfsoftware.jeb.util.format.Strings;

public class Ft implements IDebuggerVariable {
   private IDebuggerThreadStackFrame pC;
   private String A;
   private byte[] kS;

   public Ft(IDebuggerThreadStackFrame var1, String var2, byte[] var3) {
      if (var1 != null && var2 != null && var3 != null) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      } else {
         throw new NullPointerException();
      }
   }

   @Override
   public String getName() {
      return this.A;
   }

   @Override
   public String getAlternateName() {
      return null;
   }

   @Override
   public int getFlags() {
      return 0;
   }

   @Override
   public ITypedValue getTypedValue() {
      return new ValueRaw(this.kS);
   }

   @Override
   public boolean setTypedValue(ITypedValue var1) {
      Object var2 = var1.getValue();
      if (var2 instanceof byte[] && this.kS.length == ((byte[])var2).length) {
         this.kS = (byte[])var2;
         return this.pC.setVariable(this);
      } else {
         return false;
      }
   }

   @Override
   public boolean canEditValue() {
      return true;
   }

   @Override
   public boolean canEditType() {
      return false;
   }

   @Override
   public boolean setTypeHint(String var1) {
      return false;
   }

   @Override
   public String format() {
      return Strings.ff("name=%s,value=%s", this.getName(), this.getTypedValue().format());
   }

   @Override
   public String toString() {
      return Strings.ff("name=%s,value=%s", this.getName(), this.getTypedValue());
   }
}
